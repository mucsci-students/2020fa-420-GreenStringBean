package controller;
import view.GUIStartScreen;
import view.Console;

public class UMLConsoleController {

		/**
		 * Main method of the program and will start the GUI or CLI
		 * @param args is input from a user to decide to use GUI or CLI
		 */
		public static void main(String[] args) {
			//start in console
			if(args.length == 1 && args[0].equals("--cli"))
			{
				Console.main(args);
			}

			//Starts the app with a prompt to start GUI 
			GUIStartScreen uml = new GUIStartScreen();
		}
}