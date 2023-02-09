package com.lemcoden.main.product;


import com.lemcoden.main.entity.ChessPosition;
import com.lemcoden.main.constant.LocationType;

import java.util.List;

public interface ChessOperator {

    List<ChessPosition> getMovableChessLocate(byte[][] bitMap);

    boolean checkMovableChessPositions(List<ChessPosition> kongGeLocate);

    List<ChessPosition> getChessLocate(byte[][] bitMap, LocationType HRDLocationType);

    default byte getChess(byte[][] bitMap, ChessPosition chessPosition) {
        return bitMap[chessPosition.getVertical()][chessPosition.getHorizontal()];
    }

    default byte getUpChess(byte[][] bitMap, ChessPosition chessPosition) {
        return bitMap[chessPosition.getVertical() - 1][chessPosition.getHorizontal()];
    }

    default byte getDownChess(byte[][] bitMap, ChessPosition chessPosition) {
        return bitMap[chessPosition.getVertical() + 1][chessPosition.getHorizontal()];
    }

    default byte getLeftChess(byte[][] bitMap, ChessPosition chessPosition) {
        return bitMap[chessPosition.getVertical()][chessPosition.getHorizontal() - 1];
    }

    default byte getRightChess(byte[][] bitMap, ChessPosition chessPosition){
        return bitMap[chessPosition.getVertical()][chessPosition.getHorizontal() + 1];
    }
}
