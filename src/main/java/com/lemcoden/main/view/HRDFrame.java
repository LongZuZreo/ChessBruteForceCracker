package com.lemcoden.main.view;

import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.html.ListView;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class HRDFrame extends Frame {

    public HRDFrame() throws HeadlessException {
        setSize(800, 500);
        setResizable(false);
        this.setLocation(600, 400);
        setTitle("华容道暴力破解");
        Panel panel = new Panel();
        TextArea textArea = new TextArea();
        textArea.setSize(100, 200);
        textArea.setFont(new Font("Serif", Font.PLAIN, 30));
        textArea.setColumns(5);
        textArea.setRows(6);
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

                        StringBuilder sb = new StringBuilder();
                        if (chars.length < 25) {
                            for (int i = 0; i < chars.length; i++) {
                                if (i % 4 == 3) {
                                    sb = sb.append(chars[i]).append("\n");
                                } else {
                                    sb = sb.append(chars[i]);
                                }
                            }
                        }else{
                            sb.append(Arrays.copyOf(chars,24));
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

        Label label = new Label("请在左边输入框中输入初始棋盘样式");
        Label label1 = new Label("其中，");
        Label label2 = new Label("\n半个破折号—表示横放棋子");
        Label label3 = new Label("\n竖线|表示半个竖放棋子");
        Label label4 = new Label("\n中文圆点·表示单棋子");
        Label label5 = new Label("\n加号+表示四个棋子");
        Label label6 = new Label("\n英文字母o表示空格位");

        Button hBtn = new Button("-");
        hBtn.setFont(new Font("Serif", Font.PLAIN, 25));
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
        shuBtn.setFont(new Font("Serif", Font.PLAIN, 25));
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
        Button dBtn = new Button("·");
        dBtn.setFont(new Font("Serif", Font.PLAIN, 25));
        dBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                String concat = text.concat("·");
                if (text.replace("\n", "").length() >= 20) return;
                if (text.replace("\n", "").length() % 4 == 3) concat = concat.concat("\n");
                textArea.setText(concat);
            }
        });
        Button siBtn = new Button("+");
        siBtn.setFont(new Font("Serif", Font.PLAIN, 25));
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
        kBtn.setFont(new Font("Serif", Font.PLAIN, 25));
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


        textArea.setSize(400, 500);
        panel.add(textArea);
        panel.add(label);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);
        panel.add(new Label("                     "));
        Panel btnPanel = new Panel();
        btnPanel.setLayout(new GridLayout(1, 5));
        btnPanel.add(hBtn);
        btnPanel.add(shuBtn);
        btnPanel.add(dBtn);
        btnPanel.add(siBtn);
        btnPanel.add(kBtn);
        panel.add(btnPanel);

        add(panel);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) { // bjmashibing/tank
                System.exit(0);
            }

        });
    }


}
