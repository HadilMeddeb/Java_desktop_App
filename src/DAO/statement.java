package DAO;
import java.sql.*;
public class statement {
	
	
	public static Statement creer(Connection con)
	{
		Statement st= null;
		if(con!=null)
		{
			try 
			
			{
				st= con.createStatement();
				return st;
			} 
			
			catch (SQLException e) 
			
			{
				
				e.printStackTrace();
				System.out.println("erreur création statement ..."+e.getMessage());
			}
		}
		else {  System.out.println("pas de connection erreur creation statement ...");}
		return st;
	}

}
