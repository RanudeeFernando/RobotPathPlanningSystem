package RobotPathPlanning;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("AUTONOMOUS ROBOT PATH PLANNING APPLICATION\n");

        Scanner userInput = new Scanner(System.in);

        int maxRows = 0;
        int maxCols = 0;
        boolean isValidRow = false;
        boolean isValidCol = false;

        // Getting grid rows and columns from the user
        while (!isValidRow || !isValidCol) {
            try {
                if (!isValidRow) {
                    System.out.print("Enter number of rows for grid: ");
                    maxRows = userInput.nextInt();

                    if (maxRows < 3 || maxRows > 50) {
                        System.out.println("Please enter an integer between 3 and 50 for rows.\n");
                    } else {
                        isValidRow = true;
                    }
                }

                // Getting input for columns if row count is valid
                if (isValidRow) {
                    System.out.print("Enter number of columns for grid: ");
                    maxCols = userInput.nextInt();

                    if (maxCols < 3 || maxCols > 50) {
                        System.out.println("Please enter an integer between 3 and 50 for columns.\n");
                    } else {
                        isValidCol = true;
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.\n");
                userInput.nextLine(); // Consume the invalid input
            }
        }


        System.out.println();

        System.out.println("Visualization of grid: \n");
        // Creating the grid
        Grid grid = new Grid(maxRows, maxCols);
        grid.initializeGrid();
        System.out.println();


        // Getting start cell coordinates from user
        Cell startCell = null;
        boolean validStartCell = false;
        boolean validStartRow = false;
        int startRow = -1;

        while (!validStartCell) {
            try {
                if (!validStartRow) {
                    System.out.print("Enter start cell row: ");
                    startRow = userInput.nextInt();

                    if (startRow >= 0 && startRow < maxRows) {
                        validStartRow = true;
                    }
                    else {
                        System.out.println("Start cell row is out of bounds.\n");
                    }
                }

                if (validStartRow) {
                    System.out.print("Enter start cell column: ");
                    int startCol = userInput.nextInt();

                    if (startCol >= 0 && startCol < maxCols) {
                        startCell = new Cell(startRow, startCol);
                        validStartCell = true;
                        System.out.printf("Start cell is successfully set to (%d, %d).\n \n", startRow, startCol);
                    }
                    else {
                        System.out.println("Start cell column is out of bounds.\n");
                    }
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.\n");
                userInput.nextLine(); // Consume the invalid input
            }
        }


        // Getting goal cell coordinates from user
        Cell goalCell = null;
        boolean validGoalCell = false;
        boolean validGoalRow = false;
        int goalRow = -1;

        while (!validGoalCell) {
            try {
                if (!validGoalRow) {
                    System.out.print("Enter goal cell row: ");
                    goalRow = userInput.nextInt();

                    if (goalRow >= 0 && goalRow < maxRows) {
                        validGoalRow = true;
                    }
                    else {
                        System.out.println("Goal cell row is out of bounds.\n");
                    }
                }

                if (validGoalRow) {
                    System.out.print("Enter goal cell column: ");
                    int goalCol = userInput.nextInt();

                    if (goalCol >= 0 && goalCol < maxCols) {
                        if (goalRow != startRow || goalCol != startCell.getCol()) {
                            goalCell = new Cell(goalRow, goalCol);
                            validGoalCell = true;
                            System.out.printf("Goal cell is successfully set to (%d, %d).\n \n", goalRow, goalCol);
                        }
                        else {
                            System.out.println("Goal cell cannot be the same as the Start cell.\n");
                        }
                    }
                    else {
                        System.out.println("Goal cell column is out of bounds.\n");
                    }
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.\n");
                userInput.nextLine(); // Consume the invalid input
            }
        }


        // Setting start and goal cells
        grid.setStartCell(startCell.row, startCell.col);
        grid.setGoalCell(goalCell.row, goalCell.col);


        // Setting the Start Cell as the Robot's current position
        Robot robot = new Robot();
        robot.setCurrentPosition(startCell.row, startCell.col);


        double obstacleProbability = 0;
        boolean probabilityIsValid = false;

        // Getting obstacle density from user
        while (!probabilityIsValid) {
            try {
                System.out.print("Enter obstacle density (0.0 - 1.0): ");
                obstacleProbability = userInput.nextDouble();

                if (obstacleProbability < 0.0 || obstacleProbability > 1.0) {
                    System.out.println("Obstacle density must be between 0.0 and 1.0. \n");
                } else {
                    probabilityIsValid = true;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a decimal between 0.0 and 1.0. \n");
                userInput.nextLine(); // Consume the invalid input
            }
        }

        System.out.println();

        // Placing random obstacles in grid
        grid.placeObstacles(obstacleProbability);


        System.out.println("Visualization of grid after setting Start Cell, Goal Cell and Obstacles: \n");
        // Displaying grid with Start, Goal, and Obstacles
        grid.displayGrid();
        System.out.println();

        System.out.println("Visualization of grid along with optimal path from Start to Goal: \n");

        // Creating the PathPlanningAlgorithm instance
        PathPlanningAlgorithm pathPlanningAlgorithm = new PathPlanningAlgorithm(grid);

        // Planning the path
        pathPlanningAlgorithm.planPath(startCell, goalCell);

        // Tracing the path
        CustomArrayList<Cell> path = pathPlanningAlgorithm.tracePath(goalCell);

        // Setting Path Cells as true
        grid.setPathCells(path);


        // Displaying the grid with the path
        grid.displayPath();
        System.out.println();

        // Update the robot's position along the path
        PathPlanningAlgorithm.updateRobotPositionAlongPath(robot, path);


    }
}


