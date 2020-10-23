package command;

import model.WorkingProject;

public class RemoveFieldCommand extends Command {
    private String className;
    private String fieldName;

    public RemoveFieldCommand (WorkingProject project, String className, String fieldName)
    {
        super(project);
        this.className = className;
        this.fieldName = fieldName;
    }

    public void onExecute()
    {
        updateStatus(project.removeField(className, fieldName));
    }
}
