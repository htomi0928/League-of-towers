package Model;

import java.io.IOException;
import javax.swing.ImageIcon;

//A zombik ebből a spriteból spawnolnak
public class Barrack extends Sprite {

    String type;

    public Barrack(int x, int y) {
        super(x, y);

        this.type = "barrack";
        this.img = new ImageIcon("src/res/barracks2.png").getImage();
    }
}
