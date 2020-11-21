package controller;
import view.CLI;
import view.GUIEditorView;
import view.GUIView;
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
			if(args.length == 1 && args[0].equals("--cli"))
			{
				//Start in console
				new CLI(editor);
			}
			else 
			{
				GUIView view = new GUIEditorView();
				GUIController controller = new GUIEditorController(view, editor);
				GUIStartScreen uml = new GUIStartScreen(controller);
			}
		}
}