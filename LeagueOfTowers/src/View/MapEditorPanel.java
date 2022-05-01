package View;

import LoTExceptions.InvalidInputException;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static View.MainWindow.gl;
import Model.Tower;
import static View.Board.tile_size;
import static View.Board.width;
import static View.Board.height;
import java.io.IOException;
import Model.Tower1;
import Model.Tower2;
import Model.Tower3;
import Model.AttackUnits;
import Model.AmphibianZombie;
import Model.BigZombie;
import Model.FighterZombie;
import Model.KamikazeZombie;
import Model.Zombie;
import java.awt.Dimension;
import java.util.Random;
import Model.Barrack;
import Model.Castle;
import java.util.ArrayList;
import Model.Position;
import static View.MainWindow.board;
import static View.MainWindow.meb;
import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicOptionPaneUI;

/*
* Az oldalsó panel
 */
public class MapEditorPanel extends JPanel {

    public static void rounded(JButton button) {
        //Round the button with radius = 15
        button.setBorder(new RoundBtn(40));
        button.setOpaque(true);
        button.setFocusPainted(true);
        button.setBorderPainted(true);
        button.setContentAreaFilled(false);
//        setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
    }

    private JLabel moneyLabel;
    public int x;
    public int y;
    public boolean StartTheGame;

    public MapEditorPanel() {

        Dimension dim = new Dimension(12 * tile_size, height * tile_size);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setSize(dim);
        StartTheGame = false;

        GridLayout gridLayout = new GridLayout(3, 3);
        setLayout(gridLayout);
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        JButton jb = new JButton("Play");
        jb.addActionListener(new ButtonListener("play", 0, 0));
        add(jb);
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        this.setBackground(new Color(200, 200, 200));

    }


    /*
    * Ez a függvény befolyásolja az oldalsó panel kinézetét
     */
    public void change(int x, int y) throws IOException, InterruptedException {
        this.removeAll();
        GridLayout gridLayout = new GridLayout(3, 3);
        if (x >= 0 && x < width && y >= 0 && y < height && gl.canPlace(x, y)) {
            gridLayout = new GridLayout(7, 3);
            add(new JLabel(""));
            add(new JLabel(""));
            add(new JLabel(""));
            add(new JLabel(""));
            JButton obsb = new JButton("Obstickle");
            obsb.addActionListener(new ButtonListener("obst", x, y));
            add(obsb);
            add(new JLabel(""));
            add(new JLabel(""));
            add(new JLabel(""));
            add(new JLabel(""));
            add(new JLabel(""));
            JButton bab = new JButton("Barrack");
            bab.addActionListener(new ButtonListener("bab", x, y));
            add(bab);
            add(new JLabel(""));
        }
        setLayout(gridLayout);

        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        JButton jb = new JButton("Play");
        jb.addActionListener(new ButtonListener("play", x, y));
        add(jb);
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        this.setBackground(new Color(200, 200, 200));

        this.revalidate();
        this.repaint();
        meb.repaint();
    }

    /*
    * A change empty paraméteres opciójának gombjainak listenerei
     */
    private class ButtonListener implements ActionListener {

        String lab;
        int x;
        int y;

        public ButtonListener(String lab, int x, int y) {
            this.lab = lab;
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {

                //Játék indítása
                if ("play".equals(lab)) {
                    StartTheGame = true;
                }
                if ("obst".equals(lab)) {
                    gl.addObstickle(x, y);
                    meb.repaint();
                }
                if ("bab".equals(lab)) {
                    if (x <= width/2) {
                        gl.get1pCastle().addBarrack(new Barrack(x, y));
                    }
                    else {
                        gl.get2pCastle().addBarrack(new Barrack(x, y));
                    }
                    meb.repaint();
                }
                
            } catch (Exception e) {
            }
        }

    }
}
