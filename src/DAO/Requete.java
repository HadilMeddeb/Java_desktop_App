package DAO;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;


public class Requete {
	public static void insert(Connection con,String nom,String prenom ,String pseudo)
	{
		String requete = "insert into etudiant (nom,prenom,pseudo) VALUES(?,?,?)";
	    try {
			PreparedStatement ps = con.prepareStatement(requete);
	
			ps.setString(1,nom);
			ps.setString(2,prenom);
			ps.setString(3,pseudo);
			ps.execute();
			
			System.out.println("inserted ..");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur insertion ...");
		}
	    
	
	}
	
	
	public static void delete(Connection con , String pseudo)
	{
	     String requete="delete from etudiant where pseudo=?;";
	     try 
	     {
			PreparedStatement ps= con.prepareStatement(requete);
			ps.setString(1,pseudo);
			ps.execute();
			System.out.println("supprimé ..");
		 }
	     catch (SQLException e) 
	     {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("erreur supression ...");
		 }
	     
	}
	
	public static ResultSet selectAll(Statement st)
	{
		ResultSet rs=null;
		try {
			rs=st.executeQuery("select * from etudiant ;");
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("erreur selection");
		}
		return rs;	
	}
	
	
	public static void updateP(Connection con,String Nvpseudo,String pseudo)
	{
		String requete ="update etudiant set pseudo =? where pseudo=?;";
	    try {
	    	PreparedStatement ps = con.prepareStatement(requete);
	    	ps.setString(1,Nvpseudo);
	    	ps.setString(2,pseudo);
		
			ps.executeUpdate();
	        System.out.println("updated ..");
		    }
	    catch (SQLException e) 
	        {
			e.printStackTrace();
		    }
	  
	  
	}
	public static void updateNPseu(Connection con,String nom,String pseudo)
	{
		String requete ="update etudiant set nom =? where pseudo=?;";
	    try {
	    	PreparedStatement ps = con.prepareStatement(requete);
			ps.setString(1,nom);
			ps.setString(2,pseudo);
			ps.executeUpdate();
			System.out.println("ubdated ...");
		    }
	    catch (SQLException e) 
	        {
			e.printStackTrace();
		    }
	}
	public static void updatePrPseu(Connection con,String prenom,String pseudo)
	{
		String requete ="update etudiant set prenom =? where pseudo=?;";
	    try {
	    	PreparedStatement ps = con.prepareStatement(requete);
			ps.setString(1,prenom);
			ps.setString(2,pseudo);
			ps.executeUpdate();
			System.out.println("ubdated ...");
		    }
	    catch (SQLException e) 
	        {
			e.printStackTrace();
		    }
	}
	

	public static String pseudoToName(Connection con,String pseudo)
	{
		String requete=" select nom from etudiant where pseudo=?";
		ArrayList<Object> data= new	ArrayList<Object>();
	    String  nom="";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(requete);
			ps.setString(1, pseudo);
			ResultSet rs=ps.executeQuery();
			
			ResultSetMetaData rsmd= rs.getMetaData();
			while(rs.next())
			{
				for(int i=0;i<rsmd.getColumnCount();i++)
				{
					data.add(rs.getObject(1));
				}
				
			}
			nom=(String)data.get(0);
		    return nom;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nom;
	
		
	}
	
	
	
	
	public static String pseudoToPrenom(Connection con,String pseudo)
	{
		String requete=" select prenom from etudiant where pseudo=?";
		ArrayList<Object> data= new	ArrayList<Object>();
		String  prenom="";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(requete);
			ps.setString(1, pseudo);
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData rsmd= rs.getMetaData();
			
			while(rs.next())
			{
				for(int i=0;i<rsmd.getColumnCount();i++)
				{
					data.add(rs.getObject(1));
				}
				
			}
			prenom=(String)data.get(0);
		    return prenom;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prenom;
	
		
	}
	
	public static ResultSet selectSelonPasswordMail(Connection con ,String email, String password)
	{
		ResultSet rs=null;
		String requete= "select *  from administrator where email= ? and password= ? ";
		try {
			PreparedStatement ps = con.prepareStatement(requete);
		    ps.setString(1,email);
		    ps.setString(2,password);
		    rs=ps.executeQuery();
		    
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return rs;
		
	}
	
	public static ResultSet selectSelonPseudo(Connection con ,String pseudo)
	{
		ResultSet rs=null;
		String requete= "select *  from etudiant where pseudo= ? ";
		try {
			PreparedStatement ps = con.prepareStatement(requete);
		    ps.setString(1,pseudo);
		    rs=ps.executeQuery();
		    
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return rs;
		
	}
	public static ResultSet selectPseudo(Connection con )
	{
		ResultSet rs =null;
		String requete="select pseudo from etudiant;";
		
			   
				try {
					 PreparedStatement ps = con.prepareStatement(requete);
					 rs = ps.executeQuery(); 

					 return rs;
				} catch (SQLException e) {
					
					e.printStackTrace();
					System.out.println("error :"+e.getMessage());
				}
			
	
		return rs;
	}
	
	public static void deleteAll(Connection con,Statement st)
	{
	            String requete = "TRUNCATE TABLE etudiant;";
	    		try {
	    			PreparedStatement ps =con.prepareStatement(requete);
					ps.executeUpdate();
				} catch (SQLException e) 
	    		{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public static void InsertNouveauAdministrateur(Connection con,String nom,String prenom ,String email, String password)
	{
		String requete = "insert into administrator (nom,prenom,email,password) VALUES(?,?,?,?)";
	    try {
			PreparedStatement ps = con.prepareStatement(requete);
	
			ps.setString(1,nom);
			ps.setString(2,prenom);
			ps.setString(3,email);
			ps.setString(4,password);
			ps.execute();
			
			System.out.println("inserted ..");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur insertion ...");
		}
	}
	
	
	
	
	
}
