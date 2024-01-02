package model;

public class good {
	private String name;
	private Integer price;
	private Integer amount;
	private Integer inventory;
	private Integer salesVol;
	private String image;
	public good() {
		super();
		// TODO Auto-generated constructor stub
	}
	public good(String name, Integer price, Integer inventory,String image) {
		super();
		this.name = name;
		this.price = price;
		this.inventory = inventory;
		salesVol = 0;
		this.image=image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getSalesVol() {
		return salesVol;
	}
	public void setSalesVol(Integer salesVol) {
		this.salesVol = salesVol;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void addSale(int amount) 
	{
		this.salesVol+=amount;
		this.inventory-=amount;
	}
	
	
}
