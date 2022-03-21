package Model;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;


/*
* Adott játékos kastélya
* A játékoshoz tartozó támadó és védekező egységeket, barakkokat ez az osztály gyűjti 
*/
public class Castle extends Sprite {
    private int hp, money;
    private ArrayList<Tower> towers;
    private ArrayList<AttackUnits> units;
    private ArrayList<Barrack> barracks;
    
    public Castle(int x, int y){
        super(x, y);
        hp = 1000;
        money = 1000;
        towers = new ArrayList<>();
        units = new ArrayList<>();
        img = new ImageIcon("src/res/castle.png").getImage(); 
    }
    
    /*
    * Adott játékos pénze
    */
    public int getMoney() {
        return money;
    }
    
    /*
    * Növeli a játékos pénzét m-mel
    */
    public void addMoney(int m) {
        this.money += m;
    }
    
    /*
    * Csökkenti a játékos pénzét m-mel
    */
    public void pay(int m) {
        this.money -= m;
    }
    
    /*
    * Csökkenti a kastély életerejét h-val
    */
    public void loseHp(int h) {
        this.hp -= h;
    }
    
    /*
    * Hozzáad egy tornyot a játékos tornyaihoz
    */
    public void addTower(Tower t) {
        towers.add(t);
    }
    
    /*
    * Hozzáad egy támadó egységet a játékos tornyaihoz
    */
    public void addUnit(AttackUnits au) {
        units.add(au);
    }
    
    /*
    * Visszaadja a játékos tornyait egy listában
    */
    public ArrayList<Tower> getTowers() {
        return towers;
    }
    
    /*
    * Visszaadja a játékos támadó egységeit egy listában
    */
    public ArrayList<AttackUnits> getUnits() {
        return units;
    }
    
    /*
    * Visszaadja a játékos barakkjait egy listában
    */
    public ArrayList<Barrack> getBarracks() {
        return barracks;
    }
    
    /*
    * Amikor vége egy körnek hozzáad 500 aranyat a játékos pénzéhez
    */
    public void addTurnGold() {
        this.money += 500;
    }
    
    public void sellTower(int n) {
        this.towers.remove(n);
    }
    
}
