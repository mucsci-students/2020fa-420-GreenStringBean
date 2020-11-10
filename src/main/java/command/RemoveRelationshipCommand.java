package command;

import model.Model;

public class RemoveRelationshipCommand extends Command {
    private String classNameFrom;
    private String classNameTo;

    public RemoveRelationshipCommand (Model project, String classNameFrom, String classNameTo)
    {
        super(project);
        this.classNameFrom = classNameFrom;
        this.classNameTo = classNameTo;
    }

    public void onExecute()
    {
        updateStatus(project.removeRelationship(classNameFrom, classNameTo));
    }
}
