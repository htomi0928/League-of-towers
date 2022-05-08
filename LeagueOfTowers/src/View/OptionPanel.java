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

/*
* Az oldalsó panel
 */
public class OptionPanel extends JPanel {
    
    private JLabel moneyLabel;
    public int x;
    public int y;
    
    public static void rounded(JButton button) {
        //Round the button with radius = 15
        button.setBorder(new RoundBtn(40)); 
        button.setOpaque(true);
        button.setFocusPainted(true);
        button.setBorderPainted(true);
        button.setContentAreaFilled(false);
    }
    
    public void separator() {
        add(new JLabel(""));
    }
    
    public void separator(int l) {
        for (int i = 0; i < l; i++) {
            add(new JLabel(""));
        }
    }
    
    public void moneyLabel() {
        add(new JLabel("Money: "));
    }
    
    public void centerLabel(String s) {
        add(new JLabel("<html><div style='text-align: center;'>" + s + "</div></html>"));
    }
    
    public void centerLabel(int i) {
        String s = i+"";
        add(new JLabel("<html><div style='text-align: center;'>" + s + "</div></html>"));
    }

    public OptionPanel() {

        Dimension dim = new Dimension(12 * tile_size, height * tile_size);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setSize(dim);

        GridLayout gridLayout = new GridLayout(2, 3);
        setLayout(gridLayout);
        separator();
        if (gl.getTurn() == 1 || gl.getTurn() == 2) {
            centerLabel(gl.whatToDo());
            separator(2);
            moneyLabel();
            moneyLabel = new JLabel(Integer.toString(gl.getCurrentPlayer().getMoney()) + "$");
            add(moneyLabel);
        }
        if (gl.getTurn() == 3 || gl.getTurn() == 4) {
            gridLayout = new GridLayout(15, 3);
            this.setLayout(gridLayout);
            centerLabel(gl.whatToDo());
            separator(2);
            moneyLabel();
            moneyLabel = new JLabel(Integer.toString(gl.getCurrentPlayer().getMoney()) + "$");
            add(moneyLabel);
            String[] labs1 = {"Kétéltű zombi", "Nagy zombi", "Harcoló zombi", "Öngyilkos zombi", "Zombi", "Kör vége"};
            
            this.setBackground(new Color(200, 200, 200));
            for (int i = 0; i < 13; ++i) {
                JLabel lab1 = new JLabel("");
                this.add(lab1);

                if (i % 2 == 1) {
                    JButton button = new JButton(labs1[(i - 1) / 2]);
                    button.addActionListener(new ButtonListener(labs1[(i - 1) / 2], 0));
                    rounded(button);
                    this.add(button);
                } else {
                    JLabel lab2 = new JLabel("");
                    this.add(lab2);
                }
                JLabel lab3 = new JLabel("");
                this.add(lab3);
            }
        }
        if (gl.getTurn() == 5) {
            centerLabel("Attacking");
            separator(4);
        }

    }
    
    

    //tornyokat hozzáadja az adott kastélyhoz és levonja az árát
    public void addTower(Tower t) throws IOException, InterruptedException, InvalidInputException {
        if (gl.getTurn() == 1) {
            if (t.getCost() <= gl.get1pCastle().getMoney()) {
                gl.get1pCastle().addTower(t);
                gl.get1pCastle().pay(t.getCost());
                OptionPanel.this.change("nothing", 0, 0);
            }
        } else {
            if (t.getCost() <= gl.get2pCastle().getMoney()) {
                gl.get2pCastle().addTower(t);
                gl.get2pCastle().pay(t.getCost());
                OptionPanel.this.change("nothing", 0, 0);
            }
        }
    }

    //zombikat hozzáadja az adott kastélyhoz és levonja az árát 
    public void addZombie(AttackUnits z) throws IOException, InterruptedException, InvalidInputException {
        if (gl.getTurn() == 3) {
            if (z.getCost() <= gl.get1pCastle().getMoney()) {
                gl.get1pCastle().addUnit(z);
                gl.get1pCastle().pay(z.getCost());
                OptionPanel.this.change("nothing", 0, 0);
            }
        } else {
            if (z.getCost() <= gl.get2pCastle().getMoney()) {
                gl.get2pCastle().addUnit(z);
                gl.get2pCastle().pay(z.getCost());
                OptionPanel.this.change("nothing", 0, 0);
            }
        }
    }

