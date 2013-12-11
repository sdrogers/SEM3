import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import java.io.File;

public class loadAction implements ActionListener
{
	private RegisterMachine rm;
	private RegisterViewer rv;
	private viewupdater vu;
	private int NRegisters;
	private JFileChooser fc;
	public loadAction(RegisterMachine rmin,RegisterViewer rvin,viewupdater vuin)
	{
		vu = vuin;
		rm = rmin;	
		rv = rvin;
		NRegisters = rm.getNRegisters();
		fc = new JFileChooser("/Users/simonrogers/Dropbox/Teaching/SEM3/Code/RegisterMachine/MVCVersion/examples/.");
	}
	public void actionPerformed(ActionEvent event)
	{
		//rm.run();
		//updateview();
		int returnval = fc.showOpenDialog(null);
		if(returnval == JFileChooser.APPROVE_OPTION)
		{
			String fullname = ""+fc.getSelectedFile();
			rm.loadProgram(fullname);
			vu.updateview();
			vu.updateprogramview();
		}
	}	
}