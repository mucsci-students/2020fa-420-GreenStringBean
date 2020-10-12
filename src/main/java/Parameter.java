import org.json.simple.JSONObject;

public class Parameter extends FormalDeclaration {
    public Parameter(String name, String type)
    {
        super(name, type);
    }

   public JSONObject toJSON()
   {
        JSONObject jsonParameter = new JSONObject();

        jsonParameter.put("name", getName());
        jsonParameter.put("type", getType());

        return jsonParameter;
   }
}
