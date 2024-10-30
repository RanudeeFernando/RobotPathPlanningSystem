package RobotPathPlanning;

// Represents a Node in a linked list
public class Node {
    public Cell cell;       // cell is stored in Node
    public Node next;       // Reference to next Node

    // Constructor for Node class
    public Node(Cell cell) {
        this.cell = cell;
        this.next = null;
    }
}
