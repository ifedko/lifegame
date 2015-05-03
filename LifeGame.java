/*
 * http://en.wikipedia.org/wiki/Conway's_Game_of_Life
 */
package lifegame;

import javax.swing.*;

/**
 * @author ifedko
 */
public class LifeGame {

    private LifeArea area;

    private LifeController controller;

    private LifeGUI gui;

    LifeGame() {
        area = new LifeArea(15, 15);
        controller = new LifeController();
        gui = new LifeGUI(area, controller);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LifeGame();
            }
        });
    }
    
}
