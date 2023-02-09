package com.lemcoden.tuixiangzi.constant;

import com.lemcoden.main.constant.ChessType;

public interface TXZChessType extends ChessType {
    byte DEST = 0B100;

    byte I = 0B001;

    byte BOX = 0B010;

    byte WALL = 0B011;

    byte SPACE = 0B000;
}
