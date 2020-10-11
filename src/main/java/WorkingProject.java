import java.util.HashMap;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;


public class WorkingProject {
    private HashMap<String, ClassObject> classes;
    private ArrayList<Relationship> relationships;
    

    // Create a new Working Project with empty ClassObject/Relationship lists
    public WorkingProject()
    {
        classes = new HashMap<>();
        relationships = new ArrayList<>();
    }

    // Add a new ClassObject called className to the project
    // Print an error if there is already a ClassObject with that name in the project
    public void addClass(String className)
    {
        if (classes.containsKey(className))
        {
            System.out.println("Error: Name is already is use");
            return;
        }

        classes.put(className, new ClassObject(className));
        System.out.println("Successfully added");
    }

    // Remove the ClassObject called className from the project
    // Remove all Relationships which contain the removed ClassObject
    // Print an error if there is no ClassObject with that name in the project
    public void removeClass(String className)
    {
        if (!classes.containsKey(className))
        {
            System.out.println("Error: Class does not exist");
            return;
        }

        ClassObject removedClass = classes.remove(className);
        removeRelationshipsByClass(removedClass);
        System.out.println("Successfully removed");
    }

    // Rename the ClassObject called oldClassName to newClassName
    // Print an error if there is no ClassObject called oldClassName in the project
    // Print an error if there is already a ClassObject called newClassName in the project
    public void renameClass(String oldClassName, String newClassName)
    {
        if (!classes.containsKey(oldClassName))
        {
            System.out.println("Error: Class does not exist");
            return;
        }

        if (classes.containsKey(newClassName))
        {
            System.out.println("Error: Name is already in use");
            return;
        }

        ClassObject renamedClass = classes.remove(oldClassName);
        renamedClass.setName(newClassName);
        classes.put(newClassName, renamedClass);
        System.out.println("Successfully renamed");
    }

    // Close ClassObject called className
    // Print an error if there is no ClassObject with that name in the list
    public void closeClass(String className)
    {
        if (!classes.containsKey(className))
        {
            System.out.println("Error: Class does not exist");
            return;
        }

        classes.get(className).close();
        System.out.println("Successfully closed");
    }

    // Open ClassObject called className
    // Print an error if there is no ClassObject with that name in the project
    public void openClass(String className)
    {
        if (!classes.containsKey(className))
        {
            System.out.println("Error: Class does not exist");
            return;
        }

        classes.get(className).open();
        System.out.println("Successfully opened");
    }

    // Add a new Field called fieldName with data type fieldType to the classObject called className
    // Print an error if there is no ClassObject with that name in the project
    public void addField(String className, String fieldName, String fieldType)
    {
        if (!classes.containsKey(className))
        {
            System.out.println("Error: Class does not exist");
            return;
        }

        classes.get(className).addField(fieldName, fieldType);
    }

    // Remove the Field called fieldName from the ClassObject called className
    // Print an error if there is no ClassObject with that name in the project
    public void removeField(String className, String fieldName)
    {
        if (!classes.containsKey(className))
        {
            System.out.println("Error: Class does not exist");
            return;
        }

        classes.get(className).removeField(fieldName);
    }

    // Rename the Field called oldFieldName to newFieldName in the ClassObject called className
    // Print an error if there is no ClassObject with that name in the project
    public void renameField(String className, String oldFieldName, String newFieldName)
    {
        if (!classes.containsKey(className))
        {
            System.out.println("Error: Class does not exist");
            return;
        }

        classes.get(className).renameField(oldFieldName, newFieldName);
    }

    // Change the data type of the Field called fieldName to newFieldType in the ClassObject called className
    // Print an error if there is no ClassObject with that name in the project
    public void changeFieldType(String className, String fieldName, String newFieldType)
    {
        if (!classes.containsKey(className))
        {
            System.out.println("Error: Class does not exist");
            return;
        }

        classes.get(className).changeFieldType(fieldName, newFieldType);
    }

    // Add a new Method called methodName with the return type methodType to the ClassObject called className
    // Print an error if there is no ClassObject with that name in the project
    public void addMethod(String className, String methodName, String methodType)
    {
        if (!classes.containsKey(className))
        {
            System.out.println("Error: Class does not exist");
            return;
        }

        classes.get(className).addMethod(methodName, methodType);
    }

