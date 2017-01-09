package com.aimdb.container.BPlus;

import com.aimdb.container.BPlus.node.BPlusTreeNode;
import com.aimdb.container.BPlus.node.BPlusTreeNodeType;
import com.aimdb.container.BPlus.node.InnerNode;
import com.aimdb.container.BPlus.node.LeafNode;

/**
 * Created by bbking on 17-1-7.
 */
public class BPlusTree<K extends Comparable<K>,V> {

    private BPlusTreeNode<K> root;

    public  BPlusTree(){
        this.root = new InnerNode<K>();
    }

    /**
     * 插入
     */
    public void insert(K key, V value) {
        LeafNode<K, V> leaf = this.findLeafNodeShouldContainKey(key);
        leaf.insertKey(key, value);

        if (leaf.isOverflow()) {
            BPlusTreeNode<K> n = leaf.dealOverflow();
            if (n != null)
                this.root = n;
        }
    }

    /**
     * 搜索
     */
    public V search(K key) {
        LeafNode<K, V> leaf = this.findLeafNodeShouldContainKey(key);

        int index = leaf.search(key);
        return (index == -1) ? null : leaf.getValue(index);
    }

    /**
     * 删除
     */
    public void delete(K key) {
        LeafNode<K, V> leaf = this.findLeafNodeShouldContainKey(key);

        if (leaf.delete(key) && leaf.isUnderflow()) {
            BPlusTreeNode<K> n = leaf.dealUnderflow();
            if (n != null)
                this.root = n;
        }
    }

    /**
     * 查找叶子几点
     */
    @SuppressWarnings("unchecked")
    private LeafNode<K, V> findLeafNodeShouldContainKey(K key) {
        BPlusTreeNode<K> node = this.root;
        while (BPlusTreeNodeType.InnerNode.getCode() == node.getNodeType().getCode()) {
            node = ((InnerNode<K>) node).getChild(node.search(key));
        }

        return (LeafNode<K, V>) node;
    }

}
