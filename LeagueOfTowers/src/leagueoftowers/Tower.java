/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leagueoftowers;

import java.io.IOException;

/**
 *
 * @author Pacza Dániel
 */
public class Tower extends Sprite {
    
    int hp, level, damage, distance, cost;
    boolean stillstanding;

    public Tower(int x, int y) throws IOException {
        super(x, y);
        stillstanding = true;
        level = 1;
    }
    
    public int getHp() {
        return this.hp;
    }
    
    public int getLevel() {
        return level;
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
    
    
    
    
    

}
