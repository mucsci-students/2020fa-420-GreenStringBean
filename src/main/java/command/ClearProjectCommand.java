package command;

import java.util.Set;

import model.Model;

public class ClearProjectCommand extends Command {

    public ClearProjectCommand (Model project)
    {
        super(project);
    }

    public void onExecute()
    {
        project.clearProject();
        // This command can't fail
        updateStatus(0);
    }
}
