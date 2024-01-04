package controller.goodController;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.impl.goodDaoImpl;
import model.good;
import util.ImagePanel;
import util.button;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class goodDel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					goodDel frame = new goodDel();
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
	public goodDel() {
		setTitle("Toy Shop");
		setIconImage(Toolkit.getDefaultToolkit().getImage(goodDel.class.getResource("/image/title.jpg")));
		List<good> l=new goodDaoImpl().QueryAll();
		DefaultComboBoxModel<good> comboBoxModel = new DefaultComboBoxModel<>(l.toArray(new good[0]));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize( 351, 261);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImagePanel panel = new ImagePanel("/image/goodBackground.jpg");
		panel.setBounds(0, 0, 335, 222);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		
		
		JButton btnNewButton_1 = new JButton("上一頁");
		btnNewButton_1.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button.goodManager();
				dispose();
			}
		});
		btnNewButton_1.setBounds(54, 149, 109, 47);
		panel.add(btnNewButton_1);
		
		JComboBox comboBox = new JComboBox<>(comboBoxModel);
		comboBox.setFont(new Font("新細明體", Font.BOLD, 16));
		comboBox.setSelectedIndex(-1);
		comboBox.setBounds(83, 92, 176, 47);
		comboBox.setRenderer(new DefaultListCellRenderer() {
	            @Override
	            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
	                    boolean isSelected, boolean cellHasFocus) {
	                if (value instanceof good) {
	                    value = ((good) value).getName();
	                }
	                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	            }
	        });
		panel.add(comboBox);
		JButton btnNewButton = new JButton("下架");
		btnNewButton.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(comboBox.getSelectedItem()==null)
				{
					JOptionPane.showMessageDialog(null,"請選擇一樣商品");
				}else
				{
				good g=(good)(comboBox.getSelectedItem());
				int choice=JOptionPane.showConfirmDialog(null,"下架確認", "是否要下架商品?",JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null,"商品已下架");
					new goodDaoImpl().delete(g.getName());
					button.goodManager();
					dispose();
		        } 
				}
		}});
		btnNewButton.setBounds(185, 149, 109, 47);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("商品下架");
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(83, 10, 176, 38);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("請選擇一樣商品");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("新細明體", Font.BOLD, 16));
		lblNewLabel_1.setBounds(83, 47, 176, 38);
		panel.add(lblNewLabel_1);
		
		
	}
}
