import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Modern Search Engine View.
 * Provides instant O(1) Student lookup via StudentHashMap, Application search, and Job lookup.
 */
public class SearchView extends JPanel {

    private PlacementSystem system;

    private JTextField searchField;
    private JComboBox<String> typeCombo;
    private JTextArea resultArea;

    public SearchView(PlacementSystem system) {
        this.system = system;

        setLayout(new BorderLayout(20, 20));
        setBackground(UITheme.COLOR_BG);
        setBorder(new EmptyBorder(24, 24, 24, 24));

        // Header
        JPanel headerPanel = UITheme.createHeaderPanel(
                "SYSTEM SEARCH ENGINE",
                "Lookup records instantly (O(1) Student HashMap lookup, Application search, and Job lookup)"
        );
        add(headerPanel, BorderLayout.NORTH);

        // Search Bar Card
        JPanel searchCard = UITheme.createCardPanel();
        searchCard.setLayout(new FlowLayout(FlowLayout.LEFT, 16, 12));

        JLabel label = new JLabel("Search Target:");
        label.setFont(UITheme.FONT_BOLD);

        typeCombo = UITheme.createStyledComboBox(new String[]{"Student (by ID)", "Application (by ID)", "Job (by ID)"});
        searchField = UITheme.createStyledTextField();
        searchField.setPreferredSize(new Dimension(220, 34));

        JButton searchBtn = UITheme.createPrimaryButton("Search Record");
        JButton clearBtn = UITheme.createSecondaryButton("Clear");

        searchBtn.addActionListener(e -> performSearch());
        clearBtn.addActionListener(e -> {
            searchField.setText("");
            resultArea.setText("");
        });

        searchCard.add(label);
        searchCard.add(typeCombo);
        searchCard.add(new JLabel("ID / Query:"));
        searchCard.add(searchField);
        searchCard.add(searchBtn);
        searchCard.add(clearBtn);

        // Results Card
        JPanel resultCard = UITheme.createCardPanel();
        resultCard.setLayout(new BorderLayout(0, 12));

        JLabel resTitle = new JLabel("Lookup Results & Metadata");
        resTitle.setFont(UITheme.FONT_HEADER);
        resTitle.setForeground(UITheme.COLOR_TEXT_DARK);
        resultCard.add(resTitle, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(UITheme.FONT_MONO);
        resultArea.setBackground(UITheme.COLOR_ROW_ALT);
        resultArea.setForeground(UITheme.COLOR_TEXT_DARK);
        resultArea.setBorder(new EmptyBorder(12, 12, 12, 12));

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(UITheme.COLOR_BORDER));

        resultCard.add(scrollPane, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout(0, 16));
        centerPanel.setOpaque(false);
        centerPanel.add(searchCard, BorderLayout.NORTH);
        centerPanel.add(resultCard, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void performSearch() {
        String input = searchField.getText().trim();
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an ID to search.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(input);
            int typeIndex = typeCombo.getSelectedIndex();

            StringBuilder sb = new StringBuilder();

            if (typeIndex == 0) { // Student Search (HashMap O(1))
                long startTime = System.nanoTime();
                Student s = system.findStudent(id);
                long endTime = System.nanoTime();

                sb.append("=== O(1) HASHMAP STUDENT LOOKUP RESULTS ===\n");
                sb.append("Lookup Execution Time: ").append(endTime - startTime).append(" nanoseconds\n");
                sb.append("--------------------------------------------------\n");

                if (s != null) {
                    sb.append("Student ID   : ").append(s.getUserId()).append("\n");
                    sb.append("Full Name    : ").append(s.getName()).append("\n");
                    sb.append("Email        : ").append(s.getEmail()).append("\n");
                    sb.append("Department   : ").append(s.getDepartment()).append("\n");
                    sb.append("CGPA         : ").append(s.getCgpa()).append("\n");
                    sb.append("Phone Number : ").append(s.getPhoneNumber()).append("\n");
                    sb.append("Skills       : ").append(s.getSkills() != null ? String.join(", ", s.getSkills()) : "None").append("\n");
                } else {
                    sb.append("STATUS: Student ID ").append(id).append(" not found in StudentHashMap.\n");
                }

            } else if (typeIndex == 1) { // Application Search
                Application app = system.findApplication(id);
                sb.append("=== LINKED LIST APPLICATION SEARCH RESULTS ===\n");
                sb.append("--------------------------------------------------\n");

                if (app != null) {
                    sb.append("Application ID: ").append(app.getApplicationId()).append("\n");
                    sb.append("Student       : ").append(app.getStudent() != null ? app.getStudent().getName() : "N/A").append("\n");
                    sb.append("Job Title     : ").append(app.getJob() != null ? app.getJob().getJobTitle() : "N/A").append("\n");
                    sb.append("Status        : ").append(app.getStatus()).append("\n");
                    sb.append("Applied Date  : ").append(app.getApplicationDate()).append("\n");
                    if (app.getInterviewDate() != null) {
                        sb.append("Interview     : ").append(app.getInterviewDate()).append(" at ").append(app.getInterviewTime()).append("\n");
                    }
                } else {
                    sb.append("STATUS: Application ID ").append(id).append(" not found.\n");
                }

            } else if (typeIndex == 2) { // Job Search
                Job job = system.findJob(id);
                sb.append("=== ARRAYLIST JOB OPENING SEARCH RESULTS ===\n");
                sb.append("--------------------------------------------------\n");

                if (job != null) {
                    sb.append("Job ID        : ").append(job.getJobId()).append("\n");
                    sb.append("Company       : ").append(job.getCompany() != null ? job.getCompany().getCompanyName() : "N/A").append("\n");
                    sb.append("Job Title     : ").append(job.getJobTitle()).append("\n");
                    sb.append("Salary Offer  : ").append(job.getSalaryLPA()).append(" LPA\n");
                    sb.append("Min CGPA Req  : ").append(job.getMinimumCGPA()).append("\n");
                    sb.append("Required Skill: ").append(job.getRequiredSkill()).append("\n");
                    sb.append("Location      : ").append(job.getLocation()).append("\n");
                    sb.append("Deadline      : ").append(job.getApplicationDeadline()).append("\n");
                } else {
                    sb.append("STATUS: Job ID ").append(id).append(" not found.\n");
                }
            }

            resultArea.setText(sb.toString());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Search ID must be a valid numeric integer.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }
}
