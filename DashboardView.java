import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Modern Dashboard View - Unified Hub displaying key stats, quick shortcuts,
 * and recent system activities.
 */
public class DashboardView extends JPanel {

    private PlacementSystem system;
    private JPanel statGrid;
    private JTable recentActionsTable;
    private DefaultTableModel actionsTableModel;
    private JLabel totalStudentsVal, companiesVal, interviewsVal, placedVal;
    private ActionListener navCallback;

    public DashboardView(PlacementSystem system, ActionListener navCallback) {
        this.system = system;
        this.navCallback = navCallback;

        setLayout(new BorderLayout(20, 20));
        setBackground(UITheme.COLOR_BG);
        setBorder(new EmptyBorder(24, 24, 24, 24));

        // Top Header
        JPanel headerPanel = UITheme.createHeaderPanel(
                "PLACEMENT DASHBOARD",
                "Overview of placement metrics, quick actions, and recent activities"
        );

        // Stats Panel (4 Stat Cards)
        statGrid = new JPanel(new GridLayout(1, 4, 16, 16));
        statGrid.setOpaque(false);

        // Center Content Container (Quick Actions + Recent Logs)
        JPanel contentGrid = new JPanel(new GridLayout(1, 2, 20, 20));
        contentGrid.setOpaque(false);

        contentGrid.add(createQuickActionsCard());
        contentGrid.add(createRecentActionsCard());

        // Assembly
        JPanel topContainer = new JPanel(new BorderLayout(0, 20));
        topContainer.setOpaque(false);
        topContainer.add(headerPanel, BorderLayout.NORTH);
        topContainer.add(statGrid, BorderLayout.SOUTH);

        add(topContainer, BorderLayout.NORTH);
        add(contentGrid, BorderLayout.CENTER);

        refreshData();
    }

    private JPanel createQuickActionsCard() {
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new BorderLayout(0, 16));

        JLabel cardTitle = new JLabel("Quick Management Shortcuts");
        cardTitle.setFont(UITheme.FONT_HEADER);
        cardTitle.setForeground(UITheme.COLOR_TEXT_DARK);
        card.add(cardTitle, BorderLayout.NORTH);

        JPanel btnGrid = new JPanel(new GridLayout(3, 2, 12, 12));
        btnGrid.setOpaque(false);

        JButton btnStudent = UITheme.createPrimaryButton("Student Directory");
        btnStudent.setActionCommand("STUDENT");
        btnStudent.addActionListener(navCallback);

        JButton btnCompany = UITheme.createPrimaryButton("Company Management");
        btnCompany.setActionCommand("COMPANY");
        btnCompany.addActionListener(navCallback);

        JButton btnJob = UITheme.createPrimaryButton("Job Listings");
        btnJob.setActionCommand("JOB");
        btnJob.addActionListener(navCallback);

        JButton btnApp = UITheme.createPrimaryButton("Job Applications");
        btnApp.setActionCommand("APPLICATION");
        btnApp.addActionListener(navCallback);

        JButton btnInterview = UITheme.createPrimaryButton("Interview Queue");
        btnInterview.setActionCommand("INTERVIEW");
        btnInterview.addActionListener(navCallback);

        JButton btnSort = UITheme.createSecondaryButton("Sort & Rank Students");
        btnSort.setActionCommand("SORTING");
        btnSort.addActionListener(navCallback);

        btnGrid.add(btnStudent);
        btnGrid.add(btnCompany);
        btnGrid.add(btnJob);
        btnGrid.add(btnApp);
        btnGrid.add(btnInterview);
        btnGrid.add(btnSort);

        card.add(btnGrid, BorderLayout.CENTER);
        return card;
    }

    private JPanel createRecentActionsCard() {
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new BorderLayout(0, 12));

        JLabel cardTitle = new JLabel("Recent System Logs (LIFO Stack)");
        cardTitle.setFont(UITheme.FONT_HEADER);
        cardTitle.setForeground(UITheme.COLOR_TEXT_DARK);
        card.add(cardTitle, BorderLayout.NORTH);

        actionsTableModel = new DefaultTableModel(new Object[]{"Action Description"}, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };

        recentActionsTable = new JTable(actionsTableModel);
        UITheme.styleTable(recentActionsTable);

        JScrollPane scrollPane = new JScrollPane(recentActionsTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(UITheme.COLOR_BORDER));

        card.add(scrollPane, BorderLayout.CENTER);
        return card;
    }

    public void refreshData() {
        statGrid.removeAll();

        int totalStudents = system.getTotalStudents();
        int totalCompanies = system.getTotalCompanies();
        int pendingInterviews = system.getInterviewQueueSize();
        int placedStudents = system.getPlacedStudentsCount();

        statGrid.add(UITheme.createStatCard("Total Students", String.valueOf(totalStudents), "Active Profiles", UITheme.COLOR_ACCENT));
        statGrid.add(UITheme.createStatCard("Companies", String.valueOf(totalCompanies), "Registered Partners", UITheme.COLOR_SLATE));
        statGrid.add(UITheme.createStatCard("Pending Interviews", String.valueOf(pendingInterviews), "FIFO Queue Slots", UITheme.COLOR_WARNING));
        statGrid.add(UITheme.createStatCard("Placed Students", String.valueOf(placedStudents), "Selected Candidates", UITheme.COLOR_SUCCESS));

        statGrid.revalidate();
        statGrid.repaint();

        // Refresh Recent Actions Table
        actionsTableModel.setRowCount(0);
        ActionStack stack = system.getActionStack();
        if (stack != null && !stack.isEmpty()) {
            ActionStack tempStack = new ActionStack();
            int count = 0;
            while (!stack.isEmpty() && count < 8) {
                String action = stack.pop();
                actionsTableModel.addRow(new Object[]{action});
                tempStack.push(action);
                count++;
            }
            // Restore stack
            while (!tempStack.isEmpty()) {
                stack.push(tempStack.pop());
            }
        } else {
            actionsTableModel.addRow(new Object[]{"No recent actions recorded."});
        }
    }
}
