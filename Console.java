import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class Console {

    //Fields
    static boolean helpfilePresent = true;
    static BufferedReader br;
    static WorkingProject project;

    //Methods

    //console is 99% of this class. It accepts user input, allowing the user to modify the working UML project.
    public static void console() {
        Scanner keyboard = new Scanner(System.in);
        while (true) {
            System.out.print(">");
            String command = keyboard.nextLine();
            ArrayList<String> commands = parseLine(command);
            if (commands == null)
                continue;

            switch (commands.get(0)) {

                //Exit the program
                case "quit":
                    keyboard.close();
                    System.exit(0);

                //Save the working project into a named file
                case "save":
                    //TODO Implement save feature
                    System.out.println("Saving isn't implemented yet. Sorry!");
                    break;

                //Load a project from a file
                case "load":
                    //TODO Implement load feature
                    System.out.println("Loading isn't implemented yet. Sorry!");
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

                //Add a named class to the working project
                case "addClass" :   
                    if (commands.size() < 2)
                        System.out.println ("error: too few arguments for addClass<name>");
                    else if (commands.size() > 2)
                        System.out.println ("error: too many arguments for addClass<name>");
                    else
                        project.addClass(commands.get(1));
                    break;

                //Delete a named class from the working project
                case "deleteClass" :
                    if (commands.size() < 2)
                        System.out.println ("error: too few arguments for deleteClass<class>");
                    else if (commands.size() > 2)
                        System.out.println ("error: too many arguents for deleteClass<class>");
                    else 
                        project.removeClass(commands.get(1));
                    break;

                //Rename a named class in the working project
                case "renameClass" :
                    if (commands.size() < 3)
                        System.out.println ("error: too few arguments for renameClass<class, newName>");
                    else if (commands.size() > 3)
                        System.out.println ("error: too many arguments for renameClass<class, newName>");
                    else
                        project.renameClass(commands.get(1), commands.get(2));
                    break;

                //Open a named class for editing
                case "open" : 
                    if (commands.size() < 2)
                        System.out.println ("error: too few arguments for open<class>");
                    else if (commands.size() > 2)
                        System.out.println ("error: too many arguments for open<class>");
                    else
                        project.openClass(commands.get(1));
                    break;

                //Close a named class to editing
                case "close" :
                    if (commands.size() < 2)
                        System.out.println ("error: too few arugments for close<class>");
                    else if (commands.size() > 2)
                        System.out.println ("error: too many arguments for close<class>");
                    else
                        project.closeClass(commands.get(1));
                    break;

                //Add a relationship of two named classes
                case "addRelationship" :
                    if (commands.size() < 4)
                        System.out.println("error: too few arguments for addRelation. <class, class, type>");
                    else if (commands.size() > 4)
                        System.out.println("error: too many arguments for addRelation. <class, class, type>");
                    else
                    {
                        switch (commands.get(4)) {
                            case "-a" :
                                //project.addRelationship (commands.get(1), commands.get(2), "aggregation"); 
                                break;
                            case "-c" : 
                                //project.addRelationship (commands.get(1), commands.get(2), "composition"); 
                                break;
                            case "-g" : 
                                //project.addRelationship (commands.get(1), commands.get(2), "generalization"); 
                                break;
                            case "-i" : 
                                //project.addRelationship (commands.get(1), commands.get(2), "inheritance"); 
                                break;
                            default :
                                System.out.println("error: no relationship type given. <class, class, type>");
                        }
                        project.addRelationship (commands.get(1), commands.get(2));
                    }
                    break;

                //Delete a relationship of two named classes
                case "deleteRelationship" :
                    if (commands.size() < 3)
                        System.out.println("error: too few arguments for deleteRelation<class, class>");
                    else if (commands.size() > 3)
                        System.out.println("error: too many arguments for deleteRelation<class, class>");
                    else
                        project.removeRelationship (commands.get(1), commands.get(2));
                    break;

                //Add a named attribute to a named class
                case "addAttribute" :
                    if (commands.size() < 3)
                        System.out.println("error: too few arguments for addAttribute<class, name>");
                    else if (commands.size() > 3)
                        System.out.println("error: too many arguments for addAttribute<class, name>");
                    else
                    {
                        //System.out.print("Is this attribute a field or a method?");
                        //String attr = keyboard.nextLine();
                        //if (attr.equals("field"))
                        //  System.out.print("What is the data type?");
                        //  String type = keyboard.nextLine();
                        //else if (attr.equals("method"))
                        //  System.out.print("What is the return type?");
                        //  String type = keyboard.nextLine();

                        //TODO: [Some checks to make sure input is valid]

                        //project.addAttribute(commands.get(1), commands.get(2), attr, type);

                        project.addAttribute(commands.get(1), commands.get(2));
                    }
                    break;

                //Add a new field attribute to named class
                case "addField" :
                    if (commands.size() < 4)
                        System.out.println("error: too few arguments for addField <class, name, data type>");
                    else if (commands.size() > 4)
                        System.out.println("error: too many arguments for addField class <class, name, data type>");
                    else 
                        //project.addAttribute(commands.get(1), commands.get(2), commands.get(3));
                    break;

                //Add a new method attribute to named class
                case "addMethod" :
                    if (commands.size() < 4)
                        System.out.println("error: too few arguments for addMethod <class, name, return type>");
                    else if (commands.size() > 4)
                        System.out.println("error: too many arguments for addMethod class <class, name, return type>");
                    else
                    {
                        ArrayList<String> param;
                        System.out.print ("What are the data types of the parameters for this method, if any?");
                        String p = keyboard.nextLine();
                        param = parseLine(p);
                        //project.addAttribute(commands.get(1), commands.get(2), commands.get(3), param);
                    }
                    break;     

                //Delete a named attribute from a named class
                case "deleteAttribute" :
                    if (commands.size() < 3)
                        System.out.println("error: too few arguments for deleteAttribute<class, attribute>");
                    else if (commands.size() > 3)
                        System.out.println("error: too many arguments for deleteAttribute<class, attribute>");
                    else
                        project.removeAttribute(commands.get(1), commands.get(2));
                    break;

                //Rename a named attribute from a named class
                case "renameAttribute" :
                    if (commands.size() < 5)
                        System.out.println("error: too few arguments for renameAttribute<class, attribute, newName, type>");
                    else if (commands.size() < 5)
                        System.out.println("error: too many arguments for renameAttribute<class, attribute, newName, type>");
                    else
                        //project.renameAttribute(commands.get(1), commands.get(2), commands.get(3), commands.get(4));
                        project.renameAttribute(commands.get(1), commands.get(2), commands.get(3));
                    break;
                
                //Print the names of each class
                case "printClasses" :
                    project.printClasses();
                    break;
                
                //Print the names of each attribute in a class
                case "printAttributes" :
                    if (commands.size() < 2)
                        System.out.println("error: too few arguments for printAttributes<class>");
                    else if (commands.size() > 2)
                        System.out.println("error: too many arguments for printAttributes<class>");
                    else
                        project.printAttributes(commands.get(1));
                    break;

                //Print each relationship
                case "printRelationships" :
                    project.printRelationships();
                    break;
                
                //If the input did not match any known command, then print an error message
                default :
                    System.out.println("error: command \"" + commands.get(0) + "\" is not recognized");
            }
        }
    }

    //Reads input from the user and parses it for tokens. Returns an ArrayList of said tokens.
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

    //Reads information from a help file.
    public static void readHelpFile() throws Exception
    {
        if (!helpfilePresent)
        {
            System.out.println("HelpDocument.txt was not found in the local directory.");
            return;
        }
        int i;
        while ((i = br.read()) != -1)
            System.out.print((char) i);
        
        br.reset();
        System.out.print("\n");
    }

    public static void main(String[] args)
    {
        try {
            br = new BufferedReader (new FileReader ("HelpDocument.txt"));
            br.mark(2000);
        }
        catch (Exception FileNotFoundException) {
            helpfilePresent = false;
        }
        project = new WorkingProject();
        console ();
        System.exit(0);
    }
}