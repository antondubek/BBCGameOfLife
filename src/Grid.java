public class Grid {

    private int width;
    private int height;

    private Cell[][] grid;

    Grid(int width, int height) {
        this.width = width;
        this.height = height;

        // Create the grid
        grid = new Cell[width][height];

        // Populate with dead cells to start
        populateGrid(grid);
    }

    private void populateGrid(Cell[][] populateGrid) {

        //Iterate through the grid and create dead cells
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                populateGrid[x][y] = new Cell(this, x, y);
            }
        }
    }

    // Used to manually set cells, called from within Cell constructor
    public void registerCell(int xCord, int yCord, Cell cell) {
        grid[xCord][yCord] = cell;
    }

    private Cell getCell(int xCord, int yCord) {
        return grid[xCord][yCord];
    }


    public void display(int currentIteration, int maxIterations) {

        //Display a line at the top
        for (int i = 0; i <= this.width; i++) {
            System.out.print("-");
        }

        // Spacer line
        System.out.println();


        for (int i = 0; i < this.height; i++) {

            //Add in the vertical bars
            System.out.print("|");

            for (int j = 0; j < this.width; j++) {

                //Print the toString of each cell
                System.out.print(grid[j][i].toString());

                // Check if its the last column and needs new line or not
                if (j != this.width - 1) {
                    System.out.print("|");
                } else {
                    System.out.println("|");
                }
            }
        }

        //Print bottom of the grid
        for (int i = 0; i <= this.width; i++) {
            System.out.print("-");
        }

        //Display the current iteration
        System.out.println("\n Iteration " + (currentIteration + 1) + " of " + maxIterations);


    }

    public void processCells() {

        //Create a new grid which will be next iteration
        Cell[][] nextGrid = new Cell[width][height];
        populateGrid(nextGrid);

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

    private int getNeighbours(Cell cell) {
        int neighbours = 0;
        int cellX = cell.getxCord();
        int cellY = cell.getyCord();

        // If the cell has space above it
        if (cellY != 0) {
            // Check above the cell
            if (getCell(cellX, cellY - 1).isAlive()) {
                neighbours++;
            }

            //If the cell has space to its left
            if(cellX != 0){
                //Check to its left
                if (getCell(cellX - 1, cellY - 1).isAlive()) {
                    neighbours++;
                }
            }

            //If the cell has space to the right
            if(cellX != this.width - 1) {
                //Check right
                if (getCell(cellX + 1, cellY - 1).isAlive()) {
                    neighbours++;
                }
            }
        }

        //If the cell has space to the left
        if (cellX != 0) {
            //Check its left
            if (getCell(cellX - 1, cellY).isAlive()) {
                neighbours++;
            }
        }

        //If the cell has space to the right
        if (cellX != this.width - 1) {
            //Check right
            if (getCell(cellX + 1, cellY).isAlive()) {
                neighbours++;
            }
        }

        //If the cell has space below
        if (cellY != this.height - 1) {
            //Check below
            if (getCell(cellX, cellY + 1).isAlive()) {
                neighbours++;
            }

            //If the cell has space to the left
            if(cellX != 0) {
                //Check left
                if (getCell(cellX - 1, cellY + 1).isAlive()) {
                    neighbours++;
                }
            }

            //If the cell has space to the right
            if(cellX != this.width - 1) {
                //Check right
                if (getCell(cellX + 1, cellY + 1).isAlive()) {
                    neighbours++;
                }
            }
        }

        return neighbours;
    }
}
