package com.lemcoden.huarongdao.product;

import com.lemcoden.huarongdao.entity.ChessPosition;
import com.lemcoden.main.product.BitMapOperator;
import com.lemcoden.main.product.ChessOperator;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import static com.lemcoden.huarongdao.common.ChessType.*;

public class HRDBitMapOperator implements BitMapOperator {

    private HRDChessOperator chessOperator;

    public HRDBitMapOperator(ChessOperator chessOperator){
        this.chessOperator = (HRDChessOperator) chessOperator;
    }

    /**
     * 输入棋盘的布局，得出一个Long类型的唯一ID
     *
     * @param bitMap
     * @return
     */
    public  long[] bitMap2Long(byte[][] bitMap) {
        long[] UID = new long[]{0};
        for (int i = 0; i < bitMap.length; i++) {
            for (int j = 0; j < bitMap[i].length; j++) {
                if (bitMap[i][j] >= 8) {
                    //异或运算 可以用来拼接
                    //与运算   可以用来截断
                    //这里抛弃两格棋子的编号，以减少唯一ID生成的位数
                    UID[0] = (UID[0] << 3) ^ (bitMap[i][j] & 0B000111);
                } else {
                    UID[0] = (UID[0] << 3) ^ bitMap[i][j];
                }
            }
        }
        return UID;
    }

    /**
     * 将棋盘转换为可以读懂的字符串
     * @return
     */
    public  String bitMap2String(byte[][] bitMap){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < bitMap.length; i++) {
            for (int j = 0; j < bitMap[i].length; j++) {
                switch (bitMap[i][j]){
                    case YIGE:
                        sb.append("· ");
                        break;
                    case SIGE:
                        sb.append("+ ");
                        break;
                    case KONGE:
                        sb.append("o ");
                        break;
                }
                switch (chessOperator.LIANGEremoveID(bitMap[i][j])){
                    case HENGFANG:
                        sb.append("— ");
                        break;
                    case SHUFANG:
                        sb.append("| ");
                        break;
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public  byte[][] string2BitMap(String bitMapStr) {
        byte[][] bitMap = new byte[5][4];
        String[] split = bitMapStr.split("\n");
        for (int i = 0; i < split.length; i++) {
            char[] chars = split[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                bitMap[i][j] = chessOperator.chessStr2Code(chars[j]);
            }
        }

        bitMapAddNum(bitMap);

        return bitMap;
    }

    private  void bitMapAddNum(byte[][] bitMap) {
        Queue<Byte> bianHaoQueue = initBianHaoQueue();
        List<ChessPosition> hengFangLocates = chessOperator.getHengFangLocate(bitMap);
        for (int i = 0; i < hengFangLocates.size(); i++) {
            ChessPosition hengFangPosition = hengFangLocates.get(i);
            byte hengfangChess = bitMap[hengFangPosition.getVertical()][hengFangPosition.getHorizontal()];
            bitMap[hengFangPosition.getVertical()][hengFangPosition.getHorizontal()] = (byte) (hengfangChess ^ bianHaoQueue.poll());
        }

        List<ChessPosition> shuFangLocates = chessOperator.getShuFangLocate(bitMap);

        for (int i = 0; i < shuFangLocates.size(); i++) {
            ChessPosition shufangPosition = shuFangLocates.get(i);
            if (!chessOperator.hasBianHao(bitMap[shufangPosition.getVertical()][shufangPosition.getHorizontal()])){
                byte shufangChessA = bitMap[shufangPosition.getVertical()][shufangPosition.getHorizontal()];
                byte shufangChessB = bitMap[shufangPosition.getVertical()+1][shufangPosition.getHorizontal()];
                bitMap[shufangPosition.getVertical()][shufangPosition.getHorizontal()] = (byte) (shufangChessA ^ bianHaoQueue.poll());
                bitMap[shufangPosition.getVertical()+1][shufangPosition.getHorizontal()] = (byte) (shufangChessB ^ bianHaoQueue.poll());
            }
        }
    }

    private  Queue<Byte> initBianHaoQueue() {
        Queue<Byte> queue = new LinkedBlockingQueue<>();
        queue.add(BIANHAO1);
        queue.add(BIANHAO1);
        queue.add(BIANHAO2);
        queue.add(BIANHAO2);
        queue.add(BIANHAO3);
        queue.add(BIANHAO3);
        queue.add(BIANHAO4);
        queue.add(BIANHAO4);
        queue.add(BIANHAO5);
        queue.add(BIANHAO5);
        return queue;
    }

    /**
     * 检查当前步骤是否为死棋子
     *
     * @param bitMap
     * @return true 为有相同棋盘
     */
    public boolean checkBitMapDeadChess(byte[][] bitMap, Map<long[],Boolean> uuids) {
        long[] uuid = bitMap2Long(bitMap);
        if (uuids.get(uuid) == null) {
            return false;
        }
        return uuids.get(uuid);
    }

    /**
     * 验证是否通关
     *
     * @param bitMap
     * @return
     */
    public boolean checkMissionComplete(byte[][] bitMap) {
        List<ChessPosition> sigeLocate = chessOperator.getSIGELocate(bitMap);
        int successCount = 0;
        for (ChessPosition chessPosition : sigeLocate) {
            if ((chessPosition.getHorizontal() == 1 || chessPosition.getHorizontal() == 2) && chessPosition.getVertical() == 4) {
                successCount++;
            }
        }
        return successCount == 2;
    }

    public byte[][] cloneBitMap(byte[][] bitMap) {
        byte[][] cloneBitMap = new byte[5][4];
        for (int i = 0; i < bitMap.length; i++) {
            for (int j = 0; j < bitMap[i].length; j++) {
                cloneBitMap[i][j] = bitMap[i][j];
            }
        }
        return cloneBitMap;
    }
}
