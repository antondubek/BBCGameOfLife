public class Main {

    public static void main(String[] args) {

        Grid grid = new Grid(10,10);

        new Cell(grid,3,4,true);

        Game game = new Game(grid);
        game.run(10);

    }
}
