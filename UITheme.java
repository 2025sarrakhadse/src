import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * Centralized Design System & UI Theme for Placement Management System.
 * Provides modern flat colors, typography, styled components, custom tables,
 * hover buttons, stat cards, and form utilities.
 */
public class UITheme {

    // =====================================================
    // COLOR PALETTE (Modern Flat UI)
    // =====================================================
    public static final Color COLOR_NAVY = Color.decode("#1E293B");        // Deep Navy Header/Sidebar
    public static final Color COLOR_NAVY_DARK = Color.decode("#0F172A");   // Darker Navy Sidebar Active/Hover
    public static final Color COLOR_SLATE = Color.decode("#64748B");       // Slate Gray Secondary
    public static final Color COLOR_ACCENT = Color.decode("#2563EB");      // Accent Blue Primary Action
    public static final Color COLOR_ACCENT_HOVER = Color.decode("#1D4ED8");// Accent Blue Hover
    public static final Color COLOR_BG = Color.decode("#F8FAFC");          // Light Gray App Background
    public static final Color COLOR_CARD_BG = Color.decode("#FFFFFF");     // Card Surface
    public static final Color COLOR_TEXT_DARK = Color.decode("#0F172A");   // Main Dark Text
    public static final Color COLOR_TEXT_MUTED = Color.decode("#64748B");  // Muted Subtitle Text
    public static final Color COLOR_BORDER = Color.decode("#E2E8F0");      // Clean Light Border
    public static final Color COLOR_ROW_ALT = Color.decode("#F1F5F9");     // Alternating Table Row
    public static final Color COLOR_SELECTION = Color.decode("#DBEAFE");   // Table Selection Blue
    
    // Status Colors
    public static final Color COLOR_SUCCESS = Color.decode("#059669");     // Emerald Green
    public static final Color COLOR_SUCCESS_BG = Color.decode("#D1FAE5");  // Green Tint
    public static final Color COLOR_DANGER = Color.decode("#DC2626");      // Red
    public static final Color COLOR_DANGER_BG = Color.decode("#FEE2E2");   // Red Tint
    public static final Color COLOR_WARNING = Color.decode("#D97706");     // Amber
    public static final Color COLOR_WARNING_BG = Color.decode("#FEF3C7");  // Amber Tint

    // =====================================================
    // TYPOGRAPHY (Segoe UI / Sans-Serif)
    // =====================================================
    public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font FONT_HEADER = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font FONT_SUBHEADER = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FONT_BODY = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_BOLD = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_SMALL = new Font("Segoe UI", Font.PLAIN, 11);
    public static final Font FONT_STAT_VAL = new Font("Segoe UI", Font.BOLD, 28);
    public static final Font FONT_MONO = new Font("Consolas", Font.PLAIN, 13);

    private UITheme() {}

    // =====================================================
    // BUTTON FACTORIES WITH HOVER EFFECTS
    // =====================================================
    public static JButton createPrimaryButton(String text) {
        return createButton(text, COLOR_ACCENT, Color.WHITE, COLOR_ACCENT_HOVER);
    }

    public static JButton createSecondaryButton(String text) {
        return createButton(text, COLOR_SLATE, Color.WHITE, COLOR_NAVY);
    }

    public static JButton createDangerButton(String text) {
        return createButton(text, COLOR_DANGER, Color.WHITE, Color.decode("#B91C1C"));
    }

    public static JButton createSuccessButton(String text) {
        return createButton(text, COLOR_SUCCESS, Color.WHITE, Color.decode("#047857"));
    }

