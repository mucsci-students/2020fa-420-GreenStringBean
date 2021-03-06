package view;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import model.ClassObject;
import model.Relationship;
import java.util.Set;
import java.util.Iterator;
import java.io.BufferedReader;
import controller.CLIController;

/**
 * The Console allows a user to interface with a UML model
 * containing classes and relationships between those classes.
 */
public class CLI {

    //Fields
    boolean helpfilePresent = true;
    BufferedReader brHelp;
    BufferedReader brProject;
    private CLIController controller;
    private CLIView view;
    private String prompt = "gsb> ";

    //Methods

    /**
     * Constructor
     */
    public CLI (CLIView view, CLIController controller) {
        //Initialize the help file
        try {
            brHelp = new BufferedReader (new FileReader ("HelpDocument.txt"));
            brHelp.mark(5000);
        } catch (Exception e) {
            helpfilePresent = false;
        }
        this.controller = controller;
        this.view = view;
    }
    /**
     * Repeatedly prompt the user for input until the program is exited via 'quit'.
     * If a valid command to modify or view the model is given, then that command is executed.
     * If an invalid command is given, then an error message is given.
     */
    public void console() {

        //Print a fun intro message
        view.printLogo();

        //Print a short instructional message to help the user get started
        view.alert("\nPress 'tab' to open a list of commands. Enjoy!\n");

        //Continue to prompt user for input
        while (true) {
            String command = view.readLine(prompt);
            ArrayList<String> commands = parseLine(command);
            if (commands == null)
                continue;

            //The name of the command
            switch (commands.get(0)) {

                //Exit the program
                case "quit":
                    view.alert("Do you want to save before you go? (Y/N/Neither to resume)");
                    quitHandler(view.readLine(prompt));
                    break;

                //Save the working project into a named file
                case "save":
                    if (commands.size() < 2)
                        view.alert("Error: too few arguments for save <filename>");
                    else if (commands.size() > 2)
                        view.alert("Error: too many arguments for save <filename>");
                    else
                        saveFile(commands.get(1));
                    
                    break;

                //Load a project from a file
                case "load":
                    if (commands.size() < 2)
                        view.alert("Error: too few arguments for load <filename.json>");
                    else if (commands.size() > 2)
                        view.alert("Error: too many arguments for load <filename.json>");
                    else
                    {
                        try 
                        {
                            loadFile(commands.get(1));
                        }
                        catch (FileNotFoundException f)
                        {
                            view.alert("Error: File could not be found.");
                        }
                    }
                    break;

                //Print a help file of commands
                case "help":
                    try {
                        readHelpFile();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;

                //Undo a previous action
                case "undo":
                    controller.undo();
                    break;

                //Redo a recently undone action
                case "redo":
                    controller.redo();
                    break;

                //Add a named class to the working project
                case "addClass" :   
                    if (commands.size() < 2)
                        view.alert ("Error: too few arguments for addClass <name>");
                    else if (commands.size() > 2)
                        view.alert ("Error: too many arguments for addClass <name>");
                    else
                        controller.addClass(commands.get(1));
                        break;

                //Delete a named class from the working project
                case "removeClass" :
                    if (commands.size() < 2)
                        view.alert ("Error: too few arguments for deleteClass <class>");
                    else if (commands.size() > 2)
                        view.alert ("Error: too many arguents for deleteClass <class>");
                    else 
                        controller.removeClass(commands.get(1));
                    break;

                //Rename a named class in the working project
                case "renameClass" :
                    if (commands.size() < 3)
                        view.alert ("Error: too few arguments for renameClass <class newName>");
                    else if (commands.size() > 3)
                        view.alert ("Error: too many arguments for renameClass <class newName>");
                    else
                        controller.renameClass(commands.get(1), commands.get(2));
                    break;

                //Open a named class for editing
                case "open" : 
                    if (commands.size() < 2)
                        view.alert ("Error: too few arguments for open <class>");
                    else if (commands.size() > 2)
                        view.alert ("Error: too many arguments for open <class>");
                    else
                        controller.openClass(commands.get(1));
                    break;

                //Close a named class to editing
                case "close" :
                    if (commands.size() < 2)
                        view.alert ("Error: too few arugments for close <class>");
                    else if (commands.size() > 2)
                        view.alert ("Error: too many arguments for close <class>");
                    else
                        controller.closeClass(commands.get(1));
                    break;

                //Add a relationship of two named classes
                case "addRelationship" :
                    if (commands.size() < 4)
                        view.alert("Error: too few arguments for addRelationship. <class class type>");
                    else if (commands.size() > 4)
                        view.alert("Error: too many arguments for addRelationship. <class class type>");
                    else
                    {
                        switch (commands.get(3)) {
                            case "AGGREGATE" :
                                controller.addRelationship (commands.get(1), commands.get(2), "A"); 
                                break;
                            case "COMPOSITION" : 
                                controller.addRelationship (commands.get(1), commands.get(2), "C"); 
                                break;
                            case "INHERITANCE" : 
                                controller.addRelationship (commands.get(1), commands.get(2), "I"); 
                                break;
                            case "REALIZATION" : 
                                controller.addRelationship (commands.get(1), commands.get(2), "R"); 
                                break;
                            default :
                                view.alert("Error: no relationship type given. addRelationship <class class type>");
                        }
                    }
                    break;

                //Delete a relationship of two named classes
                case "removeRelationship" :
                    if (commands.size() < 3)
                        view.alert("Error: too few arguments for deleteRelation <class class>");
                    else if (commands.size() > 3)
                        view.alert("Error: too many arguments for deleteRelation <class class>");
                    else
                        controller.removeRelationship (commands.get(1), commands.get(2));
                    break;

                //Add a new field attribute to named class
                case "addField" :
                    if (commands.size() < 5)
                        view.alert("Error: too few arguments for addField <class visibility type name>");
                    else if (commands.size() > 5)
                        view.alert("Error: too many arguments for addField <class visibility type name>");
                    else 
                        controller.addField(commands.get(1), commands.get(4), commands.get(3), commands.get(2));
                    break;

                //Add a new method attribute to named class
                case "addMethod" :
                    if (commands.size() < 5)
                        view.alert("Error: too few arguments for addMethod <class visibility return type name>");
                    else if (commands.size() > 5)
                        view.alert("Error: too many arguments for addMethod <class visibility return type name>");
                    else
                        controller.addMethod(commands.get(1), commands.get(4), commands.get(3), commands.get(2));
                    break;

                //Add a new parameter to a method in a class
                case "addParameter" :
                    if (commands.size() < 5)
                        view.alert("Error: too few arguments for addParameter <class method paramType paramName>");
                    else if (commands.size() > 5)
                        view.alert("Error: too many arguments for addParameter <class method paramType paramName");
                    else
                        controller.addParameter(commands.get(1), commands.get(2), commands.get(4), commands.get(3));
                    break;

                //Delete a named field from a named class
                case "removeField" :
                    if (commands.size() < 3)
                        view.alert("Error: too few arguments for removeField <class field>");
                    else if (commands.size() > 3)
                        view.alert("Error: too many arguments for removeField <class field>");
                    else
                        controller.removeField(commands.get(1), commands.get(2));
                    break;

                //Delete a named method from a named class
                case "removeMethod" :
                    if (commands.size() < 3)
                        view.alert("Error: too few arguments for removeMethod <class method>");
                    else if (commands.size() > 3)
                        view.alert("Error: too many arguments for removeMethod <class method>");
                    else
                        controller.removeMethod(commands.get(1), commands.get(2));
                    break;   

                //Deleter a named parameter from a named method
                case "removeParameter" :
                    if (commands.size() < 4)
                        view.alert("Error: too few arguments for removeParameter <class method param>");
                    else if (commands.size() > 4)
                        view.alert("Error: too many arguments for removeParameter <class method param");
                    else
                        controller.removeParameter(commands.get(1), commands.get(2), commands.get(3));
                    break;
                
                //Rename a named field from a named class
                case "renameField" :
                    if (commands.size() < 4)
                        view.alert("Error: too few arguments for renameField <class oldName newName>");
                    else if (commands.size() > 4)
                        view.alert("Error: too many arguments for renameField <class oldName newName>");
                    else
                        controller.renameField(commands.get(1), commands.get(2), commands.get(3));
                    break;
                
                //Rename a named method from a named class
                case "renameMethod" :
                    if (commands.size() < 4)
                        view.alert("Error: too few arguments for renameMethod <class oldName newName>");
                    else if (commands.size() > 4)
                        view.alert("Error: too many arguments for renameMethod <class oldName newName>");
                    else
                        controller.renameMethod(commands.get(1), commands.get(2), commands.get(3));
                    break;

                //Rename a parameter 
                case "renameParameter" :
                    if (commands.size() < 5)
                        view.alert("Error: too few arguments for renameParameter <class method param newName>");
                    else if (commands.size() > 5)
                        view.alert("Error: too many arguments for renameParameter <class method param newName>");
                    else
                        controller.renameParameter(commands.get(1), commands.get(2), commands.get(3), commands.get(4));
                    break;

                //Change a field's type
                case "changeFieldType" :
                    if (commands.size() < 4)
                        view.alert("Error: too few arguments for changeFieldType <class field newType>");
                    else if (commands.size() > 4)
                        view.alert("Error: too many arguments for changeFieldType <class field newType>");
                    else 
                        controller.changeFieldType (commands.get(1), commands.get(2), commands.get(3));
                    break;

                //Change a method's return type
                case "changeMethodType" :
                    if (commands.size() < 4)
                        view.alert("Error: too few arguments for changeMethodType <class method newType>");
                    else if (commands.size() > 4)
                        view.alert("Error: too many arguments for changeMethodType <class method newType>");
                    else 
                        controller.changeMethodType (commands.get(1), commands.get(2), commands.get(3));
                    break;

                //Change a parameter's type
                case "changeParameterType" :
                    if (commands.size() < 5)
                        view.alert("Error: too few arguments for changeParameterType <class method param newType>");
                    else if (commands.size() > 5)
                        view.alert("Error: too many arguments for changeParameterType <class method param newType>");
                    else
                        controller.changeParameterType (commands.get(1), commands.get(2), commands.get(3), commands.get(4));
                    break;

                //Change a relationship's type
                case "changeRelationshipType" :
                    if (commands.size() < 4)
                        view.alert("Error: too few arguments for changeRelationshipType <class class newType>");
                    else if (commands.size() > 4)
                        view.alert("Error: too many arguments for changeRelationshipType <class class newType>");
                    else
                    {
                        switch (commands.get(3)) {
                            case "AGGREGATE" :
                                controller.changeRelationshipType (commands.get(1), commands.get(2), "A"); 
                                break;
                            case "COMPOSITION" : 
                                controller.changeRelationshipType (commands.get(1), commands.get(2), "C"); 
                                break;
                            case "INHERITANCE" : 
                                controller.changeRelationshipType (commands.get(1), commands.get(2), "I"); 
                                break;
                            case "REALIZATION" : 
                                controller.changeRelationshipType (commands.get(1), commands.get(2), "R"); 
                                break;
                            default :
                                view.alert("Error: no relationship type given. changeRelationshipType <class class type>");
                        }
                    }
                    break;
                
                //Change a field's visibility
                case "changeFieldVisibility" :
                    if (commands.size() < 4)
                        view.alert("Error: too few arguments for changeFieldVisibility <class field newVisibility>");
                    else if (commands.size() > 4)
                        view.alert("Error: too many arguments for changeFieldVisiblity <class field newVisibility>");
                    else
                        controller.changeFieldVisibility (commands.get(1), commands.get(2), commands.get(3));
                    break;

                    //Change a method's visiblity
                    case "changeMethodVisibility": 
                    if (commands.size() < 4)
                        view.alert("Error: too few arguments for changeMethodVisibility <class method newVisibility>");
                    else if (commands.size() > 4)
                        view.alert("Error: too many arguments for changeMethodVisibility <class method newVisibility>");
                    else
                        controller.changeMethodVisibility (commands.get(1), commands.get(2), commands.get(3));
                    break;

                //Print the names of each class
                case "printClasses" :
                    controller.getProjectSnapshot().printClasses();
                    break;

                //Print a named class with fields and methods
                case "printClass" :
                    if (commands.size() < 2)
                        view.alert("Error: too few arguments for printClass <class>");
                    else if (commands.size() > 2)
                        view.alert("Error: too many arguments for printClass <class>");
                    else
                        printClass(commands.get(1));
                    break;
                
                //Print each relationship
                case "printRelationships" :
                    printRelationships();
                    break;

                case "clearModel" :
                    view.alert("Are you sure you want to clear the project? All unsaved data will be lost. (Y/N)");
                    clearHandler(view.readLine(prompt));
                    break;
                
                case "launchGUI" :
                    view.alert("Launching the GUI will erase any unsaved changes to the project. Proceed? (Y/N/Neither to resume)");
                    launchGUIHander(view.readLine(prompt));
                    break;
                    
                //Bean It Up!
                case "beanItUp" :
                    view.beanItUp();
                    break;
                
                //If the input did not match any known command, then print an error message
                default :
                    view.alert("Error: command \"" + commands.get(0) + "\" is not recognized");
            }
        }
    } 
    

    /**
     * Reads input from the user and parses it for tokens.
     * @param command   a single string containing the full command 
     * @return          Returns an ArrayList of said tokens.
     */
    public ArrayList<String> parseLine(String command)
    {
        ArrayList<String> commandList = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(command);
        while (st.hasMoreTokens())
        {
            commandList.add(st.nextToken());
        }

        if (commandList.size() < 1)
            return null;
        return commandList;
    }

    /*
     * Reads information from a help file.
    */
    public void readHelpFile() throws Exception
    {
        if (!helpfilePresent)
        {
            view.alert("HelpDocument.txt was not found in the local directory.");
            return;
        }
        int i;
        while ((i = brHelp.read()) != -1)
            view.printChar((char) i);
        
        brHelp.reset();
        view.printChar('\n');
    }

    /**
     * Save the working project into a json file
     * @param projectName The name that will be given to the file
     */
    public void saveFile (String projectName)
    {
        String filename = projectName + ".json";
        try {
            File proj = new File(filename);
            if (proj.exists()) 
            {
                //when false file is overwritten
                FileWriter fw = new FileWriter(proj, false);
                fw.write(controller.getProjectSnapshot().toJSONString());
                fw.close();
            }
            else if (proj.createNewFile())
            {
                //when true file is appended
                FileWriter fw = new FileWriter(proj, true);
                fw.write(controller.getProjectSnapshot().toJSONString());
                fw.close();
            }
            else 
            {
                view.alert("For some reason, nothing saved. That's too bad.");
            }
        } catch (IOException e) {
            view.alert ("It's broke, man.");
        }
    }

    /**
     * Load a project from a given json file
     * @param filename The name of the file to be loaded
     * @throws FileNotFoundException
     */
    public void loadFile (String filename) throws FileNotFoundException
    {
        
        try {
            brProject = new BufferedReader(new FileReader(filename));
            int i;
            StringBuilder projectString = new StringBuilder();
            while ((i = brProject.read()) != -1)
                projectString.append((char) i);

            controller.loadProject(projectString.toString());
        } catch (IOException e)
        {
            view.alert("Error: File could not be read.");
        }
    }

    /**
     * Print the relationships in the WorkingProject
     * TODO: Have the Workingcontroller return a string representing the relationships
     */
    private void printRelationships()
    {
        Set<Relationship> r = controller.getProjectSnapshot().getRelationships();
        Iterator iter = r.iterator();
        while(iter.hasNext())
        {
            Relationship rel = (Relationship)iter.next();
            view.alert(rel.getClassNameFrom() + " -> " + rel.getClassNameTo() + " " + rel.getType());
        }
    }

    /**
     * Gets a snapshot of the WorkingProject
     * @param className
     * TODO: Have Workingcontroller return a string representing a class, and print the string
     */
    private void printClass(String className)
    {
        if (controller.getProjectSnapshot().hasClass(className))
        {
            ClassObject c = controller.getProjectSnapshot().getClass(className);
            view.alert("Fields");
            c.printFields();
            view.alert("Methods");
            c.printMethods();
        }
    }

    /**
     * Launch the GUI
     */
    private void launchGUI()
    {
        ProcessBuilder processbuilder = new ProcessBuilder("java", "-jar", "./build/libs/UML-all.jar");
        try {
            processbuilder.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
    }

    /**
     * Handles when the user quits
     * @param input A string expected to be y/n
     */
    private void quitHandler(String input)
    {
        if (input.equals("Y") || input.equals("y"))
        {
            saveFile(view.readLine(prompt));
            System.exit(0);
        }
        else if (input.equals("N") || input.equals("n"))
            System.exit(0);
        else
            view.alert("Inappropriate input. Resuming...");
    }

    /**
     * Handles when the user clears
     * @param input A string expected to be y/n
     */
    private void clearHandler(String input)
    {
        if (input.equals("Y") || input.equals("y"))
            controller.clearProject();
        else if (input.equals("N") || input.equals("n"))
            return;
        else
            view.alert("Inappropriate input. Resuming...");
    }

    /**
     * Handles when the user launches the GUI
     * @param input A string expected to be y/n
     */
    private void launchGUIHander(String input)
    {
        if (input.equals("Y") || input.equals("y"))
        {
            launchGUI();
            System.exit(0);
        }
        else if (input.equals("N") || input.equals("n"))
            return;
        else
            view.alert("Inappropriate input. Resuming...");
    }

    
}