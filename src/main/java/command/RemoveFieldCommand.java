package command;

import model.WorkingProject;

public class RemoveFieldCommand extends Command {
    private WorkingProject project;
    private String className;
    private String fieldName;

    public RemoveFieldCommand (WorkingProject project, String className, String fieldName)
    {
        super();
        this.project = project;
        this.className = className;
        this.fieldName = fieldName;
    }

    public void execute()
    {
        updateStatus(project.removeField(className, fieldName));
    }
}
