package lifegame;

/**
 * @author ifedko
 */
public class LifeCell {

    private int columnPosition;

    private int rowPosition;

    private boolean isAlive;

    LifeCell(int rowPosition, int columnPosition, boolean isAlive) {
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
        this.isAlive = isAlive;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public int getColumnPosition() {
        return columnPosition;
    }

    public boolean isAlive() {
        return (isAlive == true);
    }

    public boolean isDead() {
        return (isAlive == false);
    }

    public void born() {
        isAlive = true;
    }

    public void die() {
        isAlive = false;
    }

}
