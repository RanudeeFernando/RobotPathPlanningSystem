package RobotPathPlanning;

public class CustomLinkedList {
    public Node head;
    public int size;

    // Constructor for CustomLinkedList class
    public CustomLinkedList() {
        head = null;
        size = 0;
    }

    // Method for adding a node to the list
    public void add(Cell cell) {
        Node newNode = new Node(cell);
        // If head is not null or new node has a better f cost than head, new node becomes the head
        if (head == null || cell.getFCost() < head.cell.getFCost()) {
            newNode.next = head;
            head = newNode;
        }
        else {
            // Finding position to insert new node
            Node current = head;
            while (current.next != null && cell.getFCost() >= current.next.cell.getFCost()) {
                current = current.next;
            }

            // Inserting new node into list
            newNode.next = current.next;
            current.next = newNode;
        }

        size++;
    }

    // Method for removing and returning the first node in the list
    public Cell remove() {
        // Checking if list is empty
        if (isEmpty()) {
            throw new RuntimeException("List is empty");
        }

        Cell removedItem = head.cell;
        head = head.next;
        size--;
        return removedItem;
    }

    // Method to check if list is empty
    public boolean isEmpty() {
        return head == null;
    }

    // Method to get size of list
    public int size() {
        return size;
    }

    // Method to check if the list contains a specific node
    public boolean contains(Cell cell) {
        Node current = head;
        while (current != null) {
            if (current.cell.equals(cell)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Method for getting a node using its index
    public Cell get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.format("Index %d out of bounds", index));
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.cell;
    }

}
