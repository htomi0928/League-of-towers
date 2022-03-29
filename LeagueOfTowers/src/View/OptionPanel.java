package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static Model.Main.gl;
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

/*
* Az oldalsó panel
 */
public class OptionPanel extends JPanel {

    private JLabel moneyLabel;
    public int x;
    public int y;

    public OptionPanel() {

        Dimension dim = new Dimension(12 * tile_size, height * tile_size);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setSize(dim);

        GridLayout gridLayout = new GridLayout(2, 3);
        setLayout(gridLayout);
        add(new JLabel(""));
        if (gl.getTurn() == 1 || gl.getTurn() == 2) {

            add(new JLabel("<html><div style='text-align: center;'>" + gl.whatToDo() + "</div></html>"));
            add(new JLabel(""));
            add(new JLabel(""));
            add(new JLabel("Money: "));
            moneyLabel = new JLabel(Integer.toString(gl.getCurrentPlayer().getMoney()) + "$");
            add(moneyLabel);
        }
        if (gl.getTurn() == 3 || gl.getTurn() == 4) {
            gridLayout = new GridLayout(15, 3);
            this.setLayout(gridLayout);
            add(new JLabel("<html><div style='text-align: center;'>" + gl.whatToDo() + "</div></html>"));
            add(new JLabel(""));
            add(new JLabel(""));
            add(new JLabel("Money: "));
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
            add(new JLabel("<html><div style='text-align: center;'>Attacking</div></html>"));
            add(new JLabel(""));
            add(new JLabel(""));
            add(new JLabel(""));
            add(new JLabel(""));
        }

    }

