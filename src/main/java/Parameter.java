import org.json.simple.JSONObject;

/**
 * A parameter is a formal declaration contained in a method. Has a name and a
 * data type.
 */

public class Parameter extends FormalDeclaration {
    /**
     * Creates a new parameter.
     * @param name the name of the parameter
     * @param type the data type of the parameter
     */
    public Parameter(String name, String type)
    {
        super(name, type);
    }

    /**
     * Converts this parameter to a JSONObject.
     * @return a JSONObject representing ths parameter
     */
    public JSONObject toJSON()
    {
        JSONObject jsonParameter = new JSONObject();

        jsonParameter.put("name", getName());
        jsonParameter.put("type", getType());

        return jsonParameter;
    }
}
