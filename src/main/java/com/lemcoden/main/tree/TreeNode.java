package com.lemcoden.main.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <E>
 */
public class TreeNode<E> {

    private int curDepth;

    boolean isTopNode = false;

    private TreeNode<E> parentNode;

    private List<TreeNode<E>> childNodes;

    private E content;

    public TreeNode(E content) {
        this.content = content;
    }

    public TreeNode(E content, TreeNode<E> parentNode) {
        this.content = content;
        this.parentNode = parentNode;
    }

    /**
     * 设置节点内容
     *
     * @return
     */
    public boolean setContent(E content) {
        this.content = content;
        return true;
    }

    /**
     * 清除所有内容
     *
     * @return
     */
    public boolean clearAll() {
        this.content = null;
        this.childNodes = null;
        return true;
    }

    public boolean hasChild() {
        return childNodes != null && childNodes.size() > 0;
    }

    public boolean hasParent(){
        return parentNode != null;
    }

    public List<TreeNode<E>> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<TreeNode<E>> childNodes) {
        this.childNodes = childNodes;
    }

    public void addChildNode(TreeNode<E> childNode) {
        if (childNodes == null) {
            childNodes = new ArrayList<>();
        }
        childNode.setParentNode(this);
        childNodes.add(childNode);
    }

    public void getChildNode(int index) {
        childNodes.get(index);
    }

    public TreeNode<E> getParentNode() {
        return parentNode;
    }

    public void setParentNode(TreeNode<E> parentNode) {
        this.parentNode = parentNode;
    }

    public boolean isTopNode() {
        return isTopNode;
    }

    public void makeMeTop(){
        isTopNode = true;
    };

    public E getContent() {
        return content;
    }

    public void setCurDepth(int curDepth) {
        this.curDepth = curDepth;
    }

    public int getCurDepth() {
        return curDepth;
    }
}
