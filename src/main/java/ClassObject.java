import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONAware;

public class ClassObject {   
    private String name;
    private boolean isOpen;
    private HashMap<String, Field> fields;
    private HashMap<String, Method> methods;

    // Create a new ClassObject with empty Field/Method lists
    public ClassObject(String name)
    {
        this.name = name;
        isOpen = false;
        fields = new HashMap<>();
        methods = new HashMap<>();
    }

    // Return the name of the class
    public String getName()
    {
        return name;
    }

    // Change the name of the class to newName
    public void setName(String newName)
    {
        name = newName;
    }

    // Return true if the class is open for editing
    public boolean isOpen()
    {
        return isOpen;
    }

    // Close the class from editing
    public void close()
    {
        isOpen = false;
    }

    // Open the class for editing
    public void open()
    {
        isOpen = true;
    }

    // Add a new Field called fieldName with the data type fieldType to the class
    // Print an error if the class is closed from editing
    // Print an error if there is already a Field with that name in the class
    public void addField(String fieldName, String fieldType)
    {
        if (!isOpen)
        {
            System.out.println("Error: Class is not open");
            return;
        }

        if (fields.containsKey(fieldName))
        {
            System.out.println("Error: Name is already in use");
            return;
        }

        fields.put(fieldName, new Field(fieldName, fieldType));
        System.out.println("Successfully added");
    }

    // Remove the Field called fieldName from the class
    // Print an error if the class is closed from editing
    // Print an error if there is no Field with that name in the class
    public void removeField(String fieldName)
    {
        if (!isOpen)
        {
            System.out.println("Error: Class is not open");
            return;
        }

        if (!fields.containsKey(fieldName))
        {
            System.out.println("Error: Field does not exist");
            return;
        }

        fields.remove(fieldName);
        System.out.println("Successfully removed");
    }

    // Rename the Field called oldFieldName to newFieldName
    // Print an error if the class is closed from editing
    // Print an error if there is no Field caled oldFieldName in the class
    // Print an error if there is already a Field called newFieldName in the class
    public void renameField(String oldFieldName, String newFieldName)
    {
        if (!isOpen)
        {
            System.out.println("Error: Class is not open");
            return;
        }

        if (!fields.containsKey(oldFieldName))
        {
            System.out.println("Error: Field does not exist");
            return;
        }

        if (fields.containsKey(newFieldName))
        {
            System.out.println("Error: Name is already in use");
            return;
        }

        Field renamedField = fields.remove(oldFieldName);
        renamedField.setName(newFieldName);
        fields.put(newFieldName, renamedField);
        System.out.println("Successfully removed");
    }

    // Change the data type of the Field called fieldName to newFieldType
    // Print an error if the class is closed from editing
    // Print an error if there is no Field called fieldName in the class
    public void changeFieldType(String fieldName, String newFieldType)
    {
        if (!isOpen)
        {
            System.out.println("Error: Class is not open");
            return;
        }

        if (!fields.containsKey(fieldName))
        {
            System.out.println("Error: Field does not exist");
            return;
        }

        fields.get(fieldName).setType(newFieldType);
        System.out.println("Successfully changed type");
    }

    // Add a new Method called methodName with the return type methoddType to the class
    // Print an error if the class is closed from editing
    // Print an error if there is already a method with that name in the class
    public void addMethod(String methodName, String methodType)
    {
        if (!isOpen)
        {
            System.out.println("Error: Class is not open");
            return;
        }

        if (methods.containsKey(methodName))
        {
            System.out.println("Error: Name is already in use");
            return;
        }

        methods.put(methodName, new Method(methodName, methodType));
        System.out.println("Successfully added");
    }

    // Remove the Method called methodName from the class
    // Print an error if the class is closed from editing
    // Print an error if there is no Method with that name in the class
    public void removeMethod(String methodName)
    {
        if (!isOpen)
        {
            System.out.println("Error: Class is not open");
            return;
        }

        if (!methods.containsKey(methodName))
        {
            System.out.println("Error: Method does not exist");
            return;
        }

        methods.remove(methodName);
        System.out.println("Successfully removed");
    }

