import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Grid grid = new Grid(8,15);

//        Cell cell1 = new Cell(grid, 1, 2, true);
//        Cell cell2 = new Cell(grid,2,2,true);
//        Cell cell3 = new Cell(grid, 3, 2, true);

        ArrayList<Cell> cells = new ArrayList<Cell>();

        cells.add(new Cell(grid, 1, 0, true));
        cells.add(new Cell(grid,0,2,true));
        cells.add(new Cell(grid, 1, 2, true));
        cells.add(new Cell(grid, 2, 1, true));
        cells.add(new Cell(grid, 2, 2, true));

        for(Cell cell : cells){
            cell.register();
        }

        Game game = new Game(grid);
        game.run(30);

    }
}
