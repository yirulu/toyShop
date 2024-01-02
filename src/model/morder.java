package model;

import java.util.List;

import util.Clock;

public class morder {
	private Integer id;
	private String account;
	private String orderId;
	private String detail;
	private Integer total;
	private String orderDate;
	public morder() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	public morder( String account, String orderId, String detail, Integer total, String orderDate) {
		super();
		
		this.account = account;
		this.orderId = orderId;
		this.detail = detail;
		this.total = total;
		this.orderDate = orderDate;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	
	}
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}



	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
	
}
