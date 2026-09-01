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

    // Constructor
    public ActionStack() {
        top = null;
    }

    // Push an action onto the stack
    public void push(String action) {

        Node newNode = new Node(action);

        newNode.next = top;

        top = newNode;
    }

    // Remove and return the top action
    public String pop() {

        if (top == null) {
            System.out.println("Action stack is empty.");
            return null;
        }

        String action = top.action;

        top = top.next;

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

    // Check whether the stack is empty
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

    // Count total actions
    public int size() {

        int count = 0;
        Node current = top;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }
}