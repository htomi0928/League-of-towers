/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leagueoftowers;

import java.io.IOException;

/**
 *
 * @author Pacza Dániel
 */
public class BigZombie extends AttackUnits {
    public BigZombie(int x, int y) throws IOException{
        super(x, y);
        hp = 20;
        level = 20;
        speed = 20;
        damage = 20;
        cost = 20;
        target = "";

    }
}
