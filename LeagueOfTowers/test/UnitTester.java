import LoTExceptions.InvalidInputException;
import Model.Zombie;
import Model.BigZombie;
import Model.AmphibianZombie;
import Model.KamikazeZombie;
import Model.FighterZombie;
import java.security.InvalidAlgorithmParameterException;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UnitTester {
    
    @Test
    public void testZombiesHealth() throws InvalidInputException{
        Zombie zombie = new Zombie(0, 0);
        assertEquals("Zombie has 50 health, when we summon", 50, zombie.getHp());
        zombie.loseHp(10);
        assertEquals("Zombie has 40 health, after he got 10 damages", 40, zombie.getHp());
        zombie.loseHp(100);
        assertEquals("Zombie has 0 health, after he got 100 damages", 0, zombie.getHp());
    }
    
    @Test (expected = InvalidInputException.class)
    public void testZombiesHealthPositiveDamage() throws InvalidInputException{
        Zombie zombie = new Zombie(0, 0);
        zombie.loseHp(-10);
    }
    
}


