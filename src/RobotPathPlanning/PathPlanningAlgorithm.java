package RobotPathPlanning;

class PathPlanningAlgorithm {
    Grid grid;
    CustomLinkedList openList;
    CustomArrayList<Cell> closedList;
    boolean goalReached = false;
    static boolean pathFound = false;


    // Constructor for PathPlanningAlgorithm class
    public PathPlanningAlgorithm(Grid grid) {
        this.grid = grid;
        openList = new CustomLinkedList();
        closedList = new CustomArrayList<>();
    }


    public void planPath(Cell startCell, Cell goalCell) {
        // Setting the gCost of the start cell to 0
        startCell.setGCost(0);
        // Calculating the hCost of the start cell
        startCell.setHCost(Math.sqrt(Math.pow(startCell.getRow() - goalCell.getRow(), 2) + Math.pow(startCell.getCol() - goalCell.getCol(), 2)));
        // Calculate the fCost for the start cell
        startCell.setFCost(startCell.getGCost() + startCell.getHCost()); // Calculate the fCost for the start cell
        // Adding start cell to the open list
        openList.add(startCell);


        while (!openList.isEmpty() && !goalReached) {
            Cell currentCell = openList.remove();

            // Check if current cell is the goal cell
            if (currentCell.getRow() == goalCell.getRow() && currentCell.getCol() == goalCell.getCol()) {
                goalReached = true;
                goalCell.parent = currentCell;
                break;
            }

            closedList.add(currentCell);

            // Explore neighbors
            exploreNeighbors(currentCell, goalCell);

        }
    }


    //  Method for exploring neighbours/adjacent cells
    private void exploreNeighbors(Cell currentCell, Cell goalCell) {
        int[] rowOffsets = {-1, 0, 1, 0, -1, -1, 1, 1};
        int[] colOffsets = {0, 1, 0, -1, 1, -1, 1, -1};

        for (int i = 0; i < 8; i++) {
            int newRow = currentCell.getRow() + rowOffsets[i];
            int newCol = currentCell.getCol() + colOffsets[i];

            // Creating adjacency lists for each cell in the grid
            if (newRow >= 0 && newRow < grid.maxRows && newCol >= 0 && newCol < grid.maxCols) {
                if (!grid.cells[newRow][newCol].isStart && !grid.cells[newRow][newCol].isObstacle) {
                    currentCell.adjacencyList.add(grid.cells[newRow][newCol]);
                }
            }

        }

        // Iterating through the adjacency list of the current cell
        for (int j = 0; j < currentCell.adjacencyList.size(); j++) {
            Cell neighbor = currentCell.adjacencyList.get(j);

            if (!neighbor.isObstacle() && !closedList.contains(neighbor)) {
                double newGCost = currentCell.getGCost() + calculateDistance(currentCell, neighbor);

                if (!openList.contains(neighbor) || newGCost < neighbor.getGCost()) {
                    neighbor.parent = currentCell;
                    neighbor.setGCost(newGCost);
                    neighbor.setHCost(Math.sqrt(Math.pow(neighbor.getRow() - goalCell.getRow(), 2) + Math.pow(neighbor.getCol() - goalCell.getCol(), 2)));
                    neighbor.setFCost(neighbor.getGCost() + neighbor.getHCost());

                    if (!openList.contains(neighbor)) {
                        openList.add(neighbor);
                    }
                }
            }

        }

    }

    // Method to calculate relative distance/cost between two cells
    public double calculateDistance(Cell cell1, Cell cell2) {
        return Math.sqrt(Math.pow(cell1.getRow() - cell2.getRow(), 2) + Math.pow(cell1.getCol() - cell2.getCol(), 2));

    }

    // Method for reconstructing the optimal path
    public CustomArrayList<Cell> tracePath(Cell goalCell) {
        CustomArrayList<Cell> path = new CustomArrayList<>();
        Cell currentCell = goalCell;

        // Tracing to get the optimal path
        while (currentCell != null) {
            if (currentCell != goalCell) {
                path.add(currentCell);
            }
            currentCell = currentCell.parent;
        }

        // Reversing the path to get it in the correct order
        CustomArrayList<Cell> reversedPath = new CustomArrayList<>();
        for (int i = path.size() - 1; i >= 0; i--) {
            reversedPath.add(path.get(i));
        }

        assert goalCell != null;
        if (goalCell.parent == null) {
            System.out.println("Path not found. \n");
        }
        else {
            pathFound = true;
        }

        return reversedPath;

    }

    // Method for updating robot's position along the optimal path
    public static void updateRobotPositionAlongPath(Robot robot, CustomArrayList<Cell> path) {
        if (pathFound) {
            System.out.println("Coordinates of Robot's Path:\n");
            for (int i = 0; i < path.size(); i++) {
                Cell cell = path.get(i);
                robot.setCurrentPosition(cell.getRow(), cell.getCol());

                // Update the robot's orientation if it's not the last cell in the path
                if (i < path.size() - 1) {
                    Cell nextCell = path.get(i + 1);
                    updateRobotOrientation(robot, cell, nextCell);

                }

                System.out.printf("Robot is in cell (%d, %d) facing %s \n", robot.getCurrentRow(), robot.getCurrentCol(), robot.getOrientation());

                // Printing "↓" if it's not the last cell in the path
                if (i != path.size() - 1) {
                    System.out.println("↓");
                }
                // Printing a message when robot reaches the goal cell
                else {
                    System.out.println("\nRobot has reached the Goal Cell.");
                }

            }
        }
    }

    // Method for updating robot's orientation along the optimal path
    private static void updateRobotOrientation(Robot robot, Cell currentCell, Cell nextCell) {
        if (currentCell.getRow() < nextCell.getRow() && currentCell.getCol() == nextCell.getCol()) {
            robot.setOrientation(Direction.SOUTH);
        } else if (currentCell.getRow() > nextCell.getRow() && currentCell.getCol() == nextCell.getCol()) {
            robot.setOrientation(Direction.NORTH);
        } else if (currentCell.getCol() < nextCell.getCol() && currentCell.getRow() == nextCell.getRow()) {
            robot.setOrientation(Direction.EAST);
        } else if (currentCell.getCol() > nextCell.getCol() && currentCell.getRow() == nextCell.getRow()) {
            robot.setOrientation(Direction.WEST);
        } else if (currentCell.getCol() < nextCell.getCol() && currentCell.getRow() > nextCell.getRow()) {
            robot.setOrientation(Direction.NORTHEAST);
        } else if (currentCell.getCol() < nextCell.getCol() && currentCell.getRow() < nextCell.getRow()) {
            robot.setOrientation(Direction.SOUTHEAST);
        } else if (currentCell.getCol() > nextCell.getCol() && currentCell.getRow() < nextCell.getRow()) {
            robot.setOrientation(Direction.SOUTHWEST);
        } else if (currentCell.getCol() > nextCell.getCol() && currentCell.getRow() > nextCell.getRow()) {
            robot.setOrientation(Direction.NORTHWEST);
        }
    }


}
