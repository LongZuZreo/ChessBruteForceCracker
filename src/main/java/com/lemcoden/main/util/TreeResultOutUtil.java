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
    public static void  successAllStepOut(TreeNode<ChessBoardLayoutEntity> treeNode, BitMapOperator bitMapOperator){
        if (!isLock){
            isLock = true;
        }else{
            return;
        }
        Stack<ChessBoardLayoutEntity> stack = new Stack<>();
        stack.push(treeNode.getContent());
        while (treeNode.hasParent()){
            treeNode = treeNode.getParentNode();
            stack.push(treeNode.getContent());
        }

        while (!stack.empty()){
            stepNum++;
            System.out.println("-------------------------------------------");
            System.out.println("第"+stepNum+"步"+"\n");
            ChessBoardLayoutEntity pop = stack.pop();
            String boardLayoutStr = bitMapOperator.bitMap2String(pop.getChessBoardLayoutMap());
            System.out.println(boardLayoutStr);
        }

    }


    /**
     * 输出赢棋的所有步骤
     */
    public static void  successAllStepOut(TreeNode<ChessBoardLayoutEntity> treeNode, BitMapOperator bitMapOperator, List list){
        if (!isLock){
            isLock = true;
        }else{
            return;
        }
        Stack<ChessBoardLayoutEntity> stack = new Stack<>();
        stack.push(treeNode.getContent());
        while (treeNode.hasParent()){
            treeNode = treeNode.getParentNode();
            stack.push(treeNode.getContent());
        }

        while (!stack.empty()){
            stepNum++;
            list.add("-----------------------------------------------");
            list.add("第"+stepNum+"步");
            ChessBoardLayoutEntity pop = stack.pop();
            String boardLayoutStr = bitMapOperator.bitMap2String(pop.getChessBoardLayoutMap());
            String[] split = boardLayoutStr.split("\n");
            for (String s : split) {
                list.add(s);
            }

        }

    }
}
