package com.aimdb.container.BPlus.node;

/**
 * Created by bbking on 17-1-8.
 */
public enum BPlusTreeNodeType {
    InnerNode(0,"内部节点"), LeafNode(1,"叶子节点");
    private  int code;
    private  String desc;
    BPlusTreeNodeType(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
