package Model;

import java.io.IOException;
import java.util.ArrayList;

public class Castle extends Sprite {
    private int hp, money;
    private ArrayList<Tower> towers;
    private ArrayList<AttackUnits> units;
    
    public Castle(int x, int y) throws IOException {
        super(x, y);
        hp = 1000;
        money = 1000;
        towers = new ArrayList<>();
        units = new ArrayList<>();
        
    }
    
    public int getMoney() {
        return money;
    }
    
    public void addMoney(int m) {
        this.money += m;
    }
    
    public void pay(int m) {
        this.money -= m;
    }
    
    public void loseHp(int h) {
        this.hp -= h;
    }
    
    public void addTower(Tower t) {
        towers.add(t);
    }
    
    public void addUnit(AttackUnits au) {
        units.add(au);
    }
    
    public ArrayList<Tower> getTowers() {
        return towers;
    }
    
    public ArrayList<AttackUnits> getUnits() {
        return units;
    }
    
    public void addTurnGold() {
        this.money += 500;
    }
    
}
