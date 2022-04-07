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

        Random rand = new Random();

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
            int x = rand.nextInt(3) - 1;
            int y = rand.nextInt(3) - 1;
            //System.out.println(obsticleX + " : " + x + " ; " + obsticleY + " : " + y);
            if (!(x == 0 && y == 0) && obsticleX + x > 0 && obsticleX + x < (width / 2) && obsticleY + y > 0 && obsticleY + y < height && canPlace(obsticleX + x, obsticleY + y)) {
                obsticleX += x;
                obsticleY += y;
                obsticles.add(new Obsticle(obsticleX, obsticleY));
                obsticles.add(new Obsticle(width - obsticleX - 1, height - obsticleY - 1));
            } else {
                i--;
            }

        }

    }
    
    /*
    * A függvény a tornyok sebzését írja le
    */
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

    public class DataPoint {

        public int x;
        public int y;
        public int special;
        public int value;
        public Position way;

        public DataPoint(int xp, int yp) {
            this.x = xp;
            this.y = yp;
            this.value = 1000;
            this.way = new Position(-1, -1);
            this.special = 0;
        }

    }
    /*
    * Táblázat létrehozása a Dijkstra algoritmushoz
    */
    public DataPoint[][] getTableFilled(int xx, int yy) {
        DataPoint[][] table = new DataPoint[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                table[i][j] = new DataPoint(i, j);
            }
        }
        table[pl1.getXc()][pl1.getYc()].value = 1000;
        table[pl1.getXc()][pl1.getYc()].special = 1;
        table[pl2.getXc()][pl2.getYc()].value = 1000;
        table[pl2.getXc()][pl2.getYc()].special = 1;

        for (int i = 0; i < obsticles.size(); i++) {
            table[obsticles.get(i).getXc()][obsticles.get(i).getYc()].value = 2000;
            table[obsticles.get(i).getXc()][obsticles.get(i).getYc()].special = 3;
        }
        for (int i = 0; i < pl1.getBarracks().size(); i++) {
            table[pl1.getBarracks().get(i).getXc()][pl1.getBarracks().get(i).getYc()].value = 1000;
            table[pl1.getBarracks().get(i).getXc()][pl1.getBarracks().get(i).getYc()].special = 1;
        }
        for (int i = 0; i < pl2.getBarracks().size(); i++) {
            table[pl2.getBarracks().get(i).getXc()][pl2.getBarracks().get(i).getYc()].value = 1000;
            table[pl2.getBarracks().get(i).getXc()][pl2.getBarracks().get(i).getYc()].special = 1;
        }
        for (int i = 0; i < pl1.getTowers().size(); i++) {
            table[pl1.getTowers().get(i).getXc()][pl1.getTowers().get(i).getYc()].value = 2000;
            table[pl1.getTowers().get(i).getXc()][pl1.getTowers().get(i).getYc()].special = 2;
        }
        for (int i = 0; i < pl2.getTowers().size(); i++) {
            table[pl2.getTowers().get(i).getXc()][pl2.getTowers().get(i).getYc()].value = 2000;
            table[pl2.getTowers().get(i).getXc()][pl2.getTowers().get(i).getYc()].special = 2;
        }

        if (xx == -1 && yy == -1) {
        } else {
            if (table[xx][yy].value != 1000) {
                return null;
            }
            table[xx][yy].value = 2000;
        }

        return table;
    }

    /*
    * Dijkstra algoritmus a PL1 kasélyból indulva
    * Azt adja vissza, hogy ha letennénk egy objektumot xx, yy helyre, akkor el tudnának-e jutni
    * az egységek a kastélyig
    */
    public DataPoint[][] getTableDijkstraFromPl1(int xx, int yy) {
        if (getTableFilled(xx, yy) == null) {
            return null;
        }

        DataPoint[][] table = getTableFilled(xx, yy);
        table[pl1.getXc()][pl1.getYc()].value = 0;

        ArrayList<Position> pos = new ArrayList();
        pos.add(new Position(table[pl1.getXc()][pl1.getYc()].x, table[pl1.getXc()][pl1.getYc()].y));
        while (!pos.isEmpty()) {
            Position p = pos.remove(0);
            if (p.getX() != 0 && table[p.getX() - 1][p.getY()].value <= 1000 && table[p.getX() - 1][p.getY()].value > table[p.getX()][p.getY()].value + 1) {
                table[p.getX() - 1][p.getY()].value = table[p.getX()][p.getY()].value + 1;
                table[p.getX() - 1][p.getY()].way = p;
                if (table[p.getX() - 1][p.getY()].special == 0) {
                    pos.add(new Position(table[p.getX() - 1][p.getY()].x, table[p.getX() - 1][p.getY()].y));
                }
            }
            if (p.getX() != width - 1 && table[p.getX() + 1][p.getY()].value <= 1000 && table[p.getX() + 1][p.getY()].value > table[p.getX()][p.getY()].value + 1) {
                table[p.getX() + 1][p.getY()].value = table[p.getX()][p.getY()].value + 1;
                table[p.getX() + 1][p.getY()].way = p;
                if (table[p.getX() + 1][p.getY()].special == 0) {
                    pos.add(new Position(table[p.getX() + 1][p.getY()].x, table[p.getX() + 1][p.getY()].y));
                }
            }
            if (p.getY() != 0 && table[p.getX()][p.getY() - 1].value <= 1000 && table[p.getX()][p.getY() - 1].value > table[p.getX()][p.getY()].value + 1) {
                table[p.getX()][p.getY() - 1].value = table[p.getX()][p.getY()].value + 1;
                table[p.getX()][p.getY() - 1].way = p;
                if (table[p.getX()][p.getY() - 1].special == 0) {
                    pos.add(new Position(table[p.getX()][p.getY() - 1].x, table[p.getX()][p.getY() - 1].y));
                }
            }
            if (p.getY() != height - 1 && table[p.getX()][p.getY() + 1].value <= 1000 && table[p.getX()][p.getY() + 1].value > table[p.getX()][p.getY()].value + 1) {
                table[p.getX()][p.getY() + 1].value = table[p.getX()][p.getY()].value + 1;
                table[p.getX()][p.getY() + 1].way = p;
                if (table[p.getX()][p.getY() + 1].special == 0) {
                    pos.add(new Position(table[p.getX()][p.getY() + 1].x, table[p.getX()][p.getY() + 1].y));
                }
            }
        }
        return table;
    }
    
    /*
    * Dijkstra algoritmus a PL2 kasélyból indulva
    * Azt adja vissza, hogy ha letennénk egy objektumot xx, yy helyre, akkor el tudnának-e jutni
    * az egységek a kastélyig
    */
    public DataPoint[][] getTableDijkstraFromPl2(int xx, int yy) {
        if (getTableFilled(xx, yy) == null) {
            return null;
        }

        DataPoint[][] table = getTableFilled(xx, yy);
        table[pl2.getXc()][pl2.getYc()].value = 0;

        ArrayList<Position> pos = new ArrayList();
        pos.add(new Position(table[pl2.getXc()][pl2.getYc()].x, table[pl2.getXc()][pl2.getYc()].y));
        while (!pos.isEmpty()) {
            Position p = pos.remove(0);
            if (p.getX() != 0 && table[p.getX() - 1][p.getY()].value <= 1000 && table[p.getX() - 1][p.getY()].value > table[p.getX()][p.getY()].value + 1) {
                table[p.getX() - 1][p.getY()].value = table[p.getX()][p.getY()].value + 1;
                table[p.getX() - 1][p.getY()].way = p;
                if (table[p.getX() - 1][p.getY()].special == 0) {
                    pos.add(new Position(table[p.getX() - 1][p.getY()].x, table[p.getX() - 1][p.getY()].y));
                }
            }
            if (p.getX() != width - 1 && table[p.getX() + 1][p.getY()].value <= 1000 && table[p.getX() + 1][p.getY()].value > table[p.getX()][p.getY()].value + 1) {
                table[p.getX() + 1][p.getY()].value = table[p.getX()][p.getY()].value + 1;
                table[p.getX() + 1][p.getY()].way = p;
                if (table[p.getX() + 1][p.getY()].special == 0) {
                    pos.add(new Position(table[p.getX() + 1][p.getY()].x, table[p.getX() + 1][p.getY()].y));
                }
            }
            if (p.getY() != 0 && table[p.getX()][p.getY() - 1].value <= 1000 && table[p.getX()][p.getY() - 1].value > table[p.getX()][p.getY()].value + 1) {
                table[p.getX()][p.getY() - 1].value = table[p.getX()][p.getY()].value + 1;
                table[p.getX()][p.getY() - 1].way = p;
                if (table[p.getX()][p.getY() - 1].special == 0) {
                    pos.add(new Position(table[p.getX()][p.getY() - 1].x, table[p.getX()][p.getY() - 1].y));
                }
            }
            if (p.getY() != height - 1 && table[p.getX()][p.getY() + 1].value <= 1000 && table[p.getX()][p.getY() + 1].value > table[p.getX()][p.getY()].value + 1) {
                table[p.getX()][p.getY() + 1].value = table[p.getX()][p.getY()].value + 1;
                table[p.getX()][p.getY() + 1].way = p;
                if (table[p.getX()][p.getY() + 1].special == 0) {
                    pos.add(new Position(table[p.getX()][p.getY() + 1].x, table[p.getX()][p.getY() + 1].y));
                }
            }
        }
        return table;
    }

    /*
    * A függvény megadja, hogy lehet-e adott koordinátára tornyot vagy akadályt helyezni
     */
    public boolean canPlace(int xx, int yy) {
        if (getTableFilled(xx, yy) == null) {
            return false;
        }
        DataPoint[][] table = getTableDijkstraFromPl1(xx, yy);
        if (table[pl2.getXc()][pl2.getYc()].value >= 1000) {
            return false;
        }
        for (int i = 0; i < pl2.getBarracks().size(); i++) {
            if (table[pl2.getBarracks().get(i).getXc()][pl2.getBarracks().get(i).getYc()].value >= 1000) {
                return false;
            }
        }

        table = getTableDijkstraFromPl2(xx, yy);
        if (table[pl1.getXc()][pl1.getYc()].value >= 1000) {
            return false;
        }
        for (int i = 0; i < pl1.getBarracks().size(); i++) {
            if (table[pl1.getBarracks().get(i).getXc()][pl1.getBarracks().get(i).getYc()].value >= 1000) {
                return false;
            }
        }
        return true;
    }
    /*
    * A függvény megadja a legrövidebb utat xx, yy koordinátáktól a Pl1 kastélyig
    */
    public ArrayList<Position> wayToCastleP1(int xx, int yy) {
        ArrayList<Position> pos = new ArrayList();
        DataPoint[][] table = getTableDijkstraFromPl1(-1, -1);
        DataPoint unitData = table[xx][yy];
        while (unitData.value != 0) {
            unitData = table[unitData.way.getX()][unitData.way.getY()];
            pos.add(new Position(unitData.x, unitData.y));
        }

        return pos;
    }
    public Position zombieMovePositionP1(int x, int y) {
        ArrayList<Position> pos = wayToCastleP1(x, y);
        if(pos.size() > 1) {
            return pos.get(1);
        } else {
            return pos.get(0);
        }
    }
    
    /*
    * A függvény megadja a legrövidebb utat xx, yy koordinátáktól a Pl2 kastélyig
    */
    public ArrayList<Position> wayToCastleP2(int xx, int yy) {
        ArrayList<Position> pos = new ArrayList();
        DataPoint[][] table = getTableDijkstraFromPl2(-1, -1);
        DataPoint unitData = table[xx][yy];
        while (unitData.value != 0) {
            unitData = table[unitData.way.getX()][unitData.way.getY()];
            pos.add(new Position(unitData.x, unitData.y));
        }

        return pos;
    }
    
    public Position zombieMovePositionP2(int x, int y) {
        ArrayList<Position> pos = wayToCastleP2(x, y);
        if(pos.size() > 1) {
            return pos.get(1);
        } else {
            return pos.get(0);
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
            System.out.println(pl1.SpriteCoord(x, y));
            return ("1" + pl1.SpriteCoord(x, y));
        } else {
            if (pl1.getXc() == x && pl1.getYc() == y) {
                return "1castle";
            }
        }
        if (pl2.SpriteCoord(x, y) != null) {
            System.out.println(pl2.SpriteCoord(x, y));
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
    
    public void clearObjectsToTest() {
        pl1.clearBarracks();
        pl2.clearBarracks();
        this.obsticles = new ArrayList();
    }
}
