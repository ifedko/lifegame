package lifegame;

import javax.swing.*;
import java.awt.*;

/**
 * @author ifedko
 */
public class LifeAreaView extends JPanel {

    private LifeArea lifeArea;

    public void setArea(LifeArea area) {
        this.lifeArea = area;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        LifeCell[][] cells = this.lifeArea.getCells();

        int iMax = this.lifeArea.getAreaRows();
        int jMax = this.lifeArea.getAreaColumns();
        int width = getWidth();
        int height = getHeight();
        int cellWidth = (int) (width / jMax);
        int cellHeight = (int) (height / iMax);

        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);

        for (int i = 0; i < iMax; i++) {
            for(int j = 0; j < jMax; j++) {
                LifeCell cell = cells[i][j];
                int x = cellWidth * j;
                int y = cellHeight * i;
                if (cell.isAlive()) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x, y, cellWidth, cellHeight);
                } else {
                    g.setColor(Color.BLACK);
                    g.fillRect(x, y, cellWidth, cellHeight);
                }
                g.setColor(Color.GRAY);
                g.drawRect(x, y, cellWidth, cellHeight);
            }
        }
    }
    
}
