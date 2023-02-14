package com.lemcoden.main.view;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChessBruteForcerFrame extends Frame {

    public ChessBruteForcerFrame() throws HeadlessException {

        setSize(500, 100);
        this.setLocation(600, 400);
        setResizable(false);
        setTitle("暴力破解器");

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) { // bjmashibing/tank
                System.exit(0);
            }

        });
    }


}
