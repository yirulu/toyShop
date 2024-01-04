package controller.loginController;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.Clock;
import util.ImagePanel;
import util.button;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class managerTerminal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					managerTerminal frame = new managerTerminal();
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
	public managerTerminal() {
		setTitle("Toy Shop");
		setIconImage(Toolkit.getDefaultToolkit().getImage(managerTerminal.class.getResource("/image/title.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(364, 342);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImagePanel panel = new ImagePanel("/image/loginBackground.jpg");
		panel.setBounds(0, 0, 348, 303);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton button1 = new JButton("會員管理");
		button1.setFont(new Font("新細明體", Font.BOLD, 16));
		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button.memberManager();
				dispose();
			}
		});
		button1.setBounds(98, 52, 159, 41);
		panel.add(button1);
		
		JButton button2 = new JButton("商品管理");
		button2.setFont(new Font("新細明體", Font.BOLD, 16));
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button.goodManager();
				dispose();
			}
		});
		button2.setBounds(98, 144, 159, 41);
		panel.add(button2);
		
		JButton button2_1 = new JButton("回登入頁");
		button2_1.setFont(new Font("新細明體", Font.BOLD, 16));
		button2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button.login();
				dispose();
			}
		});
		button2_1.setBounds(98, 232, 159, 41);
		panel.add(button2_1);
		
		JLabel clock = new Clock();
		clock.setFont(new Font("新細明體", Font.BOLD, 13));
		clock.setForeground(Color.WHITE);
		clock.setHorizontalAlignment(SwingConstants.CENTER);
		clock.setBounds(0, 0, 149, 26);
		panel.add(clock);
	}

}
