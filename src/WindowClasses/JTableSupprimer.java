package WindowClasses;
import IHM.JTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.*;

import DAO.Requete;

public class JTableSupprimer extends MouseAdapter {
	
	private JTable jt_etudiants;
	private JTableModel jt_model;
	private DefaultListModel model;
    private Connection con;
    
	
    public JTableSupprimer( JTable jt_etudiants, JTableModel jt_model,Connection con,DefaultListModel model)
    {
    	this.jt_etudiants=jt_etudiants;
    	this.jt_model=jt_model;
    	this.model=model;
    	this.con=con;
    }
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		super.mouseClicked(e);
		if(SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1)
		{
			JPopupMenu jp_supprimer = new JPopupMenu();
			JMenuItem supprimer = new JMenuItem("supprimer");
			jp_supprimer.add(supprimer);
			jp_supprimer.show(jt_etudiants, e.getX(), e.getY());
			
			supprimer.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					
					
					int row=jt_etudiants.getSelectedRow();
					
					if(jt_etudiants.isRowSelected(row)) 
					{
					 
					 jt_model.supprimer(row);
					 String pseudo =(String)jt_model.getValueAt(row, 0);
					 model.remove(row);
					  Requete.delete(con, pseudo);
					}
					
				  	
				}
			});
		}
	}
}