    // Remove the Method called methodName from the ClassObject called className
    // Print an error if there is no ClassObject with that name in the project
    public void removeMethod(String className, String methodName)
    {
        if (!classes.containsKey(className))
        {
            System.out.println("Error: Class does not exist");
            return;
        }

        classes.get(className).removeMethod(methodName);
    }

    // Rename the Method called oldMethodName to newMethodName in the ClassObject called className
    // Print an error if there is no ClassObject with that name in the project
    public void renameMethod(String className, String oldMethodName, String newMethodName)
    {
        if (!classes.containsKey(className))
        {
            System.out.println("Error: Class does not exist");
            return;
        }

        classes.get(className).renameMethod(oldMethodName, newMethodName);
    }

    // Change the return type of the method called methodName to newMethodType in the ClassObject called className
    // Print an error if there is no ClassObject with that name in the project
    public void changeMethodType(String className, String methodName, String newMethodType)
    {
        if (!classes.containsKey(className))
        {
            System.out.println("Error: Class does not exist");
            return;
        }

        classes.get(className).changeMethodType(methodName, newMethodType);
    }

    // Add a new Parameter called paramName with the data type paramType to the Method called methodName in the ClassObject called className
    // Print an error if there is no ClassObject with that name in the project
    public void addParameter(String className, String methodName, String paramName, String paramType)
    {
        if (!classes.containsKey(className))
        {
            System.out.println("Error: Class does not exist");
            return;
        }

        classes.get(className).addParameter(methodName, paramName, paramType);
    }

    // Remove the Parameter called paramName from the method called methodName in the ClassObject called className
    // Print an error if there is no ClassObject with that name in the project
    public void removeParameter(String className, String methodName, String paramName)
    {
        if (!classes.containsKey(className))
        {
            System.out.println("Error: Class does not exist");
            return;
        }

        classes.get(className).removeParameter(methodName, paramName);
    }

    // Rename the Parameter called oldParamName to newParamName in the Method called methodName in the ClassObject called className
    // Print an error if there is no ClassObject with that name in the project
    public void renameParameter(String className, String methodName, String oldParamName, String newParamName)
    {
        if (!classes.containsKey(className))
        {
            System.out.println("Error: Class does not exist");
            return;
        }

        classes.get(className).renameParameter(methodName, oldParamName, newParamName);
    }

    // Change the data type of the Parameter called paramName to newParamType in the Method called methodName in the ClassObject called className
    // Print an error if there is no ClassObject with that name in the project
    public void changeParameterType(String className, String methodName, String paramName, String newParamType)
    {
        if (!classes.containsKey(className))
        {
            System.out.println("Error: Class does not exist");
            return;
        }

        classes.get(className).changeParameterType(methodName, paramName, newParamType);
    }

    // Add a new ordered Relationship from the ClassObject called classNameFrom to the ClassObject called classNameTo with the type specified by typeName
    // Print an error if either of those names are not found in the project
    // Print an error if there is already a Relationship between those classes in the project
    // Print an error if typeName does not specify a valid Relationship type
    public void addRelationship(String classNameFrom, String classNameTo, String typeName)
    {
        Relationship.relationshipType type = stringToRelationshipType(typeName);
        
        if (type == null)
        {
            System.out.println("Error: Not a valid relationship type");
            return;
        }

        if (!classes.containsKey(classNameFrom) || !classes.containsKey(classNameTo))
        {
            System.out.println("Error: One or both classes do not exist");
            return;
        }
        
        ClassObject classObjFrom = classes.get(classNameFrom);
        ClassObject classObjTo = classes.get(classNameTo);

        int index = getRelationshipIndex(classObjFrom, classObjTo);

        if (index != -1)
        {
            System.out.println("Error: Relationship already exists");
            return;
        }

        relationships.add(new Relationship(classObjFrom, classObjTo, type));
        System.out.println("Successfully added");
    }


