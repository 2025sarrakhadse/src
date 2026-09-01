import javax.swing.*;
import java.awt.*;

public class InterviewGUI extends JFrame {

    private PlacementSystem system;

    private JTextField applicationIdField;
    private JTextField dateField;
    private JTextField timeField;
    private JTextField modeField;
    private JTextField interviewerField;

    private JTextArea outputArea;

    public InterviewGUI(PlacementSystem system) {

        this.system = system;

        setTitle("Placement Management System - Interviews");
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
                        "INTERVIEW MANAGEMENT",
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
                                5,
                                2,
                                10,
                                10
                        )
                );

        inputPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Interview Information"
                )
        );


        applicationIdField =
                new JTextField();

        dateField =
                new JTextField();

        timeField =
                new JTextField();

        modeField =
                new JTextField();

        interviewerField =
                new JTextField();


        inputPanel.add(
                new JLabel("Application ID:")
        );

        inputPanel.add(
                applicationIdField
        );


        inputPanel.add(
                new JLabel("Interview Date:")
        );

        inputPanel.add(
                dateField
        );


        inputPanel.add(
                new JLabel("Interview Time:")
        );

        inputPanel.add(
                timeField
        );


        inputPanel.add(
                new JLabel("Interview Mode:")
        );

        inputPanel.add(
                modeField
        );


        inputPanel.add(
                new JLabel("Interviewer:")
        );

        inputPanel.add(
                interviewerField
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


        JButton scheduleButton =
                new JButton("Schedule Interview");

        JButton viewButton =
                new JButton("View Application");

        JButton cancelButton =
                new JButton("Cancel Interview");

        JButton queueButton =
                new JButton("View Interview Queue");

        JButton clearButton =
                new JButton("Clear");

        JButton closeButton =
                new JButton("Close");


        buttonPanel.add(
                scheduleButton
        );

        buttonPanel.add(
                viewButton
        );

        buttonPanel.add(
                cancelButton
        );

        buttonPanel.add(
                queueButton
        );

        buttonPanel.add(
                clearButton
        );

        buttonPanel.add(
                closeButton
        );


        // =====================================================
        // TOP PANEL
        // =====================================================

        JPanel topPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
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
                        "Interview Details"
                )
        );


        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );


        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        scheduleButton.addActionListener(
                e -> scheduleInterview()
        );


        viewButton.addActionListener(
                e -> viewApplication()
        );


        cancelButton.addActionListener(
                e -> cancelInterview()
        );


        queueButton.addActionListener(
                e -> viewQueue()
        );


        clearButton.addActionListener(
                e -> clearFields()
        );


        closeButton.addActionListener(
                e -> dispose()
        );


        add(mainPanel);
    }


    // =====================================================
    // SCHEDULE INTERVIEW
    // =====================================================

    private void scheduleInterview() {

        try {

            int applicationId =
                    Integer.parseInt(
                            applicationIdField
                                    .getText()
                                    .trim()
                    );


            String date =
                    dateField
                            .getText()
                            .trim();

            String time =
                    timeField
                            .getText()
                            .trim();

            String mode =
                    modeField
                            .getText()
                            .trim();

            String interviewer =
                    interviewerField
                            .getText()
                            .trim();


            if (date.isEmpty()
                    || time.isEmpty()
                    || mode.isEmpty()
                    || interviewer.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all interview fields.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            Application application =
                    system.findApplication(
                            applicationId
                    );


            if (application == null) {

                outputArea.setText(
                        "Application not found."
                );

                return;
            }


            boolean scheduled =
                    application.scheduleInterview(
                            date,
                            time,
                            mode,
                            interviewer
                    );


            if (scheduled) {

                outputArea.setText(
                        "INTERVIEW SCHEDULED SUCCESSFULLY!\n\n"
                        + "Application ID: "
                        + applicationId
                        + "\nStudent: "
                        + application
                                .getStudent()
                                .getName()
                        + "\nJob: "
                        + application
                                .getJob()
                                .getJobTitle()
                        + "\nDate: "
                        + date
                        + "\nTime: "
                        + time
                        + "\nMode: "
                        + mode
                        + "\nInterviewer: "
                        + interviewer
                );

            } else {

                outputArea.setText(
                        "Unable to schedule interview.\n"
                        + "Please check all details."
                );
            }


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Application ID must be a valid number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =====================================================
    // VIEW APPLICATION
    // =====================================================

    private void viewApplication() {

        try {

            int applicationId =
                    Integer.parseInt(
                            applicationIdField
                                    .getText()
                                    .trim()
                    );


            Application application =
                    system.findApplication(
                            applicationId
                    );


            if (application == null) {

                outputArea.setText(
                        "Application not found."
                );

                return;
            }


            StringBuilder result =
                    new StringBuilder();


            result.append(
                    "===== APPLICATION =====\n\n"
            );


            result.append(
                    "Application ID: "
                    + application.getApplicationId()
                    + "\n"
            );


            result.append(
                    "Student ID: "
                    + application
                            .getStudent()
                            .getUserId()
                    + "\n"
            );


            result.append(
                    "Student Name: "
                    + application
                            .getStudent()
                            .getName()
                    + "\n"
            );


            result.append(
                    "Job Title: "
                    + application
                            .getJob()
                            .getJobTitle()
                    + "\n"
            );


            result.append(
                    "Company: "
                    + (
                        application
                                .getJob()
                                .getCompany() != null
                        ?
                        application
                                .getJob()
                                .getCompany()
                                .getCompanyName()
                        :
                        "Not specified"
                    )
                    + "\n"
            );


            result.append(
                    "Status: "
                    + application.getStatus()
                    + "\n"
            );


            if (application.isInterviewScheduled()) {

                result.append(
                        "\n===== INTERVIEW DETAILS =====\n"
                );


                result.append(
                        "Date: "
                        + application
                                .getInterviewDate()
                        + "\n"
                );


                result.append(
                        "Time: "
                        + application
                                .getInterviewTime()
                        + "\n"
                );


                result.append(
                        "Mode: "
                        + application
                                .getInterviewMode()
                        + "\n"
                );


                result.append(
                        "Interviewer: "
                        + application
                                .getInterviewer()
                        + "\n"
                );

            } else {

                result.append(
                        "\nNo interview scheduled."
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
    // CANCEL INTERVIEW
    // =====================================================

    private void cancelInterview() {

        try {

            int applicationId =
                    Integer.parseInt(
                            applicationIdField
                                    .getText()
                                    .trim()
                    );


            Application application =
                    system.findApplication(
                            applicationId
                    );


            if (application == null) {

                outputArea.setText(
                        "Application not found."
                );

                return;
            }


            if (!application.isInterviewScheduled()) {

                outputArea.setText(
                        "No interview is currently scheduled "
                        + "for this application."
                );

                return;
            }


            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Cancel interview for Application ID "
                            + applicationId
                            + "?",
                            "Confirm Cancellation",
                            JOptionPane.YES_NO_OPTION
                    );


            if (choice ==
                    JOptionPane.YES_OPTION) {

                application.cancelInterview();


                outputArea.setText(
                        "Interview cancelled successfully.\n\n"
                        + "Application ID: "
                        + applicationId
                        + "\nNew Status: "
                        + application.getStatus()
                );
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
    // VIEW INTERVIEW QUEUE
    // =====================================================

    private void viewQueue() {

        Application[] applications =
                system.getAllApplications();


        if (applications == null
                || applications.length == 0) {

            outputArea.setText(
                    "No applications available."
            );

            return;
        }


        StringBuilder result =
                new StringBuilder();


        result.append(
                "===== INTERVIEW QUEUE =====\n\n"
        );


        int count = 0;


        for (Application application :
                applications) {

            if (application.isInterviewScheduled()) {

                count++;


                result.append(
                        "Application ID: "
                        + application
                                .getApplicationId()
                        + "\n"
                );


                result.append(
                        "Student: "
                        + application
                                .getStudent()
                                .getName()
                        + "\n"
                );


                result.append(
                        "Job: "
                        + application
                                .getJob()
                                .getJobTitle()
                        + "\n"
                );


                result.append(
                        "Date: "
                        + application
                                .getInterviewDate()
                        + "\n"
                );


                result.append(
                        "Time: "
                        + application
                                .getInterviewTime()
                        + "\n"
                );


                result.append(
                        "Mode: "
                        + application
                                .getInterviewMode()
                        + "\n"
                );


                result.append(
                        "Interviewer: "
                        + application
                                .getInterviewer()
                        + "\n"
                );


                result.append(
                        "\n-----------------------------\n\n"
                );
            }
        }


        if (count == 0) {

            result.append(
                    "No interviews are currently scheduled."
            );

        } else {

            result.append(
                    "\nTotal Scheduled Interviews: "
                    + count
            );
        }


        outputArea.setText(
                result.toString()
        );
    }


    // =====================================================
    // CLEAR FIELDS
    // =====================================================

    private void clearFields() {

        applicationIdField.setText("");
        dateField.setText("");
        timeField.setText("");
        modeField.setText("");
        interviewerField.setText("");

        outputArea.setText("");
    }
}