    //Zombi pozícióját adja meg, random választ ki a barakkok listájából egy
    //barakkot és oda teszi le a zombit ahol a barakk van    
    public Position zombiePosition() {
        int barracksListLength = 0;
        Position p = new Position(0, 0);
        Random rand = new Random();
        if (gl.getTurn() == 3) {
            int xb = 0;
            int yb = 0;
            barracksListLength = gl.get1pCastle().getBarracks().size();
            int random = rand.nextInt(barracksListLength + 1);
            if (random == barracksListLength) {
                Castle b = gl.get1pCastle();
                xb = b.getXc();
                yb = b.getYc();
            } else {
                Barrack b = gl.get1pCastle().getBarracks().get(random);
                xb = b.getXc();
                yb = b.getYc();
            }

            p.setX(xb);
            p.setY(yb);
        } else {
            int xb = 0;
            int yb = 0;
            barracksListLength = gl.get2pCastle().getBarracks().size();
            int random = rand.nextInt(barracksListLength + 1);
            if (random == barracksListLength) {
                Castle b = gl.get2pCastle();
                xb = b.getXc();
                yb = b.getYc();
            } else {
                Barrack b = gl.get2pCastle().getBarracks().get(random);
                xb = b.getXc();
                yb = b.getYc();
            }
            p.setX(xb);
            p.setY(yb);
        }

        return p;
    }

