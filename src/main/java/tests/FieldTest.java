import org.junit.Test;
import org.json.simple.JSONObject;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import model.Field;
import model.*;
public class FieldTest {

    String name;
    String type;
    Field f;
    @Test
    public void TestCreateField ()
    {
        Field f = new Field("SSN", "int", ClassObject.stringToVisibility("public"));
    }

    @Test
    public void TestGetFieldName ()
    {
        assertTrue(null, f.getName().equals(name));
    }

    @Test
    public void TestGetFieldType ()
    {
        assertTrue(null, f.getType().equals(type));
    }

    @Test 
    public void TestSetFieldName ()
    {
        String newName;
        if (name.equals("SSN"))
            newName = "Routing_Number";
        else if (name.equals("Routing_Number"))
            newName = "SSN";
        else 
            newName = "Routing_Number";
        
        f.setName(newName);
        assertTrue(null, f.getName().equals(newName));

    }

    @Test
    public void TestSetFieldType ()
    {
        String newType;
        if (type.equals("int"))
            newType = "short";
        else if (type.equals("short"))
            newType = "int";
        else 
            newType = "short";

        f.setType(newType);
        assertTrue(null, f.getType().equals(newType));
    }

    @Test
    public void TestToJSON ()
    {
        Field g = new Field("Building", "Height", ClassObject.stringToVisibility("public"));
        JSONObject actual = g.toJSON();
        JSONObject expected = new JSONObject();
        expected.put("name", "Building");
        expected.put("type", "Height");
        assertTrue(null, actual.toJSONString().equals(expected.toJSONString()));
    }

}