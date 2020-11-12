package controller;

import java.util.ArrayDeque;

import view.Observer;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import command.*;
import model.Model;
import model.ClassObject;

public class WorkingProjectEditor implements ModelEditor{
    private List<Observer> observers;
    
    private Model project;
    private boolean lastCommandStatus;
    private String lastCommandStatusMsg;

    private ArrayDeque<Command> executedCommands;
    private ArrayDeque<Command> undoneCommands;

    private static final int MAX_COMMAND_HISTORY = 50;

    public WorkingProjectEditor(Model model)
    {
        project = model;
        lastCommandStatus = false;
        lastCommandStatusMsg = "No commands yet";

        executedCommands = new ArrayDeque<>();
        undoneCommands = new ArrayDeque<>();

        observers = new ArrayList<>();
    }

    /**
     * Attach an observer to the project
     * @param observer the observer to be used by the project
     */
    public void attach (Observer observer)
    {
        observers.add(observer);
        Set<String> classNames = project.getClassNames();
        for(String className : classNames)
        {
            project.getClass(className).attach(observer);
        }
    }

    /**
     * Detach an observer from the project
     * @param observer the observer to be detached from the project
     */
    public void detach (Observer observer)
    {
        observers.remove(observer);
        Set<String> classNames = project.getClassNames();
        for(String className : classNames)
        {
            project.getClass(className).detach(observer);
        }
    }

    /**
     * Let the Observer know each time the project updates
     */
    private void notifyAllObservers(boolean load)
    {
        observers.forEach(o -> o.onUpdate(getProjectSnapshot(), load));
    }

    /**
     * Return status of most recently executed command
     * @return true if successful, false otherwise
     */
    public boolean getLastCommandStatus()
    {
        return lastCommandStatus;
    }

    /**
     * Return status message of most recently executed command
     * @return status message of the most recently executed command
     */
    public String getLastCommandStatusMessage()
    {
        return lastCommandStatusMsg;
    }

    /**
     * Creates a copy of the current state of the Model
     * @return copy of the model
     */
    public Model getProjectSnapshot()
    {
        return project.copy();
    }

    /**
     * Adds a new class to the project. Notifies observers with the project.
     * @param className the name to be used by the new class
     */
    public void addClass(String className)
    {
        Command cmd = new AddClassCommand(project, className);
        executeProjectCommand(cmd);
        ClassObject addedClass = project.getClass(className);

        if(cmd.getStatus())
            observers.forEach(o -> addedClass.attach(o));
    }

    /**
     * Removes a class from the project, if it exists. Also removes any
     * relationships the class was part of.
     * Notifies Observers with the project.
     * @param className the name of the class to remove
     */
    public void removeClass(String className)
    {
        ClassObject removedClass = project.getClass(className);
        Command cmd = new RemoveClassCommand(project, className);
        executeProjectCommand(cmd);

        if(cmd.getStatus())
            observers.forEach(o -> removedClass.detach(o));
    }

    /**
     * Renames a class, if it exists. (Preserves order)
     * Notifies observers with the project.
     * @param oldClassName the current name of the class to rename
     * @param newClassName the new name to give to the class
     */
    public void renameClass(String oldClassName, String newClassName)
    {
        Command cmd = new RenameClassCommand(project, oldClassName, newClassName);
        executeProjectCommand(cmd);
    }

    /**
     * Opens a class, if it exists.
     * @param className the name of the class to open
     */
    public void openClass(String className)
    {
        Command cmd = new OpenClassCommand(project, className);
        executeCommand(cmd);
    }

    /**
     * Load a project
     * Notifies observers with the project.
     * @param jsonString json representation of the project
     */
    public void loadProject(String jsonString)
    {
        for (String className : project.getClassNames())
        {
            ClassObject classObj = project.getClass(className);
            observers.forEach(o -> classObj.detach(o));
        }
        Command cmd = new LoadProjectCommand(project, jsonString);
        executeProjectCommand(cmd);
        for (String className : project.getClassNames())
        {
            ClassObject classObj = project.getClass(className);
            observers.forEach(o -> classObj.attach(o));
        }
    }

    /**
     * Closes a class, if it exists.
     * @param className the name of the class to close
     */
    public void closeClass(String className)
    {
        Command cmd = new CloseClassCommand(project, className);
        executeCommand(cmd);
    }

