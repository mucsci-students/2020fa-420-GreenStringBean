import org.json.simple.*;
import org.junit.Test;
import org.junit.Ignore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import model.*;
import model.Parameter;
import model.VisibleDeclaration.visibility;

/**
 * Test method functions
 * TODO: Add more edge cases
 */
public class MethodTest {

    /**
     * Test creating a Method object
     */
    @Test
    public void testCreateMethod ()
    {
        Method m = new Method("doSomething", "void", visibility.PUBLIC);
        assertTrue(m != null);
    }

    @Test
    public void testParamListConstructor()
    {
        List<String> paramNames = new ArrayList<>();
        paramNames.add("param1");
        paramNames.add("param2");
        paramNames.add("param3");
        List<String> paramTypes = new ArrayList<>();
        paramTypes.add("int");
        paramTypes.add("boolean");
        paramTypes.add("String");

        Method method = new Method("method", "void", visibility.PUBLIC, paramNames, paramTypes);

        assertEquals("Correct name", "method", method.getName());
        assertEquals("Correct return type", "void", method.getType());
        assertEquals("Correct visibility", visibility.PUBLIC, method.getVisibility());

        List<Parameter> params = method.getParameters();
        assertEquals("Correct param 1 name", "param1", params.get(0).getName());
        assertEquals("Correct param 2 name", "param2", params.get(1).getName());
        assertEquals("Correct param 3 name", "param3", params.get(2).getName());
        assertEquals("Correct param 1 type", "int", params.get(0).getType());
        assertEquals("Correct param 2 type", "boolean", params.get(1).getType());
        assertEquals("Correct param 3 type", "String", params.get(2).getType());
    }

    /**
     * Test getting a Method name
     */
    @Test
    public void testGetMethodName ()
    {
        Method m = buildMockMethod();
        assertTrue(m.getName().equals("doThis"));
    }

    /**
     * Test getting a Method return type
     */
    @Test
    public void testGetMethodType ()
    {
        Method m = buildMockMethod();
        assertTrue(m.getType().equals("void"));
    }

    /**
     * Test setting a Method name
     */
    @Test 
    public void testSetMethodName ()
    {
        Method m = buildMockMethod();
        m.setName("doThat");
        assertTrue(m.getName().equals("doThat"));
        assertTrue(!m.getName().equals("doThis"));
    }

    /**
     * Test adding a Method Parameter
     */
    @Test
    public void testAddParameter ()
    {
        Method m = buildMockMethod();
        m.addParameter("thingy", "int");
        assertTrue(m.hasParameter("thingy"));
    }

    @Test
    public void testAddInvalidParameter()
    {
        Method method = new Method("method", "void", visibility.PUBLIC);

        method.addParameter("dupParam", "int");
        int ret = method.addParameter("dupParam", "double");
        assertEquals("Correct error code returned", 8, ret);
        assertEquals("Duplicate param was not added", 1, method.getParameters().size());

        ret = method.addParameter("invalid name", "float");
        assertEquals("Correct error code returned", 9, ret);
        assertEquals("Invalid param was not added", 1, method.getParameters().size());

        ret = method.addParameter("param", "invalid type");
        assertEquals("Correct error code returned", 10, ret);
        assertEquals("Invalid param was not added", 1, method.getParameters().size());
    }

    /**
     * Test renaming a Method Parameter
     */
    @Test
    public void testRenameParameter()
    {
        Method m = buildMockMethod();
        m.renameParameter("number", "integer");
        assertTrue(m.hasParameter("integer"));
    }

    @Test
    public void testRenameInvalidParameter()
    {
        Method method = new Method("method", "void", visibility.PUBLIC);
        method.addParameter("param", "int");
        method.addParameter("param2", "boolean[][]");

        int ret = method.renameParameter("param3", "param3a");
        assertEquals("Correct error code returned", 5, ret);
        assertFalse("No param with old name", method.hasParameter("param3"));
        assertFalse("No param with new name", method.hasParameter("param3a"));

        ret = method.renameParameter("param2", "param");
        assertEquals("Correct error code returned", 8, ret);
        assertTrue("Param with old name still exists", method.hasParameter("param2"));
        assertTrue("Param with new name still exists", method.hasParameter("param"));

        ret = method.renameParameter("param", "it'sNotValid");
        assertEquals("Correct error code returned", 9, ret);
        assertTrue("Param with old name still exists", method.hasParameter("param"));
        assertFalse("No param with new name", method.hasParameter("it'sNotValid"));
    }

    /**
     * Test changing a Method Parameter type
     */
    @Test
    public void testChangeParameterType()
    {
        Method m = buildMockMethod();
        m.changeParameterType("number", "short");
        assertTrue(m.getParameters().get(0).getType().equals("short"));
    }

