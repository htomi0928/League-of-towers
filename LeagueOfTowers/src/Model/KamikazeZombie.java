package Model;

import java.io.IOException;
import javax.swing.ImageIcon;

/*
* Zombi speciális típusa
* Nem a kastély, hanem a tornyok az elsődleges célpontjai
* Egy toronyhoz érve azt sebzi, majd meghal
 */
public class KamikazeZombie extends AttackUnits {

    public KamikazeZombie(int x, int y) {
        super(x, y);

        this.hp = 50;
        this.maxhp = 50;
        this.speed = 7;
        this.damage = 50;
        this.cost = 120;
        this.target = "Tower";
        this.img = new ImageIcon("src/res/zombi5.png").getImage();
    }
}
