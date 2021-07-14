package WindowClasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.*;

import DAO.Requete;
import IHM.JTableModel;
import IHM.Renommer;

public class JPopupAfficheOnList extends MouseAdapter {

	
	
	
	
	private JMenuItem jmi_supprimer;
	private JMenuItem jmi_renommer;
	private JMenuItem jmi_supprimerTous;
	private JTableModel jt_model;
	private JTabbedPane jt_main;
	private  DefaultListModel model;
	private JList list;
	private Connection con;
	private Statement st;
	

	public JPopupAfficheOnList(Connection con,JTableModel jt_model,JTabbedPane jt_main,DefaultListModel model,JList list) 
	{
		super();
		this.jt_model = jt_model;
		this.con=con;
		this.jt_main=jt_main;
		this.model=model;
		this.list=list;
	}


	@Override
	public void mouseClicked(MouseEvent e) 
	{
	  if(SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1)
	    {		    
		jmi_supprimer = new  JMenuItem("supprimer");
		jmi_renommer = new  JMenuItem("renommer");
		jmi_supprimerTous = new  JMenuItem("supprimer Tous");
		
		JPopupMenu jpp_Menu = new JPopupMenu();
		jpp_Menu.setLocation(e.getX(), e.getY());
		jpp_Menu.add(jmi_supprimer);
		jpp_Menu.add(jmi_supprimerTous);
		jpp_Menu.add(jmi_renommer);
		jpp_Menu.show(list,e.getX(),e.getY());
		
		/**************************supprimer item*******************/
		jmi_supprimer.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!list.isSelectionEmpty())
				{
					String selected_pseu=(String) list.getSelectedValue();
					int selection_index = list.getSelectedIndex();
					System.out.println(selection_index);
					model.remove(list.getSelectedIndex());
					int index=existTab(selected_pseu);
				
			 if(jt_model != null)
				{
					jt_model.remove(selection_index);
					if(index!=-100)
					{
						jt_main.remove(index);
					}
					jt_model.fireTableDataChanged();
					Requete.delete(con, selected_pseu);
				}
				else
				{
					System.out.println("jt_model est null");
				}


				}
			}
		});
		
		/**************************renommer item*******************/
		jmi_renommer.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				String pseudo=(String)list.getSelectedValue();
				
				int index=existTab(pseudo);
				
					Renommer renommer = new Renommer(con,model,pseudo,list,jt_model,index,jt_main);
			
			
				
			}
			
		});
		
		/**************************supprimmer  tout*************************/
		
		jmi_supprimerTous.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				model.removeAllElements();
				Requete.deleteAll(con, st);
				jt_main.removeAll();
				jt_model.removeAll();
			}
		});
		
	     }	
	  
	 
     }
	
	
	/**************************supprimmer  tout*************************/
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
