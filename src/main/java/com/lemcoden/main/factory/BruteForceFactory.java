package com.lemcoden.main.factory;

import com.lemcoden.main.ChessBoardStateContext;
import com.lemcoden.main.product.ChessOperator;
import com.lemcoden.main.product.ChessPlayer;
import com.lemcoden.main.product.BitMapOperator;

public interface BruteForceFactory {

    //盘面上
    // 1个四格子棋子 001                   二进制总位数 4x3=12
    // 5个两格子棋子 010 横放 011 竖放      二进制总位数 5x2x3=30
    // 4个一格子棋子 100                   二进制总位数 4x3=12
    // 两个空格     000                    二进制总位数  2x3=6
    // 利用二进制表示棋盘的唯一布局
    // 可以用一个（12+30+12+6=）60位的二进制数表示棋盘的唯一ID
    ChessOperator generateChessOperator();

    BitMapOperator generateBitMapOperator();

    ChessPlayer generateChessPlayer(ChessBoardStateContext chessBruteForcer);

}
