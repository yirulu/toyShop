package controller.goodController;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.impl.goodDaoImpl;
import model.good;
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
		List<good> l=new goodDaoImpl().QueryAll();
		DefaultComboBoxModel<good> comboBoxModel = new DefaultComboBoxModel<>(l.toArray(new good[0]));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize( 351, 194);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 335, 155);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		
		
		JButton btnNewButton_1 = new JButton("上一頁");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button.goodManager();
				dispose();
			}
		});
		btnNewButton_1.setBounds(53, 98, 109, 47);
		panel.add(btnNewButton_1);
		
		JComboBox comboBox = new JComboBox<>(comboBoxModel);
		comboBox.setBounds(86, 10, 176, 47);
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
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				good g=(good)(comboBox.getSelectedItem());
				JOptionPane.showMessageDialog(null,"商品已下架");
				new goodDaoImpl().delete(g.getName());
				button.goodManager();
				dispose();
				
		}});
		btnNewButton.setBounds(185, 98, 109, 47);
		panel.add(btnNewButton);
		
		
	}

}
