package KEO;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import BLL.Combo_BLL;
import DTO.Itemplus;
import EDIT.RoundedPanel;

public class keocombo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel_menubar;
	private JPanel panel_content;
	private RoundedPanel panel_listcombo;
	private DefaultTableModel modeltable_combo;
	private JTable table_combo;
	private JTextField tf_searchcombo;
	private JLabel label_addcombo;
	private JLabel label_deletecombo;
	private JLabel label_search;
	private RoundedPanel panel_detailcombo;
	private JTextField tf_namecombo;
	private JTextField tf_pricecombo;
	private JPanel panel_listitemincombo;
	private JButton button_additem;
	private JLabel label_savechange;
	private RoundedPanel panel_deleteitemincombo;
	private JLabel label_deleteitemincombo;
	private JLabel label_cancel;
	private JPanel panel_listdeleteitem;
	private JPanel panel;
	
	private List<Map.Entry<JCheckBox, Itemplus>> checkBoxItemList = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					keocombo frame = new keocombo();
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
	public keocombo() {
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

		JPanel panel = new JPanel();
		panel.setBounds(30, 30, 230, 75);
		contentPane.add(panel);
		panel.setLayout(null);

		panel_menubar = new JPanel();
		panel_menubar.setBounds(30, 155, 230, 605);
		contentPane.add(panel_menubar);
		panel_menubar.setLayout(null);
		
		panel_content = new JPanel();
		panel_content.setBackground(new Color(254, 215, 124));
		panel_content.setBounds(310, 30, 1190, 730);
		contentPane.add(panel_content);
		panel_content.setLayout(null);
		
		panel_listcombo = new RoundedPanel(50, new Color(249, 230, 186));
		panel_listcombo.setBounds(0, 0, 710, 730);
		panel_content.add(panel_listcombo);
		panel_listcombo.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 75, 680, 620);
		scrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách combo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 15), new java.awt.Color(102, 102, 102))); // NOI18N
		panel_listcombo.add(scrollPane);

		String[] columnNames = { "Mã Combo", "Tên Combo", "Giá Bán" };
		modeltable_combo = new DefaultTableModel(columnNames, 0);

		table_combo = new JTable(modeltable_combo);
		scrollPane.setViewportView(table_combo);

		// hide column show item ID
		TableColumn column = table_combo.getColumnModel().getColumn(0);
		column.setMinWidth(0);
		column.setMaxWidth(0);
		column.setPreferredWidth(0);
		
		tf_searchcombo = new JTextField();
		tf_searchcombo.setBounds(530, 20, 150, 25);
		panel_listcombo.add(tf_searchcombo);
		tf_searchcombo.setColumns(10);
		
		label_addcombo = new JLabel("Thêm");
		label_addcombo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		label_addcombo.setHorizontalAlignment(SwingConstants.CENTER);
		label_addcombo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_addcombo.setBorder(new LineBorder(Color.BLACK, 1));
		label_addcombo.setBounds(120, 15, 120, 30);
		panel_listcombo.add(label_addcombo);
		
		label_deletecombo = new JLabel("Xóa");
		label_deletecombo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		label_deletecombo.setHorizontalAlignment(SwingConstants.CENTER);
		label_deletecombo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_deletecombo.setBorder(new LineBorder(Color.BLACK, 1));
		label_deletecombo.setBounds(270, 15, 120, 30);
		panel_listcombo.add(label_deletecombo);
		
		label_search = new JLabel("Tìm kiếm");
		label_search.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_search.setBounds(420, 20, 100, 25);
		panel_listcombo.add(label_search);
		
//		panel_detailcombo = new RoundedPanel(50, new Color(249, 230, 186));
//		panel_detailcombo.setBounds(730, 0, 450, 730);
//		panel_content.add(panel_detailcombo);
//		panel_detailcombo.setLayout(null);
		
