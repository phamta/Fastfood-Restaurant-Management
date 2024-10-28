package VIEW;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import BLL.Category_BLL;
import BLL.Item_BLL;
import DTO.Category;
import DTO.Item;
import EDIT.RoundedPanel;

public class Category_VIEW extends RoundedPanel {
	private static final long serialVersionUID = 1L;
	private RoundedPanel panel_listcategory;
	private JTextField tf_searchcategory;
	private JTable table_listcategory;
	private DefaultTableModel modeltable_listcategory;
	private JLabel label_addcategory;
	private JLabel label_cancel;
	private RoundedPanel panel_detailcategory;
	private JTextField tf_namecategory;
	private JLabel label_savechange;
	
	private String categoryid = null;
	private JRadioButton rdbutton_active;
	private JRadioButton rdbutton_disable;
	private JRadioButton rdbutton_savestate;
	private JScrollPane scrollPane_listitem;
	private JPanel panel_listitem;

	public Category_VIEW(int radius, Color backgroundColor) {
		super(radius, backgroundColor);
		setLayout(null);
        setBounds(0, 0, 1190, 730);
        
        panel_listcategory = new RoundedPanel(50, new Color(249, 230, 186));
		panel_listcategory.setBounds(0, 0, 710, 730);
		this.add(panel_listcategory);
		panel_listcategory.setLayout(null);

		tf_searchcategory = new JTextField();
		tf_searchcategory.setBounds(500, 20, 150, 25);
		panel_listcategory.add(tf_searchcategory);
		tf_searchcategory.setColumns(10);
		tf_searchcategory.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				searchCategory();
			}

			public void removeUpdate(DocumentEvent e) {
				searchCategory();
			}

			public void insertUpdate(DocumentEvent e) {
				searchCategory();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 75, 680, 620);
		panel_listcategory.add(scrollPane);

		table_listcategory = new JTable();
		scrollPane.setViewportView(table_listcategory);
		

		String[] columnNames = { "Số thứ tự", "Danh mục", "Tình trạng" };
		modeltable_listcategory = new DefaultTableModel(columnNames, 0);

		label_addcategory = new JLabel("Thêm");
		label_addcategory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label_addcategory.setVisible(false);
				label_cancel.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		label_addcategory.setHorizontalAlignment(SwingConstants.CENTER);
		label_addcategory.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_addcategory.setBorder(new LineBorder(Color.BLACK, 1));
		label_addcategory.setBounds(320, 13, 120, 30);
		panel_listcategory.add(label_addcategory);

		label_cancel = new JLabel("Hủy");
		label_cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label_addcategory.setVisible(true);
				label_cancel.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		label_cancel.setHorizontalAlignment(SwingConstants.CENTER);
		label_cancel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_cancel.setBorder(new LineBorder(Color.BLACK, 1));
		label_cancel.setBounds(320, 13, 120, 30);
		panel_listcategory.add(label_cancel);
		label_cancel.setVisible(false);
		
		panel_detailcategory = new RoundedPanel(50, new Color(249, 230, 186));
		panel_detailcategory.setBounds(730, 0, 460, 710);
		this.add(panel_detailcategory);
		panel_detailcategory.setLayout(null);

		JLabel label_namecategory = new JLabel("Tên");
		label_namecategory.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_namecategory.setBounds(10, 20, 100, 25);
		panel_detailcategory.add(label_namecategory);

