package VIEW;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import BLL.Bonusitem_BLL;
import BLL.Category_BLL;
import BLL.Item_BLL;
import DTO.Bonusitem;
import DTO.Category;
import DTO.Item;
import EDIT.RoundedPanel;
import TEST.DashedCircleLabel;

public class Item_VIEW extends RoundedPanel {
	private static final long serialVersionUID = 1L;
	private JPanel panel_menu; // display list of item
	private RoundedPanel panel_detailitem; // show detail information of chosen item
	private JScrollPane scrollPanepanel_menu; // scroll panel menu
	private JTextField tf_nameitem; // display name of item
	private JTextField tf_price; // display price of item
	private JComboBox<String> comboBox_typeofitem; // combo box choose category of item

	private String itemid; // save id of current item is choose
	private boolean detail_mode = false; // check any change of data in item
	private JLabel label_imageitem; // label display image of item
	private int quantity; // quantity of current item
	private File selectedFile; // file of image when select image for item
	
	private int padding_top = 30;
	private int height = 650;

	public Item_VIEW(int radius, Color backgroundColor) {
		super(radius, backgroundColor);
		setBounds(0, 0, 1190, 730);
		setLayout(null);

		panel_menu = new JPanel();
		panel_menu.setBackground(new Color(249, 230, 186));
		panel_menu.setLayout(null);
		this.add(panel_menu);

		scrollPanepanel_menu = new JScrollPane(panel_menu);
		scrollPanepanel_menu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPanepanel_menu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scrollPanepanel_menu);
		
