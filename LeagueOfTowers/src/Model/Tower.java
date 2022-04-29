package Model;

import LoTExceptions.IllegalLevelOfTowerException;
import java.io.IOException;
import javax.swing.ImageIcon;

/*
* Védekező egységek ősosztálya
 */
public class Tower extends Sprite {

    int hp, maxhp, level, damage, distance, cost, upgradecost2, upgradecost3, cleanRuin, sellCost;
    boolean stillstanding;
    int type;
    int turnWhenDied;

    public Tower(int x, int y) {
        super(x, y);

        this.stillstanding = true;
        this.level = 1;
        this.cleanRuin = 50;
        turnWhenDied = 0;
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
    public void upgrade() throws IllegalLevelOfTowerException {
        if (this.level < 3) {
            this.level += 1;
        } else {
            throw new IllegalLevelOfTowerException("Nem lehet egy torony 3-asnál magasabb szintű!");
        }
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

    public int getType() {
        return this.type;
    }
    
    public int getWhenDied() {
        return turnWhenDied;
    }

    public int getMaxhp() {
        return maxhp;
    }
    
    public void setWhenDied(int twd) {
        this.turnWhenDied = twd;
    }
    
    public void loseHp(int h) {
        if (h >= this.hp) {
            this.hp = 0;
            this.stillstanding = false;
            
        }
        else {
            this.hp -= h;
        }
    }
    
    public void changeToTombstone(){
        this.img = new ImageIcon("src/res/tombstone.png").getImage();
        this.damage = 0;
    }
}
