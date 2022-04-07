package Model;

import java.io.IOException;
import javax.swing.ImageIcon;

/*
* Zombi speciális típusa
* Képes áthaladni az akadályokon, nem kerüli ki őket
*/
public class AmphibianZombie extends AttackUnits {
    public AmphibianZombie(int x, int y) {
        super(x, y);
        this.hp = 80;
        this.maxhp = 80;
        this.speed = 5;
        this.damage = 50;
        this.cost = 100;
        this.target = "Castle";
        this.type = "Kétéltű zombi";
        this.img = new ImageIcon("src/res/zombi3.png").getImage();
    }
}
