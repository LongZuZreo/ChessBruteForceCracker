package com.lemcoden.tuixiangzi.product;

import com.lemcoden.main.entity.ChessPosition;
import com.lemcoden.main.constant.LocationType;
import com.lemcoden.main.product.ChessOperator;
import com.lemcoden.tuixiangzi.constant.TXZLocationType;

import java.util.ArrayList;
import java.util.List;

import static com.lemcoden.tuixiangzi.constant.TXZChessType.*;


public class TXZChessOperator implements ChessOperator {
    @Override
    public List<ChessPosition> getMovableChessLocate(byte[][] bitMap) {
        return getChessLocate(bitMap, TXZLocationType.I);
    }

    public List<ChessPosition> getBoxLocate(byte[][] bitMap) {
        return getChessLocate(bitMap, TXZLocationType.BOX);
    }

    public List<ChessPosition> getCompleteChessLocate(byte[][] bitMap) {
        List<ChessPosition> chessLocate = getChessLocate(bitMap, TXZLocationType.BOX$DEST);
        return chessLocate == null ? new ArrayList<>() : chessLocate;
    }


    @Override
    public boolean checkMovableChessPositions(List<ChessPosition> movableLocate) {
        return movableLocate != null && movableLocate.size() == 1;
    }

    @Override
    public List<ChessPosition> getChessLocate(byte[][] bitMap, LocationType locationType) {
        TXZLocationType txzLocationType = (TXZLocationType) locationType;
        //空格坐标存储
        List<ChessPosition> chessPositions = new ArrayList<>();
        for (int i = 0; i < bitMap.length; i++) {
            for (int j = 0; j < bitMap[i].length; j++) {
                switch (txzLocationType) {
                    case I:
                        if (removeDest(bitMap[i][j]) == I) {
                            chessPositions.add(new ChessPosition((short) j, (short) i));
                        }
                        break;
                    case BOX:
                        if (removeDest(bitMap[i][j]) == BOX) {
                            chessPositions.add(new ChessPosition((short) j, (short) i));
                        }
                        break;
                    case WALL:
                        if (bitMap[i][j] == WALL) {
                            chessPositions.add(new ChessPosition((short) j, (short) i));
                        }
                        break;
                    case DEST:
                        if (removeIOrBox(bitMap[i][j]) == DEST) {
                            chessPositions.add(new ChessPosition((short) j, (short) i));
                        }
                        break;
                    case BOX$DEST:
                        if (bitMap[i][j] == (BOX ^ DEST)) {
                            chessPositions.add(new ChessPosition((short) j, (short) i));
                        }
                        break;

                }

            }
        }
        return chessPositions;
    }

    public byte removeDest(byte i) {
        return (byte) (i & 0b011);
    }

    public byte removeIOrBox(byte b) {
        return (byte) (b & 0B100);
    }
}
