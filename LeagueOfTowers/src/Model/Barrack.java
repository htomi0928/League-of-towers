/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.IOException;

/**
 *
 * @author Pacza Dániel
 */
public class Barrack extends Sprite {
    String type;
    public Barrack(int x, int y) throws IOException {
        super(x, y);
        type = "barrack";
    }
}
