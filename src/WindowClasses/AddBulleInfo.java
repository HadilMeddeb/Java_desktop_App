package WindowClasses;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import DAO.Requete;

public class AddBulleInfo extends MouseAdapter {
	private  DefaultListModel model;
	private JList list;
	private Connection con;
	public AddBulleInfo(Connection con,JList list,DefaultListModel model)
	{
		this.list=list;
		this.model=model;
		this.con=con;
		
	}
	 @Override
		public void mouseEntered(MouseEvent e) 
		 {
			 int index= list.locationToIndex(new Point(e.getX(),e.getY()));
			 if(index>=0)
			 {
				 String survolledPseudo =(String)model.getElementAt(index);
				 String nom= Requete.pseudoToName(con,survolledPseudo );
				 String prenom= Requete.pseudoToPrenom(con,survolledPseudo);
				 
				list.setToolTipText(nom+" "+prenom); 
				 
			 }
			 
		 }
	
	
	
	
}
