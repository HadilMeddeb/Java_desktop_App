package IHM;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.Border;

import IHM.Window;
import DAO.*;
public class Authentification extends JFrame {
	private JPasswordField jf_Password;
	private JButton connecter;
	private JTextField jf_email;
	private Connection con;
	private JButton inscrire;

	public Authentification() 
	{
		
	
		this.setTitle("Welcome");
		
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    	this.setSize(new Dimension(800,700));
    	
      	this.setResizable(true);
      	this.setLocationRelativeTo(null);
    	
    
    	JLabel welcome = new JLabel("Welcome",JLabel.CENTER);
    	welcome.setFont(new Font("verdana",30,30));
    	
    	welcome.setPreferredSize(new Dimension(800,50));
    	
    	
    	String url="C:/users/user/desktop/student.png";
    	ImageIcon image = new ImageIcon(url);
 
    	JLabel  jl_image = new JLabel(image,JLabel.CENTER);
    	jl_image.setSize(new Dimension(300,300));
    	
    	
    	
    	 JPanel container = new JPanel();
    	 container.setLayout(new GridLayout(5,4,5,5));
    	 container.setBackground(Color.WHITE);
    	 Border  blackLine = BorderFactory.createLineBorder(Color.BLACK);
    	 container.setBorder(blackLine);
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
    	 
  		
    	

    	
    	 JLabel password = new JLabel("password");
    	 jf_Password = new JPasswordField(15) ;

    	 
    	 JLabel email = new JLabel("email");
    	 jf_email = new JTextField(15);

    	 connecter = new JButton("connecter");
    	 inscrire = new JButton("inscrire");

    	 container.add(lab0);
    	 container.add(lab1);
    	 
    	 container.add(lab2);
    	 container.add(lab3);
    	 
    	 container.add(lab4);
    	 container.add(password);
    	 container.add(jf_Password);
    	 container.add(lab5);
    	 
    	 container.add(lab6);
    	 container.add(email);
    	 container.add(jf_email);
    	 container.add(lab7);
    	 
    	 container.add(lab8);
    	 container.add(connecter);
    	 container.add(inscrire);
    	 container.add(lab9);
    	 
    	 container.add(lab10);
    	 container.add(lab11);
    	 container.add(lab12);
    	 container.add(lab13);

    	
    	 JPanel paneau = new JPanel();
    	 paneau.setSize(new Dimension(800,700));
    	 paneau.setBackground(Color.white);
    	 
    	 paneau.add(welcome);
    	 paneau.add(jl_image);
    	 paneau.add(container);
    	 
    	 this.add(paneau);
    	 
    	 Driver.charger();
         con =Connexion.connecter("jdbc:mysql://127.0.0.1/etudiants", "root", "");
    	  
    	 
    	 /*************************************events*******************************************/
         connecter.addActionListener(new Connecter());
         inscrire.addActionListener(new Sinscire());

         
         this.setVisible(true);  
	}

	public JPasswordField getJf_Password() {
		return jf_Password;
	}

	public void setJf_Password(JPasswordField jf_Password) {
		this.jf_Password = jf_Password;
	}

	public JButton getValider() {
		return connecter;
	}

	public void setValider(JButton valider) {
		this.connecter = valider;
	}

	public JTextField getJf_email() {
		return jf_email;
	}

	public void setJf_email(JTextField jf_email) {
		this.jf_email = jf_email;
	}

	
	private class Connecter implements ActionListener
	{
		private String password;
		private String email;
		Connecter()
		{		}
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			password =jf_Password.getText();
			email =jf_email.getText();

				if(password.equals("")||email.equals(""))
				{
					
				 JOptionPane message = new JOptionPane();
				 message.showMessageDialog(Authentification.this, "veuillez  remplir tous les champs !!");
				}
			else if(!exist(email,password))
			   {
				
				JOptionPane message = new JOptionPane();
				 message.showMessageDialog(Authentification.this, " données fausses vous n'etes pas inscrit veuillez vous inscrire !!");
			}
			else
			{
				Window w = new Window(con);
				w.setVisible(true);
				Authentification.this.setVisible(false);
				
			}

		}

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
	
	class Sinscire implements ActionListener
	{
		public Sinscire()
		{}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Inscription inscrit = new Inscription(con,Authentification.this);
			inscrit.setVisible(true);	
		}
		
	}
	
	

}
