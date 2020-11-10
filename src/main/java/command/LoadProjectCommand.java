package command;

import model.Model;

public class LoadProjectCommand extends Command {
    private String jsonString;

    public LoadProjectCommand (Model project, String jsonString)
    {
        super(project);
        this.jsonString = jsonString;
    }

    public void onExecute()
    {
        updateStatus(project.loadFromJSON(jsonString));
    }
}
