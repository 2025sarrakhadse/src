import java.util.HashMap;

public class StudentHashMap {

    private HashMap<Integer, Student> students;

    // Constructor
    public StudentHashMap() {
        students = new HashMap<>();
    }

    // Add student
    public void addStudent(Student student) {

        int id = student.getUserId();

        // Prevent duplicate Student IDs
        if (students.containsKey(id)) {
            System.out.println("Error: Student ID already exists!");
            return;
        }

        students.put(id, student);

        System.out.println("Student added successfully.");
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

    // Delete student
    public void deleteStudent(int studentId) {

        if (students.containsKey(studentId)) {

            students.remove(studentId);

            System.out.println("Student deleted successfully.");

        } else {

            System.out.println("Student not found.");
        }
    }

    // Display all students
    public void displayAllStudents() {

    System.out.println("\n===== Registered Students =====");

    for (Student student : students.values()) {
        student.displayStudentDetails();
    }
}


// Get all students for sorting
public Student[] getAllStudents() {
    return students.values().toArray(new Student[0]);
}

public int getTotalStudents() {
    return students.size();
}

}
