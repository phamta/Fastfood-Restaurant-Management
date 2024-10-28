package VIEW;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import BLL.Account_BLL;
import BLL.Combo_BLL;
import BLL.Item_BLL;
import DTO.Account;
import DTO.Combo;
import DTO.Itemplus;
import EDIT.RoundedPanel;

public class Combo_VIEW extends JPanel {
	private static final long serialVersionUID = 1L;
	private RoundedPanel panel_listcombo;
	private DefaultTableModel modeltable_combo;
	private JTable table_combo;
	private JTextField tf_searchcombo;

	private RoundedPanel panel_detailcombo;
	private JTextField tf_namecombo;
	private JTextField tf_pricecombo;
	private JPanel panel_listitemincombo; // display list of item plus in combo
	private JButton button_additem;

	private RoundedPanel panel_listadditem;
	private JTextField tf_searchitem;
	private JComboBox<String> comboBox_typeofitem;
	private JPanel panel_menu;
	private JLabel label_additemtocombo;
	private JLabel label_cancelitemtocombo;

	private int countClick = 0; // variable to count number of item be chosen to add to combo
	private String comboid = null;
	private RoundedPanel panel_deleteitemincombo;
	private JScrollPane scrollPanepanel_menu;
	private JScrollPane scrollPane_listitemincombo;
	private JLabel label_addcombo;
	private JLabel label_deletecombo;
	private JPanel panel_listdeleteitem;

	private List<Map.Entry<JCheckBox, Itemplus>> checkBoxItemList = new ArrayList<>();
	private JLabel label_avatar;
	private JLabel label_savechange;
	private File selectedFile;

	public Combo_VIEW() {
		setLayout(null);
		setBounds(0, 0, 1190, 730);

		JPanel panel_content = new JPanel();
		panel_content.setBounds(0, 0, 1190, 730);
		panel_content.setLayout(null);
		panel_content.setBackground(new Color(254, 215, 124));
		add(panel_content);

		panel_listcombo = new RoundedPanel(50, new Color(249, 230, 186));
		panel_listcombo.setBounds(0, 0, 710, 730);
		panel_content.add(panel_listcombo);
		panel_listcombo.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 75, 680, 620);
		scrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách combo",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Tahoma", 0, 15), new java.awt.Color(102, 102, 102))); // NOI18N
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

		loadComboData();

		table_combo.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
					int selectedRow = table_combo.getSelectedRow();
					if (selectedRow != -1) {
						comboid = modeltable_combo.getValueAt(selectedRow, 0).toString();

						showDetailCombo(comboid);
					}
				}
			}
		});

		label_addcombo = new JLabel("Thêm");
		label_addcombo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboid = null;
				clearDetailCombo();
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
				if (comboid != null) {
					Combo_BLL.getInstance().deleteCombo(comboid);
					JOptionPane.showMessageDialog(null, "Đã xóa combo vừa chọn");
					loadComboData();
				}
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

		JLabel label_search = new JLabel("Tìm kiếm");
		label_search.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_search.setBounds(420, 20, 100, 25);
		panel_listcombo.add(label_search);

		tf_searchcombo = new JTextField();
		tf_searchcombo.setBounds(530, 20, 150, 25);
		panel_listcombo.add(tf_searchcombo);
		tf_searchcombo.setColumns(10);

		panel_detailcombo = new RoundedPanel(50, new Color(249, 230, 186));
		panel_detailcombo.setBounds(730, 0, 450, 730);
		panel_content.add(panel_detailcombo);
		panel_detailcombo.setLayout(null);

		GUI_paneldetailcombo();

		panel_listadditem = new RoundedPanel(50, new Color(249, 230, 186));
		panel_listadditem.setBounds(10, 10, 700, 710);
		panel_content.add(panel_listadditem);
		panel_listadditem.setLayout(null);
		panel_listadditem.setVisible(false);

		tf_searchitem = new JTextField();
		tf_searchitem.setBounds(475, 20, 150, 25);
		panel_listadditem.add(tf_searchitem);
		tf_searchitem.setColumns(10);

		comboBox_typeofitem = new JComboBox<>();
		comboBox_typeofitem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_typeofitem.setBounds(30, 20, 120, 30);
		panel_listadditem.add(comboBox_typeofitem);

		scrollPanepanel_menu = new JScrollPane();
		scrollPanepanel_menu.setBounds(10, 60, 680, 640);
