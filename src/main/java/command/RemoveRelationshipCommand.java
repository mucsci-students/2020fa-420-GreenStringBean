package command;

import model.WorkingProject;

public class RemoveRelationshipCommand extends Command {
    private WorkingProject project;
    private String classNameFrom;
    private String classNameTo;

    public RemoveRelationshipCommand (WorkingProject project, String classNameFrom, String classNameTo)
    {
        super();
        this.project = project;
        this.classNameFrom = classNameFrom;
        this.classNameTo = classNameTo;
    }

    public void execute()
    {
        updateStatus(project.removeRelationship(classNameFrom, classNameTo));
    }
}