		tf_namecategory = new JTextField();
		tf_namecategory.setToolTipText("");
		tf_namecategory.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tf_namecategory.setColumns(10);
		tf_namecategory.setBackground(new Color(249, 230, 186));
		tf_namecategory.setBounds(140, 20, 270, 30);
		panel_detailcategory.add(tf_namecategory);
		tf_namecategory.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Called when a key is typed
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Called when a key is pressed
            }

            @Override
            public void keyReleased(KeyEvent e) {
                turnonSaveMode();
            }
        });

		JLabel lblBaoGm = new JLabel("Bao gồm");
		lblBaoGm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBaoGm.setBounds(10, 158, 100, 25);
		panel_detailcategory.add(lblBaoGm);

		JLabel lblNewLabel_1 = new JLabel("Tên món");
		lblNewLabel_1.setBounds(30, 193, 85, 25);
		panel_detailcategory.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel_1_2 = new JLabel("Giá bán");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(360, 193, 75, 25);
		panel_detailcategory.add(lblNewLabel_1_2);

		JLabel lblNewLabel = new JLabel("Tình trạng");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 70, 100, 25);
		panel_detailcategory.add(lblNewLabel);

		rdbutton_active = new JRadioButton("Đang bán");
		rdbutton_active.setBackground(new Color(249, 230, 186));
		rdbutton_active.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbutton_active.setBounds(140, 70, 120, 25);
		panel_detailcategory.add(rdbutton_active);
		rdbutton_active.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleRadioButtonChange(rdbutton_disable);
			}
		});

		rdbutton_disable = new JRadioButton("Dừng bán");
		rdbutton_disable.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbutton_disable.setBackground(new Color(249, 230, 186));
		rdbutton_disable.setBounds(290, 70, 130, 25);
		panel_detailcategory.add(rdbutton_disable);
		rdbutton_disable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleRadioButtonChange(rdbutton_disable);
			}
		});
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbutton_active);
		group.add(rdbutton_disable);
		
		rdbutton_savestate = null;

		label_savechange = new JLabel("Lưu thay đổi");
		label_savechange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String catagoryname = tf_namecategory.getText().trim();
				int Status = rdbutton_active.isSelected() ? 1 : 0;
				
				Category category = Category_BLL.getInstance().getCategoryBuID(categoryid);
				category.setCategoryName(catagoryname);
				category.setStatus(Status);
				if(categoryid != null) {
					Category_BLL.getInstance().updateCategory(category);
				}
				else {
					Category_BLL.getInstance().insertCategory(category);
				}
				turnoffSaveMode();
				label_addcategory.setVisible(true);
				label_cancel.setVisible(false);
				loadAllCategory();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		label_savechange.setHorizontalAlignment(SwingConstants.CENTER);
		label_savechange.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_savechange.setBorder(new LineBorder(Color.BLACK, 1));
		label_savechange.setBounds(130, 115, 200, 35);
		panel_detailcategory.add(label_savechange);
		
		scrollPane_listitem = new JScrollPane();
		scrollPane_listitem.setBounds(0, 225, 460, 460);
		panel_detailcategory.add(scrollPane_listitem);

		panel_listitem = new JPanel();
		scrollPane_listitem.setViewportView(panel_listitem);
		panel_listitem.setBackground(new Color(249, 230, 186));
		panel_listitem.setPreferredSize(new Dimension(440, 1000));
		panel_listitem.setLayout(null);
		
		table_listcategory.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
					int selectedRow = table_listcategory.getSelectedRow();
					if (selectedRow != -1) {
						categoryid = modeltable_listcategory.getValueAt(selectedRow, 0).toString();
						showDetailCategory(categoryid);
					}
				}
			}
		});
		
		loadAllCategory();
	}
	
	private void loadAllCategory() {
		List<Category> list = Category_BLL.getInstance().getAllCategory();
		for(Category category: list) {
			Object[] row = {category.getCategoryID(), category.getCategoryName(), category.getStatus() == 1? "Đang bán": "Dừng bán"};
			modeltable_listcategory.addRow(row);
		}
		table_listcategory.setModel(modeltable_listcategory);
	}
	
	private void showDetailCategory(String categoryid) {
		Category category = Category_BLL.getInstance().getCategoryBuID(categoryid);
		tf_namecategory.setText(category.getCategoryName());
		if(category.getStatus() == 1) {
			rdbutton_active.setSelected(true);
			rdbutton_savestate = rdbutton_active;
		}
		else {
			rdbutton_disable.setSelected(true);
			rdbutton_savestate = rdbutton_disable;
		}
		
		List<Item> list = Item_BLL.getInstance().getAllByType(category.getCategoryName());
		displayItemInCategory(list);
	}
	
	private JPanel createPanelItem(Item item, int y) {
		JPanel panel = new JPanel();
		panel.setBounds(0, y, 460, 30);
		panel.setBackground(new Color(249, 230, 186));
		panel.setLayout(null);

		JLabel label_name = new JLabel(item.getItemName());
		label_name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_name.setBounds(5, 0, 340, 30);
		panel.add(label_name);

		JLabel label_price = new JLabel("" + item.getPrice());
		label_price.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_price.setBounds(350, 0, 90, 30);
		label_price.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_price);
		
		return panel;
	}
	
	private void displayItemInCategory(List<Item> list) {
		if (panel_listitem != null) {
	        panel_listitem.removeAll();
	    }
		int y = 10;
		for(Item item: list) {
			JPanel panel = createPanelItem(item, y);
			panel_listitem.add(panel);
			 y += panel.getHeight() + 10;
		}
		if(y > scrollPane_listitem.getHeight()) {
			scrollPane_listitem.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		}
		else {
			scrollPane_listitem.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		}
		panel_listitem.revalidate();
		panel_listitem.repaint();
	}
	
	private void handleRadioButtonChange(JRadioButton rdbutton_current) {
		if (rdbutton_current != rdbutton_savestate) {
			rdbutton_savestate = rdbutton_current;
			if (rdbutton_current == rdbutton_active) {
				turnonSaveMode();
			} else if (rdbutton_current == rdbutton_disable) {
				turnonSaveMode();
			}
		}
	}

	private void turnonSaveMode() {
		label_savechange.setBorder(new LineBorder(Color.GREEN, 1));
		label_savechange.setForeground(Color.green);
	}
	private void turnoffSaveMode() {
		label_savechange.setBorder(new LineBorder(Color.black, 1));
		label_savechange.setForeground(Color.black);
	}
	
	private void searchCategory() {
		modeltable_listcategory.setRowCount(0);
		String search = tf_searchcategory.getText();
		List<Category> list = Category_BLL.getInstance().searchCategoryByName(search);
		for(Category category: list) {
			Object[] row = {category.getCategoryID(), category.getCategoryName(), category.getStatus() == 1? "Đang bán": "Dừng bán"};
			modeltable_listcategory.addRow(row);
		}
		table_listcategory.setModel(modeltable_listcategory);
	}

}