//		GUI_paneldetailcombo();
		
		
		
		panel_deleteitemincombo = new RoundedPanel(50, new Color(249, 230, 186));
		panel_deleteitemincombo.setBounds(730, 0, 450, 730);
		panel_content.add(panel_deleteitemincombo);
		panel_deleteitemincombo.setLayout(null);
//		panel_deleteitemincombo.setVisible(false);
		
		GUI_paneldeleteitemincombo();
	}
	
	private void GUI_paneldetailcombo() {
		JLabel lblNewLabel = new JLabel("Tên");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 20, 45, 25);
		panel_detailcombo.add(lblNewLabel);

		tf_namecombo = new JTextField();
		tf_namecombo.setToolTipText("");
		tf_namecombo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tf_namecombo.setBackground(new Color(249, 230, 186));
		tf_namecombo.setBounds(170, 20, 250, 30);
		panel_detailcombo.add(tf_namecombo);
		tf_namecombo.setColumns(10);

		JLabel lblGiBn = new JLabel("Giá bán");
		lblGiBn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGiBn.setBounds(10, 70, 100, 25);
		panel_detailcombo.add(lblGiBn);

		tf_pricecombo = new JTextField();
		tf_pricecombo.setToolTipText("");
		tf_pricecombo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tf_pricecombo.setColumns(10);
		tf_pricecombo.setBackground(new Color(249, 230, 186));
		tf_pricecombo.setBounds(170, 70, 250, 30);
		panel_detailcombo.add(tf_pricecombo);
		
		label_savechange = new JLabel("Lưu thay đổi");
		label_savechange.setHorizontalAlignment(SwingConstants.CENTER);
		label_savechange.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_savechange.setBorder(new LineBorder(Color.BLACK, 1));
		label_savechange.setBounds(125, 115, 200, 30);
		panel_detailcombo.add(label_savechange);

		JLabel lblBaoGm = new JLabel("Bao gồm");
		lblBaoGm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBaoGm.setBounds(10, 145, 100, 25);
		panel_detailcombo.add(lblBaoGm);
		
		button_additem = new JButton("Add");
		button_additem.setFont(new Font("Tahoma", Font.PLAIN, 25));
		button_additem.setBounds(10, 190, 150, 50);
		button_additem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				panel_listcombo.setVisible(false);
