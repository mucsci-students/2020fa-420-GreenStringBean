package command;

import model.WorkingProject;

public class ChangeRelationshipTypeCommand extends Command {
    private WorkingProject project;
    private String classNameFrom;
    private String classNameTo;
    private String newTypeName;

    public ChangeRelationshipTypeCommand (WorkingProject project, String classNameFrom, String classNameTo, String newTypeName)
    {
        super();
        this.project = project;
        this.classNameFrom = classNameFrom;
        this.classNameTo = classNameTo;
        this.newTypeName = newTypeName;
    }

    public void execute()
    {
        updateStatus(project.changeRelationshipType(classNameFrom, classNameTo, newTypeName));
    }
}
