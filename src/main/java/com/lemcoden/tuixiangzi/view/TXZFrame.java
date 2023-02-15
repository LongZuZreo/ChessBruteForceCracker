package com.lemcoden.tuixiangzi.view;

import com.lemcoden.main.ChessBruteForcer;
import com.lemcoden.main.exception.ChessException;
import com.lemcoden.main.product.BitMapOperator;
import com.lemcoden.tuixiangzi.factory.TXZBruteForceFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class TXZFrame extends Frame {


    Font font = new Font("Serif", Font.PLAIN, 25);

    private int bitMapWidth = 0;
    private int bitMapHeight = 0;

    public TXZFrame() throws HeadlessException {
        setExtendedState(MAXIMIZED_BOTH);
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        setTitle("推箱子暴力破解");
        Panel panel = new Panel();
        panel.setLayout(new FlowLayout());
        TextArea textArea = new TextArea(); textArea.addTextListener(new TextListener() {
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
                            if (i >= (getBitMapWidth() * getBitMapHeight())) {
                                break;
                            }
                            if (i % getBitMapWidth() == (getBitMapWidth()-1)) {
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
        TextArea resultTextArea = new TextArea();
        resultTextArea.setFont(new Font("Serif", Font.PLAIN, 30));
        resultTextArea.setFocusable(false);
        textArea.setFont(new Font("Serif", Font.PLAIN, 30));


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
                if (text.replace("\n", "").length() >= (getBitMapWidth() * getBitMapHeight())) return;
                if (text.replace("\n", "").length() % getBitMapWidth() == (getBitMapWidth()-1)) concat = concat.concat("\n");

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
                if (text.replace("\n", "").length() >= (getBitMapWidth() * getBitMapHeight())) return;
                if (text.replace("\n", "").length() % getBitMapWidth() == (getBitMapWidth()-1)) concat = concat.concat("\n");
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
                if (text.replace("\n", "").length() >= (getBitMapWidth() * getBitMapHeight())) return;
                if (text.replace("\n", "").length() % getBitMapWidth() == (getBitMapWidth()-1)) concat = concat.concat("\n");
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
                if (text.replace("\n", "").length() >= (getBitMapWidth() * getBitMapHeight())) return;
                if (text.replace("\n", "").length() % getBitMapWidth() == (getBitMapWidth()-1)) concat = concat.concat("\n");
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
                if (text.replace("\n", "").length() >= (getBitMapWidth() * getBitMapHeight())) return;
                if (text.replace("\n", "").length() % getBitMapWidth() == (getBitMapWidth()-1)) concat = concat.concat("\n");
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
                if (text.replace("\n", "").length() >= (getBitMapWidth() * getBitMapHeight())) return;
                if (text.replace("\n", "").length() % getBitMapWidth() == (getBitMapWidth()-1)) concat = concat.concat("\n");
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
                if (text.replace("\n", "").length() >= (getBitMapWidth() * getBitMapHeight())) return;
                if (text.replace("\n", "").length() % getBitMapWidth() == (getBitMapWidth()-1)) concat = concat.concat("\n");
                textArea.setText(concat);
            }
        });
        Panel bitMapSize = new Panel();

        bitMapSize.setLayout(new GridLayout(2,6));
        Label labelx= new Label("         请在下方");
        Label labely = new Label("输入行数和列数:");
        Label labelz = new Label("否则棋盘无法输入");
        bitMapSize.add(labelx);
        bitMapSize.add(labely);
        bitMapSize.add(labelz);
        labelx.setFont(new Font("Serif", Font.PLAIN, 15));
        labely.setFont(new Font("Serif", Font.PLAIN, 15));
        labelz.setFont(new Font("Serif", Font.PLAIN, 15));
        for (int i = 0; i < 2; i++) {
            bitMapSize.add(new Label(" "));
        }

        TextField rowsField = new TextField();
        rowsField.setFont(font);
        Label label9 = new Label("行           x");
        label9.setFont(font);
        TextField columnField = new TextField();
        columnField.setFont(font);
        Label label10 = new Label("列");
        label10.setFont(font);
        Button button = new Button("确认");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rowStr = rowsField.getText();
                String columnStr = rowsField.getText();

                int row = 0;
                int column = 0;

                if (isDigit(rowStr) && isDigit(columnStr) && (row = Integer.parseInt(rowStr)) <= 9 && (column = Integer.parseInt(columnStr)) <=11){
                    textArea.setFocusable(true);
                    setBitMapHeight(row);
                    setBitMapWidth(column);

                }else{
                    Dialog dialog = new Dialog(getThisFrame());
                    dialog.setLayout(new GridLayout(2,1));
                    dialog.add(new Label("请输入数字!行数最大9,列数最大11"));
                    Button btn = new Button("确认");
                    btn.setSize(60,30);
                    btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dialog.setVisible(false);
                        }
                    });
                    dialog.add(btn);
                    dialog.setBounds(20, 30, 300, 200);
                    dialog.setVisible(true);

                }


            }
        });
        bitMapSize.add(rowsField);
        bitMapSize.add(label9);
        bitMapSize.add(columnField);
        bitMapSize.add(label10);
        bitMapSize.add(button);


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
        btnPanel.setLayout(new GridLayout(1, 7));
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
        Button clearBtn = new Button("清空");
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
        gb.weightx=1.0;
        gridBagLayout.setConstraints(textArea,gb);
        add(textArea,gb);
        gb.fill = GridBagConstraints.BOTH;
        gb.gridx=1;
        gb.gridy=0;
        gb.weighty=1.0;
        gb.weightx=0;
        gridBagLayout.setConstraints(bitMapSize,gb);
        add(bitMapSize,gb);


        gb.fill = GridBagConstraints.HORIZONTAL;
        gb.gridx=0;
        gb.gridy=1;
        gb.weighty=1.0;
        gb.weightx=0;
        gridBagLayout.setConstraints(panel,gb);
        add(panel,gb);
        gb.fill = GridBagConstraints.HORIZONTAL;
        gb.gridx=0;
        gb.gridy=2;
        gb.weighty=6.0;
        gb.weightx=0;
        gridBagLayout.setConstraints(resultTextArea,gb);
        add(resultTextArea,gb);
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

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) { // bjmashibing/tank
                System.exit(0);
            }

        });
        textArea.setFocusable(false);
        pack();
    }

    private Frame getThisFrame() {
        return this;
    }

    private boolean isDigit(String row) {
        for (char c : row.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public void setBitMapHeight(int bitMapHeight) {
        this.bitMapHeight = bitMapHeight;
    }

    public void setBitMapWidth(int bitMapWidth) {
        this.bitMapWidth = bitMapWidth;
    }

    public int getBitMapWidth() {
        return bitMapWidth;
    }

    public int getBitMapHeight() {
        return bitMapHeight;
    }
}
