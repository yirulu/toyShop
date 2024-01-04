package util.memberOpreate;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.impl.memberDaoImpl;
import model.member;
import javax.swing.table.DefaultTableModel;
public class memberFilter {
	
	public static void filter(DefaultTableModel tableModel,String Account,String Name,String Phone,String Email,Boolean A,Boolean N,Boolean P,Boolean E)
	{
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

}
