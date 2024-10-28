package VIEW;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import BLL.Employee_BLL;
import BLL.Account_BLL;
import DTO.Employee;
import DTO.Account;
import EDIT.RoundedPanel;

public class ManaEmployee_VIEW extends JPanel {
	private static final long serialVersionUID = 1L;
	private RoundedPanel panel_listemployee;
	private JTable table_employee;
	private DefaultTableModel modeltable_employee;
	private JComboBox<String> comboBox_search;
	private JTextField tf_search;

	private RoundedPanel panel_detailemployee;
	private JTextField tf_employeeid;
	private JTextField tf_fullname;
	private JTextField tf_phonenumber;
	private JTextField tf_address;
	private JTextField tf_salary;
	private JDateChooser dateChooser;
	private JComboBox<String> comboBox_gender;
	private JLabel label_avatar;

	private File selectedFile;
	private String employeeid = null;
	private boolean update_image = false;

	public ManaEmployee_VIEW() {
		setLayout(null);
		setBounds(0, 0, 1190, 730);

		JPanel panel_content = new JPanel();
		panel_content.setBounds(0, 0, 1190, 730);
		panel_content.setLayout(null);
		panel_content.setBackground(new Color(249, 230, 186));
		add(panel_content);

		panel_listemployee = new RoundedPanel(50, new Color(249, 230, 186));
		panel_listemployee.setBounds(0, 0, 710, 730);
		panel_content.add(panel_listemployee);
		panel_listemployee.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 75, 680, 620);
		panel_listemployee.add(scrollPane);

