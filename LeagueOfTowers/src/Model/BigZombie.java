package Model;

import java.io.IOException;

/*
* Nagy Zombi, a zombik egy típusa
*/
public class BigZombie extends AttackUnits {
    public BigZombie(int x, int y) throws IOException{
        super(x, y);
        hp = 150;
        speed = 3;
        damage = 60;
        cost = 100;
        target = "Castle";

    }
}
