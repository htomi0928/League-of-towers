package leagueoftowers;

import java.io.IOException;

public class Tower1 extends Tower {
    public Tower1(int x, int y) throws IOException {
        
        super(x, y);
        hp = 20;
        level = 3;
        damage=200;
        distance = 2000;
        cost = 500;
    }
}