    /*
    * Ez a függvény befolyásolja az oldalsó panel kinézetét
     */
    public void change(String todo, int x, int y) throws IOException, InterruptedException {
        this.x = x;
        this.y = y;
        this.removeAll();

        board.setDrawCircle(false);
        /*
        * Ha illegális mezőre kattintunk (kastély, ellenfél térfele)
         */
        if ("nothing".equals(todo)) {
            GridLayout gridLayout = new GridLayout(2, 3);
            setLayout(gridLayout);
            add(new JLabel(""));
            if (gl.getTurn() == 1 || gl.getTurn() == 2) {

                centerLabel(gl.whatToDo());
                separator(2);
                moneyLabel();
                moneyLabel = new JLabel(Integer.toString(gl.getCurrentPlayer().getMoney()) + "$");
                add(moneyLabel);
            }
            if (gl.getTurn() == 3 || gl.getTurn() == 4) {
                gridLayout = new GridLayout(15, 3);
                this.setLayout(gridLayout);
                centerLabel(gl.whatToDo());
                separator(2);
                moneyLabel();
                moneyLabel = new JLabel(Integer.toString(gl.getCurrentPlayer().getMoney()) + "$");
                add(moneyLabel);
                String[] labs1 = {"Kétéltű zombi", "Nagy zombi", "Harcoló zombi", "Öngyilkos zombi", "Zombi", "Kör vége"};
                this.setBackground(new Color(200, 200, 200));
                for (int i = 0; i < 13; ++i) {
                    JLabel lab1 = new JLabel("");
                    this.add(lab1);

                    if (i % 2 == 1) {
                        JButton button = new JButton(labs1[(i - 1) / 2]);
                        button.addActionListener(new ButtonListener(labs1[(i - 1) / 2], 0));
                        rounded(button);
                        this.add(button);
                    } else {
                        JLabel lab2 = new JLabel("");
                        this.add(lab2);
                    }
                    JLabel lab3 = new JLabel("");
                    this.add(lab3);
                }
            }
            if (gl.getTurn() == 5) {
                centerLabel("Attacking");
                separator(4);
            }
        }

        /*
        * Ha az első játékos tornyára kattint valaki
        * - Ha már rom
        * - Ha még áll
        */
        if ("1tower".equals(todo) && !gl.get1pCastle().returnTower(x, y).getStatus()) {
            GridLayout gridLayout = new GridLayout(3, 3);
            Tower t = gl.get1pCastle().returnTower(x, y);
            if (gl.getTurn() == 1 && t.getWhenDied() + 2 <= gl.turnCounter) {
                gridLayout = new GridLayout(6, 3);
            }
            this.setLayout(gridLayout);
            separator();
            centerLabel("1st Player Clear Tower");
            separator(2);
            moneyLabel();
            moneyLabel = new JLabel(Integer.toString(gl.get1pCastle().getMoney()) + "$");
            this.add(moneyLabel);
            separator();
            centerLabel("Leomlott torony");
            this.add(new JLabel(""));
            
            if (gl.getTurn() == 1 && t.getWhenDied() + 2 <= gl.turnCounter) {
                separator(4);
                JButton button = new JButton("<html><div style='text-align: center;'>Torony eltakarítása</div></html>");
                button.addActionListener(new ButtonListener("towercleaner", gl.get1pCastle().returnTowerCoord(x, y)));
                rounded(button);
                this.add(button);
                separator(4);
            }
        }
        
        if ("1tower".equals(todo) && gl.get1pCastle().returnTower(x, y).getStatus()) {
            GridLayout gridLayout = new GridLayout(3, 3);
            if (gl.getTurn() == 1 && gl.get1pCastle().returnTower(x, y).getLevel() < 3 && gl.get1pCastle().returnTower(x, y).getStatus()) {
                gridLayout = new GridLayout(8, 3);
            }
            Tower t = gl.get1pCastle().returnTower(x, y);
            board.setCircleX(t.getXc());
            board.setCircleR(t.getDistance());
            board.setCircleY(t.getYc());
            board.setDrawCircle(true);
            this.setLayout(gridLayout);
            separator();
            centerLabel("1st Player Upgrade Tower");
            separator(2);
            moneyLabel();
            moneyLabel = new JLabel(Integer.toString(gl.get1pCastle().getMoney()) + "$");
            this.add(moneyLabel);
            separator();
            centerLabel("Torony típusa: " + t.getType()
                + "<br>Torony szintje: " + t.getLevel() + "<br>Torony élete: " + t.getHp() + "<br>Torony sebzése: "
                + t.getDamage() + "<br>Torony hatósugara: " + t.getDistance());
            separator();
            if (gl.getTurn() == 1 && gl.get1pCastle().returnTower(x, y).getLevel() < 3 && gl.get1pCastle().returnTower(x, y).getStatus()) {
                separator(4);
                JButton button = new JButton();
                if (gl.get1pCastle().returnTower(x, y).getLevel() == 1) {
                    button.setText("<html><div style='text-align: center;'>Upgrade<br>" + Integer.toString(gl.get1pCastle().returnTower(x, y).getUpgradeCost2()) + "$</div></html>");
                } else {
                    button.setText("<html><div style='text-align: center;'>Upgrade<br>" + Integer.toString(gl.get1pCastle().returnTower(x, y).getUpgradeCost3()) + "$</div></html>");
                }
                button.addActionListener(new ButtonListener("upgrade", gl.get1pCastle().returnTowerCoord(x, y)));
                rounded(button);
                this.add(button);
                separator(5);
                JButton sellbutton = new JButton();
                rounded(sellbutton);
                sellbutton.setText("<html><div style='text-align: center;'>Sell<br>" + Integer.toString(gl.get1pCastle().returnTower(x, y).getSellCost()) + "$");
                sellbutton.addActionListener(new ButtonListener("sell", gl.get1pCastle().returnTowerCoord(x, y)));
                this.add(sellbutton);
                separator(4);
            }
        }
        
        /*
        * Ha az második játékos tornyára kattint valaki
        * - Ha már rom
        * - Ha még áll
        */
        if ("2tower".equals(todo) && !gl.get2pCastle().returnTower(x, y).getStatus()) {
            GridLayout gridLayout = new GridLayout(3, 3);
            Tower t = gl.get2pCastle().returnTower(x, y);
            if (gl.getTurn() == 2 && t.getWhenDied() + 2 <= gl.turnCounter) {
                gridLayout = new GridLayout(6, 3);
            }
            this.setLayout(gridLayout);
            separator();
            centerLabel("2st Player Clear Tower");
            separator(2);
            moneyLabel();
            moneyLabel = new JLabel(Integer.toString(gl.get1pCastle().getMoney()) + "$");
            this.add(moneyLabel);
            separator();
            centerLabel("Leomlott torony");
            separator();
            if (gl.getTurn() == 1 && t.getWhenDied() + 2 <= gl.turnCounter) {

                separator(4);
                JButton button = new JButton("<html><div style='text-align: center;'>Torony eltakarítása</div></html>");
                button.addActionListener(new ButtonListener("towercleaner", gl.get2pCastle().returnTowerCoord(x, y)));
                rounded(button);
                this.add(button);
                separator(4);
            }
        }
        
        if ("2tower".equals(todo) && gl.get2pCastle().returnTower(x, y).getStatus()) {
            Tower t = gl.get2pCastle().returnTower(x, y);
            board.setCircleX(t.getXc());
            board.setCircleR(t.getDistance());
            board.setCircleY(t.getYc());
            board.setDrawCircle(true);
            GridLayout gridLayout = new GridLayout(3, 3);
            if (gl.getTurn() == 2 && gl.get2pCastle().returnTower(x, y).getLevel() < 3 && gl.get2pCastle().returnTower(x, y).getStatus()) {
                gridLayout = new GridLayout(8, 3);
            }
            this.setLayout(gridLayout);
            separator();
            centerLabel("2nd Player Upgrade Tower");
            separator(2);
            moneyLabel();
            moneyLabel = new JLabel(Integer.toString(gl.get2pCastle().getMoney()) + "$");
            this.add(moneyLabel);
            separator();
            centerLabel("Torony típusa: " + t.getType()
                    + "<br>Torony szintje: " + t.getLevel() + "<br>Torony élete: " + t.getHp() + "<br>Torony sebzése: "
                    + t.getDamage() + "<br>Torony hatósugara: " + t.getDistance());
            separator();
            if (gl.getTurn() == 2 && gl.get2pCastle().returnTower(x, y).getLevel() < 3 && gl.get2pCastle().returnTower(x, y).getStatus()) {
                separator(4);
                JButton button = new JButton();
                if (gl.get2pCastle().returnTower(x, y).getLevel() == 1) {
                    button.setText("<html><div style='text-align: center;'>Upgrade<br>" + Integer.toString(gl.get2pCastle().returnTower(x, y).getUpgradeCost2()) + "$</div></html>");
                } else {
                    button.setText("<html><div style='text-align: center;'>Upgrade<br>" + Integer.toString(gl.get2pCastle().returnTower(x, y).getUpgradeCost3()) + "$</div></html>");
                }
                button.addActionListener(new ButtonListener("upgrade", gl.get2pCastle().returnTowerCoord(x, y)));
                rounded(button);
                this.add(button);
                separator(5);
                JButton sellbutton = new JButton();
                rounded(sellbutton);
                sellbutton.setText("<html><div style='text-align: center;'>Sell<br>" + Integer.toString(gl.get2pCastle().returnTower(x, y).getSellCost()) + "$");
                sellbutton.addActionListener(new ButtonListener("sell", gl.get2pCastle().returnTowerCoord(x, y)));
                this.add(sellbutton);
                separator(4);
            }
        }

        /*
        * Ha barrackra kattint
         */
        if ("barrack".equals(todo)) {
            change("nothing", x, y);
        }
        if ("1castle".equals(todo)) {
            GridLayout gridLayout = new GridLayout(5, 3);
            setLayout(gridLayout);
            separator();
            centerLabel(gl.whatToDo());
            separator(2);
            moneyLabel();
            moneyLabel = new JLabel(Integer.toString(gl.getCurrentPlayer().getMoney()) + "$");
            add(moneyLabel);
            separator();
            centerLabel("1st Player Castle");
            separator(2);
            centerLabel(gl.get1pCastle().getMoney());
            separator(2);
            centerLabel(gl.get1pCastle().getHp());
            separator();
        }
        if ("2castle".equals(todo)) {
            GridLayout gridLayout = new GridLayout(5, 3);
            setLayout(gridLayout);
            separator();
            centerLabel(gl.whatToDo());
            separator(2);
            moneyLabel();
            moneyLabel = new JLabel(Integer.toString(gl.getCurrentPlayer().getMoney()) + "$");
            add(moneyLabel);
            separator();
            centerLabel("2nd Player Castle");
            separator(2);
            centerLabel(gl.get2pCastle().getMoney());
            separator(2);
            centerLabel(gl.get2pCastle().getHp());
            separator();
        }

        /*
        *Ha az első játékos a támadó egységeire kattint
         */
        if ("1units".equals(todo) || "2units".equals(todo)) {
            ArrayList<AttackUnits> au1 = gl.get1pCastle().returnUnits(x, y);
            ArrayList<AttackUnits> au2 = gl.get2pCastle().returnUnits(x, y);
            GridLayout gridLayout = new GridLayout(au1.size() + au2.size() + 2, 3);
            this.setLayout(gridLayout);
            separator();
            centerLabel(gl.whatToDo());
            separator(2);
            moneyLabel();
            moneyLabel = new JLabel(Integer.toString(gl.getCurrentPlayer().getMoney()) + "$");
            this.add(moneyLabel);
            for (int i = 0; i < au1.size(); i++) {
                separator();
                centerLabel("Első Játékos Zombija<br>Támadó egység adatai: " + au1.get(i).getType()
                        + "<br>Zombi max életereje: " + au1.get(i).getMaxhp() + "<br>Zombi sebzése: " + au1.get(i).getDamage() + "<br>Zombi sebessége: "
                        + au1.get(i).getSpeed() + "<br>Zombi jelenlegi életereje: " + au1.get(i).getHp());
                separator();
            }
            for (int i = 0; i < au2.size(); i++) {
                separator();
                centerLabel("Második Játékos Zombija<br>Támadó egység adatai: " + au2.get(i).getType()
                        + "<br>Zombi max életereje: " + au2.get(i).getMaxhp() + "<br>Zombi sebzése: " + au2.get(i).getDamage() + "<br>Zombi sebessége: "
                        + au2.get(i).getSpeed() + "<br>Zombi jelenlegi életereje: " + au2.get(i).getHp());
                separator();
            }
        }

        /*
        * Ha üres mezőre kattint
         */
        if ("empty".equals(todo)) {
            if (((gl.getTurn() == 1 && x >= 15) || (gl.getTurn() == 2 && x <= 14)) || gl.getTurn() >= 3 || !gl.canPlace(x, y)) {
                this.change("nothing", x, y);
            } else {
                GridLayout gridLayout = new GridLayout(11, 3);
                this.setLayout(gridLayout);
                separator();
                if (gl.getTurn() == 1) {
                    centerLabel("1st Player Building");
                } else {
                    centerLabel("2nd Player Building");
                }
                separator(2);
                moneyLabel();
                if (gl.getTurn() == 1 || gl.getTurn() == 3) {
                    moneyLabel = new JLabel(Integer.toString(gl.get1pCastle().getMoney()) + "$");
                }
                if (gl.getTurn() == 2 || gl.getTurn() == 4) {
                    moneyLabel = new JLabel(Integer.toString(gl.get2pCastle().getMoney()) + "$");
                }
                this.add(moneyLabel);
                String[] labs = {"1. Torony", "2. Torony", "3. Torony", "Kör vége"};
                this.setBackground(new Color(200, 200, 200));
                for (int i = 0; i < 9; ++i) {
                    JLabel lab1 = new JLabel("");
                    this.add(lab1);

                    if (i % 2 == 1) {
                        JButton button = new JButton(labs[(i - 1) / 2]);
                        rounded(button);
                        button.addActionListener(new ButtonListener(labs[(i - 1) / 2], 0));
                        this.add(button);
                    } else {
                        JLabel lab2 = new JLabel("");
                        this.add(lab2);
                    }
                    JLabel lab3 = new JLabel("");
                    this.add(lab3);
                }
            }
        }
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
                if ("Kör vége".equals(lab)) {
                    gl.nextTurn();
                    OptionPanel.this.change("nothing", x, y);
                    if (gl.getTurn() == 5) {
                        gl.AttackSimulation();
                        gl.nextTurn();
                        OptionPanel.this.change("nothing", 0, 0);
                    }
                }
                if ("1. Torony".equals(lab)) {
                    Tower1 nt = new Tower1(OptionPanel.this.x, OptionPanel.this.y);
                    addTower(nt);
                }
                if ("2. Torony".equals(lab)) {
                    Tower2 nt = new Tower2(OptionPanel.this.x, OptionPanel.this.y);
                    addTower(nt);
                }
                if ("3. Torony".equals(lab)) {
                    Tower3 nt = new Tower3(OptionPanel.this.x, OptionPanel.this.y);
                    addTower(nt);
                }
                if ("upgrade".equals(lab)) {
                    if (gl.getTurn() == 1) {
                        if (gl.get1pCastle().getTowers().get(numOfTower).getLevel() == 1) {
                            if (gl.get1pCastle().getMoney() >= gl.get1pCastle().getTowers().get(numOfTower).getUpgradeCost2()) {
                                gl.get1pCastle().getTowers().get(numOfTower).upgrade();
                                gl.get1pCastle().pay(gl.get1pCastle().getTowers().get(numOfTower).getUpgradeCost2());
                            }
                        } else {
                            if (gl.get1pCastle().getTowers().get(numOfTower).getLevel() == 2) {
                                if (gl.get1pCastle().getMoney() >= gl.get1pCastle().getTowers().get(numOfTower).getUpgradeCost3()) {
                                    gl.get1pCastle().getTowers().get(numOfTower).upgrade();
                                    gl.get1pCastle().pay(gl.get1pCastle().getTowers().get(numOfTower).getUpgradeCost3());
                                }
                            }
                        }
                    }
                    if (gl.getTurn() == 2) {
                        if (gl.get2pCastle().getTowers().get(numOfTower).getLevel() == 1) {
                            if (gl.get2pCastle().getMoney() >= gl.get2pCastle().getTowers().get(numOfTower).getUpgradeCost2()) {
                                gl.get2pCastle().getTowers().get(numOfTower).upgrade();
                                gl.get2pCastle().pay(gl.get2pCastle().getTowers().get(numOfTower).getUpgradeCost2());
                            }
                        } else {
                            if (gl.get2pCastle().getTowers().get(numOfTower).getLevel() == 2) {
                                if (gl.get2pCastle().getMoney() >= gl.get2pCastle().getTowers().get(numOfTower).getUpgradeCost3()) {
                                    gl.get2pCastle().getTowers().get(numOfTower).upgrade();
                                    gl.get2pCastle().pay(gl.get2pCastle().getTowers().get(numOfTower).getUpgradeCost3());
                                }
                            }
                        }
                    }
                    OptionPanel.this.change("nothing", 0, 0);
                }
                if ("sell".equals(lab)) {
                    if (gl.getTurn() == 1) {
                        gl.get1pCastle().addMoney(gl.get1pCastle().getTowers().get(numOfTower).getSellCost());
                        gl.get1pCastle().removeTower(numOfTower);
                    }
                    if (gl.getTurn() == 2) {
                        gl.get2pCastle().addMoney(gl.get2pCastle().getTowers().get(numOfTower).getSellCost());
                        gl.get2pCastle().removeTower(numOfTower);
                    }
                    OptionPanel.this.change("nothing", OptionPanel.this.x, OptionPanel.this.y);
                }
                if ("towercleaner".equals(lab)) {
                    if (gl.getTurn() == 1) {
                        if (gl.get1pCastle().getMoney() >= 50) {
                            gl.get1pCastle().removeTower(numOfTower);
                            gl.get1pCastle().pay(50);
                        }
                    }
                    if (gl.getTurn() == 2) {
                        if (gl.get2pCastle().getMoney() >= 50) {
                            gl.get2pCastle().removeTower(numOfTower);
                            gl.get2pCastle().pay(50);
                        }
                    }
                    OptionPanel.this.change("nothing", OptionPanel.this.x, OptionPanel.this.y);
                }

                //Zombik gombjainak a kezelése
                if ("Kétéltű zombi".equals(lab)) {
                    Position p = zombiePosition();
                    AmphibianZombie z = new AmphibianZombie(p.getX(), p.getY());
                    addZombie(z);
                }
                if ("Nagy zombi".equals(lab)) {
                    Position p = zombiePosition();
                    BigZombie z = new BigZombie(p.getX(), p.getY());
                    addZombie(z);
                }
                if ("Harcoló zombi".equals(lab)) {
                    Position p = zombiePosition();
                    FighterZombie z = new FighterZombie(p.getX(), p.getY());
                    addZombie(z);
                }
                if ("Öngyilkos zombi".equals(lab)) {
                    Position p = zombiePosition();
                    KamikazeZombie z = new KamikazeZombie(p.getX(), p.getY());
                    addZombie(z);
                }
                if ("Zombi".equals(lab)) {
                    Position p = zombiePosition();
                    Zombie z = new Zombie(p.getX(), p.getY());
                    addZombie(z);
                }

            } catch (Exception e) {
            }
        }

    }
}
