public class Grid {

    private int width;
    private int height;

    private Cell[][] grid;
    private Cell[][] nextGrid;

    Grid(int width, int height){
        this.width = width;
        this.height = height;

        // Create the grid
        grid = new Cell[width][height];

        // Populate with dead cells to start
        populateGrid(grid);
    }

    private void populateGrid(Cell[][] populateGrid){

        //Iterate through the grid and create dead cells
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                populateGrid[x][y] = new Cell(this, x, y);
            }
        }
    }

    // Used to manually set cells, called from within Cell constructor
    public void registerCell(int xCord, int yCord, Cell cell){
        grid[xCord][yCord] = cell;
    }

    private Cell getCell(int xCord, int yCord){
        return grid[xCord][yCord];
    }


    public void display(int currentIteration, int maxIterations){

        //Display a line at the top
        for(int i = 0; i <= this.width; i++){
            System.out.print("-");
        }

        // Spacer line
        System.out.println();


        for(int i = 0; i < this.height; i++){

            //Add in the vertical bars
            System.out.print("|");

            for(int j = 0; j < this.width; j++) {

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
        for(int i = 0; i <= this.width; i++){
            System.out.print("-");
        }

        //Display the current iteration
        System.out.println("\n Iteration " + (currentIteration + 1) + " of " + maxIterations);


    }

    public void processCells(){

        //Create a new grid which will be next iteration
        nextGrid = new Cell[width][height];
        populateGrid(nextGrid);

        for(int x = 1; x < this.width - 1; x++){
            for(int y = 1; y < this.height - 1; y++){

                //Get the cell
                Cell currentCell = getCell(x,y);

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

    private boolean applyRules(boolean isAlive, int aliveNeighbours){
        if(isAlive && aliveNeighbours < 2){
            //Underpopulation
            return false;
        } else if(isAlive && aliveNeighbours > 3){
            //Overcrowding
            return false;
        } else if(!isAlive && aliveNeighbours == 3){
            //Creation of life
            return true;
        } else {
            return isAlive;
        }
    }

    private int getNeighbours(Cell cell){
        int neighbours = 0;
        int cellX = cell.getxCord();
        int cellY = cell.getyCord();

        // Above the cell
        if(getCell(cellX - 1, cellY - 1).isAlive()) {neighbours++;}
        if(getCell(cellX, cellY - 1).isAlive()) {neighbours++;}
        if(getCell(cellX + 1, cellY - 1).isAlive()) {neighbours++;}

        //Either side of cell
        if(getCell(cellX - 1, cellY).isAlive()) {neighbours++;}
        if(getCell(cellX + 1, cellY).isAlive()) {neighbours++;}

        //Below the cell
        if(getCell(cellX - 1, cellY + 1).isAlive()) {neighbours++;}
        if(getCell(cellX, cellY + 1).isAlive()) {neighbours++;}
        if(getCell(cellX + 1, cellY + 1).isAlive()) {neighbours++;}

        return neighbours;
    }
}
