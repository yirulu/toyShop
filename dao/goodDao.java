package dao;

import java.util.List;

import model.good;

public interface goodDao {
	//create
	public void add(good g);
	//read
	public List<good> QueryAll();
	public Boolean NameIsExist(String name);
	public good QueryName(String name);
	public List<good> QueryByPrice();
	public List<good> QueryByInventory();
	public List<good> QueryBySalesVol();
	//update
	public void changePrice(good g,int price);
	public void changeInventory(good g,int inventory);
	public void changeSalesVol(good g,int salesVol);
	
	//delete
	public void delete(String name);
	
}
