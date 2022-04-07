package Model;

import java.io.IOException;
import javax.swing.ImageIcon;
import LoTExceptions.IllegalLevelOfTowerException;

/*
* Az első Towerből származtatott osztály
 */
public class Tower1 extends Tower {

    public Tower1(int x, int y) {

        super(x, y);

        this.hp = 100;
        this.maxhp = 100;
        this.damage = 10;
        this.distance = 1;
        this.cost = 100;
        this.type = 1;
        this.upgradecost2 = 50;
        this.upgradecost3 = 100;
        this.sellCost = 50;
        this.img = new ImageIcon("src/res/torony1.png").getImage();
    }

    @Override
    /*
    * Először is növeli az adott torony szintjét egy meghívás esetén. Ezután
    * megnézi, hogy hanyas szintű az adott torony és ennek megfelelően kap plusz
    * hatótávolságot és/vagy sebzést az adott torony
     */
    public void upgrade() throws IllegalLevelOfTowerException {
        if (this.level < 3) {
        this.level += 1;
        if (this.level == 2) {
            this.damage = 15;
        }
        if (this.level == 3) {
            this.damage = 20;
            this.distance = 2;
        }
        }
        else {
            throw new IllegalLevelOfTowerException("Level of a Tower can't be more than 3!");
        }
    }
}
