package com.aimdb.container.BPlus.node;

/**
 * Created by bbking on 17-1-7.
 * 叶子节点
 */
public class LeafNode<K extends Comparable<K>, V> extends BPlusTreeNode<K> {

    private Object[]  values;

    public V getValue(int index) {
        return  (V)this.values[index];
    }

    public void setValue(int index, Object value) {
        this.values[index] = value;
    }

    public BPlusTreeNodeType getNodeType() {
        return BPlusTreeNodeType.LeafNode;
    }

    public int search(K key) {
        for (int i = 0; i < this.getKeyNum(); ++i) {
            int cmp = this.getKey(i).compareTo(key);
            if (cmp == 0) {
                return i;
            }
            else if (cmp > 0) {
                return -1;
            }
        }
        return -1;
    }


    public void insertKey(K key, V value) {
        int index = 0;
        while (index < this.getKeyNum() && this.getKey(index).compareTo(key) < 0){
            ++index;
        }
        this.insertAt(index, key, value);
    }

    private void insertAt(int index, K key, V value) {
        for (int i = this.getKeyNum() - 1; i >= index; --i) {
            this.setKey(i + 1, this.getKey(i));
            this.setValue(i + 1, this.getValue(i));
        }

        // insert new key and value
        this.setKey(index, key);
        this.setValue(index, value);
        ++this.keyNum;
    }


    protected BPlusTreeNode split() {
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

    public boolean delete(K key) {
        int index = this.search(key);
        if (index == -1)
            return false;

        this.deleteAt(index);
        return true;
    }

    private void deleteAt(int index) {
        int i = index;
        for (i = index; i < this.getKeyNum() - 1; ++i) {
            this.setKey(i, this.getKey(i + 1));
            this.setValue(i, this.getValue(i + 1));
        }
        this.setKey(i, null);
        this.setValue(i, null);
        --this.keyNum;
    }

}
