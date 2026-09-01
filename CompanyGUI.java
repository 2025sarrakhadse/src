import javax.swing.*;
import java.awt.*;

public class CompanyGUI extends JFrame {

    private PlacementSystem system;

    private JTextField companyIdField;
    private JTextField companyNameField;
    private JTextField locationField;
    private JTextField hrNameField;
    private JTextField hrEmailField;
    private JTextField contactField;

    private JTextArea outputArea;

    public CompanyGUI(PlacementSystem system) {

        this.system = system;

        setTitle("Company Management");
        setSize(850, 600);
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
                        "COMPANY MANAGEMENT",
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
                        new GridLayout(6, 2, 10, 10)
                );

        inputPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Company Information"
                )
        );

        companyIdField = new JTextField();
        companyNameField = new JTextField();
        locationField = new JTextField();
        hrNameField = new JTextField();
        hrEmailField = new JTextField();
        contactField = new JTextField();

        inputPanel.add(
                new JLabel("Company ID:")
        );
        inputPanel.add(companyIdField);

        inputPanel.add(
                new JLabel("Company Name:")
        );
        inputPanel.add(companyNameField);

        inputPanel.add(
                new JLabel("Location:")
        );
        inputPanel.add(locationField);

        inputPanel.add(
                new JLabel("HR Name:")
        );
        inputPanel.add(hrNameField);

        inputPanel.add(
                new JLabel("HR Email:")
        );
        inputPanel.add(hrEmailField);

        inputPanel.add(
                new JLabel("Contact Number:")
        );
        inputPanel.add(contactField);


        // =========================================
        // BUTTON PANEL
        // =========================================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(1, 3, 10, 10)
                );

        JButton addButton =
                new JButton("Add Company");

        JButton viewButton =
                new JButton("View Companies");

        JButton clearButton =
                new JButton("Clear");

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
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
                        "Company Details"
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
        // ADD COMPANY
        // =========================================

        addButton.addActionListener(
                e -> addCompany()
        );


        // =========================================
        // VIEW COMPANIES
        // =========================================

        viewButton.addActionListener(
                e -> viewCompanies()
        );


        // =========================================
        // CLEAR
        // =========================================

        clearButton.addActionListener(
                e -> clearFields()
        );


        add(mainPanel);
    }


    // =====================================================
    // ADD COMPANY
    // =====================================================

    private void addCompany() {

        try {

            int companyId =
                    Integer.parseInt(
                            companyIdField
                                    .getText()
                                    .trim()
                    );

            String companyName =
                    companyNameField
                            .getText()
                            .trim();

            String location =
                    locationField
                            .getText()
                            .trim();

            String hrName =
                    hrNameField
                            .getText()
                            .trim();

            String hrEmail =
                    hrEmailField
                            .getText()
                            .trim();

            String contactNumber =
                    contactField
                            .getText()
                            .trim();


            // =====================================
            // VALIDATION
            // =====================================

            if (companyName.isEmpty()
                    || location.isEmpty()
                    || hrName.isEmpty()
                    || hrEmail.isEmpty()
                    || contactNumber.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all fields."
                );

                return;
            }


            // =====================================
            // CREATE COMPANY
            // =====================================

            Company company =
                    new Company(
                            companyId,
                            companyName,
                            location,
                            hrName,
                            hrEmail,
                            contactNumber
                    );


            // =====================================
            // ADD TO SYSTEM
            // =====================================

            system.addCompany(company);


            outputArea.setText(
                    "Company added successfully!\n\n"
                    + "Company ID: "
                    + companyId
                    + "\nCompany Name: "
                    + companyName
                    + "\nLocation: "
                    + location
                    + "\nHR Name: "
                    + hrName
                    + "\nHR Email: "
                    + hrEmail
                    + "\nContact Number: "
                    + contactNumber
            );


            JOptionPane.showMessageDialog(
                    this,
                    "Company added successfully!"
            );


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Company ID must be a valid number."
            );
        }
    }


    // =====================================================
    // VIEW COMPANIES
    // =====================================================

    private void viewCompanies() {

        Company[] companies =
                system.getAllCompanies();


        if (companies == null
                || companies.length == 0) {

            outputArea.setText(
                    "No companies registered."
            );

            return;
        }


        StringBuilder result =
                new StringBuilder();


        result.append(
                "===== REGISTERED COMPANIES =====\n\n"
        );


        for (Company company :
                companies) {

            result.append(
                    "Company ID: "
                    + company.getCompanyId()
                    + "\n"
            );

            result.append(
                    "Company Name: "
                    + company.getCompanyName()
                    + "\n"
            );

            result.append(
                    "Location: "
                    + company.getLocation()
                    + "\n"
            );

            result.append(
                    "HR Name: "
                    + company.getHrName()
                    + "\n"
            );

            result.append(
                    "HR Email: "
                    + company.getHrEmail()
                    + "\n"
            );

            result.append(
                    "Contact Number: "
                    + company.getContactNumber()
                    + "\n"
            );

            result.append(
                    "Number of Jobs: "
                    + company.getJobs().size()
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
    // CLEAR FIELDS
    // =====================================================

    private void clearFields() {

        companyIdField.setText("");
        companyNameField.setText("");
        locationField.setText("");
        hrNameField.setText("");
        hrEmailField.setText("");
        contactField.setText("");

        outputArea.setText("");
    }
}