    @Test
    public void testChangeInvalidParameterType()
    {
        Method method = new Method("method", "void", visibility.PUBLIC);
        method.addParameter("param", "char");
        
        int ret = method.changeParameterType("param2", "long");
        assertEquals("Correct error code returned", 5, ret);
        assertFalse("Param with missing name still doesn't exist", method.hasParameter("param2"));

        ret = method.changeParameterType("param", "1nvalid");
        assertEquals("Correct error code returned", 10, ret);
        assertEquals("Param still has old type", "char", method.getParameters().get(0).getType());
    }

    /**
     * Test removing a Method Parameter
     */
    @Test
    public void testRemoveParameter()
    {
        Method m = buildMockMethod();
        m.removeParameter("number");
        assertTrue(!m.hasParameter("number"));
    }

    @Test
    public void testRemoveMissingParameter()
    {
        Method method = new Method("method", "void", visibility.PUBLIC);
        method.addParameter("param", "int");

        int ret = method.removeParameter("param2");
        assertEquals("Correct error code returned", 5, ret);
        assertEquals("No params were removed", 1, method.getParameters().size());
    }

    @Test
    public void testSetParameters()
    {
        Method method = new Method("method", "void", visibility.PUBLIC);
        method.addParameter("oldParam", "byte");
        method.addParameter("param2", "WorkingProjectEditor");

        List<String> paramNames = new ArrayList<>();
        paramNames.add("param1");
        paramNames.add("param2");
        paramNames.add("param3");
        List<String> paramTypes = new ArrayList<>();
        paramTypes.add("int");
        paramTypes.add("boolean");
        paramTypes.add("String");

        method.setParameters(paramNames, paramTypes);
        List<Parameter> params = method.getParameters();
        assertEquals("Correct param count", 3, params.size());
        assertEquals("Correct param 1 name", "param1", params.get(0).getName());
        assertEquals("Correct param 2 name", "param2", params.get(1).getName());
        assertEquals("Correct param 3 name", "param3", params.get(2).getName());
        assertEquals("Correct param 1 type", "int", params.get(0).getType());
        assertEquals("Correct param 2 type", "boolean", params.get(1).getType());
        assertEquals("Correct param 3 type", "String", params.get(2).getType());
    }

    @Test
    public void testToString()
    {
        Method method = new Method("method", "void", visibility.PUBLIC);
        assertEquals("Correct toString() output for empty method", "public void method()", method.toString());

        method.addParameter("param1", "Scanner");
        method.addParameter("param2", "short");
        assertEquals("Correct toString() output for method with params", "public void method( Scanner param1, short param2 )", method.toString());
    }

    @Test
    public void testGetParameterNames()
    {
        Method method = new Method("method", "void", visibility.PUBLIC);
        method.addParameter("param1", "Set");
        method.addParameter("param2", "Map");
        method.addParameter("param3", "Deque");

        List<String> paramNames = method.getParameterNames();
        assertEquals("Correct param 1 name", "param1", paramNames.get(0));
        assertEquals("Correct param 2 name", "param2", paramNames.get(1));
        assertEquals("Correct param 3 name", "param3", paramNames.get(2));
    }

    @Test
    public void testCopy()
    {
        Method method = new Method("method", "void", visibility.PUBLIC);
        method.addParameter("param1", "JPanel");
        method.addParameter("param2", "JLabel");

        Method methodCopy = method.copy();
        assertNotSame("Copy is a new object", method, methodCopy);
        assertEquals("Same name", method.getName(), methodCopy.getName());
        assertEquals("Same return type", method.getType(), methodCopy.getType());
        assertEquals("Same visibility", method.getVisibility(), methodCopy.getVisibility());

        List<Parameter> params = method.getParameters();
        List<Parameter> copyParams = methodCopy.getParameters();
        assertEquals("Same param count", params.size(), copyParams.size());

        for (int i = 0; i < params.size(); ++i)
        {
            Parameter param = params.get(i);
            Parameter copyParam = copyParams.get(i);
            assertNotSame("Copy param is a new object", param, copyParam);
            assertEquals("Same name", param.getName(), copyParam.getName());
            assertEquals("Same data type", param.getType(), copyParam.getType());
        }
    }

    /**
     * Test conversion of Method to JSON
     */
    @Test
    public void testToJSON ()
    {
        Method method = new Method("method", "void", visibility.PUBLIC);
        method.addParameter("param1", "Component");
        method.addParameter("param2", "Point");

        JSONObject jsonMethod = method.toJSON();
        assertEquals("JSONObject has correct name", method.getName(), jsonMethod.get("name"));
        assertEquals("JSONObject has correct return type", method.getType(), jsonMethod.get("type"));
        assertEquals("JSONObject has correct visibility", method.getVisibility().name(), jsonMethod.get("visibility"));

        List<Parameter> params = method.getParameters();
        JSONArray jsonParams = (JSONArray)jsonMethod.get("parameters");
        assertEquals("JSONObject has correct parameter count", params.size(), jsonParams.size());

        for (int i = 0; i < params.size(); ++i)
        {
            assertEquals("JSONObject's param has correct name", params.get(i).getName(), ((JSONObject)jsonParams.get(i)).get("name"));
            assertEquals("JSONObject's param has correct data type", params.get(i).getType(), ((JSONObject)jsonParams.get(i)).get("type"));
        }
    }

