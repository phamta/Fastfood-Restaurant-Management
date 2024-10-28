package TEST;

import javax.swing.*;
import java.awt.*;

public class ItemListPanel extends JPanel {
    
    public ItemListPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Arrange items vertically
        
        // Example: Adding 30 item panels
        for (int i = 1; i <= 10; i++) {
            JPanel itemPanel = createItemPanel("Item " + i);
            add(itemPanel);
        }
    }

    private JPanel createItemPanel(String itemName) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(itemName));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Optional: Add a border to each item panel
        panel.setPreferredSize(new Dimension(300, 30)); // Optional: Set a preferred size for each item panel
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Item List with Scroll Pane");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            ItemListPanel itemListPanel = new ItemListPanel();
            JScrollPane scrollPane = new JScrollPane(itemListPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            frame.getContentPane().add(scrollPane);
            frame.setSize(400, 400);
            frame.setVisible(true);
        });
    }
}
