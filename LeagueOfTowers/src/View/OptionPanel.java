package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static leagueoftowers.Main.gl;
import java.io.IOException;
import leagueoftowers.Tower1;
import leagueoftowers.Tower2;
import leagueoftowers.Tower3;

public class OptionPanel extends JPanel {

    private JLabel moneyLabel;
    public int x;
    public int y;

    public OptionPanel() {

        GridLayout gridLayout = new GridLayout(2, 3);
        setLayout(gridLayout);
        add(new JLabel(""));
        switch (gl.getTurn()) {
            case 1:
                add(new JLabel("1st Player Building"));
                add(new JLabel(""));
                add(new JLabel(""));
                add(new JLabel("Money: "));
                moneyLabel = new JLabel(Integer.toString(gl.get1pCastle().getMoney()) + "$");
                add(moneyLabel);
                break;
            case 2:
                add(new JLabel("2nd Player Building"));
                add(new JLabel(""));
                add(new JLabel(""));
                add(new JLabel("Money: "));
                moneyLabel = new JLabel(Integer.toString(gl.get2pCastle().getMoney()) + "$");
                add(moneyLabel);
                break;
            case 3:
                add(new JLabel("1st Player Trainig Units"));
                add(new JLabel(""));
                add(new JLabel(""));
                add(new JLabel("Money: "));
                moneyLabel = new JLabel(Integer.toString(gl.get1pCastle().getMoney()) + "$");
                add(moneyLabel);
                break;
            case 4:
                add(new JLabel("2nd Player Trainig Units"));
                add(new JLabel(""));
                add(new JLabel(""));
                add(new JLabel("Money: "));
                moneyLabel = new JLabel(Integer.toString(gl.get2pCastle().getMoney()) + "$");
                add(moneyLabel);
                break;
            case 5:
                add(new JLabel("Attacking"));
                add(new JLabel(""));
                add(new JLabel(""));
                add(new JLabel(""));
                add(new JLabel(""));
                break;
        }

    }

    public void change(String todo, int x, int y) throws IOException {
        this.x = x;
        this.y = y;
        this.removeAll();
        if ("nothing".equals(todo)) {
            GridLayout gridLayout = new GridLayout(2, 3);
            setLayout(gridLayout);
            add(new JLabel(""));
            switch (gl.getTurn()) {
                case 1:
                    add(new JLabel("1st Player Building"));
                    add(new JLabel(""));
                    add(new JLabel(""));
                    add(new JLabel("Money: "));
                    moneyLabel = new JLabel(Integer.toString(gl.get1pCastle().getMoney()) + "$");
                    add(moneyLabel);
                    break;
                case 2:
                    add(new JLabel("2nd Player Building"));
                    add(new JLabel(""));
                    add(new JLabel(""));
                    add(new JLabel("Money: "));
                    moneyLabel = new JLabel(Integer.toString(gl.get2pCastle().getMoney()) + "$");
                    add(moneyLabel);
                    break;
                case 3:
                    add(new JLabel("1st Player Trainig Units"));
                    add(new JLabel(""));
                    add(new JLabel(""));
                    add(new JLabel("Money: "));
                    moneyLabel = new JLabel(Integer.toString(gl.get1pCastle().getMoney()) + "$");
                    add(moneyLabel);
                    break;
                case 4:
                    add(new JLabel("2nd Player Trainig Units"));
                    add(new JLabel(""));
                    add(new JLabel(""));
                    add(new JLabel("Money: "));
                    moneyLabel = new JLabel(Integer.toString(gl.get2pCastle().getMoney()) + "$");
                    add(moneyLabel);
                    break;
                case 5:
                    add(new JLabel("Attacking"));
                    add(new JLabel(""));
                    add(new JLabel(""));
                    add(new JLabel(""));
                    add(new JLabel(""));
                    break;
            }
        }
        if ("tower".equals(todo)) {

        }
        if ("barack".equals(todo)) {

        }

        if ("empty".equals(todo)) {
            GridLayout gridLayout = new GridLayout(11, 3);
            this.setLayout(gridLayout);
            this.add(new JLabel(""));
            if (gl.getTurn() == 1) {
                this.add(new JLabel("1st Player Building"));
            } else {
                this.add(new JLabel("2nd Player Building"));
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
                    button.addActionListener(new ButtonListener(labs[(i - 1) / 2]));
                    this.add(button);
                } else {
                    JLabel lab2 = new JLabel("");
                    this.add(lab2);
                }
                JLabel lab3 = new JLabel("");
                this.add(lab3);
            }
        }
        this.revalidate();
        this.repaint();
    }

    private class ButtonListener implements ActionListener {

        String lab;

        public ButtonListener(String lab) throws IOException {
            this.lab = lab;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
            if ("Kör vége".equals(lab)) {
                gl.nextTurn();
                OptionPanel.this.change("nothing", x, y);
            }
            if ("1. Torony".equals(lab)) {
                Tower1 nt = new Tower1(OptionPanel.this.x, OptionPanel.this.y);
                if (gl.getTurn() == 1) {
                    if (nt.getCost() <= gl.get1pCastle().getMoney()) {
                        gl.get1pCastle().addTower(nt);
                        gl.get1pCastle().pay(nt.getCost());
                    }
                } else {
                    if (nt.getCost() <= gl.get2pCastle().getMoney()) {
                        gl.get2pCastle().addTower(nt);
                        gl.get2pCastle().pay(nt.getCost());
                    }
                }
            }
            if ("2. Torony".equals(lab)) {
                Tower2 nt = new Tower2(OptionPanel.this.x, OptionPanel.this.y);
                if (gl.getTurn() == 1) {
                    if (nt.getCost() <= gl.get1pCastle().getMoney()) {
                        gl.get1pCastle().addTower(nt);
                        gl.get1pCastle().pay(nt.getCost());
                    }
                } else {
                    if (nt.getCost() <= gl.get2pCastle().getMoney()) {
                        gl.get2pCastle().addTower(nt);
                        gl.get2pCastle().pay(nt.getCost());
                    }
                }
            }
            if ("3. Torony".equals(lab)) {
                Tower3 nt = new Tower3(OptionPanel.this.x, OptionPanel.this.y);
                if (gl.getTurn() == 1) {
                    if (nt.getCost() <= gl.get1pCastle().getMoney()) {
                        gl.get1pCastle().addTower(nt);
                        gl.get1pCastle().pay(nt.getCost());
                    }
                } else {
                    if (nt.getCost() <= gl.get2pCastle().getMoney()) {
                        gl.get2pCastle().addTower(nt);
                        gl.get2pCastle().pay(nt.getCost());
                    }
                }
            }
        }
        catch(Exception e) {}
        }
       
    }
}