    @Test
    public void testLoadFromJSON()
    {
        JSONObject jsonMethod = new JSONObject();
        jsonMethod.put("name", "method");
        jsonMethod.put("type", "void");
        jsonMethod.put("visibility", visibility.PUBLIC.name());

        JSONArray jsonParams = new JSONArray();
        JSONObject jsonParam1 = new JSONObject();
        jsonParam1.put("name", "param1");
        jsonParam1.put("type", "long");
        JSONObject jsonParam2 = new JSONObject();
        jsonParam2.put("name", "param2");
        jsonParam2.put("type", "int[]");
        jsonParams.add(jsonParam1);
        jsonParams.add(jsonParam2);
        jsonMethod.put("parameters", jsonParams);

        Method method = Method.loadFromJSON(jsonMethod);
        assertEquals("Loaded correct name", jsonMethod.get("name"), method.getName());
        assertEquals("Loaded correct return type", jsonMethod.get("type"), method.getType());
        assertEquals("Loaded correct visibility", jsonMethod.get("visibility"), method.getVisibility().name());

        List<Parameter> params = method.getParameters();

        assertEquals("Loaded correct param 1 name", jsonParam1.get("name"), params.get(0).getName());
        assertEquals("Loaded correct param 2 name", jsonParam2.get("name"), params.get(1).getName());
        assertEquals("Loaded correct param 1 data type", jsonParam1.get("type"), params.get(0).getType());
        assertEquals("Loaded correct param 2 data type", jsonParam2.get("type"), params.get(1).getType());
    }

    @Test
    public void testLoadInvalidJSON()
    {
        JSONObject jsonMethod = new JSONObject();
        jsonMethod.put("name", "method");
        jsonMethod.put("type", "void");
        jsonMethod.put("visibility", visibility.PUBLIC.name());

        JSONArray jsonParams = new JSONArray();
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("name", "param");
        jsonParam.put("type", "int");
        jsonParams.add(jsonParam);
        jsonMethod.put("parameters", jsonParams);

        JSONObject jsonMethodNoName = (JSONObject)jsonMethod.clone();
        jsonMethodNoName.remove("name");
        assertNull("Missing name returns null", Method.loadFromJSON(jsonMethodNoName));

        JSONObject jsonMethodNoType = (JSONObject)jsonMethod.clone();
        jsonMethodNoType.remove("type");
        assertNull("Missing return type returns null", Method.loadFromJSON(jsonMethodNoType));

        JSONObject jsonMethodNoVis = (JSONObject)jsonMethod.clone();
        jsonMethodNoVis.remove("visibility");
        assertNull("Missing visibility returns null", Method.loadFromJSON(jsonMethodNoVis));

        JSONObject jsonMethodInvalidVis = (JSONObject)jsonMethod.clone();
        jsonMethodInvalidVis.put("visibility", "abc");
        assertNull("Invalid visibility returns null", Method.loadFromJSON(jsonMethodInvalidVis));

        JSONObject jsonMethodNoParams = (JSONObject)jsonMethod.clone();
        jsonMethodNoParams.remove("parameters");
        assertNull("Missing param list returns null", Method.loadFromJSON(jsonMethodNoParams));

        JSONObject jsonMethodNoParamName = (JSONObject)jsonMethod.clone();
        JSONObject jsonParamNoName = (JSONObject)jsonParam.clone();
        jsonParamNoName.remove("name");
        jsonParams.clear();
        jsonParams.add(jsonParamNoName);
        jsonMethod.put("parameters", jsonParams);
        assertNull("Missing param name returns null", Method.loadFromJSON(jsonMethodNoParamName));

        JSONObject jsonMethodNoParamType = (JSONObject)jsonMethod.clone();
        JSONObject jsonParamNoType = (JSONObject)jsonParam.clone();
        jsonParamNoType.remove("type");
        jsonParams.clear();
        jsonParams.add(jsonParamNoType);
        jsonMethod.put("parameters", jsonParams);
        assertNull("Missing param type returns null", Method.loadFromJSON(jsonMethodNoParamType));

        JSONObject jsonMethodDupParam = (JSONObject)jsonMethod.clone();
        JSONObject jsonParamDup = new JSONObject();
        jsonParamDup.put("name", "param");
        jsonParamDup.put("type", "boolean");
        jsonParams.clear();
        jsonParams.add(jsonParam);
        jsonParams.add(jsonParamDup);
        jsonMethod.put("parameters", jsonParams);
        assertNull("Duplicate param name returns null", Method.loadFromJSON(jsonMethodDupParam));
    }

    private static Method buildMockMethod()
    {
        Method m = new Method("doThis", "void", visibility.PUBLIC);
        m.addParameter("number", "int");
        return m;
    }

}