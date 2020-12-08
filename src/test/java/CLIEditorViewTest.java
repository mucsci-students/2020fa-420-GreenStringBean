import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import view.CLIEditorView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class CLIEditorViewTest {
    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private PrintStream originalOutput = System.out;
    //private Terminal terminal;
    private CLIEditorView view;

    @Before
    public void setup() throws IOException
    {
        System.setOut(new PrintStream(output));
        view = new CLIEditorView();
    }

    @After
    public void tearDownStream()
    {
        System.setOut(originalOutput);
    }

    @Ignore
    public void beanItUpTest() throws IOException
    {
        view.beanItUp();
        String expected = "";
        expected += "                           .                                            \n";
        expected += "                           *#                                           \n";
        expected += "                           /(#*                                         \n";
        expected += "                           (((##                                        \n";
        expected += "                           /((((##                                      \n";
        expected += "                           ,#(((((#*                                    \n";
        expected += "                           /#((//(##                                    \n";
        expected += "                           ##((///(#.                                   \n";
        expected += "                           %#(////(#,                                   \n";
        expected += "                           %#(////(#(                                   \n";
        expected += "                          .%#(////(#,                                   \n";
        expected += "                           %#(////(%,                                   \n";
        expected += "                          *%((////(%                                    \n";
        expected += "                          ##((////(%                                    \n";
        expected += "                          %#(/////##                                    \n";
        expected += "                         .%#(#///##(                                    \n";
        expected += "                         *%(((/(/(%*                                    \n";
        expected += "                         /#((&*@*#%                                     \n";
        expected += "   .,*.*.         ...,*//%#**(**(*/                                     \n";
        expected += " ,,*(..*   ,., */,..    ,%#(/(@@##/(((.       ,..                       \n";
        expected += " .*,.      ,.           /%((//(((#    ,.,,,.  ,. /*.                    \n";
        expected += "  *.. .,,.              %#(////(%,      .,*      .,                     \n";
        expected += "  .*,                  .%#(////(%           .,*. ,                      \n";
        expected += "                       (%((///(#/                                       \n";
        expected += "                       %#((/(((%.                                       \n";
        expected += "                      .%#(((((%(                                        \n";
        expected += "                      (%#((((#%.                                        \n";
        expected += "                      %#######(            .,***.                       \n";
        expected += "                     .%######%.         *#( . .////                     \n";
        expected += "                     .%%#%%%%(   ,    ,,(#/ ./*////.                    \n";
        expected += "                      %%%%%%(*,*,,*//*((#,.///////                      \n";
        expected += "                     ,&%%%,     .*/#(###,.(/(///,                       \n";
        expected += "                         ,*      .#####,.(((((/                         \n";
        expected += "                          *         (#,,(((((.                          \n";
        expected += "                        ..*           ,(((#.                            \n";
        expected += "                      ,(,*###(.                                         \n";
        expected += "                     ,/((*****/*,.                                      \n";
        expected += "                    ,#*###,***//*.                                      \n";
        expected += "                    */#####,*,,*(.                                      \n";
        expected += "                     .**#######%###                                     \n";
        expected += "                      * ,..        *                                    \n";
        expected += "                         *,.. ...*                                      \n";
        assertEquals(output.toString(), expected);
    }

    @Ignore
    public void printLogoTest() throws IOException
    {
        view.printLogo();
        String expected = "";
        expected += "  ___  ____  ____  ____  __ _  ____  ____  ____  __  __ _   ___  ____  ____   __   __ _          \n";
        expected += " / __)(  _ \\(  __)(  __)(  ( \\/ ___)(_  _)(  _ \\(  )(  ( \\ / __)(  _ \\(  __) / _\\ (  ( \\  \n";
        expected += "( (_ \\ )   / ) _)  ) _) /    /\\___ \\  )(   )   / )( /    /( (_ \\ ) _ ( ) _) /    \\/    /    \n";
        expected += " \\___/(__\\_)(____)(____)\\_)__)(____/ (__) (__\\_)(__)\\_)__) \\___/(____/(____)\\_/\\_/\\_)__)\n";
        assertEquals(output.toString(), expected);
    }

}