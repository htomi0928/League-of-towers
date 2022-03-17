package Model;

import java.io.IOException;

public class AttackUnits extends Sprite {
    int hp, speed, damage, cost;
    String target;
    
    public AttackUnits(int x, int y) throws IOException {
        super(x, y);
    }
    
    public int getHp() {
        return this.hp;
    }
    
    public int getSpeed() {
        return this.speed;
    }
    
    public int getDamage() {
        return this.damage;
    }
    
    public int getCost() {
        return this.cost;
    }
    
    public String getTarget() {
        return target;
    }
    
}
