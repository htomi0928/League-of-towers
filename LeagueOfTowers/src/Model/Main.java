package Model;

import View.*;
import java.io.IOException;

/*
* A játék főosztálya
*/
public class Main {

    private static MainWindow mw;
    public static GameLogic gl;
    
    public static void main(String[] args) {
        
        gl = new GameLogic();
        mw = new MainWindow();

    }
    
}
