import org.json.simple.JSONObject;

/**
 * A field is a formal declaration contained in a class. Has a name and a data
 * type.
 */

public class Field extends FormalDeclaration {
    /**
     * Creates a new field.
     * @param name the name of the field, which must always match this field's
     *             key in the class
     * @param type the data type of the field
     */
    public Field(String name, String type)
    {
        super(name, type);
    }

    /**
     * Converts this field to a JSONObject.
     * @return a JSONObject representing this field
     */
    public JSONObject toJSON()
    {
        JSONObject jsonField = new JSONObject();

        jsonField.put("name", getName());
        jsonField.put("type", getType());

        return jsonField;
    }

   /**
    * Converts a JSONObject into a field.
    * @param jsonField a JSONObject representing a field
    * @return          the field represented by the JSONObject
    */
    public static Field loadFromJSON(JSONObject jsonField)
    {
        String name = (String)jsonField.get("name");
        String type = (String)jsonField.get("type");

        return new Field(name, type);
    }
    public String toString()
    {
        return getType() + " " + getName();
    }

    public Field copy(){
        return new Field(getName(), getType());
    }
}
