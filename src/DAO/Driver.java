package DAO;

import IHM.Authentification;
import IHM.Renommer;

public class Driver 
	
	
	public static void charger()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("chargé ...");
			
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			System.out.println("erreur chargement ...");
		}
		
		
	}
	
	
	

}
