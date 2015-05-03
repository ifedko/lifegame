package lifegame;

/**
 * @author ifedko
 */
public class LifeController {

    LifeController() {
    }

    public LifeArea fillArea(LifeArea area) {
        area.randomFillArea();
        return area;
    }

    public LifeArea updateArea(LifeArea currentArea) {
        LifeArea nextArea = new LifeArea(currentArea.getAreaRows(), currentArea.getAreaColumns());
        int iMax = currentArea.getAreaRows();
        int jMax = currentArea.getAreaColumns();

        LifeCell[][] currentCells = currentArea.getCells();
        for (int i = 0; i < iMax; i++) {
            for(int j = 0; j < jMax; j++) {
                LifeCell cell = currentCells[i][j];
                int countAliveNeighbors = currentArea.getCountNeighbors(cell);

                if (cell.isDead() && countAliveNeighbors == 3) {
                    cell.born();
                }
                if (cell.isAlive() && (countAliveNeighbors < 2 || countAliveNeighbors > 3)) {
                    cell.die();
                }

                nextArea.addCell(cell);
            }
        }

        return nextArea;
    }
}
