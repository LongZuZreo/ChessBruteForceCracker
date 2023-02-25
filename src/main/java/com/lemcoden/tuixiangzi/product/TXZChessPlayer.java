package com.lemcoden.tuixiangzi.product;

import com.lemcoden.main.constant.ChessType;
import com.lemcoden.main.entity.ChessBoardLayoutEntity;
import com.lemcoden.main.entity.ChessPosition;
import com.lemcoden.main.product.BitMapOperator;
import com.lemcoden.main.tree.TreeNode;
import com.lemcoden.main.ChessBoardStateContext;
import com.lemcoden.main.product.ChessOperator;
import com.lemcoden.main.product.ChessPlayer;
import com.lemcoden.main.util.TreeResultOutUtil;
import com.lemcoden.tuixiangzi.constant.TXZChessType;

import java.util.HashMap;
import java.util.List;

public class TXZChessPlayer implements ChessPlayer {

    private final BitMapOperator bitMapOperator;
    private final HashMap<String, Boolean> uuids;
    private final ChessBoardStateContext context;
    TXZChessOperator chessOperator;

    public TXZChessPlayer(ChessBoardStateContext context, ChessOperator chessOperator) {
        bitMapOperator = context.bitMapOperator;
        this.chessOperator = (TXZChessOperator) chessOperator;
        uuids = context.uuids;
        this.context = context;
    }

