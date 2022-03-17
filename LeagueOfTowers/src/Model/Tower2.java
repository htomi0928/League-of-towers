/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.IOException;

/**
 *
 * @author Pacza Dániel
 */
public class Tower2 extends Tower {
    public Tower2(int x, int y) throws IOException {
        super(x, y);
        hp = 50;
        damage=10;
        distance = 2;
        cost = 150;
        
        this.upgradecost2 = 50;
        this.upgradecost3 = 100;
        this.sellCost = 75;
    }
    
    @Override
    public void upgrade() {
        this.level += 1;
        if (this.level == 2) {
            this.damage=15;
        }
        if (this.level == 3) {
            this.damage=20;
            this.distance = 3;
        }
    }
}
