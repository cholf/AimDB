package com.aimdb.container.BPlus.node;

/**
 * Created by bbking on 17-1-7.
 * BP+树节点,下分两种
 * 1.索引节点，负责找到叶子节点
 * 2.叶子节点，负责存储实际信息
 */
 public abstract class BPlusTreeNode<K extends Comparable<K>> {
    protected  Object [] keys;
    protected  int keyNum;
    protected BPlusTreeNode<K> parent;
    protected BPlusTreeNode<K> left;
    protected BPlusTreeNode<K> right;

    public BPlusTreeNode<K> getParent() {
        return parent;
    }

    public BPlusTreeNode<K> getLeft() {
        return left;
    }

    public BPlusTreeNode setLeft(BPlusTreeNode<K> left) {
        this.left = left;
        return this;
    }

    public BPlusTreeNode<K> getRight() {
        return right;
    }

    public BPlusTreeNode setRight(BPlusTreeNode<K> right) {
        this.right = right;
        return this;
    }

    public abstract BPlusTreeNodeType getNodeType();
    public abstract int search(K key);
    protected abstract BPlusTreeNode<K> split();

    public  int  getKeyNum(){
        return  this.keyNum;
    }

    public K getKey(int  index){
        return (K)this.keys[index];
    }

    public void setKey(int  index,K key){
         this.keys[index] =key;
    }

    public void setParent(BPlusTreeNode<K> parent){
        this.parent = parent;
    }

    public boolean isOverflow() {
        return this.getKeyNum() == this.keys.length;
    }

    public BPlusTreeNode<K> dealOverflow() {
        int midIndex = this.getKeyNum() / 2;
        K upKey = this.getKey(midIndex);

        BPlusTreeNode<K> newRNode = this.split();

        if (this.getParent() == null) {
            this.setParent(new InnerNode<K>());
        }
        newRNode.setParent(this.getParent());

        // maintain links of sibling nodes
        newRNode.setLeft(this);
        newRNode.setRight(this.right);
        if (this.getRight() != null)
            this.getRight().setLeft(newRNode);
        this.setRight(newRNode);

        // push up a key to parent internal node
        return this.getParent().pushUpKey(upKey, this, newRNode);
    }

    public boolean isUnderflow() {
        return this.getKeyNum() < (this.keys.length / 2);
    }

    public BPlusTreeNode<K> dealUnderflow() {
        if (this.getParent() == null)
            return null;

        // try to borrow a key from sibling
        BPlusTreeNode<K> leftSibling = this.getLeft();
        if (leftSibling != null && leftSibling.canLendAKey()) {
            this.getParent().processChildrenTransfer(this, leftSibling, leftSibling.getKeyNum() - 1);
            return null;
        }

        BPlusTreeNode<K> rightSibling = this.getRight();
        if (rightSibling != null && rightSibling.canLendAKey()) {
            this.getParent().processChildrenTransfer(this, rightSibling, 0);
            return null;
        }

        // Can not borrow a key from any sibling, then do fusion with sibling
        if (leftSibling != null) {
            return this.getParent().processChildrenFusion(leftSibling, this);
        }
        else {
            return this.getParent().processChildrenFusion(this, rightSibling);
        }
    }

    /**
     * 能否借出一个key
     * @return
     */
    public boolean canLendAKey() {
        return this.getKeyNum() > (this.keys.length / 2);
    }



    protected abstract BPlusTreeNode<K> pushUpKey(K key, BPlusTreeNode<K> leftChild, BPlusTreeNode<K> rightNode);

    protected abstract void processChildrenTransfer(BPlusTreeNode<K> borrower, BPlusTreeNode<K> lender, int borrowIndex);

    protected abstract BPlusTreeNode<K> processChildrenFusion(BPlusTreeNode<K> leftChild, BPlusTreeNode<K> rightChild);

    protected abstract void fusionWithSibling(K sinkKey, BPlusTreeNode<K> rightSibling);

    protected abstract K transferFromSibling(K sinkKey, BPlusTreeNode<K> sibling, int borrowIndex);

}