    // Rename the Method called oldMethodName to newMethodName
    // Print an error if the class is closed from editing
    // Print an error if there is no Method caled oldMethodName in the class
    // Print an error if there is already a Method called newMethodName in the class
    public void renameMethod(String oldMethodName, String newMethodName)
    {
        if (!isOpen)
        {
            System.out.println("Error: Class is not open");
            return;
        }

        if (!methods.containsKey(oldMethodName))
        {
            System.out.println("Error: Method does not exist");
            return;
        }

        if (methods.containsKey(newMethodName))
        {
            System.out.println("Error: Name is already in use");
            return;
        }

        Method renamedMethod = methods.remove(oldMethodName);
        renamedMethod.setName(newMethodName);
        methods.put(newMethodName, renamedMethod);
        System.out.println("Successfully removed");
    }

    // Change the return type of the Method called methodName to newMethodType
    // Print an error if the class is closed from editing
    // Print an error if there is no Method called methodName in the class
    public void changeMethodType(String methodName, String newMethodType)
    {
        if (!isOpen)
        {
            System.out.println("Error: Class is not open");
            return;
        }

        if (!methods.containsKey(methodName))
        {
            System.out.println("Error: Method does not exist");
            return;
        }

        methods.get(methodName).setType(newMethodType);
        System.out.println("Successfully changed type");
    }

    // Add a new Parameter called paramName with the data type paramType to the Method called methodName
    // Print an error if the class is closed from editing
    // Print an error if there is no Method with that name in the class
    public void addParameter(String methodName, String paramName, String paramType)
    {
        if (!methods.containsKey(methodName))
        {
            System.out.println("Error: Method does not exist");
            return;
        }

        methods.get(methodName).addParameter(paramName, paramType);
    }

    // Remove the Parameter called paramName from the Method called methodName
    // Print an error if the class is closed from editing
    // Print an error if there is no Method with that name in the class
    public void removeParameter(String methodName, String paramName)
    {
        if (!methods.containsKey(methodName))
        {
            System.out.println("Error: Method does not exist");
            return;
        }

        methods.get(methodName).removeParameter(paramName);
    }

    // Rename the Parameter called oldParamName to newParamName in the Method called methodName
    // Print an error if the class is closed from editing
    // Print an error if there is no Method with that name in the class
    public void renameParameter(String methodName, String oldParamName, String newParamName)
    {
        if (!methods.containsKey(methodName))
        {
            System.out.println("Error: Method does not exist");
            return;
        }

        methods.get(methodName).renameParameter(oldParamName, newParamName);
    }

    // Change the data type of the Parameter called paramName to newParamType in the Method called methodName
    // Print an error if the class is closed from editing
    // Print an error if there is no Method with that name in the class
    public void changeParameterType(String methodName, String paramName, String newParamType)
    {
        if (!methods.containsKey(methodName))
        {
            System.out.println("Error: Method does not exist");
            return;
        }

        methods.get(methodName).changeParameterType(paramName, newParamType);
    }

    public void printFields()
    {
        for (Field field : fields.values())
        {
            System.out.println(field.getType() + " " + field.getName());
        }
    }

    public void printMethods()
    {
        for (Method method : methods.values())
        {
            System.out.print(method.getType() + " " + method.getName() + "(");
            method.printParameters();
            System.out.println(")");
        }
    }

    public JSONObject toJSON()
    {
        JSONObject jsonClass = new JSONObject();

        JSONObject jsonFields = new JSONObject();
        for (String fieldName : fields.keySet())
        {
            jsonFields.put(fieldName, fields.get(fieldName).toJSON());
        }

        JSONObject jsonMethods = new JSONObject();
        for (String methodName : methods.keySet())
        {
            jsonMethods.put(methodName, methods.get(methodName).toJSON());
        }

        jsonClass.put("name", name);
        jsonClass.put("fields", jsonFields);
        jsonClass.put("methods", jsonMethods);

        return jsonClass;
    }

    public static ClassObject loadFromJSON(JSONObject jsonClass)
    {
        String name = (String)jsonClass.get("name");
        
        ClassObject classObj = new ClassObject(name);
        
        JSONObject jsonFields = (JSONObject)jsonClass.get("fields");
        JSONObject jsonMethods = (JSONObject)jsonClass.get("methods");

        for (Object fieldName : jsonFields.keySet())
        {
            classObj.fields.put((String)fieldName, Field.loadFromJSON((JSONObject)jsonFields.get(fieldName)));
        }

        for (Object methodName : jsonMethods.keySet())
        {
            classObj.methods.put((String)methodName, Method.loadFromJSON((JSONObject)jsonMethods.get(methodName)));
        }

        return classObj;
    }
}
