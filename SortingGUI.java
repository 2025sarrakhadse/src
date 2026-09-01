import javax.swing.*;
import java.awt.*;

public class SortingGUI extends JFrame {

    private PlacementSystem system;
    private JTextArea outputArea;

    public SortingGUI(PlacementSystem system) {

        this.system = system;

        setTitle("Placement Management System - Student Sorting");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // =====================================================
        // MAIN PANEL
        // =====================================================

        JPanel mainPanel =
                new JPanel(new BorderLayout(15, 15));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );


        // =====================================================
        // TITLE
        // =====================================================

        JLabel titleLabel =
                new JLabel(
                        "STUDENT SORTING",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );


        // =====================================================
        // INFORMATION LABEL
        // =====================================================

        JLabel infoLabel =
                new JLabel(
                        "Students will be sorted by CGPA in descending order.",
                        SwingConstants.CENTER
                );

        infoLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );


        // =====================================================
        // BUTTON PANEL
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );


        JButton sortButton =
                new JButton("Sort by CGPA");


        JButton refreshButton =
                new JButton("Refresh");


        JButton clearButton =
                new JButton("Clear");


        JButton closeButton =
                new JButton("Close");


        buttonPanel.add(sortButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(closeButton);


        // =====================================================
        // TOP PANEL
        // =====================================================

        JPanel topPanel =
                new JPanel(
                        new BorderLayout()
                );


        topPanel.add(
                infoLabel,
                BorderLayout.NORTH
        );


        topPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        mainPanel.add(
                topPanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // OUTPUT AREA
        // =====================================================

        outputArea =
                new JTextArea();

        outputArea.setEditable(false);

        outputArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );


        JScrollPane scrollPane =
                new JScrollPane(
                        outputArea
                );


        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Sorted Students"
                )
        );


        mainPanel.add(
                scrollPane,
                BorderLayout.SOUTH
        );


        // =====================================================
        // FIX LAYOUT
        // =====================================================

        JPanel contentPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );


        contentPanel.add(
                topPanel,
                BorderLayout.NORTH
        );


        contentPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );


        mainPanel.removeAll();

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        sortButton.addActionListener(
                e -> sortStudents()
        );


        refreshButton.addActionListener(
                e -> displayStudents()
        );


        clearButton.addActionListener(
                e -> outputArea.setText("")
        );


        closeButton.addActionListener(
                e -> dispose()
        );


        add(mainPanel);
    }


    // =====================================================
    // SORT STUDENTS
    // =====================================================

    private void sortStudents() {

        Student[] students =
                system.getAllStudents();


        if (students == null ||
                students.length == 0) {

            outputArea.setText(
                    "No students available for sorting."
            );

            return;
        }


        StudentSorting.sortByCGPA(students);


        StringBuilder result =
                new StringBuilder();


        result.append(
                "===== STUDENTS SORTED BY CGPA =====\n\n"
        );


        int rank = 1;


        for (Student student : students) {

            result.append(
                    "Rank: "
                    + rank
                    + "\n"
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
                    "Skills: "
            );


            String[] skills =
                    student.getSkills();


            if (skills != null) {

                for (String skill : skills) {

                    result.append(
                            skill + " "
                    );
                }

            } else {

                result.append(
                        "None"
                );
            }


            result.append(
                    "\n"
            );


            result.append(
                    "--------------------------------\n"
            );


            rank++;
        }


        outputArea.setText(
                result.toString()
        );
    }


    // =====================================================
    // DISPLAY STUDENTS WITHOUT SORTING
    // =====================================================

    private void displayStudents() {

        Student[] students =
                system.getAllStudents();


        if (students == null ||
                students.length == 0) {

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
                    + " | "
            );


            result.append(
                    "Name: "
                    + student.getName()
                    + " | "
            );


            result.append(
                    "Department: "
                    + student.getDepartment()
                    + " | "
            );


            result.append(
                    "CGPA: "
                    + student.getCgpa()
                    + "\n"
            );
        }


        outputArea.setText(
                result.toString()
        );
    }
}
