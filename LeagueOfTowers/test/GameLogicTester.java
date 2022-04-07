
import LoTExceptions.InvalidInputException;
import Model.Barrack;
import Model.Castle;
import Model.Tower;
import Model.Zombie;
import Model.BigZombie;
import Model.AmphibianZombie;
import Model.GameLogic;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import Model.GameLogic.DataPoint;
import Model.Tower1;
import java.io.IOException;

public class GameLogicTester {

    @Test
    public void getTableFilledTest() throws IOException {
        GameLogic gl = new GameLogic();
        gl.get1pCastle().addTower(new Tower1(0, 0));
        DataPoint[][] pd = gl.getTableFilled(-1, -1);
        assertEquals("The castle's  special is 1", 1, pd[gl.get1pCastle().getXc()][gl.get1pCastle().getYc()].special);
        assertEquals("The castle's  special is 1", 1, pd[gl.get2pCastle().getXc()][gl.get2pCastle().getYc()].special);
        for (int i = 0; i < gl.get1pCastle().getBarracks().size(); i++) {
            assertEquals("The barracks's  special is 1", 1, pd[gl.get1pCastle().getBarracks().get(i).getXc()][gl.get1pCastle().getBarracks().get(i).getYc()].special);
        }
        for (int i = 0; i < gl.get2pCastle().getBarracks().size(); i++) {
            assertEquals("The barracks's  special is 1", 1, pd[gl.get2pCastle().getBarracks().get(i).getXc()][gl.get2pCastle().getBarracks().get(i).getYc()].special);
        }
        for (int i = 0; i < gl.getObsticles().size(); i++) {
            assertEquals("The obstickles's  special is 3", 3, pd[gl.getObsticles().get(i).getXc()][gl.getObsticles().get(i).getYc()].special);
        }
        assertEquals("Towers's special is 2", 2, pd[0][0].special);
        
    }
    
    @Test
    public void DijkstraTest() throws IOException {
        GameLogic gl = new GameLogic();
        gl.clearObjectsToTest();
        gl.get1pCastle().addTower(new Tower1(gl.get1pCastle().getXc()-1, gl.get1pCastle().getYc()));
        gl.get1pCastle().addTower(new Tower1(gl.get1pCastle().getXc(), gl.get1pCastle().getYc()-1));
        gl.get1pCastle().addTower(new Tower1(gl.get1pCastle().getXc(), gl.get1pCastle().getYc()+1));
        gl.get1pCastle().addTower(new Tower1(gl.get1pCastle().getXc()+2, gl.get1pCastle().getYc()));
        assertEquals("Place to the castle (x, y)", false, gl.canPlace(gl.get1pCastle().getXc(), gl.get1pCastle().getYc()));
        assertEquals("Place next to castle (x+1, y)", false, gl.canPlace(gl.get1pCastle().getXc(), gl.get1pCastle().getYc()+1));
        assertEquals("Place next to castle (x+1, y-1)", true, gl.canPlace(gl.get1pCastle().getXc()+1, gl.get1pCastle().getYc()-1));
        gl.get1pCastle().addTower(new Tower1(gl.get1pCastle().getXc()+1, gl.get1pCastle().getYc()-1));
        assertEquals("Place next to castle (x+1, y+1)", false, gl.canPlace(gl.get1pCastle().getXc()+1, gl.get1pCastle().getYc()+1));
        assertEquals("Place next to castle (x+1, y-2)", true, gl.canPlace(gl.get1pCastle().getXc()+1, gl.get1pCastle().getYc()-2));
    }
    
    public void ShortestWayTest() throws IOException {
        GameLogic gl = new GameLogic();
        gl.clearObjectsToTest();
        assertEquals("Castle is in (3, 9), unit is in (10, 9)", 7, gl.wayToCastleP1(10, 9));
        assertEquals("Castle is in (3, 9), unit is in (23, 13)", 24, gl.wayToCastleP1(23, 13));
        gl.get1pCastle().addTower(new Tower(4, 9));
        assertEquals("Castle is in (3, 9), unit is in (5, 9)", 4, gl.wayToCastleP1(5, 9));
        gl.get1pCastle().addTower(new Tower(3, 10));
        assertEquals("Castle is in (3, 9), unit is in (4, 10)", 6, gl.wayToCastleP1(4, 10));
        gl.get1pCastle().addTower(new Tower(3, 8));
        assertEquals("Castle is in (3, 9), unit is in (5, 9)", 8, gl.wayToCastleP1(5, 9));
    }
    
    
}

