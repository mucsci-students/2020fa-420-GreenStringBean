import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;

public class Console {

    //Fields
    static boolean helpfilePresent = true;
    static BufferedReader brHelp;
    static BufferedReader brProject;
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
                    if (commands.size() < 2)
                        System.out.println("error: too few arguments for save<filename>");
                    else if (commands.size() > 2)
                        System.out.println("error: too many arguments for save<filename>");
                    else
                    {
                        saveFile(commands.get(1));
                    }
                    
                    break;

                //Load a project from a file
                case "load":
                    if (commands.size() < 2)
                        System.out.println("error: too few arguments for load<filename.txt>");
                    else if (commands.size() > 2)
                        System.out.println("error: too many arguments for load<filename.txt>");
                    else
                    {
                        try 
                        {
                            loadFile(commands.get(1));
                        }
                        catch (FileNotFoundException f)
                        {
                            System.out.println("error: File could not be found.");
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

                //Add a named class to the working project
                case "addClass" :   
                    if (commands.size() < 2)
                        System.out.println ("error: too few arguments for addClass");
                    else if (commands.size() > 2)
                        System.out.println ("error: too many arguments for addClass");
                    else
                        project.addClass(commands.get(1));
                    break;

                //Delete a named class from the working project
                case "deleteClass" :
                    if (commands.size() < 2)
                        System.out.println ("error: too few arguments for deleteClass");
                    else if (commands.size() > 2)
                        System.out.println ("error: too many arguents for deleteClass");
                    else 
                        project.removeClass(commands.get(1));
                    break;

                case "renameClass" :
                    if (commands.size() < 3)
                        System.out.println ("error: too few arguments for renameClass");
                    else if (commands.size() > 3)
                        System.out.println ("error: too many arguments for renameClass");
                    else
                        project.renameClass(commands.get(1), commands.get(2));
                    break;

                //Open a named class for editing
                case "open" : 
                    if (commands.size() < 2)
                        System.out.println ("error: too few arguments for open");
                    else if (commands.size() > 2)
                        System.out.println ("error: too many arguments for open");
                    else
                        project.openClass(commands.get(1));
                    break;

                //Close a named class to editing
                case "close" :
                    if (commands.size() < 2)
                        System.out.println ("error: too few arugments for close");
                    else if (commands.size() > 2)
                        System.out.println ("error: too many arguments for close");
                    else
                        project.closeClass(commands.get(1));
                    break;

                //Add a relationship of two named classes
                case "addRelationship" :
                    if (commands.size() < 3)
                        System.out.println("error: too few arguments for addRelation.");
                    else if (commands.size() > 3)
                        System.out.println("error: too many arguments for addRelation.");
                    else
                        project.addRelationship (commands.get(1), commands.get(2));
                    break;

                //Delete a relationship of two named classes
                case "deleteRelationship" :
                    if (commands.size() < 3)
                        System.out.println("error: too few arguments for deleteRelation");
                    else if (commands.size() > 3)
                        System.out.println("error: too many arguments for deleteRelation");
                    else
                        project.removeRelationship (commands.get(1), commands.get(2));
                    break;

                //Add a named attribute to a named class
                case "addAttribute" :
                    if (commands.size() < 3)
                        System.out.println("error: too few arguments for addAttribute");
                    else if (commands.size() > 3)
                        System.out.println("error: too many arguments for addAttribute");
                    else
                    {
                        project.addAttribute(commands.get(1), commands.get(2));
                    }
                    break;

                //Delete a named attribute from a named class
                case "deleteAttribute" :
                    if (commands.size() < 3)
                        System.out.println("error: too few arguments for deleteAttribute");
                    else if (commands.size() > 3)
                        System.out.println("error: too many arguments for deleteAttribute");
                    else
                        project.removeAttribute(commands.get(1), commands.get(2));
                    break;

                //Rename a named attribute from a named class
                case "renameAttribute" :
                    if (commands.size() < 4)
                        System.out.println("error: too few arguments for renameAttribute");
                    else if (commands.size() < 4)
                        System.out.println("error: too many arguments for renameAttribute");
                    else
                        project.renameAttribute(commands.get(1), commands.get(2), commands.get(3));
                    break;
                
                //Print the names of each class
                case "printClasses" :
                    project.printClasses();
                    break;
                
                //Print the names of each attribute in a class
                case "printAttributes" :
                    if (commands.size() < 2)
                        System.out.println("error: too few arguments for printAttributes");
                    else if (commands.size() > 2)
                        System.out.println("error: too many arguments for printAttributes");
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
        while ((i = brHelp.read()) != -1)
            System.out.print((char) i);
        
        brHelp.reset();
        System.out.print("\n");
    }

    public static void saveFile (String projectName)
    {
        String filename = projectName + ".txt";
        try {
            File proj = new File(filename);
            if (proj.createNewFile())
            {
                FileWriter fw = new FileWriter(proj);
                fw.write(project.toJSONString());
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

    public static void loadFile (String filename) throws FileNotFoundException
    {
        
        try {
            brProject = new BufferedReader(new FileReader(filename));
            int i;
            StringBuilder projectString = new StringBuilder();
            while ((i = brProject.read()) != -1)
                projectString.append((char) i);

            project = loadProject(projectString.toString());
        } catch (IOException e)
        {
            System.out.println("error: File could not be read.");
        }
    }

    public static WorkingProject loadProject(String projectString)
    {
        WorkingProject loadedProject = new WorkingProject();

        Object obj = JSONValue.parse(projectString);
        JSONObject object = (JSONObject)obj;
        JSONArray classes = (JSONArray)object.get("Classes");
        JSONArray relationships = (JSONArray)object.get("Relationships");
        
        for (int i = 0; i < classes.size(); ++i)
        {
            JSONObject c = (JSONObject)classes.get(i);
            String className = (String)c.get("Name");
            loadedProject.addClass(className);

            JSONArray attributes = (JSONArray)c.get("Attributes");
            for (int j = 0; j < attributes.size(); ++j)
            {
                JSONObject a = (JSONObject)attributes.get(j);
                String attributeName = (String)a.get("Name");
                //String attributeType = a.get("Type");
                //loadedProject.addAttribute(className, attributeName, attributeType);
                loadedProject.addAttribute(className, attributeName);
            }
        }
        for (int i = 0; i < relationships.size(); ++i)
        {
            JSONObject r = (JSONObject)relationships.get(i);
            String classOne = (String)r.get("ClassOne");
            String classTwo = (String)r.get("ClassTwo");
            loadedProject.addRelationship(classOne, classTwo);
        }

        return loadedProject;
    }

    public static void main(String[] args)
    {
        try {
            brHelp = new BufferedReader (new FileReader ("HelpDocument.txt"));
            brHelp.mark(2000);
        }
        catch (Exception FileNotFoundException) {
            helpfilePresent = false;
        }
        project = new WorkingProject();
        console ();
        System.exit(0);
    }
}