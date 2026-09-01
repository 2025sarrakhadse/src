import javax.swing.*;
import java.awt.*;

public class StudentGUI extends JFrame {

    private PlacementSystem system;

    private JTextField idField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField passwordField;
    private JTextField departmentField;
    private JTextField cgpaField;
    private JTextField phoneField;
    private JTextField skillsField;

    private JTextArea outputArea;

    public StudentGUI(PlacementSystem system) {

        this.system = system;

        setTitle("Student Management");
        setSize(850, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        // =========================================
        // TITLE
        // =========================================

        JLabel titleLabel = new JLabel(
                "STUDENT MANAGEMENT",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        mainPanel.add(titleLabel, BorderLayout.NORTH);


        // =========================================
        // INPUT PANEL
        // =========================================

        JPanel inputPanel = new JPanel(
                new GridLayout(8, 2, 10, 10)
        );

        inputPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Student Information"
                )
        );

        idField = new JTextField();
        nameField = new JTextField();
        emailField = new JTextField();
        passwordField = new JTextField();
        departmentField = new JTextField();
        cgpaField = new JTextField();
        phoneField = new JTextField();
        skillsField = new JTextField();

        inputPanel.add(new JLabel("Student ID:"));
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);

        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passwordField);

        inputPanel.add(new JLabel("Department:"));
        inputPanel.add(departmentField);

        inputPanel.add(new JLabel("CGPA:"));
        inputPanel.add(cgpaField);

        inputPanel.add(new JLabel("Phone Number:"));
        inputPanel.add(phoneField);

        inputPanel.add(new JLabel("Skills:"));
        inputPanel.add(skillsField);


        // =========================================
        // BUTTON PANEL
        // =========================================

        JPanel buttonPanel = new JPanel(
                new GridLayout(2, 3, 10, 10)
        );

        JButton addButton =
                new JButton("Add Student");

        JButton viewButton =
                new JButton("View All");

        JButton searchButton =
                new JButton("Search");

        JButton deleteButton =
                new JButton("Delete");

        JButton sortButton =
                new JButton("Sort by CGPA");

        JButton clearButton =
                new JButton("Clear");


        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(clearButton);


        // =========================================
        // OUTPUT AREA
        // =========================================

        outputArea = new JTextArea();

        outputArea.setEditable(false);
        outputArea.setFont(
                new Font("Monospaced", Font.PLAIN, 14)
        );

        JScrollPane scrollPane =
                new JScrollPane(outputArea);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Output"
                )
        );


        // =========================================
        // CENTER PANEL
        // =========================================

        JPanel centerPanel =
                new JPanel(new BorderLayout(10, 10));

        centerPanel.add(
                inputPanel,
                BorderLayout.NORTH
        );

        centerPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                scrollPane,
                BorderLayout.SOUTH
        );


        // =========================================
        // ADD STUDENT
        // =========================================

        addButton.addActionListener(e -> addStudent());


        // =========================================
        // VIEW STUDENTS
        // =========================================

        viewButton.addActionListener(e -> viewStudents());


        // =========================================
        // SEARCH STUDENT
        // =========================================

        searchButton.addActionListener(e -> searchStudent());


        // =========================================
        // DELETE STUDENT
        // =========================================

        deleteButton.addActionListener(e -> deleteStudent());


        // =========================================
        // SORT STUDENTS
        // =========================================

        sortButton.addActionListener(e -> sortStudents());


        // =========================================
        // CLEAR FIELDS
        // =========================================

        clearButton.addActionListener(e -> clearFields());


        add(mainPanel);
    }


    // =====================================================
    // ADD STUDENT
    // =====================================================

    private void addStudent() {

        try {

            int id =
                    Integer.parseInt(
                            idField.getText().trim()
                    );

            String name =
                    nameField.getText().trim();

            String email =
                    emailField.getText().trim();

            String password =
                    passwordField.getText().trim();

            String department =
                    departmentField.getText().trim();

            double cgpa =
                    Double.parseDouble(
                            cgpaField.getText().trim()
                    );

            String phone =
                    phoneField.getText().trim();

            String skillInput =
                    skillsField.getText().trim();

            if (name.isEmpty()
                    || email.isEmpty()
                    || password.isEmpty()
                    || department.isEmpty()
                    || phone.isEmpty()
                    || skillInput.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all fields."
                );

                return;
            }

            String[] skills =
                    skillInput.split(",");

            for (int i = 0; i < skills.length; i++) {
                skills[i] = skills[i].trim();
            }

            Student student =
                    new Student(
                            id,
                            name,
                            email,
                            password,
                            department,
                            cgpa,
                            skills,
                            phone
                    );

            system.registerStudent(student);

            outputArea.setText(
                    "Student added successfully.\n\n"
                    + "Student ID: " + id
                    + "\nName: " + name
                    + "\nDepartment: " + department
                    + "\nCGPA: " + cgpa
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student ID and CGPA must be valid numbers."
            );
        }
    }


    // =====================================================
    // VIEW ALL STUDENTS
    // =====================================================

    private void viewStudents() {

        Student[] students =
                system.getAllStudents();

        if (students == null
                || students.length == 0) {

            outputArea.setText(
                    "No students registered."
            );

            return;
        }

        StringBuilder result =
                new StringBuilder();

        result.append(
                "===== REGISTERED STUDENTS =====\n\n"
        );

        for (Student student : students) {

            result.append(
                    "ID: "
                    + student.getUserId()
                    + "\n"
            );

            result.append(
                    "Name: "
                    + student.getName()
                    + "\n"
            );

            result.append(
                    "Email: "
                    + student.getEmail()
                    + "\n"
            );

            result.append(
                    "Department: "
                    + student.getDepartment()
                    + "\n"
            );

            result.append(
                    "CGPA: "
                    + student.getCgpa()
                    + "\n"
            );

            result.append(
                    "Phone: "
                    + student.getPhoneNumber()
                    + "\n"
            );

            result.append(
                    "Skills: "
            );

            for (String skill :
                    student.getSkills()) {

                result.append(
                        skill + " "
                );
            }

            result.append(
                    "\n-----------------------------\n"
            );
        }

        outputArea.setText(
                result.toString()
        );
    }


    // =====================================================
    // SEARCH STUDENT
    // =====================================================

    private void searchStudent() {

        try {

            int id =
                    Integer.parseInt(
                            idField.getText().trim()
                    );

            Student student =
                    system.findStudent(id);

            if (student == null) {

                outputArea.setText(
                        "Student not found."
                );

                return;
            }

            StringBuilder result =
                    new StringBuilder();

            result.append(
                    "===== STUDENT DETAILS =====\n\n"
            );

            result.append(
                    "Student ID: "
                    + student.getUserId()
                    + "\n"
            );

            result.append(
                    "Name: "
                    + student.getName()
                    + "\n"
            );

            result.append(
                    "Email: "
                    + student.getEmail()
                    + "\n"
            );

            result.append(
                    "Department: "
                    + student.getDepartment()
                    + "\n"
            );

            result.append(
                    "CGPA: "
                    + student.getCgpa()
                    + "\n"
            );

            result.append(
                    "Phone: "
                    + student.getPhoneNumber()
                    + "\n"
            );

            result.append(
                    "Skills: "
            );

            for (String skill :
                    student.getSkills()) {

                result.append(
                        skill + " "
                );
            }

            outputArea.setText(
                    result.toString()
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid Student ID."
            );
        }
    }


    // =====================================================
    // DELETE STUDENT
    // =====================================================

    private void deleteStudent() {

        try {

            int id =
                    Integer.parseInt(
                            idField.getText().trim()
                    );

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Delete student with ID "
                                    + id + "?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

            if (choice ==
                    JOptionPane.YES_OPTION) {

                system.deleteStudent(id);

                outputArea.setText(
                        "Delete operation completed."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid Student ID."
            );
        }
    }


    // =====================================================
    // SORT BY CGPA
    // =====================================================

    private void sortStudents() {

        Student[] students =
                system.getStudentsSortedByCGPA();

        if (students == null
                || students.length == 0) {

            outputArea.setText(
                    "No students available."
            );

            return;
        }

        StudentSorting.sortByCGPA(students);

        StringBuilder result =
                new StringBuilder();

        result.append(
                "===== STUDENTS SORTED BY CGPA =====\n\n"
        );

        for (Student student :
                students) {

            result.append(
                    "ID: "
                    + student.getUserId()
                    + " | Name: "
                    + student.getName()
                    + " | CGPA: "
                    + student.getCgpa()
                    + "\n"
            );
        }

        outputArea.setText(
                result.toString()
        );
    }


    // =====================================================
    // CLEAR
    // =====================================================

    private void clearFields() {

        idField.setText("");
        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        departmentField.setText("");
        cgpaField.setText("");
        phoneField.setText("");
        skillsField.setText("");
        outputArea.setText("");
    }
}
