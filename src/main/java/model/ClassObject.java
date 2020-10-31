package model;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import model.VisibleDeclaration.visibility;
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
    private LinkedHashMap<String, Field> fields;
    private LinkedHashMap<String, Method> methods;

    private List<Observer> observers;

    /**
     * Creates a new open class with no fields or methods.
     * @param name the name of the class, which must always match this class's
     *             key in the working project
     */
    public ClassObject(String name)
    {
        this.name = name;
        isOpen = true;
        fields = new LinkedHashMap<>();
        methods = new LinkedHashMap<>();

        observers = new ArrayList<>();
    }
    
    /**
     * Attaches an observer to this class, to be notified on state changes.
     * @param observer the observer to attach
     */
    public void attach (Observer observer)
    {
        observers.add(observer);
    }

    /**
     * Detaches an observer from this class.
     * @param observer the observer to detach
     */
    public void detach (Observer observer)
    {
        observers.remove(observer);
    }

    /**
     * Notifies all observers, by calling their onUpdate method. Sends each
     * observer a copy of this class, so that this class cannot be modified by
     * an observer.
     */
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
        notifyAllObservers();
    }

    /**
     * Opens the class for editing
     */
    public void open()
    {
        isOpen = true;
        notifyAllObservers();
    }

    /**
     * Adds a new field to the class.
     * @param fieldName    the name to be used by the new field
     * @param fieldType    the data type to be used by the new field
     * @param fieldVisName the visibility to be used by the new field
     * @return             0 if successful, error code otherwise
     */
    public int addField(String fieldName, String fieldType, String fieldVisName)
    {
        if (!isOpen)
        {
            return 1;
        }

        visibility fieldVis = stringToVisibility(fieldVisName);
        if (fieldVis == null)
        {
            return 14;
        }

        if (fields.containsKey(fieldName))
        {
            return 8;
        }

        fields.put(fieldName, new Field(fieldName, fieldType, fieldVis));
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
     * Renames a field, if it exists. (Preserves order)
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

        LinkedHashMap<String, Field> tempFields = new LinkedHashMap<>();
        for (Map.Entry<String, Field> fieldEntry: fields.entrySet())
        {
            if (fieldEntry.getKey().equals(oldFieldName))
            {
                Field field = fieldEntry.getValue();
                field.setName(newFieldName);
                tempFields.put(newFieldName, field);
            }
            else
            {
                tempFields.put(fieldEntry.getKey(), fieldEntry.getValue());
            }
        }
        fields = tempFields;
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
     * Changes the visibility of a field, if it exists.
     * @param fieldName       the name of the field to modify
     * @param newFieldVisName the new visibility modifier to give to the field
     * @return                0 if successful, error code otherwise
     */
    public int changeFieldVisibility(String fieldName, String newFieldVisName)
    {
        if (!isOpen)
        {
            return 1;
        }
        visibility newFieldVis = stringToVisibility(newFieldVisName);
        if(newFieldVis == null)
        {
            return 14;
        }

        if (!fields.containsKey(fieldName))
        {
            return 3;
        }

        fields.get(fieldName).setVisibility(newFieldVis);

        notifyAllObservers();
        return 0;
    }
    
    /**
     * Adds a new method to the class.
     * @param methodName    the name to be used by the new method
     * @param methodType    the return type to be used by the new method
     * @param methodVisName the visibility to be used by the new method
     * @return              0 if successful, error code otherwise
     */
    public int addMethod(String methodName, String methodType, String methodVisName)
    {
        if (!isOpen)
        {
            return 1;
        }

        visibility methodVis = stringToVisibility(methodVisName);
        if (methodVis == null)
        {
            return 14;
        }

        if (methods.containsKey(methodName))
        {
            return 8;
        }

        methods.put(methodName, new Method(methodName, methodType, methodVis));
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

        LinkedHashMap<String, Method> tempMethods = new LinkedHashMap<>();
        for (Map.Entry<String, Method> methodEntry: methods.entrySet())
        {
            if (methodEntry.getKey().equals(oldMethodName))
            {
                Method method = methodEntry.getValue();
                method.setName(newMethodName);
                tempMethods.put(newMethodName, method);
            }
            else
            {
                tempMethods.put(methodEntry.getKey(), methodEntry.getValue());
            }
        }
        methods = tempMethods;
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
     * Changes the visibility type of a method, if it exists.
     * @param methodName       the name of the method to modify
     * @param newMethodVisName the new visibility to give to the method
     * @return                 0 if successful, error code otherwise
     */
    public int changeMethodVisibility(String methodName, String newMethodVisName)
    {
        if (!isOpen)
        {
            return 1;
        }
        visibility methodVis = stringToVisibility(newMethodVisName);
        if(methodVis == null)
        {
            return 14;
        }

        if (!methods.containsKey(methodName))
        {
            return 3;
        }

        methods.get(methodName).setVisibility(methodVis);

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

    /**
     * Prints all fields in this class. (Legacy)
     */
    public void printFields()
    {
        for (Field field : fields.values())
        {
            System.out.println(field.toString());
        }
    }

    /**
     * Prints all the methods in this class. (Legacy)
     */
    public void printMethods()
    {
        for (Method method : methods.values())
        {
            System.out.println(method.toString());
        }
    }

    /**
     * Converts this class into a JSONObject.
     * @return a JSONObject representing this class
     */
    public JSONObject toJSON()
    {
        JSONObject jsonClass = new JSONObject();

        JSONArray jsonFields = new JSONArray();
        for (String fieldName : fields.keySet())
        {
            jsonFields.add(fields.get(fieldName).toJSON());
        }

        JSONArray jsonMethods = new JSONArray();
        for (String methodName : methods.keySet())
        {
            jsonMethods.add(methods.get(methodName).toJSON());
        }

        jsonClass.put("name", name);
        jsonClass.put("isOpen", isOpen);
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
        classObj.isOpen = (boolean)jsonClass.get("isOpen");
        
        JSONArray jsonFields = (JSONArray)jsonClass.get("fields");
        JSONArray jsonMethods = (JSONArray)jsonClass.get("methods");

        for (Object jsonField : jsonFields)
        {
            classObj.fields.put((String)(((JSONObject)jsonField).get("name")), Field.loadFromJSON((JSONObject)jsonField));
        }

        for (Object jsonMethod : jsonMethods)
        {
            classObj.methods.put((String)(((JSONObject)jsonMethod).get("name")), Method.loadFromJSON((JSONObject)jsonMethod));
        }

        return classObj;
    }

    /**
     * Accessor for the set of field names in this class
     * @return the set of field names
     */
    public Set<String> getFieldNames()
    {
        return fields.keySet();
    }

    /**
     * Returns true if the class contains a field called fieldName
     * @param fieldName the name to search for
     * @return          true if the field is found, false otherwise
     */
    public boolean hasField(String fieldName)
    {
        return fields.containsKey(fieldName);
    }
    
    /**
     * Accesses a field in the class by name.
     * @param fieldName the name of the field to access
     * @return          the field called fieldName, or null if none exists
     */
    public Field getField(String fieldName)
    {
        if(fields.containsKey(fieldName))
        {
            return fields.get(fieldName);
        }
        return null;
    }

    /**
     * Accessor for the set of method names in this class
     * @return the set of method names
     */
    public Set<String> getMethodNames()
    {
        return methods.keySet();
    }

    /**
     * Returns true if the class contains a method called methodName
     * @param methodName the name to search for
     * @return           true if the method is found, false otherwise
     */
    public boolean hasMethod(String methodName)
    {
        return methods.containsKey(methodName);
    }
    
    /**
     * Accesses a method in the class by name.
     * @param methodName the name of the method to access
     * @return           the method called methodName, or null if none exists
     */
    public Method getMethod(String methodName){
        if(methods.containsKey(methodName)){
            return methods.get(methodName);
        }
        return null;
    }

    /**
     * Creates a copy of this class object
     * @return the copy of this class object
     */
    public ClassObject copy()
    {
        ClassObject copy = new ClassObject(name);
        copy.isOpen = isOpen;
        copy.fields.putAll(fields);
        copy.fields.replaceAll((fieldName, field) -> field.copy());
        copy.methods.putAll(methods);
        copy.methods.replaceAll((methodName, method) -> method.copy());
        return copy;
    }

    /**
     * Converts a string to the associated visibility modifier. Not case
     * sensitive.
     * @param str the String to convert to a visibility modifier
     * @return    the String's associated visibility modifier, or null if the
     *            String is not a visibility modifier
     */
    public static visibility stringToVisibility(String str)
    {
        str = str.toUpperCase();
        switch(str)
        {
            case "PUBLIC":
                return visibility.PUBLIC;
            case "PRIVATE":
                return visibility.PRIVATE;
            case "PROTECTED":
                return visibility.PROTECTED;
            default :
                return null;
        }
    }

}