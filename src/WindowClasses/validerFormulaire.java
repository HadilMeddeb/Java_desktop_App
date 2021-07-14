package WindowClasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DAO.Requete;
import IHM.JTableModel;
import IHM.Window;

public class validerFormulaire implements ActionListener
{
	JTextField tf_nom;
	JTextField tf_prenom;
	JTextField tf_pseudo;
	Window win;
	Connection con;
	JTableModel jt_model;
	DefaultListModel model;
	
	public validerFormulaire(Connection con,Window win,JTextField tf_nom,JTextField tf_prenom,JTextField tf_pseudo,JTableModel jt_model, DefaultListModel model)
	{
		this.tf_nom=tf_nom;
		this.tf_prenom=tf_prenom;
		this.tf_pseudo=tf_pseudo;
		this.win=win;
		this.con=con;
		this.jt_model=jt_model;
		this.model =model;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		String nom=tf_nom.getText();
		String prenom=tf_prenom.getText();
		String pseudo=tf_pseudo.getText();
		
		if((nom.equals("")||nom.equals("Tapez votre nom")) || (prenom.equals("")|| prenom.equals("Tapez votre Prenom")) || (pseudo.equals("")||pseudo.equals("Tapez votre pseudo")))
		{
			 JOptionPane message = new JOptionPane();
			 message.showMessageDialog(win, "veuillez remplir tous les champs ");
		
		}
		else if(!exist(pseudo))
		
		{
			 model.addElement(tf_pseudo.getText());
			 Requete.insert(con,nom, prenom, pseudo);
			 jt_model.updateData(nom, prenom, pseudo);
			 
		}
		else 
		{
			JOptionPane message = new JOptionPane();
			 message.showMessageDialog(win, "cette personne existe deja !!");
		
		}
	}
	
	
	public boolean exist(String pseudo)
	{
		
		boolean exist =false;
		ResultSet rs =Requete.selectSelonPseudo(con, pseudo); 
		try 
		{
			if(rs.first())
			 {
			  exist=true;
			 }
		}
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return exist;
	}
	
	

}
