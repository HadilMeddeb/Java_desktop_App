package WindowClasses;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class EventsOnValiderForm extends MouseAdapter {
	private String chaine;
	private JLabel help;

	public EventsOnValiderForm(String chaine,JLabel help)
	  {
		this.chaine=chaine;
		this.help=help;
	  }

	@Override
	public void mouseEntered(MouseEvent e)
	{
		
		if(e.getSource().getClass()==(new JTextField()).getClass() )
		 {
		    help.setText("   vous devez taper votre " +chaine+" ici");
		    help.setHorizontalAlignment(JLabel.CENTER);
		 }
		else 
			if(e.getSource().getClass()==(new JLabel()).getClass() )
		     {
			    ((JLabel) e.getSource()).setForeground(Color.red);
		     }	
	 }

	@Override
	public void mouseExited(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getSource().getClass()==(new JTextField()).getClass() )
		   {
		     help.setText("help:");
		     help.setHorizontalAlignment(JLabel.LEFT);
		   }
		else
			if (e.getSource().getClass()==(new JLabel()).getClass() )
		    {((JLabel) e.getSource()).setForeground(Color.black);}
	}

}
