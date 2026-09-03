import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Modern Recent Actions View.
 * Uses LIFO Stack (ActionStack) to log recent operations and support Undo functionality.
 */
public class ActionsView extends JPanel {

    private PlacementSystem system;

    private JTable actionsTable;
    private DefaultTableModel tableModel;
    private JLabel stackSizeLabel;

    public ActionsView(PlacementSystem system) {
        this.system = system;

        setLayout(new BorderLayout(20, 20));
        setBackground(UITheme.COLOR_BG);
        setBorder(new EmptyBorder(24, 24, 24, 24));

        // Header
        JPanel headerPanel = UITheme.createHeaderPanel(
                "RECENT SYSTEM ACTIONS & UNDO LOG",
                "Audit trail backed by custom ActionStack (Last-In-First-Out LIFO data structure)"
        );
        add(headerPanel, BorderLayout.NORTH);

        // Control bar + Table
        JPanel centerPanel = new JPanel(new BorderLayout(0, 16));
        centerPanel.setOpaque(false);

        centerPanel.add(createControlsCard(), BorderLayout.NORTH);
        centerPanel.add(createTableCard(), BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        refreshTable();
    }

    private JPanel createControlsCard() {
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new FlowLayout(FlowLayout.LEFT, 16, 10));

        JButton undoBtn = UITheme.createDangerButton("Undo Last Action (Pop)");
        JButton refreshBtn = UITheme.createSecondaryButton("Refresh Stack");

        stackSizeLabel = new JLabel("Stack Depth: 0 items");
        stackSizeLabel.setFont(UITheme.FONT_BOLD);
        stackSizeLabel.setForeground(UITheme.COLOR_TEXT_MUTED);

        undoBtn.addActionListener(e -> undoAction());
        refreshBtn.addActionListener(e -> refreshTable());

        card.add(undoBtn);
        card.add(refreshBtn);
        card.add(stackSizeLabel);

        return card;
    }

    private JPanel createTableCard() {
        JPanel card = UITheme.createCardPanel();
        card.setLayout(new BorderLayout(0, 12));

        JLabel title = new JLabel("LIFO Action History (Top to Bottom)");
        title.setFont(UITheme.FONT_HEADER);
        title.setForeground(UITheme.COLOR_TEXT_DARK);
        card.add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new Object[]{"Stack Position", "Logged Action Description"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };

        actionsTable = new JTable(tableModel);
        UITheme.styleTable(actionsTable);

        JScrollPane scrollPane = new JScrollPane(actionsTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(UITheme.COLOR_BORDER));

        card.add(scrollPane, BorderLayout.CENTER);
        return card;
    }

    private void undoAction() {
        ActionStack stack = system.getActionStack();
        if (stack == null || stack.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Action stack is empty. Nothing to undo.", "Stack Empty", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String poppedAction = system.undoLastAction();
        JOptionPane.showMessageDialog(this, "Undone / Popped Action:\n\"" + poppedAction + "\"", "Action Undone", JOptionPane.INFORMATION_MESSAGE);
        refreshTable();
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        ActionStack stack = system.getActionStack();
        int size = stack != null ? stack.size() : 0;
        stackSizeLabel.setText("Stack Depth: " + size + " item(s)");

        if (stack != null && !stack.isEmpty()) {
            ActionStack tempStack = new ActionStack();
            int pos = 1;
            while (!stack.isEmpty()) {
                String action = stack.pop();
                tableModel.addRow(new Object[]{pos == 1 ? "TOP (Next Pop)" : "Depth " + pos, action});
                tempStack.push(action);
                pos++;
            }
            // Restore original stack
            while (!tempStack.isEmpty()) {
                stack.push(tempStack.pop());
            }
        }
    }
}
