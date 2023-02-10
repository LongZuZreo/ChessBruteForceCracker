package com.lemcoden.main.view;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TXZFrame extends Frame {

    public TXZFrame() throws HeadlessException {
        setSize(800, 600);
        setResizable(false);
        setTitle("推箱子暴力破解");

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) { // bjmashibing/tank
                System.exit(0);
            }

        });
    }
}
