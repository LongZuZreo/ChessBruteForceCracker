package com.lemcoden.huarongdao.product;

import com.lemcoden.huarongdao.entity.ChessBoardLayoutEntity;
import com.lemcoden.huarongdao.entity.ChessPosition;
import com.lemcoden.huarongdao.tree.TreeNode;
import com.lemcoden.main.ChessBoardStateContext;
import com.lemcoden.main.product.BitMapOperator;
import com.lemcoden.main.product.ChessOperator;
import com.lemcoden.main.product.ChessPlayer;

import java.util.List;

import static com.lemcoden.huarongdao.common.ChessType.*;

public class HRDChessPlayer implements ChessPlayer {

    ChessBoardStateContext context;
    BitMapOperator bitMapOperator;
    HRDChessOperator chessOperator;

    public HRDChessPlayer(ChessBoardStateContext context, ChessOperator chessOperator) {
        this.context = context;
        bitMapOperator = context.bitMapOperator;
        this.chessOperator = (HRDChessOperator) chessOperator;
    }

    @Override
    public void moveChessToNextStep(List<ChessPosition> movableChessPositions, TreeNode<ChessBoardLayoutEntity> parentNode) {
        //判断单个空格的周围，走出下一步
        checkAndMoveSimpleKongGe(movableChessPositions, parentNode);
        //判断两个空格是否在一起，下一步是否可以由两格棋子直接移动填补空格
        checkAndMoveDoubleKongGe(movableChessPositions, parentNode);
    }


