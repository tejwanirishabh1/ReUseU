
package com.reuseu;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Marketplace {

    // Requests are stored in memory for now.
    // They will not persist after the application closes.
    private static final List<String> requests = new ArrayList<>();

    public static void showMarketplace(JFrame parent) {

        JFrame marketplace = new JFrame("ReUseU | Marketplace");
        marketplace.setSize(1000, 650);
        marketplace.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        marketplace.setLocationRelativeTo(parent);
        marketplace.setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(38, 65, 52));
        header.setBorder(
            BorderFactory.createEmptyBorder(20, 25, 20, 25)
        );

        JLabel title = new JLabel("ReUseU Marketplace");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 26));

        JLabel subtitle = new JLabel("Find something. Share something.");
        subtitle.setForeground(new Color(220, 230, 222));
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));

        header.add(title, BorderLayout.WEST);
        header.add(subtitle, BorderLayout.EAST);

        // Main content
        JPanel content = new JPanel(new BorderLayout(10, 15));
        content.setBackground(new Color(247, 247, 242));
        content.setBorder(
            BorderFactory.createEmptyBorder(25, 30, 25, 30)
        );

        JLabel heading = new JLabel("Available on Campus");
        heading.setFont(new Font("SansSerif", Font.BOLD, 24));
        heading.setForeground(new Color(38, 65, 52));

        JPanel headingPanel = new JPanel(new BorderLayout());
        headingPanel.setOpaque(false);
        headingPanel.add(heading, BorderLayout.WEST);

        // Search field
        JTextField searchField = new JTextField();
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 15));
        searchField.setToolTipText("Search campus items");
        searchField.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(210, 215, 205)
                ),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
            )
        );

        // Item cards
        JPanel itemsPanel = new JPanel(
            new GridLayout(0, 3, 18, 18)
        );
        itemsPanel.setOpaque(false);

        addItem(marketplace, itemsPanel, "📚", "Academic Books",
                "Useful books for your next semester.", "Borrow");

        addItem(marketplace, itemsPanel, "🧮", "Calculator",
                "A scientific calculator for coursework.", "Borrow");

        addItem(marketplace, itemsPanel, "🏀", "Basketball",
                "Sports equipment available to share.", "Request");

        addItem(marketplace, itemsPanel, "🎒", "College Backpack",
                "A backpack available for reuse.", "Request");

        addItem(marketplace, itemsPanel, "📒", "Notebook Set",
                "Extra notebooks for class notes.", "Request");

        addItem(marketplace, itemsPanel, "🎧", "Headphones",
                "Headphones available from a campus student.",
                "Request");

        // Heading and search layout
        JPanel topPanel = new JPanel(new BorderLayout(0, 15));
        topPanel.setOpaque(false);
        topPanel.add(headingPanel, BorderLayout.NORTH);
        topPanel.add(searchField, BorderLayout.CENTER);

        content.add(topPanel, BorderLayout.NORTH);
        content.add(new JScrollPane(itemsPanel), BorderLayout.CENTER);

        // Live search
        searchField.getDocument().addDocumentListener(
            new DocumentListener() {

                private void filterItems() {
                    String query = searchField.getText()
                            .trim()
                            .toLowerCase();

                    for (Component component :
                            itemsPanel.getComponents()) {

                        if (component instanceof JPanel) {
                            JPanel card = (JPanel) component;

                            String itemName = (String)
                                card.getClientProperty("itemName");

                            card.setVisible(
                                itemName != null
                                && itemName.contains(query)
                            );
                        }
                    }

                    itemsPanel.revalidate();
                    itemsPanel.repaint();
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    filterItems();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    filterItems();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    filterItems();
                }
            }
        );

        // Footer
        JLabel footer = new JLabel(
            "Share more. Waste less. Build a better campus."
        );
        footer.setHorizontalAlignment(SwingConstants.CENTER);
        footer.setBorder(
            BorderFactory.createEmptyBorder(15, 10, 15, 10)
        );
        footer.setFont(new Font("SansSerif", Font.PLAIN, 12));
        footer.setForeground(new Color(100, 100, 100));

        marketplace.add(header, BorderLayout.NORTH);
        marketplace.add(content, BorderLayout.CENTER);
        marketplace.add(footer, BorderLayout.SOUTH);

        marketplace.setVisible(true);
    }

    // Create an item card
    private static void addItem(
            JFrame marketplace,
            JPanel panel,
            String emoji,
            String name,
            String description,
            String actionText) {

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(225, 228, 220)
                ),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)
            )
        );

        JLabel icon = new JLabel(emoji);
        icon.setFont(new Font("SansSerif", Font.PLAIN, 32));
        icon.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel itemName = new JLabel(name);
        itemName.setFont(new Font("SansSerif", Font.BOLD, 17));
        itemName.setForeground(new Color(38, 65, 52));
        itemName.setBorder(
            BorderFactory.createEmptyBorder(12, 0, 8, 0)
        );
        itemName.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel itemDescription = new JLabel(
            "<html><div style='width:190px'>"
            + description + "</div></html>"
        );
        itemDescription.setFont(
            new Font("SansSerif", Font.PLAIN, 13)
        );
        itemDescription.setForeground(new Color(90, 95, 90));
        itemDescription.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton actionButton = new JButton(actionText);
        actionButton.setBackground(new Color(38, 65, 52));
        actionButton.setForeground(Color.WHITE);
        actionButton.setFocusPainted(false);
        actionButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Open the request form
        actionButton.addActionListener(e ->
            showRequestForm(marketplace, name)
        );

        card.add(icon);
        card.add(itemName);
        card.add(itemDescription);
        card.add(Box.createVerticalStrut(15));
        card.add(actionButton);

        // Store item name for live search
        card.putClientProperty("itemName", name.toLowerCase());

        panel.add(card);
    }

    // Request form
    private static void showRequestForm(
            JFrame marketplace,
            String itemName) {

        JTextField studentNameField = new JTextField(20);

        String[] actions = {
            "Borrow",
            "Request",
            "Donate",
            "Exchange"
        };

        JComboBox<String> actionChoice = new JComboBox<>(actions);
        actionChoice.setSelectedItem(
            itemName.equals("Academic Books")
            || itemName.equals("Calculator")
            ? "Borrow" : "Request"
        );

        SpinnerDateModel dateModel = new SpinnerDateModel(
            java.util.Date.from(
                LocalDate.now().plusDays(7)
                    .atStartOfDay(java.time.ZoneId.systemDefault())
                    .toInstant()
            ),
            new java.util.Date(),
            null,
            java.util.Calendar.DAY_OF_MONTH
        );

        JSpinner returnDateSpinner = new JSpinner(dateModel);
        returnDateSpinner.setEditor(
            new JSpinner.DateEditor(returnDateSpinner, "dd-MM-yyyy")
        );

        JPanel form = new JPanel(new GridLayout(0, 2, 10, 12));
        form.add(new JLabel("Item:"));
        form.add(new JLabel(itemName));
        form.add(new JLabel("Your name:"));
        form.add(studentNameField);
        form.add(new JLabel("Action:"));
        form.add(actionChoice);
        form.add(new JLabel("Requested return date:"));
        form.add(returnDateSpinner);

        int result = JOptionPane.showConfirmDialog(
            marketplace,
            form,
            "Submit Item Request",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String studentName = studentNameField.getText().trim();

        if (studentName.isEmpty()) {
            JOptionPane.showMessageDialog(
                marketplace,
                "Please enter your name.",
                "Name Required",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String selectedAction = (String) actionChoice.getSelectedItem();

        java.util.Date chosenDate =
            (java.util.Date) returnDateSpinner.getValue();

        LocalDate returnDate = chosenDate.toInstant()
            .atZone(java.time.ZoneId.systemDefault())
            .toLocalDate();

        if (returnDate.isBefore(LocalDate.now())) {
            JOptionPane.showMessageDialog(
                marketplace,
                "Please choose today or a future date.",
                "Invalid Date",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String request = "Student: " + studentName
            + " | Item: " + itemName
            + " | Action: " + selectedAction
            + " | Requested return date: " + returnDate;

        requests.add(request);

        JOptionPane.showMessageDialog(
            marketplace,
            "Your request has been recorded for this session!\n\n"
                + request
                + "\n\nNote: Requests are not saved permanently yet.",
            "Request Submitted",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
