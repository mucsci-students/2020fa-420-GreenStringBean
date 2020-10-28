package controller;
import view.GUIStartScreen;

public class UMLConsoleController {

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