    private void checkAndMoveDoubleKongGe(List<ChessPosition> KONGGEPositions, TreeNode<ChessBoardLayoutEntity> parentNode) {
        if (context.isMissionComplete) {
            return;
        }

        ChessPosition posA = KONGGEPositions.get(0);
        ChessPosition posB = KONGGEPositions.get(1);


        //两个空格坐标纵向相邻
        if (posA.getHorizontal() == posB.getHorizontal() && Math.abs(posA.getVertical() - posB.getVertical()) == 1) {

            //判断左边是否有棋子
            if (posA.getHorizontal() != 0 && posA.getHorizontal() != 0) {
                byte[][] newbitMap = bitMapOperator.cloneBitMap(context.getBitMap(parentNode));
                byte leftChessA = newbitMap[posA.getVertical()][posA.getHorizontal() - 1];
                byte leftChessB = newbitMap[posB.getVertical()][posB.getHorizontal() - 1];

                if (chessOperator.LIANGEremoveID(leftChessA) == SHUFANG && chessOperator.LIANGEremoveID(leftChessB) == SHUFANG && leftChessA == leftChessB) {
                    //移动棋子
                    newbitMap[posA.getVertical()][posA.getHorizontal() - 1] = KONGE;
                    newbitMap[posB.getVertical()][posB.getHorizontal() - 1] = KONGE;
                    newbitMap[posA.getVertical()][posA.getHorizontal()] = leftChessA;
                    newbitMap[posB.getVertical()][posB.getHorizontal()] = leftChessB;
                    if (checkSuccessOrToNextStep(parentNode, newbitMap)) return;
                }

                if (leftChessA == SIGE && leftChessB == SIGE) {
                    //移动棋子
                    newbitMap[posA.getVertical()][posA.getHorizontal() - 2] = KONGE;
                    newbitMap[posB.getVertical()][posB.getHorizontal() - 2] = KONGE;
                    newbitMap[posA.getVertical()][posA.getHorizontal() - 1] = leftChessA;
                    newbitMap[posB.getVertical()][posB.getHorizontal() - 1] = leftChessB;
                    newbitMap[posA.getVertical()][posA.getHorizontal()] = leftChessA;
                    newbitMap[posB.getVertical()][posB.getHorizontal()] = leftChessB;
                    if (checkSuccessOrToNextStep(parentNode, newbitMap)) return;
                }
            }
            //判断右边是否有棋子
            if (posA.getHorizontal() != 3 && posA.getHorizontal() != 3) {
                byte[][] newbitMap = bitMapOperator.cloneBitMap(context.getBitMap(parentNode));
                byte rightChessA = newbitMap[posA.getVertical()][posA.getHorizontal() + 1];
                byte rightChessB = newbitMap[posB.getVertical()][posB.getHorizontal() + 1];

                if (chessOperator.LIANGEremoveID(rightChessA) == SHUFANG && chessOperator.LIANGEremoveID(rightChessB) == SHUFANG && rightChessA == rightChessB) {
                    //移动棋子
                    newbitMap[posA.getVertical()][posA.getHorizontal() + 1] = KONGE;
                    newbitMap[posB.getVertical()][posB.getHorizontal() + 1] = KONGE;
                    newbitMap[posA.getVertical()][posA.getHorizontal()] = rightChessA;
                    newbitMap[posB.getVertical()][posB.getHorizontal()] = rightChessB;
                    if (checkSuccessOrToNextStep(parentNode, newbitMap)) return;
                }

                if (rightChessA == SIGE && rightChessB == SIGE) {
                    //移动棋子
                    newbitMap[posA.getVertical()][posA.getHorizontal() + 2] = KONGE;
                    newbitMap[posB.getVertical()][posB.getHorizontal() + 2] = KONGE;
                    newbitMap[posA.getVertical()][posA.getHorizontal() + 1] = rightChessA;
                    newbitMap[posB.getVertical()][posB.getHorizontal() + 1] = rightChessB;
                    newbitMap[posA.getVertical()][posA.getHorizontal()] = rightChessA;
                    newbitMap[posB.getVertical()][posB.getHorizontal()] = rightChessB;
                    if (checkSuccessOrToNextStep(parentNode, newbitMap)) return;
                }
            }

        }
        //两个空格坐标横向相邻居
        if (posA.getVertical() == posB.getVertical() && Math.abs(posA.getHorizontal() - posB.getHorizontal()) == 1) {

            //判断上面是否有棋子
            if (posA.getVertical() != 0 && posA.getVertical() != 0) {
                byte[][] newbitMap = bitMapOperator.cloneBitMap(context.getBitMap(parentNode));
                byte upChessA = newbitMap[posA.getVertical() - 1][posA.getHorizontal()];
                byte upChessB = newbitMap[posB.getVertical() - 1][posB.getHorizontal()];

                if (chessOperator.LIANGEremoveID(upChessA) == HENGFANG && chessOperator.LIANGEremoveID(upChessB) == HENGFANG && upChessA == upChessB) {
                    //移动棋子
                    newbitMap[posA.getVertical() - 1][posA.getHorizontal()] = KONGE;
                    newbitMap[posB.getVertical() - 1][posB.getHorizontal()] = KONGE;
                    newbitMap[posA.getVertical()][posA.getHorizontal()] = upChessA;
                    newbitMap[posB.getVertical()][posB.getHorizontal()] = upChessB;
                    if (checkSuccessOrToNextStep(parentNode, newbitMap)) return;
                }

                if (upChessA == SIGE && upChessB == SIGE) {
                    //移动棋子
                    newbitMap[posA.getVertical() - 2][posA.getHorizontal()] = KONGE;
                    newbitMap[posB.getVertical() - 2][posB.getHorizontal()] = KONGE;
                    newbitMap[posA.getVertical() - 1][posA.getHorizontal()] = upChessA;
                    newbitMap[posB.getVertical() - 1][posB.getHorizontal()] = upChessB;
                    newbitMap[posA.getVertical()][posA.getHorizontal()] = upChessA;
                    newbitMap[posB.getVertical()][posB.getHorizontal()] = upChessB;
                    if (checkSuccessOrToNextStep(parentNode, newbitMap)) return;
                }
            }
            //判断下面是否有棋子
            if (posA.getVertical() != 4 && posA.getVertical() != 4) {
                byte[][] newbitMap = bitMapOperator.cloneBitMap(context.getBitMap(parentNode));
                byte downChessA = newbitMap[posA.getVertical() + 1][posA.getHorizontal()];
                byte downChessB = newbitMap[posB.getVertical() + 1][posB.getHorizontal()];

                if (chessOperator.LIANGEremoveID(downChessA) == HENGFANG && chessOperator.LIANGEremoveID(downChessB) == HENGFANG && downChessA == downChessB) {
                    //移动棋子
                    newbitMap[posA.getVertical() + 1][posA.getHorizontal()] = KONGE;
                    newbitMap[posB.getVertical() + 1][posB.getHorizontal()] = KONGE;
                    newbitMap[posA.getVertical()][posA.getHorizontal()] = downChessA;
                    newbitMap[posB.getVertical()][posB.getHorizontal()] = downChessB;
                    if (checkSuccessOrToNextStep(parentNode, newbitMap)) return;
                }

                if (downChessA == SIGE && downChessB == SIGE) {
                    //移动棋子
                    newbitMap[posA.getVertical() + 2][posA.getHorizontal()] = KONGE;
                    newbitMap[posB.getVertical() + 2][posB.getHorizontal()] = KONGE;
                    newbitMap[posA.getVertical() + 1][posA.getHorizontal()] = downChessA;
                    newbitMap[posB.getVertical() + 1][posB.getHorizontal()] = downChessB;
                    newbitMap[posA.getVertical()][posA.getHorizontal()] = downChessA;
                    newbitMap[posB.getVertical()][posB.getHorizontal()] = downChessB;
                    if (checkSuccessOrToNextStep(parentNode, newbitMap)) return;
                }
            }

        }


    }

