import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Modern Student Management View.
 * Handles Student registration, search, deletion, and sorting with
 * strict input validation and HashMap backing store.
 */
public class StudentView extends JPanel {

    private PlacementSystem system;

    private JTextField idField, nameField, emailField, passwordField;
    private JTextField deptField, cgpaField, phoneField, skillsField;
    private JTable studentTable;
    private DefaultTableModel tableModel;

    public StudentView(PlacementSystem system) {
        this.system = system;

        setLayout(new BorderLayout(20, 20));
        setBackground(UITheme.COLOR_BG);
        setBorder(new EmptyBorder(24, 24, 24, 24));

        // Header
        JPanel headerPanel = UITheme.createHeaderPanel(
                "STUDENT MANAGEMENT",
                "Manage student records stored in O(1) StudentHashMap data structure"
        );
        add(headerPanel, BorderLayout.NORTH);

        // Split Pane Container: Form Left, Table Right
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createFormCard(), createTableCard());
        splitPane.setDividerLocation(380);
        splitPane.setOpaque(false);
        splitPane.setBorder(null);

        add(splitPane, BorderLayout.CENTER);

        refreshTable();
    }

    private JPanel createFormCard() {
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new BorderLayout(0, 16));

        JLabel title = new JLabel("Student Information");
        title.setFont(UITheme.FONT_HEADER);
        title.setForeground(UITheme.COLOR_TEXT_DARK);
        card.add(title, BorderLayout.NORTH);

        JPanel formGrid = new JPanel(new GridLayout(8, 2, 8, 12));
        formGrid.setOpaque(false);

        idField = UITheme.createStyledTextField();
        nameField = UITheme.createStyledTextField();
        emailField = UITheme.createStyledTextField();
        passwordField = UITheme.createStyledPasswordField();
        deptField = UITheme.createStyledTextField();
        cgpaField = UITheme.createStyledTextField();
        phoneField = UITheme.createStyledTextField();
        skillsField = UITheme.createStyledTextField();

        formGrid.add(createFormLabel("Student ID (Int):"));
        formGrid.add(idField);
        formGrid.add(createFormLabel("Full Name:"));
        formGrid.add(nameField);
        formGrid.add(createFormLabel("Email:"));
        formGrid.add(emailField);
        formGrid.add(createFormLabel("Password:"));
        formGrid.add(passwordField);
        formGrid.add(createFormLabel("Department:"));
        formGrid.add(deptField);
        formGrid.add(createFormLabel("CGPA (0.0-10.0):"));
        formGrid.add(cgpaField);
        formGrid.add(createFormLabel("Phone (10 Digits):"));
        formGrid.add(phoneField);
        formGrid.add(createFormLabel("Skills (Comma Sep):"));
        formGrid.add(skillsField);

        card.add(formGrid, BorderLayout.CENTER);

        // Buttons Panel
        JPanel btnPanel = new JPanel(new GridLayout(2, 2, 8, 8));
        btnPanel.setOpaque(false);

        JButton addBtn = UITheme.createPrimaryButton("Save / Add");
        JButton deleteBtn = UITheme.createDangerButton("Delete");
        JButton sortBtn = UITheme.createSecondaryButton("Sort CGPA");
        JButton clearBtn = UITheme.createButton("Clear", UITheme.COLOR_SLATE, Color.WHITE, UITheme.COLOR_NAVY);

        addBtn.addActionListener(e -> addStudent());
        deleteBtn.addActionListener(e -> deleteStudent());
        sortBtn.addActionListener(e -> sortStudents());
        clearBtn.addActionListener(e -> clearForm());

        btnPanel.add(addBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(sortBtn);
        btnPanel.add(clearBtn);

        card.add(btnPanel, BorderLayout.SOUTH);

        return card;
    }

    private JPanel createTableCard() {
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new BorderLayout(0, 12));

        JLabel title = new JLabel("Registered Students Directory");
        title.setFont(UITheme.FONT_HEADER);
        title.setForeground(UITheme.COLOR_TEXT_DARK);
        card.add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Name", "Department", "CGPA", "Phone", "Email", "Skills"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };

        studentTable = new JTable(tableModel);
        UITheme.styleTable(studentTable);

        // Listen for table row selection to populate form
        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && studentTable.getSelectedRow() != -1) {
                int row = studentTable.getSelectedRow();
                idField.setText(tableModel.getValueAt(row, 0).toString());
                nameField.setText(tableModel.getValueAt(row, 1).toString());
                deptField.setText(tableModel.getValueAt(row, 2).toString());
                cgpaField.setText(tableModel.getValueAt(row, 3).toString());
                phoneField.setText(tableModel.getValueAt(row, 4).toString());
                emailField.setText(tableModel.getValueAt(row, 5).toString());
                skillsField.setText(tableModel.getValueAt(row, 6).toString());
            }
        });

        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(UITheme.COLOR_BORDER));

        card.add(scrollPane, BorderLayout.CENTER);
        return card;
    }

    private JLabel createFormLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(UITheme.FONT_BOLD);
        label.setForeground(UITheme.COLOR_TEXT_MUTED);
        return label;
    }

    private void addStudent() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String password = passwordField.getText().trim();
            String dept = deptField.getText().trim();
            double cgpa = Double.parseDouble(cgpaField.getText().trim());
            String phone = phoneField.getText().trim();
            String skillsRaw = skillsField.getText().trim();

            // Strict Input Validations via ValidationUtil
            if (!ValidationUtil.isValidName(name)) {
                showError("Name cannot be empty.");
                return;
            }
            if (!ValidationUtil.isValidEmail(email)) {
                showError("Invalid Email address format (must contain '@' and '.').");
                return;
            }
            if (!ValidationUtil.isValidPassword(password)) {
                showError("Password must be at least 6 characters.");
                return;
            }
            if (!ValidationUtil.isValidPhone(phone)) {
                showError("Invalid Phone number. Must be exactly 10 digits.");
                return;
            }
            if (!ValidationUtil.isValidCGPA(cgpa)) {
                showError("CGPA must be between 0.0 and 10.0.");
                return;
            }

            String[] skills = skillsRaw.isEmpty() ? new String[0] : skillsRaw.split(",");
            for (int i = 0; i < skills.length; i++) {
                skills[i] = skills[i].trim();
            }

            Student student = new Student(id, name, email, password, dept, cgpa, skills, phone);
            boolean success = system.registerStudent(student);

            if (success) {
                JOptionPane.showMessageDialog(this, "Student registered successfully and saved to file!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                refreshTable();
            } else {
                showError("Student ID already exists in StudentHashMap.");
            }
        } catch (NumberFormatException ex) {
            showError("Please enter valid numerical values for Student ID and CGPA.");
        }
    }

    private void deleteStudent() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            boolean deleted = system.deleteStudent(id);
            if (deleted) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                refreshTable();
            } else {
                showError("Student ID not found in system.");
            }
        } catch (NumberFormatException ex) {
            showError("Please enter a valid numeric Student ID to delete.");
        }
    }

    private void sortStudents() {
        Student[] sorted = system.getStudentsSortedByCGPA();
        populateTable(sorted);
    }

    private void clearForm() {
        idField.setText("");
        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        deptField.setText("");
        cgpaField.setText("");
        phoneField.setText("");
        skillsField.setText("");
        studentTable.clearSelection();
    }

    public void refreshTable() {
        populateTable(system.getAllStudents());
    }

    private void populateTable(Student[] students) {
        tableModel.setRowCount(0);
        if (students != null) {
            for (Student s : students) {
                if (s != null) {
                    String skillsStr = s.getSkills() != null ? String.join(", ", s.getSkills()) : "";
                    tableModel.addRow(new Object[]{
                            s.getUserId(), s.getName(), s.getDepartment(), s.getCgpa(),
                            s.getPhoneNumber(), s.getEmail(), skillsStr
                    });
                }
            }
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Validation Error", JOptionPane.ERROR_MESSAGE);
    }
}
