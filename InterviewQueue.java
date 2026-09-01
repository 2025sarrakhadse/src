public class InterviewQueue {

    // Node represents one element of the queue
    private class Node {

        Application application;
        Node next;

        Node(Application application) {
            this.application = application;
            this.next = null;
        }
    }

    // Front = first application in the queue
    private Node front;

    // Rear = last application in the queue
    private Node rear;

    // Number of applications in the queue
    private int size;

    // Constructor
    public InterviewQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    // Add application to the rear
    public boolean enqueue(Application application) {

        if (application == null) {
            return false;
        }

        Node newNode = new Node(application);

        // If queue is empty
        if (rear == null) {

            front = newNode;
            rear = newNode;
            size++;

            return true;
        }

        // Connect new node after rear
        rear.next = newNode;

        // Move rear to the new node
        rear = newNode;

        size++;

        return true;
    }

    // Remove application from the front
    public Application dequeue() {

        if (front == null) {
            System.out.println("Interview queue is empty.");
            return null;
        }

        // Store application being removed
        Application application = front.application;

        // Move front to next node
        front = front.next;

        // If queue becomes empty
        if (front == null) {
            rear = null;
        }

        size--;

        return application;
    }

    // View the next application without removing it
    public Application peek() {

        if (front == null) {
            System.out.println("Interview queue is empty.");
            return null;
        }

        return front.application;
    }

    // Display all applications in queue
    public void displayQueue() {

        if (front == null) {
            System.out.println("Interview queue is empty.");
            return;
        }

        Node current = front;

        System.out.println("\n===== Interview Queue =====");

        while (current != null) {

            current.application.displayApplicationDetails();

            current = current.next;
        }
    }

    // Check whether queue is empty
    public boolean isEmpty() {

        return front == null;
    }

    // Get number of applications
    public int size() {

        return size;
    }

    // Clear the entire queue
    public void clear() {

        front = null;
        rear = null;
        size = 0;
    }
}
