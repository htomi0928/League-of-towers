package Model;

import java.io.IOException;
import javax.swing.ImageIcon;

/*
* Akadály a pályán
 */
public class Obsticle extends Sprite {

    String type;

    public Obsticle(int x, int y) {
        super(x, y);
        
        this.type = "river";
        this.img = new ImageIcon("src/res/folyo.png").getImage();
    }
}
