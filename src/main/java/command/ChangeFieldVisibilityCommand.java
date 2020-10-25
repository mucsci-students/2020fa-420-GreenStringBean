package command;

import model.WorkingProject;

public class ChangeFieldVisibilityCommand extends Command {
    private String className;
    private String fieldName;
    private String newFieldVisName;

    public ChangeFieldVisibilityCommand (WorkingProject project, String className, String fieldName, String newFieldVisName )
    {
        super(project);
        this.className = className;
        this.fieldName = fieldName;
        this.newFieldVisName = newFieldVisName;
    }

    public void onExecute()
    {
        updateStatus(project.changeFieldVisibility(className, fieldName, newFieldVisName));
    }
}
