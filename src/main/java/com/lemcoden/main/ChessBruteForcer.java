package com.lemcoden.main;

import com.lemcoden.main.entity.ChessPosition;
import com.lemcoden.main.entity.ChessBoardLayoutEntity;
import com.lemcoden.main.exception.ChessException;
import com.lemcoden.main.tree.LinkedTree;
import com.lemcoden.main.tree.TreeNode;
import com.lemcoden.huarongdao.util.TreeResultOutUtil;
import com.lemcoden.main.factory.BruteForceFactory;
import com.lemcoden.main.product.ChessOperator;
import com.lemcoden.main.product.ChessPlayer;

import java.util.List;


/**
 * 暴力破解器
 */
public class ChessBruteForcer extends ChessBoardStateContext {

    //用数结构存储所有棋盘布局
    private final LinkedTree<ChessBoardLayoutEntity> allStepTree;
    private final ChessPlayer chessPlayer;
    private final ChessOperator chessOperator;

    public ChessBruteForcer(byte[][] bitMap, BruteForceFactory bruteForceFactory) {
        super(bitMap);
        System.out.println("初始棋盘\n" + bitMapOperator.bitMap2String(bitMap));
        //初始化树结构，设置初始棋盘
        allStepTree = new LinkedTree(new ChessBoardLayoutEntity(bitMap));
        //初始化棋盘操作器，棋手（用于移动棋子），棋子操作器
        bitMapOperator = bruteForceFactory.generateBitMapOperator();
        chessPlayer = bruteForceFactory.generateChessPlayer(this);
        chessOperator  = bruteForceFactory.generateChessOperator();
    }

    /**
     * 运行破解程序
     * @throws ChessException
     */
    public void caculateRun() throws ChessException {
        while (!isMissionComplete) {
            //获取当前树深度的所有叶子节点
            for (TreeNode<ChessBoardLayoutEntity> curDepthChildNode : allStepTree.popCurDepthChildNodes()) {
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
            }
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
        TreeResultOutUtil.successAllStepOut(treeNode, bitMapOperator);
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


}
