import org.junit.Test;
import org.json.simple.JSONObject;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import model.Parameter;

public class ParameterTest {

    String name;
    String type;
    Parameter p;
    @Test
    public void TestCreateParameter (String name, String type)
    {
        p = new Parameter("Distance", "double");
    }

    @Test
    public void TestGetParameterName ()
    {
        assertTrue(null, p.getName().equals(name));
    }

    @Test
    public void TestGetFieldType ()
    {
        assertTrue(null, p.getType().equals(type));
    }

    @Test 
    public void TestSetFieldName ()
    {
        String newName;
        if (name.equals("Distance"))
            newName = "Miles";
        else if (name.equals("Miles"))
            newName = "Distance";
        else 
            newName = "Miles";
        
        p.setName(newName);
        assertTrue(null, p.getName().equals(newName));

    }

    @Test
    public void TestSetFieldType ()
    {
        String newType;
        if (type.equals("double"))
            newType = "float";
        else if (type.equals("float"))
            newType = "double";
        else 
            newType = "float";

        p.setType(newType);
        assertTrue(null, p.getType().equals(newType));
    }

    @Test
    public void TestToJSON ()
    {
        Parameter q = new Parameter("Height", "float");
        JSONObject actual = q.toJSON();
        JSONObject expected = new JSONObject();
        expected.put("name", "Height");
        expected.put("type", "float");
        assertTrue(null, actual.toJSONString().equals(expected.toJSONString()));
    }

}