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
public class AmphibianZombie extends AttackUnits {
    public AmphibianZombie(int x, int y) throws IOException {
        super(x, y);
        hp = 80;
        speed = 5;
        damage = 50;
        cost = 100;
        target = "Castle";
    }
}