    // Remove the relationship from the ClassObject called classNameFrom to the ClassObject called classNameTo
    // Print an error if either of those names are not found in the project
    // Print an error if there is no Relationship between those classes in the project
    public void removeRelationship(String classNameFrom, String classNameTo)
    {
        if (!classes.containsKey(classNameFrom) || !classes.containsKey(classNameTo))
        {
            System.out.println("Error: One or both classes do not exist");
            return;
        }

        ClassObject classObjFrom = classes.get(classNameFrom);
        ClassObject classObjTo = classes.get(classNameTo);

        int index = getRelationshipIndex(classObjFrom, classObjTo);

        if (index == -1)
        {
            System.out.println("Error: Relationship does not exist");
            return;
        }

        relationships.remove(index);
        System.out.println("Successfully removed");
    }

    // Change the type of a Relationship from the ClassObject called classNameFrom to the ClassObject called ClassNameTo to newTypeName
    // Print an error if either of those names are not found in the project
    // Print an error if newTypeName does not speicfy a valid Relationship type
    public void changeRelationshipType(String classNameFrom, String classNameTo, String newTypeName)
    {
        Relationship.relationshipType newType = stringToRelationshipType(newTypeName);
        
        if (newType == null)
        {
            System.out.println("Error: Not a valid relationship type");
            return;
        }

        if (!classes.containsKey(classNameFrom) || !classes.containsKey(classNameTo))
        {
            System.out.println("Error: One or both classes do not exist");
            return;
        }

        ClassObject classObjFrom = classes.get(classNameFrom);
        ClassObject classObjTo = classes.get(classNameTo);

        int index = getRelationshipIndex(classObjFrom, classObjTo);

        if (index == -1)
        {
            System.out.println("Error: Relationship does not exist");
            return;
        }

        relationships.get(index).setType(newType);
        System.out.println("Successfully changed type");
    }

    // Print the name of each ClassObject in the project
    public void printClasses()
    {
        for (ClassObject classObj : classes.values())
        {
            System.out.println(classObj.getName());
        }
    }

    // Print the names of the ClassObjects in each Relationship in the project
    // Print in the format className1 -> className2 (type)
    public void printRelationships()
    {
        for (Relationship relationship : relationships)
        {
            System.out.println(relationship.getClassFrom().getName() + " -> " + relationship.getClassTo().getName() + " (" + relationship.getType() + ")");
        }
    }

    // Print the Fields of the ClassObject called className
    // Print an error if there is no ClassObject with that name in the project
    public void printFields(String className)
    {
        if (!classes.containsKey(className))
        {
            System.out.println("Error: Class does not exist");
            return;
        }

        classes.get(className).printFields();
    }

    // Print the Methods of the ClassObject called className
    // Print an error if there is no ClassObject with that name in the project
    public void printMethods(String className)
    {
        if (!classes.containsKey(className))
        {
            System.out.println("Error: Class does not exist");
            return;
        }

        classes.get(className).printMethods();
    }

    // Print the ClassObject called className, including Fields and Methods
    // Print an error if there is no ClassObject with that name in the project
    public void printClass(String className)
    {
        if (!classes.containsKey(className))
        {
            System.out.println("Error: Class does not exist");
            return;
        }

        System.out.println(className);
        System.out.println("------------");
        classes.get(className).printFields();
        classes.get(className).printMethods();
    }

    // Return the index of the first ordered Relationship between classObj1 and classObj2 in the list
    // Return -1 if no Relationship is found
    private int getRelationshipIndex(ClassObject classObjFrom, ClassObject classObjTo)
    {
        for (int i = 0; i < relationships.size(); ++i)
        {
            if (relationships.get(i).getClassFrom() == classObjFrom && relationships.get(i).getClassTo() == classObjTo)
            {
                return i;
            }
        }
        return -1;
    }

    // Remove any relationships which contain classObj from the list
    private void removeRelationshipsByClass(ClassObject classObj)
    {
        for (Relationship relationship : relationships)
        {
            if (relationship.containsClass(classObj))
            {
                relationships.remove(relationship);
            }
        }
    }
    
    private Relationship.relationshipType stringToRelationshipType(String str)
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

    // Decode a JSONObject from the encoded String, then convert it back to a working project
    public void loadFromJSON(String jsonString)
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
            relationships.add(new Relationship(classes.get(classNameFrom), classes.get(classNameTo), type));
        }

        System.out.println("Successfully loaded");
    }
    
    // Convert working project into a JSONObject, then return its JSON encoding
    public String toJSONString ()
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
}