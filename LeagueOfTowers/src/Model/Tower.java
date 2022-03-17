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
public class Tower extends Sprite {
    
    int hp, level, damage, distance, cost, upgradecost2, upgradecost3, cleanRuin, sellCost;
    boolean stillstanding;

    public Tower(int x, int y) throws IOException {
        super(x, y);
        this.stillstanding = true;
        this.level = 1;
        this.cleanRuin = 50;
    }
    
    public int getHp() {
        return this.hp;
    }
    
    public int getLevel() {
        return this.level;
    }
    
    public int getDamage() {
        return this.damage;
    }
    
    public int getDistance() {
        return this.distance;
    }
    
    public int getCost() {
        return this.cost;
    }
    
    public int getUpgradeCost2() {
        return this.upgradecost2;
    }
    
    public int getUpgradeCost3() {
        return this.upgradecost3;
    }
    
    public void upgrade() {
        this.level += 1;
    }
    
    public boolean getStatus() {
        return this.stillstanding;
    }
    
    public int getCleanRuinCost() {
        return this.cleanRuin;
    }
    
    public int getSellCost() {
        return this.sellCost;
    }
}
