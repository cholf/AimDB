package com.aimdb.container.BPlus.node;

/**
 * Created by bbking on 17-1-7.
 * 索引节点
 */
public class InnerNode<K extends Comparable<K>> extends BPlusTreeNode<K> {
    private Object child[];

    public InnerNode() {

    }

    public BPlusTreeNode<K> getChild(int index) {
        return null;
    }

    public BPlusTreeNode<K> setChild() {
        return null;
    }

    public void insert() {

    }

    public void delete() {

    }

    public void search() {

    }

    public BPlusTreeNodeType getNodeType() {
        return null;
    }

    public int search(K key) {
        return 0;
    }

    protected BPlusTreeNode<K> split() {
        return null;
    }

    protected BPlusTreeNode<K> pushUpKey(K key, BPlusTreeNode<K> leftChild, BPlusTreeNode<K> rightNode) {
        return null;
    }

    protected void processChildrenTransfer(BPlusTreeNode<K> borrower, BPlusTreeNode<K> lender, int borrowIndex) {

    }

    protected BPlusTreeNode<K> processChildrenFusion(BPlusTreeNode<K> leftChild, BPlusTreeNode<K> rightChild) {
        return null;
    }

    protected void fusionWithSibling(K sinkKey, BPlusTreeNode<K> rightSibling) {

    }

    protected K transferFromSibling(K sinkKey, BPlusTreeNode<K> sibling, int borrowIndex) {
        return null;
    }
}
