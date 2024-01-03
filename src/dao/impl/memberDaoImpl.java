package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.memberDao;
import dao.impl.DbConnection;
import model.member;

public class memberDaoImpl implements memberDao {

	public static void main(String[] args) {

		// Create test
		/*
		 * member m=new member("Mr.Lee","aaa","123","123456","abc@abc.com"); new
		 * memberDaoImpl().addmember(m);
		 * 
		 * System.out.println(new memberDaoImpl().accIsExist("aad"));
		 */

		// read test
		/*
		 * List<member> l=new memberDaoImpl().QueryEmail("333"); String show="";
		 * for(member m:l) { show=show+"帳號:"+m.getAccount()+ "密碼:"+m.getPassword()+
		 * "姓名:"+m.getName()+ "電話:"+m.getPhone()+ "Email:"+m.getEmail()+"\n"; }
		 * System.out.println(show);
		 */

		/*
		 * member m=new memberDaoImpl().QueryAccount("555");----->QueryAccount
		 * System.out.println("帳號:"+m.getAccount()+ "密碼:"+m.getPassword()+
		 * "姓名:"+m.getName()+ "電話:"+m.getPhone()+ "Email:"+m.getEmail());
		 */

		//System.out.println(new memberDaoImpl().nameIsExist("11"));
		member m=new member(); 
	}

	@Override
	public void addmember(member m) {
		Connection conn = DbConnection.getDb();
		String SQL = "insert into member(account,password,name,phone,email,accConsumption,vipLevel) value(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, m.getAccount());
			ps.setString(2, m.getPassword());
			ps.setString(3, m.getName());
			ps.setString(4, m.getPhone());
			ps.setString(5, m.getEmail());
			ps.setInt(6, 0);
			ps.setInt(7, 0);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Boolean accIsExist(String account) {
		Connection conn = DbConnection.getDb();
		String SQL = "select * from member where account=?";
		Boolean check = false;
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, account);
			ResultSet rs = ps.executeQuery();
			check = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public Boolean nameIsExist(String name) {
		Connection conn = DbConnection.getDb();
		String SQL = "select * from member where name=?";
		Boolean check = false;
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			check = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public Boolean phoneIsExist(String phone) {
		Connection conn = DbConnection.getDb();
		String SQL = "select * from member where phone=?";
		Boolean check = false;
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, phone);
			ResultSet rs = ps.executeQuery();
			check = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public Boolean emailIsExist(String email) {
		Connection conn = DbConnection.getDb();
		String SQL = "select * from member where email=?";
		Boolean check = false;
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			check = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	@Override
	public void modifyPw(String password, String account) {
		String SQL = "update member set password=? where account=?";
		Connection conn = DbConnection.getDb();

		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, password);
			ps.setString(2, account);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<member> QueryAll() {
		Connection conn = DbConnection.getDb();
		List<member> l = new ArrayList();
		String SQL = "select * from member";
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				member m = new member();
				m.setAccount(rs.getString("account"));
				m.setPassword(rs.getString("password"));
				m.setName(rs.getString("name"));
				m.setPhone(rs.getString("phone"));
				m.setEmail(rs.getString("email"));
				m.setAccConsumption(rs.getInt("accConsumption"));
				m.setVipLevel(rs.getInt("vipLevel"));
				l.add(m);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public member QueryAccount(String account) {
		Connection conn = DbConnection.getDb();
		String SQL = "select * from member where account=?";
		member m = null;
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, account);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				m = new member();
				m.setAccount(rs.getString("account"));
				m.setPassword(rs.getString("password"));
				m.setName(rs.getString("name"));
				m.setPhone(rs.getString("phone"));
				m.setEmail(rs.getString("email"));
				m.setAccConsumption(rs.getInt("accConsumption"));
				m.setVipLevel(rs.getInt("vipLevel"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return m;
	}

	@Override
	public List<member> QueryName(String name) {
		Connection conn = DbConnection.getDb();
		String SQL = "select * from member where name=?";
		List<member> l = new ArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				member m = new member(rs.getString("account"), rs.getString("password"), rs.getString("name"),
						rs.getString("phone"), rs.getString("email"));
				l.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return l;
	}

	@Override
	public List<member> QueryPhone(String phone) {
		Connection conn = DbConnection.getDb();
		String SQL = "select * from member where phone=?";
		List<member> l = new ArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, phone);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				member m = new member(rs.getString("account"), rs.getString("password"), rs.getString("name"),
						rs.getString("phone"), rs.getString("email"));
				l.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return l;
	}

	@Override
	public List<member> QueryEmail(String email) {
		Connection conn = DbConnection.getDb();
		String SQL = "select * from member where email=?";
		List<member> l = new ArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				member m = new member(rs.getString("account"), rs.getString("password"), rs.getString("name"),
						rs.getString("phone"), rs.getString("email"));
				l.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return l;
	}

	@Override
	public void update(member m, Integer newAC,Integer newVIP) {
		Connection conn=DbConnection.getDb();
		String SQL="update member set accConsumption=?,vipLevel=? where account=?";
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ps.setInt(1, newAC);
			ps.setInt(2, newVIP);
			ps.setString(3, m.getAccount());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
