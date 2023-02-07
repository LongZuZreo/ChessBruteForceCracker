package com.lemcoden.main.product;

import java.util.Map;

public interface BitMapOperator {
    byte[][] string2BitMap(String str);


    String bitMap2String(byte[][] bitMap);

    String bitMap2IDStr(byte[][] bitMap);

    boolean checkBitMapDeadChess(byte[][] newbitMap, Map<String, Boolean> uuids);


    boolean checkMissionComplete(byte[][] newbitMap);

    byte[][] cloneBitMap(byte[][] bitMap);

}
