package Model;

import java.io.IOException;

public class Tower1 extends Tower {
    public Tower1(int x, int y) throws IOException {
        
        super(x, y);
        
        this.hp = 100;
        this.damage=10;
        this.distance = 1;
        this.cost = 100;
        
        this.upgradecost2 = 50;
        this.upgradecost3 = 100;
        this.sellCost = 50;
    }
    
    @Override
    public void upgrade() {
        this.level += 1;
        if (this.level == 2) {
            this.damage=15;
        }
        if (this.level == 3) {
            this.damage=20;
            this.distance = 2;
        }
    }
}
