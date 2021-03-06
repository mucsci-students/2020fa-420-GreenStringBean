package command;

import model.Model;

public class RenameFieldCommand extends Command {
    private String className;
    private String oldFieldName;
    private String newFieldName;

    public RenameFieldCommand (Model project, String className, String oldFieldName, String newFieldName)
    {
        super(project);
        this.className = className;
        this.oldFieldName = oldFieldName;
        this.newFieldName = newFieldName;
    }

    public void onExecute()
    {
        updateStatus(project.renameField(className, oldFieldName, newFieldName));
    }
}
