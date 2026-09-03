import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Modern Login View - Enterprise Authentication Screen.
 * Uses flat UI styling, input validation via ValidationUtil, and smooth transition
 * to DashboardGUI.
 */
public class LoginGUI extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginGUI() {
        setTitle("Placement Management System - Login");
        setSize(480, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main Background Panel (Deep Navy)
        JPanel bgPanel = new JPanel(new GridBagLayout());
        bgPanel.setBackground(UITheme.COLOR_NAVY);

        // Centered White Login Card
        JPanel loginCard = UITheme.createCardPanel();
        loginCard.setPreferredSize(new Dimension(380, 420));
        loginCard.setLayout(new BorderLayout(0, 20));

        // Header Title Panel
        JPanel titlePanel = new JPanel(new GridLayout(3, 1, 0, 4));
        titlePanel.setOpaque(false);

        JLabel brandIcon = new JLabel("PLACEMENT OS", SwingConstants.CENTER);
        brandIcon.setFont(new Font("Segoe UI", Font.BOLD, 22));
        brandIcon.setForeground(UITheme.COLOR_NAVY);

        JLabel titleLabel = new JLabel("Welcome Back", SwingConstants.CENTER);
        titleLabel.setFont(UITheme.FONT_HEADER);
        titleLabel.setForeground(UITheme.COLOR_TEXT_DARK);

        JLabel subLabel = new JLabel("Sign in to access Placement Dashboard", SwingConstants.CENTER);
        subLabel.setFont(UITheme.FONT_SMALL);
        subLabel.setForeground(UITheme.COLOR_TEXT_MUTED);

        titlePanel.add(brandIcon);
        titlePanel.add(titleLabel);
        titlePanel.add(subLabel);

        loginCard.add(titlePanel, BorderLayout.NORTH);

        // Form Fields Grid
        JPanel formGrid = new JPanel(new GridLayout(4, 1, 0, 8));
        formGrid.setOpaque(false);
        formGrid.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel emailLabel = new JLabel("Email / Username");
        emailLabel.setFont(UITheme.FONT_BOLD);
        emailLabel.setForeground(UITheme.COLOR_TEXT_MUTED);

        emailField = UITheme.createStyledTextField();
        emailField.setText("officer@placement.edu"); // Default convenience text

        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(UITheme.FONT_BOLD);
        passLabel.setForeground(UITheme.COLOR_TEXT_MUTED);

        passwordField = UITheme.createStyledPasswordField();
        passwordField.setText("admin123"); // Default convenience text

        formGrid.add(emailLabel);
        formGrid.add(emailField);
        formGrid.add(passLabel);
        formGrid.add(passwordField);

        loginCard.add(formGrid, BorderLayout.CENTER);

        // Actions Bottom Panel
        JPanel btnPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        btnPanel.setOpaque(false);
        btnPanel.setBorder(new EmptyBorder(0, 10, 10, 10));

        JButton loginButton = UITheme.createPrimaryButton("Sign In to Portal");
        loginButton.setPreferredSize(new Dimension(300, 40));
        loginButton.addActionListener(e -> performLogin());

        JLabel footerLabel = new JLabel("Placement Management System v2.0", SwingConstants.CENTER);
        footerLabel.setFont(UITheme.FONT_SMALL);
        footerLabel.setForeground(UITheme.COLOR_TEXT_MUTED);

        btnPanel.add(loginButton);
        btnPanel.add(footerLabel);

        loginCard.add(btnPanel, BorderLayout.SOUTH);

        bgPanel.add(loginCard);
        add(bgPanel);
    }

    private void performLogin() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your login email and password.",
                    "Input Required",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Validate Email format if email is entered
        if (email.contains("@") && !ValidationUtil.isValidEmail(email)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid email address format.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Successful Login Transition
        new DashboardGUI().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginGUI().setVisible(true);
        });
    }
}
