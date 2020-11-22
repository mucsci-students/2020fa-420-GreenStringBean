package controller; 

import model.Model;
import view.*;

public class CLIEditorController implements CLIController
{

    private ModelEditor modelEditor;
    private CLIView cli;

    public CLIEditorController (CLIView cli, ModelEditor modelEditor)
    {
        this.cli = cli;
        this.modelEditor = modelEditor;
        modelEditor.attach(this.cli);
    }

     /**
     * Creates a copy of the current state of the Model
     * @return copy of the model
     */
    public Model getProjectSnapshot()
    {
        return modelEditor.getProjectSnapshot();
    }

    /**
     * Adds a new class to the project. Notifies observers with the project.
     * @param className the name to be used by the new class
     */
    public void addClass(String className)
    {
        modelEditor.addClass(className);
        checkStatus();
        printStatusMessage();
        cli.updateReaderAndCompleter(getProjectSnapshot());
    }

    /**
     * Removes a class from the project, if it exists. Also removes any
     * relationships the class was part of.
     * Notifies Observers with the project.
     * @param className the name of the class to remove
     */
    public void removeClass(String className)
    {
        modelEditor.removeClass(className);
        checkStatus();
        printStatusMessage();
        cli.updateReaderAndCompleter(getProjectSnapshot());
    }

    /**
     * Renames a class, if it exists. (Preserves order)
     * Notifies observers with the project.
     * @param oldClassName the current name of the class to rename
     * @param newClassName the new name to give to the class
     */
    public void renameClass(String oldClassName, String newClassName)
    {
        modelEditor.renameClass(oldClassName, newClassName);
        checkStatus();
        printStatusMessage();
        cli.updateReaderAndCompleter(getProjectSnapshot());
    }

    /**
     * Opens a class, if it exists.
     * @param className the name of the class to open
     */
    public void openClass(String className)
    {
        modelEditor.openClass(className);
        checkStatus();
        printStatusMessage();
    }

    /**
     * Load a project
     * Notifies observers with the project.
     * @param jsonString json representation of the project
     */
    public void loadProject(String jsonString)
    {
        modelEditor.loadProject(jsonString);
        checkStatus();
        printStatusMessage();
        cli.updateReaderAndCompleter(getProjectSnapshot());
    }

    /**
     * Closes a class, if it exists.
     * @param className the name of the class to close
     */
    public void closeClass(String className)
    {
        modelEditor.closeClass(className);
        checkStatus();
        printStatusMessage();
    }

    /**
     * Adds a new field to a class.
     * @param className the name of the class to add a field to
     * @param fieldName the name to be used by the new field
     * @param fieldType the data type to be used by the new field
     */
    public void addField(String className, String fieldName, String fieldType, String fieldVisName)
    {
        modelEditor.addField(className, fieldName, fieldType, fieldVisName);
        checkStatus();
        printStatusMessage();
        cli.updateReaderAndCompleter(getProjectSnapshot());
    }

     /**
     * Removes a field from a class, if it exists.
     * @param className the name of the class to remove a field from
     * @param fieldName the name of the field to remove
     */
    public void removeField(String className, String fieldName)
    {
        modelEditor.removeField(className, fieldName);
        checkStatus();
        printStatusMessage();
        cli.updateReaderAndCompleter(getProjectSnapshot());
    }

    /**
     * Renames a field, if it exists.
     * @param className    the name of the class with the field to rename
     * @param oldFieldName the current name of the field to rename
     * @param newFieldName the new name to give to the field
     */
    public void renameField(String className, String oldFieldName, String newFieldName)
    {
        modelEditor.renameField(className, oldFieldName, newFieldName);
        checkStatus();
        printStatusMessage();
        cli.updateReaderAndCompleter(getProjectSnapshot());
    }

    /**
     * Changes the data type of a field, if it exists.
     * @param className    the name of the class with the field to modify
     * @param fieldName    the name of the field to change the data type of
     * @param newFieldType the new data type to give to the field
     */
    public void changeFieldType(String className, String fieldName, String newFieldType)
    {
        modelEditor.changeFieldType(className, fieldName, newFieldType);
        checkStatus();
        printStatusMessage();
        cli.updateReaderAndCompleter(getProjectSnapshot());
    }

    /**
     * Changes the visibility of a field, if it exists.
     * @param className    the name of the class with the field to modify
     * @param fieldName    the name of hte field to change the visibility of
     * @param fieldVisName the new visibility to give to the field
     */
    public void changeFieldVisibility(String className, String fieldName, String newFieldVis)
    {
        modelEditor.changeFieldVisibility(className, fieldName, newFieldVis);
        checkStatus();
        printStatusMessage();
        cli.updateReaderAndCompleter(getProjectSnapshot());
    }

    public void addMethod(String className, String methodName, String methodType, String methodVisName)
    {
        modelEditor.addMethod(className, methodName, methodType, methodVisName);
        checkStatus();
        printStatusMessage();
        cli.updateReaderAndCompleter(getProjectSnapshot());
    }

    /**
     * Removes a method from a class, if it exists.
     * @param className  the name of the class to remove a method from
     * @param methodName the name of the method to remove
     */
    public void removeMethod(String className, String methodName)
    {
        modelEditor.removeMethod(className, methodName);
        checkStatus();
        printStatusMessage();
        cli.updateReaderAndCompleter(getProjectSnapshot());
    }

