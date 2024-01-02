package controller.loginController;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.impl.memberDaoImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import util.Clock;
import util.ImagePanel;
import util.button;
import java.awt.Color;
public class passwordUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField account;
	private JTextField password;
	private JTextField newPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					passwordUI frame = new passwordUI();
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
	public passwordUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize( 445, 376);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImagePanel panel = new ImagePanel("/image/loginBackground.jpg");
		panel.setBounds(0, 0, 429, 337);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("忘記密碼");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(127, 10, 182, 33);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("帳號");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("新細明體", Font.BOLD, 16));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(34, 67, 117, 33);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("新密碼");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("新細明體", Font.BOLD, 16));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(34, 132, 117, 33);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("確認密碼");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("新細明體", Font.BOLD, 16));
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setBounds(34, 196, 117, 33);
		panel.add(lblNewLabel_1_2);
		
		account = new JTextField();
		account.setBounds(161, 73, 143, 21);
		panel.add(account);
		account.setColumns(10);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(161, 138, 143, 21);
		panel.add(password);
		
		newPassword = new JTextField();
		newPassword.setColumns(10);
		newPassword.setBounds(161, 202, 143, 21);
		panel.add(newPassword);
		
		JButton btnNewButton = new JButton("回登入頁");
		btnNewButton.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button.login();
				dispose();
			}
		});
		btnNewButton.setBounds(81, 261, 111, 33);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("修改密碼");
		btnNewButton_1.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Account=account.getText();
				String Password=password.getText();
				String NewPassword=newPassword.getText();
				if(!(Account.isEmpty())) 
				{
				if(new memberDaoImpl().accIsExist(Account))
				{
					if(!(Password.isEmpty()))
					{
						if(Password.equals(NewPassword) )
							{
							new memberDaoImpl().modifyPw(Password, Account);
							JOptionPane.showMessageDialog(null,"修改成功,回登入頁");
							button.login();
							dispose();
							}else
							{
								JOptionPane.showMessageDialog(null,"確認密碼與新密碼不同");
							}
					}else
					{
						JOptionPane.showMessageDialog(null,"密碼不能為空");
					}
				}else
					JOptionPane.showMessageDialog(null,"帳號不存在");
			}else
				JOptionPane.showMessageDialog(null,"帳號不能為空");
			}});
		btnNewButton_1.setBounds(244, 261, 111, 33);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new Clock();
		lblNewLabel_2.setBounds(302, 17, 117, 21);
		panel.add(lblNewLabel_2);
	}
}
