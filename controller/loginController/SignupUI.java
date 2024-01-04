package controller.loginController;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import dao.impl.memberDaoImpl;
import model.member;
import util.Clock;
import util.ImagePanel;
import util.button;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;

public class SignupUI extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField account;
	private JFormattedTextField password;
	private JTextField name;
	private JFormattedTextField phone;
	private JTextField email;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JLabel accountDetcted;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private Timer timer;
	private JLabel lblNewLabel_6;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignupUI frame = new SignupUI();
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
	public SignupUI() {
		setTitle("Toy Shop");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SignupUI.class.getResource("/image/title.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImagePanel panel = new ImagePanel("/image/loginBackground.jpg");
		panel.setLocation(0, 0);
		panel.setSize(477, 494);
		setLocationRelativeTo(null);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("* 帳號");
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(24, 84, 133, 38);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("* 密碼");
		lblNewLabel_1.setFont(new Font("新細明體", Font.BOLD, 16));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(24, 143, 133, 38);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("* 姓名");
		lblNewLabel_2.setFont(new Font("新細明體", Font.BOLD, 16));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(24, 207, 133, 38);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("  電話");
		lblNewLabel_3.setFont(new Font("新細明體", Font.BOLD, 16));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(24, 272, 133, 38);
		panel.add(lblNewLabel_3);
		
		JLabel lblEmail = new JLabel("  Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("新細明體", Font.BOLD, 16));
		lblEmail.setBounds(24, 334, 133, 38);
		panel.add(lblEmail);
		
		
		account = new JFormattedTextField();
       	account.setBounds(156, 85, 184, 38);
		panel.add(account);
		account.setColumns(10);
		
		
			
		password = new JFormattedTextField();
		password.setColumns(10);
		password.setBounds(156, 144, 184, 38);
		panel.add(password);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(156, 208, 184, 38);
		panel.add(name);
		
		
		
		phone = new JFormattedTextField();
	
			// TODO Auto-generated catch block
		
		phone.setColumns(10);
		phone.setBounds(156, 273, 184, 38);
		panel.add(phone);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(156, 335, 184, 38);
		panel.add(email);
		
		btnNewButton = new JButton("註冊");
		btnNewButton.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Account=account.getText();
				String Password=password.getText();
				String Name=name.getText();
				String Phone=phone.getText();
				String Email=email.getText();
				 if(Phone.isEmpty()) 
					{
						Phone="未填寫";						
					}
				 if(Email.isEmpty()) 
					{
						Email="未填寫";						
					}
				if(Account.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "帳號不能為空");
				}else if(Password.isEmpty()) 
				{
					JOptionPane.showMessageDialog(null, "密碼不能為空");
				}else if(Name.isEmpty()) 
				{
					JOptionPane.showMessageDialog(null, "姓名不能為空");
				}else if(new memberDaoImpl().accIsExist(Account)) 
				{
					JOptionPane.showMessageDialog(null, "帳號已使用請更換");
				}			 
				else
				{
					member m=new member();
					m.setAccount(Account);
					m.setPassword(Password);
					m.setName(Name);
					m.setPhone(Phone);
					m.setEmail(Email);
					new memberDaoImpl().addmember(m);
					JOptionPane.showMessageDialog(null, "註冊成功,回登入頁面");
					LoginUI login=new LoginUI();
					login.setVisible(true);
					dispose();
				}
				
			}
		});
		btnNewButton.setBounds(254, 421, 151, 46);
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("回登入頁");
		btnNewButton_1.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button.login();
				dispose();
			}
		});
		btnNewButton_1.setBounds(62, 421, 151, 46);
		panel.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("檢查");
		btnNewButton_2.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Account=account.getText();
				if(Account.isEmpty())
				{
					accountDetcted.setText("帳號不能為空");
				}
				else if(new memberDaoImpl().accIsExist(Account))
				{
					accountDetcted.setText("帳號已重複");
				}else
				{
					accountDetcted.setText("帳號可使用");
				}
				 timer = new Timer(2000, new ActionListener() {
			            @Override
			            public void actionPerformed(ActionEvent e) {
			                accountDetcted.setText("");  // 將文字設為空
			                timer.stop();  // 停止 Timer
			            }
			        });
				 timer.start();
			}
		});
		btnNewButton_2.setBounds(363, 85, 71, 38);
		panel.add(btnNewButton_2);
		
		accountDetcted = new JLabel("");
		accountDetcted.setFont(new Font("新細明體", Font.BOLD, 16));
		accountDetcted.setForeground(Color.WHITE);
		accountDetcted.setHorizontalAlignment(SwingConstants.CENTER);
		accountDetcted.setBounds(174, 53, 151, 32);
		panel.add(accountDetcted);
		
		lblNewLabel_4 = new JLabel("* 為必填");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setBounds(10, 10, 80, 23);
		panel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new Clock();
		lblNewLabel_5.setFont(new Font("新細明體", Font.BOLD, 13));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setBounds(325, 10, 152, 23);
		panel.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("註冊帳號");
		lblNewLabel_6.setFont(new Font("新細明體", Font.BOLD, 20));
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(174, 10, 151, 56);
		panel.add(lblNewLabel_6);
	}

}
