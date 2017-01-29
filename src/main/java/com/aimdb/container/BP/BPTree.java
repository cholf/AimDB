package com.aimdb.container.BP;

import java.io.Serializable;

/**
 * Created by bbking
 */
public class BPTree<K extends Comparable<K>, V> implements Serializable {

    private static final int M = 4;
    private Node root;
    private int height;
    private int kvnums;// k-v对数

    public BPTree() {
        root = new Node(null, null, 0);
    }

    private static final class Node implements Serializable {
        private int childSize;
        private Node[] children = new Node[M];
        private Comparable key;
        private Object value;

        public Node setKey(Comparable key) {
            this.key = key;
            return this;
        }

        public Node setValue(Object value) {
            this.value = value;
            return this;
        }

        private Node(Comparable key, Object value, int size) {
            this.key = key;
            this.value = value;
            childSize = size;
        }
    }

    /*
     * private static class Entry implements Serializable { private Comparable key; private final Object value; private
     * Node next; public Entry(Comparable key, Object value, Node next) { this.key = key; this.value = value; this.next
     * = next; } }
     */

    // #####################################
    public void put(K key, V val) {
        if (key == null)
            throw new IllegalArgumentException("key null");
        Node newNode = insert(root, key, val, height);
        kvnums++;
        if (newNode == null)
            return;

        // 父分裂
        Node t = new Node(null, null, 2);
        root.setKey(root.children[0].key);
        t.children[0] = root;
        t.children[1] = newNode;
        t.setKey(t.children[0].key);
        root = t;
        height++;
    }

    // 插入
    private Node insert(Node oldNode, K key, V val, int level) {
        Node newNode = new Node(key, val, 2);
        if (level == 0) {
            return handSameLevel(key, newNode, oldNode);
        } else {
            return handDffLevel(oldNode, key, val, level, newNode);
        }
    }

    // 不同级别处理
    private Node handDffLevel(Node oldNode, K key, V val, int level, Node newNode) {
        int index = 0;
        for (int j = 0; j < oldNode.childSize; j++) {
            if ((j + 1 == oldNode.childSize) || smaller(key, oldNode.children[j + 1].key)) {// 最后一个孩子，或者小于j+1个孩子
                index = j + 1;
                Node temp = oldNode.children[j];
                Node u = insert(temp, key, val, level - 1);
                if (u == null)
                    return null;
                newNode = u;
                break;
            }
        }
        return exChange(oldNode, index, newNode);
    }

    // 相同级层,寻找插入位置
    // 要么最后一个了,要么找到比自己大的那个之前插入
    private Node handSameLevel(K key, Node newNode, Node oldNode) {
        int i = 0;
        for (; i < oldNode.childSize; i++) {
            if (smaller(key, oldNode.children[i].key))
                break;
        }
        return exChange(oldNode, i, newNode);
    }

    // 交换赋值
    private Node exChange(Node oldNode, int index, Node newNode) {
        // 移位置
        for (int i = oldNode.childSize; i > index; i--)
            oldNode.children[i] = oldNode.children[i - 1];
        // 赋值
        oldNode.children[index] = newNode;
        oldNode.childSize++;
        if (oldNode.childSize < M)
            return null;
        else
            return split(oldNode);
    }

    // 分裂
    /**
     * 1.新建一个节点（孩子策略M/2）, 2.被分裂节点孩子策略设置（M/2） 3.拷贝分裂的节点中大于M/2的节点孩子到新节点 4.置空/移除被分裂的分出去的孩子
     */
    private Node split(Node oldNode) {
        Node newer = new Node(oldNode.children[2].key, null, M / 2);
        oldNode.childSize = M / 2;
        for (int j = 0; j < M / 2; j++) {
            newer.children[j] = oldNode.children[M / 2 + j];
            oldNode.children[M / 2 + j] = null;// 貌似有bug
        }
        return newer;
    }

    private boolean smaller(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }

    // ××××××××××××××××××××××××××××××××
    public V get(K key) {
        if (key == null)
            throw new IllegalArgumentException("args null");
        V v = search(root, key, height);
        return v;
    }

    private V search(Node node, K key, int level) {
        Node[] children = node.children;
        if (level == 0) {
            for (int j = 0; j < node.childSize; j++) {
                if (equalsCompare(key, (children[j].key).toString().trim()))
                    return (V) children[j].value;
            }
        } else {
            for (int j = 0; j < node.childSize; j++) {
                if (j + 1 == node.childSize || smaller(key, children[j + 1].key))
                    return search(children[j], key, level - 1);
            }
        }
        return null;
    }

    private boolean equalsCompare(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }

}
