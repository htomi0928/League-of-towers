package Model;

import java.io.IOException;

/*
* Zombi speciális típusa
* Képes áthaladni az akadályokon, nem kerüli ki őket
*/
public class AmphibianZombie extends AttackUnits {
    public AmphibianZombie(int x, int y) throws IOException {
        super(x, y);
        hp = 80;
        speed = 5;
        damage = 50;
        cost = 100;
        target = "Castle";
    }
}
