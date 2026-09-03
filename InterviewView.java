import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Modern Interview Management View.
 * Uses FIFO Queue (InterviewQueue) to manage candidate interview scheduling and processing order.
 */
public class InterviewView extends JPanel {

    private PlacementSystem system;

    private JTextField appIdField, dateField, timeField, modeField, interviewerField;
    private JTable queueTable;
    private DefaultTableModel tableModel;
    private JLabel nextCandidateLabel;

    public InterviewView(PlacementSystem system) {
        this.system = system;

        setLayout(new BorderLayout(20, 20));
        setBackground(UITheme.COLOR_BG);
        setBorder(new EmptyBorder(24, 24, 24, 24));

        // Header
        JPanel headerPanel = UITheme.createHeaderPanel(
                "INTERVIEW SCHEDULE & QUEUE",
                "First-In-First-Out (FIFO) queue slot management for conducting candidate interviews"
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

        JLabel title = new JLabel("Schedule Interview Slot");
        title.setFont(UITheme.FONT_HEADER);
        title.setForeground(UITheme.COLOR_TEXT_DARK);
        card.add(title, BorderLayout.NORTH);

        JPanel formGrid = new JPanel(new GridLayout(5, 2, 8, 12));
        formGrid.setOpaque(false);

        appIdField = UITheme.createStyledTextField();
        dateField = UITheme.createStyledTextField();
        timeField = UITheme.createStyledTextField();
        modeField = UITheme.createStyledTextField();
        interviewerField = UITheme.createStyledTextField();

        // Defaults
        dateField.setText("2026-09-10");
        timeField.setText("10:00 AM");
        modeField.setText("Online (Google Meet)");
        interviewerField.setText("HR Team");

        formGrid.add(createFormLabel("Application ID:"));
        formGrid.add(appIdField);
        formGrid.add(createFormLabel("Interview Date:"));
        formGrid.add(dateField);
        formGrid.add(createFormLabel("Interview Time:"));
        formGrid.add(timeField);
        formGrid.add(createFormLabel("Interview Mode:"));
        formGrid.add(modeField);
        formGrid.add(createFormLabel("Interviewer:"));
        formGrid.add(interviewerField);

        card.add(formGrid, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new GridLayout(2, 2, 8, 8));
        btnPanel.setOpaque(false);

        JButton scheduleBtn = UITheme.createPrimaryButton("Enqueue Interview");
        JButton cancelBtn = UITheme.createDangerButton("Cancel Interview");
        JButton processBtn = UITheme.createSuccessButton("Process (Dequeue)");
        JButton peekBtn = UITheme.createSecondaryButton("Peek Next");

        scheduleBtn.addActionListener(e -> scheduleInterview());
        cancelBtn.addActionListener(e -> cancelInterview());
        processBtn.addActionListener(e -> processNext());
        peekBtn.addActionListener(e -> peekNext());

        btnPanel.add(scheduleBtn);
        btnPanel.add(processBtn);
        btnPanel.add(peekBtn);
        btnPanel.add(cancelBtn);

        card.add(btnPanel, BorderLayout.SOUTH);
        return card;
    }

    private JPanel createRightContainer() {
        JPanel container = new JPanel(new BorderLayout(0, 16));
        container.setOpaque(false);

        container.add(createQueueTableCard(), BorderLayout.CENTER);
        container.add(createCandidateBannerCard(), BorderLayout.SOUTH);

        return container;
    }

    private JPanel createQueueTableCard() {
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new BorderLayout(0, 12));

        JLabel title = new JLabel("Interview Queue Slots (FIFO Front to Rear)");
        title.setFont(UITheme.FONT_HEADER);
        title.setForeground(UITheme.COLOR_TEXT_DARK);
        card.add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new Object[]{"Queue Position", "App ID", "Student Name", "Job Role", "Date/Time", "Mode", "Interviewer"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };

        queueTable = new JTable(tableModel);
        UITheme.styleTable(queueTable);

        JScrollPane scrollPane = new JScrollPane(queueTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(UITheme.COLOR_BORDER));

        card.add(scrollPane, BorderLayout.CENTER);
        return card;
    }

    private JPanel createCandidateBannerCard() {
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new BorderLayout(10, 8));

