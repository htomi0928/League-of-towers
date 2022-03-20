package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/*
* A játék fóablaka
* Itt helyezkedik el a JFrame
 */
public class MainWindow {

    private Board board;
    private JFrame frame;
    protected static OptionPanel opanel;

    public MainWindow() {
        frame = new JFrame("League Of Towers");

        OptionPanel opanel = new OptionPanel();
        frame.add(opanel, BorderLayout.EAST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        Board board = new Board(opanel);
        frame.add(board, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu menu = new JMenu("Menü");
        menuBar.add(menu);
        JMenuItem menuGameExit = new JMenuItem(new AbstractAction("Kilépés") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JMenuItem newGame = new JMenuItem("Új játék");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* 
                    frame.getContentPane().remove(table);
                    table = new Table();
                    frame.getContentPane().add(boardGUI.getBoardPanel(), BorderLayout.CENTER);
                 */
            }
        });
        menu.add(newGame);
        menu.add(menuGameExit);

        JMenu jatekMenu = new JMenu("Játék");
        menuBar.add(jatekMenu);
        JMenuItem options = new JMenuItem("Beállítások");
        jatekMenu.add(options);

        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
