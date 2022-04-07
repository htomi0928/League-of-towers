package Model;

import java.awt.Image;

public class Sprite {

    /*
    * A position nem a Sprite koordinátáit tárolja a Boardon
    * A position a négyzethálón elfoglalt helyet jelzi
     */
    private Position xy;
    protected Image img;

    //private Image zombie, bigZombie, fighterZombie, amphibianZombie, kamikazeZombie, tower1, tower2, tower3, castle, barrack, obsticle;
    public Sprite(int x, int y) {
        this.xy = new Position(x, y);
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

    public Image getImg() {
        return this.img;
    }
}
