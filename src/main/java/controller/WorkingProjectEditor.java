package controller;

import java.util.ArrayDeque;

import command.*;
import model.WorkingProject;

public class WorkingProjectEditor {
    private WorkingProject project;
    private boolean lastCommandStatus;
    private String lastCommandStatusMsg;

    private ArrayDeque<Command> executedCommands;

    private static final int MAX_COMMAND_HISTORY = 50;

    public WorkingProjectEditor()
    {
        project = new WorkingProject();
        lastCommandStatus = false;
        lastCommandStatusMsg = "No commands yet";

        executedCommands = new ArrayDeque<>();
    }

    public boolean getLastCommandStatus()
    {
        return lastCommandStatus;
    }

    public String getLastCommandStatusMessage()
    {
        return lastCommandStatusMsg;
    }

    public WorkingProject getProjectSnapshot()
    {
        return project.copy();
    }

    public void addClass(String className)
    {
        Command cmd = new AddClassCommand(project, className);
        executeCommand(cmd);
    }

    public void removeClass(String className)
    {
        Command cmd = new RemoveClassCommand(project, className);
        executeCommand(cmd);
    }

    public void renameClass(String oldClassName, String newClassName)
    {
        Command cmd = new RenameClassCommand(project, oldClassName, newClassName);
        executeCommand(cmd);
    }

    public void openClass(String className)
    {
        Command cmd = new OpenClassCommand(project, className);
        executeCommand(cmd);
    }

    public void closeClass(String className)
    {
        Command cmd = new CloseClassCommand(project, className);
        executeCommand(cmd);
    }

    public void addField(String className, String fieldName, String fieldType)
    {
        Command cmd = new AddFieldCommand(project, className, fieldName, fieldType);
        executeCommand(cmd);
    }

    public void removeField(String className, String fieldName)
    {
        Command cmd = new RemoveFieldCommand(project, className, fieldName);
        executeCommand(cmd);
    }

    public void renameField(String className, String oldFieldName, String newFieldName)
    {
        Command cmd = new RenameFieldCommand(project, className, oldFieldName, newFieldName);
        executeCommand(cmd);
    }

    public void changeFieldType(String className, String fieldName, String newFieldType)
    {
        Command cmd = new ChangeFieldTypeCommand(project, className, fieldName, newFieldType);
        executeCommand(cmd);
    }

    public void addMethod(String className, String methodName, String methodType)
    {
        Command cmd = new AddMethodCommand(project, className, methodName, methodType);
        executeCommand(cmd);
    }

    public void removeMethod(String className, String methodName)
    {
        Command cmd = new RemoveMethodCommand(project, className, methodName);
        executeCommand(cmd);
    }

    public void renameMethod(String className, String oldMethodName, String newMethodName)
    {
        Command cmd = new RenameMethodCommand(project, className, oldMethodName, newMethodName);
        executeCommand(cmd);
    }

    public void changeMethodType(String className, String methodName, String newMethodType)
    {
        Command cmd = new ChangeMethodTypeCommand(project, className, methodName, newMethodType);
        executeCommand(cmd);
    }

    public void addParameter(String className, String methodName, String paramName, String paramType)
    {
        Command cmd = new AddParameterCommand(project, className, methodName, paramName, paramType);
        executeCommand(cmd);
    }

    public void removeParameter(String className, String methodName, String paramName)
    {
        Command cmd = new RemoveParameterCommand(project, className, methodName, paramName);
        executeCommand(cmd);
    }

    public void renameParameter(String className, String methodName, String oldParamName, String newParamName)
    {
        Command cmd = new RenameParameterCommand(project, className, methodName, oldParamName, newParamName);
        executeCommand(cmd);
    }

    public void changeParameterType(String className, String methodName, String paramName, String newParamType)
    {
        Command cmd = new ChangeParameterTypeCommand(project, className, methodName, paramName, newParamType);
        executeCommand(cmd);
    }

    public void addRelationship(String classNameFrom, String classNameTo, String typeName)
    {
        Command cmd = new AddRelationshipCommand(project, classNameFrom, classNameTo, typeName);
        executeCommand(cmd);
    }

    public void removeRelationship(String classNameFrom, String classNameTo)
    {
        Command cmd = new RemoveRelationshipCommand(project, classNameFrom, classNameTo);
        executeCommand(cmd);
    }

    public void changeRelationshipType(String classNameFrom, String classNameTo, String newTypeName)
    {
        Command cmd = new ChangeRelationshipTypeCommand(project, classNameFrom, classNameTo, newTypeName);
        executeCommand(cmd);
    }

    private void executeCommand(Command cmd)
    {
        cmd.execute();
        if (cmd.getStatus())
        {
            if (executedCommands.size() == MAX_COMMAND_HISTORY)
            {
                executedCommands.remove();
            }
            executedCommands.push(cmd);
        }
        lastCommandStatus = cmd.getStatus();
        lastCommandStatusMsg = cmd.getStatusMessage();
    }
}
