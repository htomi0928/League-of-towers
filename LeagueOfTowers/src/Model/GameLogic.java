package Model;

import LoTExceptions.InvalidInputException;
import static Model.Main.gl;
import java.io.IOException;
import java.util.ArrayList;

/*
* A játék logikájárt felelős osztály
* Itt tárolódik a két játékos kastélya, az akadályok
* Ez végzi a körök lebonyolítását
 */
public class GameLogic {

    private Castle pl1;
    private Castle pl2;
    private Barrack b1;
    private Barrack b2;
    private Barrack b3;
    private Barrack b4;
    private int turn;
    private ArrayList<Obsticle> obsticles;

    public GameLogic() throws IOException {
        pl1 = new Castle(4 - 1, 10 - 1); //Az első játékos kastélya és pozíciója
        pl2 = new Castle(27 - 1, 10 - 1); //A második játékos kastélya és pozíciója
        b1 = new Barrack(5 - 1, 5 - 1);
        b2 = new Barrack(5 - 1, 15 - 1);
        b3 = new Barrack(26 - 1, 5 - 1);
        b4 = new Barrack(26 - 1, 15 - 1);
        pl1.addBarrack(b1);
        pl1.addBarrack(b2);
        pl2.addBarrack(b3);
        pl2.addBarrack(b4);
        
        turn = 1; //A körök ennek az értéknek a változásával fordulnak a játékosok között
    }

    public void damage() {
        for (int i = 0; i < pl2.getTowers().size(); i++) {
            int xc = pl2.getTowers().get(i).getXc();
            int yc = pl2.getTowers().get(i).getYc();
            int radius = pl2.getTowers().get(i).distance;

            for (int j = 0; j < pl1.getUnits().size(); j++) {
                int xp = pl1.getUnits().get(j).getXc();
                int yp = pl1.getUnits().get(j).getYc();

                double distance = Math.sqrt(Math.pow(xp - xc, 2) + Math.pow(yp - yc, 2));
                if (distance < radius) {
                    try {
                        pl1.getUnits().get(j).loseHp(pl2.getTowers().get(i).getDamage());
                    } catch (InvalidInputException exc) {
                        System.out.println("Negative damage");
                    }
                    break;
                }
            }
        }
    }

    /*
    * Melyik kör van éppen
     */
    public int getTurn() {
        return turn;
    }

    /*
    * A játék során 5 kör van:
    * - 1.: Első játékos épít
    * - 2.: Második játékos épít
    * - 3.: Első játékos telepít támadóegységeket
    * - 4.: Második játékos telepít támadóegységeket
    * - 5.: Támadás
    * A támadás után újból jön az első kör
     */
    public void nextTurn() {
        if (turn == 5) {
            turn = 1;
            pl1.addTurnGold();
            pl2.addTurnGold();
        } else {
            turn += 1;
        }
    }

    /*
    * Az első játékos kastélya
     */
    public Castle get1pCastle() {
        return pl1;
    }

    /*
    * A második játékos kastélya
     */
    public Castle get2pCastle() {
        return pl2;
    }

    /*
    * Visszaadja az akadályokat a pályán
     */
    public ArrayList<Obsticle> getObsticles() {
        return this.obsticles;
    }

    public String returnSprites(int x, int y) {
        if (pl1.SpriteCoord(x, y) != null) {
            return ("1" + pl1.SpriteCoord(x, y));
        } else {
            if (pl1.getXc() == x && pl1.getYc() == y) {
                return "1castle";
            }
        }
        if (pl2.SpriteCoord(x, y) != null) {
            return ("2" + pl2.SpriteCoord(x, y));
        } else {
            if (pl2.getXc() == x && pl2.getYc() == y) {
                return "2castle";
            }
        }
        return "empty";
    }

    public Castle getCurrentPlayer() {
        if (turn == 1 || turn == 3) {
            return pl1;
        }
        if (turn == 2 || turn == 4) {
            return pl2;
        }
        return null;
    }

    public String whatToDo() {
        switch (turn) {
            case 1:
                return "1st Player Building";
            case 2:
                return "2st Player Building";
            case 3:
                return "1st Player Training Units";
            case 4:
                return "2nd Player Training Units";
            case 5:
                return "Attacking";
        }
        return "Error";
    }
}
