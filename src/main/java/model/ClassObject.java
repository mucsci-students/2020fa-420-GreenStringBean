package model;

import java.util.HashMap;
import org.json.simple.JSONObject;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import view.Observer;

/**
 * The class object represents a UML class containing fields and methods.
 * Includes methods for creating and modifying fields and methods, as well as
 * the parameters of methods. The class can only be modified when it is open.
 * Methods use int return values to denote success or failure as documetned in
 * the working project class.
 */

public class ClassObject{   
    private String name;
    private boolean isOpen;
    private HashMap<String, Field> fields;
    private HashMap<String, Method> methods;

    private List<Observer> observers;

    /**
     * Creates a new closed class with no fields or methods.
     * @param name the name of the class, which must always match this class's
     *             key in the working project
     */
    public ClassObject(String name)
    {
        this.name = name;
        isOpen = false;
        fields = new HashMap<>();
        methods = new HashMap<>();

        observers = new ArrayList<>();
    }

    public void attach (Observer observer)
    {
        observers.add(observer);
    }

    public void detach (Observer observer)
    {
        observers.remove(observer);
    }

    private void notifyAllObservers()
    {
        observers.forEach(o -> o.onUpdate(copy()));
    }

    /**
     * Accessor for the class name.
     * @return the name of the class
     */
    public String getName()
    {
        return name;
    }

    /**
     * Mutator for the class name.
     * @param name the new name to give the class
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Checks if the class is open for editing
     * @return true if the class is open, false if it is closed
     */
    public boolean isOpen()
    {
        return isOpen;
    }

    /**
     * Closes the class from editing
     */
    public void close()
    {
        isOpen = false;
    }

    /**
     * Opens the class for editing
     */
    public void open()
    {
        isOpen = true;
    }

    /**
     * Adds a new field to the class.
     * @param fieldName the name to be used by the new field
     * @param fieldType the data type to be used by the new field
     * @return          0 if successful, error code otherwise
     */
    public int addField(String fieldName, String fieldType)
    {
        if (!isOpen)
        {
            return 1;
        }

        if (fields.containsKey(fieldName))
        {
            return 8;
        }

        fields.put(fieldName, new Field(fieldName, fieldType));
        notifyAllObservers();
        return 0;
    }

    /**
     * Removes a field from the class, if it exists.
     * @param fieldName the name of the field to be removed
     * @return          0 if successful, error code otherwise
     */
    public int removeField(String fieldName)
    {
        if (!isOpen)
        {
            return 1;
        }

        if (!fields.containsKey(fieldName))
        {
            return 3;
        }

        fields.remove(fieldName);
        notifyAllObservers();
        return 0;
    }
    
    /**
     * Renames a field, if it exists.
     * @param oldFieldName the current name of the field to rename
     * @param newFieldName the new name to give to the field
     * @return             0 if successful, error code otherwise
     */
    public int renameField(String oldFieldName, String newFieldName)
    {
        if (!isOpen)
        {
            return 1;
        }

        if (!fields.containsKey(oldFieldName))
        {
            return 3;
        }

        if (fields.containsKey(newFieldName))
        {
            return 8;
        }

        Field renamedField = fields.remove(oldFieldName);
        renamedField.setName(newFieldName);
        fields.put(newFieldName, renamedField);
        notifyAllObservers();
        return 0;
    }
    
    /**
     * Changes the data type of a field, if it exists.
     * @param fieldName    the name of the field to modify
     * @param newFieldType the new data type to give to the field
     * @return             0 if successful, error code otherwise
     */
    public int changeFieldType(String fieldName, String newFieldType)
    {
        if (!isOpen)
        {
            return 1;
        }

        if (!fields.containsKey(fieldName))
        {
            return 3;
        }

        fields.get(fieldName).setType(newFieldType);
        notifyAllObservers();
        return 0;
    }
    
    /**
     * Adds a new method to the class.
     * @param methodName the name to be used by the new method
     * @param methodType the return type to be used by the new method
     * @return           0 if successful, error code otherwise
     */
    public int addMethod(String methodName, String methodType)
    {
        if (!isOpen)
        {
            return 1;
        }

        if (methods.containsKey(methodName))
        {
            return 8;
        }

        methods.put(methodName, new Method(methodName, methodType));
        notifyAllObservers();
        return 0;
    }
    
    /**
     * Removes a method from the class, if it exists.
     * @param methodName the name of the method to remove
     * @return           0 if successful, error code otherwise
     */
    public int removeMethod(String methodName)
    {
        if (!isOpen)
        {
            return 1;
        }

        if (!methods.containsKey(methodName))
        {
            return 4;
        }

        methods.remove(methodName);
        notifyAllObservers();
        return 0;
    }

