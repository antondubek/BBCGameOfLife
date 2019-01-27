/**
 * Game class used to run the game for as many iterations as required
 */
public class Game {

    private Grid grid;

    /**
     * Game constructor
     *
     * @param grid The grid that needs to be processed at runtime.
     */
    Game(Grid grid) {
        this.grid = grid;
    }

    /**
     * Runs the Game of Life for the number of iterations required, displaying
     * the board and processing the cells at each iteration.
     *
     * @param iterations No of Iterations to run the game for
     */
    public void run(int iterations) {

        // Runs the game for as many iterations as specified
        for (int i = 0; i < iterations; i++) {

            //Display the current grid to console
            grid.display(i, iterations);

            //Process the cells for the next iteration
            grid.processCells();
        }
    }
}
