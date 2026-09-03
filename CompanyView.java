import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Modern Company Management View.
 * Displays registered hiring partner companies stored in ArrayList<Company>.
 */
public class CompanyView extends JPanel {

    private PlacementSystem system;

    private JTextField idField, nameField, locationField, hrNameField, hrEmailField, contactField;
    private JTable companyTable;
    private DefaultTableModel tableModel;

    public CompanyView(PlacementSystem system) {
        this.system = system;

        setLayout(new BorderLayout(20, 20));
        setBackground(UITheme.COLOR_BG);
        setBorder(new EmptyBorder(24, 24, 24, 24));

        // Header
        JPanel headerPanel = UITheme.createHeaderPanel(
                "COMPANY MANAGEMENT",
                "Manage recruiting companies and corporate partners stored in ArrayList"
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

        JLabel title = new JLabel("Company Details");
        title.setFont(UITheme.FONT_HEADER);
        title.setForeground(UITheme.COLOR_TEXT_DARK);
        card.add(title, BorderLayout.NORTH);

        JPanel formGrid = new JPanel(new GridLayout(6, 2, 8, 12));
        formGrid.setOpaque(false);

        idField = UITheme.createStyledTextField();
        nameField = UITheme.createStyledTextField();
        locationField = UITheme.createStyledTextField();
        hrNameField = UITheme.createStyledTextField();
        hrEmailField = UITheme.createStyledTextField();
        contactField = UITheme.createStyledTextField();

        formGrid.add(createFormLabel("Company ID:"));
        formGrid.add(idField);
        formGrid.add(createFormLabel("Company Name:"));
        formGrid.add(nameField);
        formGrid.add(createFormLabel("Location:"));
        formGrid.add(locationField);
        formGrid.add(createFormLabel("HR Contact Person:"));
        formGrid.add(hrNameField);
        formGrid.add(createFormLabel("HR Email:"));
        formGrid.add(hrEmailField);
        formGrid.add(createFormLabel("Contact Phone:"));
        formGrid.add(contactField);

        card.add(formGrid, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new GridLayout(1, 3, 8, 8));
        btnPanel.setOpaque(false);

        JButton addBtn = UITheme.createPrimaryButton("Add Company");
        JButton deleteBtn = UITheme.createDangerButton("Delete");
        JButton clearBtn = UITheme.createSecondaryButton("Clear");

        addBtn.addActionListener(e -> addCompany());
        deleteBtn.addActionListener(e -> deleteCompany());
        clearBtn.addActionListener(e -> clearForm());

        btnPanel.add(addBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(clearBtn);

        card.add(btnPanel, BorderLayout.SOUTH);
        return card;
    }

    private JPanel createTableCard() {
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new BorderLayout(0, 12));

        JLabel title = new JLabel("Corporate Partners Directory");
        title.setFont(UITheme.FONT_HEADER);
        title.setForeground(UITheme.COLOR_TEXT_DARK);
        card.add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Company Name", "Location", "HR Name", "HR Email", "Contact", "Open Jobs"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };

        companyTable = new JTable(tableModel);
        UITheme.styleTable(companyTable);

        companyTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && companyTable.getSelectedRow() != -1) {
                int row = companyTable.getSelectedRow();
                idField.setText(tableModel.getValueAt(row, 0).toString());
                nameField.setText(tableModel.getValueAt(row, 1).toString());
                locationField.setText(tableModel.getValueAt(row, 2).toString());
                hrNameField.setText(tableModel.getValueAt(row, 3).toString());
                hrEmailField.setText(tableModel.getValueAt(row, 4).toString());
                contactField.setText(tableModel.getValueAt(row, 5).toString());
            }
        });

        JScrollPane scrollPane = new JScrollPane(companyTable);
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

    private void addCompany() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            String location = locationField.getText().trim();
            String hrName = hrNameField.getText().trim();
            String hrEmail = hrEmailField.getText().trim();
            String contact = contactField.getText().trim();

            if (!ValidationUtil.isValidName(name)) {
                showError("Company Name cannot be empty.");
                return;
            }
            if (!ValidationUtil.isValidEmail(hrEmail)) {
                showError("Invalid HR Email address format.");
                return;
            }
            if (!ValidationUtil.isValidPhone(contact)) {
                showError("Invalid contact phone number (10 digits required).");
                return;
            }

            Company company = new Company(id, name, location, hrName, hrEmail, contact);
            boolean added = system.addCompany(company);

            if (added) {
                JOptionPane.showMessageDialog(this, "Company added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                refreshTable();
            } else {
                showError("Company ID already exists.");
            }
        } catch (NumberFormatException ex) {
            showError("Company ID must be a valid number.");
        }
    }

    private void deleteCompany() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            boolean deleted = system.deleteCompany(id);
            if (deleted) {
                JOptionPane.showMessageDialog(this, "Company deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                refreshTable();
            } else {
                showError("Company ID not found.");
            }
        } catch (NumberFormatException ex) {
            showError("Enter a valid numerical Company ID.");
        }
    }

    private void clearForm() {
        idField.setText("");
        nameField.setText("");
        locationField.setText("");
        hrNameField.setText("");
        hrEmailField.setText("");
        contactField.setText("");
        companyTable.clearSelection();
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        Company[] companies = system.getAllCompanies();
        if (companies != null) {
            for (Company c : companies) {
                if (c != null) {
                    int jobsCount = c.getJobs() != null ? c.getJobs().size() : 0;
                    tableModel.addRow(new Object[]{
                            c.getCompanyId(), c.getCompanyName(), c.getLocation(),
                            c.getHrName(), c.getHrEmail(), c.getContactNumber(), jobsCount
                    });
                }
            }
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Validation Error", JOptionPane.ERROR_MESSAGE);
    }
}
