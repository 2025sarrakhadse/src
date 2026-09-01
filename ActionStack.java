public class ActionStack {

    // Node represents one action in the stack
    private class Node {

        String action;
        Node next;

        Node(String action) {
            this.action = action;
            this.next = null;
        }
    }

    // Top of the stack
    private Node top;

    // Number of actions
    private int size;

    // Constructor
    public ActionStack() {
        top = null;
        size = 0;
    }

    // Push an action onto the stack
    public boolean push(String action) {

        // Do not add empty actions
        if (action == null || action.trim().isEmpty()) {
            return false;
        }

        Node newNode = new Node(action);

        // New node points to current top
        newNode.next = top;

        // New node becomes top
        top = newNode;

        size++;

        return true;
    }

    // Remove and return the top action
    public String pop() {

        if (top == null) {
            System.out.println("Action stack is empty.");
            return null;
        }

        // Store top action
        String action = top.action;

        // Move top to the next node
        top = top.next;

        size--;

        return action;
    }

    // View the top action without removing it
    public String peek() {

        if (top == null) {
            System.out.println("Action stack is empty.");
            return null;
        }

        return top.action;
    }

    // Check whether stack is empty
    public boolean isEmpty() {

        return top == null;
    }

    // Display all actions
    public void displayActions() {

        if (top == null) {
            System.out.println("No actions available.");
            return;
        }

        Node current = top;

        System.out.println("\n===== Recent Actions =====");

        while (current != null) {

            System.out.println(current.action);

            current = current.next;
        }
    }

    // Get number of actions
    public int size() {

        return size;
    }

    // Remove all actions
    public void clear() {

        top = null;
        size = 0;
    }
}
