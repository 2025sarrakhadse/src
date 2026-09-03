import java.awt.*;
import javax.swing.*;

public class DashboardGUI extends JFrame {

    private PlacementSystem system;

    public DashboardGUI() {

        system = PlacementSystem.getInstance();

        setTitle("Placement Management System - Dashboard");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        // =====================================================
        // TITLE
        // =====================================================

        JLabel titleLabel = new JLabel(
                "PLACEMENT MANAGEMENT SYSTEM",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );


        // =====================================================
        // BUTTONS
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(3, 3, 15, 15)
                );


        JButton studentButton =
                new JButton("Student Management");

        JButton companyButton =
                new JButton("Company Management");

        JButton jobButton =
                new JButton("Job Management");

        JButton applicationButton =
                new JButton("Application Management");

        JButton interviewButton =
                new JButton("Interview Management");

        JButton searchButton =
                new JButton("Search");

        JButton sortingButton =
                new JButton("Sort Students");

        JButton actionsButton =
                new JButton("Recent Actions");

        JButton summaryButton =
                new JButton("System Summary");


        buttonPanel.add(studentButton);
        buttonPanel.add(companyButton);
        buttonPanel.add(jobButton);

        buttonPanel.add(applicationButton);
        buttonPanel.add(interviewButton);
        buttonPanel.add(searchButton);

        buttonPanel.add(sortingButton);
        buttonPanel.add(actionsButton);
        buttonPanel.add(summaryButton);


        mainPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // LOGOUT
        // =====================================================

        JButton logoutButton =
                new JButton("Logout");

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        bottomPanel.add(logoutButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );


        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        studentButton.addActionListener(e -> {

            new StudentGUI(system).setVisible(true);

        });


        companyButton.addActionListener(e -> {

            new CompanyGUI(system).setVisible(true);

        });


        jobButton.addActionListener(e -> {

            new JobGUI(system).setVisible(true);

        });


        applicationButton.addActionListener(e -> {

            new ApplicationGUI(system).setVisible(true);

        });


        interviewButton.addActionListener(e -> {

            new InterviewGUI(system).setVisible(true);

        });


        searchButton.addActionListener(e -> {

            new SearchGUI(system).setVisible(true);

        });


        sortingButton.addActionListener(e -> {

            new SortingGUI(system).setVisible(true);

        });


        actionsButton.addActionListener(e -> {

            new ActionsGUI(system).setVisible(true);

        });


        summaryButton.addActionListener(e -> {

            new SummaryGUI(system).setVisible(true);

        });


        // =====================================================
        // LOGOUT ACTION
        // =====================================================

        logoutButton.addActionListener(e -> {

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to logout?",
                            "Logout",
                            JOptionPane.YES_NO_OPTION
                    );


            if (choice ==
                    JOptionPane.YES_OPTION) {

                new LoginGUI().setVisible(true);

                dispose();
            }
        });


        add(mainPanel);
    }
}
