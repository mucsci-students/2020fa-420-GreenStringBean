package command;

import model.Model;

public class ChangeFieldTypeCommand extends Command {
    private String className;
    private String fieldName;
    private String newFieldType;

    public ChangeFieldTypeCommand (Model project, String className, String fieldName, String newFieldType)
    {
        super(project);
        this.className = className;
        this.fieldName = fieldName;
        this.newFieldType = newFieldType;
    }

    public void onExecute()
    {
        updateStatus(project.changeFieldType(className, fieldName, newFieldType));
    }
}
