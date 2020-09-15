import java.util.StringTokenizer;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Console {

    static boolean helpfilePresent = true;
    static FileReader fr;

    public static void console() {
        Scanner keyboard = new Scanner(System.in);
        while (true) {
            System.out.print(">");
            String command = keyboard.nextLine();
            ArrayList<String> commands = parseLine(command);
            if (commands == null)
                continue;

            switch (commands.get(0)) {
                case "quit": // quit
                    System.exit(0);
                case "save": // save
                    break;
                case "load": // load filename
                    break;
                case "help":
                    try {
                        readHelpFile();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case "addClass" :   //addClass name
                    break;
                case "deleteClass" :    //deleteClass name
                    break;
                case "open" :   //open name
                    break;
                case "close" :  //close name
                    break;
                case "addRelation" :    //addRelation name1 name2
                    break;
                case "deleteRelation" : //deleteRelation name1 name2
                    break;
                case "addAttribute" :   //addAttribute Aname Cname
                    break;
                case "deleteAttribute" :    //deleteAttribute Aname Cname
                    break;
                case "editAttribute" :  //editAttribute Aname Cname
                    break;
                case "test" : 
                	System.out.println (commands.get(1));
                default :
                    break;

            }
        }
    }

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

    public static void readHelpFile() throws Exception
    {
        int i;
        while ((i = fr.read()) != -1)
            System.out.print((char) i);
    }

    public static void main(String[] args)
    {
        try {
            fr = new FileReader ("C:\\Users\\Jeremy\\Documents\\420\\HelpDocument.txt");
        }
        catch (Exception FileNotFoundException) {
            helpfilePresent = false;
        }
        console ();
        System.exit(0);
    }
}