package com.lemcoden.tuixiangzi.factory;

import com.lemcoden.main.ChessBoardStateContext;
import com.lemcoden.main.factory.BruteForceFactory;
import com.lemcoden.main.product.BitMapOperator;
import com.lemcoden.main.product.ChessOperator;
import com.lemcoden.main.product.ChessPlayer;
import com.lemcoden.tuixiangzi.product.TXZBitMapOperator;
import com.lemcoden.tuixiangzi.product.TXZChessOperator;
import com.lemcoden.tuixiangzi.product.TXZChessPlayer;

public class TXZBruteForceFactory implements BruteForceFactory {

    TXZChessOperator txzChessOperator = new TXZChessOperator();

    @Override
    public ChessOperator generateChessOperator() {
        return txzChessOperator;
    }

    @Override
    public BitMapOperator generateBitMapOperator() {
        return new TXZBitMapOperator(txzChessOperator);
    }

    @Override
    public ChessPlayer generateChessPlayer(ChessBoardStateContext chessBruteForcer) {
        return new TXZChessPlayer(chessBruteForcer,txzChessOperator);
    }
}
