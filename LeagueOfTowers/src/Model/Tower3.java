package Model;

import java.io.IOException;

public class Tower3 extends Tower {
    public Tower3(int x, int y) throws IOException {
        super(x, y);
        this.hp = 150;
        this.damage=15;
        this.distance = 1;
        this.cost = 200;
        
        this.upgradecost2 = 50;
        this.upgradecost3 = 100;
        this.sellCost = 100;
    }
    
    
    @Override
    public void upgrade() {
        this.level += 1;
        if (this.level == 2) {
            this.damage=20;
        }
        if (this.level == 3) {
            this.damage=25;
            this.distance = 2;
        }
    }
}
