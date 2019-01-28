import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Grid grid = new Grid(5,5);


        ArrayList<Cell> cells = new ArrayList<Cell>();

        //Blinker
        cells.add(new Cell(grid, 1, 2, true));
        cells.add(new Cell(grid,2,2,true));
        cells.add(new Cell(grid, 3, 2, true));

        //Glider top
//        cells.add(new Cell(grid, 1, 0, true));
//        cells.add(new Cell(grid,0,2,true));
//        cells.add(new Cell(grid, 1, 2, true));
//        cells.add(new Cell(grid, 2, 1, true));
//        cells.add(new Cell(grid, 2, 2, true));

        //Glider top middle
//        cells.add(new Cell(grid, 2, 0, true));
//        cells.add(new Cell(grid,1,2,true));
//        cells.add(new Cell(grid, 2, 2, true));
//        cells.add(new Cell(grid, 3, 1, true));
//        cells.add(new Cell(grid, 3, 2, true));

        //Glider Middle
//        cells.add(new Cell(grid, 2, 1, true));
//        cells.add(new Cell(grid,1,3,true));
//        cells.add(new Cell(grid, 2, 3, true));
//        cells.add(new Cell(grid, 3, 2, true));
//        cells.add(new Cell(grid, 3, 3, true));

        //Glider Upside Down - Develops big
//        cells.add(new Cell(grid, 2, 2, true));
//        cells.add(new Cell(grid,0,1,true));
//        cells.add(new Cell(grid, 1, 1, true));
//        cells.add(new Cell(grid, 1, 3, true));
//        cells.add(new Cell(grid, 2, 1, true));

//        //Lightweight Space Ship (Goes left)
//        cells.add(new Cell(grid, 2, 1, true));
//        cells.add(new Cell(grid,5,1,true));
//        cells.add(new Cell(grid, 1, 2, true));
//        cells.add(new Cell(grid, 1, 3, true));
//        cells.add(new Cell(grid, 1, 4, true));
//        cells.add(new Cell(grid, 2, 4, true));
//        cells.add(new Cell(grid, 3, 4, true));
//        cells.add(new Cell(grid, 4, 4, true));
//        cells.add(new Cell(grid, 5, 3, true));

        //Lightweight Space Ship Higher (Goes left)
//        cells.add(new Cell(grid, 2, 0, true));
//        cells.add(new Cell(grid,5,0,true));
//        cells.add(new Cell(grid, 1, 1, true));
//        cells.add(new Cell(grid, 1, 2, true));
//        cells.add(new Cell(grid, 1, 3, true));
//        cells.add(new Cell(grid, 2, 3, true));
//        cells.add(new Cell(grid, 3, 3, true));
//        cells.add(new Cell(grid, 4, 3, true));
//        cells.add(new Cell(grid, 5, 2, true));

        //Square top middle
//        cells.add(new Cell(grid, 2,1,true));
//        cells.add(new Cell(grid, 2,0,true));
//        cells.add(new Cell(grid, 3,0,true));
//        cells.add(new Cell(grid, 3,1,true));

        for(Cell cell : cells){
            cell.register();
        }

        Game game = new Game(grid);
        game.run(3);

    }
}
