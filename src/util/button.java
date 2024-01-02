package util;

import controller.loginController.LoginUI;
import controller.goodController.goodManager;
import controller.loginController.memberManager;
import controller.loginController.managerTerminal;
public class button {
	public static void login()
	{
		LoginUI login=new LoginUI();
		login.setVisible(true);
	}
	public static void goodManager()
	{
		goodManager gm=new goodManager();
		gm.setVisible(true);
	}
	public static void memberManager()
	{
		memberManager mm=new memberManager();
		mm.setVisible(true);
	}
	public static void managerTerminal()
	{
		managerTerminal mt=new managerTerminal();
		mt.setVisible(true);
	}
}
