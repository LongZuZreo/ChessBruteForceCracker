package com.lemcoden.huarongdao.product;

import com.lemcoden.huarongdao.entity.ChessPosition;
import com.lemcoden.huarongdao.common.LocationType;
import com.lemcoden.main.product.ChessOperator;

import java.util.ArrayList;
import java.util.List;

import static com.lemcoden.huarongdao.common.ChessType.*;

public class HRDChessOperator implements ChessOperator {

    /**
     * 两格棋子去除编号信息
     */
    public byte LIANGEremoveID(byte chess) {
        return (byte) (chess & 0B000111);
    }

    /**
     * 两格棋子获取编号信息
     */
    public byte getLIANGEID(byte chess) {
        return (byte) (chess & 0B111000);
    }

    /**
     * @param bitMap
     * @return
     */
    public List<ChessPosition> getMovableChessLocate(byte[][] bitMap) {
        return getChessLocate(bitMap, LocationType.KONGE);
    }

    public List<ChessPosition> getHengFangLocate(byte[][] bitMap) {
        return getChessLocate(bitMap, LocationType.HENGFANG);
    }

    public List<ChessPosition> getShuFangLocate(byte[][] bitMap) {
        return getChessLocate(bitMap, LocationType.SHUFANG);
    }

    public List<ChessPosition> getSIGELocate(byte[][] bitMap) {
        return getChessLocate(bitMap, LocationType.SIGE);
    }

    public List<ChessPosition> getChessLocate(byte[][] bitMap, LocationType locationType) {
        //空格坐标存储
        List<ChessPosition> chessPositions = new ArrayList<>();
        for (int i = 0; i < bitMap.length; i++) {
            for (int j = 0; j < bitMap[i].length; j++) {
                switch (locationType) {
                    case KONGE:
                        if (bitMap[i][j] == KONGE) {
                            chessPositions.add(new ChessPosition((short) j, (short) i));
                        }
                        break;
                    case SIGE:
                        if (bitMap[i][j] == SIGE) {
                            chessPositions.add(new ChessPosition((short) j, (short) i));
                        }
                        break;
                    case HENGFANG:
                        if (LIANGEremoveID(bitMap[i][j]) == HENGFANG) {
                            chessPositions.add(new ChessPosition((short) j, (short) i));
                        }
                        break;
                    case SHUFANG:
                        if (LIANGEremoveID(bitMap[i][j]) == SHUFANG) {
                            chessPositions.add(new ChessPosition((short) j, (short) i));
                        }
                        break;

                }

            }
        }
        return chessPositions;
    }

    public boolean checkMovableChessPositions(List<ChessPosition> KONGGEPositions) {
        return KONGGEPositions != null && KONGGEPositions.size() == 2;
    }


    public byte chessStr2Code(char aChar) {
        switch (aChar){
            case '|':
                return SHUFANG;
            case '—':
                return HENGFANG;
            case '+':
                return SIGE;
            case 'o':
                return KONGE;
            case '·':
                return YIGE;
        }
        return 0;
    }

    public boolean hasBianHao(byte chess) {
        return getLIANGEID(chess) != 0;
    }
}
