package IHM;

import java.awt.*;

import javax.swing.JFrame;

public class Test_paneau extends JFrame 
{ 
	public Test_paneau()
	{
		 Paneau_etudiant paneau = new  Paneau_etudiant("Meddeb","Hadil","");
	     this.setContentPane(paneau);
	     this.setSize(new Dimension(800,700));
	}
	public static void main(String[] args) {
		Test_paneau test = new Test_paneau();
		test.setVisible(true);
	}
}
