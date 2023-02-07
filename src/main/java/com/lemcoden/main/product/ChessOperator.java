package com.lemcoden.main.product;

import com.lemcoden.huarongdao.entity.ChessPosition;

import java.util.List;

public interface ChessOperator {

    List<ChessPosition> getMovableChessLocate(byte[][] bitMap);

    boolean checkMovableChessPositions(List<ChessPosition> kongGeLocate);
}