    private void checkAndMoveSimpleKongGe(List<ChessPosition> KONGGEPositions, TreeNode<ChessBoardLayoutEntity> parentNode) {
        //是否通关,如果通关停止棋子行走
        if (context.isMissionComplete) {
            return;
        }
        for (ChessPosition konggePosition : KONGGEPositions) {
            //分析空格的周围，以此走出下一步
            //判断空格上面是否有棋子
            if (konggePosition.getVertical() != 0) {
                byte[][] newbitMap = bitMapOperator.cloneBitMap(context.getBitMap(parentNode));
                byte upChess = newbitMap[konggePosition.getVertical() - 1][konggePosition.getHorizontal()];
                if (upChess == YIGE) {
                    //在棋盘上移动棋子；
                    newbitMap[konggePosition.getVertical()][konggePosition.getHorizontal()] = upChess;
                    newbitMap[konggePosition.getVertical() - 1][konggePosition.getHorizontal()] = KONGE;

                    if (checkSuccessOrToNextStep(parentNode, newbitMap)) return;
                }

                if (chessOperator.LIANGEremoveID(upChess) == SHUFANG) {
                    //在棋盘上移动棋子；
                    newbitMap[konggePosition.getVertical()][konggePosition.getHorizontal()] = upChess;
                    newbitMap[konggePosition.getVertical() - 1][konggePosition.getHorizontal()] = (byte) (SHUFANG ^ chessOperator.getLIANGEID(upChess));
                    newbitMap[konggePosition.getVertical() - 2][konggePosition.getHorizontal()] = KONGE;
                    if (checkSuccessOrToNextStep(parentNode, newbitMap)) return;
                }

            }

            //判断空格下面是否有棋子
            if (konggePosition.getVertical() != 4) {
                byte[][] newbitMap = bitMapOperator.cloneBitMap(context.getBitMap(parentNode));
                byte downChess = newbitMap[konggePosition.getVertical() + 1][konggePosition.getHorizontal()];
                if (downChess == YIGE) {
                    //在棋盘上移动棋子；
                    newbitMap[konggePosition.getVertical()][konggePosition.getHorizontal()] = downChess;
                    newbitMap[konggePosition.getVertical() + 1][konggePosition.getHorizontal()] = KONGE;

                    if (checkSuccessOrToNextStep(parentNode, newbitMap)) return;
                }
                if (chessOperator.LIANGEremoveID(downChess) == SHUFANG) {
                    //在棋盘上移动棋子；
                    newbitMap[konggePosition.getVertical()][konggePosition.getHorizontal()] = downChess;
                    newbitMap[konggePosition.getVertical() + 1][konggePosition.getHorizontal()] = (byte) (SHUFANG ^ chessOperator.getLIANGEID(downChess));
                    newbitMap[konggePosition.getVertical() + 2][konggePosition.getHorizontal()] = KONGE;

                    if (checkSuccessOrToNextStep(parentNode, newbitMap)) return;
                }
            }
            //判断空格左面是否由棋子
            if (konggePosition.getHorizontal() != 0) {
                byte[][] newbitMap = bitMapOperator.cloneBitMap(context.getBitMap(parentNode));
                byte leftChess = newbitMap[konggePosition.getVertical()][konggePosition.getHorizontal() - 1];
                if (leftChess == YIGE) {
                    //在棋盘上移动棋子；
                    newbitMap[konggePosition.getVertical()][konggePosition.getHorizontal()] = leftChess;
                    newbitMap[konggePosition.getVertical()][konggePosition.getHorizontal() - 1] = KONGE;

                    if (checkSuccessOrToNextStep(parentNode, newbitMap)) return;
                }
                if (chessOperator.LIANGEremoveID(leftChess) == HENGFANG) {
                    //在棋盘上移动棋子；
                    newbitMap[konggePosition.getVertical()][konggePosition.getHorizontal()] = leftChess;
                    newbitMap[konggePosition.getVertical()][konggePosition.getHorizontal() - 1] = (byte) (HENGFANG ^ chessOperator.getLIANGEID(leftChess));
                    newbitMap[konggePosition.getVertical()][konggePosition.getHorizontal() - 2] = KONGE;

                    if (checkSuccessOrToNextStep(parentNode, newbitMap)) return;
                }
            }
            //判断空格右面是否有棋子
            if (konggePosition.getHorizontal() != 3) {
                byte[][] newbitMap = bitMapOperator.cloneBitMap(context.getBitMap(parentNode));
                byte rightChess = newbitMap[konggePosition.getVertical()][konggePosition.getHorizontal() + 1];
                if (rightChess == YIGE) {
                    //在棋盘上移动棋子；
                    newbitMap[konggePosition.getVertical()][konggePosition.getHorizontal()] = rightChess;
                    newbitMap[konggePosition.getVertical()][konggePosition.getHorizontal() + 1] = KONGE;

                    if (checkSuccessOrToNextStep(parentNode, newbitMap)) return;
                }
                if (chessOperator.LIANGEremoveID(rightChess) == HENGFANG) {
                    //在棋盘上移动棋子；
                    newbitMap[konggePosition.getVertical()][konggePosition.getHorizontal()] = rightChess;
                    newbitMap[konggePosition.getVertical()][konggePosition.getHorizontal() + 1] = (byte) (HENGFANG ^ chessOperator.getLIANGEID(rightChess));
                    newbitMap[konggePosition.getVertical()][konggePosition.getHorizontal() + 2] = KONGE;

                    if (checkSuccessOrToNextStep(parentNode, newbitMap)) return;
                }
            }
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
        if (!bitMapOperator.checkBitMapDeadChess(newbitMap,context.uuids)) {
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
