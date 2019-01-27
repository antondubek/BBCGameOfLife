/**
 * Cell class which populate the grid. Monitor there location and whether they are dead and alive.
 */
public class Cell {

    private int xCord;
    private int yCord;
    private Grid grid;
    private boolean isAlive;

    /**
     * Constructor used to create cells, can be defined whether dead or alive.
     *
     * @param grid  Grid that they belong to, needed for registering.
     * @param xCord X co-ordinate location on the grid
     * @param yCord Y co-ordinate location on the grid
     */
    Cell(Grid grid, int xCord, int yCord, boolean isAlive) {
        this.grid = grid;
        this.xCord = xCord;
        this.yCord = yCord;
        this.isAlive = isAlive;
    }

    /**
     * Register the cell to the grid, used by user in main when manually placing
     * starting seed cells.
     */
    public void register() {
        grid.registerCell(this.xCord, this.yCord, this);
    }

    /**
     * Overridden toString method to display a 0 or . depending if cell is dead or alive.
     */
    @Override
    public String toString() {
        if (isAlive) {
            return "0";
        } else {
            return ".";
        }
    }

    /**
     * Getter for isAlive.
     *
     * @return Whether cell is dead (false) or alive (true).
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Getter for X co-ordinate.
     *
     * @return X co-ordinate of the cell.
     */
    public int getxCord() {
        return xCord;
    }

    /**
     * Getter for Y co-ordinate
     *
     * @return Y co-ordinate of the cell.
     */
    public int getyCord() {
        return yCord;
    }

}
