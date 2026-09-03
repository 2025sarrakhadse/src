import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Modern Job Openings Management View.
 * Displays job roles posted by companies, eligibility criteria, and quick eligibility check.
 */
public class JobView extends JPanel {

    private PlacementSystem system;

    private JTextField companyIdField, jobIdField, titleField, salaryField;
    private JTextField minCgpaField, skillField, locationField, typeField, deadlineField;
    private JTable jobTable;
    private DefaultTableModel tableModel;

    // Eligibility Checker Fields
    private JTextField testStudentIdField, testJobIdField;
    private JLabel eligibilityResultLabel;

    public JobView(PlacementSystem system) {
        this.system = system;

        setLayout(new BorderLayout(20, 20));
        setBackground(UITheme.COLOR_BG);
        setBorder(new EmptyBorder(24, 24, 24, 24));

        // Header
        JPanel headerPanel = UITheme.createHeaderPanel(
                "JOB OPENINGS & REQUIREMENTS",
                "Post new job openings and verify student eligibility criteria"
        );
        add(headerPanel, BorderLayout.NORTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createFormCard(), createRightContainer());
        splitPane.setDividerLocation(380);
        splitPane.setOpaque(false);
        splitPane.setBorder(null);

        add(splitPane, BorderLayout.CENTER);

        refreshTable();
    }

    private JPanel createFormCard() {
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new BorderLayout(0, 16));

        JLabel title = new JLabel("Post New Job Opportunity");
        title.setFont(UITheme.FONT_HEADER);
        title.setForeground(UITheme.COLOR_TEXT_DARK);
        card.add(title, BorderLayout.NORTH);

        JPanel formGrid = new JPanel(new GridLayout(9, 2, 8, 10));
        formGrid.setOpaque(false);

        companyIdField = UITheme.createStyledTextField();
        jobIdField = UITheme.createStyledTextField();
        titleField = UITheme.createStyledTextField();
        salaryField = UITheme.createStyledTextField();
        minCgpaField = UITheme.createStyledTextField();
        skillField = UITheme.createStyledTextField();
        locationField = UITheme.createStyledTextField();
        typeField = UITheme.createStyledTextField();
        deadlineField = UITheme.createStyledTextField();

        formGrid.add(createFormLabel("Company ID:"));
        formGrid.add(companyIdField);
        formGrid.add(createFormLabel("Job ID:"));
        formGrid.add(jobIdField);
        formGrid.add(createFormLabel("Job Title:"));
        formGrid.add(titleField);
        formGrid.add(createFormLabel("Salary (LPA):"));
        formGrid.add(salaryField);
        formGrid.add(createFormLabel("Min CGPA:"));
        formGrid.add(minCgpaField);
        formGrid.add(createFormLabel("Required Skill:"));
        formGrid.add(skillField);
        formGrid.add(createFormLabel("Location:"));
        formGrid.add(locationField);
        formGrid.add(createFormLabel("Job Type:"));
        formGrid.add(typeField);
        formGrid.add(createFormLabel("Deadline:"));
        formGrid.add(deadlineField);

        card.add(formGrid, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new GridLayout(1, 3, 8, 8));
        btnPanel.setOpaque(false);

        JButton addBtn = UITheme.createPrimaryButton("Post Job");
        JButton deleteBtn = UITheme.createDangerButton("Delete");
        JButton clearBtn = UITheme.createSecondaryButton("Clear");

        addBtn.addActionListener(e -> addJob());
        deleteBtn.addActionListener(e -> deleteJob());
        clearBtn.addActionListener(e -> clearForm());

        btnPanel.add(addBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(clearBtn);

        card.add(btnPanel, BorderLayout.SOUTH);
        return card;
    }

    private JPanel createRightContainer() {
        JPanel container = new JPanel(new BorderLayout(0, 16));
        container.setOpaque(false);

        container.add(createTableCard(), BorderLayout.CENTER);
        container.add(createEligibilityCheckerCard(), BorderLayout.SOUTH);

        return container;
    }

    private JPanel createTableCard() {
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new BorderLayout(0, 12));

        JLabel title = new JLabel("Active Job Listings");
        title.setFont(UITheme.FONT_HEADER);
        title.setForeground(UITheme.COLOR_TEXT_DARK);
        card.add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new Object[]{"Job ID", "Company", "Title", "Salary", "Min CGPA", "Skill", "Location", "Deadline"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };

        jobTable = new JTable(tableModel);
        UITheme.styleTable(jobTable);

        jobTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && jobTable.getSelectedRow() != -1) {
                int row = jobTable.getSelectedRow();
                jobIdField.setText(tableModel.getValueAt(row, 0).toString());
                titleField.setText(tableModel.getValueAt(row, 2).toString());
                salaryField.setText(tableModel.getValueAt(row, 3).toString().replace(" LPA", ""));
                minCgpaField.setText(tableModel.getValueAt(row, 4).toString());
                skillField.setText(tableModel.getValueAt(row, 5).toString());
                locationField.setText(tableModel.getValueAt(row, 6).toString());
                deadlineField.setText(tableModel.getValueAt(row, 7).toString());
                testJobIdField.setText(tableModel.getValueAt(row, 0).toString());
            }
        });

        JScrollPane scrollPane = new JScrollPane(jobTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(UITheme.COLOR_BORDER));

        card.add(scrollPane, BorderLayout.CENTER);
        return card;
    }

    private JPanel createEligibilityCheckerCard() {
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new FlowLayout(FlowLayout.LEFT, 16, 10));

        JLabel title = new JLabel("Check Eligibility:");
        title.setFont(UITheme.FONT_BOLD);
        title.setForeground(UITheme.COLOR_TEXT_DARK);

        testStudentIdField = UITheme.createStyledTextField();
        testStudentIdField.setPreferredSize(new Dimension(80, 30));

        testJobIdField = UITheme.createStyledTextField();
        testJobIdField.setPreferredSize(new Dimension(80, 30));

        JButton checkBtn = UITheme.createSecondaryButton("Verify");

        eligibilityResultLabel = new JLabel("Select Student & Job to verify");
        eligibilityResultLabel.setFont(UITheme.FONT_BOLD);
        eligibilityResultLabel.setForeground(UITheme.COLOR_TEXT_MUTED);

        checkBtn.addActionListener(e -> verifyEligibility());

        card.add(title);
        card.add(new JLabel("Student ID:"));
        card.add(testStudentIdField);
        card.add(new JLabel("Job ID:"));
        card.add(testJobIdField);
        card.add(checkBtn);
        card.add(eligibilityResultLabel);

        return card;
    }

    private JLabel createFormLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(UITheme.FONT_BOLD);
        label.setForeground(UITheme.COLOR_TEXT_MUTED);
        return label;
    }

    private void addJob() {
        try {
            int companyId = Integer.parseInt(companyIdField.getText().trim());
            int jobId = Integer.parseInt(jobIdField.getText().trim());
            String title = titleField.getText().trim();
            double salary = Double.parseDouble(salaryField.getText().trim());
            double minCgpa = Double.parseDouble(minCgpaField.getText().trim());
            String skill = skillField.getText().trim();
            String location = locationField.getText().trim();
            String type = typeField.getText().trim();
            String deadline = deadlineField.getText().trim();

            if (!ValidationUtil.isValidName(title)) {
                showError("Job Title cannot be empty.");
                return;
            }
            if (!ValidationUtil.isValidCGPA(minCgpa)) {
                showError("Minimum CGPA must be between 0.0 and 10.0.");
                return;
            }

            Company company = system.findCompany(companyId);
            if (company == null) {
                showError("Company ID " + companyId + " not found. Add company first!");
                return;
            }

            Job job = new Job(jobId, title, salary, minCgpa, skill, location, type, deadline, company);
            boolean added = system.addJob(companyId, job);

            if (added) {
                JOptionPane.showMessageDialog(this, "Job posted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                refreshTable();
            } else {
                showError("Unable to add job. Ensure Job ID is unique.");
            }
        } catch (NumberFormatException ex) {
            showError("Please enter valid numeric values for IDs, Salary, and Min CGPA.");
        }
    }

    private void deleteJob() {
        try {
            int jobId = Integer.parseInt(jobIdField.getText().trim());
            boolean deleted = system.deleteJob(jobId);
            if (deleted) {
                JOptionPane.showMessageDialog(this, "Job deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                refreshTable();
            } else {
                showError("Job ID not found.");
            }
        } catch (NumberFormatException ex) {
            showError("Enter a valid numerical Job ID.");
        }
    }

    private void verifyEligibility() {
        try {
            int sId = Integer.parseInt(testStudentIdField.getText().trim());
            int jId = Integer.parseInt(testJobIdField.getText().trim());

            Student s = system.findStudent(sId);
            Job j = system.findJob(jId);

            if (s == null) {
                eligibilityResultLabel.setText("Student #" + sId + " Not Found");
                eligibilityResultLabel.setForeground(UITheme.COLOR_DANGER);
                return;
            }
            if (j == null) {
                eligibilityResultLabel.setText("Job #" + jId + " Not Found");
                eligibilityResultLabel.setForeground(UITheme.COLOR_DANGER);
                return;
            }

            boolean eligible = j.isStudentEligible(s);
            String reason = j.getEligibilityReason(s);
            eligibilityResultLabel.setText(reason);

            if (eligible) {
                eligibilityResultLabel.setForeground(UITheme.COLOR_SUCCESS);
            } else {
                eligibilityResultLabel.setForeground(UITheme.COLOR_DANGER);
            }
        } catch (NumberFormatException ex) {
            eligibilityResultLabel.setText("Enter valid IDs");
            eligibilityResultLabel.setForeground(UITheme.COLOR_DANGER);
        }
    }

    private void clearForm() {
        companyIdField.setText("");
        jobIdField.setText("");
        titleField.setText("");
        salaryField.setText("");
        minCgpaField.setText("");
        skillField.setText("");
        locationField.setText("");
        typeField.setText("");
        deadlineField.setText("");
        jobTable.clearSelection();
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        Job[] jobs = system.getAllJobs();
        if (jobs != null) {
            for (Job j : jobs) {
                if (j != null) {
                    String compName = j.getCompany() != null ? j.getCompany().getCompanyName() : "N/A";
                    tableModel.addRow(new Object[]{
                            j.getJobId(), compName, j.getJobTitle(), j.getSalaryLPA() + " LPA",
                            j.getMinimumCGPA(), j.getRequiredSkill(), j.getLocation(), j.getApplicationDeadline()
                    });
                }
            }
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Validation Error", JOptionPane.ERROR_MESSAGE);
    }
}
