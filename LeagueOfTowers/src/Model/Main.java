/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Model;

import View.*;
import java.io.IOException;
/**
 *
 * @author abelsz
 */
public class Main {

    private static MainWindow mw;
    public static GameLogic gl;
    
    public static void main(String[] args) throws IOException {
        
        gl = new GameLogic();
        mw = new MainWindow();

    }
    
}
