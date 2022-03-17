package Model;

import java.awt.Image;
import java.io.IOException;
import res.ResourceLoader;

public class Sprite {

    private int xc;
    private int yc;
    //private Image zombie, bigZombie, fighterZombie, amphibianZombie, kamikazeZombie, tower1, tower2, tower3, castle, barrack, obsticle;

    public Sprite(int x, int y) throws IOException {
        xc = x;
        yc = y;
        /*
        zombie = ResourceLoader.loadImage("res/zombi1");
        bigZombie = ResourceLoader.loadImage("res/zombi2");
        fighterZombie = ResourceLoader.loadImage("res/zombi2");
        amphibianZombie = ResourceLoader.loadImage("res/zombi3");
        kamikazeZombie = ResourceLoader.loadImage("res/zombi5");
        tower1 = ResourceLoader.loadImage("res/torony1");
        tower2 = ResourceLoader.loadImage("res/torony2");
        tower3 = ResourceLoader.loadImage("res/torony3");
        //castle = ResourceLoader.loadImage("res/kastély");
        barrack = ResourceLoader.loadImage("res/barracks2");
        obsticle = ResourceLoader.loadImage("res/akadály1");
        */
    }
    
    public int getXc() {
        return this.xc;
    }
    
    public int getYc() {
        return this.yc;
    }
}
