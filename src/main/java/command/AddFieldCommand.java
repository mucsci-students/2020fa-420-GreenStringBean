package command;

import model.WorkingProject;

public class AddFieldCommand extends Command {
    private WorkingProject project;
    private String className;
    private String fieldName;
    private String fieldType;

    public AddFieldCommand (WorkingProject project, String className, String fieldName, String fieldType)
    {
        super();
        this.project = project;
        this.className = className;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    public void execute()
    {
        updateStatus(project.addField(className, fieldName, fieldType));
    }
}
