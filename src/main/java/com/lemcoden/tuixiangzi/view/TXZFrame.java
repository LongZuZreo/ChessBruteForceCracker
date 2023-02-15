package com.lemcoden.tuixiangzi.view;

import com.lemcoden.main.ChessBruteForcer;
import com.lemcoden.main.exception.ChessException;
import com.lemcoden.main.product.BitMapOperator;
import com.lemcoden.tuixiangzi.factory.TXZBruteForceFactory;

import java.awt.*;
import java.awt.event.*;

public class TXZFrame extends Frame {


    Font font = new Font("Serif", Font.PLAIN, 25);

    public TXZFrame() throws HeadlessException {
        setExtendedState(MAXIMIZED_BOTH);
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        setTitle("推箱子暴力破解");
        Panel panel = new Panel();
        panel.setLayout(new FlowLayout());
        TextArea textArea = new TextArea();
        TextArea resultTextArea = new TextArea();
        resultTextArea.setFont(new Font("Serif", Font.PLAIN, 30));
        resultTextArea.setFocusable(false);
        textArea.setFont(new Font("Serif", Font.PLAIN, 30));
        textArea.setColumns(5);
        textArea.setRows(5);


        Label label = new Label("请在上方输入框中输入初始棋盘样式");
        Label label1 = new Label("其中，");
        Label label2 = new Label("\n空格 表示空位");
        Label label3 = new Label("\n英文字母M表示墙");
        Label label4 = new Label("\n符号@表示小人");
        Label label5 = new Label("\n英文符号?表示目标位置");
        Label label6 = new Label("\n英文符号#表示箱子");
        Label label7 = new Label("\n英文符号*表示箱子与目标位置重叠");
        Label label8 = new Label("\n英文符号!表示小人与目标位置重叠");

        Button kBtn = new Button(" ");
        kBtn.setFont(font);
        kBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                String concat = null;

                concat = text.concat(" ");
                if (text.replace("\n", "").length() >= 20) return;
                if (text.replace("\n", "").length() % 4 == 3) concat = concat.concat("\n");

                textArea.setText(concat);
            }
        });
        Button qBtn = new Button("M");
        qBtn.setFont(font);
        qBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                String concat = text.concat("M");
                if (text.replace("\n", "").length() >= 20) return;
                if (text.replace("\n", "").length() % 4 == 3) concat = concat.concat("\n");
                textArea.setText(concat);
            }
        });
        Button rBtn = new Button("@");
        rBtn.setFont(font);
        rBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                String concat = text.concat("@");
                if (text.replace("\n", "").length() >= 20) return;
                if (text.replace("\n", "").length() % 4 == 3) concat = concat.concat("\n");
                textArea.setText(concat);
            }
        });
        Button dBtn = new Button("?");
        dBtn.setFont(font);
        dBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                String concat = text.concat("?");
                if (text.replace("\n", "").length() >= 20) return;
                if (text.replace("\n", "").length() % 4 == 3) concat = concat.concat("\n");
                textArea.setText(concat);
            }
        });

        Button xBtn = new Button("#");
        xBtn.setFont(font);
        xBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                String concat = text.concat("#");
                if (text.replace("\n", "").length() >= 20) return;
                if (text.replace("\n", "").length() % 4 == 3) concat = concat.concat("\n");
                textArea.setText(concat);
            }
        });

        Button gBtn = new Button("!");
        gBtn.setFont(font);
        gBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                String concat = text.concat("!");
                if (text.replace("\n", "").length() >= 20) return;
                if (text.replace("\n", "").length() % 4 == 3) concat = concat.concat("\n");
                textArea.setText(concat);
            }
        });

        Button xxBtn = new Button("*");
        xxBtn.setFont(font);
        xxBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                String concat = text.concat("*");
                if (text.replace("\n", "").length() >= 20) return;
                if (text.replace("\n", "").length() % 4 == 3) concat = concat.concat("\n");
                textArea.setText(concat);
            }
        });

        panel.add(label);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);
        panel.add(label7);
        panel.add(label8);
        panel.add(new Label("                     "));
        panel.setLayout(new GridLayout(6,2));
        Panel btnPanel = new Panel();
        btnPanel.setLayout(new GridLayout(1, 6));
        btnPanel.add(kBtn);
        btnPanel.add(qBtn);
        btnPanel.add(rBtn);
        btnPanel.add(xBtn);
        btnPanel.add(dBtn);
        btnPanel.add(gBtn);
        btnPanel.add(xxBtn);
        Button confirmBtn = new Button("确认");
        confirmBtn.setFont(font);
        confirmBtn.addActionListener(e -> {
            String bitMapStr = textArea.getText();

            TXZBruteForceFactory txzBruteForceFactory = new TXZBruteForceFactory();
            BitMapOperator bitMapOperator = txzBruteForceFactory.generateBitMapOperator();
            byte[][] bitMap = bitMapOperator.string2BitMap(bitMapStr);
            ChessBruteForcer chessBruteForcer = new ChessBruteForcer(bitMap, txzBruteForceFactory);
            chessBruteForcer.setOutView(resultTextArea);
            try {
                chessBruteForcer.caculateRun();
            } catch (ChessException ex) {
                ex.printStackTrace();
            }

        });
        btnPanel.add(confirmBtn);
        Button clearBtn = new Button("清空所有");
        clearBtn.setFont(font);
        clearBtn.addActionListener(e -> {
            textArea.setText("");
            resultTextArea.setText("");
        });
        btnPanel.add(clearBtn);

        GridBagConstraints gb = new GridBagConstraints();
        panel.add(btnPanel);
        gb.fill = GridBagConstraints.BOTH;
        gb.gridx=0;
        gb.gridy=0;
        gb.weighty=1.0;
        gb.weightx=GridBagConstraints.REMAINDER;
        gridBagLayout.setConstraints(textArea,gb);
        add(textArea,gb);
        gb.fill = GridBagConstraints.VERTICAL;
        gb.gridx=0;
        gb.gridy=1;
        gb.weighty=1.0;
        gb.weightx=GridBagConstraints.REMAINDER;
        gridBagLayout.setConstraints(panel,gb);
        add(panel,gb);
        gb.fill = GridBagConstraints.BOTH;
        gb.gridx=0;
        gb.gridy=3;
        gb.weighty=6.0;
        gb.weightx=GridBagConstraints.REMAINDER;
        gridBagLayout.setConstraints(resultTextArea,gb);
        add(resultTextArea,gb);


        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) { // bjmashibing/tank
                System.exit(0);
            }

        });
        pack();
    }
}
