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
public class BigZombie extends AttackUnits {
    public BigZombie(int x, int y) throws IOException{
        super(x, y);
        hp = 150;
        speed = 3;
        damage = 60;
        cost = 100;
        target = "Castle";

    }
}
