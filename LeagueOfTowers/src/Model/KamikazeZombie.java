package Model;

import java.io.IOException;

/*
* Zombi speciális típusa
* Nem a kastély, hanem a tornyok az elsődleges célpontjai
* Egy toronyhoz érve azt sebzi, majd meghal
*/
public class KamikazeZombie extends AttackUnits{
    public KamikazeZombie(int x, int y) throws IOException {
        super(x, y);
        hp = 50;
        speed = 7;
        damage = 50;
        cost = 120;
        target = "Tower";
    }
}
