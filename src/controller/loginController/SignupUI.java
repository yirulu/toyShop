package controller.loginController;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import dao.impl.memberDaoImpl;
import model.member;
import util.Clock;
import util.button;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setSize(477, 494);
		setLocationRelativeTo(null);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("* 帳號");
		lblNewLabel.setBounds(41, 46, 133, 38);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("* 密碼");
		lblNewLabel_1.setBounds(41, 105, 133, 38);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("* 姓名");
		lblNewLabel_2.setBounds(41, 169, 133, 38);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("  電話");
		lblNewLabel_3.setBounds(41, 234, 133, 38);
		panel.add(lblNewLabel_3);
		
		JLabel lblEmail = new JLabel("  Email");
		lblEmail.setBounds(41, 296, 133, 38);
		panel.add(lblEmail);
		
		
		account = new JFormattedTextField();
       	account.setBounds(154, 46, 184, 38);
		panel.add(account);
		account.setColumns(10);
		
		
			
		password = new JFormattedTextField();
		password.setColumns(10);
		password.setBounds(154, 105, 184, 38);
		panel.add(password);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(154, 169, 184, 38);
		panel.add(name);
		
		
		
		phone = new JFormattedTextField();
	
			// TODO Auto-generated catch block
		
		phone.setColumns(10);
		phone.setBounds(154, 234, 184, 38);
		panel.add(phone);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(154, 296, 184, 38);
		panel.add(email);
		
		btnNewButton = new JButton("註冊");
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
		btnNewButton.setBounds(254, 395, 151, 46);
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("回登入頁");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button.login();
				dispose();
			}
		});
		btnNewButton_1.setBounds(64, 395, 151, 46);
		panel.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("檢查帳號");
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
			}
		});
		btnNewButton_2.setBounds(361, 46, 92, 38);
		panel.add(btnNewButton_2);
		
		accountDetcted = new JLabel("");
		accountDetcted.setHorizontalAlignment(SwingConstants.CENTER);
		accountDetcted.setBounds(171, 22, 151, 23);
		panel.add(accountDetcted);
		
		lblNewLabel_4 = new JLabel("* 為必填");
		lblNewLabel_4.setBounds(10, 10, 80, 23);
		panel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new Clock();
		lblNewLabel_5.setBounds(325, 10, 152, 23);
		panel.add(lblNewLabel_5);
	}

}
