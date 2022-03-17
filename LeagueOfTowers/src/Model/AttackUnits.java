package Model;

import java.io.IOException;


/*
* A zombik ősosztálya
*/
public class AttackUnits extends Sprite {
    int hp, speed, damage, cost;
    String target;
    
    
    public AttackUnits(int x, int y) throws IOException {
        super(x, y);
    }
    
    
    /*
    * Adott támadó egység életereje
    */
    public int getHp() { 
        return this.hp;
    }
    
    
    /*
    * Adott támadó egység sebessége
    * Egy támadáskor mennyit sebez
    */
    public int getSpeed() {
        return this.speed;
    }
    
    
    /*
    * Adott támadó egység sebzése
    */
    public int getDamage() { 
        return this.damage;
    }
    
    
    /*
    * Adott támadó egység ára
    */
    public int getCost() { 
        return this.cost;
    }
    
    
    /*
    * Adott támadó egység célpontja
    */
    public String getTarget() { 
        return target;
    }
    
}
