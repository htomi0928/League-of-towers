package Model;

import java.io.IOException;
import javax.swing.ImageIcon;

/*
* A harmadik Towerből származtatott osztály
*/
public class Tower3 extends Tower {
    public Tower3(int x, int y) throws IOException {
        super(x, y);
        
        this.hp = 150;
        this.maxhp = 150;
        this.damage=15;
        this.distance = 1;
        this.cost = 200;
        this.type = 3;
        this.upgradecost2 = 50;
        this.upgradecost3 = 100;
        this.sellCost = 100;
        this.img = new ImageIcon("src/res/torony3.png").getImage();
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
            this.damage=20;
        }
        if (this.level == 3) {
            this.damage=25;
            this.distance = 2;
        }
    }
}