    /**
     * Renames a method, if it exists.
     * @param oldMethodName the current name of the method to rename
     * @param newMethodName the new name to give to the method
     * @return              0 if successful, error code otherwise
     */
    public int renameMethod(String oldMethodName, String newMethodName)
    {
        if (!isOpen)
        {
            return 1;
        }

        if (!methods.containsKey(oldMethodName))
        {
            return 4;
        }

        if (methods.containsKey(newMethodName))
        {
            return 8;
        }

        Method renamedMethod = methods.remove(oldMethodName);
        renamedMethod.setName(newMethodName);
        methods.put(newMethodName, renamedMethod);
        notifyAllObservers();
        return 0;
    }
    
    /**
     * Changes the return type of a method, if it exists.
     * @param methodName    the name of the method to modify
     * @param newMethodType the new return type to give to the method
     * @return              0 if successful, error code otherwise
     */
    public int changeMethodType(String methodName, String newMethodType)
    {
        if (!isOpen)
        {
            return 1;
        }

        if (!methods.containsKey(methodName))
        {
            return 4;
        }

        methods.get(methodName).setType(newMethodType);
        notifyAllObservers();
        return 0;
    }
    
    /**
     * Adds a new parameter to a method.
     * @param methodName the name of the method to add a parameter to
     * @param paramName  the name to be used by the new parameter
     * @param paramType  the data type to be used by the new parameter
     * @return           0 if successful, error code otherwise
     */
    public int addParameter(String methodName, String paramName, String paramType)
    {
        if (!methods.containsKey(methodName))
        {
            return 4;
        }

        int retVal = methods.get(methodName).addParameter(paramName, paramType);
        if(retVal == 0)
            notifyAllObservers();
        return retVal;
    }
    
    /**
     * Removes a parameter from a method, if it exists.
     * @param methodName the name of the method to remove a parameter from
     * @param paramName  the name of the parameter to remove
     * @return           0 if successful, error code otherwise
     */
    public int removeParameter(String methodName, String paramName)
    {
        if (!methods.containsKey(methodName))
        {
            return 4;
        }

        int retVal = methods.get(methodName).removeParameter(paramName);
        if(retVal == 0)
            notifyAllObservers();
        return retVal;
    }

    /**
     * Renames a parameter, if it exists.
     * @param methodName   the name of the method with the parameter to rename
     * @param oldParamName the current name of the parameter to rename
     * @param newParamName the new name to give to the parameter
     * @return             0 if successful, error code otherwise
     */
    public int renameParameter(String methodName, String oldParamName, String newParamName)
    {
        if (!methods.containsKey(methodName))
        {
            return 4;
        }

        int retVal = methods.get(methodName).renameParameter(oldParamName, newParamName);
        if(retVal == 0)
            notifyAllObservers();
        return retVal;
        
    }
    
    /**
     * Changes the data type of a parameter, if it exists.
     * @param methodName   the name of the method with the parameter to modify
     * @param paramName    the name of the parameter to modify
     * @param newParamType the new data type to give to the parameter
     * @return             0 if successful, error code otherwise
     */
    public int changeParameterType(String methodName, String paramName, String newParamType)
    {
        if (!methods.containsKey(methodName))
        {
            return 4;
        }

        int retVal = methods.get(methodName).changeParameterType(paramName, newParamType);
        if(retVal == 0)
            notifyAllObservers();
        return retVal;
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

    /**
     * Converts this class into a JSONObject.
     * @return a JSONObject representing this class
     */
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

    /**
     * Converts a JSONObject into a class.
     * @param jsonClass a JSONObject representing a class
     * @return          the class represented by the JSONObject
     */
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

    public Set<String> getFieldNames(){
        return fields.keySet();
    }

    public boolean hasField(String fieldName)
    {
        return fields.containsKey(fieldName);
    }
    
    public Field getField(String fieldName){
        if(fields.containsKey(fieldName)){
            return fields.get(fieldName);
        }
        return null;
    }

    public Set<String> getMethodNames(){
        return methods.keySet();
    }

    public boolean hasMethod(String methodName)
    {
        return methods.containsKey(methodName);
    }
    
    public Method getMethod(String methodName){
        if(methods.containsKey(methodName)){
            return methods.get(methodName);
        }
        return null;
    }

    public ClassObject copy(){
        ClassObject copy = new ClassObject(name);
        copy.fields.putAll(fields);
        copy.fields.replaceAll((fieldName, field)->field.copy());
        copy.methods.putAll(methods);
        copy.methods.replaceAll((methodName, method)->method.copy());
        return copy;
    }


}
