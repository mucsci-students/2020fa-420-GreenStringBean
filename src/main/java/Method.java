import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * A method is a formal declaration contained in a class. Has a name, a return
 * type, and an ordered list of parameters. Includes methods for creating and
 * modifying parameters. Methods use int return values to denote success or
 * failure as documented in the working project class.
 */

public class Method extends FormalDeclaration {
    private ArrayList<Parameter> parameters;

    /**
     * Creates a new method with no parameters.
     * @param name the name of the method, which must always match this
     *             method's key in the class
     * @param type the return type of the method
     */
    public Method(String name, String type)
    {
        super(name, type);
        parameters = new ArrayList<>();
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

        parameters.get(index).setType(newType);
        return 0;
    }

    // Print the list of Parameters, separated by commas and flanked with spaces
    public void printParameters()
    {
        if (!parameters.isEmpty())
        {
            Parameter currentParam = parameters.get(0);
            System.out.print(" " + currentParam.getType() + " " + currentParam.getName());
            for (int i = 1; i < parameters.size(); ++i)
            {
                currentParam = parameters.get(i);
                System.out.print(", " + currentParam.getType() + " " + currentParam.getName());
            }
            System.out.print(" ");
        }
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

        jsonMethod.put("name", getName());
        jsonMethod.put("type", getType());
        jsonMethod.put("parameters", jsonParameters);

        return jsonMethod;       
    }

    /**
     * Converts a JSONObject into a method.
     * @param jsonMethod a JSONObject representing a method
     * @return           the method represented by the JSONObject
     */
    public static Method loadFromJSON(JSONObject jsonMethod)
    {
        String name = (String)jsonMethod.get("name");
        System.out.println(name);
        String type = (String)jsonMethod.get("type");

        Method method = new Method(name, type);

        JSONArray jsonParameters = (JSONArray)jsonMethod.get("parameters");

        for (Object jsonParameter : jsonParameters)
        {
            String paramName = (String)((JSONObject)jsonParameter).get("name");
            String paramType = (String)((JSONObject)jsonParameter).get("type");
            method.parameters.add(new Parameter(paramName, paramType));
        }

        return method;
    }
}
