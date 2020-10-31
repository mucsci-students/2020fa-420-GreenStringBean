import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.json.simple.JSONObject;
import org.junit.Test;

import model.Parameter;

public class ParameterTest {
    
    @Test
    public void testCreateParameter()
    {
        Parameter param = new Parameter("param", "int");
        assertEquals("Correct name", "param", param.getName());
        assertEquals("Correct data type", "int", param.getType());
    }

    @Test
    public void testToString()
    {
        Parameter param = new Parameter("param", "int");
        assertEquals("Correct toString() output", "int param", param.toString());
    }

    @Test
    public void testCopy()
    {
        Parameter param = new Parameter("param", "int");
        Parameter paramCopy = param.copy();
        assertNotSame("Copy is a new object", param, paramCopy);
        assertEquals("Same name", param.getName(), paramCopy.getName());
        assertEquals("Same data type", param.getType(), paramCopy.getType());
    }

    @Test
    public void testToJSON()
    {
        Parameter param = new Parameter("param", "int");
        JSONObject jsonParam = param.toJSON();
        assertEquals("JSONObject has correct name", param.getName(), jsonParam.get("name"));
        assertEquals("JSONObject has correct data type", param.getType(), jsonParam.get("type"));
    }
}
