package IHM;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import DAO.Requete;

public class Renommer extends JFrame {


	String ancien_pseu; 
	JList list ;
	JTableModel jt_model;
	Connection con ;
	DefaultListModel model;
	private JTextField tf_nvpseudo;

	int indexModifiedTab;
	
	public Renommer(Connection con, DefaultListModel model,String ancien_pseu,JList list,JTableModel jt_model,int index,JTabbedPane jt_main)
	{
		
		             this.con= con;
		             this.ancien_pseu= ancien_pseu;
		             this.list=list;
		             this.jt_model=jt_model;
		             indexModifiedTab=index;
					this.setSize(new Dimension(300,200));
					this.setVisible(true);
					this.setLocationRelativeTo(null);
					
					
					
					JLabel nvpseudo = new JLabel("nouveau pseudo");
					tf_nvpseudo= new JTextField(15);
					JPanel jp_nvpseudo = new JPanel();
					jp_nvpseudo.setPreferredSize(new Dimension(300,25));
					jp_nvpseudo.setLayout(new FlowLayout());
					jp_nvpseudo.add(nvpseudo);
					jp_nvpseudo.add(tf_nvpseudo);
					
					
					
					
					
					JButton Renommer = new JButton("Renommer"); 
					
					this.getContentPane().setLayout(new FlowLayout());
					
					this.getContentPane().add(jp_nvpseudo);
					this.getContentPane().add(Renommer);
					
					Renommer.addActionListener(new ActionListener() 
					{
						
						
						
						
						@Override
						public void actionPerformed(ActionEvent e) 
						{   String nouveauPseudo="";
							nouveauPseudo= tf_nvpseudo.getText();
						   if(!exist(nouveauPseudo))
						   {
						   		int currentIndex =list.getSelectedIndex();
							   	model.set( currentIndex,nouveauPseudo);
							   	if(index!=-100)
							   	{jt_main.setTitleAt(indexModifiedTab,nouveauPseudo);
							   	}
							   	Requete.updateP(con, ancien_pseu, nouveauPseudo);
							   	jt_model.update(nouveauPseudo,currentIndex);
							   	
							   	
						   }	
						   else
						   {
							   JOptionPane message = new JOptionPane();
								 message.showMessageDialog(Renommer.this, "pseudo existe deja ");
							
						   }
						}
						
					});
		 
	}
	
	

	public boolean exist(String pseudo)
	{
		
		boolean exist = false;
		
		ResultSet rs =Requete.selectSelonPseudo(con, pseudo); 
		try 
		{
			if(rs.first())
			 {
			  exist=true;
			 }
			
		} catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		return exist;
		
	}



	
	
	
	
}
