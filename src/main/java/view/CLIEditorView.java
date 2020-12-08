package view;

import model.ClassObject;
import model.Model;
import model.WorkingProject;

import java.io.IOException;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.history.*;
import org.jline.reader.History;
import org.jline.reader.impl.DefaultParser;

public class CLIEditorView implements CLIView {

    private Terminal terminal;
    private CommandCompleter completer;
    private LineReader lineReader;
    private History history;
    private DefaultParser parser;

    public CLIEditorView() throws IOException
    {
        terminal = TerminalBuilder.builder().system(true).build();
        parser = new DefaultParser();
        history = new DefaultHistory(lineReader);
        completer = new CommandCompleter();
        completer.updateCompleter(new WorkingProject());
        lineReader = LineReaderBuilder.builder()
            .terminal(terminal)
            .completer(completer
            .getCompleter())
            .parser(parser)
            .history(history)
            .build();
    }

    /**
     * TODO: Use onUpdate to update the CommandCompleter
     */
    public void onUpdate(Model project, boolean newLoadedProject)
    {
        //updateReaderAndCompleter(project);
    }

    public void onUpdate(ClassObject classObj)
    {
        //updateReaderAndCompleter(classObj);
    }

    /**
     * Displays a message to the user
     * @param message the message to display
     */
    public void alert(String message)
    {
        terminal.writer().println(message);
    } 

    /**
     * Displays a single character to the user
     * Used for displaying the help message
     * @param c The character to be printed
     */
    public void printChar(char c)
    {
        terminal.writer().print(c);
    }

    /**
     * Display a prompt to the user and ask for input
     * @param prompt The String that should be printed before the user's input
     * @return The entered String
     */
    public String readLine(String prompt)
    {
        return lineReader.readLine(prompt);
    }

    /**
     * Update the state of the lineReader and its Completer whenever the model is changed
     */
    public void updateReaderAndCompleter(Model model)
    {
        completer.updateCompleter(model);
        lineReader = LineReaderBuilder.builder()
            .terminal(terminal)
            .completer(completer
            .getCompleter())
            .parser(parser)
            .history(history)
            .build();
    }

    /**
     * Update the state of the lineReader and its Completer whenever a class is changed
     * TODO: 
     */
    /*
    public void updateReaderAndCompleter(ClassObject classObject)
    {
        completer.updateCompleter(classObject);
        lineReader = LineReaderBuilder.builder()
            .terminal(terminal)
            .completer(completer
            .getCompleter())
            .parser(parser)
            .history(history)
            .build();
    }
    */

    /**
     * Print the logo
     */
    public void printLogo()
    {
        alert("  ___  ____  ____  ____  __ _  ____  ____  ____  __  __ _   ___  ____  ____   __   __ _          ");
        alert(" / __)(  _ \\(  __)(  __)(  ( \\/ ___)(_  _)(  _ \\(  )(  ( \\ / __)(  _ \\(  __) / _\\ (  ( \\  ");
        alert("( (_ \\ )   / ) _)  ) _) /    /\\___ \\  )(   )   / )( /    /( (_ \\ ) _ ( ) _) /    \\/    /    ");
        alert(" \\___/(__\\_)(____)(____)\\_)__)(____/ (__) (__\\_)(__)\\_)__) \\___/(____/(____)\\_/\\_/\\_)__)");
    }

    /**
     * Bean it up!
     */

    public void beanItUp()
    {
       alert("                           .                                           ");
       alert("                           *#                                          ");
       alert("                           /(#*                                        ");
       alert("                           (((##                                       ");
       alert("                           /((((##                                     ");
       alert("                           ,#(((((#*                                   ");
       alert("                           /#((//(##                                   ");
       alert("                           ##((///(#.                                  ");
       alert("                           %#(////(#,                                  ");
       alert("                           %#(////(#(                                  ");
       alert("                          .%#(////(#,                                  ");
       alert("                           %#(////(%,                                  ");
       alert("                          *%((////(%                                   ");
       alert("                          ##((////(%                                   ");
       alert("                          %#(/////##                                   ");
       alert("                         .%#(#///##(                                   ");
       alert("                         *%(((/(/(%*                                   ");
       alert("                         /#((&*@*#%                                    ");
       alert("   .,*.*.         ...,*//%#**(**(*/                                    ");
       alert(" ,,*(..*   ,., */,..    ,%#(/(@@##/(((.       ,..                      ");
       alert(" .*,.      ,.           /%((//(((#    ,.,,,.  ,. /*.                   ");
       alert("  *.. .,,.              %#(////(%,      .,*      .,                    ");
       alert("  .*,                  .%#(////(%           .,*. ,                     ");
       alert("                       (%((///(#/                                      ");
       alert("                       %#((/(((%.                                      ");
       alert("                      .%#(((((%(                                       ");
       alert("                      (%#((((#%.                                       ");
       alert("                      %#######(            .,***.                      ");
       alert("                     .%######%.         *#( . .////                    ");
       alert("                     .%%#%%%%(   ,    ,,(#/ ./*////.                   ");
       alert("                      %%%%%%(*,*,,*//*((#,.///////                     ");
       alert("                     ,&%%%,     .*/#(###,.(/(///,                      ");
       alert("                         ,*      .#####,.(((((/                        ");
       alert("                          *         (#,,(((((.                         ");
       alert("                        ..*           ,(((#.                           ");
       alert("                      ,(,*###(.                                        ");
       alert("                     ,/((*****/*,.                                     ");
       alert("                    ,#*###,***//*.                                     ");
       alert("                    */#####,*,,*(.                                     ");
       alert("                     .**#######%###                                    ");
       alert("                      * ,..        *                                   ");
       alert("                         *,.. ...*                                     ");
    }
}