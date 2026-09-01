import javax.swing.*;
import java.awt.*;

public class JobGUI extends JFrame {

    private PlacementSystem system;

    private JTextField jobIdField;
    private JTextField titleField;
    private JTextField salaryField;
    private JTextField cgpaField;
    private JTextField skillField;
    private JTextField locationField;
    private JTextField typeField;
    private JTextField deadlineField;
    private JTextField companyIdField;

    private JTextArea outputArea;

    public JobGUI(PlacementSystem system) {

        this.system = system;

        setTitle("Job Management");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel =
                new JPanel(new BorderLayout(10, 10));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15
                )
        );

        // =========================================
        // TITLE
        // =========================================

        JLabel titleLabel =
                new JLabel(
                        "JOB MANAGEMENT",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );


        // =========================================
        // INPUT PANEL
        // =========================================

        JPanel inputPanel =
                new JPanel(
                        new GridLayout(9, 2, 10, 10)
                );

        inputPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Job Information"
                )
        );

        jobIdField = new JTextField();
        titleField = new JTextField();
        salaryField = new JTextField();
        cgpaField = new JTextField();
        skillField = new JTextField();
        locationField = new JTextField();
        typeField = new JTextField();
        deadlineField = new JTextField();
        companyIdField = new JTextField();

        inputPanel.add(
                new JLabel("Job ID:")
        );
        inputPanel.add(jobIdField);

        inputPanel.add(
                new JLabel("Job Title:")
        );
        inputPanel.add(titleField);

        inputPanel.add(
                new JLabel("Salary (LPA):")
        );
        inputPanel.add(salaryField);

        inputPanel.add(
                new JLabel("Minimum CGPA:")
        );
        inputPanel.add(cgpaField);

        inputPanel.add(
                new JLabel("Required Skill:")
        );
        inputPanel.add(skillField);

        inputPanel.add(
                new JLabel("Location:")
        );
        inputPanel.add(locationField);

        inputPanel.add(
                new JLabel("Job Type:")
        );
        inputPanel.add(typeField);

        inputPanel.add(
                new JLabel("Application Deadline:")
        );
        inputPanel.add(deadlineField);

        inputPanel.add(
                new JLabel("Company ID:")
        );
        inputPanel.add(companyIdField);


        // =========================================
        // BUTTON PANEL
        // =========================================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(1, 4, 10, 10)
                );

        JButton addButton =
                new JButton("Add Job");

        JButton viewButton =
                new JButton("View Jobs");

        JButton searchButton =
                new JButton("Search Job");

        JButton clearButton =
                new JButton("Clear");

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(clearButton);


        // =========================================
        // OUTPUT AREA
        // =========================================

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
                new JScrollPane(outputArea);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Job Details"
                )
        );


        // =========================================
        // CENTER PANEL
        // =========================================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

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
        // BUTTON ACTIONS
        // =========================================

        addButton.addActionListener(
                e -> addJob()
        );

        viewButton.addActionListener(
                e -> viewJobs()
        );

        searchButton.addActionListener(
                e -> searchJob()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );


        add(mainPanel);
    }


    // =====================================================
    // ADD JOB
    // =====================================================

    private void addJob() {

        try {

            int jobId =
                    Integer.parseInt(
                            jobIdField
                                    .getText()
                                    .trim()
                    );

            String title =
                    titleField
                            .getText()
                            .trim();

            double salary =
                    Double.parseDouble(
                            salaryField
                                    .getText()
                                    .trim()
                    );

            double minimumCGPA =
                    Double.parseDouble(
                            cgpaField
                                    .getText()
                                    .trim()
                    );

            String requiredSkill =
                    skillField
                            .getText()
                            .trim();

            String location =
                    locationField
                            .getText()
                            .trim();

            String jobType =
                    typeField
                            .getText()
                            .trim();

            String deadline =
                    deadlineField
                            .getText()
                            .trim();

            int companyId =
                    Integer.parseInt(
                            companyIdField
                                    .getText()
                                    .trim()
                    );


            // =====================================
            // VALIDATION
            // =====================================

            if (title.isEmpty()
                    || requiredSkill.isEmpty()
                    || location.isEmpty()
                    || jobType.isEmpty()
                    || deadline.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all fields."
                );

                return;
            }


            // =====================================
            // FIND COMPANY
            // =====================================

            Company company =
                    system.findCompany(companyId);


            if (company == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Company not found. Enter a valid Company ID."
                );

                return;
            }


            // =====================================
            // CREATE JOB
            // =====================================

            Job job =
                    new Job(
                            jobId,
                            title,
                            salary,
                            minimumCGPA,
                            requiredSkill,
                            location,
                            jobType,
                            deadline,
                            company
                    );


            // =====================================
            // ADD JOB TO COMPANY
            // =====================================

            company.addJob(job);


            // =====================================
            // ADD JOB TO SYSTEM
            // =====================================

            system.addJob(job);


            outputArea.setText(
                    "Job added successfully!\n\n"
                    + "Job ID: "
                    + jobId
                    + "\nJob Title: "
                    + title
                    + "\nCompany: "
                    + company.getCompanyName()
                    + "\nSalary: "
                    + salary
                    + " LPA"
                    + "\nMinimum CGPA: "
                    + minimumCGPA
                    + "\nRequired Skill: "
                    + requiredSkill
                    + "\nLocation: "
                    + location
                    + "\nJob Type: "
                    + jobType
                    + "\nDeadline: "
                    + deadline
            );


            JOptionPane.showMessageDialog(
                    this,
                    "Job added successfully!"
            );


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Job ID, Company ID, Salary and CGPA must be valid numbers."
            );
        }
    }


    // =====================================================
    // VIEW ALL JOBS
    // =====================================================

    private void viewJobs() {

        Job[] jobs =
                system.getAllJobs();


        if (jobs == null
                || jobs.length == 0) {

            outputArea.setText(
                    "No jobs available."
            );

            return;
        }


        StringBuilder result =
                new StringBuilder();


        result.append(
                "===== AVAILABLE JOBS =====\n\n"
        );


        for (Job job : jobs) {

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
                        ? job.getCompany()
                                .getCompanyName()
                        : "Not specified"
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

            result.append(
                    "--------------------------------\n"
            );
        }


        outputArea.setText(
                result.toString()
        );
    }


    // =====================================================
    // SEARCH JOB
    // =====================================================

    private void searchJob() {

        try {

            int jobId =
                    Integer.parseInt(
                            jobIdField
                                    .getText()
                                    .trim()
                    );


            Job job =
                    system.findJob(jobId);


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
                        ? job.getCompany()
                                .getCompanyName()
                        : "Not specified"
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


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid Job ID."
            );
        }
    }


    // =====================================================
    // CLEAR FIELDS
    // =====================================================

    private void clearFields() {

        jobIdField.setText("");
        titleField.setText("");
        salaryField.setText("");
        cgpaField.setText("");
        skillField.setText("");
        locationField.setText("");
        typeField.setText("");
        deadlineField.setText("");
        companyIdField.setText("");

        outputArea.setText("");
    }
}
