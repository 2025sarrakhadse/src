import javax.swing.*;
import java.awt.*;

public class SearchGUI extends JFrame {

    private PlacementSystem system;

    private JTextField searchField;
    private JComboBox<String> searchType;
    private JTextArea outputArea;

    public SearchGUI(PlacementSystem system) {

        this.system = system;

        setTitle("Placement Management System - Search");
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
                        "SEARCH MANAGEMENT SYSTEM",
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
        // SEARCH PANEL
        // =====================================================

        JPanel searchPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                10,
                                10
                        )
                );

        searchPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Search"
                )
        );


        searchType =
                new JComboBox<>(
                        new String[]{
                                "Student by ID",
                                "Job by ID",
                                "Application by ID"
                        }
                );


        searchField =
                new JTextField();


        JButton searchButton =
                new JButton("Search");


        searchPanel.add(
                new JLabel("Search Type:")
        );

        searchPanel.add(
                searchType
        );


        searchPanel.add(
                new JLabel("ID:")
        );

        searchPanel.add(
                searchField
        );


        // =====================================================
        // BUTTON PANEL
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER
                        )
                );

        buttonPanel.add(
                searchButton
        );


        JButton clearButton =
                new JButton("Clear");

        JButton closeButton =
                new JButton("Close");


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
                        new BorderLayout(10, 10)
                );

        topPanel.add(
                searchPanel,
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
                        "Search Results"
                )
        );


        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );


        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        searchButton.addActionListener(
                e -> performSearch()
        );


        clearButton.addActionListener(
                e -> {

                    searchField.setText("");
                    outputArea.setText("");

                }
        );


        closeButton.addActionListener(
                e -> dispose()
        );


        add(mainPanel);
    }


    // =====================================================
    // PERFORM SEARCH
    // =====================================================

    private void performSearch() {

        String text =
                searchField
                        .getText()
                        .trim();


        if (text.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter an ID.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        int id;


        try {

            id = Integer.parseInt(text);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "ID must be a valid number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        String selectedType =
                (String) searchType.getSelectedItem();


        if (selectedType.equals(
                "Student by ID")) {

            searchStudent(id);

        } else if (selectedType.equals(
                "Job by ID")) {

            searchJob(id);

        } else if (selectedType.equals(
                "Application by ID")) {

            searchApplication(id);
        }
    }


    // =====================================================
    // SEARCH STUDENT
    // =====================================================

    private void searchStudent(int id) {

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
                "Phone: "
                + student.getPhoneNumber()
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


        outputArea.setText(
                result.toString()
        );
    }


    // =====================================================
    // SEARCH JOB
    // =====================================================

    private void searchJob(int id) {

        Job job =
                system.findJob(id);


        if (job == null) {

            outputArea.setText(
                    "Job not found."
            );

            return;
        }


        StringBuilder result =
                new StringBuilder();


        result.append(
                "===== JOB DETAILS =====\n\n"
        );


        result.append(
                "Job ID: "
                + job.getJobId()
                + "\n"
        );


        result.append(
                "Job Title: "
                + job.getJobTitle()
                + "\n"
        );


        result.append(
                "Company: "
                + (
                    job.getCompany() != null
                    ?
                    job.getCompany().getCompanyName()
                    :
                    "Not specified"
                )
                + "\n"
        );


        result.append(
                "Salary: "
                + job.getSalary()
                + " LPA\n"
        );


        result.append(
                "Minimum CGPA: "
                + job.getMinimumCGPA()
                + "\n"
        );


        result.append(
                "Required Skill: "
                + job.getRequiredSkill()
                + "\n"
        );


        result.append(
                "Location: "
                + job.getLocation()
                + "\n"
        );


        result.append(
                "Job Type: "
                + job.getJobType()
                + "\n"
        );


        result.append(
                "Application Deadline: "
                + job.getApplicationDeadline()
                + "\n"
        );


        outputArea.setText(
                result.toString()
        );
    }


    // =====================================================
    // SEARCH APPLICATION
    // =====================================================

    private void searchApplication(int id) {

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
                "Job ID: "
                + application
                        .getJob()
                        .getJobId()
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
                "Application Date: "
                + application
                        .getApplicationDate()
                + "\n"
        );


        result.append(
                "Status: "
                + application
                        .getStatus()
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
        }


        outputArea.setText(
                result.toString()
        );
    }
}
