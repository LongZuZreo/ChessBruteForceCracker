package com.lemcoden.huarongdao.entity;

/**
 * 棋盘布局实体，包含唯一ID和棋盘布局实体，
 *
 */
public class ChessBoardLayoutEntity {

    private byte[][] chessBoardLayoutMap;

    public ChessBoardLayoutEntity(byte[][] chessBoardLayoutMap){
        this.chessBoardLayoutMap = chessBoardLayoutMap;
    }

    public byte[][] getChessBoardLayoutMap() {
        return chessBoardLayoutMap;
    }

}




