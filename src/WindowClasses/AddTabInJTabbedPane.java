package WindowClasses;

import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.awt.event.MouseAdapter;
import javax.swing.JList;
import javax.swing.JTabbedPane;

import DAO.Requete;
import IHM.Paneau_etudiant;

public class AddTabInJTabbedPane extends MouseAdapter 
{

	Connection con;
	JTabbedPane jt_main;
	
	public AddTabInJTabbedPane(Connection con, JTabbedPane jt_main)
	{
		this.con=con;
		this.jt_main=jt_main;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		
		if (e.getClickCount()>=2)
		{
			String pseudo = (String)((JList)e.getSource()).getSelectedValue();
			String nom=Requete.pseudoToName(con, pseudo);
			String prenom=Requete.pseudoToPrenom(con, pseudo);
			
			
			
			
			if(jt_main.getTabCount()==0)
			{
				jt_main.addTab((String)((JList)e.getSource()).getSelectedValue(), new Paneau_etudiant(nom,prenom,pseudo));
			}
			else 
			{
			  int index =existTab(pseudo);
			  if(index ==-100)
			  {
				  jt_main.addTab((String)((JList)e.getSource()).getSelectedValue(), new Paneau_etudiant(nom,prenom,pseudo));
				  int indice =existTab((String)((JList)e.getSource()).getSelectedValue());
				  jt_main.setSelectedIndex(indice);	
			  }
			  else
			  {
				  jt_main.setSelectedIndex(index);	
			  }
			 
			}
			
		
		}
	}
	
	
	 public int existTab(String pseudo)
		{
			int a=0;
			
					while(a<jt_main.getTabCount())
					{
						if(jt_main.getTitleAt(a).equals(pseudo))
						{
							return a;
						}
						else a++;
					}
						return -100;	
		}
}
