public class ApplicationLinkedList {

    // Node represents one element of the linked list
    private class Node {

        Application application;
        Node next;

        Node(Application application) {
            this.application = application;
            this.next = null;
        }
    }

    // First node of the linked list
    private Node head;

    // Constructor
    public ApplicationLinkedList() {
        head = null;
    }

    // Add application at the end
    public void addApplication(Application application) {

        Node newNode = new Node(application);

        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }

    // Display all applications
    public void displayApplications() {

        if (head == null) {
            System.out.println("No applications found.");
            return;
        }

        Node current = head;

        System.out.println("\n===== Application List =====");

        while (current != null) {

            current.application.displayApplicationDetails();

            current = current.next;
        }
    }

    // Search application by ID
    public Application searchApplication(int applicationId) {

        Node current = head;

        while (current != null) {

            if (current.application.getApplicationId() == applicationId) {
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

        // If first node contains the application
        if (head.application.getApplicationId() == applicationId) {
            head = head.next;
            return true;
        }

        Node current = head;

        while (current.next != null) {

            if (current.next.application.getApplicationId() == applicationId) {

                current.next = current.next.next;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    // Count total applications
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
