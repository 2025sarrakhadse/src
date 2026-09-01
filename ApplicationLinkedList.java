public class ApplicationLinkedList {

    // Node of the linked list
    private class Node {

        Application application;
        Node next;

        Node(Application application) {
            this.application = application;
            this.next = null;
        }
    }

    // First node
    private Node head;

    // Constructor
    public ApplicationLinkedList() {
        head = null;
    }

    // Add application at the end
    public boolean addApplication(Application application) {

        if (application == null) {
            return false;
        }

        Node newNode = new Node(application);

        if (head == null) {
            head = newNode;
            return true;
        }

        Node current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;

        return true;
    }

    // Display all applications
    public void displayApplications() {

        if (head == null) {
            System.out.println("No applications found.");
            return;
        }

        System.out.println("\n===== Application List =====");

        Node current = head;

        while (current != null) {

            current.application.displayApplicationDetails();

            current = current.next;
        }
    }

    // Search application by ID
    public Application searchApplication(int applicationId) {

        Node current = head;

        while (current != null) {

            if (current.application.getApplicationId()
                    == applicationId) {

                return current.application;
            }

            current = current.next;
        }

        return null;
    }

    // Delete application by ID
    public boolean deleteApplication(int applicationId) {

        if (head == null) {
            return false;
        }

        // Delete first node
        if (head.application.getApplicationId()
                == applicationId) {

            head = head.next;
            return true;
        }

        Node current = head;

        while (current.next != null) {

            if (current.next.application
                    .getApplicationId() == applicationId) {

                current.next = current.next.next;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    // Count applications
    public int getTotalApplications() {

        int count = 0;
        Node current = head;

        while (current != null) {

            count++;
            current = current.next;
        }

        return count;
    }
}
