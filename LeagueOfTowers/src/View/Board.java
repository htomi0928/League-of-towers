/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import static View.MainWindow.opanel;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static Model.Main.gl;


/**
 *
 * @author tomih
 */
public class Board extends JPanel {

    private int tile_size = 32;
    private final int n = 29 + 1;
    private final int m = 18 + 1;
    
    
    int x = 0;
    int y = 0;

    public Board(OptionPanel opanel) {

        
        setBackground(new Color(32, 217, 19));
        Dimension dim = new Dimension(n * tile_size, m * tile_size);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setSize(dim);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //System.out.println(e.getX() / tile_size + "," + e.getY() / tile_size);
                //System.out.println(getSize().width + "," + getSize().height);
                if ((gl.getTurn() == 1 && (e.getX() / tile_size) <= 14) || (gl.getTurn() == 2 && (e.getX() / tile_size) >= 15)) {
                    x = e.getX() / tile_size;
                    y = e.getY() / tile_size;
                    
                    try {
                        String isEmpty = "";
                        if (gl.get1pCastle().getXc() == x && gl.get1pCastle().getYc() == y) {isEmpty = "1castle";}
                        if (gl.get2pCastle().getXc() == x && gl.get2pCastle().getYc() == y) {isEmpty = "2castle";}
                        int tower = 0;
                        for (int i = 0; i < gl.get1pCastle().getTowers().size(); i++) {
                            if (gl.get1pCastle().getTowers().get(i).getXc() == x && gl.get1pCastle().getTowers().get(i).getYc() == y) {isEmpty = "1tower"; tower = i;}
                        }
                        for (int i = 0; i < gl.get2pCastle().getTowers().size(); i++) {
                            if (gl.get2pCastle().getTowers().get(i).getXc() == x && gl.get2pCastle().getTowers().get(i).getYc() == y) {isEmpty = "2tower"; tower = i;}
                        }
                        for (int i = 0; i < gl.get1pCastle().getUnits().size(); i++) {
                            if (gl.get1pCastle().getUnits().get(i).getXc() == x && gl.get1pCastle().getUnits().get(i).getYc() == y) {isEmpty = "1unit";}
                        }
                        for (int i = 0; i < gl.get2pCastle().getUnits().size(); i++) {
                            if (gl.get2pCastle().getUnits().get(i).getXc() == x && gl.get2pCastle().getUnits().get(i).getYc() == y) {isEmpty = "2unit";}
                        }
                        
                        if ("".equals(isEmpty)) {
                            opanel.change("empty", x, y);
                        }
                        if ("1tower".equals(isEmpty) && gl.getTurn() == 1) {
                            opanel.change("1tower", x, y);
                        }
                        if ("2tower".equals(isEmpty) && gl.getTurn() == 2) {
                            opanel.change("2tower", x, y);
                        }
                        if ("1castle".equals(isEmpty) || "2castle".equals(isEmpty) || "1unit".equals(isEmpty) || "2unit".equals(isEmpty)) {
                            opanel.change("nothing", 0, 0);
                        }
                        
                    } catch (IOException ex) {
                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    repaint();
                }
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
        
        
        gr.setColor(new Color(255, 255, 255));
        gr.fillRect(gl.get1pCastle().getXc() * tile_size, gl.get1pCastle().getYc() * tile_size, tile_size, tile_size);
        gr.fillRect(gl.get2pCastle().getXc() * tile_size, gl.get2pCastle().getYc() * tile_size, tile_size, tile_size);
        
        gr.setColor(new Color(255, 0, 0));
        for (int i = 0; i < gl.get1pCastle().getTowers().size(); i++) {
            gr.fillRect(gl.get1pCastle().getTowers().get(i).getXc() * tile_size, gl.get1pCastle().getTowers().get(i).getYc() * tile_size, tile_size, tile_size);
        }
        
        gr.setColor(new Color(0, 0, 255));
        for (int i = 0; i < gl.get2pCastle().getTowers().size(); i++) {
            gr.fillRect(gl.get2pCastle().getTowers().get(i).getXc() * tile_size, gl.get2pCastle().getTowers().get(i).getYc() * tile_size, tile_size, tile_size);
        }
        
        gr.setColor(new Color(255, 255, 0));
        for (int i = 0; i < gl.get1pCastle().getUnits().size(); i++) {
            gr.fillRect(gl.get1pCastle().getTowers().get(i).getXc() * tile_size, gl.get1pCastle().getTowers().get(i).getYc() * tile_size, tile_size, tile_size);
        }
        
        gr.setColor(new Color(0, 255, 255));
        for (int i = 0; i < gl.get1pCastle().getUnits().size(); i++) {
            gr.fillRect(gl.get2pCastle().getTowers().get(i).getXc() * tile_size, gl.get2pCastle().getTowers().get(i).getYc() * tile_size, tile_size, tile_size);
        }
        
        
        /*
        gr.fillRect(n * tile_size - 4 * tile_size, m * tile_size / 2 - tile_size / 2, tile_size, tile_size);
        gr.fillRect(3 * tile_size, m * tile_size / 2 - tile_size / 2, tile_size, tile_size);
        */
        
    }
    
    
    
}
