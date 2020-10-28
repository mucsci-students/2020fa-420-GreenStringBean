package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.*;

public class FileButtonClick implements ActionListener
{
	private MenuViews view;
	private HelperControllers controller;
	
	public FileButtonClick(MenuViews v, HelperControllers c)
	{
		this.view = v;
		this.controller = c;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("succesfully got to FileClick actions: FileClick()");
		
		String cmd = e.getActionCommand();
		if(cmd.equals("Undo"))
		{
			controller.undo();
		}
		else if(cmd.equals("Redo"))
		{
			controller.redo();
		}
		else if(cmd.equals("Save"))
		{
			String currFile = view.getText("Save ->", cmd);
			controller.save(currFile);
		}
		else if(cmd.equals("Load"))
		{
			String projectToLoad = view.getText("Load -> ", cmd);
			controller.load(projectToLoad);
		}
	}
}
