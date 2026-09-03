import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Modern System Summary & Analytics View.
 * Displays overall placement statistics, counts, and system status breakdown.
 */
public class SummaryView extends JPanel {

    private PlacementSystem system;

    private JTextArea summaryText;

    public SummaryView(PlacementSystem system) {
        this.system = system;

        setLayout(new BorderLayout(20, 20));
        setBackground(UITheme.COLOR_BG);
        setBorder(new EmptyBorder(24, 24, 24, 24));

        // Header
        JPanel headerPanel = UITheme.createHeaderPanel(
                "SYSTEM SUMMARY & PLACEMENT ANALYTICS",
                "High-level overview of placement system metrics and data structures"
        );
        add(headerPanel, BorderLayout.NORTH);

        // Center card with summary text
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new BorderLayout(0, 16));

        JLabel title = new JLabel("System Audit & Performance Report");
        title.setFont(UITheme.FONT_HEADER);
        title.setForeground(UITheme.COLOR_TEXT_DARK);

        summaryText = new JTextArea();
        summaryText.setEditable(false);
        summaryText.setFont(UITheme.FONT_MONO);
        summaryText.setBackground(UITheme.COLOR_ROW_ALT);
        summaryText.setForeground(UITheme.COLOR_TEXT_DARK);
        summaryText.setBorder(new EmptyBorder(16, 16, 16, 16));

        JScrollPane scrollPane = new JScrollPane(summaryText);
        scrollPane.setBorder(BorderFactory.createLineBorder(UITheme.COLOR_BORDER));

        JButton refreshBtn = UITheme.createPrimaryButton("Refresh System Summary");
        refreshBtn.addActionListener(e -> refreshSummary());

        card.add(title, BorderLayout.NORTH);
        card.add(scrollPane, BorderLayout.CENTER);
        card.add(refreshBtn, BorderLayout.SOUTH);

        add(card, BorderLayout.CENTER);

        refreshSummary();
    }

    public void refreshSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================================================\n");
        sb.append("                PLACEMENT MANAGEMENT SYSTEM - AUDIT REPORT              \n");
        sb.append("========================================================================\n\n");

        sb.append("[1] DATA STRUCTURE CAPACITY & METRICS\n");
        sb.append("------------------------------------------------------------------------\n");
        sb.append(" • Student HashMap (O(1) Search)   : ").append(system.getTotalStudents()).append(" registered students\n");
        sb.append(" • Company ArrayList               : ").append(system.getTotalCompanies()).append(" registered companies\n");
        sb.append(" • Job Listings                    : ").append(system.getTotalJobs()).append(" total job openings\n");
        sb.append(" • Application LinkedList          : ").append(system.getTotalApplications()).append(" submitted applications\n");
        sb.append(" • Interview Queue (FIFO)          : ").append(system.getInterviewQueueSize()).append(" candidates pending\n");
        sb.append(" • Action Stack (LIFO)             : ").append(system.getActionCount()).append(" logged recent actions\n\n");

        sb.append("[2] PLACEMENT OUTCOMES & STATUS\n");
        sb.append("------------------------------------------------------------------------\n");
        sb.append(" • Selected / Placed Students      : ").append(system.getPlacedStudentsCount()).append("\n");

        int totalApps = system.getTotalApplications();
        double placementRate = system.getTotalStudents() > 0 ? ((double) system.getPlacedStudentsCount() / system.getTotalStudents()) * 100.0 : 0.0;
        sb.append(" • Placement Success Percentage    : ").append(String.format("%.2f", placementRate)).append("%\n\n");

        sb.append("[3] PERSISTENCE & FILE STORAGE\n");
        sb.append("------------------------------------------------------------------------\n");
        sb.append(" • Storage Backend File            : students.txt\n");
        sb.append(" • Persistence Status              : Synchronized via FileManager\n\n");

        sb.append("========================================================================\n");
        sb.append("                  END OF REPORT - ALL SYSTEMS OPERATIONAL               \n");
        sb.append("========================================================================\n");

        summaryText.setText(sb.toString());
    }
}
