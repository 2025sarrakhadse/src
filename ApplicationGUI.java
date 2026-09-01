import javax.swing.*;
import java.awt.*;

public class ApplicationGUI extends JFrame {

    private PlacementSystem system;

    private JTextField applicationIdField;
    private JTextField studentIdField;
    private JTextField jobIdField;

    private JTextArea outputArea;

    public ApplicationGUI(PlacementSystem system) {

        this.system = system;

        setTitle("Placement Management System - Applications");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // =====================================================
        // MAIN PANEL
        // =====================================================

        JPanel mainPanel =
                new JPanel(new BorderLayout(10, 10));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15
                )
        );


        // =====================================================
        // TITLE
        // =====================================================

        JLabel titleLabel =
                new JLabel(
                        "APPLICATION MANAGEMENT",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );


        // =====================================================
        // INPUT PANEL
        // =====================================================

        JPanel inputPanel =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                10,
                                10
                        )
                );

        inputPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Application Information"
                )
        );


        applicationIdField =
                new JTextField();

        studentIdField =
                new JTextField();

        jobIdField =
                new JTextField();


        inputPanel.add(
                new JLabel("Application ID:")
        );

        inputPanel.add(
                applicationIdField
        );


        inputPanel.add(
                new JLabel("Student ID:")
        );

        inputPanel.add(
                studentIdField
        );


        inputPanel.add(
                new JLabel("Job ID:")
        );

        inputPanel.add(
                jobIdField
        );


        // =====================================================
        // BUTTON PANEL
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                3,
                                10,
                                10
                        )
                );


        JButton applyButton =
                new JButton("Apply for Job");

        JButton viewButton =
                new JButton("View Applications");

        JButton searchButton =
                new JButton("Search Application");

        JButton deleteButton =
                new JButton("Delete Application");

        JButton clearButton =
                new JButton("Clear");

        JButton backButton =
                new JButton("Close");


        buttonPanel.add(applyButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(searchButton);

        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);


        // =====================================================
        // TOP SECTION
        // =====================================================

        JPanel topPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        topPanel.add(
                inputPanel,
                BorderLayout.CENTER
        );

        topPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        mainPanel.add(
                topPanel,
                BorderLayout.NORTH
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
                        "Application Details"
                )
        );


        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );


        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        applyButton.addActionListener(
                e -> applyForJob()
        );


        viewButton.addActionListener(
                e -> viewApplications()
        );


        searchButton.addActionListener(
                e -> searchApplication()
        );


        deleteButton.addActionListener(
                e -> deleteApplication()
        );


        clearButton.addActionListener(
                e -> clearFields()
        );


        backButton.addActionListener(
                e -> dispose()
        );


        add(mainPanel);
    }


    // =====================================================
    // APPLY FOR JOB
    // =====================================================

    private void applyForJob() {

        try {

            int applicationId =
                    Integer.parseInt(
                            applicationIdField
                                    .getText()
                                    .trim()
                    );

            int studentId =
                    Integer.parseInt(
                            studentIdField
                                    .getText()
                                    .trim()
                    );

            int jobId =
                    Integer.parseInt(
                            jobIdField
                                    .getText()
                                    .trim()
                    );


            Student student =
                    system.findStudent(studentId);


            if (student == null) {

                outputArea.setText(
                        "Student not found."
                );

                return;
            }


            Job job =
                    system.findJob(jobId);


            if (job == null) {

                outputArea.setText(
                        "Job not found."
                );

                return;
            }


            // Check eligibility

            if (!job.isStudentEligible(student)) {

                outputArea.setText(
                        "Student is NOT eligible for this job.\n\n"
                        + "Minimum CGPA: "
                        + job.getMinimumCGPA()
                        + "\nRequired Skill: "
                        + job.getRequiredSkill()
                );

                return;
            }


            Application application =
                    new Application(
                            applicationId,
                            student,
                            job
                    );


            system.addApplication(application);


            outputArea.setText(
                    "APPLICATION SUCCESSFUL!\n\n"
                    + "Application ID: "
                    + applicationId
                    + "\nStudent: "
                    + student.getName()
                    + "\nJob: "
                    + job.getJobTitle()
                    + "\nCompany: "
                    + (
                        job.getCompany() != null
                        ? job.getCompany().getCompanyName()
                        : "Not specified"
                    )
                    + "\nStatus: Applied"
            );


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Application ID, Student ID and Job ID "
                    + "must be valid numbers.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =====================================================
    // VIEW ALL APPLICATIONS
    // =====================================================

    private void viewApplications() {

        Application[] applications =
                system.getAllApplications();


        if (applications == null
                || applications.length == 0) {

            outputArea.setText(
                    "No applications found."
            );

            return;
        }


        StringBuilder result =
                new StringBuilder();


        result.append(
                "===== ALL APPLICATIONS =====\n\n"
        );


        for (Application application :
                applications) {

            result.append(
                    "Application ID: "
                    + application.getApplicationId()
                    + "\n"
            );


            result.append(
                    "Student ID: "
                    + application.getStudent().getUserId()
                    + "\n"
            );


            result.append(
                    "Student Name: "
                    + application.getStudent().getName()
                    + "\n"
            );


            result.append(
                    "Job ID: "
                    + application.getJob().getJobId()
                    + "\n"
            );


            result.append(
                    "Job Title: "
                    + application.getJob().getJobTitle()
                    + "\n"
            );


            result.append(
                    "Company: "
                    + (
                        application.getJob().getCompany() != null
                        ? application.getJob()
                                .getCompany()
                                .getCompanyName()
                        : "Not specified"
                    )
                    + "\n"
            );


            result.append(
                    "Status: "
                    + application.getStatus()
                    + "\n"
            );


            result.append(
                    "Application Date: "
                    + application.getApplicationDate()
                    + "\n"
            );


            if (application.isInterviewScheduled()) {

                result.append(
                        "Interview Date: "
                        + application.getInterviewDate()
                        + "\n"
                );

                result.append(
                        "Interview Time: "
                        + application.getInterviewTime()
                        + "\n"
                );

                result.append(
                        "Interview Mode: "
                        + application.getInterviewMode()
                        + "\n"
                );
            }


            result.append(
                    "\n-----------------------------\n\n"
            );
        }


        outputArea.setText(
                result.toString()
        );
    }


    // =====================================================
    // SEARCH APPLICATION
    // =====================================================

    private void searchApplication() {

        try {

            int id =
                    Integer.parseInt(
                            applicationIdField
                                    .getText()
                                    .trim()
                    );


            Application application =
                    system.findApplication(id);


            if (application == null) {

                outputArea.setText(
                        "Application not found."
                );

                return;
            }


            StringBuilder result =
                    new StringBuilder();


            result.append(
                    "===== APPLICATION DETAILS =====\n\n"
            );


            result.append(
                    "Application ID: "
                    + application.getApplicationId()
                    + "\n"
            );


            result.append(
                    "Student ID: "
                    + application.getStudent().getUserId()
                    + "\n"
            );


            result.append(
                    "Student Name: "
                    + application.getStudent().getName()
                    + "\n"
            );


            result.append(
                    "Job ID: "
                    + application.getJob().getJobId()
                    + "\n"
            );


            result.append(
                    "Job Title: "
                    + application.getJob().getJobTitle()
                    + "\n"
            );


            result.append(
                    "Company: "
                    + (
                        application.getJob().getCompany() != null
                        ? application.getJob()
                                .getCompany()
                                .getCompanyName()
                        : "Not specified"
                    )
                    + "\n"
            );


            result.append(
                    "Application Date: "
                    + application.getApplicationDate()
                    + "\n"
            );


            result.append(
                    "Status: "
                    + application.getStatus()
                    + "\n"
            );


            if (application.isInterviewScheduled()) {

                result.append(
                        "\n===== INTERVIEW =====\n"
                );

                result.append(
                        "Date: "
                        + application.getInterviewDate()
                        + "\n"
                );

                result.append(
                        "Time: "
                        + application.getInterviewTime()
                        + "\n"
                );

                result.append(
                        "Mode: "
                        + application.getInterviewMode()
                        + "\n"
                );

                result.append(
                        "Interviewer: "
                        + application.getInterviewer()
                        + "\n"
                );
            }


            outputArea.setText(
                    result.toString()
            );


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid Application ID.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =====================================================
    // DELETE APPLICATION
    // =====================================================

    private void deleteApplication() {

        try {

            int id =
                    Integer.parseInt(
                            applicationIdField
                                    .getText()
                                    .trim()
                    );


            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Delete application with ID "
                            + id
                            + "?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );


            if (choice ==
                    JOptionPane.YES_OPTION) {

                boolean deleted =
                        system.deleteApplication(id);


                if (deleted) {

                    outputArea.setText(
                            "Application deleted successfully."
                    );

                } else {

                    outputArea.setText(
                            "Application not found."
                    );
                }
            }


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid Application ID.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =====================================================
    // CLEAR FIELDS
    // =====================================================

    private void clearFields() {

        applicationIdField.setText("");
        studentIdField.setText("");
        jobIdField.setText("");

        outputArea.setText("");
    }
}
