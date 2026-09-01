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

    // Number of applications in the list
    private int size;

    // Constructor
    public ApplicationLinkedList() {
        head = null;
        size = 0;
    }

    // Add application at the end
    public boolean addApplication(Application application) {

        if (application == null) {
            return false;
        }

        // Prevent duplicate application IDs
        if (searchApplication(application.getApplicationId()) != null) {
            System.out.println("Error: Application ID already exists.");
            return false;
        }

        Node newNode = new Node(application);

        // If list is empty
        if (head == null) {
            head = newNode;
            size++;
            return true;
        }

        // Traverse to the last node
        Node current = head;

        while (current.next != null) {
            current = current.next;
        }

        // Add new node at the end
        current.next = newNode;
        size++;

        return true;
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

        // If first node contains the application
        if (head.application.getApplicationId()
                == applicationId) {

            head = head.next;
            size--;

            return true;
        }

        Node current = head;

        while (current.next != null) {

            if (current.next.application.getApplicationId()
                    == applicationId) {

                current.next = current.next.next;
                size--;

                return true;
            }

            current = current.next;
        }

        return false;
    }

    // Get first application
    public Application getHead() {

        if (head == null) {
            return null;
        }

        return head.application;
    }

    // Get next application after a given application
    public Application getNext(Application application) {

        Node current = head;

        while (current != null) {

            if (current.application == application) {

                if (current.next != null) {
                    return current.next.application;
                }

                return null;
            }

            current = current.next;
        }

        return null;
    }

    // Get total number of applications
    public int getTotalApplications() {

        return size;
    }

    // Alias used by PlacementSystem
    public int getSize() {

        return size;
    }

    // Check whether the list is empty
    public boolean isEmpty() {

        return head == null;
    }

    // Clear all applications
    public void clear() {

        head = null;
        size = 0;
    }
}
