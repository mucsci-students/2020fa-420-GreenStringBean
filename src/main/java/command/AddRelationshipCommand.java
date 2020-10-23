package command;

import model.WorkingProject;

public class AddRelationshipCommand extends Command {
    private WorkingProject project;
    private String classNameFrom;
    private String classNameTo;
    private String typeName;

    public AddRelationshipCommand (WorkingProject project, String classNameFrom, String classNameTo, String typeName)
    {
        super();
        this.project = project;
        this.classNameFrom = classNameFrom;
        this.classNameTo = classNameTo;
        this.typeName = typeName;
    }

    public void execute()
    {
        updateStatus(project.addRelationship(classNameFrom, classNameTo, typeName));
    }
}
