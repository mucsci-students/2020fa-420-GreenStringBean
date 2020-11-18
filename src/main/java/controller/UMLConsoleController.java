package controller;
import view.GUIStartScreen;
import view.CLI;
import model.Model;
import model.WorkingProject;

public class UMLConsoleController {

		/**
		 * Main method of the program and will start the GUI or CLI
		 * @param args is input from a user to decide to use GUI or CLI
		 */
		public static void main(String[] args) 
		{
			Model model = new WorkingProject();
			ModelEditor editor = new WorkingProjectEditor(model);
			//start in console
			if(args.length == 1 && args[0].equals("--cli"))
			{
				new CLI(editor);
			}
			else {
				//Starts the app with a prompt to start GUI 
				GUIStartScreen uml = new GUIStartScreen(editor);
			}
		}
}