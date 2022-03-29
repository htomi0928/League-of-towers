package Model;

import java.io.IOException;

/*
* Zombi speciális típusa
* Képes áthaladni az akadályokon, nem kerüli ki őket
*/
public class AmphibianZombie extends AttackUnits {
    public AmphibianZombie(int x, int y) throws IOException {
        super(x, y);
        this.hp = 80;
        this.maxhp = 80;
        this.speed = 5;
        this.damage = 50;
        this.cost = 100;
        this.target = "Castle";
    }
}
