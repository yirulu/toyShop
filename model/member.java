package model;

public class member {
	
	public static void main(String[] args)
	{
		member m=new member("222","222","222","222","222");
		m.setVipLevel(2);
		System.out.println(m.discount(m));
	}
	
	private String account;
	private String password;
	private String name;
	private String phone;
	private String email;
	private Integer accConsumption;
	private Integer vipLevel;
	public member() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public member(String account, String password, String name, String phone, String email) {
		super();
		this.account = account;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.email = email;
		accConsumption=0;
		vipLevel=0;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getVipLevel() {
		return vipLevel;
	}
	public void setVipLevel(Integer vipLevel) {
		this.vipLevel = vipLevel;
	}
	public Integer getAccConsumption() {
		return accConsumption;
	}
	public void setAccConsumption(Integer accConsumption) {
		this.accConsumption = accConsumption;
		vipUpdate(accConsumption);
	} 
	
	public void vipUpdate(Integer accConsumption)
	{	
		if(accConsumption>=2000)
			this.vipLevel=1;
		if(accConsumption>=5000)
			this.vipLevel=2;
		if(accConsumption>=10000)
			this.vipLevel=3;
		if(accConsumption>=20000)
			this.vipLevel=4;
	}
	
	public String vipUpdateShow(Integer total)
	{
		String gap="";
					
					
		
		if(total<2000)
			gap="差"+(2000-total)+"元可升級為VIP1,之後享9折優惠";
		else if(total<5000)
			gap="差"+(5000-total)+"元可升級為VIP2,之後享85折優惠";			
		else if(total<10000)
			gap="差"+(10000-total)+"元可升級為VIP3,之後享8折優惠";
		else if(total<20000)
			gap="差"+(20000-total)+"元可升級為VIP4,之後享5折優惠";
		else
			gap="恭喜成為最高級VIP,感謝您的購買!";
		
		return gap;
		
	}
	
	public Double discount(member m)
	{
					
		switch(m.getVipLevel()) 
		{				
			case 1:
				return 0.9;
				
				
			case 2:
				return 0.85;
								
			case 3:
				return 0.8;
				
				
			case 4:
				return 0.5;
				
				
			default:
				return 1.0;
				
		}
		
		
	}
	public String discountShow(Integer vipLevel)
	{
		switch(getVipLevel()) 
		{				
			case 1:
				return "打9折";
				
				
			case 2:
				return "打85折";
								
			case 3:
				return "打8折";
				
				
			case 4:
				return "打5折";
				
				
			default:
				return "無折扣";
				
		}
		
	}
}
