import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Modern Application Management View.
 * Backed by custom Singly Linked List (ApplicationLinkedList).
 * Handles job applications, status updates, and eligibility enforcement.
 */
public class ApplicationView extends JPanel {

    private PlacementSystem system;

    private JTextField studentIdField, jobIdField, appIdField;
    private JComboBox<String> statusCombo;
    private JTable appTable;
    private DefaultTableModel tableModel;

    public ApplicationView(PlacementSystem system) {
        this.system = system;

        setLayout(new BorderLayout(20, 20));
        setBackground(UITheme.COLOR_BG);
        setBorder(new EmptyBorder(24, 24, 24, 24));

        // Header
        JPanel headerPanel = UITheme.createHeaderPanel(
                "APPLICATION MANAGEMENT",
                "Dynamic application lifecycle tracking backed by custom ApplicationLinkedList"
        );
        add(headerPanel, BorderLayout.NORTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createFormCard(), createTableCard());
        splitPane.setDividerLocation(360);
        splitPane.setOpaque(false);
        splitPane.setBorder(null);

        add(splitPane, BorderLayout.CENTER);

        refreshTable();
    }

    private JPanel createFormCard() {
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new BorderLayout(0, 16));

        JLabel title = new JLabel("Apply & Update Status");
        title.setFont(UITheme.FONT_HEADER);
        title.setForeground(UITheme.COLOR_TEXT_DARK);
        card.add(title, BorderLayout.NORTH);

        JPanel mainForm = new JPanel(new GridLayout(2, 1, 0, 16));
        mainForm.setOpaque(false);

        // Section 1: Apply for Job
        JPanel applySection = new JPanel(new BorderLayout(0, 8));
        applySection.setOpaque(false);
        applySection.setBorder(BorderFactory.createTitledBorder("Submit New Application"));

        JPanel applyGrid = new JPanel(new GridLayout(2, 2, 8, 8));
        applyGrid.setOpaque(false);

        studentIdField = UITheme.createStyledTextField();
        jobIdField = UITheme.createStyledTextField();

        applyGrid.add(createFormLabel("Student ID:"));
        applyGrid.add(studentIdField);
        applyGrid.add(createFormLabel("Job ID:"));
        applyGrid.add(jobIdField);

        JButton applyBtn = UITheme.createPrimaryButton("Submit Application");
        applyBtn.addActionListener(e -> applyForJob());

        applySection.add(applyGrid, BorderLayout.CENTER);
        applySection.add(applyBtn, BorderLayout.SOUTH);

        // Section 2: Update Application Status
        JPanel statusSection = new JPanel(new BorderLayout(0, 8));
        statusSection.setOpaque(false);
        statusSection.setBorder(BorderFactory.createTitledBorder("Manage Application Status"));

        JPanel statusGrid = new JPanel(new GridLayout(2, 2, 8, 8));
        statusGrid.setOpaque(false);

        appIdField = UITheme.createStyledTextField();
        statusCombo = UITheme.createStyledComboBox(new String[]{
                "Applied", "Shortlisted", "Interview Scheduled", "Selected", "Rejected"
        });

        statusGrid.add(createFormLabel("Application ID:"));
        statusGrid.add(appIdField);
        statusGrid.add(createFormLabel("New Status:"));
        statusGrid.add(statusCombo);

        JPanel statusBtnGrid = new JPanel(new GridLayout(1, 2, 8, 8));
        statusBtnGrid.setOpaque(false);

        JButton updateBtn = UITheme.createSuccessButton("Update Status");
        JButton deleteBtn = UITheme.createDangerButton("Delete App");

        updateBtn.addActionListener(e -> updateStatus());
        deleteBtn.addActionListener(e -> deleteApplication());

        statusBtnGrid.add(updateBtn);
        statusBtnGrid.add(deleteBtn);

        statusSection.add(statusGrid, BorderLayout.CENTER);
        statusSection.add(statusBtnGrid, BorderLayout.SOUTH);

        mainForm.add(applySection);
        mainForm.add(statusSection);

