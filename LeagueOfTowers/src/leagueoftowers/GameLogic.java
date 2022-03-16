/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leagueoftowers;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Pacza Dániel
 */

public class GameLogic {
    
    private Castle pl1;
    private Castle pl2;
    private int turn;
    
    public GameLogic() throws IOException {
        pl1 = new Castle(4-1, 10-1);
        pl2 = new Castle(27-1, 10-1);
        
        turn = 1;
        
    }
    
    public int getTurn() {
        return turn;
    }
    
    public void nextTurn() {
        if (turn == 5) {
            turn = 1;
        }
        else {
            turn += 1;
        }
    }
    
    public Castle get1pCastle() {
        return pl1;
    }
    
    public Castle get2pCastle() {
        return pl2;
    }
    
}
