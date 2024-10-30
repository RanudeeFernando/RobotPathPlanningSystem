package RobotPathPlanning;


import java.util.Random;

public class Grid {
    Cell[][] cells;
    int maxRows;
    int maxCols;


    // Constructor for Grid class
    public Grid(int maxRows, int maxCols) {
        this.maxRows = maxRows;
        this.maxCols = maxCols;
        this.cells = new Cell[maxRows][maxCols];

    }

    // Method for initializing and displaying the empty grid
    public void initializeGrid() {
        for (int i = 0; i < maxRows; i++) {
            for (int j = 0; j < maxCols; j++) {
                cells[i][j] = new Cell(i, j);
                System.out.print("_ ");
            }
            System.out.println();
        }
    }

    // Method for placing obstacles in grid
    public void placeObstacles(double obstacleDensity) {
        Random rand = new Random();
        for (int i = 0; i < maxRows; i++) {
            for (int j = 0; j < maxCols; j++) {
                if (rand.nextDouble() < obstacleDensity && !cells[i][j].isStart && !cells[i][j].isGoal) {
                    cells[i][j].isObstacle = true;
                }
            }
        }
    }

    // Method for setting the start cell in grid
    public void setStartCell(int row, int col) {
        cells[row][col].isStart = true;
    }

    // Method for setting the goal cell in grid
    public void setGoalCell(int row, int col) {
        cells[row][col].isGoal = true;
    }

    // Method for displaying grid with start cell, goal cell, and obstacles
    public void displayGrid() {
        for (int i = 0; i < maxRows; i++) {
            for (int j = 0; j < maxCols; j++) {
                if (cells[i][j].isObstacle) {
                    System.out.print("x ");
                } else if (cells[i][j].isStart) {
                    System.out.print("S ");
                } else if (cells[i][j].isGoal) {
                    System.out.print("G ");
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
    }

    // Method for setting path cells
    public void setPathCells(CustomArrayList<Cell> path) {
        for (int i = 0; i < path.size(); i++) {
            Cell cell = path.get(i);
            if (!cell.isStart && !cell.isGoal) {
                cells[cell.getRow()][cell.getCol()].isPath = true;
            }
        }
    }

    // Method for displaying the path in grid
    public void displayPath() {
        for (int i = 0; i < maxRows; i++) {
            for (int j = 0; j < maxCols; j++) {
                if (cells[i][j].isObstacle) {
                    System.out.print("x ");         // Signifies obstacle
                } else if (cells[i][j].isStart) {
                    System.out.print("S ");         // Signifies start cell
                } else if (cells[i][j].isGoal) {
                    System.out.print("G ");         // Signifies goal cell
                } else if (cells[i][j].isPath) {
                    System.out.print("* ");         // Signifies path cell
                } else {
                    System.out.print("_ ");         // Signifies empty cell
                }
            }
            System.out.println();
        }

    }

}