package com.lemcoden.main.product;

import java.util.Map;

public interface BitMapOperator {
    byte[][] string2BitMap(String str);


    String bitMap2String(byte[][] bitMap);

    long[] bitMap2Long(byte[][] bitMap);

    boolean checkBitMapDeadChess(byte[][] newbitMap, Map<long[], Boolean> uuids);


    boolean checkMissionComplete(byte[][] newbitMap);

    byte[][] cloneBitMap(byte[][] bitMap);

}
