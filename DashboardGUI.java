import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

/**
 * Modern Dashboard GUI - Central Frame featuring a sleek Left Navigation Sidebar
 * and a CardLayout center container switching views seamlessly without spawning popups.
 */
public class DashboardGUI extends JFrame {

    private PlacementSystem system;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private Map<String, JButton> sidebarButtons;

    // View Components
    private DashboardView dashboardView;
    private StudentView studentView;
    private CompanyView companyView;
    private JobView jobView;
    private ApplicationView applicationView;
    private InterviewView interviewView;
    private SearchView searchView;
    private SortingView sortingView;
    private ActionsView actionsView;
    private SummaryView summaryView;

    public DashboardGUI() {
        this.system = PlacementSystem.getInstance();

        setTitle("Placement Management System - Enterprise Dashboard");
        setSize(1280, 800);
        setMinimumSize(new Dimension(1024, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        sidebarButtons = new HashMap<>();

        // Main Frame Layout
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(UITheme.COLOR_BG);

        // Navigation Sidebar
        container.add(createSidebarPanel(), BorderLayout.WEST);

        // Center Content Area (CardLayout)
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(UITheme.COLOR_BG);

        // Action listener for navigation switching
        ActionListener navListener = e -> navigateTo(e.getActionCommand());

        // Initialize Views
        dashboardView = new DashboardView(system, navListener);
        studentView = new StudentView(system);
        companyView = new CompanyView(system);
        jobView = new JobView(system);
        applicationView = new ApplicationView(system);
        interviewView = new InterviewView(system);
        searchView = new SearchView(system);
        sortingView = new SortingView(system);
        actionsView = new ActionsView(system);
        summaryView = new SummaryView(system);

        // Add Views to CardLayout Container
        cardPanel.add(dashboardView, "DASHBOARD");
        cardPanel.add(studentView, "STUDENT");
        cardPanel.add(companyView, "COMPANY");
        cardPanel.add(jobView, "JOB");
        cardPanel.add(applicationView, "APPLICATION");
        cardPanel.add(interviewView, "INTERVIEW");
        cardPanel.add(searchView, "SEARCH");
        cardPanel.add(sortingView, "SORTING");
        cardPanel.add(actionsView, "ACTIONS");
        cardPanel.add(summaryView, "SUMMARY");

        container.add(cardPanel, BorderLayout.CENTER);
        add(container);

        // Default to Dashboard View
        navigateTo("DASHBOARD");
    }

    private JPanel createSidebarPanel() {
        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setPreferredSize(new Dimension(250, 800));
        sidebar.setBackground(UITheme.COLOR_NAVY);
        sidebar.setBorder(new MatteBorder(0, 0, 0, 1, UITheme.COLOR_BORDER));

        // 1. Sidebar Brand Header
        JPanel brandPanel = new JPanel(new GridLayout(2, 1, 0, 4));
        brandPanel.setOpaque(false);
        brandPanel.setBorder(new EmptyBorder(24, 20, 20, 20));

        JLabel brandTitle = new JLabel("PLACEMENT OS");
        brandTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        brandTitle.setForeground(Color.WHITE);

        JLabel brandSub = new JLabel("PLACEMENT OFFICER PORTAL");
        brandSub.setFont(UITheme.FONT_SMALL);
        brandSub.setForeground(Color.decode("#94A3B8"));

        brandPanel.add(brandTitle);
        brandPanel.add(brandSub);

        sidebar.add(brandPanel, BorderLayout.NORTH);

        // 2. Sidebar Navigation Links List
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setOpaque(false);
        menuPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

        String[][] menuItems = {
                {"Dashboard", "DASHBOARD"},
                {"Student Management", "STUDENT"},
                {"Company Management", "COMPANY"},
                {"Job Openings", "JOB"},
                {"Job Applications", "APPLICATION"},
                {"Interview Queue", "INTERVIEW"},
                {"Search Engine", "SEARCH"},
                {"Sort & Rank Students", "SORTING"},
                {"Recent Actions (LIFO)", "ACTIONS"},
                {"System Summary", "SUMMARY"}
        };

        for (String[] item : menuItems) {
            JButton btn = UITheme.createSidebarButton(item[0]);
            btn.setActionCommand(item[1]);
            btn.addActionListener(e -> navigateTo(e.getActionCommand()));

            sidebarButtons.put(item[1], btn);
            menuPanel.add(btn);
        }

        JScrollPane menuScroll = new JScrollPane(menuPanel);
        menuScroll.setOpaque(false);
        menuScroll.getViewport().setOpaque(false);
        menuScroll.setBorder(null);
        menuScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        sidebar.add(menuScroll, BorderLayout.CENTER);

        // 3. Bottom User Profile & Logout Panel
        JPanel footerPanel = new JPanel(new BorderLayout(10, 0));
        footerPanel.setOpaque(false);
        footerPanel.setBorder(new EmptyBorder(16, 20, 20, 20));

        JButton logoutBtn = UITheme.createDangerButton("Logout");
        logoutBtn.addActionListener(e -> logout());

        footerPanel.add(logoutBtn, BorderLayout.CENTER);
        sidebar.add(footerPanel, BorderLayout.SOUTH);

        return sidebar;
    }

    public void navigateTo(String viewKey) {
        cardLayout.show(cardPanel, viewKey);

        // Update active sidebar button styles
        for (Map.Entry<String, JButton> entry : sidebarButtons.entrySet()) {
            JButton btn = entry.getValue();
            if (entry.getKey().equals(viewKey)) {
                btn.setBackground(UITheme.COLOR_ACCENT);
                btn.setForeground(Color.WHITE);
                btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            } else {
                btn.setBackground(UITheme.COLOR_NAVY);
                btn.setForeground(Color.decode("#CBD5E1"));
                btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            }
        }

        // Trigger dynamic refresh on navigated panel
        switch (viewKey) {
            case "DASHBOARD":
                dashboardView.refreshData();
                break;
            case "STUDENT":
                studentView.refreshTable();
                break;
            case "COMPANY":
                companyView.refreshTable();
                break;
            case "JOB":
                jobView.refreshTable();
                break;
            case "APPLICATION":
                applicationView.refreshTable();
                break;
            case "INTERVIEW":
                interviewView.refreshTable();
                break;
            case "ACTIONS":
                actionsView.refreshTable();
                break;
            case "SUMMARY":
                summaryView.refreshSummary();
                break;
        }
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to log out of Placement Management System?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            new LoginGUI().setVisible(true);
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DashboardGUI().setVisible(true);
        });
    }
}
