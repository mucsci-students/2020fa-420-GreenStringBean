package model;

import java.util.Set;
/**
 * The model represents a UML diagram containing classes and
 * relationships between those classes. Includes methods for creating and
 * modifying the classes and relationships, as well as the data contained
 * in classes. Methods use int return values to denote success or failure as
 * follows:
 *     00 - Success
 *     01 - Class is not open
 *     02 - Class does not exist
 *     03 - Field does not exist
 *     04 - Method does not exist
 *     05 - Parameter does not exist
 *     06 - Relationship does not exist
 *     07 - Relationship already exists
 *     08 - Name is already in use
 *     09 - Name is not valid
 *     10 - Data type is not valid
 *     11 - Relationship type is not valid
 *     12 - Error loading project
 *     13 - Loaded project is not valid
 *     14 - Visibility Modifier is not valid
 */
public interface Model
{
    /**
     * Adds a new class to the project.
     * @param className the name to be used by the new class
     * @return          0 if successful, error code otherwise
     */
    int addClass(String className);

    /**
     * Removes a class from the project, if it exists. Also removes any
     * relationships the class was part of.
     * @param className the name of the class to remove
     * @return          0 if successful, error code otherwise
     */
    int removeClass(String className);
    
     /**
     * Renames a class, if it exists. (Preserves order)
     * @param oldClassName the current name of the class to rename
     * @param newClassName the new name to give to the class
     * @return             0 if successful, error code otherwise
     */
    int renameClass(String oldClassName, String newClassName);
    
    /**
     * Closes a class, if it exists.
     * @param className the name of the class to close
     * @return          0 if successful, error code otherwise
     */
    int closeClass(String className);
    
    /**
     * Opens a class, if it exists.
     * @param className the name of the class to open
     * @return          0 if successful, error code otherwise
     */
    int openClass(String className);

    /**
     * Adds a new field to a class.
     * @param className the name of the class to add a field to
     * @param fieldName the name to be used by the new field
     * @param fieldType the data type to be used by the new field
     * @return          0 if successful, error code otherwise
     */
    int addField(String className, String fieldName, String fieldType, String fieldVisName);
    
    /**
     * Removes a field from a class, if it exists.
     * @param className the name of the class to remove a field from
     * @param fieldName the name of the field to remove
     * @return          0 if successful, error code otherwise
     */
    int removeField(String className, String fieldName);
    
    /**
     * Renames a field, if it exists.
     * @param className    the name of the class with the field to rename
     * @param oldFieldName the current name of the field to rename
     * @param newFieldName the new name to give to the field
     * @return             0 if successful, error code otherwise
     */
    int renameField(String className, String oldFieldName, String newFieldName);
    
    /**
     * Changes the data type of a field, if it exists.
     * @param className    the name of the class with the field to modify
     * @param fieldName    the name of the field to change the data type of
     * @param newFieldType the new data type to give to the field
     * @return             0 if successful, error code otherwise
     */
    int changeFieldType(String className, String fieldName, String newFieldType);
    
    /**
     * Changes the visibility of a field, if it exists.
     * @param className    the name of the class with the field to modify
     * @param fieldName    the name of hte field to change the visibility of
     * @param fieldVisName the new visibility to give to the field
     * @return             0 if successful, error code otherwise
     */
    int changeFieldVisibility(String className, String fieldName, String fieldVisName);
    
    /**
     * Adds a new method to a class.
     * @param className  the name of the class to add a method to
     * @param methodName the name to be used by the new method
     * @param methodType the data type to be used by the new method
     * @return           0 if successful, error code otherwise
     */
    int addMethod(String className, String methodName, String methodType, String methodVisName);
    
    /**
     * Removes a method from a class, if it exists.
     * @param className  the name of the class to remove a method from
     * @param methodName the name of the method to remove
     * @return           0 if successful, error code otherwise
     */
    int removeMethod(String className, String methodName);
    
    /**
     * Renames a method, if it exists.
     * @param className     the name of the class with the method to rename
     * @param oldMethodName the current name of the method to rename
     * @param newMethodName the new name to give to the method
     * @return              0 if successful, error code otherwise
     */
    int renameMethod(String className, String oldMethodName, String newMethodName);
    
    /**
     * Changes the return type of a method, if it exists.
     * @param className     the name of the class with the method to modify
     * @param methodName    the name of the method to change the return type of
     * @param newMethodType the new return type to give to the method
     * @return              0 if successful, error code otherwise
     */
    int changeMethodType(String className, String methodName, String newMethodType);
    
