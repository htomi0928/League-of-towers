package leagueoftowers;

import java.io.IOException;

public class AttackUnits extends Sprite {
    int hp, level, speed, damage, cost;
    String target;
    
    public AttackUnits(int x, int y) throws IOException {
        super(x, y);
    }
    
}
