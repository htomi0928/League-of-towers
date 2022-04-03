package Model;

import LoTExceptions.InvalidInputException;
import java.io.IOException;
import java.util.ArrayList;
import static View.Board.width;
import static View.Board.height;
import java.util.Random;

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
        /*b1 = new Barrack(5 - 1, 5 - 1);
        b2 = new Barrack(5 - 1, 15 - 1);
        b3 = new Barrack(26 - 1, 5 - 1);
        b4 = new Barrack(26 - 1, 15 - 1);
        pl1.addBarrack(b1);
        pl1.addBarrack(b2);
        pl2.addBarrack(b3);
        pl2.addBarrack(b4);*/
        obsticles = new ArrayList();

        turn = 1; //A körök ennek az értéknek a változásával fordulnak a játékosok között

        Random rand = new Random();;
        int barrackCount = 2;
        int barrackDist = 5;
        for (int i = 0; i < barrackCount; i++) {
            boolean goodPos = true;
            int y = rand.nextInt(height);
            int x = rand.nextInt(width / 2);
            for (int j = 0; j < pl1.getBarracks().size() && goodPos; j++) {
                int barrackX = pl1.getBarracks().get(j).getXc();
                int barrackY = pl1.getBarracks().get(j).getYc();
                if (Math.abs(barrackX - x) < barrackDist && Math.abs(barrackY - y) < barrackDist) {
                    goodPos = false;
                }
            }
            if (goodPos && canPlace(x, y)) {
                pl1.addBarrack(new Barrack(x, y));
                pl2.addBarrack(new Barrack(width - x - 1, height - y - 1));
            } else {
                i--;
            }
        }

        int obsticleCount = 10;
        int obsticleX;
        int obsticleY;
        do {
            obsticleY = rand.nextInt(height);
            obsticleX = rand.nextInt(width / 2);
        } while (!canPlace(obsticleX, obsticleY));
        obsticles.add(new Obsticle(obsticleX, obsticleY));
        obsticles.add(new Obsticle(width - obsticleX - 1, height - obsticleY - 1));

        //itt az indexelésekkel lesznek még bajok
        for (int i = 0; i < obsticleCount - 1; i++) {
            int x = rand.nextInt(2) - 1;
            int y = rand.nextInt(2) - 1;
            if (!(x == 0 && y == 0) && obsticleX + x > 0 && obsticleX + y < (width / 2) && obsticleY + y > 0 && obsticleX + x < height && canPlace(obsticleX, obsticleY)) {
                obsticleX += x;
                obsticleY += y;
                obsticles.add(new Obsticle(obsticleX, obsticleY));
                obsticles.add(new Obsticle(width - obsticleX - 1, height - obsticleY - 1));
            } else {
                i--;
            }
        }
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

    class DataPoint {

        public int x;
        public int y;
        public int value;
        public Position way;

        public DataPoint(int xp, int yp) {
            this.x = xp;
            this.y = yp;
            this.value = 1000;
            this.way = new Position(-1, -1);
        }

    }

    public DataPoint[][] getTableFilled(int xx, int yy) {
        DataPoint[][] table = new DataPoint[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                table[i][j] = new DataPoint(i, j);
            }
        }
        table[pl1.getXc()][pl1.getYc()].value = 2000;
        table[pl2.getXc()][pl2.getYc()].value = 2000;
        for (int i = 0; i < obsticles.size(); i++) {
            table[obsticles.get(i).getXc()][obsticles.get(i).getYc()].value = 2000;
        }
        for (int i = 0; i < pl1.getBarracks().size(); i++) {
            table[pl1.getBarracks().get(i).getXc()][pl1.getBarracks().get(i).getYc()].value = 2000;
        }
        for (int i = 0; i < pl2.getBarracks().size(); i++) {
            table[pl2.getBarracks().get(i).getXc()][pl2.getBarracks().get(i).getYc()].value = 2000;
        }
        for (int i = 0; i < pl1.getTowers().size(); i++) {
            table[pl1.getTowers().get(i).getXc()][pl1.getTowers().get(i).getYc()].value = 2000;
        }
        for (int i = 0; i < pl2.getTowers().size(); i++) {
            table[pl2.getTowers().get(i).getXc()][pl2.getTowers().get(i).getYc()].value = 2000;
        }

        table[xx][yy].value = 2000;
        return table;
    }

    public DataPoint[][] getTableDijkstraFromPl1(int xx, int yy) {
        DataPoint[][] table = getTableFilled(xx, yy);
        table[pl1.getXc()][pl1.getYc()].value = 0;

        ArrayList<Position> pos = new ArrayList();
        pos.add(new Position(table[pl1.getXc()][pl1.getYc()].x, table[pl1.getXc()][pl1.getYc()].y));
        while (!pos.isEmpty()) {
            Position p = pos.remove(0);
            if (p.getX() != 0 && table[p.getX() - 1][p.getY()].value <= 1000 && table[p.getX() - 1][p.getY()].value > table[p.getX()][p.getY()].value + 1) {
                table[p.getX() - 1][p.getY()].value = table[p.getX()][p.getY()].value + 1;
                table[p.getX() - 1][p.getY()].way = p;
                pos.add(new Position(table[p.getX() - 1][p.getY()].x, table[p.getX() - 1][p.getY()].y));
            }
            if (p.getX() != width - 1 && table[p.getX() + 1][p.getY()].value <= 1000 && table[p.getX() + 1][p.getY()].value > table[p.getX()][p.getY()].value + 1) {
                table[p.getX() + 1][p.getY()].value = table[p.getX()][p.getY()].value + 1;
                table[p.getX() + 1][p.getY()].way = p;
                pos.add(new Position(table[p.getX() + 1][p.getY()].x, table[p.getX() + 1][p.getY()].y));
            }
            if (p.getY() != 0 && table[p.getX()][p.getY() - 1].value <= 1000 && table[p.getX()][p.getY() - 1].value > table[p.getX()][p.getY()].value + 1) {
                table[p.getX()][p.getY() - 1].value = table[p.getX()][p.getY()].value + 1;
                table[p.getX()][p.getY() - 1].way = p;
                pos.add(new Position(table[p.getX()][p.getY() - 1].x, table[p.getX()][p.getY() - 1].y));
            }
            if (p.getY() != height - 1 && table[p.getX()][p.getY() + 1].value <= 1000 && table[p.getX()][p.getY() + 1].value > table[p.getX()][p.getY()].value + 1) {
                table[p.getX()][p.getY() + 1].value = table[p.getX()][p.getY()].value + 1;
                table[p.getX()][p.getY() + 1].way = p;
                pos.add(new Position(table[p.getX()][p.getY() + 1].x, table[p.getX()][p.getY() + 1].y));
            }
        }
        return table;
    }

    public DataPoint[][] getTableDijkstraFromPl2(int xx, int yy) {
        DataPoint[][] table = getTableFilled(xx, yy);
        table[pl2.getXc()][pl2.getYc()].value = 0;

        ArrayList<Position> pos = new ArrayList();
        pos.add(new Position(table[pl2.getXc()][pl2.getYc()].x, table[pl2.getXc()][pl2.getYc()].y));
        while (!pos.isEmpty()) {
            Position p = pos.remove(0);
            if (p.getX() != 0 && table[p.getX() - 1][p.getY()].value <= 1000 && table[p.getX() - 1][p.getY()].value > table[p.getX()][p.getY()].value + 1) {
                table[p.getX() - 1][p.getY()].value = table[p.getX()][p.getY()].value + 1;
                table[p.getX() - 1][p.getY()].way = p;
                pos.add(new Position(table[p.getX() - 1][p.getY()].x, table[p.getX() - 1][p.getY()].y));
            }
            if (p.getX() != width - 1 && table[p.getX() + 1][p.getY()].value <= 1000 && table[p.getX() + 1][p.getY()].value > table[p.getX()][p.getY()].value + 1) {
                table[p.getX() + 1][p.getY()].value = table[p.getX()][p.getY()].value + 1;
                table[p.getX() + 1][p.getY()].way = p;
                pos.add(new Position(table[p.getX() + 1][p.getY()].x, table[p.getX() + 1][p.getY()].y));
            }
            if (p.getY() != 0 && table[p.getX()][p.getY() - 1].value <= 1000 && table[p.getX()][p.getY() - 1].value > table[p.getX()][p.getY()].value + 1) {
                table[p.getX()][p.getY() - 1].value = table[p.getX()][p.getY()].value + 1;
                table[p.getX()][p.getY() - 1].way = p;
                pos.add(new Position(table[p.getX()][p.getY() - 1].x, table[p.getX()][p.getY() - 1].y));
            }
            if (p.getY() != height - 1 && table[p.getX()][p.getY() + 1].value <= 1000 && table[p.getX()][p.getY() + 1].value > table[p.getX()][p.getY()].value + 1) {
                table[p.getX()][p.getY() + 1].value = table[p.getX()][p.getY()].value + 1;
                table[p.getX()][p.getY() + 1].way = p;
                pos.add(new Position(table[p.getX()][p.getY() + 1].x, table[p.getX()][p.getY() + 1].y));
            }
        }
        return table;
    }

    /*
    * A függvény megadja, hogy lehet-e adott koordinátára tornyot vagy akadályt helyezni
     */
    public boolean canPlace(int xx, int yy) {
        DataPoint[][] table = getTableDijkstraFromPl1(xx, yy);
        int badCells = 0;
        if (pl2.getXc() != 0 && table[pl2.getXc() - 1][pl2.getYc()].value >= 1000) {
            badCells += 1;
        }
        if (pl2.getXc() != width - 1 && table[pl2.getXc() + 1][pl2.getYc()].value >= 1000) {
            badCells += 1;
        }
        if (pl2.getYc() != 0 && table[pl2.getXc()][pl2.getYc() - 1].value >= 1000) {
            badCells += 1;
        }
        if (pl2.getYc() != height - 1 && table[pl2.getXc()][pl2.getYc() + 1].value >= 1000) {
            badCells += 1;
        }
        if (badCells >= 4) {
            return false;
        }

        for (int i = 0; i < pl2.getBarracks().size(); i++) {
            badCells = 0;
            if (pl2.getBarracks().get(i).getXc() != 0 && table[pl2.getBarracks().get(i).getXc() - 1][pl2.getBarracks().get(i).getYc()].value >= 1000) {
                badCells += 1;
            }
            if (pl2.getBarracks().get(i).getXc() != width - 1 && table[pl2.getBarracks().get(i).getXc() + 1][pl2.getBarracks().get(i).getYc()].value >= 1000) {
                badCells += 1;
            }
            if (pl2.getBarracks().get(i).getYc() != 0 && table[pl2.getBarracks().get(i).getXc()][pl2.getBarracks().get(i).getYc() - 1].value >= 1000) {
                badCells += 1;
            }
            if (pl2.getBarracks().get(i).getYc() != height - 1 && table[pl2.getBarracks().get(i).getXc()][pl2.getBarracks().get(i).getYc() + 1].value >= 1000) {
                badCells += 1;
            }
            if (badCells >= 4) {
                return false;
            }
        }

        table = getTableDijkstraFromPl2(xx, yy);
        badCells = 0;
        if (pl1.getXc() != 0 && table[pl1.getXc() - 1][pl1.getYc()].value >= 1000) {
            badCells += 1;
        }
        if (pl1.getXc() != width - 1 && table[pl1.getXc() + 1][pl1.getYc()].value >= 1000) {
            badCells += 1;
        }
        if (pl1.getYc() != 0 && table[pl1.getXc()][pl1.getYc() - 1].value >= 1000) {
            badCells += 1;
        }
        if (pl1.getYc() != height - 1 && table[pl1.getXc()][pl1.getYc() + 1].value >= 1000) {
            badCells += 1;
        }
        if (badCells >= 4) {
            return false;
        }

        for (int i = 0; i < pl1.getBarracks().size(); i++) {
            badCells = 0;
            if (pl1.getBarracks().get(i).getXc() != 0 && table[pl1.getBarracks().get(i).getXc() - 1][pl1.getBarracks().get(i).getYc()].value >= 1000) {
                badCells += 1;
            }
            if (pl1.getBarracks().get(i).getXc() != width - 1 && table[pl1.getBarracks().get(i).getXc() + 1][pl1.getBarracks().get(i).getYc()].value >= 1000) {
                badCells += 1;
            }
            if (pl1.getBarracks().get(i).getYc() != 0 && table[pl1.getBarracks().get(i).getXc()][pl1.getBarracks().get(i).getYc() - 1].value >= 1000) {
                badCells += 1;
            }
            if (pl1.getBarracks().get(i).getYc() != height - 1 && table[pl1.getBarracks().get(i).getXc()][pl1.getBarracks().get(i).getYc() + 1].value >= 1000) {
                badCells += 1;
            }
            if (badCells >= 4) {
                return false;
            }
        }
        return true;
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
