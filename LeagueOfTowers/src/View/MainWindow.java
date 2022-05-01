package View;

import Model.GameLogic;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/*
* A játék főablaka
* Itt helyezkedik el a JFrame
 */
public class MainWindow {

    public static Board board;
    private JFrame frame;
    public static OptionPanel opanel;
    public static GameLogic gl;

    public static MapEditorBoard meb;
    public static MapEditorPanel mep;

    public BackgroundMusic bm;
    Timer gameTimer;

    public MainWindow() {
        frame = new JFrame("League Of Towers");

        try {
            gl = new GameLogic();
        } catch (IOException e) {
            System.out.println("GameLogic hiba");
        }
        OptionPanel opanel = new OptionPanel();
        frame.add(opanel, BorderLayout.EAST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        board = new Board(opanel);
        meb = new MapEditorBoard(mep);
        mep = new MapEditorPanel();

        frame.add(board, BorderLayout.CENTER);
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu menu = new JMenu("Menü");
        menuBar.add(menu);

        gameTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                checkEndOfTheGame();
            }
        });

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
                try {
                    gameTimer.stop();
                    frame.getContentPane().remove(board);
                    frame.getContentPane().remove(meb);
                    frame.getContentPane().remove(opanel);
                    frame.getContentPane().remove(mep);

                    MainWindow.opanel = new OptionPanel();
                    board = new Board(opanel);
                    frame.add(board, BorderLayout.CENTER);
                    frame.add(opanel, BorderLayout.EAST);
                    gameTimer = new Timer(100, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            checkEndOfTheGame();
                        }
                    });
                    gameTimer.start();
                    board.repaint();
                } catch (Exception exc) {
                    System.out.println(exc);
                }
                try {
                    gl = new GameLogic();
                } catch (IOException a) {
                    System.out.println("GameLogic hiba");
                }
            }
        });
        menu.add(newGame);
        menu.add(menuGameExit);

        JMenu jatekMenu = new JMenu("Játék");
        menuBar.add(jatekMenu);
        JMenuItem mapEditor = new JMenuItem("Térképszerkesztő");
        mapEditor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gameTimer.stop();
                    frame.getContentPane().remove(board);
                    frame.getContentPane().remove(meb);
                    frame.getContentPane().remove(opanel);
                    frame.getContentPane().remove(mep);

                    mep = new MapEditorPanel();
                    meb = new MapEditorBoard(mep);
                    frame.add(meb, BorderLayout.CENTER);
                    frame.add(mep, BorderLayout.EAST);
                    gameTimer = new Timer(100, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            checkEndOfTheModifying();
                        }

                    });
                    gameTimer.start();
                    mep.change(-1, -1);
                    meb.repaint();
                } catch (Exception exc) {
                    System.err.println(exc);
                }
                try {
                    gl = new GameLogic();
                    gl.clearObjectsToTest();
                } catch (IOException a) {
                    System.out.println("GameLogic hiba");
                }
            }
        });
        jatekMenu.add(mapEditor);

        gameTimer.start();
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        bm = new BackgroundMusic("../LeagueOfTowers/src/res/bgmusic.mp3");
        //bm.start();

    }

    private void checkEndOfTheGame() {
        if (gl.CheckEndOfTheGame() != null) {
            String end = gl.CheckEndOfTheGame();
            gameTimer.stop();
            if ("pl1".equals(end)) {
                JOptionPane.showMessageDialog(this.frame, "2nd Player has won!", "Congrats!",
                        JOptionPane.PLAIN_MESSAGE);
            }
            if ("pl2".equals(end)) {
                JOptionPane.showMessageDialog(this.frame, "1st Player has won!", "Congrats!",
                        JOptionPane.PLAIN_MESSAGE);
            }
            if ("pl1pl2".equals(end)) {
                JOptionPane.showMessageDialog(this.frame, "That was a draw!", "Congrats!",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    private void checkEndOfTheModifying() {
        if (mep.StartTheGame) {
            try {
            gameTimer.stop();
            frame.getContentPane().remove(meb);
            frame.getContentPane().remove(mep);

            opanel = new OptionPanel();
            board = new Board(opanel);
            frame.add(board, BorderLayout.CENTER);
            frame.add(opanel, BorderLayout.EAST);
            gameTimer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    checkEndOfTheGame();
                }
            });
            gameTimer.start();
            board.repaint();
            opanel.change("nothing", 0, 0);
            }
            catch(Exception exc) {
                System.err.println(exc);
            }
            
        }

    }
}
