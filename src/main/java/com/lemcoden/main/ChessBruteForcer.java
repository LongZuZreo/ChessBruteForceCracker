package com.lemcoden.main;

import com.lemcoden.main.entity.ChessPosition;
import com.lemcoden.main.entity.ChessBoardLayoutEntity;
import com.lemcoden.main.exception.ChessException;
import com.lemcoden.main.tree.LinkedTree;
import com.lemcoden.main.tree.TreeNode;
import com.lemcoden.main.util.TreeResultOutUtil;
import com.lemcoden.main.factory.BruteForceFactory;
import com.lemcoden.main.product.ChessOperator;
import com.lemcoden.main.product.ChessPlayer;

import java.awt.*;
import java.util.List;


/**
 * 暴力破解器
 */
public class ChessBruteForcer extends ChessBoardStateContext {

    //用数结构存储所有棋盘布局
    private final LinkedTree<ChessBoardLayoutEntity> allStepTree;
    private final ChessPlayer chessPlayer;
    private final ChessOperator chessOperator;
    private TextArea textArea;

    public ChessBruteForcer(byte[][] bitMap, BruteForceFactory bruteForceFactory) {
        super(bitMap);
        bitMapOperator = bruteForceFactory.generateBitMapOperator();
        System.out.println("初始棋盘\n" + bitMapOperator.bitMap2String(bitMap));
        //初始化树结构，设置初始棋盘
        allStepTree = new LinkedTree(new ChessBoardLayoutEntity(bitMap));
        //初始化棋盘操作器，棋手（用于移动棋子），棋子操作器
        chessPlayer = bruteForceFactory.generateChessPlayer(this);
        chessOperator  = bruteForceFactory.generateChessOperator();
    }

    int n;
    int m;
    /**
     * 运行破解程序
     * @throws ChessException
     */
    public void caculateRun() throws ChessException {
        n++;
        while (!isMissionComplete) {
            System.out.println("第"+n+"层");
            m=1;
            //获取当前树深度的所有叶子节点
            List<TreeNode<ChessBoardLayoutEntity>> treeNodes = allStepTree.popCurDepthChildNodes();
            if (treeNodes.size() == 0){
                isMissionComplete =  true;
                TreeResultOutUtil.deadOut("该棋盘无解",textArea);
            }

            for (TreeNode<ChessBoardLayoutEntity> curDepthChildNode :treeNodes) {
                System.out.println("第"+m+"个");
                System.out.println(bitMapOperator.bitMap2String(curDepthChildNode.getContent().getChessBoardLayoutMap()));
                byte[][] curBitMap = curDepthChildNode.getContent().getChessBoardLayoutMap();
                List<ChessPosition> movableChessLocate = chessOperator.getMovableChessLocate(curBitMap);
                //获取并且验证可移动棋子定位
                if (chessOperator.checkMovableChessPositions(movableChessLocate)) {
                    //走出下一步棋子，并将下一步棋子的所有可能的棋盘布局写入到树结构的叶子节点当中当中
                    chessPlayer.moveChessToNextStep(movableChessLocate, curDepthChildNode);
                } else {
                    throw new ChessException("棋子空格出现问题\n" + bitMapOperator.bitMap2String(curBitMap) + "\n\n" +
                            bitMapOperator.bitMap2String(curDepthChildNode.getParentNode().getContent().getChessBoardLayoutMap()));
                }
                m++;
            }
            n++;
        }

    }


    /**
     * 获取树节点中的棋盘内容，如果输入数据为空，则返回初始棋盘
     *
     * @param treeNode
     * @return
     */
    @Override
    public byte[][] getBitMap(TreeNode<ChessBoardLayoutEntity> treeNode) {
        return treeNode == null ? initBitMap : treeNode.getContent().getChessBoardLayoutMap();
    }


    public void outPutAllStep(TreeNode<ChessBoardLayoutEntity> treeNode) {
        if (textArea == null) {
            TreeResultOutUtil.successAllStepOut(treeNode, bitMapOperator);
        }else{
            TreeResultOutUtil.successAllStepOut(treeNode, bitMapOperator, textArea);
        }
    }

    /**
     * 记录行走后的棋盘，棋子
     * @param bitMap
     * @param parentNode
     * @return
     */
    public TreeNode<ChessBoardLayoutEntity> recordBitMap(byte[][] bitMap, TreeNode<ChessBoardLayoutEntity> parentNode) {
        ChessBoardLayoutEntity chessBoardLayoutEntity = new ChessBoardLayoutEntity(bitMap);
        if (parentNode == null) {
            parentNode = allStepTree.getTopNode();
        }
        TreeNode<ChessBoardLayoutEntity> childNode = new TreeNode<>(chessBoardLayoutEntity);

        allStepTree.addChildNode(parentNode, childNode);

        uuids.put(bitMapOperator.bitMap2IDStr(bitMap), true);
        return childNode;
    }

    public void setOutView(TextArea textArea) {
        this.textArea = textArea;
    }
}
