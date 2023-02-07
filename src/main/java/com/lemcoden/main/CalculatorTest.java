package com.lemcoden.main;

import com.lemcoden.huarongdao.exception.ChessException;
import com.lemcoden.main.factory.BruteForceFactory;
import com.lemcoden.main.factory.HRDBruteForceFactory;
import com.lemcoden.main.product.BitMapOperator;
import com.lemcoden.main.product.ChessOperator;

import java.util.Scanner;

/**
 * 测试程序
 */
public class CalculatorTest {


    public static void main(String[] args) {


        BruteForceFactory bruteForceFactory = new HRDBruteForceFactory();
        ChessOperator chessOperator = bruteForceFactory.generateChessOperator();
        BitMapOperator bitMapOperator = bruteForceFactory.generateBitMapOperator();

        //棋盘样式4*5
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        byte[][] bitMap = null;
        for (int i = 0; i < 5; i++) {
            System.out.println("请输入第"+(i+1)+"行");
            sb=sb.append(scanner.nextLine()).append("\n");
        }

        bitMap = bitMapOperator.string2BitMap(sb.toString());

        ChessBruteForcer chessBruteForcer = new ChessBruteForcer(bitMap,bruteForceFactory);
        try {
            chessBruteForcer.caculateRun();
        } catch (ChessException e) {
            e.printStackTrace();
        }
    }

}
