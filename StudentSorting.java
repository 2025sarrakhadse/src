/**
 * Custom Data Structure Algorithm (DSA) Class: Bubble Sort implementation
 * for sorting Student records by CGPA (descending) or Name (alphabetical).
 * 
 * Complexity Analysis:
 * --------------------
 * - Worst-Case Time Complexity: O(N^2) - Occurs when elements are in reverse order.
 * - Average-Case Time Complexity: O(N^2) - Occurs for typical unordered input arrays.
 * - Best-Case Time Complexity: O(N) - Occurs when array is already sorted, due to early exit boolean flag (`swapped`).
 * - Auxiliary Space Complexity: O(1) - Sorting is performed in-place without additional memory allocation.
 */
public class StudentSorting {

    /**
     * Sorts student records by CGPA in descending order (highest CGPA first).
     * Algorithm: Custom Bubble Sort with early exit flag optimization.
     * 
     * @param students Array of Student objects to sort in-place.
     */
    public static void sortByCGPA(Student[] students) {
        if (students == null || students.length < 2) {
            return;
        }

        int n = students.length;

        // Bubble Sort Pass
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                // Compare CGPA values (descending order)
                if (students[j] != null && students[j + 1] != null) {
                    if (students[j].getCgpa() < students[j + 1].getCgpa()) {
                        // Swap adjacent elements
                        Student temp = students[j];
                        students[j] = students[j + 1];
                        students[j + 1] = temp;

                        swapped = true;
                    }
                }
            }

            // Optimization: Stop if no elements were swapped in inner loop (already sorted)
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * Sorts student records alphabetically by Name in ascending order (A to Z).
     * Algorithm: Custom Bubble Sort with early exit flag optimization.
     * 
     * @param students Array of Student objects to sort in-place.
     */
    public static void sortByName(Student[] students) {
        if (students == null || students.length < 2) {
            return;
        }

        int n = students.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (students[j] != null && students[j + 1] != null) {
                    String name1 = students[j].getName() != null ? students[j].getName() : "";
                    String name2 = students[j + 1].getName() != null ? students[j + 1].getName() : "";

                    if (name1.compareToIgnoreCase(name2) > 0) {
                        // Swap adjacent elements
                        Student temp = students[j];
                        students[j] = students[j + 1];
                        students[j + 1] = temp;

                        swapped = true;
                    }
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    /**
     * Displays sorted student records to standard output.
     * 
     * @param students Array of Student objects to print.
     */
    public static void displaySortedStudents(Student[] students) {
        if (students == null || students.length == 0) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("\n===== STUDENTS SORTED BY CGPA =====");

        for (Student student : students) {
            if (student != null) {
                System.out.println(
                        "ID: " + student.getUserId()
                        + " | Name: " + student.getName()
                        + " | CGPA: " + student.getCgpa()
                );
            }
        }
    }
}