     /**
     * Renames a method, if it exists.
     * @param className     the name of the class with the method to rename
     * @param oldMethodName the current name of the method to rename
     * @param newMethodName the new name to give to the method
     */
    public void renameMethod(String className, String oldMethodName, String newMethodName)
    {
        modelEditor.renameMethod(className, oldMethodName, newMethodName);
        checkStatus();
        printStatusMessage();
        cli.updateReaderAndCompleter(getProjectSnapshot());
    }

    /**
     * Changes the return type of a method, if it exists.
     * @param className     the name of the class with the method to modify
     * @param methodName    the name of the method to change the return type of
     * @param newMethodType the new return type to give to the method
     */
    public void changeMethodType(String className, String methodName, String newMethodType)
    {
        modelEditor.changeMethodType(className, methodName, newMethodType);
        checkStatus();
        printStatusMessage();
        cli.updateReaderAndCompleter(getProjectSnapshot());
    }

    /**
     * Changes the visibility of a method, if it exists.
     * @param className     the name of the class with the method to modify
     * @param methodName    the name of the method to change the visibility of
     * @param methodVisName the new visibility to give to the method
     */
    public void changeMethodVisibility(String className, String methodName, String newMethodVis)
    {
        modelEditor.changeMethodVisibility(className, methodName, newMethodVis);
        checkStatus();
        printStatusMessage();
        cli.updateReaderAndCompleter(getProjectSnapshot());
    }

    /**
     * Adds a parameter to a method
     * @param className     the name of the class which is getting a parameter
     * @param methodName    the name of the method which is getting a parameter
     * @param paramName     the name of the parameter to be added
     * @param paramType     the type of the parameter to be added
     */
    public void addParameter(String className, String methodName, String paramName, String paramType)
    {
        modelEditor.addParameter(className, methodName, paramName, paramType);
        checkStatus();
        printStatusMessage();
        cli.updateReaderAndCompleter(getProjectSnapshot());
    }

    /**
     * @param className     the name of the class which is losing a parameter
     * @param methodName    the name of the method which is losing a parameter
     * @param paramName     the name of the parameter to be added
     */
    public void removeParameter(String className, String methodName, String paramName)
    {
        modelEditor.removeParameter(className, methodName, paramName);
        checkStatus();
        printStatusMessage();
        cli.updateReaderAndCompleter(getProjectSnapshot());
    }

    /**
     * @param className     the name of the class with a parameter being renamed
     * @param methodName    the name of the method with a parameter being renamed
     * @param oldParamName  the name of the parameter which is being renamed
     * @param newParamName  the new name of the parameter being renamed
     */
    public void renameParameter(String className, String methodName, String oldParamName, String newParamName)
    {
        modelEditor.renameParameter(className, methodName, oldParamName, newParamName);
        checkStatus();
        printStatusMessage();
        cli.updateReaderAndCompleter(getProjectSnapshot());
    }

    /**
     * @param className     the name of the class with a parameter being re-typed
     * @param methodName    the name of the method with a parameter being re-typed
     * @param paramName     the name of the parameter being re-typed
     * @param newParamType  the type the parameter is being re-typed to
     */
    public void changeParameterType(String className, String methodName, String paramName, String newParamType)
    {
        modelEditor.changeParameterType(className, methodName, paramName, newParamType);
        checkStatus();
        printStatusMessage();
        cli.updateReaderAndCompleter(getProjectSnapshot());
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
        modelEditor.addRelationship(classNameFrom, classNameTo, typeName);
        checkStatus();
        printStatusMessage();
        cli.updateReaderAndCompleter(getProjectSnapshot());
    }

    /**
     * Removes a relationship from the project, if it exists.
     * Notifies observers with the project.
     * @param classNameFrom the name of the class the relationship starts at
     * @param classNameTo   the name of the class the relationship goes to
     */
    public void removeRelationship(String classNameFrom, String classNameTo)
    {
        modelEditor.removeRelationship(classNameFrom, classNameTo);
        checkStatus();
        printStatusMessage();
        cli.updateReaderAndCompleter(getProjectSnapshot());
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
        modelEditor.changeRelationshipType(classNameFrom, classNameTo, newTypeName);
        checkStatus();
        printStatusMessage();
        cli.updateReaderAndCompleter(getProjectSnapshot());
    }

    /**
     * Encodes the current project as a JSON string.
     * @return a JSON encoding of the project
     */
    public String toJSONString()
    {
        return modelEditor.toJSONString();
    }

    /**
     * Undo the last undoable command
     */
    public void undo() 
    {
        modelEditor.undo();
        cli.updateReaderAndCompleter(getProjectSnapshot());
    }

    /**
     * Redo the last undone command
     */
    public void redo()
    {
        modelEditor.redo();
        cli.updateReaderAndCompleter(getProjectSnapshot());
    }

    /**
     * Print a status message saying if an action was successful
     */
    public String getLastCommandStatusMessage()
    {
        return modelEditor.getLastCommandStatusMessage();
    }

    /**
     * Print getLastCommandStatusMessage in the Console
     */
    public void printStatusMessage()
    {
        cli.alert(getLastCommandStatusMessage());
    }
    
    /**
	 * Checks if the WPEditor modification was legal or not
	 */
	private void checkStatus()
    {
        // If the last command failed
        if(!modelEditor.getLastCommandStatus())
        {
            cli.alert("Error: " + modelEditor.getLastCommandStatusMessage());
        }
    }
}