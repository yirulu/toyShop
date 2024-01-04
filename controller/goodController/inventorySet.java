package controller.goodController;

import java.awt.Component;
import java.awt.EventQueue;
import java.util.List;

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
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class inventorySet extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField newInventory;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inventorySet frame = new inventorySet();
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
	public inventorySet() {
		setTitle("Toy Shop");
		setIconImage(Toolkit.getDefaultToolkit().getImage(inventorySet.class.getResource("/image/title.jpg")));
		List<good> l=new goodDaoImpl().QueryAll();
		DefaultComboBoxModel<good> comboBoxModel = new DefaultComboBoxModel<>(l.toArray(new good[0]));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(343, 297);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImagePanel panel = new ImagePanel("/image/goodBackground.jpg");
		panel.setBounds(0, 0, 327, 258);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("新庫存");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("新細明體", Font.BOLD, 16));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(41, 143, 66, 35);
		panel.add(lblNewLabel_1);
		
		newInventory = new JTextField();
		newInventory.setBounds(117, 150, 119, 21);
		panel.add(newInventory);
		newInventory.setColumns(10);
		
		
		
		
		JButton btnNewButton_1 = new JButton("回管理頁");
		btnNewButton_1.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button.goodManager();
				dispose();
			}
		});
		btnNewButton_1.setBounds(51, 199, 104, 35);
		panel.add(btnNewButton_1);
		
		JLabel oldIInventory = new JLabel("請選擇一樣商品");
		oldIInventory.setFont(new Font("新細明體", Font.BOLD, 16));
		oldIInventory.setForeground(Color.WHITE);
		oldIInventory.setHorizontalAlignment(SwingConstants.CENTER);
		oldIInventory.setBounds(80, 46, 181, 31);
		panel.add(oldIInventory);
		
		JComboBox comboBox = new JComboBox<>(comboBoxModel);
		comboBox.setSelectedIndex(-1);
		comboBox.setFont(new Font("新細明體", Font.BOLD, 16));
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
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				good g=(good)comboBox.getSelectedItem();
				oldIInventory.setText("原庫存"+g.getInventory().toString()+"個");
			}
		});
		comboBox.setBounds(80, 87, 181, 46);
		panel.add(comboBox);
		
		JButton btnNewButton = new JButton("改庫存");
		btnNewButton.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(comboBox.getSelectedItem()==null)
				{
					JOptionPane.showMessageDialog(null,"請選擇一樣商品");
				}else {
				if(!(newInventory.getText().isEmpty()))
				{	
					Integer Inventory=Integer.parseInt(newInventory.getText());
					if(Inventory<=0)
					{
						JOptionPane.showMessageDialog(null,"請輸入正整數");
						newInventory.setText("");
					}else
					{	
						good g=(good)(comboBox.getSelectedItem());
						JOptionPane.showMessageDialog(null,"修改成功");
						new goodDaoImpl().changeInventory(g,Inventory);
						button.goodManager();
						dispose();
					}
				}else
				{
					JOptionPane.showMessageDialog(null,"欄位不能為空");
				}
			}
			}	
		});
		btnNewButton.setBounds(176, 200, 104, 35);
		panel.add(btnNewButton);
		
		JLabel oldIInventory_1 = new JLabel("更改庫存");
		oldIInventory_1.setFont(new Font("新細明體", Font.BOLD, 20));
		oldIInventory_1.setForeground(Color.WHITE);
		oldIInventory_1.setHorizontalAlignment(SwingConstants.CENTER);
		oldIInventory_1.setBounds(80, 5, 181, 31);
		panel.add(oldIInventory_1);
	}
}