//		scrollPanepanel_menu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPanepanel_menu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel_listadditem.add(scrollPanepanel_menu);

		panel_menu = new JPanel();
		scrollPanepanel_menu.setViewportView(panel_menu);
		panel_menu.setBackground(new Color(249, 230, 186));
		panel_menu.setLayout(null);
		panel_menu.setPreferredSize(new Dimension(680, 1400));

		label_additemtocombo = new JLabel("Add to combo");
		label_additemtocombo.setName("label_additemtocombo");
		label_additemtocombo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_additemtocombo.setBackground(panel_listadditem.getBackground());
		label_additemtocombo.setBounds(200, 20, 150, 35);
		label_additemtocombo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		label_additemtocombo.addMouseListener(createMouseListener(label_additemtocombo));
		label_additemtocombo.addMouseMotionListener(createMouseMotionListener(label_additemtocombo));
		label_additemtocombo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_listadditem.add(label_additemtocombo);

		label_cancelitemtocombo = new JLabel("Cancel");
		label_cancelitemtocombo.setName("label_cancelitemtocombo");
		label_cancelitemtocombo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_cancelitemtocombo.setBounds(360, 20, 100, 35);
		label_cancelitemtocombo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		label_cancelitemtocombo.setHorizontalAlignment(SwingConstants.CENTER);
		label_cancelitemtocombo.addMouseListener(createMouseListener(label_cancelitemtocombo));
		label_cancelitemtocombo.addMouseMotionListener(createMouseMotionListener(label_cancelitemtocombo));
		panel_listadditem.add(label_cancelitemtocombo);

		panel_deleteitemincombo = new RoundedPanel(50, new Color(249, 230, 186));
		panel_deleteitemincombo.setBounds(730, 10, 450, 710);
		panel_content.add(panel_deleteitemincombo);
		panel_deleteitemincombo.setLayout(null);
		panel_deleteitemincombo.setVisible(false);

		GUI_paneldeleteitemincombo();
	}
	
	private void clearDetailCombo() {
		tf_namecombo.setText("");
		tf_pricecombo.setText("");
		panel_listitemincombo.removeAll();
		label_avatar.setIcon(null);
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
		tf_namecombo.addKeyListener(new KeyListener() {
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
		tf_pricecombo.addKeyListener(new KeyListener() {
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

		label_savechange = new JLabel("Lưu thay đổi");
		label_savechange.setHorizontalAlignment(SwingConstants.CENTER);
		label_savechange.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_savechange.setBorder(new LineBorder(Color.BLACK, 1));
		label_savechange.setBounds(125, 115, 200, 30);
		panel_detailcombo.add(label_savechange);
		label_savechange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (comboid != null) {
					Combo combo = Combo_BLL.getInstance().getComboByID(comboid);
					combo.setComboName(tf_namecombo.getText());
					combo.setPrice(Integer.parseInt(tf_pricecombo.getText()));
					Combo_BLL.getInstance().updateCombo(combo);
					turnoffSaveMode();
					if (selectedFile != null) {
						try {
							updateImage(selectedFile, combo.getImage());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				else {
					String comboid_new = Combo_BLL.getInstance().generateNextComboID();
					String comboname = tf_namecombo.getText();
					int price = Integer.parseInt(tf_pricecombo.getText());
					String image = "/IMAGE/" + comboname + ".jpg";
					
					Combo combo = new Combo(comboid_new, comboname, price);
					combo.setImage(image);
					try {
						saveImage(selectedFile, comboname);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Combo_BLL.getInstance().addCombo(combo);
					JOptionPane.showMessageDialog(null, "Đã thêm combo " + comboname);
					loadComboData();
					showDetailCombo(comboid_new);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});

		JLabel lblBaoGm = new JLabel("Bao gồm");
		lblBaoGm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBaoGm.setBounds(10, 145, 100, 25);
		panel_detailcombo.add(lblBaoGm);

		button_additem = new JButton("Add");
		button_additem.setFont(new Font("Tahoma", Font.PLAIN, 25));
		button_additem.setBounds(10, 190, 150, 50);
		button_additem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_listcombo.setVisible(false);
				panel_listadditem.setVisible(true);
				List<Itemplus> list = Item_BLL.getInstance()
						.getItemsNotInList(Combo_BLL.getInstance().getItemOfCombo(comboid));
				displayItemtoCombo(list);
			}
		});
		panel_detailcombo.add(button_additem);

		JButton button_deleteitem = new JButton("Remove");
		button_deleteitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Itemplus> list = Combo_BLL.getInstance().getItemOfCombo("CMBS001");
				displayListItemtoDelete(list);
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

		scrollPane_listitemincombo = new JScrollPane();
		scrollPane_listitemincombo.setBounds(0, 280, 450, 240);
		panel_detailcombo.add(scrollPane_listitemincombo);

		panel_listitemincombo = new JPanel();
		scrollPane_listitemincombo.setViewportView(panel_listitemincombo);
		panel_listitemincombo.setBackground(new Color(249, 230, 186));
		panel_listitemincombo.setLayout(null);

		JLabel lblnhMinhHa = new JLabel("Ảnh minh họa");
		lblnhMinhHa.setVerticalAlignment(SwingConstants.BOTTOM);
		lblnhMinhHa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblnhMinhHa.setBounds(10, 540, 150, 25);
		panel_detailcombo.add(lblnhMinhHa);

		JButton button_openfiledialog = new JButton("Chọn ảnh minh họa");
		button_openfiledialog.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button_openfiledialog.setBounds(170, 540, 250, 30);
		panel_detailcombo.add(button_openfiledialog);
		button_openfiledialog.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Tạo JFileChooser
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				int result = fileChooser.showOpenDialog(Combo_VIEW.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();
					if (selectedFile != null) {
						ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
						Image image = imageIcon.getImage(); // Lấy đối tượng Image từ ImageIcon
						Image scaledImage = image.getScaledInstance(label_avatar.getWidth(), label_avatar.getHeight(),
								java.awt.Image.SCALE_SMOOTH); // Thay đổi kích thước ảnh
						ImageIcon scaledIcon = new ImageIcon(scaledImage);
						label_avatar.setIcon(scaledIcon);
						turnonSaveMode();
					} else {
						System.out.println("No file selected.");
					}

				} else {
					System.out.println("File selection was cancelled.");
				}
			}
		});

		label_avatar = new JLabel("");
		label_avatar.setBounds(250, 600, 100, 100);
		panel_detailcombo.add(label_avatar);
	}
	
	private void saveImage(File selectedFile, String comboname) throws IOException {
		String newFileName = comboname + ".jpg"; // Hoặc định dạng ảnh phù hợp
		Path destinationPath = Paths.get("src/IMAGE", newFileName);

		File imageDir = new File("src/IMAGE");
		if (!imageDir.exists()) {
			imageDir.mkdirs();
		}

		Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
	}
	
	private static void updateImage(File selectedFile, String imagepath) throws IOException {
		String oldFileName = imagepath.substring(imagepath.lastIndexOf("/") + 1);
		Path destinationPath = Paths.get("src/IMAGE", oldFileName);

		File imageDir = new File("src/IMAGE");
		if (!imageDir.exists()) {
			imageDir.mkdirs();
		}

		// If the old image exists, delete it
		if (Files.exists(destinationPath)) {
			Files.delete(destinationPath);
		}

		// Copy the new image to the destination path
		Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
	}

	private void GUI_paneldeleteitemincombo() {
		JLabel label_deleteitemincombo = new JLabel("Xóa");
		label_deleteitemincombo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List<Itemplus> selectedItems = getSelectedItems();
				Combo_BLL.getInstance().deleteComboDetail(selectedItems, comboid);
				panel_detailcombo.setVisible(true);
				panel_deleteitemincombo.setVisible(false);
				showDetailCombo(comboid);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
				label_deleteitemincombo.setCursor(cursor);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Cursor cursor = Cursor.getDefaultCursor();
				label_deleteitemincombo.setCursor(cursor);
			}
		});
		label_deleteitemincombo.setHorizontalAlignment(SwingConstants.CENTER);
		label_deleteitemincombo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_deleteitemincombo.setBorder(new LineBorder(Color.BLACK, 1));
		label_deleteitemincombo.setBounds(20, 20, 120, 30);
		panel_deleteitemincombo.add(label_deleteitemincombo);

		JLabel label_canceldeleteitemincombo = new JLabel("Hủy");
		label_canceldeleteitemincombo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_detailcombo.setVisible(true);
				panel_deleteitemincombo.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
				label_canceldeleteitemincombo.setCursor(cursor);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Cursor cursor = Cursor.getDefaultCursor();
				label_canceldeleteitemincombo.setCursor(cursor);
			}
		});
		label_canceldeleteitemincombo.setHorizontalAlignment(SwingConstants.CENTER);
		label_canceldeleteitemincombo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_canceldeleteitemincombo.setBorder(new LineBorder(Color.BLACK, 1));
		label_canceldeleteitemincombo.setBounds(300, 20, 120, 30);
		panel_deleteitemincombo.add(label_canceldeleteitemincombo);

		panel_listdeleteitem = new JPanel();
		panel_listdeleteitem.setBackground(new Color(249, 230, 186));
		panel_listdeleteitem.setBounds(0, 70, 450, 640);
		panel_deleteitemincombo.add(panel_listdeleteitem);
		panel_listdeleteitem.setLayout(null);

	}

	private JPanel createPanelItem(Itemplus itemplus, int y) {
		JPanel panel = new JPanel();
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

		for (Itemplus itemplus : list) {
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

	private void loadComboData() {
		modeltable_combo.setRowCount(0);
		List<Combo> list = Combo_BLL.getInstance().getAllCombo();
		for (Combo combo : list) {
			Object[] row = { combo.getComboID(), combo.getComboName(), combo.getPrice() };
			modeltable_combo.addRow(row);
		}
		table_combo.setModel(modeltable_combo);
	}

	private void showDetailCombo(String comboid) {
		addItemPanelsToList(Combo_BLL.getInstance().getItemOfCombo(comboid));
		Combo combo = Combo_BLL.getInstance().getComboByID(comboid);
		tf_namecombo.setText(combo.getComboName());
		tf_pricecombo.setText("" + combo.getPrice());

		String s = combo.getImage();
		ImageIcon icon = new ImageIcon(getClass().getResource(s));
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(label_avatar.getWidth(), label_avatar.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		label_avatar.setIcon(scaledIcon);
	}

	private JPanel createItemplusPanel(Itemplus itemplus, int x, int y) {
		JPanel panel = new JPanel();
		panel.setBounds(x, y, 160, 180);
		panel.setLayout(null);

		JLabel lable_image = new JLabel();
		lable_image.setBounds(0, 0, 160, 80);
		panel.add(lable_image);

		ImageIcon icon = new ImageIcon(getClass().getResource(itemplus.getImagePath()));
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(lable_image.getWidth(), lable_image.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		lable_image.setIcon(scaledIcon);

		JLabel label_nameitem = new JLabel(itemplus.getItemName());
		label_nameitem.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_nameitem.setBounds(10, 80, 150, 30);
		panel.add(label_nameitem);

		JLabel label_priceitem = new JLabel(itemplus.getPrice() + "");
		label_priceitem.setForeground(new Color(255, 0, 0));
		label_priceitem.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_priceitem.setBounds(10, 140, 150, 30);
		panel.add(label_priceitem);

		JTextField tf_quantity = new JTextField();
		tf_quantity.setFont(new Font("Tahoma", Font.BOLD, 15));
		tf_quantity.setText("1");
		tf_quantity.setBounds(10, 110, 50, 30);
		tf_quantity.setBackground(panel.getBackground());
		tf_quantity.setBorder(BorderFactory.createEmptyBorder());
		tf_quantity.setColumns(10);
		tf_quantity.setEditable(false);
		panel.add(tf_quantity);
		tf_quantity.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateQuantity();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateQuantity();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateQuantity();
			}

			private void updateQuantity() {
				try {
					int quantity = Integer.parseInt(tf_quantity.getText());
					itemplus.setQuantity(quantity);
				} catch (NumberFormatException ex) {
//					tf_quantity.setText("1");
					itemplus.setQuantity(1);
				}
			}
		});

		panel.putClientProperty("key", 0);
		panel.putClientProperty("itemplus", itemplus);

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int value = (int) panel.getClientProperty("key");
				if (value == 0) {
					panel.setBackground(Color.GREEN); // Trở về màu nền ban đầu
					tf_quantity.setEditable(true);
					panel.putClientProperty("key", 1);
					tf_quantity.setBackground(panel.getBackground());
					++countClick;
					handelLabelAdditemtocomboClick(label_additemtocombo);
				} else {
					panel.setBackground(null); // Chuyển sang màu xanh
					tf_quantity.setEditable(false);
					panel.putClientProperty("key", 0);
					tf_quantity.setBackground(panel.getBackground());
					--countClick;
					handelLabelAdditemtocomboClick(label_additemtocombo);
				}
			}
		});

		return panel;
	}

	private void displayItemtoCombo(List<Itemplus> list) {
		int x = 10;
		int y = 10;
		int padding = 10;
		int panelWidth = 160;
		int panelHeight = 180;
		int maxWidth = 680;

		panel_menu.removeAll(); // Clear existing panels

		for (Itemplus itemplus : list) {
			if (x + panelWidth > maxWidth) {
				x = 10;
				y += panelHeight + padding;
			}
			JPanel panel = createItemplusPanel(itemplus, x, y);
			panel_menu.add(panel);
			x += panelWidth + padding;
		}

		if (y > scrollPanepanel_menu.getHeight()) {
			scrollPanepanel_menu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		} else {
			scrollPanepanel_menu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		}

		panel_menu.revalidate();
		panel_menu.repaint();
	}

	private void turnonSaveMode() {
		label_savechange.setBorder(new LineBorder(Color.GREEN, 1));
		label_savechange.setForeground(Color.green);
	}

	private void turnoffSaveMode() {
		label_savechange.setBorder(new LineBorder(Color.black, 1));
		label_savechange.setForeground(Color.black);
	}

	private JPanel createItemPanel(Itemplus item) {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(249, 230, 186));
		panel.setPreferredSize(new Dimension(430, 30));
		panel.setLayout(null);

		JTextField txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtName.setText(item.getItemName());
		txtName.setBackground(new Color(249, 230, 186));
		txtName.setBounds(0, 0, 230, 30);
		txtName.setBorder(BorderFactory.createEmptyBorder());
		panel.add(txtName);
		txtName.setColumns(10);

		JTextField txtPrice = new JTextField();
		txtPrice.setText("" + item.getTotal());
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPrice.setBackground(new Color(249, 230, 186));
		txtPrice.setBounds(330, 0, 100, 30);
		txtPrice.setBorder(BorderFactory.createEmptyBorder());
		txtPrice.setHorizontalAlignment(JTextField.CENTER);
		txtPrice.setEditable(false);
		panel.add(txtPrice);
		txtPrice.setColumns(10);

		JTextField txtQuantity = new JTextField();
		txtQuantity.setBackground(new Color(249, 230, 186));
		txtQuantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtQuantity.setText(String.valueOf(item.getQuantity()));
		txtQuantity.setBounds(260, 0, 30, 30);
		txtQuantity.setBorder(BorderFactory.createEmptyBorder());
		txtQuantity.setHorizontalAlignment(JTextField.CENTER);
		panel.add(txtQuantity);
		txtQuantity.setColumns(10);

		return panel;
	}

	public void addItemPanelsToList(List<Itemplus> itemList) {
		panel_listitemincombo.removeAll(); // Clear any existing components

		int yOffset = 10;
		for (Itemplus item : itemList) {
			JPanel itemPanel = createItemPanel(item);
			itemPanel.setBounds(10, yOffset, 430, 30);
			panel_listitemincombo.add(itemPanel);
			yOffset += 40; // 30 (height of panel) + 10 (space between panels)
		}

		if (yOffset > scrollPane_listitemincombo.getHeight()) {
			scrollPane_listitemincombo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		} else {
			scrollPane_listitemincombo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		}

		// Refresh the panel to show the added components
		panel_listitemincombo.revalidate();
		panel_listitemincombo.repaint();
	}

	private void addItemToCombo(String comboid, List<Itemplus> list) {
		Combo_BLL.getInstance().addItemToCombo(comboid, list);
	}

	private void handelLabelAdditemtocomboClick(JLabel label) {
		if (countClick != 0) {
			label.setBorder(BorderFactory.createLineBorder(Color.GREEN));
			label.setForeground(Color.GREEN);
		} else {
			label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			label.setForeground(Color.BLACK);
		}
	}

	private MouseAdapter createMouseListener(JLabel label) {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String choice = label.getName();

				switch (choice) {
				case "label_additemtocombo":
					List<Itemplus> list = new ArrayList<Itemplus>();
					for (Component comp : panel_menu.getComponents()) {
						if (comp instanceof JPanel) {
							JPanel itemPanel = (JPanel) comp;
							int checkChoose = (int) itemPanel.getClientProperty("key");
							if (checkChoose != 0) {
								Itemplus itemplus = (Itemplus) itemPanel.getClientProperty("itemplus");
								list.add(itemplus);
							}
						}
					}
					addItemToCombo(comboid, list);
					List<Itemplus> listitemexist = Combo_BLL.getInstance().getItemOfCombo(comboid);
					addItemPanelsToList(listitemexist);
					displayItemtoCombo(Item_BLL.getInstance().getItemsNotInList(listitemexist));
					countClick = 0;
					handelLabelAdditemtocomboClick(label_additemtocombo);
					break;

				case "label_cancelitemtocombo":
					panel_listadditem.setVisible(false);
					panel_listcombo.setVisible(true);
					countClick = 0;
					handelLabelAdditemtocomboClick(label_additemtocombo);
					break;

				default:
					break;
				}

			}
		};
	}

	private MouseAdapter createMouseMotionListener(JLabel label) {
		return new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if (countClick != 0) {
					Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
					label.setCursor(cursor);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Cursor cursor = Cursor.getDefaultCursor();
				label.setCursor(cursor);
			}
		};
	}
}
