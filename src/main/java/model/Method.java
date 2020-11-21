package model;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 * A method is a formal declaration contained in a class. Has a name, a return
 * type, a visibility modifier, and an ordered list of parameters. Includes
 * methods for creating and modifying parameters. Methods use int return values
 * to denote success or failure as documented in the working project class.
 */

public class Method extends VisibleDeclaration {
    private ArrayList<Parameter> parameters;

    /**
     * Creates a new method with no parameters.
     * @param name the name of the method, which must always match this
     *             method's key in the class
     * @param type the return type of the method
     * @param vis  the visibility of the method
     */
    public Method(String name, String type, visibility vis)
    {
        super(name, type, vis);
        parameters = new ArrayList<>();
    }

    /**
     * Creates a new method with parameters.
     * @param name       the name of the method, which must always match this
     *                   method's key in the class
     * @param type       the return type of the method
     * @param vis        the visibility of the method
     * @param paramNames the names of the method's parameters
     * @param paramTypes the data types of the method's parameters
     */
    public Method(String name, String type, visibility vis, List<String> paramNames, List<String> paramTypes)
    {
        super(name, type, vis);
        parameters = new ArrayList<>();
        for (int i = 0; i < paramNames.size(); ++i)
        {
            parameters.add(new Parameter(paramNames.get(i), paramTypes.get(i)));
        }
    }
    
    /**
     * Adds a new parameter to the method.
     * @param paramName the name to be used by the new parameter
     * @param paramType the data type to be used by the new parameter
     * @return          0 if successful, error code otherwise
     */
    public int addParameter(String paramName, String paramType)
    {
        if (getParamIndex(paramName) != -1)
        {
            return 8;
        }

        if (!WorkingProject.isValidName(paramName))
        {
            return 9;
        }

        if (!WorkingProject.isValidDataType(paramType))
        {
            return 10;
        }

        parameters.add(new Parameter(paramName, paramType));
        return 0;
    }
    
    /**
     * Removes a parameter from the method, if it exists.
     * @param paramName the name of the parameter to be removed
     * @return          0 if successful, error code otherwise
     */
    public int removeParameter(String paramName)
    {
        int index = getParamIndex(paramName);
        if (index == -1)
        {
            return 5;
        }

        parameters.remove(index);
        return 0;
    }
    
    /**
     * Renames a parameter, if it exists.
     * @param oldParamName the current name of the parameter to rename
     * @param newParamName the new name to give to the parameter
     * @return             0 if successful, error code otherwise
     */
    public int renameParameter(String oldParamName, String newParamName)
    {
        int oldIndex = getParamIndex(oldParamName);
        if (oldIndex == -1)
        {
            return 5;
        }
        
        int newIndex = getParamIndex(newParamName);
        if (newIndex != -1)
        {
            return 8;
        }

        if (!WorkingProject.isValidName(newParamName))
        {
            return 9;
        }

        parameters.get(oldIndex).setName(newParamName);
        return 0;
    }
    
    /**
     * Changes the data type of a parameter, if it exists.
     * @param paramName the name of the parameter to modify
     * @param newType   the new data type to give to the parameter
     * @return          0 if successful, error code otheriwse
     */
    public int changeParameterType(String paramName, String newType)
    {
        int index = getParamIndex(paramName);
        if (index == -1)
        {
            return 5;
        }

        if (!WorkingProject.isValidDataType(newType))
        {
            return 10;
        }

        parameters.get(index).setType(newType);
        return 0;
    }

    /**
     * Mutator for the entire parameter list
     * @param paramNames the list of new parameter names
     * @param paramTypes the list of new parameter data types
     */
    public void setParameters(List<String> paramNames, List<String> paramTypes)
    {
        parameters.clear();
        for (int i = 0; i < paramNames.size(); ++i)
        {
            parameters.add(new Parameter(paramNames.get(i), paramTypes.get(i)));
        }
    }

    /**
     * Creates a String representation of the method as it would appear in java
     * @return the String representing this method
     */
    public String toString()
    {
        String methodString = vis.name().toLowerCase() + " " + type + " " + name + "("; 
        if(!parameters.isEmpty())
        {
            Parameter currentParam = parameters.get(0);
            methodString += " " + currentParam.toString();
            for (int i = 1; i < parameters.size(); ++i)
            {
                currentParam = parameters.get(i);
                methodString += ", " + currentParam.toString();
            }
            methodString += " ";
        }
        return methodString + ")";
    }

    /**
     * Returns true if the method contains a parameter called paramName
     * @param paramName the name of the parameter to search for
     * @return          true if the parameter is found, false otherwise
     */
    public boolean hasParameter(String paramName)
    {
        return (getParamIndex(paramName) != -1);
    }

    /**
     * Accessor for the parameter list
     * @return the list of parameters
     */
    public List<Parameter> getParameters(){
        return parameters;
    }

    /**
     * Accessor for the list of parameter names
     * @return the list of parameter names
     */
    public List<String> getParameterNames(){
        ArrayList<String> paramNames = new ArrayList<>();
        for (Parameter p : parameters)
        {
            paramNames.add(p.getName());
        }
        return paramNames;
    }

    /**
     * Creates a copy of this method
     * @return the copy of this method
     */
    public Method copy()
    {
        Method copy = new Method(name, type, vis);
        copy.parameters.addAll(parameters);
        copy.parameters.replaceAll(parameter -> parameter.copy());
        return copy;
    }

    /**
     * Converts this method to a JSONObject.
     * @return a JSONObject representing the method
     */
    public JSONObject toJSON()
    {
        JSONObject jsonMethod = new JSONObject();

        JSONArray jsonParameters = new JSONArray();
        for (Parameter parameter : parameters)
        {
            jsonParameters.add(parameter.toJSON());
        }

        jsonMethod.put("name", name);
        jsonMethod.put("type", type);
        jsonMethod.put("visibility", vis.name());
        jsonMethod.put("parameters", jsonParameters);

        return jsonMethod;       
    }

    /**
     * Converts a JSONObject into a method.
     * @param jsonMethod a JSONObject representing a method
     * @return           the method represented by the JSONObject, or null if
     *                   the JSONObject does not encode a method
     */
    public static Method loadFromJSON(JSONObject jsonMethod)
    {
        String name = (String)jsonMethod.get("name");
        String type = (String)jsonMethod.get("type");
        String visName = (String)jsonMethod.get("visibility");

        if (name == null || type == null || visName == null)
        {
            return null;
        }

        visibility vis = ClassObject.stringToVisibility(visName);

        if (vis == null)
        {
            return null;
        }

        Method method = new Method(name, type, vis);
        JSONArray jsonParameters = (JSONArray)jsonMethod.get("parameters");

        if (jsonParameters == null)
        {
            return null;
        }

        for (Object jsonParameter : jsonParameters)
        {
            String paramName = (String)((JSONObject)jsonParameter).get("name");
            String paramType = (String)((JSONObject)jsonParameter).get("type");

            if (paramName == null || paramType == null || method.getParamIndex(paramName)!=-1)
            {
                return null;
            }

            method.parameters.add(new Parameter(paramName, paramType));
        }

        return method;
    }
    
    /**
     * Finds a parameter in the list.
     * @param paramName the name of the parameter to search for
     * @return          the index of the parameter in the list, or -1 if not
     *                  found
     */
    private int getParamIndex(String paramName)
    {
        for(int i = 0; i < parameters.size(); i++)
        {
            if(parameters.get(i).getName().equals(paramName))
                return i;
        }
        return -1;
    }
}

