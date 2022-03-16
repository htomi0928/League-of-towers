package View;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import leagueoftowers.Sprite;

public class OptionPanel extends JPanel {

    private JLabel moneyLabel;
    private Sprite[] attackUnits = {/*ide majd az összes zombis sprite*/};
    private Sprite[] towers = { };

    public OptionPanel() {
        GridLayout gridLayout = new GridLayout(13, 3);
        setLayout(gridLayout);
        add(new JLabel("Money: "));
        moneyLabel = new JLabel("$1000");
        add(moneyLabel);

        this.setBackground(new Color(200, 200, 200));
        for (int i = 0; i < 11; ++i) {
            JLabel lab1 = new JLabel("");
            this.add(lab1);
            JLabel lab3 = new JLabel("");
            this.add(lab3);
            if (i % 2 == 1) {
                JButton button = new JButton("OK");
                this.add(button);
            }
            else {
                JLabel lab2 = new JLabel("");
                this.add(lab2);
            }
            
            //button.addActionListener(new Board.ButtonListener());
            //button.setPreferredSize(new Dimension(10, 10));
            //button.setBackground(Color.WHITE);
            
        }
    }
}
