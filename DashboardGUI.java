import javax.swing.*;
import java.awt.*;

public class DashboardGUI extends JFrame {

    public DashboardGUI() {

        setTitle("Placement Management System - Dashboard");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        // =========================================
        // TITLE
        // =========================================

        JLabel titleLabel = new JLabel(
                "PLACEMENT MANAGEMENT SYSTEM",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        mainPanel.add(titleLabel, BorderLayout.NORTH);


        // =========================================
        // DASHBOARD BUTTONS
        // =========================================

        JPanel buttonPanel = new JPanel(
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


        mainPanel.add(buttonPanel, BorderLayout.CENTER);


        // =========================================
        // LOGOUT BUTTON
        // =========================================

        JButton logoutButton =
                new JButton("Logout");

        JPanel bottomPanel =
                new JPanel(new FlowLayout(FlowLayout.RIGHT));

        bottomPanel.add(logoutButton);

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );


        // =========================================
        // BUTTON ACTIONS
        // =========================================

        studentButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Student Management module will open here."
            );
        });


        companyButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Company Management module will open here."
            );
        });


        jobButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Job Management module will open here."
            );
        });


        applicationButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Application Management module will open here."
            );
        });


        interviewButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Interview Management module will open here."
            );
        });


        searchButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Search module will open here."
            );
        });


        sortingButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Student sorting module will open here."
            );
        });


        actionsButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Recent actions will be displayed here."
            );
        });


        summaryButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "System summary will be displayed here."
            );
        });


        logoutButton.addActionListener(e -> {

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {

                new LoginGUI().setVisible(true);

                dispose();
            }
        });


        add(mainPanel);
    }
}
