import org.junit.Test;
import org.json.simple.JSONObject;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import model.Method;
import model.Parameter;


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
    public void TestAddParameter ()
    {
        m.addParameter("Thingy", "void");
        assertTrue(m.getParameters().contains(new Parameter("Thingy", "void")));
    }

    @Test
    public void TestRenameParameter()
    {
        m.renameParameter("Thingy", "Thingabob");
        assertTrue(m.getParameters().contains(new Parameter("Thingabob", "void")));
    }

    @Test
    public void TestChangeParameterType()
    {
        m.changeParameterType("Thingabob", "int");
        assertTrue(m.getParameters().contains(new Parameter("Thingabob", "int")));
    }

    @Test
    public void TestDeleteParameter()
    {
        m.removeParameter("Thingabob");
        assertTrue(!m.getParameters().contains(new Parameter("Thingabob", "int")));
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