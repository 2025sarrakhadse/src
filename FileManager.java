import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileManager {

    private static final String STUDENT_FILE = "students.txt";

    // ========================================
    // SAVE STUDENTS TO FILE
    // ========================================

    public static void saveStudents(Student[] students) {

        if (students == null) {
            System.out.println("No student data to save.");
            return;
        }

        try (PrintWriter writer =
                     new PrintWriter(new FileWriter(STUDENT_FILE))) {

            for (Student student : students) {

                if (student == null) {
                    continue;
                }

                // Convert skills array into comma-separated text
                String skills = "";

                if (student.getSkills() != null) {

                    for (int i = 0; i < student.getSkills().length; i++) {

                        if (student.getSkills()[i] != null &&
                            !student.getSkills()[i].trim().isEmpty()) {

                            if (!skills.isEmpty()) {
                                skills += ",";
                            }

                            skills += student.getSkills()[i].trim();
                        }
                    }
                }

                writer.println(
                        student.getUserId() + "|" +
                        student.getName() + "|" +
                        student.getEmail() + "|" +
                        student.getPassword() + "|" +
                        student.getPhoneNumber() + "|" +
                        student.getDepartment() + "|" +
                        student.getCgpa() + "|" +
                        skills
                );
            }

            System.out.println("Students saved successfully.");

        } catch (IOException e) {

            System.out.println(
                    "Error while saving students: " + e.getMessage()
            );
        }
    }


    // ========================================
    // LOAD STUDENTS FROM FILE
    // ========================================

    public static Student[] loadStudents() {

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(STUDENT_FILE))) {

            // First count the number of valid records
            int count = 0;
            String line;

            while ((line = reader.readLine()) != null) {

                if (!line.trim().isEmpty()) {
                    count++;
                }
            }

            if (count == 0) {
                return new Student[0];
            }

            // Create array according to number of records
            Student[] students = new Student[count];

            // Reopen file for reading actual records
            try (BufferedReader secondReader =
                         new BufferedReader(new FileReader(STUDENT_FILE))) {

                int index = 0;

                while ((line = secondReader.readLine()) != null) {

                    if (line.trim().isEmpty()) {
                        continue;
                    }

                    String[] data = line.split("\\|", -1);

                    // We need exactly 8 fields
                    if (data.length < 8) {

                        System.out.println(
                                "Skipping invalid student record."
                        );

                        continue;
                    }

                    try {

                        int userId = Integer.parseInt(data[0].trim());

                        String name = data[1].trim();
                        String email = data[2].trim();
                        String password = data[3].trim();
                        String phoneNumber = data[4].trim();
                        String department = data[5].trim();

                        double cgpa =
                                Double.parseDouble(data[6].trim());

                        // Convert comma-separated skills into String[]
                        String[] skills;

                        if (data[7].trim().isEmpty()) {

                            skills = new String[0];

                        } else {

                            String[] rawSkills =
                                    data[7].split(",");

                            skills = new String[rawSkills.length];

                            for (int i = 0; i < rawSkills.length; i++) {
                                skills[i] = rawSkills[i].trim();
                            }
                        }

                        students[index] =
                                new Student(
                                        userId,
                                        name,
                                        email,
                                        password,
                                        department,
                                        cgpa,
                                        skills,
                                        phoneNumber
                                );

                        index++;

                    } catch (NumberFormatException e) {

                        System.out.println(
                                "Skipping invalid student record: "
                                + line
                        );
                    }
                }
            }

            System.out.println(
                    students.length +
                    " student record(s) loaded successfully."
            );

            return students;

        } catch (IOException e) {

            System.out.println(
                    "No student file found."
            );

            return new Student[0];
        }
    }


    // ========================================
    // DISPLAY SAVED STUDENTS
    // ========================================

    public static void displaySavedStudents() {

        Student[] students = loadStudents();

        if (students.length == 0) {

            System.out.println(
                    "\n===== SAVED STUDENTS ====="
            );

            System.out.println(
                    "No student records found."
            );

            return;
        }

        System.out.println(
                "\n===== SAVED STUDENTS ====="
        );

        for (Student student : students) {

            System.out.println(
                    "----------------------------------------"
            );

            System.out.println(
                    "Student ID: " + student.getUserId()
            );

            System.out.println(
                    "Name: " + student.getName()
            );

            System.out.println(
                    "Email: " + student.getEmail()
            );

            System.out.println(
                    "Phone: " + student.getPhoneNumber()
            );

            System.out.println(
                    "Department: " + student.getDepartment()
            );

            System.out.println(
                    "CGPA: " + student.getCgpa()
            );

            System.out.print("Skills: ");

            if (student.getSkills() == null ||
                student.getSkills().length == 0) {

                System.out.println("No skills added");

            } else {

                for (String skill : student.getSkills()) {

                    if (skill != null &&
                        !skill.trim().isEmpty()) {

                        System.out.print(skill + " ");
                    }
                }

                System.out.println();
            }
        }

        System.out.println(
                "----------------------------------------"
        );
    }
}