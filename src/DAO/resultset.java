package DAO;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class resultset {
	
	public static void afficher(ResultSet rs)
	{
		try {
			
			ResultSetMetaData rsmd =rs.getMetaData();
			int nbcol= rsmd.getColumnCount();
			String [] entete =new String[nbcol];
			
			
			for(int i=0 ; i<nbcol ; i++)
			{
				System.out.print("  "+rsmd.getColumnName(i+1)+"  |");
				
			}
			System.out.println("");
			
			while(rs.next()) 
			{
				for(int i=0 ; i<nbcol ; i++)
				{
					System.out.print("  "+rs.getObject(i+1)+"  ");
				}	
				System.out.println("");
			}
			
		    } 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
	}

}
