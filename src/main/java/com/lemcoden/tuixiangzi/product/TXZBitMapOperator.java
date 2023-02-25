package com.lemcoden.tuixiangzi.product;

import com.lemcoden.main.entity.ChessPosition;
import com.lemcoden.main.product.BitMapOperator;
import com.lemcoden.main.product.ChessOperator;
import com.lemcoden.tuixiangzi.constant.TXZChessType;

import java.util.List;
import java.util.Map;

import static com.lemcoden.tuixiangzi.constant.TXZChessType.*;

public class TXZBitMapOperator implements BitMapOperator {

    TXZChessOperator txzChessOperator;

    public TXZBitMapOperator(ChessOperator chessOperator) {
        txzChessOperator = (TXZChessOperator) chessOperator;
    }


    @Override
    public byte[][] string2BitMap(String str) {
        byte[][] bitMap = null;
        String[] split = str.split("\n");
        for (int i = 0; i < split.length; i++) {
            char[] chars = split[i].toCharArray();
            if (bitMap == null) bitMap = new byte[split.length][chars.length];
            for (int j = 0; j < chars.length; j++) {
                switch (chars[j]) {
                    case '@':
                        bitMap[i][j] = TXZChessType.I;
                        break;
                    case 'M':
                        bitMap[i][j] = WALL;
                        break;
                    case '#':
                        bitMap[i][j] = BOX;
                        break;
                    case '?':
                        bitMap[i][j] = TXZChessType.DEST;
                        break;
                    case ' ':
                        bitMap[i][j] = TXZChessType.SPACE;
                        break;
                    case '!':
                        bitMap[i][j] = TXZChessType.I ^ TXZChessType.DEST;
                        break;
                    case '*':
                        bitMap[i][j] = BOX ^ TXZChessType.DEST;
                        break;
                }
            }
        }

        return bitMap;
    }

    @Override
    public String bitMap2String(byte[][] bitMap) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bitMap.length; i++) {
            for (int j = 0; j < bitMap[i].length; j++) {
                switch (bitMap[i][j]) {
                    case TXZChessType.I:
                        sb.append("@");
                        break;
                    case WALL:
                        sb.append("M");
                        break;
                    case BOX:
                        sb.append("#");
                        break;
                    case TXZChessType.DEST:
                        sb.append("?");
                        break;
                    case TXZChessType.SPACE:
                        sb.append(" ");
                        break;
                    case TXZChessType.I ^ TXZChessType.DEST:
                        sb.append("!");
                        break;
                    case BOX ^ TXZChessType.DEST:
                        sb.append("*");
                        break;
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public String bitMap2IDStr(byte[][] bitMap) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bitMap.length; i++) {
            sb = sb.append(new String(bitMap[i]));
        }
        return sb.toString();
    }

    @Override
    public boolean checkBitMapDeadChess(byte[][] newbitMap, Map<String, Boolean> uuids) {
        //判断是否有重复棋盘
        if (uuids.get(bitMap2IDStr(newbitMap)) != null) {
            return true;
        }
        //判断棋盘中箱子是否卡死
        List<ChessPosition> boxLocates = txzChessOperator.getBoxLocate(newbitMap);
        for (ChessPosition boxLocate : boxLocates) {
            if (newbitMap[boxLocate.getVertical()][boxLocate.getHorizontal()] == (BOX ^ DEST)){
                continue;
            }
            if (newbitMap[boxLocate.getVertical() + 1][boxLocate.getHorizontal()] == WALL &&
                    ((newbitMap[boxLocate.getVertical()][boxLocate.getHorizontal() + 1] == WALL || newbitMap[boxLocate.getVertical()][boxLocate.getHorizontal() - 1] == WALL))) {
                return true;
            }
            if ((newbitMap[boxLocate.getVertical() - 1][boxLocate.getHorizontal()] == WALL) &&
                    ((newbitMap[boxLocate.getVertical()][boxLocate.getHorizontal() + 1] == WALL || newbitMap[boxLocate.getVertical()][boxLocate.getHorizontal() - 1] == WALL))) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean checkMissionComplete(byte[][] newbitMap) {
        int boxLocationsSize = txzChessOperator.getBoxLocate(newbitMap).size();
        int completeChessSize = txzChessOperator.getCompleteChessLocate(newbitMap).size();
        return boxLocationsSize == completeChessSize;
    }

    @Override
    public byte[][] cloneBitMap(byte[][] bitMap) {
        byte[][] cloneBitMap = new byte[bitMap.length][bitMap[0].length];
        for (int i = 0; i < bitMap.length; i++) {
            for (int j = 0; j < bitMap[i].length; j++) {
                cloneBitMap[i][j] = bitMap[i][j];
            }
        }
        return cloneBitMap;
    }
}
