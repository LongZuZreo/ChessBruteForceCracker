package com.lemcoden.main;

import com.lemcoden.main.view.ChessBruteForcerFrame;
import com.lemcoden.huarongdao.view.HRDFrame;
import com.lemcoden.tuixiangzi.view.TXZFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 测试程序
 */
public class MainView {

    public static void main(String[] args) {


        ChessBruteForcerFrame chessBruteForcerFrame = new ChessBruteForcerFrame();


        Panel panel = new Panel();

        Label label = new Label("请选择您要破解的游戏？");
        Font defaultFont = new Font("Serif", Font.PLAIN, 20);
        label.setFont(defaultFont);

        Button txzBtn = new Button("推箱子");
        txzBtn.setFont(defaultFont);
        Button hrdBtn = new Button("华容道");
        txzBtn.setFont(defaultFont);
        panel.add(label);
        panel.add(txzBtn);
        panel.add(hrdBtn);
        chessBruteForcerFrame.add(panel);
        chessBruteForcerFrame.setVisible(true);
        txzBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chessBruteForcerFrame.setVisible(false);
                TXZFrame txzFrame = new TXZFrame();
                txzFrame.setVisible(true);
            }


        });
        hrdBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chessBruteForcerFrame.setVisible(false);
                HRDFrame hrdFrame = new HRDFrame();
                hrdFrame.setVisible(true);
            }
        });
    }

}
