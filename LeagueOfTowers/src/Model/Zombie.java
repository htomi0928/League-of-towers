package Model;

import java.io.IOException;

/*
* A zombik legegyszerűbb és olcsóbb típusa
*/
public class Zombie extends AttackUnits{
    public Zombie(int x, int y) throws IOException {
        super(x, y);
        hp = 50;
        speed = 5;
        damage = 50;
        cost = 50;
        target = "Castle";
                
    }
}
