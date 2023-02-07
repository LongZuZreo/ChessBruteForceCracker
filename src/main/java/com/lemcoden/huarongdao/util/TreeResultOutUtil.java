package com.lemcoden.huarongdao.util;

import com.lemcoden.huarongdao.entity.ChessBoardLayoutEntity;
import com.lemcoden.huarongdao.tree.TreeNode;
import com.lemcoden.main.product.BitMapOperator;

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



}
