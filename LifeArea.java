package lifegame;

import java.util.Random;

/**
 * @author ifedko
 */
public class LifeArea {

    private int numberRows;

    private int numberColumns;

    private LifeCell[][] cells;

    LifeArea(int numberRows, int numberColumns) {
        this.numberRows = numberRows;
        this.numberColumns = numberColumns;
        this.cells = new LifeCell[numberRows][numberColumns];
    }

    public void randomFillArea() throws Exception {
        int iMax = this.getAreaRows();
        int jMax = this.getAreaColumns();
        Random random = new Random();

        for (int i = 0; i < iMax; i++) {
            for(int j = 0; j < jMax; j++) {
                boolean isAlive = random.nextBoolean();
                LifeCell cell = new LifeCell(i, j, isAlive);
                this.addCell(cell);
            }
        }
    }

    public void addCell(LifeCell cell) throws Exception {
        if (!isExistPositionForCell(cell)) {
            throw new Exception("Cell cannot be added, because cell position is outside the lifearea.");
        }

        int cellRowPosition = cell.getRowPosition();
        int cellColumnPosition = cell.getColumnPosition();
        this.cells[cellRowPosition][cellColumnPosition] = cell;
    }

    public int getAreaColumns() {
        return numberColumns;
    }

    public int getAreaRows() {
        return numberRows;
    }

    public LifeCell[][] getCells() {
        return cells;
    }

    public int getCountNeighbors(LifeCell cell) throws Exception {
        if (!isExistPositionForCell(cell)) {
            throw new Exception("Cell does not exist for counting neighbors.");
        }

        LifeCell[][] cells = getCells();
        int countAliveNeighbors = 0;
        int currentRow = cell.getRowPosition();
        int currentColumn = cell.getColumnPosition();

        int neighborRowStart = (currentRow == 0) ? 0 : (currentRow - 1);
        int neighborRowMax = (currentRow == (this.numberRows - 1)) ? currentRow : (currentRow + 1);
        int neighborColumnStart = (currentColumn == 0) ? 0 : (currentColumn - 1);
        int neighborColumnMax = (currentColumn == (this.numberColumns - 1)) ? currentColumn : (currentColumn + 1);

        if (!isExistPosition(neighborColumnStart, neighborRowStart)
                || !isExistPosition(neighborColumnMax, neighborRowMax)) {
            throw new Exception("Extreme positions of neighbor cells are outside the lifearea.");
        }

        for (int neighborRow = neighborRowStart; neighborRow <= neighborRowMax; neighborRow++) {
            for (int neighborColumn = neighborColumnStart; neighborColumn <= neighborColumnMax; neighborColumn++) {
                if (neighborRow == currentRow && neighborColumn == currentColumn) {
                    continue;
                }

                LifeCell neighborCell = cells[neighborRow][neighborColumn];
                if (neighborCell.isAlive()) {
                    countAliveNeighbors++;
                }
            }
        }

        return countAliveNeighbors;
    }

    private boolean isExistPositionForCell(LifeCell cell) {
        int cellColumn = cell.getColumnPosition();
        int cellRow = cell.getRowPosition();

        return isExistPosition(cellColumn, cellRow);
    }

    private boolean isExistPosition(int columnPosition, int rowPosition) {
        return (columnPosition < numberRows && rowPosition < numberColumns);
    }

}
