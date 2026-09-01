import java.util.HashMap;

public class StudentHashMap {

    // HashMap stores Student objects using Student ID as the key
    private HashMap<Integer, Student> students;

    // Constructor
    public StudentHashMap() {
        students = new HashMap<>();
    }

    // Add a student
    public boolean addStudent(Student student) {

        if (student == null) {
            return false;
        }

        int id = student.getUserId();

        // Prevent duplicate Student IDs
        if (students.containsKey(id)) {

            System.out.println(
                    "Error: Student ID already exists!"
            );

            return false;
        }

        // Store student using ID as the key
        students.put(id, student);

        System.out.println(
                "Student added successfully."
        );

        return true;
    }

    // Search student by ID
    public Student searchStudent(int studentId) {

        Student student = students.get(studentId);

        if (student == null) {

            System.out.println(
                    "Student not found."
            );

            return null;
        }

        return student;
    }

    // Delete student by ID
    public boolean deleteStudent(int studentId) {

        if (!students.containsKey(studentId)) {

            System.out.println(
                    "Student not found."
            );

            return false;
        }

        students.remove(studentId);

        System.out.println(
                "Student deleted successfully."
        );

        return true;
    }

    // Display all registered students
    public void displayAllStudents() {

        if (students.isEmpty()) {

            System.out.println(
                    "No students registered."
            );

            return;
        }

        System.out.println(
                "\n===== Registered Students ====="
        );

        for (Student student : students.values()) {

            student.displayStudentDetails();
        }
    }

    // Return all students as an array
    // Used by StudentSorting
    public Student[] getAllStudents() {

        return students.values()
                       .toArray(new Student[0]);
    }

    // Return total number of students
    public int getTotalStudents() {

        return students.size();
    }

    // Check whether a student ID exists
    public boolean containsStudent(int studentId) {

        return students.containsKey(studentId);
    }
}
