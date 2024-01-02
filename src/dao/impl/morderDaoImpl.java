package dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import dao.morderDao;
import model.good;
import model.morder;
import util.Clock;


public class morderDaoImpl implements morderDao{

	public static void main(String[] args) {
		//morder o=new morder("123456",new goodDaoImpl().QueryName("apple"),);
		/*good g1=new good("apple",20,50,"");
		good g2=new good("banana",15,100,"");
		List<good> l=new ArrayList();
		new morderDaoImpl().add(l,g1,6);
		new morderDaoImpl().add(l,g2,8);
		int total=new morderDaoImpl().total(l);*/
		//String detail=new morderDaoImpl().Gson(l);
		
		
		/*String detail="";
		for(good o:l)
		{
			detail=detail+"品名:"+o.getName()+
						"\t價格:"+o.getPrice()+
						"\t數量:"+o.getAmount()+"\n";
		}
		morder ol=new morder("Mr.Lee","112233",detail,total,Clock.getCurrentTime());*/
	
		//System.out.println(show);
		
		
		//System.out.println("total="+new morderDaoImpl().total(l));
		System.out.println(new morderDaoImpl().orderIdAdd());
	}

	@Override
	public void add(morder o) {
		Connection conn=DbConnection.getDb();
		String SQL="insert into morder(account,orderId,detail,total,orderDate) value(?,?,?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ps.setString(1,o.getAccount() );
			ps.setString(2,o.getOrderId());
			ps.setString(3, o.getDetail());
			ps.setInt(4,o.getTotal());
			ps.setString(5, o.getOrderDate());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<good> add(List<good> l,good g,int amount) {
		g.setAmount(amount);
		g.addSale(amount);
		l.add(g);
		return l;
	}

	@Override
	public List<morder> QueryAll() {
		Connection conn=DbConnection.getDb();
		String SQL="select * from morder";
		List<morder> l=new ArrayList();
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				morder o=new morder();
				o.setId(rs.getInt("id"));
				o.setAccount(rs.getString("account"));
				o.setOrderId(rs.getString("orderId"));
				o.setDetail(rs.getString("detail"));
				o.setTotal(rs.getInt("total"));
				o.setOrderDate(rs.getString("orderDate"));
				l.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public morder QueryOrderId(String orderId) {
		Connection conn=DbConnection.getDb();
		String SQL="select * from morder where orderId=?";
		morder o=null;
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ps.setString(1, orderId);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				o=new morder();
				o.setId(rs.getInt("id"));
				o.setAccount(rs.getString("account"));
				o.setOrderId(rs.getString("orderId"));
				o.setDetail(rs.getString("detail"));
				o.setTotal(rs.getInt("total"));
				o.setOrderDate(rs.getString("orderDate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}

	@Override
	public void update(morder o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String orderId) {
		Connection conn=DbConnection.getDb();
		String SQL="delete from morder where orderId=?";
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ps.setString(1, orderId);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Integer total(List<good> l) {
		int total=0;
		for(good o:l)
		{
			total=total+(o.getPrice()*o.getAmount());
		}
		return total;
	}

	@Override
	public String orderIdAdd() {
		String randomCode="";
		do {
		Random random = new Random();
        int randomNumber = random.nextInt(10000);
        Date currentDateTime = new Date();
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        char randomLetter1 = (char) ('A' + random.nextInt(26));
        char randomLetter2 = (char) ('A' + random.nextInt(26));
        String formattedDateTime = dateTimeFormat.format(currentDateTime);
        randomCode = formattedDateTime + randomLetter1 + randomLetter2 + String.format("%04d", randomNumber);
		}while(new morderDaoImpl().orderIdIsExist(randomCode));
		
		return randomCode;
	}

	@Override
	public Boolean orderIdIsExist(String orderId) {
		Connection conn=DbConnection.getDb();
		String SQL="select * from morder where orderId=?";
		Boolean check=false;
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ps.setString(1, orderId);
			ResultSet rs=ps.executeQuery();
			check=rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
	}

	

	

}
