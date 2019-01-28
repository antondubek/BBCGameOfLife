/**
 * Grid class contains all functions for outputting the grid to console and
 * processing cells for the next iteration.
 */
public class Grid {

    private int width;
    private int height;

    private Cell[][] grid;

    /*
        Pre built arrays of neighbours to check based on position of cell.
        Eg. topRightCords suggests the cell is in the upper right corner of grid and therefore will only
        contain neighbour co-ordinates to the left and below it. { X, Y} Used in getNeighbours!
    */
    private int[][] allCords = {{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}};
    private int[][] topRightCords = {{-1, 0}, {0, 1}, {-1, 1}};
    private int[][] topLeftCords = {{1, 0}, {0, 1}, {1, 1}};
    private int[][] bottomLeftCords = {{0, -1}, {1, -1}, {1, 0}};
    private int[][] bottomRightCords = {{-1, -1}, {0, -1}, {-1, 0},};
    private int[][] topCords = {{-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}};
    private int[][] leftCords = {{0, -1}, {1, -1}, {1, 0}, {0, 1}, {1, 1}};
    private int[][] bottomCords = {{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0},};
    private int[][] rightCords = {{-1, -1}, {0, -1}, {-1, 0}, {-1, 1}, {0, 1},};

    /**
     * Grid constructor, used to setup the initial empty grid based on height and width
     * passed.
     *
     * @param width  Initial width (no of columns) of the grid.
     * @param height Initial height (no of rows) of the grid.
     */
    Grid(int width, int height) {
        this.width = width;
        this.height = height;

        // Create the grid
        grid = new Cell[width][height];

        // Populate with dead cells to start
        populateGrid(grid);
    }

