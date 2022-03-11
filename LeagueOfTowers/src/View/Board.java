/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author tomih
 */
public class Board extends JPanel {

    private final int tile_size = 32;
    private final int n = 28;
    private final int m = 18;
    private JButton[][] buttons;

    public Board() {
        GridLayout gridLayout = new GridLayout(28, 18);
        setLayout(gridLayout);
        this.buttons = new JButton[n][m];
        this.setBackground(new Color(255,255,255));
        for (int i = 0; i < this.n; ++i) {
            for (int j = 0; j < this.m; ++j) {
                JButton button = new JButton();
                button.addActionListener(new ButtonListener(i, j));
                //button.setPreferredSize(new Dimension(10, 10));
                button.setBackground(new Color(32, 217, 19));

                buttons[i][j] = button;
                button.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 0));
                add(button);
            }
        }
    }

    class ButtonListener implements ActionListener {

        private int x, y;

        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
