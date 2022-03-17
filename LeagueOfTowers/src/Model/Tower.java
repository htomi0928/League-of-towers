package Model;

import java.io.IOException;

/*
* Védekező egységek ősosztálya
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
    
    /*
    * Az adott torony életereje
    */
    public int getHp() { 
        return this.hp;
    }
    
    /*
    * Az adott torony szintje
    */
    public int getLevel() { 
        return this.level;
    }
    
    /*
    * Az adott torony sebzése
    */
    public int getDamage() {
        return this.damage;
    }
    
    /*
    * Az adott torny tábolsága a kastélytól
    */
    public int getDistance() {
        return this.distance;
    }
    
    /*
    * Az adott torony ára
    */
    public int getCost() {
        return this.cost;
    }
    
    /*
    * Az adott torny első fejlesztésének ára
    */
    public int getUpgradeCost2() {
        return this.upgradecost2;
    }
    
    /*
    * Az adott torny második fejlesztésének ára
    */
    public int getUpgradeCost3() {
        return this.upgradecost3;
    }
    
    /*
    * Növeli egyel az adott torony szintjét
    */
    public void upgrade() {
        this.level += 1;
    }
    
    /*
    * Ha több, mint 0 életereje van az adott toronynak, akkor true-t ad vissza
    * Azért fontos, hogy tudjuk rom-e 
    */
    public boolean getStatus() {
        return this.stillstanding; 
    }
    
    /*
    * Az adott toronyrom eltakarításának az ára
    */
    public int getCleanRuinCost() {
        return this.cleanRuin;
    }
    
    /*
    * Az adott torony eladásának az ára
    */
    public int getSellCost() {
        return this.sellCost;
    }
}
