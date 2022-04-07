package Model;

import java.io.IOException;
import javax.swing.ImageIcon;

/*
* Nagy Zombi, a zombik egy típusa
 */
public class BigZombie extends AttackUnits {

    public BigZombie(int x, int y) {
        super(x, y);

        this.hp = 150;
        this.maxhp = 150;
        this.speed = 3;
        this.damage = 60;
        this.cost = 100;
        this.target = "Castle";
        this.type = "Nagy zombi";
        this.img = new ImageIcon("src/res/zombi4.png").getImage();
    }
}
