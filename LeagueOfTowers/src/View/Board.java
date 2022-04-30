package View;

import Model.AttackUnits;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.logging.Level;
import java.util.logging.Logger;
import static View.MainWindow.gl;
import javax.swing.JOptionPane;


/*
* A játék boardjának osztálya
* A játéktér
 */
public class Board extends JPanel {

    public static final int tile_size = 36;
    public static final int width = 29 + 1;
    public static final int height = 18 + 1;

    private int circleX;
    private int circleY;
    private int circleR;
    private boolean drawCircle = false;

    int x = -1;
    int y = -1;

    public Board(OptionPanel opanel) {

        setBackground(new Color(32, 217, 19));
        Dimension dim = new Dimension(width * tile_size, height * tile_size);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setSize(dim);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //System.out.println(e.getX() / tile_size + "," + e.getY() / tile_size);
                //System.out.println(getSize().width + "," + getSize().height);

                x = e.getX() / tile_size;
                y = e.getY() / tile_size;

                /*
                    * Annak vizsgálata, hogy milyen mezőn állunk a táblán
                    *   Kastély
                    *   Torony
                    *   Egység
                    *   Üres
                    * Ennek függvényében a change függvényt meghívva változtatható az oldalsó panel
                 */
                try {
                    opanel.change(gl.returnSprites(x, y), x, y);

                } catch (Exception ex) {
                    Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                }

                repaint();
            }
        });
    }

    /*
    * A játék boardjának lefestése
    * Kastélyok, akadályok, tornyok, egységek, barakkok megjelenítése
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gr = (Graphics2D) g;
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
                    /*
                    * A játéktábla kockássá tétele
                     */
                    gr.setColor(new Color(0, 200, 0));
                    gr.fillRect(i * tile_size, j * tile_size, tile_size, tile_size);
                }
            }
        }


        /*
        * Kastélyok megjelenítése
         */
        gr.drawImage(gl.get1pCastle().getImg(), gl.get1pCastle().getXc() * tile_size, gl.get1pCastle().getYc() * tile_size, tile_size, tile_size, this);
        gr.drawImage(gl.get2pCastle().getImg(), tile_size + gl.get2pCastle().getXc() * tile_size, gl.get2pCastle().getYc() * tile_size, -tile_size, tile_size, this);
        drawHealthbar(gl.get1pCastle().getXc(), gl.get1pCastle().getYc(), gl.get1pCastle().getHp(), gl.get1pCastle().getMaxhp(), gr);
        drawHealthbar(gl.get2pCastle().getXc(), gl.get2pCastle().getYc(), gl.get2pCastle().getHp(), gl.get2pCastle().getMaxhp(), gr);

        /*
        * Akadályok megjelenítése
         */
        for (int i = 0; i < gl.getObsticles().size(); i++) {
            gr.drawImage(gl.getObsticles().get(i).getImg(), gl.getObsticles().get(i).getXc() * tile_size, gl.getObsticles().get(i).getYc() * tile_size, tile_size, tile_size, this);
        }

        /*
        *Barrackok megjelenítése
         */
        for (int i = 0; i < gl.get1pCastle().getBarracks().size(); i++) {
            gr.drawImage(gl.get1pCastle().getBarracks().get(i).getImg(), gl.get1pCastle().getBarracks().get(i).getXc() * tile_size, gl.get1pCastle().getBarracks().get(i).getYc() * tile_size, tile_size, tile_size, this);
        }
        for (int i = 0; i < gl.get2pCastle().getBarracks().size(); i++) {
            gr.drawImage(gl.get2pCastle().getBarracks().get(i).getImg(), tile_size + gl.get2pCastle().getBarracks().get(i).getXc() * tile_size, gl.get2pCastle().getBarracks().get(i).getYc() * tile_size, -tile_size, tile_size, this);
        }
        /*
        * Első játékos tornyainak megjelenítése
         */
        for (int i = 0; i < gl.get1pCastle().getTowers().size(); i++) {
            gr.drawImage(gl.get1pCastle().getTowers().get(i).getImg(), gl.get1pCastle().getTowers().get(i).getXc() * tile_size, gl.get1pCastle().getTowers().get(i).getYc() * tile_size, tile_size, tile_size, this);
            if (gl.get1pCastle().getTowers().get(i).getStatus()) {
                drawHealthbar(gl.get1pCastle().getTowers().get(i).getXc(), gl.get1pCastle().getTowers().get(i).getYc(), gl.get1pCastle().getTowers().get(i).getHp(), gl.get1pCastle().getTowers().get(i).getMaxhp(), gr);
            }
        }

        /*
        * Második játékos tornyainak megjelenítése
         */
        for (int i = 0; i < gl.get2pCastle().getTowers().size(); i++) {
            gr.drawImage(gl.get2pCastle().getTowers().get(i).getImg(), tile_size + gl.get2pCastle().getTowers().get(i).getXc() * tile_size, gl.get2pCastle().getTowers().get(i).getYc() * tile_size, -tile_size, tile_size, this);
            if (gl.get2pCastle().getTowers().get(i).getStatus()) {
                drawHealthbar(gl.get2pCastle().getTowers().get(i).getXc(), gl.get2pCastle().getTowers().get(i).getYc(), gl.get2pCastle().getTowers().get(i).getHp(), gl.get2pCastle().getTowers().get(i).getMaxhp(), gr);
            }
        }

        /*
            * Első játékos támadó egységeinek megjelenítése
         */
        for (int i = 0; i < gl.get1pCastle().getUnits().size(); i++) {
            AttackUnits u = gl.get1pCastle().getUnits().get(i);
            gr.drawImage(u.getImg(), tile_size + u.getXc() * tile_size + u.wayX, u.getYc() * tile_size + u.wayY, -tile_size, tile_size, this);
            drawHealthbar(u.getXc(), u.getYc(), u.getHp(), u.getMaxhp(), gr);
        }

        /*
            * Második játékos támadó egységeinek megjelenítése
         */
        for (int i = 0; i < gl.get2pCastle().getUnits().size(); i++) {
            AttackUnits u = gl.get2pCastle().getUnits().get(i);
            gr.drawImage(u.getImg(), u.getXc() * tile_size + u.wayX, u.getYc() * tile_size + u.wayY, tile_size, tile_size, this);
            drawHealthbar(u.getXc(), u.getYc(), u.getHp(), u.getMaxhp(), gr);
        }

        /*
        * A játéktábla elválasztó csíkja középen
         */
        gr.setColor(new Color(0, 0, 0));
        for (int i = 0; i < height; i++) {
            gr.fillRect(width / 2 * tile_size - 1, i * tile_size, 3, tile_size);

        }
        /*
        * A mező, ahol a játékos éppen áll
         */
        gr.setColor(new Color(0, 0, 0));
        gr.drawRect(x * tile_size, y * tile_size, tile_size, tile_size);

        /*
        gr.fillRect(n * tile_size - 4 * tile_size, m * tile_size / 2 - tile_size / 2, tile_size, tile_size);
        gr.fillRect(3 * tile_size, m * tile_size / 2 - tile_size / 2, tile_size, tile_size);
         */
        gr.setColor(new Color(255, 0, 0));
        if (drawCircle) {
            gr.drawOval((circleX - circleR) * tile_size, (circleY - circleR) * tile_size, circleR * tile_size * 2 + tile_size, circleR * tile_size * 2 + tile_size);
        }
    }

    public void drawHealthbar(int x, int y, int currentHp, int maxHp, Graphics2D gr) {
        int hpbarWidth = 30;
        int hpbarHeight = 5;
        int barposX = x * tile_size + (tile_size - hpbarWidth) / 2;
        int barposY = y * tile_size + tile_size - hpbarHeight - 3;

        gr.setColor(Color.green);
        gr.fillRect(barposX, barposY, hpbarWidth * currentHp / maxHp, hpbarHeight);

        gr.setColor(Color.red);
        gr.fillRect(barposX + hpbarWidth * currentHp / maxHp, barposY, hpbarWidth - (hpbarWidth * currentHp / maxHp), hpbarHeight);

        gr.setColor(Color.black);
        gr.drawRect(barposX, barposY, hpbarWidth, hpbarHeight);

    }

    public void setCircleX(int circleX) {
        this.circleX = circleX;
    }

    public void setCircleY(int circleY) {
        this.circleY = circleY;
    }

    public void setCircleR(int circleR) {
        this.circleR = circleR;
    }

    public void setDrawCircle(boolean drawCircle) {
        this.drawCircle = drawCircle;
    }
    
   
}