    /**
     * Changes the visibility of a method, if it exists.
     * @param className     the name of the class with the method to modify
     * @param methodName    the name of the method to change the visibility of
     * @param methodVisName the new visibility to give to the method
     * @return              0 if successful, error code otherwise
     */
    int changeMethodVisibility(String className, String methodName, String methodVisName);
    
    /**
     * Adds a new parameter to a method.
     * @param className  the name of the class with the method to modify
     * @param methodName the name of the method to add a parameter to
     * @param paramName  the name to be used by the new parameter
     * @param paramType  the data type to be used by the new parameter
     * @return           0 if successful, error code otherwise
     */
    int addParameter(String className, String methodName, String paramName, String paramType);
    
    /**
     * Removes a parameter from a method, if it exists.
     * @param className  the name of the class with the method to modify
     * @param methodName the name of the method to remove a parameter from
     * @param paramName  the name of the parameter to remove
     * @return           0 if successful, error code otherwise
     */
    int removeParameter(String className, String methodName, String paramName);
    
    /**
     * Renames a parameter, if it exists.
     * @param className    the name of the class with the method to modify
     * @param methodName   the name of the method with the parameter to rename
     * @param oldParamName the current name of the parameter to rename
     * @param newParamName the new name to give to the parameter
     * @return             0 if successful, error code otherwise
     */
    int renameParameter(String className, String methodName, String oldParamName, String newParamName);
    
    /**
     * Changes the data type of a parameter, if it exists.
     * @param className    the name of the class with the method to modify
     * @param methodName   the name of the method with the parameter to modify
     * @param paramName    the name of the parameter to change the data type of
     * @param newParamType the new data type to give to the parameter
     * @return             0 if successful, error code otherwise
     */
    int changeParameterType(String className, String methodName, String paramName, String newParamType);
    
    /**
     * Adds a new ordered relationship to the project.
     * @param classNameFrom the name of the class the relationship starts at
     * @param classNameTo   the name of the class the relationship goes to
     * @param typeName      the first letter of the relationship's type
     * @return              0 if successful, error code otherwise
     */
    int addRelationship(String classNameFrom, String classNameTo, String typeName);
    
    /**
     * Removes a relationship from the project, if it exists.
     * @param classNameFrom the name of the class the relationship starts at
     * @param classNameTo   the name of the class the relationship goes to
     * @return              0 if successful, error code otherwise
     */
    int removeRelationship(String classNameFrom, String classNameTo);
    
    /**
     * Changes the type of a relationship, if it exists.
     * @param classNameFrom the name of the class the relationship starts at
     * @param classNameTo   the name of the class the relationship goes to
     * @param newTypeName   the first letter of the relationship's new type
     * @return              0 if successful, error code otherwise
     */
    int changeRelationshipType(String classNameFrom, String classNameTo, String newTypeName);
    
    // Print the name of each ClassObject in the project
    // TODO: remove this method //////////////////////////////////////////////////////////////////////
    void printClasses();
    
    /**
     * Replaces current project with an encoded project.
     * @param jsonString a JSON encoding of a project
     * @return           0 if successful, error code otherwise
     */
    int loadFromJSON(String jsonString);

    /**
     * Encodes the current project as a JSON string.
     * @return a JSON encoding of the project
     */
    String toJSONString();

    /**
     * Check if a project contains a class called className.
     * @param className the name of the class to search for
     * @return          true if the project contains the class, false otherwise
     */
    boolean hasClass(String className);

    /**
     * Gets a set containing the names of the classes in the project.
     * @return the set of class names
     */
    Set<String> getClassNames();

    /**
     * Accesses a class by name. Should be used after hasClass returned true or
     * using a member of the set returned by getClassNames. 
     * @param className the name of the class to return
     * @return          the class called className, or null if not found
     */
    ClassObject getClass(String className);

    /**
     * Check if a project contains a relationship from one class to another.
     * @param classNameFrom the name of the "from" class
     * @param classNameTo   the name of the "to" class
     * @return              true if the project contains the relationship,
     *                      false otherwise
     */
    boolean hasRelationship(String classNameFrom, String classNameTo);

    /**
     * Gets the set of relationships in the project.
     * @return the set of relationships
     */
    Set<Relationship> getRelationships();

    /**
     * Creates a copy of the project
     * @return the copy of the project
     */
    WorkingProject copy();

}