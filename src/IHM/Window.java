package IHM;
import DAO.*;
import IHM.Window;
import WindowClasses.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Window extends JFrame 
{
    private JTextField tf_prenom,tf_pseudo;
	private JTextField tf_nom;
	private JButton jb_valider;
	private JLabel help;
	private  DefaultListModel model;
	private JList list;
    private int nb_inscription=0;
   
	private JTabbedPane jt_main;
	private JButton Jb_afficherTous;
	private Connection con;
	private JTable jt_table;
	private JTableModel jt_model;
	private Statement st;
	private JFrame jf_jtable;
	private JTable jt_etudiants;
	


	public Window(Connection con)
    {
    	this.setTitle("fenetre tp4");
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	this.setLayout(new BorderLayout());
    	this.setSize(new Dimension(800,700));   
    	this.setLocationRelativeTo(null);
    	this.con=con;
		st = statement.creer(con);
    	
    	/*******************************formulaire*************************************/

		
		
		
    	 JPanel jp_form= new JPanel();
    	 
    	 JLabel lb_nom= new JLabel("Nom");
    	 tf_nom = new JTextField("Tapez votre nom",15);
    	 tf_nom.setHorizontalAlignment(JTextField.CENTER);
    	 
    	 JLabel etoile1 =new JLabel("*");
    	 etoile1.setForeground(Color.red);
    	 
    	 JLabel lb_prenom= new JLabel("Prenom");
    	 tf_prenom= new JTextField("Tapez votre Prenom",15);
    	 tf_prenom.setHorizontalAlignment(JTextField.CENTER);
    	
    	 JLabel etoile2 =new JLabel("*");
    	 etoile2.setForeground(Color.red);
    	 
    	 JLabel lb_pseudo= new JLabel("Pseudo");
    	 tf_pseudo= new JTextField("Tapez votre pseudo",15);
    	 tf_pseudo.setHorizontalAlignment(JTextField.CENTER);
    	 
    	 JLabel etoile3 =new JLabel("*");
    	 etoile3.setForeground(Color.red);
    	 
    	 jb_valider= new JButton("valider");
    	 jp_form.setLayout( new FlowLayout());
    	 
    
    	 
    	 jp_form.add(lb_nom);
    	 jp_form.add(tf_nom);
    	 jp_form.add(etoile1);
    	 
    	 jp_form.add(lb_prenom);
    	 jp_form.add(tf_prenom);
    	 jp_form.add(etoile2);
    	 
    	 jp_form.add(lb_pseudo);
    	 jp_form.add(tf_pseudo);
    	 jp_form.add(etoile3);
    	 
    	 jp_form.add(jb_valider);
    	 this.add(jp_form,BorderLayout.NORTH);
    	 
    	 /*************************************help*****************************************/
    	 
    	 help =new JLabel("help :");
    	 this.add(help,BorderLayout.SOUTH);
    	 
    	 /*************************************main content jTabbedPane*****************************************/
         
    	 /***************************************right side*********************************/
    	 jt_main = new JTabbedPane();
    	 jt_main.setBackground(new Color(83,83,83));
    	 jt_main.setForeground(new Color(255,255,255));
    	 
    	 
    	 model= new DefaultListModel<String>();
    	 RemplirJListModel();
     	
    	
    	 list = new JList(model);
    	 list.setPreferredSize(getMinimumSize());
    	
    	 
    	 /******************************************left side**********************************/
    	 
    	 JScrollPane js_scroll1 = new JScrollPane(list);
    	 JPanel container = new JPanel();
    	 Jb_afficherTous = new JButton("afficher tous");
    	 container.setLayout(new BorderLayout());
    	 container.add(Jb_afficherTous,BorderLayout.SOUTH);
    	 container.add(js_scroll1,BorderLayout.CENTER);
    
     	 JSplitPane jsp = new JSplitPane();
     	 jsp.setRightComponent(jt_main);
     	 jsp.setLeftComponent(container);
     	 this.add(jsp,BorderLayout.CENTER);
     	 
     	/***************************************events*****************************************/
    
		tf_nom.addFocusListener(new FocusEcouteur("Tapez votre nom"));
    	tf_prenom.addFocusListener(new FocusEcouteur("Tapez votre Prenom"));
    	tf_pseudo.addFocusListener(new FocusEcouteur("Tapez votre pseudo"));
       
    
    	tf_nom.addMouseListener(new EventsOnValiderForm("nom",help));
    	tf_prenom.addMouseListener(new EventsOnValiderForm("prenom",help));
    	tf_pseudo.addMouseListener(new EventsOnValiderForm("pseudo",help));
    	
    
    	lb_nom.addMouseListener(new EventsOnValiderForm("nom",help));
    	lb_prenom.addMouseListener(new EventsOnValiderForm("prenom",help));
    	lb_pseudo.addMouseListener(new EventsOnValiderForm("pseudo",help));
    	
  
    
    	
    		
    	jt_model = new  JTableModel(con,st);
		jt_etudiants= new JTable(jt_model);
		
		
		JScrollPane js_scroll =new JScrollPane(jt_etudiants);
		jf_jtable = new JFrame();
		jf_jtable.setSize(new Dimension(800,700));
		jf_jtable.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		jf_jtable.add(js_scroll);
		jf_jtable.setVisible(false);
		
		
    	/************************************events*******************************************/
        
    	jb_valider.addActionListener(new validerFormulaire(con,Window.this,tf_nom,tf_prenom,tf_pseudo,jt_model,model) );
    	
    	list.addMouseListener(new AddTabInJTabbedPane(con,jt_main));
    	
    	list.addMouseListener(new AddBulleInfo( con,list,model));
    	
    	 jt_etudiants.addMouseListener(new JTableSupprimer( jt_etudiants,jt_model,con,model));
     	
    	list.addMouseListener(new JPopupAfficheOnList(con,jt_model,jt_main, model,list));
        
     	Jb_afficherTous.addActionListener(new ActionListener() 
        {
        	@Override
    		public void actionPerformed(ActionEvent e) 
    		{
    			jf_jtable.setVisible(true);
    		}
        });
     	

    }
	

	public void RemplirJListModel()
	{
		 ResultSet rs= Requete.selectPseudo(con);
		    
	    	try {
				while(rs.next())
				 {
					 model.addElement(rs.getString(1));
				 }
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}


}



