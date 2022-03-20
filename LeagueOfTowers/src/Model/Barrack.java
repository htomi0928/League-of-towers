package Model;

import java.io.IOException;
import javax.swing.ImageIcon;

//A zombik ebből a spriteból spawnolnak
public class Barrack extends Sprite {
    String type;
    public Barrack(int x, int y) throws IOException {
        super(x, y);
        type = "barrack";
        img = new ImageIcon("src/res/barracks2.png").getImage();
    }
}
