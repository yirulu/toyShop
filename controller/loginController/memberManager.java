package controller.loginController;

import java.awt.EventQueue;
import dao.impl.memberDaoImpl;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import model.member;
import util.ImagePanel;
import util.button;
import util.memberOpreate.deleteMember;
import util.memberOpreate.memberFilter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import util.Clock;
import java.awt.Color;
import java.awt.Toolkit;

public class memberManager extends JFrame {

	private JPanel contentPane;
	private JTable mTable;
	private DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					memberManager frame = new memberManager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public memberManager(){
		setTitle("Toy Shop");
		setIconImage(Toolkit.getDefaultToolkit().getImage(memberManager.class.getResource("/image/title.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 560);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImagePanel panel = new ImagePanel("/image/managerBackground.jpg");
		panel.setBounds(0, 0, 784, 521);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("會員列表");
		lblNewLabel.setBounds(258, -4, 272, 49);
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);

		mTable = new JTable();
		mTable.setEnabled(false);
		tableModel = new DefaultTableModel();
		Object[] column = { "帳號", "密碼", "姓名", "電話", "信箱", "累計消費", "會員等級" };
		Object[] row = new Object[7];
		tableModel.setColumnIdentifiers(column);
		mTable.setModel(tableModel);
		tableModel = (DefaultTableModel) mTable.getModel();

		for (member m : new memberDaoImpl().QueryAll()) {
			tableModel.addRow(new Object[] { m.getAccount(), m.getPassword(), m.getName(), m.getPhone(), m.getEmail(),
					m.getAccConsumption(), m.getVipLevel() });
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 106, 764, 344);
		panel.add(scrollPane);

		scrollPane.setViewportView(mTable);

		JLabel lblNewLabel_1 = new JLabel("帳號");
		lblNewLabel_1.setBackground(Color.PINK);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("新細明體", Font.BOLD, 18));
		lblNewLabel_1.setBounds(89, 43, 42, 29);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("姓名");
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("新細明體", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(245, 43, 42, 29);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("電話");
		lblNewLabel_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_2.setFont(new Font("新細明體", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(418, 43, 42, 29);
		panel.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("信箱");
		lblNewLabel_1_3.setForeground(Color.BLACK);
		lblNewLabel_1_3.setFont(new Font("新細明體", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(573, 43, 42, 29);
		panel.add(lblNewLabel_1_3);

		
		JFormattedTextField account = new JFormattedTextField();
		account.setBounds(63, 71, 99, 25);
		panel.add(account);

		JFormattedTextField name = new JFormattedTextField();
		name.setBounds(216, 71, 99, 25);
		panel.add(name);

		
		JFormattedTextField phone  = new JFormattedTextField();

		phone.setBounds(390, 71, 99, 25);
		panel.add(phone);

		JFormattedTextField email = new JFormattedTextField();
		email.setBounds(541, 71, 99, 25);
		panel.add(email);

		JButton btnNewButton = new JButton("篩選");
		btnNewButton.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Account = account.getText();
				String Name = name.getText();
				String Phone = phone.getText();
				String Email=email.getText();
				Boolean A=new memberDaoImpl().accIsExist(Account);
				Boolean N=new memberDaoImpl().nameIsExist(Name);
				Boolean P=new memberDaoImpl().phoneIsExist(Phone);
				Boolean E=new memberDaoImpl().emailIsExist(Email);
				
				memberFilter.filter(tableModel, Account, Name, Phone, Email, A, N, P, E);
				

				if(A && (!(Account.isEmpty())))
				{
					tableModel.setRowCount(0);
					member m=new memberDaoImpl().QueryAccount(Account);
					tableModel.addRow(new Object[]{ m.getAccount(), m.getPassword(), m.getName(), m.getPhone(), m.getEmail(),
						m.getAccConsumption(), m.getVipLevel() });
				}else if(N && (!(Name.isEmpty())))
				{
					tableModel.setRowCount(0);
					for (member m : new memberDaoImpl().QueryName(Name)) {
						tableModel.addRow(new Object[] { m.getAccount(), m.getPassword(), m.getName(), m.getPhone(), m.getEmail(),
								m.getAccConsumption(), m.getVipLevel() });
					}
				}else if(P && (!(Phone.isEmpty())))
				{
					tableModel.setRowCount(0);
					for (member m : new memberDaoImpl().QueryPhone(Phone)) {
						tableModel.addRow(new Object[] { m.getAccount(), m.getPassword(), m.getName(), m.getPhone(), m.getEmail(),
								m.getAccConsumption(), m.getVipLevel() });
					}
				}else if(E && (!(Email.isEmpty())))
				{
					tableModel.setRowCount(0);
					for (member m : new memberDaoImpl().QueryEmail(Email)) 
					{
						tableModel.addRow(new Object[] { m.getAccount(), m.getPassword(), m.getName(), m.getPhone(), m.getEmail(),
								m.getAccConsumption(), m.getVipLevel()});
					}
				}else if((Account.isEmpty())&&(Name.isEmpty())&&(Phone.isEmpty())&&(Email.isEmpty()))
				{
					tableModel.setRowCount(0);
					for (member m : new memberDaoImpl().QueryAll()) {
						tableModel.addRow(new Object[] { m.getAccount(), m.getPassword(), m.getName(), m.getPhone(), m.getEmail(),
								m.getAccConsumption(), m.getVipLevel() });
					}
				}else
				{
					tableModel.setRowCount(0);
					JOptionPane.showMessageDialog(null,"沒有找到符合的資料");
				}


			}
		});
		btnNewButton.setBounds(673, 70, 87, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("回總管理頁");
		btnNewButton_1.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button.managerTerminal();
				dispose();
			}
		});
		btnNewButton_1.setBounds(652, 10, 122, 23);
		panel.add(btnNewButton_1);
		
		Clock clock = new Clock();
		clock.setForeground(Color.WHITE);
		clock.setFont(new Font("新細明體", Font.BOLD, 14));
		clock.setBackground(Color.WHITE);
		clock.setBounds(10, 9, 151, 24);
		panel.add(clock);
		

		JButton delete = new JButton("刪除會員");
		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Account=JOptionPane.showInputDialog("請輸入帳號");
				deleteMember.deleteM(Account);
				
			}
		});
		delete.setFont(new Font("新細明體", Font.BOLD, 16));
		delete.setBounds(652, 472, 108, 23);
		panel.add(delete);

		JButton btnNewButton_2 = new JButton("刪除會員");
		btnNewButton_2.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton_2.setBounds(652, 472, 108, 23);
		panel.add(btnNewButton_2);

	}
}
