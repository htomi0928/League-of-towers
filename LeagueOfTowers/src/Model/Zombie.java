package Model;

import java.io.IOException;
import javax.swing.ImageIcon;

/*
* A zombik legegyszerűbb és olcsóbb típusa
 */
public class Zombie extends AttackUnits {

    public Zombie(int x, int y) {
        
        super(x, y);

        this.hp = 50;
        this.maxhp = 50;
        this.speed = 5;
        this.damage = 50;
        this.cost = 50;
        this.target = "Castle";
        this.img = new ImageIcon("src/res/zombi1.png").getImage();
    }
}
