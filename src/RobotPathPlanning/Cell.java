package RobotPathPlanning;

public class Cell {
    int row;
    int col;
    boolean isObstacle;
    boolean isStart;
    boolean isGoal;
    boolean isPath;
    double fCost;
    double gCost;
    double hCost;
    Cell parent;
    public CustomLinkedList adjacencyList;


    // Constructor for Cell class
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.isObstacle = false;
        this.isStart = false;
        this.isGoal = false;
        this.fCost = 0;
        this.gCost = 0;
        this.hCost = 0;
        this.parent = null;
        this.adjacencyList = new CustomLinkedList();
    }

    // Method for retrieving a row of a cell
    public int getRow() {
        return row;
    }

    // Method for retrieving a column of a cell
    public int getCol() {
        return col;
    }

    // Method for checking if cell is obstacle
    public boolean isObstacle() {
        return isObstacle;
    }

    // Method for setting g-cost of a cell
    public void setGCost(double gCost) {
        this.gCost = gCost;

    }

    // Method for getting g-cost of a cell
    public double getGCost() {
        return gCost;
    }

    // Method for setting h-cost of a cell
    public void setHCost(double hCost) {
        this.hCost = hCost;
    }

    // Method for getting h-cost of a cell
    public double getHCost() {
        return hCost;
    }

    // Method for setting f-cost of a cell
    public void setFCost(double fCost) {
        this.fCost = fCost;
    }

    // Method for getting f-cost of a cell
    public double getFCost() {
        return fCost;
    }

    // Method for returning cell in "(row, column)" format
    @Override
    public String toString() {
        return String.format("(%d, %d)", row, col);
    }

}
