package command;

import model.WorkingProject;

public class AddFieldCommand extends Command {
    
    private String className;
    private String fieldName;
    private String fieldType;

    public AddFieldCommand (WorkingProject project, String className, String fieldName, String fieldType)
    {
        super(project);
        this.className = className;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    public void onExecute()
    {
        updateStatus(project.addField(className, fieldName, fieldType));
    }
}
