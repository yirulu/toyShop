package controller.orderController;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.impl.goodDaoImpl;
import dao.impl.memberDaoImpl;
import dao.impl.morderDaoImpl;
import model.good;
import model.member;
import model.morder;
import util.Clock;
import util.ImagePanel;
import util.button;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import javax.swing.JProgressBar;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Font;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTable;

public class orderUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private BufferedImage backgroundImage;
	static String Account;
	int sum;
	int total;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					orderUI frame = new orderUI(Account);
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
	public orderUI(String Account_1) {
		if(Account_1!=null)
		{
			this.Account=Account_1;			
		}
		member user=new memberDaoImpl().QueryAccount(Account);
		tableModel=new DefaultTableModel();
		List<good> l=new goodDaoImpl().QueryAll();
		DefaultComboBoxModel<good> comboBoxModel = new DefaultComboBoxModel<>(l.toArray(new good[0]));
		Map<good,Integer> gMap=new HashMap<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(667, 558);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImagePanel panel = new ImagePanel("/image/background.png");
		panel.setBounds(0, 0, 651, 519);
		
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel photo = new JLabel("預覽圖片");
		photo.setForeground(Color.WHITE);
		photo.setFont(new Font("新細明體", Font.BOLD, 16));
		photo.setHorizontalAlignment(SwingConstants.CENTER);
		photo.setBounds(30, 178, 255, 255);
		panel.add(photo);
		
		JTextArea price = new JTextArea();
		price.setFont(new Font("Monospaced", Font.BOLD, 16));
		price.setEditable(false);
		price.setText("請選擇一件商品");
		price.setBackground(SystemColor.menu);
		price.setBounds(81, 68, 146, 45);
		panel.add(price);
		
		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
		JSpinner spinner = new JSpinner(spinnerModel);
		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinner.setBounds(229, 123, 66, 45);
		panel.add(spinner);
		
		JComboBox<good> goodListCombo = new JComboBox<>(comboBoxModel);
		goodListCombo.setFont(new Font("新細明體", Font.BOLD, 18));
		goodListCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				photo.setText("");
				spinner.setValue(1);
				good g=(good)goodListCombo.getSelectedItem();
				price.setText("售價"+g.getPrice().toString()+"元"+"\n庫存尚有"+g.getInventory().toString()+"件");
				photo.setIcon(new ImageIcon(orderUI.class.getResource(g.getImage())));
				//設定下單數量不能超過庫存
				int newMaxValue = g.getInventory();
	            ((SpinnerNumberModel) spinner.getModel()).setMaximum(newMaxValue);
			}
		});
		goodListCombo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                if (value instanceof good) {
                    value = ((good) value).getName();
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
		goodListCombo.setBounds(10, 123, 209, 45);
		panel.add(goodListCombo);
		
		
		
		JButton btnNewButton = new JButton("回登入頁");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button.login();
				dispose();
			}
		});
		btnNewButton.setBounds(10, 485, 136, 23);
		panel.add(btnNewButton);
		
		
		
		JTextArea output = new JTextArea();
		output.setFont(new Font("Monospaced", Font.BOLD, 14));
		output.setEditable(false);
		output.setBounds(323, 0, 328, 58);
		panel.add(output);
		
		
		
		JLabel label = new JLabel("New label");
		label.setBounds(391, 100, -363, -114);
		panel.add(label);
		
		JButton delete = new JButton("清除選取");
		delete.setFont(new Font("新細明體", Font.BOLD, 16));
		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
						output.setText(" ");
						int i = table.getSelectedRow();
						if (i >= 0) {
							tableModel.removeRow(i);
						} else {
							int rowCount = tableModel.getRowCount();
							if (rowCount > 0) {
								// 刪除最後一行
								tableModel.removeRow(rowCount - 1);
							}

						}
				
			}
		});
		delete.setBounds(379, 437, 103, 36);
		panel.add(delete);
		
		
		Object[] column = { "商品", "價格","數量" };
		Object[] row = new Object[3];
		tableModel.setColumnIdentifiers(column);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(379, 68, 262, 351);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(table);
		table.setModel(tableModel);
		tableModel = (DefaultTableModel) table.getModel();
		
		JButton btnNewButton_1 = new JButton("加到購物車");
		btnNewButton_1.setFont(new Font("新細明體", Font.BOLD, 14));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tableModel.setRowCount(0);
				good g=(good)goodListCombo.getSelectedItem();
				g.setAmount((int)spinner.getValue());
				if(gMap.containsKey(g))
				{
					int oldAmount=gMap.get(g);
					int newAmount=oldAmount+g.getAmount();
					g.setAmount(newAmount);
					gMap.put(g, g.getAmount());
				}else
				gMap.put(g, g.getAmount());
				
				
				gMap.forEach((K,V)->
				{
					tableModel.addRow(new Object[] {K.getName(),K.getPrice(),V});
				});
				//計算總金額
				sum = 0;
				int rowCount = tableModel.getRowCount();
				for (int i = 0; i < rowCount; i++) {
					Object singlePrize = tableModel.getValueAt(i, 1);
					Object amount = tableModel.getValueAt(i, 2);
					if (singlePrize instanceof Number && amount instanceof Number) {
						int result = ((Number) singlePrize).intValue() * ((Number) amount).intValue();
						sum += result;
					}
					
					total=(int)(sum*(user.discount(user)));
					output.setText("總金額為\t" + sum + "元"+"\n折扣後為\t"+total+"元\n"+user.vipUpdateShow(user.getAccConsumption()+total));

				}
				
			}
		});
		
		btnNewButton_1.setBounds(97, 437, 110, 36);
		panel.add(btnNewButton_1);
		
		JTextArea userInfo = new JTextArea();
		userInfo.setToolTipText("VIP 0  無折扣\r\nVIP 1 95折\r\nVIP 2 9折\r\nVIP 3 85折\r\nVIP 4 5折");
		userInfo.setFont(new Font("Monospaced", Font.BOLD, 16));
		userInfo.setEditable(false);
		userInfo.setText(user.getName()+"\t先生/小姐您好\n您的VIP等級為:\t"+user.getVipLevel());
		userInfo.setBackground(SystemColor.menu);
		userInfo.setBounds(0, 0, 328, 58);
		panel.add(userInfo);
		
		JLabel clock = new Clock();
		clock.setForeground(Color.WHITE);
		clock.setFont(new Font("新細明體", Font.BOLD, 14));
		clock.setBounds(483, 483, 153, 26);
		panel.add(clock);
		
		
		JButton btnNewButton_1_2 = new JButton("送出訂單");
		btnNewButton_1_2.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
						
				String orderId=new morderDaoImpl().orderIdAdd();
				String detail="";
				
				for(int i=0;i<table.getRowCount();i++)
				{
					detail+="商品名稱:\t"+table.getValueAt(i,0)+
							"\t價格:"+table.getValueAt(i,1)+
							"\t數量:"+table.getValueAt(i,2)+"\n";
					
				}
				String orderDate=Clock.getCurrentTime();
				Integer oldAC=user.getAccConsumption();
				Integer newAC=oldAC+total;
				user.setAccConsumption(newAC);
				
				new memberDaoImpl().update(user,newAC,user.getVipLevel());
				for(int i=0;i<tableModel.getRowCount();i++)
				{
					good g=new goodDaoImpl().QueryName(tableModel.getValueAt(i, 0).toString());
					g.setInventory(g.getInventory()-(int)(tableModel.getValueAt(i, 2)));
					g.setSalesVol(g.getSalesVol()+(int)(tableModel.getValueAt(i, 2)));
					new goodDaoImpl().changeInventory(g, g.getInventory());
					new goodDaoImpl().changeSalesVol(g, g.getSalesVol());
				}
				
				morder o=new morder(Account,orderId,detail,total,orderDate);
				new morderDaoImpl().add(o);
				
				JOptionPane.showMessageDialog(null, "下單完成");
				orderPrint op=new orderPrint(orderId);
				op.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1_2.setBounds(533, 437, 103, 36);
		panel.add(btnNewButton_1_2);
		
		
		
		
		
	}
}
