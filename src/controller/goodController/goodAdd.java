package controller.goodController;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import dao.impl.goodDaoImpl;
import model.good;
import util.button;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class goodAdd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField price;
	private JTextField inventory;
	private JTextField image;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					goodAdd frame = new goodAdd();
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
	public goodAdd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(452, 419);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 436, 380);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("商品名稱");
		lblNewLabel.setBounds(37, 53, 131, 45);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("商品價格");
		lblNewLabel_1.setBounds(37, 125, 131, 45);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("起始庫存");
		lblNewLabel_2.setBounds(37, 205, 131, 45);
		panel.add(lblNewLabel_2);

		JLabel lblurl = new JLabel("商品圖片(URL)");
		lblurl.setBounds(37, 280, 131, 45);
		panel.add(lblurl);

		name = new JTextField();
		name.setBounds(176, 65, 121, 21);
		panel.add(name);
		name.setColumns(10);

		price = new JTextField();
		price.setColumns(10);
		price.setBounds(178, 137, 121, 21);
		panel.add(price);

		inventory = new JTextField();
		inventory.setColumns(10);
		inventory.setBounds(176, 217, 121, 21);
		panel.add(inventory);

		image = new JTextField();
		image.setText("/image/");
		image.setColumns(10);
		image.setBounds(176, 292, 121, 21);
		panel.add(image);

		JButton btnNewButton_1 = new JButton("上架");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				String Name = name.getText();
				Integer Price = Integer.parseInt(price.getText());
				Integer Inventory = Integer.parseInt(inventory.getText());
				String Image = image.getText();
				
					if (new goodDaoImpl().NameIsExist(Name)) {
						JOptionPane.showMessageDialog(null, "此商品已上架");
					} else {
						good g = new good(Name, Price, Inventory, Image);
						new goodDaoImpl().add(g);
						JOptionPane.showMessageDialog(null, "商品上架成功");
						button.goodManager();
						dispose();
					}
				
			}catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(null, "欄位不能為空");
			}}
		});
		btnNewButton_1.setBounds(253, 328, 110, 29);
		panel.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("回管理頁");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button.goodManager();
				dispose();
			}
		});
		btnNewButton_1_1.setBounds(91, 328, 110, 29);
		panel.add(btnNewButton_1_1);

	}

}
