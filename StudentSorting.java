public class StudentSorting {

    // Sort students by CGPA in descending order
    public static void sortByCGPA(Student[] students) {

        // Check for null or empty array
        if (students == null || students.length == 0) {
            return;
        }

        int n = students.length;

        // Bubble Sort
        for (int i = 0; i < n - 1; i++) {

            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {

                // Higher CGPA should come first
                if (students[j].getCgpa()
                        < students[j + 1].getCgpa()) {

                    Student temp = students[j];

                    students[j] = students[j + 1];

                    students[j + 1] = temp;

                    swapped = true;
                }
            }

            // If no swapping happened,
            // array is already sorted
            if (!swapped) {
                break;
            }
        }
    }

    // Display students sorted by CGPA
    public static void displaySortedStudents(
            Student[] students) {

        System.out.println(
                "\n===== STUDENTS SORTED BY CGPA ====="
        );

        if (students == null || students.length == 0) {

            System.out.println("No students available.");
            return;
        }

        for (Student student : students) {

            System.out.println(
                    "ID: " + student.getUserId()
                    + " | Name: " + student.getName()
                    + " | CGPA: " + student.getCgpa()
            );
        }
    }
}
