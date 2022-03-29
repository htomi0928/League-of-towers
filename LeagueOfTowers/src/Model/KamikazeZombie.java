package Model;

import java.io.IOException;

/*
* Zombi speciális típusa
* Nem a kastély, hanem a tornyok az elsődleges célpontjai
* Egy toronyhoz érve azt sebzi, majd meghal
 */
public class KamikazeZombie extends AttackUnits {

    public KamikazeZombie(int x, int y) throws IOException {
        super(x, y);

        this.hp = 50;
        this.maxhp = 50;
        this.speed = 7;
        this.damage = 50;
        this.cost = 120;
        this.target = "Tower";
    }
}
