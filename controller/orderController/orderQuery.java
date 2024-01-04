package controller.orderController;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import model.morder;
import util.ImagePanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import dao.impl.morderDaoImpl;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
public class orderQuery extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private static String Account;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					orderQuery frame = new orderQuery(Account);
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
	public orderQuery(String Account_1) {
		setTitle("Toy Shop");
		setIconImage(Toolkit.getDefaultToolkit().getImage(orderQuery.class.getResource("/image/title.jpg")));
		if(Account_1!=null)
		{
			this.Account=Account_1;
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImagePanel panel = new ImagePanel("/image/background.png");
		panel.setBounds(0, 0, 684, 461);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("歷史訂單");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(236, 24, 200, 46);
		panel.add(lblNewLabel);
		tableModel = new DefaultTableModel();
		Object[] column = { "訂單編號", "訂單內容", "訂單金額","訂單日期"};
		Object[] row = new Object[4];
		tableModel.setColumnIdentifiers(column);
		for ( morder o: new morderDaoImpl().QueryAccount(Account)) {
			tableModel.addRow(new Object[] {o.getOrderId(),o.getDetail(),o.getTotal(),o.getOrderDate()});
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 84, 664, 301);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(tableModel);
		
		JButton btnNewButton = new JButton("回購買頁");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				orderUI ou=new orderUI(Account);
				ou.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton.setBounds(260, 405, 150, 35);
		panel.add(btnNewButton);
		tableModel = (DefaultTableModel) table.getModel();
	}
}
