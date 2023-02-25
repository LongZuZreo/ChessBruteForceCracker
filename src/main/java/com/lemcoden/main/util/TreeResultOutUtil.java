package com.lemcoden.main.util;

import com.lemcoden.main.entity.ChessBoardLayoutEntity;
import com.lemcoden.main.tree.TreeNode;
import com.lemcoden.main.product.BitMapOperator;

import java.awt.*;
import java.util.Stack;

/**
 * 将棋盘走势结果输出
 */
public class TreeResultOutUtil {

    private static int stepNum = 0;

    private static boolean isLock = false;

    /**
     * 输出赢棋的所有步骤
     */
    public static void successAllStepOut(TreeNode<ChessBoardLayoutEntity> treeNode, BitMapOperator bitMapOperator) {
        if (!isLock) {
            isLock = true;
        } else {
            return;
        }
        Stack<ChessBoardLayoutEntity> stack = new Stack<>();
        stack.push(treeNode.getContent());
        while (treeNode.hasParent()) {
            treeNode = treeNode.getParentNode();
            stack.push(treeNode.getContent());
        }

        while (!stack.empty()) {
            stepNum++;
            System.out.println("-------------------------------------------");
            System.out.println("第" + stepNum + "步" + "\n");
            ChessBoardLayoutEntity pop = stack.pop();
            String boardLayoutStr = bitMapOperator.bitMap2String(pop.getChessBoardLayoutMap());
            System.out.println(boardLayoutStr);
        }

    }


    /**
     * 输出赢棋的所有步骤
     */
    public static void successAllStepOut(TreeNode<ChessBoardLayoutEntity> treeNode, BitMapOperator bitMapOperator, TextArea textArea) {
        if (!isLock) {
            isLock = true;
        } else {
            return;
        }
        Stack<ChessBoardLayoutEntity> stack = new Stack<>();
        stack.push(treeNode.getContent());
        while (treeNode.hasParent()) {
            treeNode = treeNode.getParentNode();
            stack.push(treeNode.getContent());
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            stepNum++;
            sb.append("-----------------------------------------------\n");
            sb.append("第" + stepNum + "步"+"\n");
            ChessBoardLayoutEntity pop = stack.pop();
            String boardLayoutStr = bitMapOperator.bitMap2String(pop.getChessBoardLayoutMap());
            sb.append(boardLayoutStr);
        }
        textArea.setText(sb.toString());

    }

    public static void deadOut(String dead,TextArea textArea) {
        textArea.setText(dead);
    }
}
