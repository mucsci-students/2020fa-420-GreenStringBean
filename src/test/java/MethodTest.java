import org.json.simple.*;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertTrue;
import model.*;
import model.Parameter;
import model.VisibleDeclaration.visibility;

public class MethodTest {

    @Test
    public void testCreateMethod ()
    {
        Method m = new Method("doSomething", "void", visibility.PUBLIC);
        assertTrue(m != null);
    }

    @Test
    public void testGetMethodName ()
    {
        Method m = buildMockMethod();
        assertTrue(m.getName().equals("doThis"));
    }

    @Test
    public void testGetMethodType ()
    {
        Method m = buildMockMethod();
        assertTrue(m.getType().equals("void"));
    }

    @Test 
    public void testSetMethodName ()
    {
        Method m = buildMockMethod();
        m.setName("doThat");
        assertTrue(m.getName().equals("doThat"));
        assertTrue(!m.getName().equals("doThis"));
    }

    @Test
    public void testAddParameter ()
    {
        Method m = buildMockMethod();
        m.addParameter("thingy", "void");
        assertTrue(m.hasParameter("thingy"));
    }

    @Test
    public void testRenameParameter()
    {
        Method m = buildMockMethod();
        m.renameParameter("number", "integer");
        assertTrue(m.hasParameter("integer"));
    }

    @Test
    public void testChangeParameterType()
    {
        Method m = buildMockMethod();
        m.changeParameterType("number", "short");
        assertTrue(m.getParameters().get(0).getType().equals("short"));
    }

    @Test
    public void testDeleteParameter()
    {
        Method m = buildMockMethod();
        m.removeParameter("number");
        assertTrue(!m.hasParameter("number"));
    }

    @Ignore
    public void testToJSON ()
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
        Method m = new Method("doThis", "void", visibility.PUBLIC);
        m.addParameter("number", "int");
        return m;
    }

}