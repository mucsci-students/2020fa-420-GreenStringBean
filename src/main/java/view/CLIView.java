package view;
import model.Model;

public interface CLIView extends View
{
    /**
     * Print the given character
     * @param c The character to be printed
     */
    void printChar(char c);

    /**
     * Prompt the user for input
     * @param prompt The prompt asking the user for new input
     * @return A String of input
     */
    String readLine(String prompt);

    /**
     * Update the state of the lineReader and its Completer whenever the model is changed
     */
    void updateReaderAndCompleter(Model model);
            
}