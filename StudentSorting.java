public class StudentSorting {

    public static void sortByCGPA(Student[] students) {

        int n = students.length;

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - i - 1; j++) {

                if (students[j].getCgpa()
                        < students[j + 1].getCgpa()) {

                    Student temp = students[j];

                    students[j] = students[j + 1];

                    students[j + 1] = temp;
                }
            }
        }
    }


    public static void displaySortedStudents(
            Student[] students) {

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