package Model;

import java.io.IOException;
import LoTExceptions.InvalidInputException;

/*
* A zombik ősosztálya
 */
public class AttackUnits extends Sprite {

    int hp, maxhp, speed, damage, cost;
    String target;
    String type;
    public int wayX, wayY;

    public AttackUnits(int x, int y){
        super(x, y);
        wayX = 0;
        wayY = 0;
    }

    /*
    * Adott támadó egység életereje
     */
    public int getHp() {
        return this.hp;
    }

    public int getMaxhp() {
        return maxhp;
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
    
    public String getType() {
        return this.type;
    }

    public void loseHp(int h) throws InvalidInputException {
        if (h < 0) {
            throw new InvalidInputException("The damage can't be more than 0!");
        }
        if (this.hp < h) {
            this.hp = 0;
        }
        else {
            this.hp -= h;
        }
    }
}
