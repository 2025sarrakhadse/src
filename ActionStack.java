public class ActionStack {

    // Node of the stack
    private class Node {

        String action;
        Node next;

        Node(String action) {
            this.action = action;
            this.next = null;
        }
    }

    // Top of stack
    private Node top;

    // Constructor
    public ActionStack() {

        top = null;
    }

    // Add action to stack
    public void push(String action) {

        if (action == null || action.trim().isEmpty()) {
            return;
        }

        Node newNode = new Node(action);

        newNode.next = top;

        top = newNode;
    }

    // Remove latest action
    public String pop() {

        if (top == null) {

            System.out.println(
                    "Action stack is empty."
            );

            return null;
        }

        String action = top.action;

        top = top.next;

        return action;
    }

    // View latest action
    public String peek() {

        if (top == null) {

            System.out.println(
                    "Action stack is empty."
            );

            return null;
        }

        return top.action;
    }

    // Check whether empty
    public boolean isEmpty() {

        return top == null;
    }

    // Display actions
    public void displayActions() {

        if (top == null) {

            System.out.println(
                    "No actions available."
            );

            return;
        }

        System.out.println(
                "\n===== Recent Actions ====="
        );

        Node current = top;

        while (current != null) {

            System.out.println(current.action);

            current = current.next;
        }
    }

    // Count actions
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
