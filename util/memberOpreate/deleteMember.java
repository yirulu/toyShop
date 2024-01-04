package util.memberOpreate;

import javax.swing.JOptionPane;

import dao.impl.memberDaoImpl;

public class deleteMember {

	public static void deleteM(String Account) {
		
		if (Account.isEmpty()) {
			JOptionPane.showMessageDialog(null, "內容不能空白", "錯誤", JOptionPane.ERROR_MESSAGE);

		} else {
			if (new memberDaoImpl().accIsExist(Account)) {
				int choice = JOptionPane.showConfirmDialog(null, "是否要刪除該會員?", "刪除確認", JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "刪除成功");
					new memberDaoImpl().delete(Account);
				}
			} else
				JOptionPane.showMessageDialog(null, "帳號不存在");
		}
	}
}
