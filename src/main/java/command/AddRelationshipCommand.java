package command;

import model.Model;

public class AddRelationshipCommand extends Command {
    private String classNameFrom;
    private String classNameTo;
    private String typeName;

    public AddRelationshipCommand (Model project, String classNameFrom, String classNameTo, String typeName)
    {
        super(project);
        this.classNameFrom = classNameFrom;
        this.classNameTo = classNameTo;
        this.typeName = typeName;
    }

    public void onExecute()
    {
        updateStatus(project.addRelationship(classNameFrom, classNameTo, typeName));
    }
}