    /*
    * Ez a függvény befolyásolja az oldalsó panel kinézetét
     */
    public void change(String todo, int x, int y) throws IOException, InterruptedException {
        this.x = x;
        this.y = y;
        this.removeAll();

        /*
        * Ha illegális mezőre kattintunk (kastély, ellenfél térfele)
         */
        if ("nothing".equals(todo)) {
            GridLayout gridLayout = new GridLayout(2, 3);
            setLayout(gridLayout);
            add(new JLabel(""));
            if (gl.getTurn() == 1 || gl.getTurn() == 2) {

                add(new JLabel("<html><div style='text-align: center;'>" + gl.whatToDo() + "</div></html>"));
                add(new JLabel(""));
                add(new JLabel(""));
                add(new JLabel("Money: "));
                moneyLabel = new JLabel(Integer.toString(gl.getCurrentPlayer().getMoney()) + "$");
                add(moneyLabel);
            }
            if (gl.getTurn() == 3 || gl.getTurn() == 4) {
                gridLayout = new GridLayout(15, 3);
                this.setLayout(gridLayout);
                add(new JLabel("<html><div style='text-align: center;'>" + gl.whatToDo() + "</div></html>"));
                add(new JLabel(""));
                add(new JLabel(""));
                add(new JLabel("Money: "));
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
                add(new JLabel("<html><div style='text-align: center;'>Attacking</div></html>"));
                add(new JLabel(""));
                add(new JLabel(""));
                add(new JLabel(""));
                add(new JLabel(""));
            }
        }

        /*
        * Ha az első játékos saját tornyára kattint
         */
        if ("1tower".equals(todo)) {
            GridLayout gridLayout = new GridLayout(3, 3);
            if (gl.getTurn() == 1 && gl.get1pCastle().returnTower(x, y).getLevel() < 3 && gl.get1pCastle().returnTower(x, y).getStatus()) {
                gridLayout = new GridLayout(8, 3);
            }
            Tower t = gl.get1pCastle().returnTower(x, y);
            this.setLayout(gridLayout);
            this.add(new JLabel(""));
            this.add(new JLabel("<html><div style='text-align: center;'>1st Player Upgrade Tower</div></html>"));
            this.add(new JLabel(""));
            this.add(new JLabel(""));
            this.add(new JLabel("Money: "));
            moneyLabel = new JLabel(Integer.toString(gl.get1pCastle().getMoney()) + "$");
            this.add(moneyLabel);
            this.add(new JLabel(""));
            this.add(new JLabel("<html><div style='text-align: center;'>Torony típusa: " + t.getType()
                    + "<br>Torony szintje: " + t.getLevel() + "<br>Torony élete: " + t.getHp() + "<br>Torony sebzése: "
                    + t.getDamage() + "<br>Torony hatósugara: " + t.getDistance() + "</div></html>"));
            this.add(new JLabel(""));
            if (gl.getTurn() == 1 && gl.get1pCastle().returnTower(x, y).getLevel() < 3 && gl.get1pCastle().returnTower(x, y).getStatus()) {

                this.add(new JLabel(""));
                this.add(new JLabel(""));
                this.add(new JLabel(""));
                this.add(new JLabel(""));
                JButton button = new JButton();
                if (gl.get1pCastle().returnTower(x, y).getLevel() == 1) {
                    button.setText("<html><div style='text-align: center;'>Upgrade<br>" + Integer.toString(gl.get1pCastle().returnTower(x, y).getUpgradeCost2()) + "$</div></html>");
                } else {
                    button.setText("<html><div style='text-align: center;'>Upgrade<br>" + Integer.toString(gl.get1pCastle().returnTower(x, y).getUpgradeCost3()) + "$</div></html>");
                }
                button.addActionListener(new ButtonListener("upgrade", gl.get1pCastle().returnTowerCoord(x, y)));

                this.add(button);
                this.add(new JLabel(""));
                this.add(new JLabel(""));
                this.add(new JLabel(""));
                this.add(new JLabel(""));
                this.add(new JLabel(""));
                JButton sellbutton = new JButton();
                sellbutton.setText("<html><div style='text-align: center;'>Sell<br>" + Integer.toString(gl.get1pCastle().returnTower(x, y).getSellCost()) + "$");
                sellbutton.addActionListener(new ButtonListener("sell", gl.get1pCastle().returnTowerCoord(x, y)));
                this.add(sellbutton);
                this.add(new JLabel(""));
                this.add(new JLabel(""));
                this.add(new JLabel(""));
                this.add(new JLabel(""));
            }

            /*
        * Ha az második játékos saját tornyára kattint
             */
        }
        if ("2tower".equals(todo)) {
            Tower t = gl.get2pCastle().returnTower(x, y);
            GridLayout gridLayout = new GridLayout(3, 3);
            if (gl.getTurn() == 2 && gl.get2pCastle().returnTower(x, y).getLevel() < 3 && gl.get2pCastle().returnTower(x, y).getStatus()) {
                gridLayout = new GridLayout(8, 3);
            }
            this.setLayout(gridLayout);
            this.add(new JLabel(""));
            this.add(new JLabel("<html><div style='text-align: center;'>2nd Player Upgrade Tower</div></html>"));
            this.add(new JLabel(""));
            this.add(new JLabel(""));
            this.add(new JLabel("Money: "));
            moneyLabel = new JLabel(Integer.toString(gl.get2pCastle().getMoney()) + "$");
            this.add(moneyLabel);
            this.add(new JLabel(""));
            this.add(new JLabel("<html><div style='text-align: center;'>Torony típusa: " + t.getType()
                    + "<br>Torony szintje: " + t.getLevel() + "<br>Torony élete: " + t.getHp() + "<br>Torony sebzése: "
                    + t.getDamage() + "<br>Torony hatósugara: " + t.getDistance() + "</div></html>"));
            this.add(new JLabel(""));
            if (gl.getTurn() == 2 && gl.get2pCastle().returnTower(x, y).getLevel() < 3 && gl.get2pCastle().returnTower(x, y).getStatus()) {
                this.add(new JLabel(""));
                this.add(new JLabel(""));
                this.add(new JLabel(""));
                this.add(new JLabel(""));
                JButton button = new JButton();
                if (gl.get2pCastle().returnTower(x, y).getLevel() == 1) {
                    button.setText("<html><div style='text-align: center;'>Upgrade<br>" + Integer.toString(gl.get2pCastle().returnTower(x, y).getUpgradeCost2()) + "$</div></html>");
                } else {
                    button.setText("<html><div style='text-align: center;'>Upgrade<br>" + Integer.toString(gl.get2pCastle().returnTower(x, y).getUpgradeCost3()) + "$</div></html>");
                }
                button.addActionListener(new ButtonListener("upgrade", gl.get2pCastle().returnTowerCoord(x, y)));

                this.add(button);
                this.add(new JLabel(""));
                this.add(new JLabel(""));
                this.add(new JLabel(""));
                this.add(new JLabel(""));
                this.add(new JLabel(""));
                JButton sellbutton = new JButton();
                sellbutton.setText("<html><div style='text-align: center;'>Sell<br>" + Integer.toString(gl.get2pCastle().returnTower(x, y).getSellCost()) + "$");
                sellbutton.addActionListener(new ButtonListener("sell", gl.get2pCastle().returnTowerCoord(x, y)));
                this.add(sellbutton);
                this.add(new JLabel(""));
                this.add(new JLabel(""));
                this.add(new JLabel(""));
                this.add(new JLabel(""));
            }
        }

        /*
        * Ha barrackra kattint
         */
        if ("barrack".equals(todo)) {

        }
        if ("1castle".equals(todo)) {
            GridLayout gridLayout = new GridLayout(5, 3);
            setLayout(gridLayout);
            add(new JLabel(""));
            add(new JLabel("<html><div style='text-align: center;'>" + gl.whatToDo() + "</div></html>"));
            add(new JLabel(""));
            add(new JLabel(""));
            add(new JLabel("Money: "));
            moneyLabel = new JLabel(Integer.toString(gl.getCurrentPlayer().getMoney()) + "$");
            add(moneyLabel);
            add(new JLabel(""));
            add(new JLabel("<html><div style='text-align: center;'>1st Player Castle</div></html>"));
            add(new JLabel(""));
            add(new JLabel(""));
            add(new JLabel("<html><div style='text-align: center;'>1st Player's Money: " + gl.get1pCastle().getMoney() + "</div></html>"));
            add(new JLabel(""));
            add(new JLabel(""));
            add(new JLabel("<html><div style='text-align: center;'>Health: " + gl.get1pCastle().getHp() + "</div></html>"));
            add(new JLabel(""));
        }
        if ("2castle".equals(todo)) {
            GridLayout gridLayout = new GridLayout(5, 3);
            setLayout(gridLayout);
            add(new JLabel(""));
            add(new JLabel("<html><div style='text-align: center;'>" + gl.whatToDo() + "</div></html>"));
            add(new JLabel(""));
            add(new JLabel(""));
            add(new JLabel("Money: "));
            moneyLabel = new JLabel(Integer.toString(gl.getCurrentPlayer().getMoney()) + "$");
            add(moneyLabel);
            add(new JLabel(""));
            add(new JLabel("<html><div style='text-align: center;'>2nd Player Castle</div></html>"));
            add(new JLabel(""));
            add(new JLabel(""));
            add(new JLabel("<html><div style='text-align: center;'>2nd Player's Money: " + gl.get2pCastle().getMoney() + "</div></html>"));
            add(new JLabel(""));
            add(new JLabel(""));
            add(new JLabel("<html><div style='text-align: center;'>Health: " + gl.get2pCastle().getHp() + "</div></html>"));
            add(new JLabel(""));
        }

        /*
        * Ha üres mezőre kattint
         */
        if ("empty".equals(todo)) {
            if (((gl.getTurn() == 1 && x >= 15) || (gl.getTurn() == 2 && x <= 14)) || gl.getTurn() >= 3) {
                this.change("nothing", x, y);
            } else {
                GridLayout gridLayout = new GridLayout(11, 3);
                this.setLayout(gridLayout);
                this.add(new JLabel(""));
                if (gl.getTurn() == 1) {
                    this.add(new JLabel("<html><div style='text-align: center;'>1st Player Building</div></html>"));
                } else {
                    this.add(new JLabel("<html><div style='text-align: center;'>2nd Player Building</div></html>"));
                }
                this.add(new JLabel(""));
                this.add(new JLabel(""));
                this.add(new JLabel("Money: "));
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
                if ("Kör vége".equals(lab)) {  //gombok kezelése a tornyok esetén
                    gl.nextTurn();
                    OptionPanel.this.change("nothing", x, y);
                }
                if ("1. Torony".equals(lab)) {
                    Tower1 nt = new Tower1(OptionPanel.this.x, OptionPanel.this.y);
                    if (gl.getTurn() == 1) {
                        if (nt.getCost() <= gl.get1pCastle().getMoney()) {
                            gl.get1pCastle().addTower(nt);
                            gl.get1pCastle().pay(nt.getCost());
                            OptionPanel.this.change("nothing", 0, 0);
                        }
                    } else {
                        if (nt.getCost() <= gl.get2pCastle().getMoney()) {
                            gl.get2pCastle().addTower(nt);
                            gl.get2pCastle().pay(nt.getCost());
                            OptionPanel.this.change("nothing", 0, 0);
                        }
                    }
                }
                if ("2. Torony".equals(lab)) {
                    Tower2 nt = new Tower2(OptionPanel.this.x, OptionPanel.this.y);
                    if (gl.getTurn() == 1) {
                        if (nt.getCost() <= gl.get1pCastle().getMoney()) {
                            gl.get1pCastle().addTower(nt);
                            gl.get1pCastle().pay(nt.getCost());
                            OptionPanel.this.change("nothing", 0, 0);
                        }
                    } else {
                        if (nt.getCost() <= gl.get2pCastle().getMoney()) {
                            gl.get2pCastle().addTower(nt);
                            gl.get2pCastle().pay(nt.getCost());
                            OptionPanel.this.change("nothing", 0, 0);
                        }
                    }
                }
                if ("3. Torony".equals(lab)) {
                    Tower3 nt = new Tower3(OptionPanel.this.x, OptionPanel.this.y);
                    if (gl.getTurn() == 1) {
                        if (nt.getCost() <= gl.get1pCastle().getMoney()) {
                            gl.get1pCastle().addTower(nt);
                            gl.get1pCastle().pay(nt.getCost());
                            OptionPanel.this.change("nothing", 0, 0);
                        }
                    } else {
                        if (nt.getCost() <= gl.get2pCastle().getMoney()) {
                            gl.get2pCastle().addTower(nt);
                            gl.get2pCastle().pay(nt.getCost());
                            OptionPanel.this.change("nothing", 0, 0);
                        }
                    }
                }
                if ("upgrade".equals(lab)) {
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
                    OptionPanel.this.change("nothing", 0, 0);
                }
                if ("sell".equals(lab)) {
                    System.out.println("perfect");
                    if (gl.getTurn() == 1) {
                        gl.get1pCastle().addMoney(gl.get1pCastle().getTowers().get(numOfTower).getSellCost());
                        gl.get1pCastle().sellTower(numOfTower);
                    }
                    if (gl.getTurn() == 2) {
                        gl.get2pCastle().addMoney(gl.get2pCastle().getTowers().get(numOfTower).getSellCost());
                        gl.get2pCastle().sellTower(numOfTower);
                    }
                    OptionPanel.this.change("nothing", OptionPanel.this.x, OptionPanel.this.y);
                } //gombok kezelése idáig
                //{"Kétéltű zombi", "Nagy zombi", "Harcoló zombi", "Öngyilkos zombi", "Zombi", "Kör vége"}
                //Zombik gombjainak a kezelése
                if ("Kétéltű zombi".equals(lab)) {
                    AmphibianZombie az = new AmphibianZombie(5,5);
                    
                    if (gl.getTurn() == 3) {
                        if (az.getCost() <= gl.get1pCastle().getMoney()) {
                            gl.get1pCastle().addUnit(az);
                            gl.get1pCastle().pay(az.getCost());
                            OptionPanel.this.change("nothing", 0, 0);
                        }
                    } else {
                        if (az.getCost() <= gl.get2pCastle().getMoney()) {
                            gl.get2pCastle().addUnit(az);
                            gl.get2pCastle().pay(az.getCost());
                            OptionPanel.this.change("nothing", 0, 0);
                        }
                    }
                }
                 
            } catch (Exception e) {
            }
        }

    }
}
