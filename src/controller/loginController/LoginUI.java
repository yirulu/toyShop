package controller.loginController;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.orderController.orderUI;
import dao.impl.goodDaoImpl;
import dao.impl.memberDaoImpl;
import model.member;
import util.Clock;
import util.ImagePanel;
import util.button;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Window.Type;
import java.awt.Toolkit;

public class LoginUI extends JFrame {

	private JPanel contentPane;
	private JTextField account;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI frame = new LoginUI();	
					
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
	public LoginUI() {
		setTitle("Toy Shop");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginUI.class.getResource("/image/title.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(509, 449);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImagePanel panel = new ImagePanel("/image/loginBackground.jpg");
		panel.setBounds(0, 0, 493, 410);
		panel.setLayout(null);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("帳號");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("新細明體", Font.BOLD, 20));
		lblNewLabel_1.setBounds(50, 77, 134, 46);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("登入畫面");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(134, 10, 215, 46);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1_1 = new JLabel("密碼");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("新細明體", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(50, 169, 134, 46);
		panel.add(lblNewLabel_1_1);

		account = new JTextField();
		account.setFont(new Font("新細明體", Font.BOLD, 16));
		account.setBounds(209, 77, 157, 46);
		panel.add(account);
		account.setColumns(10);

		JButton btnNewButton = new JButton("登入");
		btnNewButton.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Account=account.getText();
				String Password=password.getText();
				if(!(Account.isEmpty()))
				{
					if(new memberDaoImpl().accIsExist(Account))
					{
					member m=new memberDaoImpl().QueryAccount(Account);
					if(!(Password.isEmpty()))
					{
						if(Password.equals(m.getPassword()))
						{
						JOptionPane.showMessageDialog(null, "登入成功");
						orderUI o=new orderUI(Account);
						o.setVisible(true);
						dispose();
						}else
						{
						JOptionPane.showMessageDialog(null, "密碼錯誤");
						}
					}else 
					{
						JOptionPane.showMessageDialog(null, "請輸入密碼");
					}
				}else
				{
					JOptionPane.showMessageDialog(null, "帳號不存在");
				}
				}else
				{		
					JOptionPane.showMessageDialog(null, "請輸入帳號");
				}	
								
			}
		});
		btnNewButton.setBounds(281, 268, 134, 34);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("註冊");
		btnNewButton_1.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SignupUI signup = new SignupUI();
				signup.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(79, 268, 134, 34);
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("離開");
		btnNewButton_2.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int choice=JOptionPane.showConfirmDialog(null,"真的要離開嗎?", "離開",JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					System.exit(0);
		        } 
		        
			}
		});
		btnNewButton_2.setBounds(281, 345, 134, 34);
		panel.add(btnNewButton_2);

		password = new JPasswordField();
		password.setFont(new Font("新細明體", Font.BOLD, 16));
		password.setBounds(209, 170, 157, 45);
		panel.add(password);

		JLabel lblNewLabel_2 = new JLabel("忘記密碼?");
		lblNewLabel_2.setFont(new Font("新細明體", Font.BOLD, 16));
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordUI pu=new passwordUI();
				pu.setVisible(true);
				dispose();
				
			}
		});
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(281, 304, 134, 31);
		panel.add(lblNewLabel_2);

		JButton btnNewButton_1_1 = new JButton("管理者頁面");
		btnNewButton_1_1.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Account = account.getText();
				String Password = password.getText();
				if (Account.equals("admin") && Password.equals("admin")) {
					JOptionPane.showMessageDialog(null, "帳號確認,進入帳號管理頁面");
					button.managerTerminal();
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "請輸入管理者帳號");
				}
			}
		});
		btnNewButton_1_1.setBounds(79, 345, 134, 34);
		panel.add(btnNewButton_1_1);
		
		JLabel clock = new Clock();
		clock.setFont(new Font("新細明體", Font.BOLD, 13));
		clock.setForeground(Color.WHITE);
		clock.setBackground(Color.WHITE);
		clock.setBounds(366, 386, 127, 24);
		panel.add(clock);
		
	}
}
