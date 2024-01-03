package controller.goodController;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import dao.impl.goodDaoImpl;
import model.good;
import util.Clock;
import util.ImagePanel;
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
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

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
		setTitle("Toy Shop");
		setIconImage(Toolkit.getDefaultToolkit().getImage(goodAdd.class.getResource("/image/title.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(452, 419);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImagePanel panel = new ImagePanel("/image/goodBackground.jpg");
		panel.setBounds(0, 0, 436, 380);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("商品名稱");
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(37, 59, 131, 45);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("商品價格");
		lblNewLabel_1.setFont(new Font("新細明體", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(37, 131, 131, 45);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("起始庫存");
		lblNewLabel_2.setFont(new Font("新細明體", Font.BOLD, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(37, 205, 131, 45);
		panel.add(lblNewLabel_2);

		JLabel lblurl = new JLabel("圖檔名稱(加副檔名)");
		lblurl.setFont(new Font("新細明體", Font.BOLD, 16));
		lblurl.setHorizontalAlignment(SwingConstants.CENTER);
		lblurl.setForeground(Color.WHITE);
		lblurl.setBounds(10, 274, 158, 45);
		panel.add(lblurl);

		name = new JTextField();
		name.setFont(new Font("新細明體", Font.BOLD, 16));
		name.setBounds(176, 65, 121, 33);
		panel.add(name);
		name.setColumns(10);

		price = new JTextField();
		price.setFont(new Font("新細明體", Font.BOLD, 16));
		price.setColumns(10);
		price.setBounds(178, 137, 121, 33);
		panel.add(price);

		inventory = new JTextField();
		inventory.setFont(new Font("新細明體", Font.BOLD, 16));
		inventory.setColumns(10);
		inventory.setBounds(176, 211, 121, 33);
		panel.add(inventory);

		image = new JTextField();
		image.setFont(new Font("新細明體", Font.BOLD, 16));
		image.setText("/image/");
		image.setColumns(10);
		image.setBounds(176, 280, 121, 33);
		panel.add(image);

		JButton btnNewButton_1 = new JButton("上架");
		btnNewButton_1.setFont(new Font("新細明體", Font.BOLD, 16));
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
		btnNewButton_1_1.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button.goodManager();
				dispose();
			}
		});
		btnNewButton_1_1.setBounds(91, 328, 110, 29);
		panel.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_3 = new JLabel("商品上架");
		lblNewLabel_3.setFont(new Font("新細明體", Font.BOLD, 20));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(153, 10, 141, 45);
		panel.add(lblNewLabel_3);
		
		JLabel clock = new Clock();
		clock.setHorizontalAlignment(SwingConstants.CENTER);
		clock.setForeground(Color.WHITE);
		clock.setFont(new Font("新細明體", Font.BOLD, 13));
		clock.setBounds(0, 0, 149, 29);
		panel.add(clock);

	}

}
