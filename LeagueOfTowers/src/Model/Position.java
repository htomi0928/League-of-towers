package Model;

/*
* Point osztály a táblán való ábrázoláshoz
*/
public class Position {
    public int x;
    public int y;
            
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    /*
    * Egy pozició módosítása
    */
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }
}
