package View;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionPanel extends JPanel {

    public OptionPanel() {
        GridLayout gridLayout = new GridLayout(11, 3);
        setLayout(gridLayout);
        this.setBackground(new Color(255, 255, 255));
        for (int i = 0; i < 11; ++i) {
            JLabel lab1 = new JLabel("");
            this.add(lab1);
            if (i % 2 == 1) {
                JButton button = new JButton("OK");
                this.add(button);
            }
            else {
                JLabel lab2 = new JLabel("");
                this.add(lab2);
            }
            JLabel lab3 = new JLabel("");
            this.add(lab3);
            
            //button.addActionListener(new Board.ButtonListener());
            //button.setPreferredSize(new Dimension(10, 10));
            //button.setBackground(Color.WHITE);
            
        }
    }
}
