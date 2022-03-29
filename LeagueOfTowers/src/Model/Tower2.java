package Model;

import java.io.IOException;
import javax.swing.ImageIcon;

/*
* A második Towerből származtatott osztály
*/
public class Tower2 extends Tower {
    public Tower2(int x, int y) throws IOException {
        super(x, y);
        this.hp = 50;
        this.maxhp = 50;
        this.damage=10;
        this.distance = 2;
        this.cost = 150;
        this.type = 2;
        this.upgradecost2 = 50;
        this.upgradecost3 = 100;
        this.sellCost = 75;
        this.img = new ImageIcon("src/res/torony2.jpg").getImage();
    }
    
    /*
    * Először is növeli az adott torony szintjét egy meghívás esetén. Ezután
    * megnézi, hogy hanyas szintű az adott torony és ennek megfelelően kap plusz
    * hatótávolságot és/vagy sebzést az adott torony
    */
    @Override
    public void upgrade() {
        this.level += 1;
        if (this.level == 2) {
            this.damage=15;
        }
        if (this.level == 3) {
            this.damage=20;
            this.distance = 3;
        }
    }
}
