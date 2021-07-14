package IHM;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.Border;

public class Paneau_etudiant extends JPanel
{
	
	private String nom;
	private String prenom;
	private String pseudo;
	private JButton valider;
	private JComboBox jc_niveau;
	private JCheckBox check2;
	private JCheckBox check3;
	private JCheckBox check4;
	private JCheckBox check5;
	private JCheckBox check6;
	private JCheckBox check7;
	private JCheckBox check1;
	private JCheckBox emettre;
	private JCheckBox score;
	private JCheckBox plein;
	private JCheckBox ombre;
	

	public Paneau_etudiant(String  nom ,String  prenom,String pseudo )
	{   this.pseudo= pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.setLayout(new FlowLayout());
		
    	/*******************************top part*************************************/
         JPanel jp_top = new JPanel();
         jp_top.setLayout(new BorderLayout());
         jp_top.setPreferredSize(new Dimension( 800,60));
		 this.add(jp_top);
		
		JLabel lb_top = new JLabel("Bienvenu "+this.nom+" "+this.prenom);
		lb_top.setOpaque(true);
		lb_top.setHorizontalAlignment(JLabel.CENTER);
	    lb_top.setBackground(Color.green);
	    lb_top.setFont(new Font("Arial",Font.BOLD,30));
	    jp_top.add(lb_top);
	    
	    
    	/*******************************main part*************************************/
	    JPanel jp_main = new JPanel();
        jp_main.setLayout(new FlowLayout());
        jp_main.setPreferredSize(new Dimension( 800,500));
		 this.add(jp_main); 
		 
	    	/***************selection***************/

		 Border br_selection =  BorderFactory.createTitledBorder("selection");
		 JPanel jp_selection = new JPanel();
		 jp_selection.setBorder(br_selection);
		 jp_selection.setLayout(new FlowLayout());
		 jp_selection.setPreferredSize(new Dimension( 500,100));
		 
		 jc_niveau = new JComboBox();
		 jc_niveau.setPreferredSize(new Dimension(180,30));
		 jc_niveau.addItem("intermediaire");
		 jc_niveau.addItem("facile");
		 jc_niveau.addItem("difficile");
		 
		 
		 
		 
		 JPanel jp_check = new JPanel();
		 jp_check.setLayout(new FlowLayout());
		 JLabel lb_choix = new JLabel("choisir la/les catégories");
		 
		 check1 = new JCheckBox("1");
		 check1.setSelected(true);
		 check1.setEnabled(false);
		 
		 check2 = new JCheckBox("2");
		 check2.setSelected(true);
		 check2.setEnabled(false);
		 
		 check3 = new JCheckBox("3");
		 check3.setSelected(false);
		 
		 check4 = new JCheckBox("4");
		 check4.setSelected(false); 
		 
		 check5 = new JCheckBox("5");
		 check5.setSelected(false); 
		 
		 check6 = new JCheckBox("6");
		 check6.setSelected(false);
		 
		 check7 = new JCheckBox("7");
		 check7.setSelected(false);
		 
		 jp_check.add(lb_choix);
		 jp_check.add(check1);	  
		 jp_check.add(check2);
		 jp_check.add(check3);
		 jp_check.add(check4);
		 jp_check.add(check6);
		 jp_check.add(check7);
	
		  
		  
		 
		 
		 
		 
		 jp_selection.add(jc_niveau);
		 jp_selection.add(jp_check);
		 
		
		 
	    	/***************option***************/

		 JPanel jp_option = new JPanel();
		 Border br_option =  BorderFactory.createTitledBorder("option :2");
		 jp_option.setBorder(br_option);
		 jp_option.setPreferredSize(new Dimension( 500,80));
		 jp_option.setLayout(new FlowLayout());
		 
		 
		 emettre = new JCheckBox();
		 emettre.setText("Emettre son");
		  
		 score = new JCheckBox();
		 score.setText("Afficher Score");
		  
		 plein = new JCheckBox();
		 plein.setText("plein ecran ");
	
		 
		 
		 ombre = new JCheckBox();
		 ombre.setText("ombre");
		 
		 jp_option.add(emettre);
		 jp_option.add(score);
		 jp_option.add(plein);
		 jp_option.add(ombre);
	     
	    
	    
	    
		 
		 jp_main.add(jp_selection);
		 jp_main.add(jp_option);
    	/*******************************bottom part*************************************/
	    JPanel jp_button = new JPanel();
        jp_button.setLayout(new FlowLayout());
        jp_button.setPreferredSize(new Dimension( 800,60));
		 this.add(jp_button);  
	    
	     valider = new JButton("valider");
	     valider.setFont(new Font("Regular",Font.CENTER_BASELINE,15));
         jp_button.add(valider);
         jp_main.add(jp_button);
         
         /*******************************events part*************************************/
	          
         
          valider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
				
				
				String nomfichier =nom+" "+prenom+".html";
				File htmlFile = new File("C:/users/user/desktop/etudiant/"+nomfichier);
				if(!htmlFile.exists())
				{
					try {
						htmlFile.createNewFile();
						System.out.println("file created ...");
						ecrireDansFile(htmlFile);
						
						System.out.println("not working");
						if(!Desktop.isDesktopSupported())
						{
				            System.out.println("Desktop n'est pas prise en charge");
				            return;
				        }
				        
						
				        Desktop d = Desktop.getDesktop();
				        if(htmlFile.exists()) 
				            d.open(htmlFile);
						
						
					    }
					catch (IOException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(htmlFile.exists())
				{
					try{ecrireDansFile(htmlFile);}
					catch(Exception e){System.out.println("error ecriture"+e.getMessage());}
					
				}
				
			}
        	  
        	  
          });  
	}
	
	/****************************************************events part***************************************************/
     
	public void ecrireDansFile(File htmlFile)
     {
	
		String diff= jc_niveau.getSelectedItem().toString();
		ArrayList<String> categories= new ArrayList<String>();
		int i=0;
		if(check1.isSelected())
			{categories.add("catégorie 1");
		    i++;
		    }
		if(check2.isSelected())
		    {categories.add("catégorie 2");
	         i++;
	        }
		if(check3.isSelected())
		    {categories.add("catégorie 3");
	         i++;
	        }
		if(check4.isSelected())
		    {categories.add("catégorie 4");
	         i++;
	        }
		if(check5.isSelected())
		    {categories.add("catégorie 5");
	         i++;
	        }
		if(check6.isSelected())
		    {categories.add("catégorie 6");
	         i++;
	        }
		if(check7.isSelected())
		    {categories.add("catégorie 7");
	         i++;
	        }

		String difficulties ="";
		for(String ch:categories)
		{
			difficulties+="<div>"+ch+"</div>";
		}
				
		String[] htmlLines= {"<html>","<head>","<Title>Etudiant</Title","</head>",
				"<body>",
				"<form>",
				   "<fieldset>",
				          "<legend>Information personelles</legend>",
				          "<div> Nom :", nom,"</div>",
				          "<div> Prenom :",prenom,"</div>",
				          "<div> pseudo :", pseudo,"</div>",
				   "</fieldset>",
				   "<fieldset>",
				            "<legend>Configuration</legend>",
				            "<fieldset>",
			                     "<legend>difficulté:",diff ,"</legend>",
			                     difficulties,
			                 "</fieldset>",
			                 "<fieldset>",
			                      "<legend>Options :</legend>",
			                      "<div> Son : ",""+emettre.isSelected(),"</div>",
			                      "<div> Affichage score : ",""+score.isSelected(),"</div>",
			                      "<div> plein ecran : ",""+plein.isSelected(),"</div>",
			                      "<div> Ombre: ",""+ombre.isSelected(),"</div>",
			                 "</fieldset>",
				   "</fieldset/>",
				   
				"</form>",
				
				"</body>",
				"</html>"};
		try {
		FileWriter writer= new FileWriter(htmlFile);
		/*memoire tompon***/
		BufferedWriter bw =new BufferedWriter(writer);
		for(String line:htmlLines)
		{
			
				bw.write(line);
				bw.newLine();
		}
		
				bw.close();
				writer.close();
		  }
		catch (IOException e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
		
    	 
     }

