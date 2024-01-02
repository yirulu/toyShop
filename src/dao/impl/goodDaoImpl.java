package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.goodDao;
import model.good;

public class goodDaoImpl implements goodDao{

	public static void main(String[] args) {
		System.out.println(new goodDaoImpl().NameIsExist("range"));
		/*good g=new good("guava",50,150,"this is url");
		new goodDaoImpl().add(g);*/
		/*List<good> l=new goodDaoImpl().QueryAll();
		String show="";
		for(good o:l)
		{
			show=show+"名稱:"+o.getName()+
					"\t價格:"+o.getPrice()+
					"\t庫存:"+o.getInventory()+"\n";
		}
		System.out.println(show);*/
		/*good g=new goodDaoImpl().QueryName("apple");
		String show="";
		show="名稱:"+g.getName()+
			"\t價格:"+g.getPrice()+
			"\t庫存:"+g.getInventory()+"\n";
		
		System.out.println(show);*/
		
		/*good g=new goodDaoImpl().QueryName("apple");
		g.setPrice(200);
		new goodDaoImpl().changePrice(g);*/
		//new goodDaoImpl().delete("guava");
	}

	@Override
	public void add(good g) {
		Connection conn=DbConnection.getDb();
		String SQL="insert into good(name,price,inventory,salesVol,image) value(?,?,?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ps.setString(1, g.getName());
			ps.setInt(2, g.getPrice());
			ps.setInt(3, g.getInventory());
			ps.setInt(4, g.getSalesVol());
			ps.setString(5, g.getImage());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<good> QueryAll() {
		Connection conn=DbConnection.getDb();
		String SQL="select * from good";
		List<good> l=new ArrayList();
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				good g=new good();
				g.setName(rs.getString("name"));
				g.setPrice(rs.getInt("price"));
				g.setInventory(rs.getInt("inventory"));
				g.setSalesVol(rs.getInt("salesVol"));
				g.setImage(rs.getString("image"));
				l.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return l;
	}
	@Override
	public Boolean NameIsExist(String name) {
		Connection conn=DbConnection.getDb();
		String SQL="select * from good where name=?";
		Boolean check=false;
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			check=rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
	}
	@Override
	public good QueryName(String name) {
		Connection conn=DbConnection.getDb();
		String SQL="select * from good where name=?";
		good g=null;
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				g=new good();
				g.setName(rs.getString("name"));
				g.setPrice(rs.getInt("price"));
				g.setInventory(rs.getInt("inventory"));
				g.setSalesVol(rs.getInt("salesVol"));
				g.setImage(rs.getString("image"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return g;
	}

	@Override
	public void changePrice(good g,int price) {
		Connection conn=DbConnection.getDb();
		String SQL="update good set price=? where name=?";
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ps.setInt(1, price);
			ps.setString(2, g.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(String name) {
		Connection conn=DbConnection.getDb();
		String SQL="delete from good where name=?";
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ps.setString(1, name);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void changeInventory(good g,int inventory) {
		Connection conn=DbConnection.getDb();
		String SQL="update good set inventory=? where name=?";
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ps.setInt(1, inventory);
			ps.setString(2, g.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<good> QueryByPrice() {
		Connection conn=DbConnection.getDb();
		String SQL="select * from good order by price desc";
		List<good> l=new ArrayList();
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				good g=new good();
				g.setName(rs.getString("name"));
				g.setPrice(rs.getInt("price"));
				g.setInventory(rs.getInt("inventory"));
				g.setSalesVol(rs.getInt("salesVol"));
				g.setImage(rs.getString("image"));
				l.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return l;
	}

	@Override
	public List<good> QueryByInventory() {
		Connection conn=DbConnection.getDb();
		String SQL="select * from good order by inventory desc";
		List<good> l=new ArrayList();
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				good g=new good();
				g.setName(rs.getString("name"));
				g.setPrice(rs.getInt("price"));
				g.setInventory(rs.getInt("inventory"));
				g.setSalesVol(rs.getInt("salesVol"));
				g.setImage(rs.getString("image"));
				l.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return l;
	}

	@Override
	public List<good> QueryBySalesVol() {
		Connection conn=DbConnection.getDb();
		String SQL="select * from good order by salesVol desc";
		List<good> l=new ArrayList();
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				good g=new good();
				g.setName(rs.getString("name"));
				g.setPrice(rs.getInt("price"));
				g.setInventory(rs.getInt("inventory"));
				g.setSalesVol(rs.getInt("salesVol"));
				g.setImage(rs.getString("image"));
				l.add(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return l;
	}

	@Override
	public void changeSalesVol(good g, int salesVol) {
		Connection conn=DbConnection.getDb();
		String SQL="update good set salesVol=? where name=?";
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ps.setInt(1, salesVol);
			ps.setString(2, g.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	

	

}
