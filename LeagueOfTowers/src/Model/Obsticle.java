package Model;

import java.io.IOException;

/*
* Akadály a pályán
*/
public class Obsticle extends Sprite {
    String type;
    
    public Obsticle(int x, int y) throws IOException {
        super(x, y);
        type = "obsticle";
    }
}
