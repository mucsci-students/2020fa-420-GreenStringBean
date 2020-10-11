import org.json.simple.JSONObject;

public class Field extends FormalDeclaration {
    // Create a new Field with a name and data type
    public Field(String name, String type)
    {
        super(name, type);
    }

   public JSONObject toJSON()
   {
       JSONObject jsonField = new JSONObject();

       jsonField.put("name", getName());
       jsonField.put("type", getType());

       return jsonField;
   }

   public static Field loadFromJSON(JSONObject jsonField)
   {
        String name = (String)jsonField.get("name");
        String type = (String)jsonField.get("type");

        return new Field(name, type);
   }
}
