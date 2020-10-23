package command;

import model.WorkingProject;

public class LoadProjectCommand extends Command {
    private String jsonString;

    public LoadProjectCommand (WorkingProject project, String jsonString)
    {
        super(project);
        this.jsonString = jsonString;
    }

    public void onExecute()
    {
        updateStatus(project.loadFromJSON(jsonString));
    }
}
