
package model;

import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

/**
 * The working project represents a UML diagram containing classes and
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

public class WorkingProject {
    private HashMap<String, ClassObject> classes;
    private ArrayList<Relationship> relationships;
    

    /**
     * Creates a new working project with no classes or relationships.
     */
    public WorkingProject()
    {
        classes = new HashMap<>();
        relationships = new ArrayList<>();
    }

    /**
     * Adds a new class to the project.
     * @param className the name to be used by the new class
     * @return          0 if successful, error code otherwise
     */
    public int addClass(String className)
    {
        if (classes.containsKey(className))
        {
            return 8;
        }

        classes.put(className, new ClassObject(className));
        return 0;
    }
    
    /**
     * Removes a class from the project, if it exists. Also removes any
     * relationships the class was part of.
     * @param className the name of the class to remove
     * @return          0 if successful, error code otherwise
     */
    public int removeClass(String className)
    {
        if (!classes.containsKey(className))
        {
            return 2;
        }

        classes.remove(className);
        removeRelationshipsByClass(className);
        return 0;
    }
    
    /**
     * Renames a class, if it exists.
     * @param oldClassName the current name of the class to rename
     * @param newClassName the new name to give to the class
     * @return             0 if successful, error code otherwise
     */
    public int renameClass(String oldClassName, String newClassName)
    {
        if (!classes.containsKey(oldClassName))
        {
            return 2;
        }

        if (classes.containsKey(newClassName))
        {
            return 8;
        }

        ClassObject renamedClass = classes.remove(oldClassName);
        renamedClass.setName(newClassName);
        classes.put(newClassName, renamedClass);
        renameClassInRelationships(oldClassName, newClassName);
        return 0;
    }

    /**
     * Closes a class, if it exists.
     * @param className the name of the class to close
     * @return          0 if successful, error code otherwise
     */
    public int closeClass(String className)
    {
        if (!classes.containsKey(className))
        {
            return 2;
        }

        classes.get(className).close();
        return 0;
    }

    /**
     * Opens a class, if it exists.
     * @param className the name of the class to open
     * @return          0 if successful, error code otherwise
     */
    public int openClass(String className)
    {
        if (!classes.containsKey(className))
        {
            return 2;
        }

        classes.get(className).open();
        return 0;
    }

    /**
     * Adds a new field to a class.
     * @param className the name of the class to add a field to
     * @param fieldName the name to be used by the new field
     * @param fieldType the data type to be used by the new field
     * @return          0 if successful, error code otherwise
     */
    public int addField(String className, String fieldName, String fieldType, String fieldVisName)
    {
        if (!classes.containsKey(className))
        {
            return 2;
        }

        return classes.get(className).addField(fieldName, fieldType, fieldVisName);
    }

    /**
     * Removes a field from a class, if it exists.
     * @param className the name of the class to remove a field from
     * @param fieldName the name of the field to remove
     * @return          0 if successful, error code otherwise
     */
    public int removeField(String className, String fieldName)
    {
        if (!classes.containsKey(className))
        {
            return 2;
        }

        return classes.get(className).removeField(fieldName);
    }

    /**
     * Renames a field, if it exists.
     * @param className    the name of the class with the field to rename
     * @param oldFieldName the current name of the field to rename
     * @param newFieldName the new name to give to the field
     * @return             0 if successful, error code otherwise
     */
    public int renameField(String className, String oldFieldName, String newFieldName)
    {
        if (!classes.containsKey(className))
        {
            return 2;
        }

        return classes.get(className).renameField(oldFieldName, newFieldName);
    }

    /**
     * Changes the data type of a field, if it exists.
     * @param className    the name of the class with the field to modify
     * @param fieldName    the name of the field to change the data type of
     * @param newFieldType the new data type to give to the field
     * @return             0 if successful, error code otherwise
     */
    public int changeFieldType(String className, String fieldName, String newFieldType)
    {
        if (!classes.containsKey(className))
        {
            return 2;
        }

        return classes.get(className).changeFieldType(fieldName, newFieldType);
    }

    public int changeFieldVisibility(String className, String fieldName, String fieldVisName)
    {
        if(!classes.containsKey(className))
        {
            return 2;
        }
        return classes.get(className).changeFieldVisibility(fieldName, fieldVisName);
    }

    /**
     * Adds a new method to a class.
     * @param className  the name of the class to add a method to
     * @param methodName the name to be used by the new method
     * @param methodType the data type to be used by the new method
     * @return           0 if successful, error code otherwise
     */
    public int addMethod(String className, String methodName, String methodType, String methodVisName)
    {
        if (!classes.containsKey(className))
        {
            return 2;
        }

        return classes.get(className).addMethod(methodName, methodType, methodVisName);
    }

    /**
     * Removes a method from a class, if it exists.
     * @param className  the name of the class to remove a method from
     * @param methodName the name of the method to remove
     * @return           0 if successful, error code otherwise
     */
    public int removeMethod(String className, String methodName)
    {
        if (!classes.containsKey(className))
        {
            return 2;
        }

        return classes.get(className).removeMethod(methodName);
    }

    /**
     * Renames a method, if it exists.
     * @param className     the name of the class with the method to rename
     * @param oldMethodName the current name of the method to rename
     * @param newMethodName the new name to give to the method
     * @return              0 if successful, error code otherwise
     */
    public int renameMethod(String className, String oldMethodName, String newMethodName)
    {
        if (!classes.containsKey(className))
        {
            return 2;
        }

        return classes.get(className).renameMethod(oldMethodName, newMethodName);
    }

    /**
     * Changes the return type of a method, if it exists.
     * @param className     the name of the class with the method to modify
     * @param methodName    the name of the method to change the return type of
     * @param newMethodType the new return type to give to the method
     * @return              0 if successful, error code otherwise
     */
    public int changeMethodType(String className, String methodName, String newMethodType)
    {
        if (!classes.containsKey(className))
        {
            return 2;
        }

        return classes.get(className).changeMethodType(methodName, newMethodType);
    }

    public int changeMethodVisibility(String className, String methodName, String methodVisName)
    {
        if(!classes.containsKey(className))
        {
            return 2;
        }
        return classes.get(className).changeMethodVisibility(methodName, methodVisName);
    }

    /**
     * Adds a new parameter to a method.
     * @param className  the name of the class with the method to modify
     * @param methodName the name of the method to add a parameter to
     * @param paramName  the name to be used by the new parameter
     * @param paramType  the data type to be used by the new parameter
     * @return           0 if successful, error code otherwise
     */
    public int addParameter(String className, String methodName, String paramName, String paramType)
    {
        if (!classes.containsKey(className))
        {
            return 2;
        }

        return classes.get(className).addParameter(methodName, paramName, paramType);
    }

    /**
     * Removes a parameter from a method, if it exists.
     * @param className  the name of the class with the method to modify
     * @param methodName the name of the method to remove a parameter from
     * @param paramName  the name of the parameter to remove
     * @return           0 if successful, error code otherwise
     */
    public int removeParameter(String className, String methodName, String paramName)
    {
        if (!classes.containsKey(className))
        {
            return 2;
        }

        return classes.get(className).removeParameter(methodName, paramName);
    }

    /**
     * Renames a parameter, if it exists.
     * @param className    the name of the class with the method to modify
     * @param methodName   the name of the method with the parameter to rename
     * @param oldParamName the current name of the parameter to rename
     * @param newParamName the new name to give to the parameter
     * @return             0 if successful, error code otherwise
     */
    public int renameParameter(String className, String methodName, String oldParamName, String newParamName)
    {
        if (!classes.containsKey(className))
        {
            return 2;
        }

        return classes.get(className).renameParameter(methodName, oldParamName, newParamName);
    }
    
    /**
     * Changes the data type of a parameter, if it exists.
     * @param className    the name of the class with the method to modify
     * @param methodName   the name of the method with the parameter to modify
     * @param paramName    the name of the parameter to change the data type of
     * @param newParamType the new data type to give to the parameter
     * @return             0 if successful, error code otherwise
     */
    public int changeParameterType(String className, String methodName, String paramName, String newParamType)
    {
        if (!classes.containsKey(className))
        {
            return 2;
        }

        return classes.get(className).changeParameterType(methodName, paramName, newParamType);
    }

    /**
     * Adds a new ordered relationship to the project.
     * @param classNameFrom the name of the class the relationship starts at
     * @param classNameTo   the name of the class the relationship goes to
     * @param typeName      the first letter of the relationship's type
     * @return              0 if successful, error code otherwise
     */
    public int addRelationship(String classNameFrom, String classNameTo, String typeName)
    {
        Relationship.relationshipType type = stringToRelationshipType(typeName);
        
        if (type == null)
        {
            return 11;
        }

        if (!classes.containsKey(classNameFrom) || !classes.containsKey(classNameTo))
        {
            return 2;
        }
        
        int index = getRelationshipIndex(classNameFrom, classNameTo);

        if (index != -1)
        {
            return 7;
        }

        relationships.add(new Relationship(classNameFrom, classNameTo, type));
        return 0;
    }

    /**
     * Removes a relationship from the project, if it exists.
     * @param classNameFrom the name of the class the relationship starts at
     * @param classNameTo   the name of the class the relationship goes to
     * @return              0 if successful, error code otherwise
     */
    public int removeRelationship(String classNameFrom, String classNameTo)
    {
        if (!classes.containsKey(classNameFrom) || !classes.containsKey(classNameTo))
        {
            return 2;
        }

        int index = getRelationshipIndex(classNameFrom, classNameTo);

        if (index == -1)
        {
            return 6;
        }

        relationships.remove(index);
        return 0;
    }
    
    /**
     * Changes the type of a relationship, if it exists.
     * @param classNameFrom the name of the class the relationship starts at
     * @param classNameTo   the name of the class the relationship goes to
     * @param newTypeName   the first letter of the relationship's new type
     * @return              0 if successful, error code otherwise
     */
    public int changeRelationshipType(String classNameFrom, String classNameTo, String newTypeName)
    {
        Relationship.relationshipType newType = stringToRelationshipType(newTypeName);
        
        if (newType == null)
        {
            return 11;
        }

        if (!classes.containsKey(classNameFrom) || !classes.containsKey(classNameTo))
        {
            return 2;
        }

        int index = getRelationshipIndex(classNameFrom, classNameTo);

        if (index == -1)
        {
            return 6;
        }

        relationships.get(index).setType(newType);
        return 0;
    }

    // Print the name of each ClassObject in the project
    public void printClasses()
    {
        for (ClassObject classObj : classes.values())
        {
            System.out.println(classObj.getName());
        }
    }

    // Return the index of the first ordered Relationship between classObj1 and classObj2 in the list
    // Return -1 if no Relationship is found
    private int getRelationshipIndex(String classNameFrom, String classNameTo)
    {
        for (int i = 0; i < relationships.size(); ++i)
        {
            if (relationships.get(i).getClassNameFrom() == classNameFrom && relationships.get(i).getClassNameTo() == classNameTo)
            {
                return i;
            }
        }
        return -1;
    }

    // Remove any relationships which contain classObj from the list
    private void removeRelationshipsByClass(String className)
    {
        for (Relationship relationship : relationships)
        {
            if (relationship.containsClass(className))
            {
                relationships.remove(relationship);
            }
        }
    }

    private void renameClassInRelationships(String oldClassName, String newClassName){
        for(Relationship relationship : relationships)
        {
            if (relationship.getClassNameFrom().equals(oldClassName))
                relationship.setClassNameFrom(newClassName);
            if (relationship.getClassNameTo().equals(oldClassName))
                relationship.setClassNameTo(newClassName);    
        }
    }
    
    public static Relationship.relationshipType stringToRelationshipType(String str)
    {
        str = str.toUpperCase();
        switch(str)
        {
            case "A":
                return Relationship.relationshipType.AGGREGATION;
            case "C":
                return Relationship.relationshipType.COMPOSITION;
            case "I":
                return Relationship.relationshipType.INHERITANCE;
            case "R":
                return Relationship.relationshipType.REALIZATION;
            default :
                return null;
        }
    }

    /**
     * Replaces current project with an encoded project.
     * @param jsonString a JSON encoding of a project
     * @return           0 if successful, error code otherwise
     */
    public int loadFromJSON(String jsonString)
    {
        classes.clear();
        relationships.clear();

        JSONObject jsonProject = (JSONObject)JSONValue.parse(jsonString);
        JSONObject jsonClasses = (JSONObject)jsonProject.get("classes");
        JSONArray jsonRelationships = (JSONArray)jsonProject.get("relationships");

        for (Object className : jsonClasses.keySet())
        {
            classes.put((String)className, ClassObject.loadFromJSON((JSONObject)jsonClasses.get(className)));
        }

        for (Object jsonRelationship : jsonRelationships)
        {
            String classNameFrom = (String)((JSONObject)jsonRelationship).get("from");
            String classNameTo = (String)((JSONObject)jsonRelationship).get("to");
            String typeName = (String)((JSONObject)jsonRelationship).get("type");
            typeName = typeName.substring(0, 1);
            Relationship.relationshipType type = stringToRelationshipType(typeName);
            relationships.add(new Relationship(classNameFrom, classNameTo, type));
        }

        return 0;
    }
    
    /**
     * Encodes the current project as a JSON string.
     * @return a JSON encoding of the project
     */
    public String toJSONString()
    {
        JSONObject jsonProject = new JSONObject();

        JSONObject jsonClasses = new JSONObject();
        for (String className : classes.keySet())
        {
            jsonClasses.put(className, classes.get(className).toJSON());
        }

        JSONArray jsonRelationships = new JSONArray();
        for (Relationship relationship : relationships)
        {
            jsonRelationships.add(relationship.toJSON());
        }

        jsonProject.put("classes", jsonClasses);
        jsonProject.put("relationships", jsonRelationships);

        System.out.println("Successfully saved");

        return jsonProject.toJSONString();
    }

    public boolean hasClass(String className)
    {
        return classes.containsKey(className);
    }

    public Set<String> getClassNames(){
        return classes.keySet();
    }

    public ClassObject getClass(String className){
        if(classes.containsKey(className)){
            return classes.get(className);
        }
        return null;
    }

    public boolean hasRelationship(String classNameFrom, String classNameTo)
    {
        return (getRelationshipIndex(classNameFrom, classNameTo) != -1);
    }

    public Set<Relationship> getRelationships(){
        return new HashSet<>(relationships);
    }

    public WorkingProject copy()
    {
        WorkingProject copy = new WorkingProject();
        copy.classes.putAll(classes);
        copy.classes.replaceAll((name, classObj)->classObj.copy());
        copy.relationships.addAll(relationships);
        copy.relationships.replaceAll(relationship->relationship.copy());
        return copy;
    }

    
}