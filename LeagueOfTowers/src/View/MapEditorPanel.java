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

    public MapEditorPanel() {

        Dimension dim = new Dimension(12 * tile_size, height * tile_size);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setSize(dim);

        GridLayout gridLayout = new GridLayout(3, 3);
        setLayout(gridLayout);
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        JButton jb = new JButton("Play");
        jb.addActionListener(new ButtonListener("play", 0));
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
    public void change() throws IOException, InterruptedException {
        this.removeAll();
        GridLayout gridLayout = new GridLayout(3, 7);
        setLayout(gridLayout);
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        JButton obsb = new JButton("Obstickle");
        obsb.addActionListener(new ButtonListener("obst", 0));
        add(obsb);
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        JButton bab = new JButton("Barrack");
        bab.addActionListener(new ButtonListener("bab", 0));
        add(bab);
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        JButton jb = new JButton("Play");
        jb.addActionListener(new ButtonListener("play", 0));
        add(jb);
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        this.setBackground(new Color(200, 200, 200));
        
        this.revalidate();
        this.repaint();
        board.repaint();
    }

    /*
    * A change empty paraméteres opciójának gombjainak listenerei
     */
    private class ButtonListener implements ActionListener {

        String lab;
        int numOfTower;

        public ButtonListener(String lab, int numOfTower) {
            this.lab = lab;
            this.numOfTower = numOfTower;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {

                //Tornyok gombjainak kezelése
                if ("play".equals(lab)) {
                }
            } catch (Exception e) {
            }
        }

    }
}
