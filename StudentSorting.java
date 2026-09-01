public class StudentSorting {

    // Sort students by CGPA in descending order
    public static void sortByCGPA(Student[] students) {

        if (students == null || students.length < 2) {
            return;
        }

        int n = students.length;

        // Bubble Sort
        for (int i = 0; i < n - 1; i++) {

            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {

                if (students[j].getCgpa()
                        < students[j + 1].getCgpa()) {

                    Student temp = students[j];

                    students[j] = students[j + 1];

                    students[j + 1] = temp;

                    swapped = true;
                }
            }

            // Stop if already sorted
            if (!swapped) {
                break;
            }
        }
    }

    // Display sorted students
    public static void displaySortedStudents(
            Student[] students) {

        if (students == null || students.length == 0) {

            System.out.println(
                    "No students available."
            );

            return;
        }

        System.out.println(
                "\n===== STUDENTS SORTED BY CGPA ====="
        );

        for (Student student : students) {

            System.out.println(
                    "ID: " + student.getUserId()
                    + " | Name: " + student.getName()
                    + " | CGPA: " + student.getCgpa()
            );
        }
    }
}
