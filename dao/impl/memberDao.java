package dao.impl;

import java.util.List;

import model.member;

public interface memberDao {
	//create
	public void addmember(member m);
	//read
	public List<member> QueryAll();
	public member QueryAccount(String account);
	public List<member> QueryName(String name);
	public List<member> QueryPhone(String phone);
	public List<member> QueryEmail(String email);
	public Boolean accIsExist(String account);
	public Boolean nameIsExist(String name);
	public Boolean phoneIsExist(String phone);
	public Boolean emailIsExist(String email);
	//update
	public member modifyPw(String password,String account);
	//delete
	
}
