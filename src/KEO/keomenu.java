package KEO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import EDIT.RoundedPanel;
import javax.swing.JScrollPane;

public class keomenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					keomenu frame = new keomenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public keomenu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set frame to full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Get screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width, screenSize.height);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(254, 215, 124));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        setContentPane(contentPane);
        
        RoundedPanel panel = new RoundedPanel(50, new Color(249, 230, 186));
        contentPane.add(panel);
        panel.setBounds(30, 30, 713, 696);
        panel.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 30, 693, 633);
        panel.add(scrollPane);
        
        JPanel panel_1 = new JPanel();
        scrollPane.setViewportView(panel_1);
        panel_1.setBackground(new Color(249, 230, 186));
	}

}
