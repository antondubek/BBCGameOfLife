public class Game {

    private Grid grid;

    Game(Grid grid){
        this.grid = grid;
    }

    public void run(int iterations){

        // Runs the game for as many iterations as specified
        for(int i = 0; i < iterations; i++){
            grid.processCells();
            grid.display(i, iterations);
        }
    }
}