    @Override
    public void moveChessToNextStep(List<ChessPosition> movableChessPositions, TreeNode<ChessBoardLayoutEntity> parentNode) {
        ChessPosition iChessPosition = movableChessPositions.get(0);
        byte[][] chessBoardLayoutMap = parentNode.getContent().getChessBoardLayoutMap();
        byte iChess = chessOperator.getChess(chessBoardLayoutMap, iChessPosition);
        byte upChess = chessOperator.getUpChess(chessBoardLayoutMap, iChessPosition);
        byte leftChess = chessOperator.getLeftChess(chessBoardLayoutMap, iChessPosition);
        byte rightChess = chessOperator.getRightChess(chessBoardLayoutMap, iChessPosition);
        byte downChess = chessOperator.getDownChess(chessBoardLayoutMap, iChessPosition);
        byte upUpChess = 0;
        byte downDownChess = 0;
        byte leftLeftChess = 0;
        byte rightRightChess = 0;


        //判断上方有没有棋子并移动
        if (upChess == TXZChessType.SPACE || upChess == TXZChessType.DEST) {
            byte[][] newBitMap = bitMapOperator.cloneBitMap(chessBoardLayoutMap);
            newBitMap[iChessPosition.getVertical() - 1][iChessPosition.getHorizontal()] = (byte) (chessOperator.removeDest(iChess) ^ upChess);
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal()] = chessOperator.removeIOrBox(iChess);
            if (checkSuccessOrToNextStep(parentNode, newBitMap)) return;
        } else if (chessOperator.removeDest(upChess) == TXZChessType.BOX &&
                ((upUpChess = chessOperator.getUpChess(chessBoardLayoutMap, iChessPosition, 2)) == TXZChessType.SPACE
                        || upUpChess == TXZChessType.DEST)) {
            byte[][] newBitMap = bitMapOperator.cloneBitMap(chessBoardLayoutMap);
            newBitMap[iChessPosition.getVertical() - 2][iChessPosition.getHorizontal()] = (byte) (chessOperator.removeDest(upChess) ^ upUpChess);
            newBitMap[iChessPosition.getVertical() - 1][iChessPosition.getHorizontal()] = (byte) (chessOperator.removeIOrBox(upChess) ^ chessOperator.removeDest(iChess));
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal()] = chessOperator.removeIOrBox(iChess);
            if (checkSuccessOrToNextStep(parentNode, newBitMap)) return;
        }


        //判断下方有没有棋子并移动
        if (downChess == TXZChessType.SPACE || downChess == TXZChessType.DEST) {
            byte[][] newBitMap = bitMapOperator.cloneBitMap(chessBoardLayoutMap);
            newBitMap[iChessPosition.getVertical() + 1][iChessPosition.getHorizontal()] = (byte) (chessOperator.removeDest(iChess) ^ downChess);
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal()] = chessOperator.removeIOrBox(iChess);
            if (checkSuccessOrToNextStep(parentNode, newBitMap)) return;
        } else if (chessOperator.removeDest(downChess) == TXZChessType.BOX &&
                ((downDownChess = chessOperator.getDownChess(chessBoardLayoutMap, iChessPosition, 2)) == TXZChessType.SPACE
                        || downDownChess == TXZChessType.DEST)) {
            byte[][] newBitMap = bitMapOperator.cloneBitMap(chessBoardLayoutMap);
            newBitMap[iChessPosition.getVertical() + 2][iChessPosition.getHorizontal()] = (byte) (chessOperator.removeDest(downChess) ^ downDownChess);
            newBitMap[iChessPosition.getVertical() + 1][iChessPosition.getHorizontal()] = (byte) (chessOperator.removeIOrBox(downChess) ^ chessOperator.removeDest(iChess));
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal()] = chessOperator.removeIOrBox(iChess);
            if (checkSuccessOrToNextStep(parentNode, newBitMap)) return;
        }


        //判断左边方有没有棋子并移动
        if (leftChess == TXZChessType.SPACE || leftChess == TXZChessType.DEST) {
            byte[][] newBitMap = bitMapOperator.cloneBitMap(chessBoardLayoutMap);
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal() - 1] = (byte) (chessOperator.removeDest(iChess) ^ leftChess);
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal()] = chessOperator.removeIOrBox(iChess);
            if (checkSuccessOrToNextStep(parentNode, newBitMap)) return;
        } else if (chessOperator.removeDest(leftChess) == TXZChessType.BOX &&
                ((leftLeftChess = chessOperator.getLeftChess(chessBoardLayoutMap, iChessPosition, 2)) == TXZChessType.SPACE
                        || leftLeftChess == TXZChessType.DEST)) {
            byte[][] newBitMap = bitMapOperator.cloneBitMap(chessBoardLayoutMap);
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal() - 2] = (byte) (chessOperator.removeDest(leftChess) ^ leftLeftChess);
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal() - 1] = (byte) (chessOperator.removeIOrBox(leftChess) ^ chessOperator.removeDest(iChess));
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal()] = chessOperator.removeIOrBox(iChess);
            if (checkSuccessOrToNextStep(parentNode, newBitMap)) return;
        }

        //判断右边有没有棋子并移动
        if (rightChess == TXZChessType.SPACE || rightChess == TXZChessType.DEST) {
            byte[][] newBitMap = bitMapOperator.cloneBitMap(chessBoardLayoutMap);
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal() + 1] = (byte) (chessOperator.removeDest(iChess) ^ rightChess);
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal()] = chessOperator.removeIOrBox(iChess);
            if (checkSuccessOrToNextStep(parentNode, newBitMap)) return;
        } else if (chessOperator.removeDest(rightChess) == TXZChessType.BOX &&
                ((rightRightChess = chessOperator.getRightChess(chessBoardLayoutMap, iChessPosition, 2)) == TXZChessType.SPACE
                        || rightRightChess == TXZChessType.DEST)) {
            byte[][] newBitMap = bitMapOperator.cloneBitMap(chessBoardLayoutMap);
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal() + 2] = (byte) (chessOperator.removeDest(rightChess) ^ rightRightChess);
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal() + 1] = (byte) (chessOperator.removeIOrBox(rightChess) ^ chessOperator.removeDest(iChess));
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal()] = chessOperator.removeIOrBox(iChess);
            if (checkSuccessOrToNextStep(parentNode, newBitMap)) return;
        }


    }

    /**
     * 测试是否已经成功，
     * 如果成功则输出结果停止迭代，
     * 如果出现重复棋路则停止迭代，
     * 否则继续迭代，
     *
     * @param parentNode
     * @param newbitMap
     * @return
     */
    private boolean checkSuccessOrToNextStep(TreeNode<ChessBoardLayoutEntity> parentNode, byte[][] newbitMap) {
        //验证当前是否是死棋
        TreeNode<ChessBoardLayoutEntity> recordedNode = null;
        if (!bitMapOperator.checkBitMapDeadChess(newbitMap, context.uuids)) {
            //如果当前步骤为死棋,相反则继续记录行走棋子
            recordedNode = context.recordBitMap(newbitMap, parentNode);
        }
        //如果没有记录当前步骤，则停止棋子行走
        if (recordedNode != null) {
            //如果任务成功则输出结果，并停止迭代
            if (bitMapOperator.checkMissionComplete(newbitMap)) {
                context.isMissionComplete = true;
                context.outPutAllStep(recordedNode);
                return true;
            }
        }
        return false;
    }
}
