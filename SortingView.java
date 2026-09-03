import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Modern Student Sorting View.
 * Uses custom Bubble Sort algorithm (StudentSorting.java) with explicit runtime complexity documentation.
 */
public class SortingView extends JPanel {

    private PlacementSystem system;

    private JComboBox<String> sortCriteriaCombo;
    private JTable sortedTable;
    private DefaultTableModel tableModel;

    public SortingView(PlacementSystem system) {
        this.system = system;

        setLayout(new BorderLayout(20, 20));
        setBackground(UITheme.COLOR_BG);
        setBorder(new EmptyBorder(24, 24, 24, 24));

        // Header
        JPanel headerPanel = UITheme.createHeaderPanel(
                "STUDENT SORTING & RANKING (BUBBLE SORT)",
                "Custom Bubble Sort implementation for ranking students by CGPA or Alphabetical Name"
        );
        add(headerPanel, BorderLayout.NORTH);

        // Control Panel + Table
        JPanel centerPanel = new JPanel(new BorderLayout(0, 16));
        centerPanel.setOpaque(false);

        centerPanel.add(createControlsCard(), BorderLayout.NORTH);
        centerPanel.add(createTableCard(), BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
        add(createComplexityCard(), BorderLayout.SOUTH);

        sortCGPA();
    }

    private JPanel createControlsCard() {
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new FlowLayout(FlowLayout.LEFT, 16, 10));

        JLabel label = new JLabel("Sorting Strategy:");
        label.setFont(UITheme.FONT_BOLD);

        sortCriteriaCombo = UITheme.createStyledComboBox(new String[]{
                "Sort by CGPA (Descending - Highest First)",
                "Sort by Student Name (Alphabetical A-Z)"
        });

        JButton sortBtn = UITheme.createPrimaryButton("Run Bubble Sort");
        sortBtn.addActionListener(e -> runSort());

        card.add(label);
        card.add(sortCriteriaCombo);
        card.add(sortBtn);

        return card;
    }

    private JPanel createTableCard() {
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new BorderLayout(0, 12));

        JLabel title = new JLabel("Sorted Student Standings");
        title.setFont(UITheme.FONT_HEADER);
        title.setForeground(UITheme.COLOR_TEXT_DARK);
        card.add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new Object[]{"Rank", "Student ID", "Name", "CGPA", "Department", "Phone", "Email"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };

        sortedTable = new JTable(tableModel);
        UITheme.styleTable(sortedTable);

        JScrollPane scrollPane = new JScrollPane(sortedTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(UITheme.COLOR_BORDER));

        card.add(scrollPane, BorderLayout.CENTER);
        return card;
    }

    private JPanel createComplexityCard() {
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new GridLayout(1, 4, 16, 10));
        card.setBackground(UITheme.COLOR_NAVY_DARK);

        card.add(createComplexityItem("Worst-Case Time", "O(N²)", "Unordered / Reverse Input"));
        card.add(createComplexityItem("Average-Case Time", "O(N²)", "Typical Input Distribution"));
        card.add(createComplexityItem("Best-Case Time", "O(N)", "Early Exit Swapped Flag"));
        card.add(createComplexityItem("Space Complexity", "O(1)", "In-Place Element Swapping"));

        return card;
    }

    private JPanel createComplexityItem(String label, String value, String sub) {
        JPanel p = new JPanel(new GridLayout(3, 1, 0, 2));
        p.setOpaque(false);

        JLabel l1 = new JLabel(label);
        l1.setFont(UITheme.FONT_SMALL);
        l1.setForeground(Color.decode("#94A3B8"));

        JLabel l2 = new JLabel(value);
        l2.setFont(UITheme.FONT_HEADER);
        l2.setForeground(UITheme.COLOR_ACCENT);

        JLabel l3 = new JLabel(sub);
        l3.setFont(UITheme.FONT_SMALL);
        l3.setForeground(Color.WHITE);

        p.add(l1);
        p.add(l2);
        p.add(l3);
        return p;
    }

    private void runSort() {
        int idx = sortCriteriaCombo.getSelectedIndex();
        if (idx == 0) {
            sortCGPA();
        } else {
            sortName();
        }
    }

    private void sortCGPA() {
        Student[] sorted = system.getStudentsSortedByCGPA();
        populateTable(sorted);
    }

    private void sortName() {
        Student[] sorted = system.getStudentsSortedByName();
        populateTable(sorted);
    }

    private void populateTable(Student[] students) {
        tableModel.setRowCount(0);
        if (students != null) {
            int rank = 1;
            for (Student s : students) {
                if (s != null) {
                    tableModel.addRow(new Object[]{
                            "#" + rank++, s.getUserId(), s.getName(), s.getCgpa(),
                            s.getDepartment(), s.getPhoneNumber(), s.getEmail()
                    });
                }
            }
        }
    }
}