    /**
     * Adds a new field to a class.
     * @param className the name of the class to add a field to
     * @param fieldName the name to be used by the new field
     * @param fieldType the data type to be used by the new field
     */
    public void addField(String className, String fieldName, String fieldType, String fieldVisName)
    {
        Command cmd = new AddFieldCommand(project, className, fieldName, fieldType, fieldVisName);
        executeCommand(cmd);
    }

     /**
     * Removes a field from a class, if it exists.
     * @param className the name of the class to remove a field from
     * @param fieldName the name of the field to remove
     */
    public void removeField(String className, String fieldName)
    {
        Command cmd = new RemoveFieldCommand(project, className, fieldName);
        executeCommand(cmd);
    }

    /**
     * Renames a field, if it exists.
     * @param className    the name of the class with the field to rename
     * @param oldFieldName the current name of the field to rename
     * @param newFieldName the new name to give to the field
     */
    public void renameField(String className, String oldFieldName, String newFieldName)
    {
        Command cmd = new RenameFieldCommand(project, className, oldFieldName, newFieldName);
        executeCommand(cmd);
    }

    /**
     * Changes the data type of a field, if it exists.
     * @param className    the name of the class with the field to modify
     * @param fieldName    the name of the field to change the data type of
     * @param newFieldType the new data type to give to the field
     */
    public void changeFieldType(String className, String fieldName, String newFieldType)
    {
        Command cmd = new ChangeFieldTypeCommand(project, className, fieldName, newFieldType);
        executeCommand(cmd);
    }

    /**
     * Changes the visibility of a field, if it exists.
     * @param className    the name of the class with the field to modify
     * @param fieldName    the name of hte field to change the visibility of
     * @param fieldVisName the new visibility to give to the field
     */
    public void changeFieldVisibility(String className, String fieldName, String newFieldVis)
    {
        Command cmd = new ChangeFieldVisibilityCommand(project, className, fieldName, newFieldVis);
        executeCommand(cmd);
    }

    /**
     * Adds a new method to a class.
     * @param className  the name of the class to add a method to
     * @param methodName the name to be used by the new method
     * @param methodType the data type to be used by the new method
     */
    public void addMethod(String className, String methodName, String methodType, String methodVis)
    {
        Command cmd = new AddMethodCommand(project, className, methodName, methodType, methodVis);
        executeCommand(cmd);
    }

    /**
     * Removes a method from a class, if it exists.
     * @param className  the name of the class to remove a method from
     * @param methodName the name of the method to remove
     */
    public void removeMethod(String className, String methodName)
    {
        Command cmd = new RemoveMethodCommand(project, className, methodName);
        executeCommand(cmd);
    }

     /**
     * Renames a method, if it exists.
     * @param className     the name of the class with the method to rename
     * @param oldMethodName the current name of the method to rename
     * @param newMethodName the new name to give to the method
     */
    public void renameMethod(String className, String oldMethodName, String newMethodName)
    {
        Command cmd = new RenameMethodCommand(project, className, oldMethodName, newMethodName);
        executeCommand(cmd);
    }

    /**
     * Changes the return type of a method, if it exists.
     * @param className     the name of the class with the method to modify
     * @param methodName    the name of the method to change the return type of
     * @param newMethodType the new return type to give to the method
     */
    public void changeMethodType(String className, String methodName, String newMethodType)
    {
        Command cmd = new ChangeMethodTypeCommand(project, className, methodName, newMethodType);
        executeCommand(cmd);
    }

    /**
     * Changes the visibility of a method, if it exists.
     * @param className     the name of the class with the method to modify
     * @param methodName    the name of the method to change the visibility of
     * @param methodVisName the new visibility to give to the method
     */
    public void changeMethodVisibility(String className, String methodName, String newMethodVis)
    {
        Command cmd = new ChangeMethodVisibilityCommand(project, className, methodName, newMethodVis);
        executeCommand(cmd);
    }

    /**
     * Adds a new parameter to a method.
     * @param className  the name of the class with the method to modify
     * @param methodName the name of the method to add a parameter to
     * @param paramName  the name to be used by the new parameter
     * @param paramType  the data type to be used by the new parameter
     */
    public void addParameter(String className, String methodName, String paramName, String paramType)
    {
        Command cmd = new AddParameterCommand(project, className, methodName, paramName, paramType);
        executeCommand(cmd);
    }

    /**
     * Removes a parameter from a method, if it exists.
     * @param className  the name of the class with the method to modify
     * @param methodName the name of the method to remove a parameter from
     * @param paramName  the name of the parameter to remove
     */
    public void removeParameter(String className, String methodName, String paramName)
    {
        Command cmd = new RemoveParameterCommand(project, className, methodName, paramName);
        executeCommand(cmd);
    }

