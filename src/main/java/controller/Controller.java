package controller;

import java.util.List;

import model.Model;

public interface Controller {
    /**
     * Creates a copy of the current state of the Model
     * @return copy of the model
     */
    Model getProjectSnapshot();

    /**
     * Adds a new class to the project. Notifies observers with the project.
     * @param className the name to be used by the new class
     */
    void addClass(String className);

    /**
     * Removes a class from the project, if it exists. Also removes any
     * relationships the class was part of.
     * Notifies Observers with the project.
     * @param className the name of the class to remove
     */
    void removeClass(String className);

    /**
     * Renames a class, if it exists. (Preserves order)
     * Notifies observers with the project.
     * @param oldClassName the current name of the class to rename
     * @param newClassName the new name to give to the class
     */
    void renameClass(String oldClassName, String newClassName);

    /**
     * Opens a class, if it exists.
     * @param className the name of the class to open
     */
    void openClass(String className);

    /**
     * Load a project
     * Notifies observers with the project.
     * @param jsonString json representation of the project
     */
    void loadProject(String jsonString);

    /**
     * Closes a class, if it exists.
     * @param className the name of the class to close
     */
    void closeClass(String className);

    /**
     * Adds a new field to a class.
     * @param className the name of the class to add a field to
     * @param fieldName the name to be used by the new field
     * @param fieldType the data type to be used by the new field
     */
    void addField(String className, String fieldName, String fieldType, String fieldVisName);

     /**
     * Removes a field from a class, if it exists.
     * @param className the name of the class to remove a field from
     * @param fieldName the name of the field to remove
     */
    void removeField(String className, String fieldName);

    /**
     * Renames a field, if it exists.
     * @param className    the name of the class with the field to rename
     * @param oldFieldName the current name of the field to rename
     * @param newFieldName the new name to give to the field
     */
    void renameField(String className, String oldFieldName, String newFieldName);

    /**
     * Changes the data type of a field, if it exists.
     * @param className    the name of the class with the field to modify
     * @param fieldName    the name of the field to change the data type of
     * @param newFieldType the new data type to give to the field
     */
    void changeFieldType(String className, String fieldName, String newFieldType);

    /**
     * Changes the visibility of a field, if it exists.
     * @param className    the name of the class with the field to modify
     * @param fieldName    the name of hte field to change the visibility of
     * @param fieldVisName the new visibility to give to the field
     */
    void changeFieldVisibility(String className, String fieldName, String newFieldVis);

    /**
     * Removes a method from a class, if it exists.
     * @param className  the name of the class to remove a method from
     * @param methodName the name of the method to remove
     */
    void removeMethod(String className, String methodName);

     /**
     * Renames a method, if it exists.
     * @param className     the name of the class with the method to rename
     * @param oldMethodName the current name of the method to rename
     * @param newMethodName the new name to give to the method
     */
    void renameMethod(String className, String oldMethodName, String newMethodName);

    /**
     * Changes the return type of a method, if it exists.
     * @param className     the name of the class with the method to modify
     * @param methodName    the name of the method to change the return type of
     * @param newMethodType the new return type to give to the method
     */
    void changeMethodType(String className, String methodName, String newMethodType);

    /**
     * Changes the visibility of a method, if it exists.
     * @param className     the name of the class with the method to modify
     * @param methodName    the name of the method to change the visibility of
     * @param methodVisName the new visibility to give to the method
     */
    void changeMethodVisibility(String className, String methodName, String newMethodVis);
    /**
     * Adds a new ordered relationship to the project.
     * Notifies observers with the project.
     * @param classNameFrom the name of the class the relationship starts at
     * @param classNameTo   the name of the class the relationship goes to
     * @param typeName      the first letter of the relationship's type
     */
    void addRelationship(String classNameFrom, String classNameTo, String typeName);

    /**
     * Removes a relationship from the project, if it exists.
     * Notifies observers with the project.
     * @param classNameFrom the name of the class the relationship starts at
     * @param classNameTo   the name of the class the relationship goes to
     */
    void removeRelationship(String classNameFrom, String classNameTo);

    /**
     * Changes the type of a relationship, if it exists.
     * Notifies observers with the project.
     * @param classNameFrom the name of the class the relationship starts at
     * @param classNameTo   the name of the class the relationship goes to
     * @param newTypeName   the first letter of the relationship's new type
     */
    void changeRelationshipType(String classNameFrom, String classNameTo, String newTypeName);

    /**
     * Encodes the current project as a JSON string.
     * @return a JSON encoding of the project
     */
    String toJSONString();

    /**
     * Undo the last undoable command
     */
    void undo();

    /**
     * Redo the last undone command
     */
    void redo();
}
