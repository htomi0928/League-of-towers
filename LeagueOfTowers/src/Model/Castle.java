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
    
    public int getHp() {
        return hp;
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
    
    /*
    * Törli a tornyot a listából
    */
    public void sellTower(int n) {
        this.towers.remove(n);
    }
    
    /*
    * Koordináták alapján visszaad egy tornyot vagy egy null értéket
    */
    public Tower returnTower(int x, int y) {
        for (int i = 0; i < towers.size(); i++) {
            if (towers.get(i).getXc() == x && towers.get(i).getYc() == y) {
                return towers.get(i);
            }
        }
        return null;
    }
    
    /*
    * Koordináták alapján visszaadja az egységek listáját, melyek a mezőn vannak
    */
    public ArrayList<AttackUnits> returnUnits(int x, int y) {
        ArrayList<AttackUnits> attun = new ArrayList();
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getXc() == x && units.get(i).getYc() == y) {
                attun.add(units.get(i));
            }
        }
        return attun;
    }
    
    /*
    * Koordináták alapján visszaad egy barrackot vagy egy null értéket
    */
    public Barrack returnBarrack(int x, int y) {
        for (int i = 0; i < barracks.size(); i++) {
            if (barracks.get(i).getXc() == x && barracks.get(i).getYc() == y) {
                return barracks.get(i);
            }
        }
        return null;
    }
    
    public int returnTowerCoord(int x, int y) {
        for (int i = 0; i < towers.size(); i++) {
            if (towers.get(i).getXc() == x && towers.get(i).getYc() == y) {
                return i;
            }
        }
        return -1;
    }
    
    public String SpriteCoord(int x, int y) {
        if (returnTower(x, y) != null) {
            return "tower";
        }
        /*
        if (returnBarrack(x, y) != null) {
            return "barrack";
        }
        else {
            System.out.println("RIGHT");
        }
        */
        if (!returnUnits(x, y).isEmpty()) {
            return "units";
        }
        return null;
    }
}
