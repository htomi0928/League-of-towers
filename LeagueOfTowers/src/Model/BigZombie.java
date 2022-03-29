package Model;

import java.io.IOException;

/*
* Nagy Zombi, a zombik egy típusa
 */
public class BigZombie extends AttackUnits {

    public BigZombie(int x, int y) throws IOException {
        super(x, y);

        this.hp = 150;
        this.maxhp = 150;
        this.speed = 3;
        this.damage = 60;
        this.cost = 100;
        this.target = "Castle";

    }
}
