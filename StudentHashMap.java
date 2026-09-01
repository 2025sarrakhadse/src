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
            System.out.println("Error: Student ID already exists!");
            return false;
        }

        students.put(id, student);

        System.out.println("Student added successfully.");
        return true;
    }

    // Search student by ID
    public Student searchStudent(int studentId) {

        Student student = students.get(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return null;
        }

        return student;
    }

    // Check whether a student exists
    public boolean containsStudent(int studentId) {

        return students.containsKey(studentId);
    }

    // Delete student
    public boolean deleteStudent(int studentId) {

        if (students.containsKey(studentId)) {

            students.remove(studentId);

            System.out.println("Student deleted successfully.");
            return true;

        } else {

            System.out.println("Student not found.");
            return false;
        }
    }

    // Display all students
    public void displayAllStudents() {

        System.out.println("\n===== Registered Students =====");

        if (students.isEmpty()) {
            System.out.println("No students registered.");
            return;
        }

        for (Student student : students.values()) {
            student.displayStudentDetails();
        }
    }

    // Get all students as an array
    public Student[] getAllStudents() {

        return students.values().toArray(new Student[0]);
    }

    // Get number of students
    public int getTotalStudents() {

        return students.size();
    }
}