        card.add(mainForm, BorderLayout.CENTER);
        return card;
    }

    private JPanel createTableCard() {
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new BorderLayout(0, 12));

        JLabel title = new JLabel("Application Records (LinkedList View)");
        title.setFont(UITheme.FONT_HEADER);
        title.setForeground(UITheme.COLOR_TEXT_DARK);
        card.add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new Object[]{"App ID", "Student Name", "Job Title", "Company", "Status", "Date"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };

        appTable = new JTable(tableModel);
        UITheme.styleTable(appTable);

        appTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && appTable.getSelectedRow() != -1) {
                int row = appTable.getSelectedRow();
                appIdField.setText(tableModel.getValueAt(row, 0).toString());
                String status = tableModel.getValueAt(row, 4).toString();
                statusCombo.setSelectedItem(status);
            }
        });

        JScrollPane scrollPane = new JScrollPane(appTable);
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

    private void applyForJob() {
        try {
            int studentId = Integer.parseInt(studentIdField.getText().trim());
            int jobId = Integer.parseInt(jobIdField.getText().trim());

            String checkStatus = system.checkApplicationSubmissionStatus(studentId, jobId);
            if (!checkStatus.equals("OK")) {
                showError(checkStatus);
                return;
            }

            Application app = system.applyForJob(studentId, jobId);

            if (app != null) {
                String cName = (app.getJob() != null && app.getJob().getCompany() != null) ? app.getJob().getCompany().getCompanyName() : "N/A";
                JOptionPane.showMessageDialog(
                        this,
                        "Application Submitted Successfully!\n\n" +
                        "Application ID: #" + app.getApplicationId() + "\n" +
                        "Candidate: " + app.getStudent().getName() + " (ID: " + studentId + ")\n" +
                        "Job Role: " + app.getJob().getJobTitle() + " (ID: " + jobId + ")\n" +
                        "Company: " + cName + "\n" +
                        "Status: " + app.getStatus() + "\n" +
                        "Application Date: " + app.getApplicationDate(),
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
                studentIdField.setText("");
                jobIdField.setText("");
                refreshTable();
            } else {
                showError("Application Failed: Unexpected internal error while persisting application.");
            }
        } catch (NumberFormatException ex) {
            showError("Student ID and Job ID must be valid integers.");
        }
    }

    private void updateStatus() {
        try {
            int appId = Integer.parseInt(appIdField.getText().trim());
            String newStatus = (String) statusCombo.getSelectedItem();

            boolean updated = system.updateApplicationStatus(appId, newStatus);
            if (updated) {
                JOptionPane.showMessageDialog(this, "Application status updated to " + newStatus, "Success", JOptionPane.INFORMATION_MESSAGE);
                refreshTable();
            } else {
                showError("Application ID #" + appId + " not found.");
            }
        } catch (NumberFormatException ex) {
            showError("Enter a valid numerical Application ID.");
        }
    }

    private void deleteApplication() {
        try {
            int appId = Integer.parseInt(appIdField.getText().trim());
            boolean deleted = system.deleteApplication(appId);
            if (deleted) {
                JOptionPane.showMessageDialog(this, "Application removed from LinkedList.", "Success", JOptionPane.INFORMATION_MESSAGE);
                appIdField.setText("");
                refreshTable();
            } else {
                showError("Application ID not found.");
            }
        } catch (NumberFormatException ex) {
            showError("Enter a valid numerical Application ID.");
        }
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        Application[] apps = system.getAllApplications();
        if (apps != null) {
            for (Application a : apps) {
                if (a != null) {
                    String sName = a.getStudent() != null ? a.getStudent().getName() : "N/A";
                    String jTitle = a.getJob() != null ? a.getJob().getJobTitle() : "N/A";
                    String cName = (a.getJob() != null && a.getJob().getCompany() != null) ? a.getJob().getCompany().getCompanyName() : "N/A";

                    tableModel.addRow(new Object[]{
                            a.getApplicationId(), sName, jTitle, cName, a.getStatus(), a.getApplicationDate()
                    });
                }
            }
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Validation Error", JOptionPane.ERROR_MESSAGE);
    }
}