		main_menu();
		detail_item();
		panel_detailitem.setVisible(false);
	}

	// create panel for product
	private JPanel createItemPanel(Item item, int x, int y) {
		JPanel panel = new JPanel();
		panel.setBounds(x, y, 155, 170);
		panel.setBackground(null);
		panel.addMouseListener(createMouseListener(panel));
		panel.setLayout(null);

		int letter_size = 18;

		JLabel label_image = new JLabel();
		label_image.setBounds(0, 0, panel.getWidth(), 85);
		label_image.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		String s = item.getImagePath();
		if (s != null) {
			try {
				ImageIcon icon = new ImageIcon(getClass().getResource(s));
				if (icon != null) {
					Image image = icon.getImage();
					Image scaledImage = image.getScaledInstance(label_image.getWidth(), label_image.getHeight(),
							Image.SCALE_SMOOTH);
					ImageIcon scaledIcon = new ImageIcon(scaledImage);
					label_image.setIcon(scaledIcon);
				}
			} catch (NullPointerException e) {
				System.err.println("NullPointerException: " + e.getMessage());
				e.printStackTrace();
			} catch (Exception ex) {
				System.err.println("Exception: " + ex.getMessage());
				ex.printStackTrace();
			}
		} else {
			System.err.println("Image path is null.");
		}
		panel.add(label_image);

		JLabel label_name = new JLabel(item.getItemName());
		label_name.setBounds(5, label_image.getHeight(), panel.getWidth() - 5, 30);
		label_name.setFont(new Font("Tahoma", Font.PLAIN, letter_size));
		panel.add(label_name);

		JLabel label_price = new JLabel(item.getPrice() + " đ");
		label_price.setForeground(new Color(255, 0, 0));
		label_price.setBounds(5, panel.getHeight() - 35, panel.getWidth() - 5, 35);
		label_price.setForeground(new Color(255, 0, 0));
		label_price.setFont(new Font("Tahoma", Font.PLAIN, letter_size));
		panel.add(label_price);

		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.putClientProperty("item", item); // get item from panel clicked

		return panel;
	}

	private void displayItems(List<Item> list) {
		String type = "Burger";
		panel_menu.removeAll();
		int x = 10, y = 10;
		int maxHeight = 70; // Để lưu chiều cao lớn nhất của panel
		for (Item item : list) {
			JPanel panel = createItemPanel(item, x, y);
			panel_menu.add(panel);

			int panelHeight = panel.getPreferredSize().height;
			if (panelHeight > maxHeight) {
				maxHeight = panelHeight;
			}
			// Kiểm tra xem panel tiếp theo có thể được thêm vào hàng hiện tại không
			if (x + panel.getWidth() * 2 + 10 > panel_menu.getPreferredSize().width
					|| item.getType().compareTo(type) != 0) {
				y += panel.getHeight() + 10; // Y tăng lên theo chiều cao lớn nhất của panel trong hàng
				x = 10;
				maxHeight = 70; // Reset chiều cao lớn nhất
				type = item.getType();
			} else {
				x += panel.getWidth() + 10;
			}
		}
		JPanel panel_add = new JPanel();
		panel_add.setBounds(x, y, 155, 170);
		panel_add.setLayout(null);

		// label click to add a item
		DashedCircleLabel label_add = new DashedCircleLabel("+");
		label_add.setSize(50, 50);
		int add_x = (panel_add.getWidth() - label_add.getWidth()) / 2;
		int add_y = (panel_add.getHeight() - 50) / 2;
		label_add.setLocation(add_x, add_y);
		panel_add.add(label_add);
		panel_add.addMouseListener(createMouseListener(panel_add));
//			System.out.println(add_x + " " + add_y);
//	      circleLabel.setSize(50, 50);
//	      System.out.println(panel_1_1.getWidth() + " " + panel_1_1.getHeight() + " " + circleLabel.getWidth() + " " + circleLabel.getHeight());

		panel_menu.add(panel_add);

		panel_menu.revalidate();
		panel_menu.repaint();
	}

	private MouseAdapter createMouseListener(JPanel panel) {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Item clickedItem = (Item) panel.getClientProperty("item");
				if (clickedItem != null) {
					if(detail_mode) {
						updateItem(clickedItem);
					}
					else {
						detail_menu();
						panel_detailitem.setVisible(true);
						updateItem(clickedItem);
					}
				}
				else {
					if(detail_mode) {	
						clearItem();
					}
					else {
						detail_menu();
						panel_detailitem.setVisible(true);
						comboBox_typeofitem.setSelectedIndex(-1);
					}
//					System.out.println("Null");
				}
				detail_mode = true;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
				panel.setCursor(cursor);
				panel.setBackground(Color.green);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				Cursor cursor = Cursor.getDefaultCursor();
				panel.setCursor(cursor);
				panel.setBackground(null);

			}

		};
	}
	
	private void main_menu() {
		int padding_side = 10;
//		panel_1.setBounds(10, 30, 1170, 670);
		panel_menu.setPreferredSize(new Dimension(this.getWidth() - 2 * padding_side, 1500));
		scrollPanepanel_menu.setBounds(padding_top, 30, panel_menu.getPreferredSize().width, height);
		
		List<Item> list = Item_BLL.getInstance().getAllItem();
		quantity = list.size();
		displayItems(list);
		
		this.revalidate(); // Revalidate the content pane to apply changes
		this.repaint(); // Repaint the content pane to apply changes
	}

	private void detail_menu() {
//		this.removeAll();
		; // Remove the old panel

//        panel_content = new RoundedPanel(50, new Color(249, 230, 186));
//        panel_content.setBounds(310, 30, 720, 730);
//        contentPane.add(panel_content);
//        panel_content.setLayout(null);

//        panel_menu = new JPanel();
//		panel_menu.setBackground(new Color(249, 230, 186));
//        int distant = 10;
		panel_menu.setPreferredSize(new Dimension(700, 2000));
//		panel_menu.setLayout(null);

//		JScrollPane scrollPanepanel_menu = new JScrollPane(panel_menu);
		scrollPanepanel_menu.setBounds(padding_top, 30, panel_menu.getPreferredSize().width, height);
//		scrollPanepanel_menu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//		scrollPanepanel_menu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		this.add(scrollPanepanel_menu);

		List<Item> list = Item_BLL.getInstance().getAllItem();
		displayItems(list);

		this.revalidate(); // Revalidate the content pane to apply changes
		this.repaint(); // Repaint the content pane to apply changes
	}
	
	private void detail_item() {
		panel_detailitem = new RoundedPanel(50, new Color(249, 230, 186));
		panel_detailitem.setBounds(730, 30, 450, 670);
		panel_detailitem.setLayout(null);
		
		JLabel label_ok = new JLabel("");
		label_ok.setBounds(350, 10, 30, 30);
		ImageIcon icon = new ImageIcon(getClass().getResource("/IMAGE/tichxanh.png"));
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(label_ok.getWidth(), label_ok.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		label_ok.setIcon(scaledIcon);
		label_ok.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(itemid != null) {
					Item item = Item_BLL.getInstance().getItemByID(itemid);
					item.setItemName(tf_nameitem.getText());
					item.setPrice(Integer.parseInt(tf_price.getText()));
					item.setType(comboBox_typeofitem.getSelectedItem().toString());
					Item_BLL.getInstance().updateItem(item);
					if(selectedFile != null) {
						try {
							updateImage(selectedFile, item.getImagePath());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				else {
					String newitemid =  String.format("PROD%03d", ++quantity);
					String name = tf_nameitem.getText();
					int price = Integer.parseInt(tf_price.getText());
					String type = (String) comboBox_typeofitem.getSelectedItem();
					String imagePath = "/IMAGE/"+ name.replaceAll(" ", "") + ".jpg";
					
					Item item = new Item(newitemid, imagePath, name, price, type);
					Item_BLL.getInstance().insertItem(item);
					
					Bonusitem bonusitem = new Bonusitem(newitemid, imagePath, name, price, type, 10, 0);
					Bonusitem_BLL.getIstance().addBonusitem(bonusitem);
					try {
						saveImage(selectedFile, imagePath);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				detail_mode = false;
				panel_detailitem.setVisible(detail_mode);
				clearItem();
				main_menu();
			}
			
			
		});
		panel_detailitem.add(label_ok);
		
		JLabel label_cancel = new JLabel("X");
		label_cancel.setForeground(new Color(255, 0, 0));
		label_cancel.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_cancel.setBounds(400, 10, 30, 30);
		label_cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				detail_mode = false;
				panel_detailitem.setVisible(detail_mode);
				clearItem();
				main_menu();
//				System.out.println("cancel 256");
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
//				panel.setBackground(null);

			}
		});
		panel_detailitem.add(label_cancel);

		JLabel labelheading_nameitem = new JLabel();
		labelheading_nameitem.setText("Tên sản phẩm");
		labelheading_nameitem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelheading_nameitem.setBounds(10, 70, 150, 25);
		panel_detailitem.add(labelheading_nameitem);

		tf_nameitem = new JTextField();
		tf_nameitem.setColumns(10);
		tf_nameitem.setBounds(170, 70, 250, 30);
		tf_nameitem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_detailitem.add(tf_nameitem);

		JLabel labelheading_price = new JLabel();
		labelheading_price.setText("Giá sản phẩm");
		labelheading_price.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelheading_price.setBounds(10, 120, 150, 25);
		panel_detailitem.add(labelheading_price);

		tf_price = new JTextField();
		tf_price.setColumns(10);
		tf_price.setBounds(170, 120, 250, 30);
		tf_price.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_detailitem.add(tf_price);

		JLabel labelheading_category = new JLabel();
		labelheading_category.setText("Danh mục");
		labelheading_category.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelheading_category.setBounds(10, 170, 150, 25);
		panel_detailitem.add(labelheading_category);

		comboBox_typeofitem = new JComboBox<>();
		comboBox_typeofitem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_typeofitem.setBounds(170, 170, 250, 30);
		panel_detailitem.add(comboBox_typeofitem);
		for (Category category : Category_BLL.getInstance().getAllCategory()) {
			comboBox_typeofitem.addItem(category.toString());
		}
		

		JLabel labelheading_imageitem = new JLabel();
		labelheading_imageitem.setText("Ảnh minh họa");
		labelheading_imageitem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelheading_imageitem.setBounds(10, 220, 150, 25);
		panel_detailitem.add(labelheading_imageitem);

		JButton button_image = new JButton("Chọn ảnh minh họa");
		button_image.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button_image.setBounds(170, 220, 250, 30);
		panel_detailitem.add(button_image);
		button_image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tạo JFileChooser
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int result = fileChooser.showOpenDialog(Item_VIEW.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    if (selectedFile != null) {
                        ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
                        Image image = imageIcon.getImage(); // Lấy đối tượng Image từ ImageIcon
                        Image scaledImage = image.getScaledInstance(label_imageitem.getWidth(), label_imageitem.getHeight(),
                                java.awt.Image.SCALE_SMOOTH); // Thay đổi kích thước ảnh
                        ImageIcon scaledIcon = new ImageIcon(scaledImage);
                        label_imageitem.setIcon(scaledIcon);
                    } else {
                        System.out.println("No file selected.");
                    }
//                    if (!tf_employeeid.getText().trim().isEmpty()) {
//                        employeeid = tf_employeeid.getText().trim();
//                    }
//                    update_image = true;
                } else {
                    System.out.println("File selection was cancelled.");
                }
            }
        });

		label_imageitem = new JLabel("");
		label_imageitem.setBounds(250, 275, 100, 100);
		panel_detailitem.add(label_imageitem);

		this.add(panel_detailitem);
	}

	private void updateItem(Item item) {
		itemid = item.getItemID();
		tf_nameitem.setText(item.getItemName());
		tf_price.setText(item.getPrice() + "");
		comboBox_typeofitem.setSelectedItem(item.getType());

		ImageIcon icon = new ImageIcon(getClass().getResource(item.getImagePath()));
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(label_imageitem.getWidth(), label_imageitem.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		label_imageitem.setIcon(scaledIcon);
	}
	
	private void clearItem() {
		itemid = null;
		tf_nameitem.setText("");
		tf_price.setText("");
		label_imageitem.setIcon(null);
		comboBox_typeofitem.setSelectedIndex(-1);
	}
	
	// save image of employee
	private void saveImage(File selectedFile, String imagePath) throws IOException {
		String oldFileName = imagePath.substring(imagePath.lastIndexOf("/") + 1);
		Path destinationPath = Paths.get("src/IMAGE", oldFileName);

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
}
