package com.lemcoden.main.factory;

import com.lemcoden.huarongdao.product.HRDBitMapOperator;
import com.lemcoden.huarongdao.product.HRDChessOperator;
import com.lemcoden.huarongdao.product.HRDChessPlayer;
import com.lemcoden.main.ChessBoardStateContext;
import com.lemcoden.main.product.ChessOperator;
import com.lemcoden.main.product.ChessPlayer;
import com.lemcoden.main.product.BitMapOperator;

public class HRDBruteForceFactory implements BruteForceFactory{

    private ChessOperator chessOperator = new HRDChessOperator();

    @Override
    public ChessOperator generateChessOperator() {
        return chessOperator;
    }

    @Override
    public BitMapOperator generateBitMapOperator() {
        return new HRDBitMapOperator(chessOperator);
    }

    @Override
    public ChessPlayer generateChessPlayer(ChessBoardStateContext chessBruteForcer) {
        return new HRDChessPlayer(chessBruteForcer,chessOperator);
    }


}
