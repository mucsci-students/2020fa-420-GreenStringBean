import org.junit.Test;
import org.junit.Ignore;
import org.json.simple.JSONObject;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import model.*;


public class MethodTest {

    String name;
    String type;
    Method m;
    @Test
    public void TestCreateMethod (String name, String type)
    {
        m = new Method("doSomething", "void", ClassObject.stringToVisibility("public"));
    }

    @Test
    public void TestGetMethodName ()
    {
        m = buildMockMethod();
        assertTrue(m.getName().equals("doThis"));
    }

    @Test
    public void TestGetMethodType ()
    {
        m = buildMockMethod();
        assertTrue(m.getType().equals(type));
    }

    @Test 
    public void TestSetMethodName ()
    {
        m = buildMockMethod();
        m.setName("doThat");
        assertTrue(m.getName().equals("doThat"));
        assertTrue(!m.getName().equals("doThis"));

    }


    @Test
    public void TestAddParameter ()
    {
        m = buildMockMethod();
        m.addParameter("Thingy", "void");
        assertTrue(m.hasParameter("Thingy"));
    }

    @Test
    public void TestRenameParameter()
    {
        m = buildMockMethod();
        m.renameParameter("number", "integer");
        assertTrue(m.hasParameter("integer"));
    }

    @Ignore
    public void TestChangeParameterType()
    {
        m = buildMockMethod();
        m.changeParameterType("doThis", "int");
        assertTrue(m.getParameters().contains(new Parameter("doThis", "int")));
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
        Method n = new Method("Attack", "void", ClassObject.stringToVisibility("public"));
        n.addParameter("Damage", "int");
        Parameter p = new Parameter("Damage", "int");
        JSONObject actual = n.toJSON();
        JSONObject expected = new JSONObject();
        
        assertTrue(null, actual.toJSONString().equals(expected.toJSONString()));
    }

    private static Method buildMockMethod()
    {
        Method m = new Method("doThis", "void", ClassObject.stringToVisibility("public"));
        m.addParameter("number", "int");
        return m;
    }

}