package leagueoftowers;

import java.io.IOException;

public class Zombie extends AttackUnits{
    public Zombie(int x, int y) throws IOException {
        super(x, y);
        hp = 20;
        level = 20;
        speed = 20;
        damage = 20;
        cost = 20;
        target = "";
                
    }
}
