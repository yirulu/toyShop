package dao;

import java.util.List;

import model.good;
import model.morder;

public interface morderDao {
	//create
	public List<good> add(List<good> l,good g,int amount);//generate List<good>
	public String orderIdAdd();//generate orderId
	public void add(morder o);
	//read
	public List<morder> QueryAll();
	public List<morder> QueryAccount(String account);
	public Boolean orderIdIsExist(String orderId);
	public morder QueryOrderId(String orderId);
	
	//update
	public void update(morder o);
	//delete
	public void delete(String orderId);
	
	
	//calculate
	public Integer total(List<good> l);
	
}
