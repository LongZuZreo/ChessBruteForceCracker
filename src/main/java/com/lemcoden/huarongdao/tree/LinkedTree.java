package com.lemcoden.huarongdao.tree;

import java.util.*;

/**
 * 树结构，并非二叉树，每个父节点可以有多个子节点
 *
 * @param <E>
 */
public class LinkedTree<E>{

    private int depth;

    private List<TreeNode<E>> list = new ArrayList<>();

    private TreeNode<E> topNode;

    public LinkedTree(E topNodeContent) {
        topNode = new TreeNode(topNodeContent);
        topNode.makeMeTop();
        depth = 1;
        list.add(topNode);
    }

    public TreeNode<E> getTopNode() {
        return topNode;
    }

    public boolean addChildNode(TreeNode<E> parentNode, TreeNode<E> childNode) {

        //如果父节点或子节点为空，返回错误
        if (parentNode == null || childNode == null) {
            return false;
        }

        if (parentNode.isTopNode) {
            setDepth(2) ;
            childNode.setCurDepth(2);
        } else {
            int curDepth = parentNode.getCurDepth();
            childNode.setCurDepth(curDepth + 1);
            setDepth(curDepth + 1);
        }

        list.add(childNode);

        parentNode.addChildNode(childNode);
        return true;
    }

    public boolean deleteNode(TreeNode<E> treeNode) {
        return treeNode.getParentNode()
                .getChildNodes()
                .remove(treeNode);
    }

    /**
     * 弹出当前树深度的所有叶子节点
     * @return
     */
    public List<TreeNode<E>> popCurDepthChildNodes(){
        List<TreeNode<E>> currentList = Arrays.asList(new TreeNode[list.size()]);
        Collections.copy(currentList,list);
        list.clear();
        return currentList;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }
}