        JLabel header = new JLabel("CURRENT FIFO FRONT CANDIDATE:");
        header.setFont(UITheme.FONT_SMALL);
        header.setForeground(UITheme.COLOR_TEXT_MUTED);

        nextCandidateLabel = new JLabel("No interview scheduled in queue.");
        nextCandidateLabel.setFont(UITheme.FONT_HEADER);
        nextCandidateLabel.setForeground(UITheme.COLOR_ACCENT);

        card.add(header, BorderLayout.NORTH);
        card.add(nextCandidateLabel, BorderLayout.CENTER);

        return card;
    }

    private JLabel createFormLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(UITheme.FONT_BOLD);
        label.setForeground(UITheme.COLOR_TEXT_MUTED);
        return label;
    }

    private void scheduleInterview() {
        try {
            int appId = Integer.parseInt(appIdField.getText().trim());
            String date = dateField.getText().trim();
            String time = timeField.getText().trim();
            String mode = modeField.getText().trim();
            String interviewer = interviewerField.getText().trim();

            if (date.isEmpty() || time.isEmpty() || mode.isEmpty() || interviewer.isEmpty()) {
                showError("Please fill all interview details.");
                return;
            }

            boolean scheduled = system.scheduleInterview(appId, date, time, mode, interviewer);
            if (scheduled) {
                system.addToInterviewQueue(appId);
                JOptionPane.showMessageDialog(this, "Interview scheduled and added to FIFO Queue!", "Success", JOptionPane.INFORMATION_MESSAGE);
                appIdField.setText("");
                refreshTable();
            } else {
                showError("Unable to schedule interview. Verify Application ID.");
            }
        } catch (NumberFormatException ex) {
            showError("Application ID must be a valid number.");
        }
    }

    private void processNext() {
        if (system.isInterviewQueueEmpty()) {
            showError("Interview queue is empty.");
            return;
        }

        Application processed = system.processNextInterview();
        if (processed != null) {
            processed.setStatus("Interview Processed");
            String studentName = processed.getStudent() != null ? processed.getStudent().getName() : "Candidate";
            JOptionPane.showMessageDialog(this, "Interview processed and dequeued for candidate: " + studentName, "Processed", JOptionPane.INFORMATION_MESSAGE);
            refreshTable();
        }
    }

    private void peekNext() {
        Application next = system.getNextInterview();
        if (next != null) {
            String studentName = next.getStudent() != null ? next.getStudent().getName() : "Unknown";
            String jobTitle = next.getJob() != null ? next.getJob().getJobTitle() : "Unknown Job";
            nextCandidateLabel.setText(studentName + " - " + jobTitle + " (App #" + next.getApplicationId() + ")");
        } else {
            nextCandidateLabel.setText("No candidates waiting in FIFO Queue.");
        }
    }

    private void cancelInterview() {
        try {
            int appId = Integer.parseInt(appIdField.getText().trim());
            boolean cancelled = system.cancelInterview(appId);
            if (cancelled) {
                JOptionPane.showMessageDialog(this, "Interview cancelled for App #" + appId, "Cancelled", JOptionPane.INFORMATION_MESSAGE);
                refreshTable();
            } else {
                showError("Application ID not found.");
            }
        } catch (NumberFormatException ex) {
            showError("Enter a valid Application ID.");
        }
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        peekNext();

        // Populate table from all applications with interviews scheduled
        int pos = 1;
        Application[] apps = system.getAllApplications();
        if (apps != null) {
            for (Application a : apps) {
                if (a != null && a.getInterviewDate() != null && !a.getInterviewDate().isEmpty()) {
                    String sName = a.getStudent() != null ? a.getStudent().getName() : "N/A";
                    String jTitle = a.getJob() != null ? a.getJob().getJobTitle() : "N/A";
                    String dateTime = a.getInterviewDate() + " " + (a.getInterviewTime() != null ? a.getInterviewTime() : "");
                    tableModel.addRow(new Object[]{
                            "Slot #" + pos++, a.getApplicationId(), sName, jTitle, dateTime, a.getInterviewMode(), a.getInterviewerName()
                    });
                }
            }
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Queue Error", JOptionPane.ERROR_MESSAGE);
    }
}
