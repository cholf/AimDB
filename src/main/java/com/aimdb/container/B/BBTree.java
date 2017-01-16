package com.aimdb.container.B;

import java.io.Serializable;

/**
 * Created by bbking
 */
public class BBTree<K extends Comparable<K>, V>  implements Serializable {

    private static final int M = 4;
    private Node root;
    private int height;
    private int kvnums;//k-v对数


    public BBTree() {
        root = new Node(0);
    }

    private static final class Node implements Serializable {
        private int childSize;
        private Entry[] children = new Entry[M];
        private Node(int size) {
            childSize = size;
        }
    }

    private static class Entry implements Serializable {
        private Comparable key;
        private final Object value;
        private Node next;
        public Entry(Comparable key, Object value, Node next) {
            this.key  = key;
            this.value = value;
            this.next = next;
        }
    }


    //#####################################
    public void put(K key, V val) {
        if (key == null) throw new IllegalArgumentException("key null");
        Node newNode = insert(root, key, val, height);
        kvnums++;
        if (newNode == null) return;

        //父分裂
        Node t = new Node(2);
        t.children[0] = new Entry(root.children[0].key, null, root);
        t.children[1] = new Entry(newNode.children[0].key, null, newNode);
        root = t;
        height++;
    }


    //插入
    private Node insert(Node n, K key, V val, int level) {
        int j=0;
        Entry t = new Entry(key, val, null);
        if (level == 0) {
            return handSameLevel(j,key,t,n);
        } else {
            return handDffLevel(j,n,key,val,level,t);
        }
    }

    //不同级别处理
    private  Node handDffLevel(int j,Node node,K key,V val,int level,Entry entry){
        for (j = 0; j < node.childSize; j++) {
            if ((j+1 == node.childSize) || smaller(key, node.children[j+1].key)) {//最后一个孩子，或者小于j+1个孩子
                Node u = insert(node.children[j++].next, key, val, level-1);
                if (u == null) return null;
                entry.key = u.children[0].key;
                entry.next = u;
                break;
            }
        }
        return  exChange(node,j,entry);
    }

    // 相同级层,寻找插入位置
    // 要么最后一个了,要么找到比自己大的那个之前插入
    private Node  handSameLevel(int j,K key,Entry e,Node n){
        for (j = 0; j < n.childSize; j++) {
            if (smaller(key, n.children[j].key)) break;
        }
        return  exChange(n,j,e);
    }

    //交换赋值
    private Node  exChange(Node h,int index, Entry e){
        //移位置
        for (int i = h.childSize; i > index; i--)
            h.children[i] = h.children[i-1];
        //赋值
        h.children[index] = e;
        h.childSize++;
        if (h.childSize < M) return null;
        else         return split(h);
    }

    // 分裂
    /**
     * 1.新建一个节点（孩子策略M/2）,
     * 2.被分裂节点孩子策略设置（M/2）
     * 3.拷贝分裂的节点中大于M/2的节点孩子到新节点
     * 4.置空/移除被分裂的分出去的孩子
     */
    private Node split(Node n) {
        Node newer = new Node(M/2);
        n.childSize = M/2;
        for (int j = 0; j < M/2; j++){
            newer.children[j] = n.children[M/2+j];
            n.children[M/2+j]=null;//貌似有bug
        }
        return newer;
    }

    private boolean smaller(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }


    //××××××××××××××××××××××××××××××××
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("args null");
        V v = search(root, key, height);
        return v;
    }

    private V search(Node node, K key, int level) {
        Entry[] children = node.children;
        if (level == 0) {
            for (int j = 0; j < node.childSize; j++) {
                if (equalsCompare(key, (children[j].key).toString().trim())) return (V) children[j].value;
            }
        } else {
            for (int j = 0; j < node.childSize; j++) {
                if (j+1 == node.childSize || smaller(key, children[j+1].key))
                    return search(children[j].next, key, level-1);
            }
        }
        return null;
    }

    private boolean equalsCompare(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }


}
