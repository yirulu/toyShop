package controller.orderController;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.impl.memberDaoImpl;
import dao.impl.morderDaoImpl;
import model.member;
import model.morder;
import util.ImagePanel;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.awt.Color;
import java.awt.Toolkit;

public class orderPrint extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static String orderId;
	private JTextField pay;
	Boolean printSet=false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					orderPrint frame = new orderPrint(orderId);
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
	public orderPrint(String orderId_1) {
		setTitle("Toy Shop");
		setIconImage(Toolkit.getDefaultToolkit().getImage(orderPrint.class.getResource("/image/title.jpg")));
		if (orderId_1 != null) {
			this.orderId = orderId_1;
		}
		morder o = new morderDaoImpl().QueryOrderId(orderId);
		member m = new memberDaoImpl().QueryAccount(o.getAccount());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(378, 530);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImagePanel panel = new ImagePanel("/image/printBackground.jpg");
		panel.setBounds(0, 0, 362, 491);
		contentPane.add(panel);
		panel.setLayout(null);

		JTextArea output = new JTextArea();
		output.setFont(new Font("Monospaced", Font.PLAIN, 15));
		output.setEditable(false);
		output.setText("下單帳號:" + o.getAccount() +"\n客戶姓名:"+m.getName()+ "\n訂單編號:" + o.getOrderId() + "\n訂單內容:\n" + o.getDetail() + "\n下單時間:"
				+ o.getOrderDate() + "\n訂單金額:" + o.getTotal());

		output.setBounds(10, 48, 343, 354);
		panel.add(output);

		JButton btnNewButton = new JButton("離開");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(296, 468, 66, 23);
		panel.add(btnNewButton);

		JButton print = new JButton("列印訂單");
		print.setFont(new Font("新細明體", Font.BOLD, 16));
		print.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(printSet)
				{
				try {
					output.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		print.setEnabled(false);
		print.setBounds(54, 422, 107, 36);
		panel.add(print);

		JButton btnNewButton_1_1 = new JButton("回購買頁");
		btnNewButton_1_1.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				orderUI ou = new orderUI(o.getAccount());
				ou.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1_1.setBounds(206, 422, 107, 36);
		panel.add(btnNewButton_1_1);

		pay = new JTextField();
		pay.setBounds(104, 10, 152, 23);
		panel.add(pay);
		pay.setColumns(10);

		JLabel lblNewLabel = new JLabel("付款金額");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 84, 28);
		panel.add(lblNewLabel);

		JButton btnNewButton_1_1_1 = new JButton("付款");
		btnNewButton_1_1_1.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (!(pay.getText().isEmpty())) {
					int Pay = Integer.parseInt(pay.getText());
					if (Pay - o.getTotal() < 0) {
						JOptionPane.showMessageDialog(null, "付款金額不足");
					} else {
						output.setText("下單帳號:" + o.getAccount() + "\n訂單編號:" + o.getOrderId() + "\n訂單內容:\n"
								+ o.getDetail() + "\n下單時間:" + o.getOrderDate() + "\n訂單金額:" + o.getTotal()
								+ "\n=====================================" + "\n付款金額" + Pay + "元" + "\n找零:"
								+ (Pay - o.getTotal()) + "元" + "\n謝謝您的惠顧,歡迎再度光臨!");
						print.setEnabled(true);
						printSet=true;
					}
				} else
					JOptionPane.showMessageDialog(null, "請輸入付款金額");

			}
		});
		btnNewButton_1_1_1.setBounds(266, 10, 87, 23);
		panel.add(btnNewButton_1_1_1);
	}
}
