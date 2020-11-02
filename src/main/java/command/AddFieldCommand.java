package command;

import model.WorkingProject;

public class AddFieldCommand extends Command {
    
    private String className;
    private String fieldName;
    private String fieldType;
    private String fieldVisName;

    public AddFieldCommand (WorkingProject project, String className, String fieldName, String fieldType, String fieldVisName)
    {
        super(project);
        this.className = className;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.fieldVisName = fieldVisName;
    }

    public void onExecute()
    {
        updateStatus(project.addField(className, fieldName, fieldType, fieldVisName));
    }
}
