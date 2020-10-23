import org.junit.Test;
import org.json.simple.JSONObject;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;

public class MethodTest {

    String name;
    String type;
    Method m;
    @Test
    public void TestCreateMethod (String name, String type)
    {
        m = new Method("doSomething", "void");
    }

    @Test
    public void TestGetMethodName ()
    {
        assertTrue(null, m.getName().equals(name));
    }

    @Test
    public void TestGetMethodType ()
    {
        assertTrue(null, m.getType().equals(type));
    }

    @Test 
    public void TestSetMethodName ()
    {
        String newName;
        if (name.equals("doSomething"))
            newName = "doThat";
        else if (name.equals("doThat"))
            newName = "doSomething";
        else 
            newName = "doThat";
        
        m.setName(newName);
        assertTrue(null, m.getName().equals(newName));

    }

    @Test
    public void TestSetFieldType ()
    {
        String newType;
        if (type.equals("void"))
            newType = "String";
        else if (type.equals("String"))
            newType = "void";
        else 
            newType = "String";

        m.setType(newType);
        assertTrue(null, m.getType().equals(newType));
    }

    @Test
    public void TestAddParameter ()
    {
        m.addParameter("Thingy", "void");
        JSONObject expectedParam = new JSONObject();
        expectedParam.put("name", "Thingy");
        expectedParam.put("type", "void");
        JSONObject expectedMethod = new JSONObject();
        JSONObject actual = m.toJSON();
    }

    @Test
    public void TestRenameParameter()
    {
        m.renameParameter("Thingy", "Thingabob");
    }

    @Test
    public void TestChangeParameterType()
    {
        m.changeParameterType("Thingabob", "int");
    }

    @Test
    public void TestDeleteParameter()
    {
        m.removeParameter("Thingabob");
    }

    @Test
    public void TestToJSON ()
    {
        Method n = new Method("Attack", "void");
        n.addParameter("Damage", "int");
        Parameter p = new Parameter("Damage", "int");
        JSONObject actual = n.toJSON();
        JSONObject expected = new JSONObject();
        
        assertTrue(null, actual.toJSONString().equals(expected.toJSONString()));
    }

}