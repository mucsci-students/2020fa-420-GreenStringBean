package controller; 

import model.Model;
import view.*;

public class CLIEditorController implements CLIController
{

    private ModelEditor model;
    private CLIView cli;

    public CLIEditorController (CLIView cli, ModelEditor model)
    {
        this.cli = cli;
        this.model = model;
        model.attach(this.cli);
    }

     /**
     * Creates a copy of the current state of the Model
     * @return copy of the model
     */
    public Model getProjectSnapshot()
    {
        return model.getProjectSnapshot();
    }

    /**
     * Adds a new class to the project. Notifies observers with the project.
     * @param className the name to be used by the new class
     */
    public void addClass(String className)
    {
        model.addClass(className);
        checkStatus();
    }

    /**
     * Removes a class from the project, if it exists. Also removes any
     * relationships the class was part of.
     * Notifies Observers with the project.
     * @param className the name of the class to remove
     */
    public void removeClass(String className)
    {
        model.removeClass(className);
        checkStatus();
    }

    /**
     * Renames a class, if it exists. (Preserves order)
     * Notifies observers with the project.
     * @param oldClassName the current name of the class to rename
     * @param newClassName the new name to give to the class
     */
    public void renameClass(String oldClassName, String newClassName)
    {
        model.renameClass(oldClassName, newClassName);
        checkStatus();
    }

    /**
     * Opens a class, if it exists.
     * @param className the name of the class to open
     */
    public void openClass(String className)
    {
        model.openClass(className);
        checkStatus();
    }

    /**
     * Load a project
     * Notifies observers with the project.
     * @param jsonString json representation of the project
     */
    public void loadProject(String jsonString)
    {
        model.loadProject(jsonString);
        checkStatus();
    }

    /**
     * Closes a class, if it exists.
     * @param className the name of the class to close
     */
    public void closeClass(String className)
    {
        model.closeClass(className);
        checkStatus();
    }

    /**
     * Adds a new field to a class.
     * @param className the name of the class to add a field to
     * @param fieldName the name to be used by the new field
     * @param fieldType the data type to be used by the new field
     */
    public void addField(String className, String fieldName, String fieldType, String fieldVisName)
    {
        model.addField(className, fieldName, fieldType, fieldVisName);
        checkStatus();
    }

     /**
     * Removes a field from a class, if it exists.
     * @param className the name of the class to remove a field from
     * @param fieldName the name of the field to remove
     */
    public void removeField(String className, String fieldName)
    {
        model.removeField(className, fieldName);
        checkStatus();
    }

    /**
     * Renames a field, if it exists.
     * @param className    the name of the class with the field to rename
     * @param oldFieldName the current name of the field to rename
     * @param newFieldName the new name to give to the field
     */
    public void renameField(String className, String oldFieldName, String newFieldName)
    {
        model.renameField(className, oldFieldName, newFieldName);
        checkStatus();
    }

    /**
     * Changes the data type of a field, if it exists.
     * @param className    the name of the class with the field to modify
     * @param fieldName    the name of the field to change the data type of
     * @param newFieldType the new data type to give to the field
     */
    public void changeFieldType(String className, String fieldName, String newFieldType)
    {
        model.changeFieldType(className, fieldName, newFieldType);
        checkStatus();
    }

    /**
     * Changes the visibility of a field, if it exists.
     * @param className    the name of the class with the field to modify
     * @param fieldName    the name of hte field to change the visibility of
     * @param fieldVisName the new visibility to give to the field
     */
    public void changeFieldVisibility(String className, String fieldName, String newFieldVis)
    {
        model.changeFieldVisibility(className, fieldName, newFieldVis);
        checkStatus();
    }

    public void addMethod(String className, String methodName, String methodType, String methodVisName)
    {
        model.addMethod(className, methodName, methodType, methodVisName);
        checkStatus();
    }

    /**
     * Removes a method from a class, if it exists.
     * @param className  the name of the class to remove a method from
     * @param methodName the name of the method to remove
     */
    public void removeMethod(String className, String methodName)
    {
        model.removeMethod(className, methodName);
        checkStatus();
    }

     /**
     * Renames a method, if it exists.
     * @param className     the name of the class with the method to rename
     * @param oldMethodName the current name of the method to rename
     * @param newMethodName the new name to give to the method
     */
    public void renameMethod(String className, String oldMethodName, String newMethodName)
    {
        model.renameMethod(className, oldMethodName, newMethodName);
        checkStatus();
    }

    /**
     * Changes the return type of a method, if it exists.
     * @param className     the name of the class with the method to modify
     * @param methodName    the name of the method to change the return type of
     * @param newMethodType the new return type to give to the method
     */
    public void changeMethodType(String className, String methodName, String newMethodType)
    {
        model.changeMethodType(className, methodName, newMethodType);
        checkStatus();
    }

    /**
     * Changes the visibility of a method, if it exists.
     * @param className     the name of the class with the method to modify
     * @param methodName    the name of the method to change the visibility of
     * @param methodVisName the new visibility to give to the method
     */
    public void changeMethodVisibility(String className, String methodName, String newMethodVis)
    {
        model.changeMethodVisibility(className, methodName, newMethodVis);
        checkStatus();
    }

    public void addParameter(String className, String methodName, String paramName, String paramType)
    {
        model.addParameter(className, methodName, paramName, paramType);
        checkStatus();
    }

    public void removeParameter(String className, String methodName, String paramName)
    {
        model.removeParameter(className, methodName, paramName);
        checkStatus();
    }

    public void renameParameter(String className, String methodName, String oldParamName, String newParamName)
    {
        model.renameParameter(className, methodName, oldParamName, newParamName);
        checkStatus();
    }

    public void changeParameterType(String className, String methodName, String paramName, String newParamType)
    {
        model.changeParameterType(className, methodName, paramName, newParamType);
        checkStatus();
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
        model.addRelationship(classNameFrom, classNameTo, typeName);
        checkStatus();
    }

    /**
     * Removes a relationship from the project, if it exists.
     * Notifies observers with the project.
     * @param classNameFrom the name of the class the relationship starts at
     * @param classNameTo   the name of the class the relationship goes to
     */
    public void removeRelationship(String classNameFrom, String classNameTo)
    {
        model.removeRelationship(classNameFrom, classNameTo);
        checkStatus();
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
        model.changeRelationshipType(classNameFrom, classNameTo, newTypeName);
        checkStatus();
    }

    /**
     * Encodes the current project as a JSON string.
     * @return a JSON encoding of the project
     */
    public String toJSONString()
    {
        return model.toJSONString();
    }

    /**
     * Undo the last undoable command
     */
    public void undo() 
    {
        model.undo();
    }

    /**
     * Redo the last undone command
     */
    public void redo()
    {
        model.redo();
    }

    public String getLastCommandStatusMessage()
    {
        return model.getLastCommandStatusMessage();
    }
    
    /**
	 * Checks if the WPEditor modification was legal or not
	 */
	private void checkStatus()
    {
        // If the last command failed
        if(!model.getLastCommandStatus())
        {
            cli.alert("Error: " + model.getLastCommandStatusMessage());
        }
    }
}