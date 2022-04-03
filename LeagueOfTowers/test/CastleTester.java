import LoTExceptions.InvalidInputException;
import Model.Barrack;
import Model.Castle;
import Model.Tower;
import Model.Zombie;
import Model.BigZombie;
import Model.AmphibianZombie;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CastleTester {
    
    @Test
    public void HpTest() throws InvalidInputException {
        Castle castle = new Castle(0,0);
        assertEquals("Castle hp is 1000 after we summon", 1000, castle.getHp());
        castle.loseHp(200);
        assertEquals("Castle hp is 800 after it loses 200hp", 800, castle.getHp());
        castle.loseHp(1200);
        assertEquals("Castle hp is 0 after it loses 1200hp", 0, castle.getHp());
    }
    
    @Test(expected = InvalidInputException.class)
    public void HpTestWithNegativeValue() throws InvalidInputException {
        Castle castle = new Castle(0,0);
        castle.loseHp(-200);
    }
    
    @Test
    public void addObjectsTest() {
        Castle castle = new Castle(0,0);
        assertEquals("Castle has 0 tower when we summon", 0, castle.getTowers().size());
        assertEquals("Castle has 0 unit when we summon", 0, castle.getUnits().size());
        assertEquals("Castle has 0 barrack when we summon", 0, castle.getBarracks().size());
        
        castle.addTower(new Tower(1, 0));
        castle.addUnit(new Zombie(0, 1));
        castle.addBarrack(new Barrack(1, 1));
        
        assertEquals("Castle has 1 tower when we add 1", 1, castle.getTowers().size());
        assertEquals("Castle has 1 unit when we add 1", 1, castle.getUnits().size());
        assertEquals("Castle has 1 barrack when we add 1", 1, castle.getBarracks().size());
        
        castle.sellTower(0);
        assertEquals("Castle has 0 tower when we delete 1", 0, castle.getTowers().size());
        
    }
    
    @Test
    public void returnTowerTest() {
        Castle castle = new Castle(0,0);
        assertEquals("Castle has 0 tower when we summon", 0, castle.getTowers().size());
        Tower t = new Tower(3, 10);
        castle.addTower(t);
        assertEquals("Tower is equal to itself", t, castle.returnTower(3, 10));
        assertEquals("Not existing tower is null", null, castle.returnTower(0, 0));
    }
    
    @Test
    public void returnBarrackTest() {
        Castle castle = new Castle(0,0);
        assertEquals("Castle has 0 barrack when we summon", 0, castle.getBarracks().size());
        Barrack b = new Barrack(1, 4);
        castle.addBarrack(b);
        assertEquals("Barrack is equal to itself", b, castle.returnBarrack(1, 4));
        assertEquals("Not existing barrack is null", null, castle.returnTower(3, 1));
    }
    
    @Test
    public void returnUnitsTest() {
        Castle castle = new Castle(0,0);
        assertEquals("Castle has 0 unit when we summon", 0, castle.getUnits().size());
        
        Zombie z1 = new Zombie(3, 16);
        BigZombie z2 = new BigZombie(2, 3);
        AmphibianZombie z3 = new AmphibianZombie(3, 16);
        
        castle.addUnit(z1);
        castle.addUnit(z2);
        castle.addUnit(z3);
        
        assertEquals("Castle has 3 units after we add them", 3, castle.getUnits().size());
        assertEquals("Castle has 1 unit in 2, 3 coords", 1, castle.returnUnits(2, 3).size());
        assertEquals("Castle has 2 units in 3, 16 coords", 2, castle.returnUnits(3, 16).size());
        assertEquals("Castle has 0 units in 1, 1 coords", 0, castle.returnUnits(1, 1).size());
    }
    
    @Test
    public void SpriteCoordTest() {
        Castle castle = new Castle(0,0);
        assertEquals("Castle has 0 tower when we summon", 0, castle.getTowers().size());
        assertEquals("Castle has 0 unit when we summon", 0, castle.getUnits().size());
        assertEquals("Castle has 0 barrack when we summon", 0, castle.getBarracks().size());
        
        Tower t = new Tower(0, 1);
        Zombie z = new Zombie(1, 0);
        Barrack b = new Barrack(1, 1);
        
        castle.addTower(t);
        castle.addUnit(z);
        castle.addBarrack(b);
        
        assertEquals("There is a tower in 0, 1 coords", "tower", castle.SpriteCoord(0, 1));
        assertEquals("There is an attack unit in 1, 0 coords", "units", castle.SpriteCoord(1, 0));
        assertEquals("There is a barrack in 1, 1 coords", "barrack", castle.SpriteCoord(1, 1));
        assertEquals("There is nothing in 2, 0 coords", null, castle.SpriteCoord(2, 0));
        
    }
    
    
}
