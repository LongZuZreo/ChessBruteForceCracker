package com.lemcoden.main.entity;

/**
 * 棋子的坐标
 */
public class ChessPosition {

    /**
     * 棋子的坐标,
     * @param horizontal 水平位置
     * @param vertical 垂直位置
     */
    public ChessPosition(short horizontal, short vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    short horizontal;
    short vertical;

    public short getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(short horizontal) {
        this.horizontal = horizontal;
    }

    public short getVertical() {
        return vertical;
    }

    public void setVertical(short vertical) {
        this.vertical = vertical;
    }
}
