package model;

import org.json.simple.JSONObject;

/**
 * A field is a formal declaration contained in a class. Has a name, a data
 * type, and a visibility modifier.
 */

public class Field extends VisibleDeclaration {
    /**
     * Creates a new field.
     * @param name the name of the field, which must always match this field's
     *             key in the class
     * @param type the data type of the field
     * @param vis  the visibility of the field
     */
    public Field(String name, String type, VisibleDeclaration.visibility vis)
    {
        super(name, type, vis);
    }

    /**
     * Creates a String representation of the field as it would appear in Java.
     * @return the String representing this field
     */
    public String toString()
    {
        return vis.name().toLowerCase() + " " + type + " " + name;
    }

    /**
     *  Creates a copy of this field
     * @return the copy of this field
     */
    public Field copy()
    {
        return new Field(name, type, vis);
    }

    /**
     * Converts this field to a JSONObject.
     * @return a JSONObject representing this field
     */
    public JSONObject toJSON()
    {
        JSONObject jsonField = new JSONObject();

        jsonField.put("name", name);
        jsonField.put("type", type);
        jsonField.put("visibility", vis.name());

        return jsonField;
    }

   /**
    * Converts a JSONObject into a field.
    * @param jsonField a JSONObject representing a field
    * @return          the field represented by the JSONObject, or null if the
                       JSONObject does not encode a field
    */
    public static Field loadFromJSON(JSONObject jsonField)
    {
        String name = (String)jsonField.get("name");
        String type = (String)jsonField.get("type");
        String visibilityName = (String)jsonField.get("visibility");

        if (name == null || type == null || visibilityName == null)
        {
            return null;
        }

        visibility vis = ClassObject.stringToVisibility(visibilityName);

        if (vis == null)
        {
            return null;
        }

        return new Field(name, type, vis);
    }
}
