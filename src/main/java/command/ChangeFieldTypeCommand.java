package command;

import model.WorkingProject;

public class ChangeFieldTypeCommand extends Command {
    private WorkingProject project;
    private String className;
    private String fieldName;
    private String newFieldType;

    public ChangeFieldTypeCommand (WorkingProject project, String className, String fieldName, String newFieldType)
    {
        super();
        this.project = project;
        this.className = className;
        this.fieldName = fieldName;
        this.newFieldType = newFieldType;
    }

    public void execute()
    {
        updateStatus(project.changeFieldType(className, fieldName, newFieldType));
    }
}
