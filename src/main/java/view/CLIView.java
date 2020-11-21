package view;

import model.ClassObject;
import model.Model;

import java.io.IOException;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class CLIView implements View {

    private Terminal terminal;

    public CLIView() throws IOException
    {
        terminal = TerminalBuilder.builder().system(true).build();
    }

    //Move commandCompleter here and update it here
    public void onUpdate(Model project, boolean newLoadedProject)
    {
        terminal.writer().println("temp");
    }

    public void onUpdate(ClassObject classObj)
    {
        terminal.writer().println("temp");
    }
    /**
     * Displays a message to the user
     * @param message the message to display
     */
    public void alert(String message)
    {
        terminal.writer().println(message);
    }

    public void printChar(char c)
    {
        terminal.writer().print(c);
    }

    public Terminal getTerminal()
    {
        return terminal;
    }
}