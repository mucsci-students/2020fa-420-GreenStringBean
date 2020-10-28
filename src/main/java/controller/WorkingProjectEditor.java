package controller;

import java.util.ArrayDeque;

import view.Observer;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import command.*;
import model.WorkingProject;
import model.ClassObject;

public class WorkingProjectEditor {
    private List<Observer> observers;
    
    private WorkingProject project;
    private boolean lastCommandStatus;
    private String lastCommandStatusMsg;

    private ArrayDeque<Command> executedCommands;
    private ArrayDeque<Command> undoneCommands;

    private static final int MAX_COMMAND_HISTORY = 50;

    public WorkingProjectEditor()
    {
        project = new WorkingProject();
        lastCommandStatus = false;
        lastCommandStatusMsg = "No commands yet";

        executedCommands = new ArrayDeque<>();
        undoneCommands = new ArrayDeque<>();

        observers = new ArrayList<>();
    }

    public void attach (Observer observer)
    {
        observers.add(observer);
        Set<String> classNames = project.getClassNames();
        for(String className : classNames)
        {
            project.getClass(className).attach(observer);
        }
    }

    public void detach (Observer observer)
    {
        observers.remove(observer);
        Set<String> classNames = project.getClassNames();
        for(String className : classNames)
        {
            project.getClass(className).detach(observer);
        }
    }

    private void notifyAllObservers()
    {
        observers.forEach(o -> o.onUpdate(getProjectSnapshot()));
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
        executeProjectCommand(cmd);
        ClassObject addedClass = project.getClass(className);

        if(cmd.getStatus())
            observers.forEach(o -> addedClass.attach(o));

    }

    public void removeClass(String className)
    {
        ClassObject removedClass = project.getClass(className);
        Command cmd = new RemoveClassCommand(project, className);
        executeProjectCommand(cmd);

        if(cmd.getStatus())
            observers.forEach(o -> removedClass.detach(o));
    }

    public void renameClass(String oldClassName, String newClassName)
    {
        Command cmd = new RenameClassCommand(project, oldClassName, newClassName);
        executeProjectCommand(cmd);
    }

    public void openClass(String className)
    {
        Command cmd = new OpenClassCommand(project, className);
        executeCommand(cmd);
    }

    public void loadProject(String jsonString)
    {
        Command cmd = new LoadProjectCommand(project, jsonString);
        executeProjectCommand(cmd);
    }

    public void closeClass(String className)
    {
        Command cmd = new CloseClassCommand(project, className);
        executeCommand(cmd);
    }

    public void addField(String className, String fieldName, String fieldType, String fieldVisName)
    {
        Command cmd = new AddFieldCommand(project, className, fieldName, fieldType, fieldVisName);
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

    public void changeFieldVisibility(String className, String fieldName, String newFieldVis)
    {
        Command cmd = new ChangeFieldVisibilityCommand(project, className, fieldName, newFieldVis);
        executeCommand(cmd);
    }

    public void addMethod(String className, String methodName, String methodType, String methodVis)
    {
        Command cmd = new AddMethodCommand(project, className, methodName, methodType, methodVis);
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

    public void changeMethodVisibility(String className, String methodName, String newMethodVis)
    {
        Command cmd = new ChangeMethodVisibilityCommand(project, className, methodName, newMethodVis);
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
        executeProjectCommand(cmd);
    }

    public void removeRelationship(String classNameFrom, String classNameTo)
    {
        Command cmd = new RemoveRelationshipCommand(project, classNameFrom, classNameTo);
        executeProjectCommand(cmd);
    }

    public void changeRelationshipType(String classNameFrom, String classNameTo, String newTypeName)
    {
        Command cmd = new ChangeRelationshipTypeCommand(project, classNameFrom, classNameTo, newTypeName);
        executeProjectCommand(cmd);
    }

    private void executeCommand(Command cmd)
    {
        undoneCommands.clear();

        cmd.execute();
        if (cmd.getStatus())
        {
            if (executedCommands.size() == MAX_COMMAND_HISTORY)
            {
                executedCommands.remove();
            }
            executedCommands.push(cmd);

            notifyAllObservers();
        }
        lastCommandStatus = cmd.getStatus();
        lastCommandStatusMsg = cmd.getStatusMessage();
    }

    private void executeProjectCommand(Command cmd)
    {
        executeCommand(cmd);
        if(cmd.getStatus())
            notifyAllObservers();
    }

    public String toJSONString()
    {
        return project.toJSONString();
    }

    public boolean canUndo()
    {
        return executedCommands.size()>0;
    }

    public void undo()
    {
        if(canUndo())
        {
            Command cmd = executedCommands.pop();
            cmd.undo();
            undoneCommands.push(cmd);
        }
    }

    public boolean canRedo()
    {
        return undoneCommands.size()>0;
    }

    public void redo()
    {
        if(canRedo())
        {
            Command cmd = undoneCommands.pop();
            cmd.execute();
            executedCommands.push(cmd);
        }
    }

}
