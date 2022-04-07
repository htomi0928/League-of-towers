import org.junit.Test;
import static org.junit.Assert.assertEquals;

import Model.Tower1;
import Model.Tower2;
import Model.Tower3;
import LoTExceptions.IllegalLevelOfTowerException;

public class TowerTester {
    
    /*
    * Tower1
    */
    @Test
    public void testTower1UpgradeTo3() throws IllegalLevelOfTowerException {
        Tower1 tower1 = new Tower1(0, 0);
        assertEquals("Tower1 has 10 damage, when we summon", 10, tower1.getDamage());
        assertEquals("Tower1 has 1 block range, when we summon", 1, tower1.getDistance());
        tower1.upgrade();
        assertEquals("Tower1 has 15 damage, when we upgrade once", 15, tower1.getDamage());
        tower1.upgrade();
        assertEquals("Tower1 has 20 damage, when we summon", 20, tower1.getDamage());
        assertEquals("Tower1 has 2 block range, when we summon", 2, tower1.getDistance());
    }
    
    @Test(expected = IllegalLevelOfTowerException.class)
    public void testTower1Upgradeto4() throws IllegalLevelOfTowerException {
        Tower1 tower1 = new Tower1(0, 0);
        tower1.upgrade();
        tower1.upgrade();
        tower1.upgrade();
    }
    
    
    /*
    * Tower2
    */
    @Test
    public void testTower2UpgradeTo3() throws IllegalLevelOfTowerException {
        Tower2 tower2 = new Tower2(0, 0);
        assertEquals("Tower2 has 10 damage, when we summon", 10, tower2.getDamage());
        assertEquals("Tower2 has 2 block range, when we summon", 2, tower2.getDistance());
        tower2.upgrade();
        assertEquals("Tower2 has 15 damage, when we upgrade once", 15, tower2.getDamage());
        tower2.upgrade();
        assertEquals("Tower2 has 20 damage, when we summon", 20, tower2.getDamage());
        assertEquals("Tower2 has 3 block range, when we summon", 3, tower2.getDistance());
    }
    
    @Test(expected = IllegalLevelOfTowerException.class)
    public void testTower2Upgradeto4() throws IllegalLevelOfTowerException {
        Tower2 tower2 = new Tower2(0, 0);
        tower2.upgrade();
        tower2.upgrade();
        tower2.upgrade();
    }
    
    
    /*
    * Tower3
    */
    @Test
    public void testTower3UpgradeTo3() throws IllegalLevelOfTowerException {
        Tower3 tower3 = new Tower3(0, 0);
        assertEquals("Tower3 has 15 damage, when we summon", 15, tower3.getDamage());
        assertEquals("Tower3 has 1 block range, when we summon", 1, tower3.getDistance());
        tower3.upgrade();
        assertEquals("Tower3 has 20 damage, when we upgrade once", 20, tower3.getDamage());
        tower3.upgrade();
        assertEquals("Tower3 has 25 damage, when we summon", 25, tower3.getDamage());
        assertEquals("Tower3 has 2 block range, when we summon", 2, tower3.getDistance());
    }
    
    @Test(expected = IllegalLevelOfTowerException.class)
    public void testTower3Upgradeto4() throws IllegalLevelOfTowerException {
        Tower3 tower3 = new Tower3(0, 0);
        tower3.upgrade();
        tower3.upgrade();
        tower3.upgrade();
    }
    
    
}