    /**
     * Populates the grid will dead cells, called from constructor and processCells().
     *
     * @param populateGrid Grid to the populate with dead cells
     */
    private void populateGrid(Cell[][] populateGrid) {

        //Iterate through the grid and create dead cells at all locations
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                populateGrid[x][y] = new Cell(this, x, y, false);
            }
        }
    }

    /**
     * Function to manually set cells within the grid. Called from within
     * Cell class.
     *
     * @param xCord X Co-ordinate within grid to put the cell
     * @param yCord Y Co-ordinate within grid to put the cell
     * @param cell  Cell object to put into the grid.
     */
    public void registerCell(int xCord, int yCord, Cell cell) {
        grid[xCord][yCord] = cell;
    }

    /**
     * Retrieves the cell at an X, Y location within the current grid.
     *
     * @param xCord X Co-ordinate within grid to retrieve the cell.
     * @param yCord Y Co-ordinate within grid to retrieve the cell.
     * @return Cell object located at X, Y location within current grid.
     */
    private Cell getCell(int xCord, int yCord) {
        return grid[xCord][yCord];
    }


    /**
     * Prints the grid to console using Cells toString methods.
     * Once finished, will check if the grid needs to expand.
     * Called from within Game run() method.
     *
     * @param currentIteration Current iteration that the game is on.
     * @param maxIterations    Number of iterations the game will run for.
     */
    public void display(int currentIteration, int maxIterations) {

        //Print the top of the grid
        for (int i = 0; i <= this.width; i++) {
            System.out.print("--");
        }

        // Spacer line
        System.out.println();


        for (int y = 0; y < this.height; y++) {

            //Add in the first vertical bar
            System.out.print("|");

            for (int x = 0; x < this.width; x++) {

                //Print the toString of each cell
                System.out.print(getCell(x, y).toString());

                // Check if its the last column and needs new line or not
                if (x != this.width - 1) {
                    System.out.print("|");
                } else {
                    System.out.println("|");
                }
            }
        }

        //Print bottom of the grid
        for (int i = 0; i <= this.width; i++) {
            System.out.print("--");
        }

        //Display the current iteration
        System.out.println("\n Iteration " + (currentIteration + 1) + " of " + maxIterations);

        //Check if the grid size needs to be increased
        checkEdges();

    }

    /**
     * Iterates through the grid and processes each of the cells.
     */
    public void processCells() {

        //Create a new grid which will be next iteration
        Cell[][] nextGrid = new Cell[this.width][this.height];

        //Populate the new grid with dead cells
        populateGrid(nextGrid);

        //Iterate through the grid
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {

                //Get the cell
                Cell currentCell = getCell(x, y);

                // Get current status of the Cell
                boolean isAlive = currentCell.isAlive();

                //Check its neighbours
                int aliveNeighbours = getNeighbours(currentCell);

                //Evaluate against rules
                isAlive = applyRules(isAlive, aliveNeighbours);

                //Add cell to new grid
                nextGrid[x][y] = new Cell(this, x, y, isAlive);
            }
        }

        //Set the next iteration to the current iteration
        this.grid = nextGrid;
    }

    /**
     * Applies the game of life rules, checking whether a cell should
     * be dead or alive on the next iteration based on its number of neighbours.
     *
     * @param isAlive         Current status of the Cell.
     * @param aliveNeighbours Number of alive neighbours the cell has.
     * @return Whether the cell should be dead (false) or alive (true) on the next iteration.
     */
    private boolean applyRules(boolean isAlive, int aliveNeighbours) {
        if (isAlive && aliveNeighbours < 2) {
            //Underpopulation
            return false;
        } else if (isAlive && aliveNeighbours > 3) {
            //Overcrowding
            return false;
        } else if (!isAlive && aliveNeighbours == 3) {
            //Creation of life
            return true;
        } else {
            // Stays the same
            return isAlive;
        }
    }

    /**
     * Checks the 8 neighbouring cells and keeps count of how many are alive.
     * Includes error checking to avoid out of bounds issues.
     *
     * @param cell Cell to check the number of alive neighbours.
     * @return Number of alive neighbours 0 - 8
     */
    private int getNeighbours(Cell cell) {

        //Get the X, Y co-ordinates of the cell
        int cellX = cell.getxCord();
        int cellY = cell.getyCord();

        // Helper variable initially set to check all neighbours.
        int[][] neighbourCords = allCords;

        //Checks what location the cell is and which of its neighbours to check
        //This avoids any array out of bounds issues by trying to look outside the grid
        if (cellX == 0 && cellY == 0) {
            neighbourCords = topLeftCords;
        } else if (cellX == this.width - 1 && cellY == this.height - 1) {
            neighbourCords = bottomRightCords;
        } else if (cellX == this.width - 1 && cellY == 0) {
            neighbourCords = topRightCords;
        } else if (cellX == 0 && cellY == this.height - 1) {
            neighbourCords = bottomLeftCords;
        } else if (cellY == 0) {
            neighbourCords = topCords;
        } else if (cellX == 0) {
            neighbourCords = leftCords;
        } else if (cellX == this.width - 1) {
            neighbourCords = rightCords;
        } else if (cellY == this.height - 1) {
            neighbourCords = bottomCords;
        }

        // Return the number of neighbours
        return searchNeighbours(neighbourCords, cellX, cellY);
    }

    /**
     * Checks and keeps count of whether the neighbours around the cell
     * are alive or not. Uses an offset based on int[][] defined at the top.
     *
     * @param neighbourCords Co-ordinates of neighbours (within grid) compared to cell
     * @param cellX          X co-ordinate of cell in grid
     * @param cellY          Y co-ordinate of cell in grid
     * @return Number of alive neighbours around the cell
     */
    private int searchNeighbours(int[][] neighbourCords, int cellX, int cellY) {
        int neighbours = 0;
        for (int[] offset : neighbourCords) {
            if (getCell(cellX + offset[0], cellY + offset[1]).isAlive()) {
                neighbours++;
            }
        }

        return neighbours;
    }


    /**
     * Checks whether there are any alive cells currently on the boundary of the grid.
     * If there are, the function will create a new bigger grid in the direction needed and
     * set this as the new grid before the cells are processed.
     */
    private void checkEdges() {

        //Check for alive cells on right hand side
        for (int y = 0; y < height; y++) {
            if (getCell(this.width - 1, y).isAlive()) {
                grid = increaseWidthRight(grid);
                break;
            }
        }

        //Check for alive cells on left side
        for (int y = 0; y < height; y++) {
            if (getCell(0, y).isAlive()) {
                grid = increaseWidthLeft(grid);
                break;
            }
        }

        // Check for alive cells at bottom
        for (int x = 0; x < width; x++) {
            if (getCell(x, this.height - 1).isAlive()) {
                grid = increaseHeightBottom(grid);
                break;
            }
        }

        //Check for alive cells at top
        for (int x = 0; x < width; x++) {
            if (getCell(x, 0).isAlive()) {
                grid = increaseHeightTop(grid);
                break;
            }
        }

    }

    /**
     * Increases the passed grid by 2 columns to the right.
     * Called from checkEdges()
     *
     * @param oldgrid Grid to be made larger
     * @return New larger grid.
     */
    private Cell[][] increaseWidthRight(Cell[][] oldgrid) {

        //Create new grid of new size
        Cell[][] newGrid = new Cell[this.width + 2][this.height];

        //Iterate through old grid and add cells
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                newGrid[x][y] = oldgrid[x][y];
            }
        }

        //Populate new empty locations with dead cells
        for (int x = this.width; x < this.width + 2; x++) {
            for (int y = 0; y < this.height; y++) {
                newGrid[x][y] = new Cell(this, x, y, false);
            }
        }

        //Add 2 to the global width
        this.width += 2;

        return newGrid;

    }

    /**
     * Increases the passed grid by 2 columns to the left.
     * Called from checkEdges()
     *
     * @param oldgrid Grid to be made larger
     * @return New larger grid.
     */
    private Cell[][] increaseWidthLeft(Cell[][] oldgrid) {

        //Create new grid of new size
        Cell[][] newGrid = new Cell[this.width + 2][this.height];

        //Iterate through old grid and add cells
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                newGrid[x + 2][y] = oldgrid[x][y];
            }
        }

        //Populate new empty locations with dead cells
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < this.height; y++) {
                newGrid[x][y] = new Cell(this, x, y, false);
            }
        }

        //Add 2 to the global width
        this.width += 2;

        //Update the cells X and Y co-ordinates with new location
        newGrid = updateCellLocation(newGrid);

        return newGrid;
    }

    /**
     * Increases the passed grid by 2 rows on the bottom.
     * Called from checkEdges()
     *
     * @param oldgrid Grid to be made larger
     * @return New larger grid.
     */
    private Cell[][] increaseHeightBottom(Cell[][] oldgrid) {
        //Create new grid of new size
        Cell[][] newGrid = new Cell[this.width][this.height + 2];

        //Iterate through old grid and add cells
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                newGrid[x][y] = oldgrid[x][y];
            }
        }

        //Populate new empty locations with dead cells
        for (int x = 0; x < this.width; x++) {
            for (int y = this.height; y < this.height + 2; y++) {
                newGrid[x][y] = new Cell(this, x, y, false);
            }
        }

        //Add 2 to the global height
        this.height += 2;

        return newGrid;
    }

    /**
     * Increases the passed grid by 2 rows on the top.
     * Called from checkEdges()
     *
     * @param oldgrid Grid to be made larger
     * @return New larger grid.
     */
    private Cell[][] increaseHeightTop(Cell[][] oldgrid) {
        //Create new grid of new size
        Cell[][] newGrid = new Cell[this.width][this.height + 2];


        //Iterate through old grid and add cells
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                newGrid[x][y + 2] = oldgrid[x][y];
            }
        }

        //Populate new empty locations with dead cells
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < 2; y++) {
                newGrid[x][y] = new Cell(this, x, y, false);
            }
        }

        //Add 2 to the global height
        this.height += 2;

        //Update the cells X and Y co-ordinates with new location
        newGrid = updateCellLocation(newGrid);

        return newGrid;
    }

    /**
     * When increasing the grid size left or upwards, cells co-ordinates are
     * changed. This updates the data within the cells to their new co-ordinates.
     *
     * @param grid Grid to iterate through and update the cells.
     * @return grid with updated cells.
     */
    private Cell[][] updateCellLocation(Cell[][] grid) {

        //Iterate through the grid
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                grid[x][y].setxCord(x); //Set the new X co-ordinate
                grid[x][y].setyCord(y); //Set the new Y co-ordinate
            }
        }

        return grid;
    }


}
