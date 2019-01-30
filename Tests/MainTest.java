import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MainTest {

    /**
     * Given a game of life
     * When there are no live cell
     * Then on the next step there are still no live cells
     */
    @Test
    public void scenario0(){
        Grid grid = new Grid(3, 3);
        Game game = new Game(grid);
        game.run(2);

        assertEquals(".", grid.getCell(0,0).toString());
        assertEquals(".", grid.getCell(1,0).toString());
        assertEquals(".", grid.getCell(2,0).toString());
        assertEquals(".", grid.getCell(0,1).toString());
        assertEquals(".", grid.getCell(1,1).toString());
        assertEquals(".", grid.getCell(2,1).toString());
        assertEquals(".", grid.getCell(0,2).toString());
        assertEquals(".", grid.getCell(1,2).toString());
        assertEquals(".", grid.getCell(2,2).toString());
    }

    /**
     * Given a game of life
     * When a live cell has fewer than 2 neighbours
     * Then the cell dies
     */
    @Test
    public void scenario1() {
        Grid grid = new Grid(3, 3);

        Cell cell = new Cell(grid, 1,1,true);
        cell.register();

        Game game = new Game(grid);
        game.run(1);

        assertEquals(".", grid.getCell(1,1).toString());

    }

    /**
     * Given a game of life
     * When a live cell has more than three neighbours
     * Then this cell dies
     */
    @Test
    public void scenario2() {
        Grid grid = new Grid(5, 5);
        ArrayList<Cell> cells = new ArrayList<Cell>();

        cells.add(new Cell(grid, 2, 1, true));
        cells.add(new Cell(grid,1,2,true));
        cells.add(new Cell(grid, 3, 2, true));
        cells.add(new Cell(grid,2,3,true));
        cells.add(new Cell(grid, 2, 2, true));

        for (Cell cell : cells) {
            cell.register();
        }

        Game game = new Game(grid);
        game.run(1);

        assertEquals(".", grid.getCell(2,2).toString());
    }

    /**
     * Given a game of life
     * When a live cell has two or three neighbours
     * Then this cell stays alive
     */
    @Test
    public void scenario3() {
        Grid grid = new Grid(4, 4);
        ArrayList<Cell> cells = new ArrayList<Cell>();

        cells.add(new Cell(grid, 1, 1, true));
        cells.add(new Cell(grid,2,1,true));
        cells.add(new Cell(grid, 1, 2, true));
        cells.add(new Cell(grid, 2, 2, true));

        for (Cell cell : cells) {
            cell.register();
        }

        Game game = new Game(grid);
        game.run(1);

        assertEquals("0", grid.getCell(1,1).toString());
        assertEquals("0", grid.getCell(2,1).toString());
        assertEquals("0", grid.getCell(1,2).toString());
        assertEquals("0", grid.getCell(2,2).toString());
    }

    /**
     * Given a game of life
     * When an empty position has exactly three neighbouring cells
     * Then a cell is created in this position
     */
    @Test
    public void scenario4() {
        Grid grid = new Grid(4, 4);
        ArrayList<Cell> cells = new ArrayList<Cell>();

        cells.add(new Cell(grid, 1, 1, true));
        cells.add(new Cell(grid, 1, 2, true));
        cells.add(new Cell(grid, 2, 2, true));

        for (Cell cell : cells) {
            cell.register();
        }

        Game game = new Game(grid);
        game.run(1);

        assertEquals("0", grid.getCell(2,1).toString());

    }

    /**
     * Tests the blinker, an oscillating pattern.
     */
    @Test
    public void scenario6() {

        Grid grid = new Grid(5, 5);
        ArrayList<Cell> cells = new ArrayList<Cell>();

        cells.add(new Cell(grid, 1, 2, true));
        cells.add(new Cell(grid,2,2,true));
        cells.add(new Cell(grid, 3, 2, true));

        for (Cell cell : cells) {
            cell.register();
        }

        Game game = new Game(grid);
        game.run(1);

        assertEquals("0", grid.getCell(2,1).toString());
        assertEquals("0", grid.getCell(2,2).toString());
        assertEquals("0", grid.getCell(2,3).toString());
        assertEquals(".", grid.getCell(1,1).toString());
        assertEquals(".", grid.getCell(3,1).toString());
        assertEquals(".", grid.getCell(1,2).toString());
        assertEquals(".", grid.getCell(3,2).toString());
        assertEquals(".", grid.getCell(1,3).toString());
        assertEquals(".", grid.getCell(3,3).toString());

    }
}