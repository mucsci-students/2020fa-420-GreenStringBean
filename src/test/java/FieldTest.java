import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import org.json.simple.JSONObject;
import org.junit.Ignore;
import org.junit.Test;

import model.Field;
import model.VisibleDeclaration.visibility;;

public class FieldTest {
    /**
     * Test creating a field.
     */
    @Test
    public void testCreateField()
    {
        Field field = new Field("field", "int", visibility.PUBLIC);
        assertEquals("Correct name", "field", field.getName());
        assertEquals("Correct data type", "int", field.getType());
        assertEquals("Correct visibility", visibility.PUBLIC, field.getVisibility());
    }

    /**
     * Test a field's toString() method.
     */
    @Test
    public void testToString()
    {
        Field field = new Field("field", "int", visibility.PUBLIC);
        assertEquals("Correct toString() output", "public int field", field.toString());
    }

    /**
     * Test copying a field. Ensure the copy is a new object but, has the same
     * state.
     */
    @Test
    public void testCopy()
    {
        Field field = new Field("field", "int", visibility.PUBLIC);
        Field fieldCopy = field.copy();
        assertNotSame("Copy is a new object", field, fieldCopy);
        assertEquals("Same name", field.getName(), fieldCopy.getName());
        assertEquals("Same data type", field.getType(), fieldCopy.getType());
        assertEquals("Same visibility", field.getVisibility(), fieldCopy.getVisibility());
    }

    /**
     * Test converting a field to a JSONObject.
     */
    @Test
    public void testToJSON()
    {
        Field field = new Field("field", "int", visibility.PUBLIC);
        JSONObject jsonField = field.toJSON();
        assertEquals("JSONObject has correct name", field.getName(), jsonField.get("name"));
        assertEquals("JSONObject has correct data type", field.getType(), jsonField.get("type"));
        assertEquals("JSONObject has correct visibility", field.getVisibility().name(), jsonField.get("visibility"));
    }

    /**
     * Test converting a JSONObject to a field.
     */
    @Test
    public void testLoadFromJSON()
    {
        JSONObject jsonField = new JSONObject();
        jsonField.put("name", "field");
        jsonField.put("type", "int");
        jsonField.put("visibility", visibility.PUBLIC.name());
        Field field = Field.loadFromJSON(jsonField);
        assertEquals("Loaded correct name", jsonField.get("name"), field.getName());
        assertEquals("Loaded correct data type", jsonField.get("type"), field.getType());
        assertEquals("Loaded correct visibility", jsonField.get("visibility"), field.getVisibility().name());
    }

    /**
     * Test converting a JSONObject to a field when the JSONObject does not
     * encode a field.
     */
    @Ignore
    public void testLoadInvalidJSON()
    {
        JSONObject emptyJsonObject = new JSONObject();
        assertNull(Field.loadFromJSON(emptyJsonObject));
    }
}
