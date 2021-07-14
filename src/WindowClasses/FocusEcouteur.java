package WindowClasses;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

public class FocusEcouteur extends FocusAdapter {
	private String chaine;

	public FocusEcouteur(String chaine)
	{
		this.chaine=chaine;
	}
     
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		if(((JTextField) e.getSource()).getText().equals(chaine))
		{
			((JTextField) e.getSource()).setText("");
		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
		if(((JTextField) e.getSource()).getText().equals(""))
		{
			((JTextField) e.getSource()).setText(chaine);
		}
	}
	
}