    /**
     * Renames a parameter, if it exists.
     * @param className    the name of the class with the method to modify
     * @param methodName   the name of the method with the parameter to rename
     * @param oldParamName the current name of the parameter to rename
     * @param newParamName the new name to give to the parameter
     */
    public void renameParameter(String className, String methodName, String oldParamName, String newParamName)
    {
        Command cmd = new RenameParameterCommand(project, className, methodName, oldParamName, newParamName);
        executeCommand(cmd);
    }

    /**
     * Changes the data type of a parameter, if it exists.
     * @param className    the name of the class with the method to modify
     * @param methodName   the name of the method with the parameter to modify
     * @param paramName    the name of the parameter to change the data type of
     * @param newParamType the new data type to give to the parameter
     */
    public void changeParameterType(String className, String methodName, String paramName, String newParamType)
    {
        Command cmd = new ChangeParameterTypeCommand(project, className, methodName, paramName, newParamType);
        executeCommand(cmd);
    }

    /**
     * Adds a new ordered relationship to the project.
     * Notifies observers with the project.
     * @param classNameFrom the name of the class the relationship starts at
     * @param classNameTo   the name of the class the relationship goes to
     * @param typeName      the first letter of the relationship's type
     */
    public void addRelationship(String classNameFrom, String classNameTo, String typeName)
    {
        Command cmd = new AddRelationshipCommand(project, classNameFrom, classNameTo, typeName);
        executeProjectCommand(cmd);
    }

    /**
     * Removes a relationship from the project, if it exists.
     * Notifies observers with the project.
     * @param classNameFrom the name of the class the relationship starts at
     * @param classNameTo   the name of the class the relationship goes to
     */
    public void removeRelationship(String classNameFrom, String classNameTo)
    {
        Command cmd = new RemoveRelationshipCommand(project, classNameFrom, classNameTo);
        executeProjectCommand(cmd);
    }

    /**
     * Changes the type of a relationship, if it exists.
     * Notifies observers with the project.
     * @param classNameFrom the name of the class the relationship starts at
     * @param classNameTo   the name of the class the relationship goes to
     * @param newTypeName   the first letter of the relationship's new type
     */
    public void changeRelationshipType(String classNameFrom, String classNameTo, String newTypeName)
    {
        Command cmd = new ChangeRelationshipTypeCommand(project, classNameFrom, classNameTo, newTypeName);
        executeProjectCommand(cmd);
    }

    /**
     * executes the command cmd
     * @param cmd
     */
    private void executeCommand(Command cmd)
    {
        undoneCommands.clear();

        cmd.execute();
        if (cmd.getStatus())
        {
            if (executedCommands.size() == MAX_COMMAND_HISTORY)
            {
                executedCommands.removeLast();
            }
            executedCommands.push(cmd);
        }
        lastCommandStatus = cmd.getStatus();
        lastCommandStatusMsg = cmd.getStatusMessage();
    }

    /**
     * executes specifically a project command, cmd
     * @param cmd
     */
    private void executeProjectCommand(Command cmd)
    {
        executeCommand(cmd);
        if(cmd.getStatus())
        {
            notifyAllObservers(cmd instanceof LoadProjectCommand);
        }
    }

    /**
     * Encodes the current project as a JSON string.
     * @return a JSON encoding of the project
     */
    public String toJSONString()
    {
        return project.toJSONString();
    }

    /**
     * Check if there are any commands that can be undone
     * @return true if there is >=1 command to undo, false otherwise
     */
    public boolean canUndo()
    {
        return !executedCommands.isEmpty();
    }

    /**
     * Undo the last undoable command
     */
    public void undo()
    {
        if(canUndo())
        {
            Command cmd = executedCommands.pop();
            cmd.undo();
            undoneCommands.push(cmd);
            notifyAllObservers(cmd instanceof LoadProjectCommand);
        }
    }

    /**
     * Check if there are any commands that can be redone
     * @return true if there is >=1 command to redo, false otherwise
     */
    public boolean canRedo()
    {
        return !undoneCommands.isEmpty();
    }

    /**
     * Redo the last undone command
     */
    public void redo()
    {
        if(canRedo())
        {
            Command cmd = undoneCommands.pop();
            cmd.execute();
            executedCommands.push(cmd);
            notifyAllObservers(cmd instanceof LoadProjectCommand);
        }
    }

}
