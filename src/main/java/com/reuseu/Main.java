
package com.reuseu;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("ReUseU | Campus Sharing Network");

            frame.setSize(1000, 650);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());

            // Header
            JPanel header = new JPanel(new BorderLayout());
            header.setBackground(new Color(38, 65, 52));
            header.setBorder(
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
            );

            JLabel logo = new JLabel("ReUseU");
            logo.setForeground(Color.WHITE);
            logo.setFont(new Font("SansSerif", Font.BOLD, 28));

            JLabel tagline = new JLabel("Campus Sharing Network");
            tagline.setForeground(new Color(220, 230, 222));
            tagline.setFont(new Font("SansSerif", Font.PLAIN, 14));

            header.add(logo, BorderLayout.WEST);
            header.add(tagline, BorderLayout.EAST);

            // Welcome section
            JPanel welcome = new JPanel();
            welcome.setLayout(new BoxLayout(welcome, BoxLayout.Y_AXIS));
            welcome.setBackground(new Color(247, 247, 242));
            welcome.setBorder(
                BorderFactory.createEmptyBorder(55, 45, 35, 45)
            );

            JLabel heading = new JLabel("Share more. Waste less.");
            heading.setFont(new Font("SansSerif", Font.BOLD, 32));
            heading.setForeground(new Color(38, 65, 52));
            heading.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel description = new JLabel(
                "Borrow, lend, donate, and exchange items on campus."
            );
            description.setFont(new Font("SansSerif", Font.PLAIN, 16));
            description.setForeground(new Color(90, 95, 90));
            description.setBorder(
                BorderFactory.createEmptyBorder(15, 0, 25, 0)
            );
            description.setAlignmentX(Component.LEFT_ALIGNMENT);

            JButton exploreButton = new JButton("Explore Items");
            exploreButton.setFont(new Font("SansSerif", Font.BOLD, 15));
            exploreButton.setBackground(new Color(38, 65, 52));
            exploreButton.setForeground(Color.WHITE);
            exploreButton.setFocusPainted(false);
            exploreButton.setBorder(
                BorderFactory.createEmptyBorder(12, 22, 12, 22)
            );
            exploreButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            exploreButton.addActionListener(e -> {
                Marketplace.showMarketplace(frame);
            });
            welcome.add(heading);
            welcome.add(description);
            welcome.add(exploreButton);

            // Footer
            JLabel footer = new JLabel(
                "A more sustainable campus starts with sharing."
            );
            footer.setHorizontalAlignment(SwingConstants.CENTER);
            footer.setBorder(
                BorderFactory.createEmptyBorder(15, 10, 15, 10)
            );
            footer.setFont(new Font("SansSerif", Font.PLAIN, 12));
            footer.setForeground(new Color(100, 100, 100));

            frame.add(header, BorderLayout.NORTH);
            frame.add(welcome, BorderLayout.CENTER);
            frame.add(footer, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
    }
}
