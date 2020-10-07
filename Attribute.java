import org.json.simple.JSONAware;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Attribute
{
   private String name;
   public Attribute(String attrName)
   {
       name = attrName;
   } 
   public String getName()
   {
       return name;
   }
   public void setName(String newName)
   {
       name = newName;
   }

   //JSON ENCODER
   public String toJSONString ()
   {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"" + "Name" + "\"");
        sb.append(":");
        sb.append("\"" + this.getName() + "\"");
        //sb.append(",");
        //sb.append("\"" + "Type" + "\"");
        //sb.append(":");
        //sb.append("\"" + this.getType() + "\"");
        sb.append("}");
        return sb.toString();
   }
   
}