    public static JButton createButton(String text, Color bg, Color fg, Color hoverBg) {
        JButton button = new JButton(text);
        button.setFont(FONT_BOLD);
        button.setBackground(bg);
        button.setForeground(fg);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(new EmptyBorder(8, 16, 8, 16));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverBg);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bg);
            }
        });
        return button;
    }

    public static JButton createSidebarButton(String text) {
        JButton button = new JButton("   " + text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setForeground(Color.decode("#CBD5E1")); // Light Slate Text
        button.setBackground(COLOR_NAVY);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setBorder(new EmptyBorder(12, 20, 12, 20));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!button.getBackground().equals(COLOR_ACCENT)) {
                    button.setBackground(COLOR_NAVY_DARK);
                    button.setForeground(Color.WHITE);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (!button.getBackground().equals(COLOR_ACCENT)) {
                    button.setBackground(COLOR_NAVY);
                    button.setForeground(Color.decode("#CBD5E1"));
                }
            }
        });
        return button;
    }

    // =====================================================
    // STAT CARD FACTORY FOR DASHBOARD
    // =====================================================
    public static JPanel createStatCard(String title, String value, String subtext, Color accentColor) {
        JPanel card = new JPanel(new BorderLayout(10, 8));
        card.setBackground(COLOR_CARD_BG);
        card.setBorder(new CompoundBorder(
                new LineBorder(COLOR_BORDER, 1, true),
                new EmptyBorder(16, 18, 16, 18)
        ));

        // Top Header Title
        JLabel titleLabel = new JLabel(title.toUpperCase());
        titleLabel.setFont(FONT_SMALL);
        titleLabel.setForeground(COLOR_TEXT_MUTED);

        // Center Big Number Value
        JLabel valLabel = new JLabel(value);
        valLabel.setFont(FONT_STAT_VAL);
        valLabel.setForeground(COLOR_TEXT_DARK);

        // Bottom Subtext / Indicator Bar
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 0));
        bottomPanel.setOpaque(false);

        JLabel subLabel = new JLabel(subtext);
        subLabel.setFont(FONT_SMALL);
        subLabel.setForeground(accentColor);

        // Accent strip at bottom
        JPanel strip = new JPanel();
        strip.setBackground(accentColor);
        strip.setPreferredSize(new Dimension(35, 3));

        bottomPanel.add(subLabel, BorderLayout.WEST);
        bottomPanel.add(strip, BorderLayout.EAST);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valLabel, BorderLayout.CENTER);
        card.add(bottomPanel, BorderLayout.SOUTH);

        return card;
    }

    // =====================================================
    // FORM INPUT STYLING
    // =====================================================
    public static JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(FONT_BODY);
        textField.setForeground(COLOR_TEXT_DARK);
        textField.setBackground(COLOR_CARD_BG);
        textField.setCaretColor(COLOR_NAVY);
        textField.setBorder(new CompoundBorder(
                new LineBorder(COLOR_BORDER, 1, true),
                new EmptyBorder(6, 10, 6, 10)
        ));
        return textField;
    }

    public static JPasswordField createStyledPasswordField() {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(FONT_BODY);
        passwordField.setForeground(COLOR_TEXT_DARK);
        passwordField.setBackground(COLOR_CARD_BG);
        passwordField.setCaretColor(COLOR_NAVY);
        passwordField.setBorder(new CompoundBorder(
                new LineBorder(COLOR_BORDER, 1, true),
                new EmptyBorder(6, 10, 6, 10)
        ));
        return passwordField;
    }

    public static JComboBox<String> createStyledComboBox(String[] items) {
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setFont(FONT_BODY);
        combo.setBackground(COLOR_CARD_BG);
        combo.setForeground(COLOR_TEXT_DARK);
        combo.setBorder(new EmptyBorder(2, 2, 2, 2));
        return combo;
    }

    // =====================================================
    // CARD & CONTAINER PANEL FACTORIES
    // =====================================================
    public static JPanel createCardPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(COLOR_CARD_BG);
        panel.setBorder(new CompoundBorder(
                new LineBorder(COLOR_BORDER, 1, true),
                new EmptyBorder(16, 16, 16, 16)
        ));
        return panel;
    }

    public static JPanel createHeaderPanel(String titleText, String subtitleText) {
        JPanel headerPanel = new JPanel(new GridLayout(2, 1, 0, 4));
        headerPanel.setOpaque(false);
        headerPanel.setBorder(new EmptyBorder(0, 0, 10, 0));

        JLabel title = new JLabel(titleText);
        title.setFont(FONT_TITLE);
        title.setForeground(COLOR_TEXT_DARK);

        JLabel subtitle = new JLabel(subtitleText);
        subtitle.setFont(FONT_BODY);
        subtitle.setForeground(COLOR_TEXT_MUTED);

        headerPanel.add(title);
        headerPanel.add(subtitle);
        return headerPanel;
    }

    // =====================================================
    // CUSTOM TABLE STYLING
    // =====================================================
    public static void styleTable(JTable table) {
        table.setFont(FONT_BODY);
        table.setRowHeight(32);
        table.setShowGrid(true);
        table.setGridColor(COLOR_BORDER);
        table.setSelectionBackground(COLOR_SELECTION);
        table.setSelectionForeground(COLOR_TEXT_DARK);

        // Header Styling
        JTableHeader header = table.getTableHeader();
        header.setFont(FONT_BOLD);
        header.setBackground(COLOR_NAVY);
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 36));
        header.setReorderingAllowed(false);

        // Header Renderer (Padding & Text Alignment)
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(JLabel.LEFT);
        headerRenderer.setBorder(new EmptyBorder(0, 10, 0, 10));

        // Alternating Row Colors Renderer
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? COLOR_CARD_BG : COLOR_ROW_ALT);
                    c.setForeground(COLOR_TEXT_DARK);
                }
                setBorder(new EmptyBorder(0, 10, 0, 10));
                return c;
            }
        });
    }
}
