import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginGUI() {

        setTitle("Placement Management System - Login");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel =
                new JLabel("PLACEMENT MANAGEMENT SYSTEM");

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        JLabel emailLabel =
                new JLabel("Email:");

        JLabel passwordLabel =
                new JLabel("Password:");

        emailField = new JTextField(20);

        passwordField =
                new JPasswordField(20);

        JButton loginButton =
                new JButton("Login");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;

        panel.add(emailLabel, gbc);

        gbc.gridx = 1;

        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;

        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;

        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;

        panel.add(loginButton, gbc);

        loginButton.addActionListener(e -> login());

        add(panel);
    }


    private void login() {

        String email =
                emailField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        if (email.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter email and password."
            );

            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Login successful!"
        );

        new DashboardGUI().setVisible(true);

        dispose();
    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new LoginGUI().setVisible(true);

        });
    }
}
