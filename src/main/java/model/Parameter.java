package model;

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
     * Creates a String representation of the parameter as it would appear in
     * Java.
     * @return the String representing this parameter
     */
    public String toString()
    {
        return getType() + " " + getName();
    }

    /**
     * Creates a copy of this parameter
     * @return the copy of this parameter
     */
    public Parameter copy()
    {
        return new Parameter(getName(), getType());
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
