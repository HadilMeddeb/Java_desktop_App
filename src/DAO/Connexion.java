package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	
	public static Connection connecter(String url,String user , String pw)
	{   
		Connection con =null;
		try {
			con=DriverManager.getConnection(url,user,pw);
			System.out.println("connecté ...");
		} catch (SQLException e) {
		
			e.printStackTrace();
			System.out.println("erreur connection ..."+url+e.getMessage());
		}
		return con;
	}
	

}
