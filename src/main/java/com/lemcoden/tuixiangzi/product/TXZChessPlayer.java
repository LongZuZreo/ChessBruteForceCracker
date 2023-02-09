package com.lemcoden.tuixiangzi.product;

import com.lemcoden.main.constant.ChessType;
import com.lemcoden.main.entity.ChessBoardLayoutEntity;
import com.lemcoden.main.entity.ChessPosition;
import com.lemcoden.main.product.BitMapOperator;
import com.lemcoden.main.tree.TreeNode;
import com.lemcoden.main.ChessBoardStateContext;
import com.lemcoden.main.product.ChessOperator;
import com.lemcoden.main.product.ChessPlayer;
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
        byte upUpChess = chessOperator.getUpChess(chessBoardLayoutMap, iChessPosition, 2);
        byte downChess = chessOperator.getDownChess(chessBoardLayoutMap, iChessPosition);
        byte downDownChess = chessOperator.getDownChess(chessBoardLayoutMap, iChessPosition, 2);
        byte leftChess = chessOperator.getLeftChess(chessBoardLayoutMap, iChessPosition);
        byte leftLeftChess = chessOperator.getLeftChess(chessBoardLayoutMap, iChessPosition, 2);
        byte rightChess = chessOperator.getRightChess(chessBoardLayoutMap, iChessPosition);
        byte rightRightChess = chessOperator.getRightChess(chessBoardLayoutMap, iChessPosition, 2);

        //判断上方有没有棋子并移动
        if (upChess == TXZChessType.SPACE || chessOperator.removeIOrBox(upChess) == TXZChessType.DEST) {
            byte[][] newBitMap = bitMapOperator.cloneBitMap(chessBoardLayoutMap);
            newBitMap[iChessPosition.getVertical() - 1][iChessPosition.getHorizontal()] = (byte) (iChess ^ upChess);
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal()] = chessOperator.removeIOrBox(iChess);
            if (checkSuccessOrToNextStep(parentNode, newBitMap)) return;
        } else if (upChess == TXZChessType.BOX &&
                (upUpChess == TXZChessType.SPACE
                        || upUpChess == TXZChessType.DEST)) {
            byte[][] newBitMap = bitMapOperator.cloneBitMap(chessBoardLayoutMap);
            newBitMap[iChessPosition.getVertical() - 2][iChessPosition.getHorizontal()] = (byte) (upChess ^ upUpChess);
            newBitMap[iChessPosition.getVertical() - 1][iChessPosition.getHorizontal()] = (byte) (chessOperator.removeIOrBox(upChess) ^ iChess);
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal()] = chessOperator.removeIOrBox(iChess);
            if (checkSuccessOrToNextStep(parentNode, newBitMap)) return;
        }


        //判断下方有没有棋子并移动
        if (downChess == TXZChessType.SPACE || chessOperator.removeIOrBox(downChess) == TXZChessType.DEST) {
            byte[][] newBitMap = bitMapOperator.cloneBitMap(chessBoardLayoutMap);
            newBitMap[iChessPosition.getVertical() + 1][iChessPosition.getHorizontal()] = (byte) (iChess ^ upChess);
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal()] = chessOperator.removeIOrBox(iChess);
            if (checkSuccessOrToNextStep(parentNode, newBitMap)) return;
        } else if (downChess == TXZChessType.BOX &&
                (downDownChess == TXZChessType.SPACE
                        || downDownChess == TXZChessType.DEST)) {
            byte[][] newBitMap = bitMapOperator.cloneBitMap(chessBoardLayoutMap);
            newBitMap[iChessPosition.getVertical() + 2][iChessPosition.getHorizontal()] = (byte) (downChess ^ downDownChess);
            newBitMap[iChessPosition.getVertical() + 1][iChessPosition.getHorizontal()] = (byte) (chessOperator.removeIOrBox(downChess) ^ iChess);
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal()] = chessOperator.removeIOrBox(iChess);
            if (checkSuccessOrToNextStep(parentNode, newBitMap)) return;
        }


        //判断左边方有没有棋子并移动
        if (leftChess == TXZChessType.SPACE || chessOperator.removeIOrBox(leftChess) == TXZChessType.DEST) {
            byte[][] newBitMap = bitMapOperator.cloneBitMap(chessBoardLayoutMap);
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal() - 1] = (byte) (iChess ^ leftChess);
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal()] = chessOperator.removeIOrBox(iChess);
            if (checkSuccessOrToNextStep(parentNode, newBitMap)) return;
        } else if (leftChess == TXZChessType.BOX &&
                (leftLeftChess == TXZChessType.SPACE
                        || leftLeftChess == TXZChessType.DEST)) {
            byte[][] newBitMap = bitMapOperator.cloneBitMap(chessBoardLayoutMap);
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal() - 2] = (byte) (leftChess ^ leftLeftChess);
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal() - 1] = (byte) (chessOperator.removeIOrBox(leftChess) ^ iChess);
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal()] = chessOperator.removeIOrBox(iChess);
            if (checkSuccessOrToNextStep(parentNode, newBitMap)) return;
        }

        //判断右边有没有棋子并移动
        if (rightChess == TXZChessType.SPACE || chessOperator.removeIOrBox(rightChess) == TXZChessType.DEST) {
            byte[][] newBitMap = bitMapOperator.cloneBitMap(chessBoardLayoutMap);
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal() - 1] = (byte) (iChess ^ rightChess);
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal()] = chessOperator.removeIOrBox(iChess);
            if (checkSuccessOrToNextStep(parentNode, newBitMap)) return;
        } else if (rightChess == TXZChessType.BOX &&
                (rightRightChess == TXZChessType.SPACE
                        || rightRightChess == TXZChessType.DEST)) {
            byte[][] newBitMap = bitMapOperator.cloneBitMap(chessBoardLayoutMap);
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal() + 2] = (byte) (rightChess ^ rightRightChess);
            newBitMap[iChessPosition.getVertical()][iChessPosition.getHorizontal() + 1] = (byte) (chessOperator.removeIOrBox(rightChess) ^ iChess);
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
