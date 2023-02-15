package com.lemcoden.huarongdao.view;

import com.lemcoden.huarongdao.factory.HRDBruteForceFactory;
import com.lemcoden.main.ChessBruteForcer;
import com.lemcoden.main.exception.ChessException;
import com.lemcoden.main.product.BitMapOperator;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class HRDFrame extends Frame {

    Font font = new Font("Serif", Font.PLAIN, 25);

    public HRDFrame() throws HeadlessException {
        setExtendedState(MAXIMIZED_BOTH);
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        setTitle("华容道暴力破解");
        Panel panel = new Panel();
        panel.setLayout(new FlowLayout());
        TextArea textArea = new TextArea();
        TextArea resultTextArea = new TextArea();
        resultTextArea.setFont(new Font("Serif", Font.PLAIN, 30));
        resultTextArea.setFocusable(false);
        textArea.setFont(new Font("Serif", Font.PLAIN, 30));
        textArea.setColumns(5);
        textArea.setRows(5);


        textArea.addTextListener(new TextListener() {
            boolean isSet;
            int lastCaretPosition;

            @Override
            public void textValueChanged(TextEvent e) {
                if (isSet) {
                    textArea.setCaretPosition(lastCaretPosition);
                    isSet = false;
                    return;
                }


                switch (e.getID()) {
                    case TextEvent.TEXT_VALUE_CHANGED:
                        String oldText = textArea.getText();
                        String newText = oldText.replace("\n", "");

                        char[] chars = newText.toCharArray();
                        char[] oldChars = oldText.toCharArray();

                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < chars.length; i++) {
                            if (i >= 20) {
                                break;
                            }
                            if (i % 4 == 3) {
                                sb = sb.append(chars[i]).append("\n");
                            } else {
                                sb = sb.append(chars[i]);
                            }
                        }


                        newText = sb.toString();
                        lastCaretPosition = textArea.getCaretPosition();

                        if (newText.length() - oldText.length() == 1) {
                            lastCaretPosition++;
                        }
                        isSet = true;
                        textArea.setText(newText);
                        break;
                }
            }
        });

        Label label = new Label("请在上方输入框中输入初始棋盘样式");
        Label label1 = new Label("其中，");
        Label label2 = new Label("\n中划线-表示横放棋子");
        Label label3 = new Label("\n竖线|表示半个竖放棋子");
        Label label4 = new Label("\n米字*表示单棋子");
        Label label5 = new Label("\n加号+表示四个棋子");
        Label label6 = new Label("\n英文字母o表示空格位");

        Button hBtn = new Button("-");
        hBtn.setFont(font);
        hBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                String concat = null;

                concat = text.concat("-");
                if (text.replace("\n", "").length() >= 20) return;
                if (text.replace("\n", "").length() % 4 == 3) concat = concat.concat("\n");

                textArea.setText(concat);
            }
        });
        Button shuBtn = new Button("|");
        shuBtn.setFont(font);
        shuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                String concat = text.concat("|");
                if (text.replace("\n", "").length() >= 20) return;
                if (text.replace("\n", "").length() % 4 == 3) concat = concat.concat("\n");
                textArea.setText(concat);
            }
        });
        Button dBtn = new Button("*");
        dBtn.setFont(font);
        dBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                String concat = text.concat("*");
                if (text.replace("\n", "").length() >= 20) return;
                if (text.replace("\n", "").length() % 4 == 3) concat = concat.concat("\n");
                textArea.setText(concat);
            }
        });
        Button siBtn = new Button("+");
        siBtn.setFont(font);
        siBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                String concat = text.concat("+");
                if (text.replace("\n", "").length() >= 20) return;
                if (text.replace("\n", "").length() % 4 == 3) concat = concat.concat("\n");
                textArea.setText(concat);
            }
        });
        Button kBtn = new Button("o");
        kBtn.setFont(font);
        kBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                String concat = text.concat("o");
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
        panel.add(new Label("                     "));
        panel.setLayout(new GridLayout(6, 2));
        Panel btnPanel = new Panel();
        btnPanel.setLayout(new GridLayout(1, 6));
        btnPanel.add(hBtn);
        btnPanel.add(shuBtn);
        btnPanel.add(dBtn);
        btnPanel.add(siBtn);
        btnPanel.add(kBtn);
        Button confirmBtn = new Button("确认");
        confirmBtn.setFont(font);
        confirmBtn.addActionListener(e -> {
            String bitMapStr = textArea.getText();

            HRDBruteForceFactory hrdBruteForceFactory = new HRDBruteForceFactory();
            BitMapOperator bitMapOperator = hrdBruteForceFactory.generateBitMapOperator();
            byte[][] bitMap = bitMapOperator.string2BitMap(bitMapStr);
            ChessBruteForcer chessBruteForcer = new ChessBruteForcer(bitMap, hrdBruteForceFactory);
            chessBruteForcer.setOutView(resultTextArea);
            try {
                chessBruteForcer.caculateRun();
            } catch (ChessException ex) {
                ex.printStackTrace();
            }

        });
        btnPanel.add(confirmBtn);
        MenuBar menuBar = new MenuBar();
        FileDialog fileDialog = new FileDialog(this, "保存文件");
        Menu menu = new Menu("选项");
        menuBar.add(menu);
        MenuItem menuItem = new MenuItem("保存结果到文件");
        menu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                fileDialog.setMode(FileDialog.SAVE);
                fileDialog.setFile("华容道暴破结果.txt");
                fileDialog.setVisible(true);
                String directory = fileDialog.getDirectory();
                String file = fileDialog.getFile();
                String text = resultTextArea.getText();
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(directory, file));
                    fileOutputStream.write(text.getBytes(StandardCharsets.UTF_8));
                    fileOutputStream.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        setMenuBar(menuBar);
        Button clearBtn = new Button("清空所有");
        clearBtn.setFont(font);
        clearBtn.addActionListener(e -> {
            textArea.setText("");
            resultTextArea.setText("");
        });
        btnPanel.add(clearBtn);

        Panel bitMapSizePanel = new Panel();
        TextField rowsField =new TextField();
        rowsField.addTextListener(new TextListener() {
            @Override
            public void textValueChanged(TextEvent e) {
                String text = rowsField.getText();
                char[] chars = text.toCharArray();
                for (char aChar : chars) {
                    if (!Character.isDigit(aChar)){

                    }
                }
            }
        });


        Button btn = new Button("更新");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        GridBagConstraints gb = new GridBagConstraints();
        panel.add(btnPanel);
        gb.fill = GridBagConstraints.BOTH;
        gb.gridx = 0;
        gb.gridy = 0;
        gb.weighty = 1.0;
        gb.weightx = GridBagConstraints.REMAINDER;
        gridBagLayout.setConstraints(textArea, gb);
        add(textArea, gb);
        gb.fill = GridBagConstraints.VERTICAL;
        gb.gridx = 0;
        gb.gridy = 1;
        gb.weighty = 1.0;
        gb.weightx = GridBagConstraints.REMAINDER;
        gridBagLayout.setConstraints(panel, gb);
        add(panel, gb);
        gb.fill = GridBagConstraints.BOTH;
        gb.gridx = 0;
        gb.gridy = 3;
        gb.weighty = 6.0;
        gb.weightx = GridBagConstraints.REMAINDER;
        gridBagLayout.setConstraints(resultTextArea, gb);
        add(resultTextArea, gb);


        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) { // bjmashibing/tank
                System.exit(0);
            }

        });
        pack();
    }


}