		table_employee = new JTable();
		table_employee.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Set selection mode to single selection
		scrollPane.setViewportView(table_employee);
		// Add ListSelectionListener to the table
		table_employee.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
					int selectedRow = table_employee.getSelectedRow();
					if (selectedRow != -1) {
						employeeid = modeltable_employee.getValueAt(selectedRow, 0).toString();
						updateEmployeeDetails(Employee_BLL.getInstance().getEmployeeByID(employeeid));

					}
				}
			}
		});

		String[] columnNames = { "Mã nhân viên", "Họ và tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Địa chỉ",
				"Lương mỗi giờ" };
		modeltable_employee = new DefaultTableModel(columnNames, 0);

		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(20, 20, 100, 20);
		panel_listemployee.add(lblNewLabel_1);

		comboBox_search = new JComboBox<>();
		comboBox_search.setBounds(130, 20, 150, 25);

		comboBox_search.addItem("Tất cả");
		comboBox_search.addItem("Mã nhân viên");
		comboBox_search.addItem("Tên nhân viên");
		comboBox_search.addItem("Số điện thoại");
		comboBox_search.setFont(new Font("Tahoma", Font.PLAIN, 20));

		panel_listemployee.add(comboBox_search);

		tf_search = new JTextField();
		tf_search.setBounds(490, 20, 150, 25);
		panel_listemployee.add(tf_search);
		tf_search.setColumns(10);
		tf_search.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				searchEmployees();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				searchEmployees();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				searchEmployees();
			}
		});

		panel_detailemployee = new RoundedPanel(50, new Color(249, 230, 186));
		panel_detailemployee.setBounds(730, 0, 460, 730);
		panel_content.add(panel_detailemployee);
		panel_detailemployee.setLayout(null);

		JLabel lblNewLabel_11 = new JLabel("Mã nhân viên");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_11.setBounds(10, 20, 150, 25);
		panel_detailemployee.add(lblNewLabel_11);

		JLabel lblNewLabel_1_1 = new JLabel("Họ và tên");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 70, 100, 25);
		panel_detailemployee.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Ngày sinh");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(10, 120, 100, 25);
		panel_detailemployee.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Giới tính");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(10, 170, 100, 25);
		panel_detailemployee.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Số điện thoại");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_4.setBounds(10, 220, 150, 25);
		panel_detailemployee.add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_5 = new JLabel("Địa chỉ");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_5.setBounds(10, 270, 100, 25);
		panel_detailemployee.add(lblNewLabel_1_5);

		JLabel lblNewLabel_1_6 = new JLabel("Lương/1h");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_6.setBounds(10, 320, 121, 25);
		panel_detailemployee.add(lblNewLabel_1_6);

		JLabel lblNewLabel_1_7 = new JLabel("Avatar");
		lblNewLabel_1_7.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_7.setBounds(10, 370, 121, 25);
		panel_detailemployee.add(lblNewLabel_1_7);

		tf_employeeid = new JTextField();
		tf_employeeid.setBounds(170, 20, 250, 30);
		panel_detailemployee.add(tf_employeeid);
		tf_employeeid.setColumns(10);
		tf_employeeid.setEditable(false);

		tf_fullname = new JTextField();
		tf_fullname.setColumns(10);
		tf_fullname.setBounds(170, 70, 250, 30);
		panel_detailemployee.add(tf_fullname);

		tf_phonenumber = new JTextField();
		tf_phonenumber.setColumns(10);
		tf_phonenumber.setBounds(170, 220, 250, 30);
		panel_detailemployee.add(tf_phonenumber);

		tf_address = new JTextField();
		tf_address.setColumns(10);
		tf_address.setBounds(170, 270, 250, 30);
		panel_detailemployee.add(tf_address);

		tf_salary = new JTextField();
		tf_salary.setColumns(10);
		tf_salary.setBounds(170, 320, 250, 30);
		panel_detailemployee.add(tf_salary);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(170, 120, 250, 30);
		panel_detailemployee.add(dateChooser);

		comboBox_gender = new JComboBox<>();
		comboBox_gender.setBounds(170, 170, 250, 30);

		comboBox_gender.addItem("Nam");
		comboBox_gender.addItem("Nữ");
		comboBox_gender.addItem("Khác");
		comboBox_gender.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_detailemployee.add(comboBox_gender);

		label_avatar = new JLabel("");
		label_avatar.setBounds(250, 420, 100, 100);
		panel_detailemployee.add(label_avatar);

		JButton button_add = new JButton("Add");
		button_add.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					validateInputs();
					String employeeid = "EMP"
							+ String.format("%03d", Employee_BLL.getInstance().getAllEmployee().size() + 1);
					String fullname = tf_fullname.getText();
					Date selectedDate = dateChooser.getDate();
					String birthday = "";
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					birthday = dateFormat.format(selectedDate);
					String gender = comboBox_gender.getSelectedItem().toString();
					String phone_number = tf_phonenumber.getText();
					String address = tf_address.getText();
					int wage_coffident = Integer.parseInt(tf_salary.getText());
					String image = "/IMAGE/avatar" + employeeid.substring(3) + ".jpg";
					Employee employee = new Employee(employeeid, fullname, wage_coffident, birthday, phone_number,
							address, image, gender);
					saveImage(selectedFile, employeeid);
					update_image = false;
					Employee_BLL.getInstance().AddEmployee(employee);
					Account account = new Account(fullname, "12345678", employeeid, 0);
					Account_BLL.getInstance().addAccount(account);

					while (modeltable_employee.getRowCount() > 0) {
						modeltable_employee.removeRow(0);
					}
					loadEmployeeData();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button_add.setBounds(45, 530, 120, 50);
		panel_detailemployee.add(button_add);

		JButton button_update = new JButton("Update");
		button_update.addActionListener(new ActionListener() {
//			private File selectedFile;

			public void actionPerformed(ActionEvent e) {
				try {
					validateInputs();
					String employeeid = tf_employeeid.getText();

					Employee employee = Employee_BLL.getInstance().getEmployeeByID(employeeid);
					employee.setFullname(tf_fullname.getText().trim());

					Date selectedDate = dateChooser.getDate();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					employee.setBirthday(dateFormat.format(selectedDate));

					employee.setGender(comboBox_gender.getSelectedItem().toString());

					employee.setPhone_number(tf_phonenumber.getText().trim());

					employee.setAddress(tf_address.getText().trim());

					employee.setWage_coefficient(Integer.parseInt(tf_salary.getText()));

					if (update_image) {
//						System.out.println("update 258");
						updateImage(selectedFile, employee.getImage());
					}
					Employee_BLL.getInstance().UpdateEmployee(employee);
					while (modeltable_employee.getRowCount() > 0) {
						modeltable_employee.removeRow(0);
					}
					loadEmployeeData();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button_update.setBounds(275, 530, 120, 50);
		panel_detailemployee.add(button_update);

		JButton button_delete = new JButton("Delete");
		button_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Employee_BLL.getInstance().DeleteEmployee(tf_employeeid.getText());
				while (modeltable_employee.getRowCount() > 0) {
					modeltable_employee.removeRow(0);
				}
				loadEmployeeData();
			}
		});
		button_delete.setBounds(45, 620, 120, 50);
		panel_detailemployee.add(button_delete);

		JButton button_clear = new JButton("Clear");
		button_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf_employeeid.setText("");
				tf_fullname.setText("");
				tf_phonenumber.setText("");
				tf_address.setText("");
				tf_salary.setText("");
				dateChooser.setDate(null);
				comboBox_gender.setSelectedIndex(0);
				label_avatar.setIcon(null);
				employeeid = null;
			}
		});
		button_clear.setBounds(275, 620, 120, 50);
		panel_detailemployee.add(button_clear);

		JButton btnNewButton = new JButton("Choose avatar");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Tạo JFileChooser
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				int result = fileChooser.showOpenDialog(ManaEmployee_VIEW.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();
					if (selectedFile != null) {
						ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
						Image image = imageIcon.getImage(); // Lấy đối tượng Image từ ImageIcon
						Image scaledImage = image.getScaledInstance(label_avatar.getWidth(), label_avatar.getHeight(),
								java.awt.Image.SCALE_SMOOTH); // Thay đổi kích thước ảnh
						ImageIcon scaledIcon = new ImageIcon(scaledImage);
						label_avatar.setIcon(scaledIcon);
					} else {
						System.out.println("No file selected.");
					}
					if (!tf_employeeid.getText().trim().isEmpty()) {
						employeeid = tf_employeeid.getText().trim();
					}
					update_image = true;
				} else {
					System.out.println("File selection was cancelled.");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(170, 370, 250, 30);
		panel_detailemployee.add(btnNewButton);

		loadEmployeeData();

	}

	private void loadEmployeeData() {
		List<Employee> employeeList = Employee_BLL.getInstance().getAllEmployeeAvailable();

		for (Employee employee : employeeList) {
			Object[] row = { employee.getEmployeeID(), employee.getFullname(), employee.getBirthday(),
					employee.getGender(), employee.getPhone_number(), employee.getAddress(),
					employee.getWage_coefficient() };
			modeltable_employee.addRow(row);
		}

		table_employee.setModel(modeltable_employee);
	}

	private void updateEmployeeDetails(Employee employee) {
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
		tf_employeeid.setText(employee.getEmployeeID());
		tf_fullname.setText(employee.getFullname());
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			// Parse the input string into a Date object
			date = inputFormat.parse(employee.getBirthday());
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Set the formatted date to JDateChooser
		dateChooser.setDate(date);
		comboBox_gender.setSelectedItem(employee.getGender());
		tf_phonenumber.setText(employee.getPhone_number());
		tf_address.setText(employee.getAddress());
		tf_salary.setText(employee.getWage_coefficient() + "");

		ImageIcon icon = new ImageIcon(getClass().getResource(employee.getImage()));

//		System.out.println(employee.getImage());
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(label_avatar.getWidth(), label_avatar.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		label_avatar.setIcon(scaledIcon);
	}

	private void searchEmployees() {
		String searchText = tf_search.getText().toLowerCase();
		List<Employee> list = Employee_BLL.getInstance().getAllEmployee();
		List<Employee> result = new ArrayList<Employee>();

		for (Employee employee : list) {
			if (employee.getEmployeeID().toLowerCase().contains(searchText)
					|| employee.getFullname().toLowerCase().contains(searchText)
					|| employee.getPhone_number().toLowerCase().contains(searchText)) {
				result.add(employee);
			}
		}

		updateTable(result);
	}

	private void updateTable(List<Employee> employees) {
		modeltable_employee.setRowCount(0); // Clear existing rows

		for (Employee employee : employees) {
			Object[] row = { employee.getEmployeeID(), employee.getFullname(), employee.getBirthday(),
					employee.getGender(), employee.getPhone_number(), employee.getAddress(),
					employee.getWage_coefficient() };
			modeltable_employee.addRow(row);
		}
	}

	private void validateInputs() throws Exception {
		if (tf_fullname.getText().trim().isEmpty()) {
			throw new Exception("Họ và tên không được để trống.");
		}

		// name not contain number or special char
//        if (!tf_fullname.getText().trim().matches("[a-zA-Z\\s]+")) {
//            throw new Exception("Họ và tên không được chứa số hoặc ký tự đặc biệt.");
//        }

		if (dateChooser.getDate() == null) {
			throw new Exception("Ngày sinh không được để trống.");
		}
		// check employee must above 18
//        Date birthDate = dateChooser.getDate();
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.YEAR, -18);
//        Date minAgeDate = cal.getTime();
//        if (birthDate.after(minAgeDate)) {
//            throw new Exception("Người nhập phải đủ 18 tuổi.");
//        }

		if (tf_phonenumber.getText().trim().isEmpty()) {
			throw new Exception("Số điện thoại không được để trống.");
		}
		// check phone number enough 10 number
//        if (!tf_phonenumber.getText().trim().matches("\\d{10}")) {
//            throw new Exception("Số điện thoại phải là số và đủ 10 chữ số.");
//        }

		if (tf_address.getText().trim().isEmpty()) {
			throw new Exception("Địa chỉ không được để trống.");
		}

		if (tf_salary.getText().trim().isEmpty()) {
			throw new Exception("Số lương mỗi giờ không được để trống.");
		}

		// check wage coefficient
//        try {
//            Double.parseDouble(tf_salary.getText().trim());
//        } catch (NumberFormatException e) {
//            throw new Exception("Lương mộĩ giờ phải là một số.");
//        }

		// check image
		if (label_avatar.getIcon() == null) {
			throw new Exception("Avatar không được để trống.");
		}
	}

	// save image of employee
	private void saveImage(File selectedFile, String employeeId) throws IOException {
		String newFileName = "avatar" + employeeId.substring(3) + ".jpg"; // Hoặc định dạng ảnh phù hợp
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

}
