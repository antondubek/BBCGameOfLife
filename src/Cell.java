public class Cell {

    private int xCord;
    private int yCord;
    private Grid grid;
    private boolean isAlive;

    //Normal constructor used by grid to populate dead cells
    Cell(Grid grid, int xCord, int yCord){
        this.grid = grid;
        this.xCord = xCord;
        this.yCord = yCord;
        this.isAlive = false;
    }

    //Overloaded constructors to allow user to place live cells
    Cell(Grid grid, int xCord, int yCord, boolean isAlive){
        this.grid = grid;
        this.xCord = xCord;
        this.yCord = yCord;
        this.isAlive = isAlive;
    }

    public void register(){
        grid.registerCell(this.xCord, this.yCord, this);
    }

    @Override
    public String toString() {
        if(isAlive){
            return "0";
        } else {
            return ".";
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getxCord() {
        return xCord;
    }

    public int getyCord() {
        return yCord;
    }

}
