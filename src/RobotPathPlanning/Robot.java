package RobotPathPlanning;

public class Robot {
    public int currentRow;
    public int currentCol;
    public Direction orientation;

    // Constructor for the Robot class
    public Robot() {
        this.currentRow = 0;
        this.currentCol = 0;
        this.orientation = Direction.NORTH; // Setting default orientation to North
    }

    // Method for setting robot orientation
    public void setOrientation(Direction orientation) {
        this.orientation = orientation;
    }

    // Method for retrieving robot orientation
    public Direction getOrientation() {
        return orientation;
    }

    // Method for setting current robot position
    public void setCurrentPosition(int row, int col) {
        this.currentRow = row;
        this.currentCol = col;
    }

    // Method for retrieving robot's current row
    public int getCurrentRow() {
        return currentRow;
    }

    // Method for retrieving robot's current column
    public int getCurrentCol() {
        return currentCol;
    }

}
