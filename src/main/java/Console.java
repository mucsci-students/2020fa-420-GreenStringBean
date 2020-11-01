import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import model.ClassObject;
import model.Relationship;
import java.util.Set;
import java.util.Iterator;
import java.io.BufferedReader;
import controller.WorkingProjectEditor;

/**
 * The Console allows a user to interface with a UML model
 * containing classes and relationships between those classes.
 */
public class Console {

    //Fields
    static boolean helpfilePresent = true;
    static BufferedReader brHelp;
    static BufferedReader brProject;
    static WorkingProjectEditor projectEditor;

    //Methods

    /**
     * Repeatedly prompt the user for input until the program is exited via 'quit'.
     * If a valid command to modify or view the model is given, then that command is executed.
     * If an invalid command is given, then an error message is given.
     */
    public static void console() {
        Scanner keyboard = new Scanner(System.in);
        //Continue to prompt user for input
        while (true) {
            System.out.print(">");
            String command = keyboard.nextLine();
            ArrayList<String> commands = parseLine(command);
            if (commands == null)
                continue;

            //The name of the command
            switch (commands.get(0)) {

                //Exit the program
                case "quit":
                    System.out.println("Do you want to save before you go? (Y/N)");
                    System.out.print(">");
                    String YN = keyboard.nextLine();

                    if (YN.equals("y".toUpperCase()) || YN.equals("y"))
                    {
                        System.out.println("What do you want to name the project?");
                        System.out.print(">");
                        String name = keyboard.nextLine();
                        saveFile (parseLine(name).get(0));
                    }
                    else if (YN.equals("n".toUpperCase()) || YN.equals("n"))
                    {
                        keyboard.close();
                        return;
                    }
                    else
                    {
                        System.out.println("Y/N not entered. Resuming...");
                    }

                    break;

                //Save the working project into a named file
                case "save":
                    if (commands.size() < 2)
                        System.out.println("Error: too few arguments for save<filename>");
                    else if (commands.size() > 2)
                        System.out.println("Error: too many arguments for save<filename>");
                    else
                    {
                        saveFile(commands.get(1));
                    }
                    
                    break;

                //Load a project from a file
                case "load":
                    if (commands.size() < 2)
                        System.out.println("Error: too few arguments for load<filename.txt>");
                    else if (commands.size() > 2)
                        System.out.println("Error: too many arguments for load<filename.txt>");
                    else
                    {
                        try 
                        {
                            loadFile(commands.get(1));
                        }
                        catch (FileNotFoundException f)
                        {
                            System.out.println("Error: File could not be found.");
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
                    projectEditor.undo();
                    break;

                //Redo a recently undone action
                case "redo":
                    projectEditor.redo();
                    break;

                //Add a named class to the working project
                case "addClass" :   
                    if (commands.size() < 2)
                        System.out.println ("Error: too few arguments for addClass<name>");
                    else if (commands.size() > 2)
                        System.out.println ("Error: too many arguments for addClass<name>");
                    else
                    {
                        projectEditor.addClass(commands.get(1));
                        printStatusMessage();
                    }
                        break;

                //Delete a named class from the working project
                case "deleteClass" :
                    if (commands.size() < 2)
                        System.out.println ("Error: too few arguments for deleteClass<class>");
                    else if (commands.size() > 2)
                        System.out.println ("Error: too many arguents for deleteClass<class>");
                    else 
                    {
                        projectEditor.removeClass(commands.get(1));
                        printStatusMessage();
                    }
                    break;

                //Rename a named class in the working project
                case "renameClass" :
                    if (commands.size() < 3)
                        System.out.println ("Error: too few arguments for renameClass<class, newName>");
                    else if (commands.size() > 3)
                        System.out.println ("Error: too many arguments for renameClass<class, newName>");
                    else
                    {
                        projectEditor.renameClass(commands.get(1), commands.get(2));
                        printStatusMessage();
                    }
                    break;

                //Open a named class for editing
                case "open" : 
                    if (commands.size() < 2)
                        System.out.println ("Error: too few arguments for open<class>");
                    else if (commands.size() > 2)
                        System.out.println ("Error: too many arguments for open<class>");
                    else
                    {
                        projectEditor.openClass(commands.get(1));
                        printStatusMessage();
                    }
                    break;

                //Close a named class to editing
                case "close" :
                    if (commands.size() < 2)
                        System.out.println ("Error: too few arugments for close<class>");
                    else if (commands.size() > 2)
                        System.out.println ("Error: too many arguments for close<class>");
                    else
                    {
                        projectEditor.closeClass(commands.get(1));
                        printStatusMessage();
                    }
                    break;

                //Add a relationship of two named classes
                case "addRelationship" :
                    if (commands.size() < 4)
                        System.out.println("Error: too few arguments for addRelation. <class, class, type>");
                    else if (commands.size() > 4)
                        System.out.println("Error: too many arguments for addRelation. <class, class, type>");
                    else
                    {
                        switch (commands.get(3)) {
                            case "-a" :
                                projectEditor.addRelationship (commands.get(1), commands.get(2), "A"); 
                                printStatusMessage();
                                break;
                            case "-c" : 
                                projectEditor.addRelationship (commands.get(1), commands.get(2), "C"); 
                                printStatusMessage();
                                break;
                            case "-i" : 
                                projectEditor.addRelationship (commands.get(1), commands.get(2), "I"); 
                                printStatusMessage();
                                break;
                            case "-r" : 
                                projectEditor.addRelationship (commands.get(1), commands.get(2), "R"); 
                                printStatusMessage();
                                break;
                            default :
                                System.out.println("Error: no relationship type given. <class, class, type>");
                        }
                    }
                    break;

                //Delete a relationship of two named classes
                case "deleteRelationship" :
                    if (commands.size() < 3)
                        System.out.println("Error: too few arguments for deleteRelation<class, class>");
                    else if (commands.size() > 3)
                        System.out.println("Error: too many arguments for deleteRelation<class, class>");
                    else
                    {
                        projectEditor.removeRelationship (commands.get(1), commands.get(2));
                        printStatusMessage();
                    }
                    break;

                //Add a new field attribute to named class
                case "addField" :
                    if (commands.size() < 5)
                        System.out.println("Error: too few arguments for addField <class, name, data type, visibility>");
                    else if (commands.size() > 5)
                        System.out.println("Error: too many arguments for addField class <class, name, data type, visibility>");
                    else 
                    {
                        projectEditor.addField(commands.get(1), commands.get(2), commands.get(3), commands.get(4));
                        printStatusMessage();
                    }
                    break;

                //Add a new method attribute to named class
                case "addMethod" :
                    if (commands.size() < 5)
                        System.out.println("Error: too few arguments for addMethod <class, name, return type, visibility>");
                    else if (commands.size() > 5)
                        System.out.println("Error: too many arguments for addMethod class <class, name, return type, visibility>");
                    else
                    {
                        projectEditor.addMethod(commands.get(1), commands.get(2), commands.get(3), commands.get(4));
                        printStatusMessage();
                    }
                    break;

                //Add a new parameter to a method in a class
                case "addParameter" :
                    if (commands.size() < 5)
                        System.out.println("Error: too few arguments for addParameter<class, method, paramName, paramType>");
                    else if (commands.size() > 5)
                        System.out.println("Error: too many arguments for addParameter<class, method, paramName, paramType");
                    else
                    {
                        projectEditor.addParameter(commands.get(1), commands.get(2), commands.get(3), commands.get(4));
                        printStatusMessage();
                    }
                    break;

                //Delete a named field from a named class
                case "deleteField" :
                    if (commands.size() < 3)
                        System.out.println("Error: too few arguments for deleteAttribute<class, attribute>");
                    else if (commands.size() > 3)
                        System.out.println("Error: too many arguments for deleteAttribute<class, attribute>");
                    else
                    {
                        projectEditor.removeField(commands.get(1), commands.get(2));
                        printStatusMessage();
                    }
                    break;

                //Delete a named method from a named class
                case "deleteMethod" :
                if (commands.size() < 3)
                    System.out.println("Error: too few arguments for deleteAttribute<class, attribute>");
                else if (commands.size() > 3)
                    System.out.println("Error: too many arguments for deleteAttribute<class, attribute>");
                else
                {
                    projectEditor.removeMethod(commands.get(1), commands.get(2));
                    printStatusMessage();
                }
                break;   

                //Deleter a named parameter from a named method
                case "deleteParameter" :
                if (commands.size() < 4)
                    System.out.println("Error: too few arguments for deleteParameter<class, method, param>");
                else if (commands.size() > 4)
                    System.out.println("Error: too many arguments for deleteParameter<class, method, param");
                else
                {
                    projectEditor.removeParameter(commands.get(1), commands.get(2), commands.get(3));
                    printStatusMessage();
                }
                break;
                
                //Rename a named field from a named class
                case "renameField" :
                    if (commands.size() < 4)
                        System.out.println("Error: too few arguments for renameField<class, oldName, newName>");
                    else if (commands.size() > 4)
                        System.out.println("Error: too many arguments for renameField<class, oldName, newName>");
                    else
                    {
                        projectEditor.renameField(commands.get(1), commands.get(2), commands.get(3));
                        printStatusMessage();
                    }
                    break;
                
                //Rename a named method from a named class
                case "renameMethod" :
                if (commands.size() < 4)
                    System.out.println("Error: too few arguments for renameMethod<class, oldName, newName>");
                else if (commands.size() > 4)
                    System.out.println("Error: too many arguments for renameMethod <class, oldName, newName>");
                else
                {
                    projectEditor.renameMethod(commands.get(1), commands.get(2), commands.get(3));
                    printStatusMessage();
                }
                break;

                //Rename a parameter 
                case "renameParameter" :
                if (commands.size() < 5)
                    System.out.println("Error: too few arguments for renameParameter<class, method, parameter, newName>");
                else if (commands.size() > 5)
                    System.out.println("Error: too many arguments for renameParameter<class, method, parameter, newName>");
                else
                {
                    projectEditor.renameParameter(commands.get(1), commands.get(2), commands.get(3), commands.get(4));
                    printStatusMessage();
                }
                break;

                //Change a field's type
                case "changeFieldType" :
                if (commands.size() < 4)
                    System.out.println("Error: too few arguments for changeFieldType<class, field, newType>");
                else if (commands.size() > 4)
                    System.out.println("Error: too many arguments for changeFieldType<class, field, newType>");
                else 
                {
                    projectEditor.changeFieldType (commands.get(1), commands.get(2), commands.get(3));
                    printStatusMessage();
                }
                break;

                //Change a method's return type
                case "changeMethodType" :
                if (commands.size() < 4)
                    System.out.println("Error: too few arguments for changeMethodType<class, method, newType>");
                else if (commands.size() > 4)
                    System.out.println("Error: too many arguments for changeMethodType<class, method, newType>");
                else 
                {
                    projectEditor.changeMethodType (commands.get(1), commands.get(2), commands.get(3));
                    printStatusMessage();
                }
                break;

                //Change a parameter's type
                case "changeParameterType" :
                if (commands.size() < 5)
                    System.out.println("Error: too few arguments for changeParameterType<class, method, parameter, newType>");
                else if (commands.size() > 5)
                    System.out.println("Error: too many arguments for changeParameterType<class, method, parameter, newType>");
                else
                {
                    projectEditor.changeParameterType (commands.get(1), commands.get(2), commands.get(3), commands.get(4));
                    printStatusMessage();
                }    
                break;

                //Change a field's visibility
                case "changeFieldVisibility" :
                if (commands.size() < 4)
                    System.out.println("Error: too few arguments for changeFieldVisibility<class, field, newVisibility>");
                else if (commands.size() > 4)
                    System.out.println("Error: too many arguments for changeFieldVisiblity<class, field, newVisibility>");
                else
                {
                    projectEditor.changeFieldVisibility (commands.get(1), commands.get(2), commands.get(3));
                    printStatusMessage();
                }
                //Change a method's visiblity
                case "changeMethodVisibility": 
                if (commands.size() < 4)
                    System.out.println("Error: too few arguments for changeMethodVisibility<class, method, newVisibility>");
                else if (commands.size() > 4)
                    System.out.println("Error: too many arguments for changeMethodVisibility<class, method, newVisibility>");
                else
                {
                    projectEditor.changeMethodVisibility (commands.get(1), commands.get(2), commands.get(3));
                    printStatusMessage();
                }

                //Print the names of each class
                case "printClasses" :
                    projectEditor.getProjectSnapshot().printClasses();
                    break;

                //Print a named class with fields and methods
                case "printClass" :
                    if (commands.size() < 2)
                        System.out.println("Error: too few arguments for printClass<class>");
                    else if (commands.size() > 2)
                        System.out.println("Error: too many arguments for printClass<class>");
                    else
                        printClass(commands.get(1));
                    break;

                
                /**
                 * //Print the fields of a named class
                 * case "printFields" :
                 *   if (commands.size() < 2)
                 *       System.out.println("Error: too few arguments for printFields<class>");
                 *   else if (commands.size() > 2)
                 *       System.out.println("Error: too many arguments for printFields<class>");
                 *   else
                 *       System.out.println("No longer supported");
                 *   break;
                 *
                 *   //Print the methods of a named class
                 *   case "printMethods" :
                 *   if (commands.size() < 2)
                 *       System.out.println("Error: too few arguments for printFields<class>");
                 *   else if (commands.size() > 2)
                 *       System.out.println("Error: too many arguments for printFields<class>");
                 *   else
                 *       System.out.println("No longer supported");
                 *   break;
                 * 
                 */
                
                
                //Print each relationship
                case "printRelationships" :
                    printRelationships();
                    break;
                
                //If the input did not match any known command, then print an error message
                default :
                    System.out.println("Error: command \"" + commands.get(0) + "\" is not recognized");
            }
        }
    }

    /**
     * Reads input from the user and parses it for tokens.
     * @param command   a single string containing the full command 
     * @return          Returns an ArrayList of said tokens.
     */
    public static ArrayList<String> parseLine(String command)
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
    public static void readHelpFile() throws Exception
    {
        if (!helpfilePresent)
        {
            System.out.println("HelpDocument.txt was not found in the local directory.");
            return;
        }
        int i;
        while ((i = brHelp.read()) != -1)
            System.out.print((char) i);
        
        brHelp.reset();
        System.out.print("\n");
    }

    /**
     * Save the working project into a json file
     * @param projectName The name that will be given to the file
     */
    public static void saveFile (String projectName)
    {
        String filename = projectName + ".json";
        try {
            File proj = new File(filename);
            if (proj.exists()) 
            {
                //when false file is overwritten
                FileWriter fw = new FileWriter(proj, false);
                fw.write(projectEditor.getProjectSnapshot().toJSONString());
                fw.close();
            }
            else if (proj.createNewFile())
            {
                //when true file is appended
                FileWriter fw = new FileWriter(proj, true);
                fw.write(projectEditor.getProjectSnapshot().toJSONString());
                fw.close();
            }
            else 
            {
                System.out.println("For some reason, nothing saved. That's too bad.");
            }
        } catch (IOException e) {
            System.out.println ("It's broke, man.");
        }
    }

    /**
     * Load a project from a given json file
     * @param filename The name of the file to be loaded
     * @throws FileNotFoundException
     */
    public static void loadFile (String filename) throws FileNotFoundException
    {
        
        try {
            brProject = new BufferedReader(new FileReader(filename + ".json"));
            int i;
            StringBuilder projectString = new StringBuilder();
            while ((i = brProject.read()) != -1)
                projectString.append((char) i);

            projectEditor.loadProject(projectString.toString());
        } catch (IOException e)
        {
            System.out.println("Error: File could not be read.");
        }
    }

    /**
     * Print getLastCommandStatusMessage in the Console
     */
    public static void printStatusMessage()
    {
        System.out.println(projectEditor.getLastCommandStatusMessage());
    }

    /**
     * Print the relationships in the WorkingProject
     * TODO: Have the WorkingProjectEditor return a string representing the relationships
     */
    private static void printRelationships()
    {
        Set<Relationship> r = projectEditor.getProjectSnapshot().getRelationships();
        Iterator iter = r.iterator();
        while(iter.hasNext())
        {
            Relationship rel = (Relationship)iter.next();
            System.out.println(rel.getClassNameFrom() + " -> " + rel.getClassNameTo() + " " + rel.getType());
        }
    }

    /**
     * Gets a snapshot of the WorkingProject
     * @param className
     * TODO: Have WorkingProjectEditor return a string representing a class, and print the string
     */
    private static void printClass(String className)
    {
        ClassObject c = projectEditor.getProjectSnapshot().getClass(className);
        c.printFields();
        c.printMethods();
    }

    public static void main(String[] args)
    {
        try {
            brHelp = new BufferedReader (new FileReader ("HelpDocument.txt"));
            brHelp.mark(5000);
        }
        catch (Exception FileNotFoundException) {
            helpfilePresent = false;
        }
        projectEditor = new WorkingProjectEditor();
        console ();
        System.exit(0);
    }
}