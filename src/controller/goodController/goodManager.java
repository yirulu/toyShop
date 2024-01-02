package controller.goodController;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;


import dao.impl.goodDaoImpl;
import dao.impl.memberDaoImpl;
import model.good;
import model.member;
import util.ImagePanel;
import util.button;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;
import java.awt.Font;

public class goodManager extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable gTable;
	private DefaultTableModel tableModel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					goodManager frame = new goodManager();
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
	public goodManager() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(634, 496);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImagePanel panel = new ImagePanel("/image/managerBackground.jpg");
		panel.setBounds(0, 0, 618, 457);
		contentPane.add(panel);
		panel.setLayout(null);
		
		gTable = new JTable();
		gTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableModel = new DefaultTableModel();
		Object[] column = { "商品名稱", "價格", "庫存","銷售量","商品圖片"};
		Object[] row = new Object[5];
		tableModel.setColumnIdentifiers(column);
		gTable.setModel(tableModel);
		tableModel = (DefaultTableModel) gTable.getModel();
		for (good g : new goodDaoImpl().QueryAll()) {
			tableModel.addRow(new Object[] {g.getName(),g.getPrice(),g.getInventory(),g.getSalesVol(),g.getImage()});
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 97, 598, 307);
		panel.add(scrollPane);

		scrollPane.setViewportView(gTable);
		JButton btnNewButton = new JButton("回復列表");
		btnNewButton.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tableModel.setRowCount(0);
				for (good g : new goodDaoImpl().QueryAll()) {
					tableModel.addRow(new Object[] {g.getName(),g.getPrice(),g.getInventory(),g.getSalesVol(),g.getImage()});
				}
			}
		});
		btnNewButton.setBounds(10, 48, 123, 33);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("依價格排列");
		btnNewButton_1.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tableModel.setRowCount(0);
				for (good g : new goodDaoImpl().QueryByPrice()) {
					tableModel.addRow(new Object[] {g.getName(),g.getPrice(),g.getInventory(),g.getSalesVol(),g.getImage()});
				}
			
			}
		});
		btnNewButton_1.setBounds(176, 48, 123, 33);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("依庫存排列");
		btnNewButton_2.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tableModel.setRowCount(0);
				for (good g : new goodDaoImpl().QueryByInventory()) {
					tableModel.addRow(new Object[] {g.getName(),g.getPrice(),g.getInventory(),g.getSalesVol(),g.getImage()});
				}
			}
		});
		btnNewButton_2.setBounds(330, 48, 123, 33);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("依銷量排列");
		btnNewButton_3.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tableModel.setRowCount(0);
				for (good g : new goodDaoImpl().QueryBySalesVol()) {
					tableModel.addRow(new Object[] {g.getName(),g.getPrice(),g.getInventory(),g.getSalesVol(),g.getImage()});
				}
			}
		});
		btnNewButton_3.setBounds(485, 48, 123, 33);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("更改價格");
		btnNewButton_4.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				priceSet ps=new priceSet();
				ps.setVisible(true);
				dispose();
			}
		});
		btnNewButton_4.setBounds(10, 414, 123, 33);
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_4_1 = new JButton("商品上架");
		btnNewButton_4_1.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton_4_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goodAdd ga=new goodAdd();
				ga.setVisible(true);
				dispose();				
			}
		});
		btnNewButton_4_1.setBounds(330, 414, 123, 33);
		panel.add(btnNewButton_4_1);
		
		JButton btnNewButton_4_2 = new JButton("商品下架");
		btnNewButton_4_2.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton_4_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goodDel gd=new goodDel();
				gd.setVisible(true);
				dispose();
			}
		});
		btnNewButton_4_2.setBounds(485, 414, 123, 33);
		panel.add(btnNewButton_4_2);
		
		JButton btnNewButton_4_3 = new JButton("更改庫存");
		btnNewButton_4_3.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton_4_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				inventorySet is=new inventorySet();
				is.setVisible(true);
				dispose();
			}
		});
		btnNewButton_4_3.setBounds(176, 414, 123, 33);
		panel.add(btnNewButton_4_3);
		
		JButton btnNewButton_5 = new JButton("回總管理頁");
		btnNewButton_5.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton_5.setBounds(485, 6, 123, 23);
		panel.add(btnNewButton_5);
		
		JLabel lblNewLabel = new JLabel("上架商品清單");
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(216, 6, 193, 37);
		panel.add(lblNewLabel);
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button.managerTerminal();
				dispose();
			}
		});
	}
}
