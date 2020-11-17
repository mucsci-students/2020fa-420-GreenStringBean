package command;

import model.Model;

public class RemoveFieldCommand extends Command {
    private String className;
    private String fieldName;

    public RemoveFieldCommand (Model project, String className, String fieldName)
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
