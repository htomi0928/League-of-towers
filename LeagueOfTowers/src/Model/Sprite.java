package Model;

import java.awt.Image;
import java.io.IOException;
import res.ResourceLoader;

public class Sprite {

    /*
    * A position nem a Sprite koordinátáit tárolja a Boardon
    * A position a négyzethálón elfoglalt helyet jelzi
    */
    private Position xy;
    
    
    
    //private Image zombie, bigZombie, fighterZombie, amphibianZombie, kamikazeZombie, tower1, tower2, tower3, castle, barrack, obsticle;

    public Sprite(int x, int y) throws IOException {
        xy = new Position(x, y);
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
    
    /*
    * X koordináta
    */
    public int getXc() {
        return this.xy.getX();
    }
    
    /*
    * Y koordináta
    */
    public int getYc() {
        return this.xy.getY();
    }
    
    /*
    * Egy Sprite mozgatása x, y mennyiséggel
    */
    public void move(int x, int y) {
        this.xy.move(x, y);
    }
}
