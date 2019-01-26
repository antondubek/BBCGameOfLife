public class Grid {

    private int width;
    private int height;

    private Cell[][] grid;

    Grid(int width, int height){
        this.width = width;
        this.height = height;

        // Create the grid
        grid = new Cell[width][height];

        // Populate with dead cells to start
        populateGrid();
    }

    private void populateGrid(){

        //Iterate through the grid and create dead cells
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                grid[x][y] = new Cell(this, x, y);
            }
        }
    }

    // Used to manually set cells, called from within Cell constructor
    public void registerCell(int xCord, int yCord, Cell cell){
        grid[xCord][yCord] = cell;
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

    }
}
