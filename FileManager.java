import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileManager {

    private static final String STUDENT_FILE =
            "students.txt";


    // ========================================
    // SAVE STUDENTS TO FILE
    // ========================================

    public static void saveStudents(Student[] students) {

        try {

            PrintWriter writer =
                    new PrintWriter(
                            new FileWriter(STUDENT_FILE)
                    );

            for (Student student : students) {

                writer.println(
                        student.getUserId() + "|" +
                        student.getName() + "|" +
                        student.getEmail() + "|" +
                        student.getDepartment() + "|" +
                        student.getCgpa()
                );
            }

            writer.close();

            System.out.println(
                    "Students saved successfully."
            );

        } catch (IOException e) {

            System.out.println(
                    "Error while saving students."
            );
        }
    }


    // ========================================
    // DISPLAY SAVED STUDENTS FROM FILE
    // ========================================

    public static void displaySavedStudents() {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(STUDENT_FILE)
                    );

            String line;

            System.out.println(
                    "\n===== SAVED STUDENTS ====="
            );

            while ((line = reader.readLine()) != null) {

                String[] data =
                        line.split("\\|");

                System.out.println(
                        "ID: " + data[0]
                        + " | Name: " + data[1]
                        + " | Email: " + data[2]
                        + " | Department: " + data[3]
                        + " | CGPA: " + data[4]
                );
            }

            reader.close();

        } catch (IOException e) {

            System.out.println(
                    "No student file found."
            );
        }
    }
}