//				panel_listadditem.setVisible(true);
//				List<Itemplus> list = Item_BLL.getInstance().getItemsNotInList(Combo_BLL.getInstance().getItemOfCombo(comboid));
//				displayItemtoCombo(list);
			}
		});
		panel_detailcombo.add(button_additem);

		JButton button_deleteitem = new JButton("Remove");
		button_deleteitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_detailcombo.setVisible(false);
				panel_deleteitemincombo.setVisible(true);
			}
		});
		button_deleteitem.setFont(new Font("Tahoma", Font.PLAIN, 25));
		button_deleteitem.setBounds(270, 190, 150, 50);
		panel_detailcombo.add(button_deleteitem);
		
		JLabel lblNewLabel_1 = new JLabel("Tên món");
		lblNewLabel_1.setBounds(20, 250, 100, 25);
		panel_detailcombo.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1_1 = new JLabel("Số lượng");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(250, 250, 90, 25);
		panel_detailcombo.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Giá bán");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(350, 250, 80, 25);
		panel_detailcombo.add(lblNewLabel_1_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 280, 450, 240);
		panel_detailcombo.add(scrollPane_1);

		panel_listitemincombo = new JPanel();
		scrollPane_1.setViewportView(panel_listitemincombo);
		panel_listitemincombo.setBackground(new Color(249, 230, 186));
		panel_listitemincombo.setLayout(null);

		JLabel lblnhMinhHa = new JLabel("Ảnh minh họa");
		lblnhMinhHa.setVerticalAlignment(SwingConstants.BOTTOM);
		lblnhMinhHa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblnhMinhHa.setBounds(10, 540, 150, 25);
		panel_detailcombo.add(lblnhMinhHa);

		JButton btnNewButton = new JButton("Chọn ảnh minh họa");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(170, 540, 250, 30);
		panel_detailcombo.add(btnNewButton);

		JLabel label_avatar = new JLabel("");
		label_avatar.setBounds(250, 600, 100, 100);
		panel_detailcombo.add(label_avatar);
	}
	
	private void GUI_paneldeleteitemincombo() {
		label_deleteitemincombo = new JLabel("Xóa");
		label_deleteitemincombo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List<Itemplus> selectedItems = getSelectedItems();
				Combo_BLL.getInstance().deleteComboDetail(selectedItems, "CMBS001");
				panel_detailcombo.setVisible(false);
				panel_deleteitemincombo.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		label_deleteitemincombo.setHorizontalAlignment(SwingConstants.CENTER);
		label_deleteitemincombo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_deleteitemincombo.setBorder(new LineBorder(Color.BLACK, 1));
		label_deleteitemincombo.setBounds(20, 20, 120, 30);
		panel_deleteitemincombo.add(label_deleteitemincombo);
		
		label_cancel = new JLabel("Hủy");
		label_cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_detailcombo.setVisible(false);
				panel_deleteitemincombo.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
				label_cancel.setCursor(cursor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Cursor cursor = Cursor.getDefaultCursor();
				label_cancel.setCursor(cursor);
			}
		});
		label_cancel.setHorizontalAlignment(SwingConstants.CENTER);
		label_cancel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_cancel.setBorder(new LineBorder(Color.BLACK, 1));
		label_cancel.setBounds(300, 20, 120, 30);
		panel_deleteitemincombo.add(label_cancel);
		
		panel_listdeleteitem = new JPanel();
		panel_listdeleteitem.setBackground(new Color(249, 230, 186));
		panel_listdeleteitem.setBounds(0, 70, 450, 640);
		panel_deleteitemincombo.add(panel_listdeleteitem);
		panel_listdeleteitem.setLayout(null);
		
		List<Itemplus> list = Combo_BLL.getInstance().getItemOfCombo("CMBS001");
		displayListItemtoDelete(list);
		
	}
	
	private JPanel createPanelItem(Itemplus itemplus, int y) {
		panel = new JPanel();
		panel.setBackground(new Color(249, 230, 186));
		panel.setBounds(0, y, 450, 30);
		panel_listdeleteitem.add(panel);
		panel.setLayout(null);
		
		JCheckBox checkbox_delete = new JCheckBox();
		checkbox_delete.setBackground(new Color(249, 230, 186));
		checkbox_delete.setBounds(5, 0, 20, 30);
		panel.add(checkbox_delete);
		
		JLabel label_nameitem = new JLabel(itemplus.getItemName());
		label_nameitem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_nameitem.setBounds(30, 0, 400, 30);
		panel.add(label_nameitem);
		
		checkBoxItemList.add(new AbstractMap.SimpleEntry<>(checkbox_delete, itemplus));
		
		return panel;
	}
	
	private void displayListItemtoDelete(List<Itemplus> list) {
		panel_listdeleteitem.removeAll();
		int y = 10;
		
		for(Itemplus itemplus: list) {
			JPanel panel = createPanelItem(itemplus, y);
			panel_listdeleteitem.add(panel);
			y += panel.getHeight() + 10;
		}
		
		panel_listdeleteitem.revalidate();
		panel_listdeleteitem.repaint();
	}
	
	private List<Itemplus> getSelectedItems() {
	    List<Itemplus> selectedItems = new ArrayList<>();
	    
	    // Duyệt qua danh sách cặp JCheckBox và Itemplus
	    for (Map.Entry<JCheckBox, Itemplus> entry : checkBoxItemList) {
	        JCheckBox checkBox = entry.getKey();
	        Itemplus item = entry.getValue();
	        
	        // Nếu JCheckBox được chọn, thêm Itemplus vào danh sách kết quả
	        if (checkBox.isSelected()) {
	            selectedItems.add(item);
	        }
	    }
	    
	    return selectedItems;
	}
}
