public class InterviewQueue {

    // Node represents one element in the queue
    private class Node {

        Application application;
        Node next;

        Node(Application application) {
            this.application = application;
            this.next = null;
        }
    }

    // Front and rear of the queue
    private Node front;
    private Node rear;

    // Constructor
    public InterviewQueue() {
        front = null;
        rear = null;
    }

    // Add application to the rear
    public void enqueue(Application application) {

        Node newNode = new Node(application);

        if (rear == null) {
            front = newNode;
            rear = newNode;
            return;
        }

        rear.next = newNode;
        rear = newNode;
    }

    // Remove application from the front
    public Application dequeue() {

        if (front == null) {
            System.out.println("Interview queue is empty.");
            return null;
        }

        Application application = front.application;

        front = front.next;

        if (front == null) {
            rear = null;
        }

        return application;
    }

    // View the next application
    public Application peek() {

        if (front == null) {
            System.out.println("Interview queue is empty.");
            return null;
        }

        return front.application;
    }

    // Display all applications in the queue
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

    // Count applications
    public int size() {

        int count = 0;
        Node current = front;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }
}