/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.IOException;

/*
* Zombi speciális típusa
* Ha ellenséges csapat mellé ér, sebzi őket
 */
public class FighterZombie extends AttackUnits {

    private int enemyDamage; //A sebzés, amit az ellenséges csapatoktra ad le

    public FighterZombie(int x, int y) throws IOException {
        super(x, y);

        this.hp = 100;
        this.maxhp = 100;
        this.speed = 5;
        this.damage = 70;
        this.enemyDamage = 50;
        this.cost = 150;
        this.target = "Castle";
    }

}
