package util;

import javax.swing.JOptionPane;

import controller.orderController.orderUI;
import dao.impl.memberDaoImpl;
import model.member;

public class loginCheck {
	public static Boolean check(String Account,String Password,Boolean checkOk)
	{	
		
		if(!(Account.isEmpty()))
		{
			if(new memberDaoImpl().accIsExist(Account))
			{
			member m=new memberDaoImpl().QueryAccount(Account);
			if(!(Password.isEmpty()))
			{
				if(Password.equals(m.getPassword()))
				{				
				JOptionPane.showMessageDialog(null, "登入成功");
				orderUI o=new orderUI(Account);
				o.setVisible(true);
				checkOk=true;
				}else
				{
				JOptionPane.showMessageDialog(null, "密碼錯誤");
				}
			}else 
			{
				JOptionPane.showMessageDialog(null, "請輸入密碼");
			}
		}else
		{
			JOptionPane.showMessageDialog(null, "帳號不存在");
		}
		}else
		{		
			JOptionPane.showMessageDialog(null, "請輸入帳號");
		}
		return checkOk;	
	}
}
