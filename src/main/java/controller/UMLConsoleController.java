package controller;
import view.CLI;
import view.GUIEditorView;
import view.CLIEditorView;
import view.GUIView;
import view.CLIView;

import java.io.IOException;

import model.Model;
import model.WorkingProject;

public class UMLConsoleController {

	/**
	 * Main method of the program and will start the GUI or CLI
	 * 
	 * @param args is input from a user to decide to use GUI or CLI
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException 
		{
			Model model = new WorkingProject();
			ModelEditor editor = new WorkingProjectEditor(model);
			
			if(args.length == 1 && args[0].equals("--cli"))
			{
				//Start in console
				CLIEditorView view = new CLIEditorView();
				CLIEditorController controller = new CLIEditorController(view, editor);
				new CLI(view, controller);
			}
			else 
			{
				GUIView view = new GUIEditorView();
				GUIController controller = new GUIEditorController(view, editor);
				GUIStartScreen uml = new GUIStartScreen(controller);
			}
		}
}