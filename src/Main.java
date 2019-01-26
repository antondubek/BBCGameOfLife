public class Main {

    public static void main(String[] args) {

        Grid grid = new Grid(5,5);

        Cell cell1 = new Cell(grid, 1, 2, true);
        Cell cell2 = new Cell(grid,2,2,true);
        Cell cell3 = new Cell(grid, 3, 2, true);

        cell1.register();
        cell2.register();
        cell3.register();

        Game game = new Game(grid);
        game.run(4);

    }
}
