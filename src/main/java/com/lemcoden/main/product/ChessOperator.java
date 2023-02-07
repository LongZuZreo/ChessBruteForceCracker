package com.lemcoden.main.product;


import com.lemcoden.huarongdao.entity.ChessPosition;
import com.lemcoden.main.constant.LocationType;

import java.util.List;

public interface ChessOperator {

    List<ChessPosition> getMovableChessLocate(byte[][] bitMap);

    boolean checkMovableChessPositions(List<ChessPosition> kongGeLocate);

    List<ChessPosition> getChessLocate(byte[][] bitMap, LocationType HRDLocationType);
}
