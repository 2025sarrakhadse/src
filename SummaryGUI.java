import javax.swing.*;
import java.awt.*;

public class SummaryGUI extends JFrame {

    private PlacementSystem system;
    private JTextArea outputArea;

    public SummaryGUI(PlacementSystem system) {

        this.system = system;

        setTitle("Placement Management System - System Summary");
        setSize(750, 550);
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
                        "SYSTEM SUMMARY",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );


        // =====================================================
        // SUMMARY AREA
        // =====================================================

        outputArea =
                new JTextArea();

        outputArea.setEditable(false);

        outputArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        16
                )
        );


        JScrollPane scrollPane =
                new JScrollPane(
                        outputArea
                );

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Placement System Statistics"
                )
        );


        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );


        // =====================================================
        // BUTTONS
        // =====================================================

        JButton refreshButton =
                new JButton("Refresh Summary");

        JButton closeButton =
                new JButton("Close");


        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );


        buttonPanel.add(
                refreshButton
        );

        buttonPanel.add(
                closeButton
        );


        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        refreshButton.addActionListener(
                e -> displaySummary()
        );


        closeButton.addActionListener(
                e -> dispose()
        );


        add(mainPanel);


        // Display summary when window opens
        displaySummary();
    }


    // =====================================================
    // DISPLAY SYSTEM SUMMARY
    // =====================================================

    private void displaySummary() {

        StringBuilder result =
                new StringBuilder();


        result.append(
                "========================================\n"
        );

        result.append(
                "       PLACEMENT SYSTEM SUMMARY\n"
        );

        result.append(
                "========================================\n\n"
        );


        // =====================================================
        // STUDENTS
        // =====================================================

        int totalStudents =
                system.getTotalStudents();


        result.append(
                "Total Students       : "
                + totalStudents
                + "\n\n"
        );


        // =====================================================
        // COMPANIES
        // =====================================================

        int totalCompanies =
                system.getTotalCompanies();


        result.append(
                "Total Companies      : "
                + totalCompanies
                + "\n\n"
        );


        // =====================================================
        // JOBS
        // =====================================================

        int totalJobs =
                system.getTotalJobs();


        result.append(
                "Total Jobs           : "
                + totalJobs
                + "\n\n"
        );


        // =====================================================
        // APPLICATIONS
        // =====================================================

        int totalApplications =
                system.getTotalApplications();


        result.append(
                "Total Applications   : "
                + totalApplications
                + "\n\n"
        );


        // =====================================================
        // INTERVIEW QUEUE
        // =====================================================

        int interviewQueueSize =
                system.getInterviewQueueSize();


        result.append(
                "Interview Queue      : "
                + interviewQueueSize
                + "\n\n"
        );


        // =====================================================
        // ACTION HISTORY
        // =====================================================

        int totalActions =
                system.getActionStackSize();


        result.append(
                "Recent Actions       : "
                + totalActions
                + "\n\n"
        );


        // =====================================================
        // HIGHEST CGPA
        // =====================================================

        Student[] students =
                system.getAllStudents();


        if (students != null &&
                students.length > 0) {

            Student highestCGPA =
                    students[0];


            for (Student student : students) {

                if (student.getCgpa()
                        > highestCGPA.getCgpa()) {

                    highestCGPA = student;
                }
            }


            result.append(
                    "----------------------------------------\n"
            );


            result.append(
                    "TOP STUDENT\n"
            );


            result.append(
                    "----------------------------------------\n"
            );


            result.append(
                    "Student ID         : "
                    + highestCGPA.getUserId()
                    + "\n"
            );


            result.append(
                    "Name               : "
                    + highestCGPA.getName()
                    + "\n"
            );


            result.append(
                    "Department         : "
                    + highestCGPA.getDepartment()
                    + "\n"
            );


            result.append(
                    "Highest CGPA       : "
                    + highestCGPA.getCgpa()
                    + "\n"
            );
        }


        result.append(
                "\n========================================\n"
        );


        outputArea.setText(
                result.toString()
        );
    }
}
