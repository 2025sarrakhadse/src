public class InterviewQueue {

    // Node of the queue
    private class Node {

        Application application;
        Node next;

        Node(Application application) {
            this.application = application;
            this.next = null;
        }
    }

    // Front and rear
    private Node front;
    private Node rear;

    // Constructor
    public InterviewQueue() {

        front = null;
        rear = null;
    }

    // Add application to queue
    public boolean enqueue(Application application) {

        if (application == null) {
            return false;
        }

        Node newNode = new Node(application);

        if (rear == null) {

            front = newNode;
            rear = newNode;

            return true;
        }

        rear.next = newNode;
        rear = newNode;

        return true;
    }

    // Remove application from front
    public Application dequeue() {

        if (front == null) {

            System.out.println(
                    "Interview queue is empty."
            );

            return null;
        }

        Application application = front.application;

        front = front.next;

        if (front == null) {
            rear = null;
        }

        return application;
    }

    // View next interview
    public Application peek() {

        if (front == null) {

            System.out.println(
                    "Interview queue is empty."
            );

            return null;
        }

        return front.application;
    }

    // Display queue
    public void displayQueue() {

        if (front == null) {

            System.out.println(
                    "Interview queue is empty."
            );

            return;
        }

        System.out.println(
                "\n===== Interview Queue ====="
        );

        Node current = front;

        while (current != null) {

            current.application
                    .displayApplicationDetails();

            current = current.next;
        }
    }

    // Check whether queue is empty
    public boolean isEmpty() {

        return front == null;
    }

    // Count interviews
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
