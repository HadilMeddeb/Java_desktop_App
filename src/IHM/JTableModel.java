package IHM;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import DAO.Requete;
import DAO.resultset;
import DAO.Requete;
public class JTableModel  extends AbstractTableModel
{

	
	
	private ArrayList<String[]> data=new ArrayList<String[]>();
	private int nbcol;
	private int nblignes;
	private ResultSetMetaData rsmd=null;
	private Connection con;
	

	
	public JTableModel(Connection con,Statement st) 
	{
		 this.con=con;
		 ResultSet rs = null;
	    	rs =Requete.selectAll(st);
	        
	    	try 
	    	{
	    		rsmd = rs.getMetaData();
	    		nbcol=rsmd.getColumnCount();
	    		
	    	    while(rs.next())
	    	    { 
	    	    	String[] ligne = new String[nbcol];
	    	    	for(int i=0;i<nbcol;i++)
	    	    	{
	    	    		ligne[i]=(String)rs.getObject(i+1);
	    	    	}
	    	    	data.add(ligne);
	    	    	nblignes++;
	    	    }
	   
	    	}
	    	catch (SQLException e1) 
	    	{e1.printStackTrace();}	
	}
	
	
	public ArrayList<String[]> getData() 
	{
		return data;
	}


	
	public void setData(ArrayList<String[]> data) 
	{
		this.data = data;
	}


	
	public void updateData(String nom, String prenom,String pseudo)
	{	
		ArrayList<String[]> data1 = new ArrayList<String[]>();
				
			for(int i=0;i<data.size();i++)
			{
				data1.add(data.get(i));
			}
		String[] ligne= {nom,prenom,pseudo};
		data1.add(ligne);
		
		data=data1;
		nblignes=data1.size();
		this.fireTableDataChanged();
	}
	
	
	
	@Override
	public String getColumnName(int c) 
	{
		// TODO Auto-generated method stub
		String nomcol="";
		try 
		{
			nomcol = rsmd.getColumnName(c+1);
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		return nomcol;
	}
	

	
	@Override
	public int getColumnCount() 
	{
			return nbcol;
	}

	
	@Override
	public int getRowCount() 
	{
		return data.size();
	}
	

	@Override
	public Object getValueAt(int l, int c) 
	{
		
		return data.get(l)[c];
	}
	
	
	
	public  int NameColToNum(String col)
	{
		
		for(int i=1; i<getColumnCount();i++)
		{
			try 
			{
				if(rsmd.getColumnName(i)==col)
				{
					return i;
				}
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 1;
	}
	
	
	
	@Override
	public void setValueAt(Object val, int l, int c) 
	{
		data.get(l)[c]=(String)val;
		String  pseudo= data.get(l)[1];
		switch(getColumnName(c))
		{
		   case "nom":
			   Requete.updateNPseu(con, pseudo, (String)val);
			   break;
		   case "prenom":
			   Requete.updatePrPseu(con, pseudo, (String) val);
		       break;
		   case "pseudo":
			   Requete.updateP(con, pseudo,(String) val);
			   break;
		}
	}
	
	
	
	
	@Override
	public boolean isCellEditable(int l, int c) 
	{
		return  true;
	}


	public void supprimer(int l) 
	{

		String pseudo = data.get(l)[NameColToNum("pseudo")];
		data.remove(l);
		Requete.delete(con, pseudo);
		this.fireTableDataChanged();
		
	}

	
	
	public void remove(int l) 
	{
		// TODO Auto-generated method stub
		data.remove(l);
		this.fireTableDataChanged();
	}
	
	
	
	
	public void update (String nv_pseudo,int row)
	{
		String[] tab = {nv_pseudo,data.get(row)[1],data.get(row)[2]};
		data.set(row,tab);
		this.fireTableDataChanged();
	}
		
	
	
	public void removeAll()
	{
		for(int i=0;i<data.size();i++)
		{   
			data.remove(i);			
		}
		this.fireTableDataChanged();
	}
	
	
		
	}


