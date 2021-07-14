package IHM;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import DAO.Requete;

public class Inscription extends JFrame {

	 private JTextField tf_nom;
	private JTextField tf_prenom;
	private JTextField tf_email;
	private JPasswordField tf_password;
	private Connection con= null;

	Inscription(Connection con,Authentification auth)
	 {
		    this.con= con;
			this.setSize(new Dimension(460,250));
			this.setLocationRelativeTo(null);
			JLabel nom = new JLabel("nom");
			tf_nom= new JTextField(15);
			
			
			
			JLabel prenom = new JLabel("prenom");
			 tf_prenom= new JTextField(15);
			
			
			
			JLabel email = new JLabel("email");
			 tf_email= new JTextField(15);

			
			JLabel password = new JLabel("password");
			 tf_password= new JPasswordField(15);
			
	
			JButton valider = new JButton("inscrire"); 
			
			JLabel lab0 = new JLabel("   ");
	    	 JLabel lab1 = new JLabel("   ");
	    	 JLabel lab2 = new JLabel("   ");
	    	 JLabel lab3 = new JLabel("   ");
	    	 JLabel lab4 = new JLabel("   ");
	    	 JLabel lab5 = new JLabel("   ");
	    	 JLabel lab6 = new JLabel("   ");
	    	 JLabel lab7 = new JLabel("   ");
	    	 JLabel lab8 = new JLabel("   ");
	    	 JLabel lab9 = new JLabel("   ");
	    	 JLabel lab10 = new JLabel("   ");
	    	 JLabel lab11 = new JLabel("   ");
	    	 JLabel lab12 = new JLabel("   ");
	    	 JLabel lab13 = new JLabel("   ");
	    	 JLabel lab14= new JLabel("   ");
	    	 JLabel lab15 = new JLabel("   ");
	    	 JLabel lab16 = new JLabel("   ");
	    	 JLabel lab17= new JLabel("   ");
	    	 JLabel lab18 = new JLabel("   ");
	    	
			this.getContentPane().setLayout(new GridLayout(7,4,5,5));
			
		    
			this.getContentPane().add(lab0);
			this.getContentPane().add(lab1);
			this.getContentPane().add(lab2);
			this.getContentPane().add(lab3);
			
			
			this.getContentPane().add(lab4);
			this.getContentPane().add(nom);
			this.getContentPane().add(tf_nom);
			this.getContentPane().add(lab5);
			
			this.getContentPane().add(lab6);
			this.getContentPane().add(prenom);
			this.getContentPane().add(tf_prenom);
			this.getContentPane().add(lab7);
			
			this.getContentPane().add(lab8);
			this.getContentPane().add(email);
			this.getContentPane().add(tf_email);
			this.getContentPane().add(lab9);
			
			this.getContentPane().add(lab10);
			this.getContentPane().add(password);
			this.getContentPane().add(tf_password);
			this.getContentPane().add(lab11);
			
			this.getContentPane().add(lab12);
			this.getContentPane().add(valider);
			this.getContentPane().add(lab13);
			this.getContentPane().add(lab14);
			
			
			this.getContentPane().add(lab15);
			this.getContentPane().add(lab16);
			this.getContentPane().add(lab17);
			this.getContentPane().add(lab18);
			
	    valider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nom=tf_nom.getText();
			    String prenom=tf_prenom.getText();
				String email=tf_email.getText();
				String password=tf_password.getText();
				if(password.equals("")||email.equals("")||nom.equals("")||prenom.equals(""))
				{
					 JOptionPane message = new JOptionPane();
					 message.showMessageDialog(auth, "veuillez  remplir tous les champs !!");
				}
				else if(!emailFormat(email))
				{

					 JOptionPane message = new JOptionPane();
					 message.showMessageDialog(auth, "votre email doit etre de la forme sentence@gmail.com");
				}
				else if(!motDePasseSolide(password))
				{

					 JOptionPane message = new JOptionPane();
					 message.showMessageDialog(auth, "votre mot de passe est faible");
				}
				else if(exist(email,password))
				{
					JOptionPane message = new JOptionPane();
					 message.showMessageDialog(auth, " tu es deja inscrit !!");
				}
				else
				{
					Requete.InsertNouveauAdministrateur(con,nom,prenom,email,password);
					JOptionPane message = new JOptionPane();
					 message.showMessageDialog(auth, "vous etes inscrit !!");
					 Inscription.this.setVisible(false);
				
				}
			}
	    	
	    	
	    	
	    	
	    });
	 
	 
	 }
	
	public boolean exist(String email,String password)
	{
		
		boolean exist =false;
		
		ResultSet rs =Requete.selectSelonPasswordMail(con, email, password); 
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
	
	public boolean emailFormat(String email)
	{
		boolean test = false;
		if(email.length()>=12)
		{
			if(email.endsWith("@gmail.com"))
			{
				test=true;
				return test;
			}
			else
				return test;
		}
		else return test;
	
	}
	public boolean motDePasseSolide( String password)
	{
		boolean test = true;
		if( password.length()<7)
		{
			test=false;
			return test;

		}
		else return test;
	}
	
}
