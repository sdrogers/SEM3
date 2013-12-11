public class viewupdater
{
	private RegisterMachine rm;
	private RegisterViewer rv;	
	public viewupdater(RegisterMachine rmin,RegisterViewer rvin)
	{
		rm = rmin;
		rv = rvin;	
	}
	
	public void updateview()
	{
		rv.updateviewdata(rm.getAllRegisters(),rm.getCurrentInstructionString(),rm.getMessage());
		rv.setSelectedProgramLine(rm.getCurrent());
	}
	public void updateprogramview()
	{
		int nLines = rm.getnLines();
		String[] program = new String[nLines];
		for(int i=0;i<nLines;i++)
		{
			program[i] = "<html>Line " + i + ": " + rm.getInstructionString(i) + "&nbsp;&nbsp;&nbsp;<font color=red>" + rm.getComment(i) + "</font></html>";	
		}
		rv.updateListing(program,0);
		rv.setSelectedProgramLine(rm.getCurrent());	
	}
}