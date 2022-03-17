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
public class FighterZombie extends AttackUnits {
    
    private int enemyDamage;
    
    public FighterZombie(int x, int y) throws IOException {
        super(x, y);
        hp = 100;
        speed = 5;
        damage = 70;
        enemyDamage = 50;
        cost = 150;
        target = "Castle";
    }
    
}
