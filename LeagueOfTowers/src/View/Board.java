/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import leagueoftowers.Sprite;

/**
 *
 * @author tomih
 */
public class Board extends JPanel {

    private int tile_size = 32;
    private final int n = 29 + 1;
    private final int m = 18 + 1;
    private Sprite[] castle1;
    private Sprite[] castle2;
    private Sprite[] attack1;
    private Sprite[] attack2;
    private Sprite[] towers1;
    private Sprite[] towers2;
    private Sprite[] obsticles;

    int x = 0;
    int y = 0;

    public Board() {
        this.setBackground(new Color(32, 217, 19));
        Dimension dim = new Dimension(n * tile_size, m * tile_size);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setSize(dim);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(e.getX() / tile_size + "," + e.getY() / tile_size);
                System.out.println(getSize().width + "," + getSize().height);
                x = e.getX() / tile_size;
                y = e.getY() / tile_size;

                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gr = (Graphics2D) g;
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
                    gr.setColor(new Color(0, 200, 0));
                    gr.fillRect(i * tile_size, j * tile_size, tile_size, tile_size);
                }
            }
        }
            gr.setColor(new Color(0, 0, 0));
        for (int i = 0; i < m; i++) {
            gr.fillRect(n / 2 * tile_size-1, i * tile_size, 3, tile_size);
            
        }
        gr.setColor(new Color(0, 0, 0));
        gr.fillRect(x * tile_size, y * tile_size, tile_size, tile_size);
        gr.fillRect(n * tile_size - 4 * tile_size, m * tile_size / 2 - tile_size / 2, tile_size, tile_size);
        gr.fillRect(3 * tile_size, m * tile_size / 2 - tile_size / 2, tile_size, tile_size);
    }
}
