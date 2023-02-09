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

import java.util.List;

public class TXZChessPlayer implements ChessPlayer {

    TXZChessOperator chessOperator;

    public TXZChessPlayer(ChessBoardStateContext context, ChessOperator chessOperator){
        BitMapOperator bitMapOperator = context.bitMapOperator;
        this.chessOperator = (TXZChessOperator) chessOperator;
    }

    @Override
    public void moveChessToNextStep(List<ChessPosition> movableChessPositions, TreeNode<ChessBoardLayoutEntity> parentNode) {
        ChessPosition iChessPosition = movableChessPositions.get(0);
        byte[][] chessBoardLayoutMap = parentNode.getContent().getChessBoardLayoutMap();
        byte iChess = chessOperator.getChess(chessBoardLayoutMap,iChessPosition);
        byte upChess = chessOperator.getUpChess(chessBoardLayoutMap,iChessPosition);
        byte leftChess = chessOperator.getLeftChess(chessBoardLayoutMap,iChessPosition);
        byte rightChess = chessOperator.getRightChess(chessBoardLayoutMap,iChessPosition);
        byte downChess =chessOperator.getDownChess(chessBoardLayoutMap,iChessPosition);

        if (upChess == TXZChessType.SPACE || chessOperator.removeIOrBox(upChess) == TXZChessType.DEST){

        }else if (upChess == TXZChessType.BOX ){

        }

    }
}
