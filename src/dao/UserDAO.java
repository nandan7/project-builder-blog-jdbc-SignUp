package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;
import utility.ConnectionManager;

public class UserDAO implements UserDaoInterface {
		
	public int signUp(User user)  {
		
		 try {
			Connection con = ConnectionManager.getConnection();
		    String sql = "insert into users(email, password)values(?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,user.getEmail());
			st.setString(2,user.getPassword());	
			int result = st.executeUpdate();
			return result;	
		} 
		 catch (Exception e) {
			 e.printStackTrace();
		}
		return 0;
	}

	public boolean loginUser(User user)  {
		try {
			Connection con = ConnectionManager.getConnection();
			boolean result = false;		     
			String sql="select * from users where email = ? and password = ? ";
			PreparedStatement st = con.prepareStatement(sql);	
			String email=user.getEmail();
			String password=user.getPassword();
			st.setString(1, email);
			st.setString(2, password);
			System.out.println(st);
			ResultSet rs = st.executeQuery();
			result = rs.next();
			return result;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}