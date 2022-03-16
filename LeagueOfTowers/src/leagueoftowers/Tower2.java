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
public class Tower2 extends Tower {
    public Tower2(int x, int y) throws IOException {
        super(x, y);
        hp = 20;
        level = 3;
        damage=200;
        distance = 2000;
        cost = 500;
    }
}
