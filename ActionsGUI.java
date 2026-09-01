import javax.swing.*;
import java.awt.*;

public class ActionsGUI extends JFrame {

    private PlacementSystem system;
    private JTextArea outputArea;

    public ActionsGUI(PlacementSystem system) {

        this.system = system;

        setTitle("Placement Management System - Recent Actions");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // =====================================================
        // MAIN PANEL
        // =====================================================

        JPanel mainPanel =
                new JPanel(new BorderLayout(15, 15));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );


        // =====================================================
        // TITLE
        // =====================================================

        JLabel titleLabel =
                new JLabel(
                        "RECENT SYSTEM ACTIONS",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );


        // =====================================================
        // OUTPUT AREA
        // =====================================================

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
                new JScrollPane(
                        outputArea
                );

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Action History"
                )
        );


        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );


        // =====================================================
        // BUTTONS
        // =====================================================

        JButton refreshButton =
                new JButton("Refresh");

        JButton clearButton =
                new JButton("Clear Display");

        JButton closeButton =
                new JButton("Close");


        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );


        buttonPanel.add(
                refreshButton
        );

        buttonPanel.add(
                clearButton
        );

        buttonPanel.add(
                closeButton
        );


        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        refreshButton.addActionListener(
                e -> displayActions()
        );


        clearButton.addActionListener(
                e -> outputArea.setText("")
        );


        closeButton.addActionListener(
                e -> dispose()
        );


        add(mainPanel);


        // Display actions when window opens
        displayActions();
    }


    // =====================================================
    // DISPLAY RECENT ACTIONS
    // =====================================================

    private void displayActions() {

        ActionStack stack =
                system.getActionStack();


        if (stack == null ||
                stack.isEmpty()) {

            outputArea.setText(
                    "No recent actions available."
            );

            return;
        }


        /*
         * We cannot directly access the internal
         * linked-list nodes of ActionStack because
         * they are private.
         *
         * Therefore we use a temporary stack.
         */


        ActionStack temporaryStack =
                new ActionStack();


        StringBuilder result =
                new StringBuilder();


        result.append(
                "===== RECENT SYSTEM ACTIONS =====\n\n"
        );


        int count = 1;


        while (!stack.isEmpty()) {

            String action =
                    stack.pop();


            if (action != null) {

                result.append(
                        count
                        + ". "
                        + action
                        + "\n"
                );


                temporaryStack.push(
                        action
                );

                count++;
            }
        }


        /*
         * Restore the original stack
         * so displaying actions does
         * not permanently delete them.
         */

        while (!temporaryStack.isEmpty()) {

            stack.push(
                    temporaryStack.pop()
            );
        }


        result.append(
                "\nTotal Actions: "
                + stack.size()
        );


        outputArea.setText(
                result.toString()
        );
    }
}
