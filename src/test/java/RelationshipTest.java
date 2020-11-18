import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.json.simple.JSONObject;
import org.junit.Ignore;
import org.junit.Test;

import model.Relationship;
import model.Relationship.relationshipType;

public class RelationshipTest {
    @Test
    public void testSetClassNames()
    {
        Relationship relationship = new Relationship("ClassFrom", "ClassTo", relationshipType.INHERITANCE);
        assertEquals("Correct initial \"from\" class name", "ClassFrom", relationship.getClassNameFrom());
        assertEquals("Correct initial \"to\" class name", "ClassTo", relationship.getClassNameTo());
        relationship.setClassNameFrom("ClassFrom2");
        assertEquals("Correct new \"from\" class name", "ClassFrom2", relationship.getClassNameFrom());
        relationship.setClassNameTo("ClassTo2");
        assertEquals("Correct new \"to\" class name", "ClassTo2", relationship.getClassNameTo());
    }

    @Test
    public void testContainsClass()
    {
        Relationship relationship = new Relationship("ClassFrom", "ClassTo", relationshipType.INHERITANCE);
        assertTrue("Contains \"from\" class", relationship.containsClass("ClassFrom"));
        assertTrue("Contains \"to\" class", relationship.containsClass("ClassTo"));
        assertFalse("Doesn't contain other class", relationship.containsClass("ClassABC"));
    }

    @Test
    public void testToJSON()
    {
        Relationship relationship = new Relationship("ClassFrom", "ClassTo", relationshipType.INHERITANCE);
        JSONObject jsonRelationship = relationship.toJSON();
        
        JSONObject expectedJson = new JSONObject();

        expectedJson.put("from", "ClassFrom");
        expectedJson.put("to", "ClassTo");
        expectedJson.put("type", relationshipType.INHERITANCE.name());

        assertEquals("JSON output matches expected", expectedJson, jsonRelationship);
    }

    @Test
    public void testCopy()
    {
        Relationship relationship = new Relationship("ClassFrom", "ClassTo", relationshipType.INHERITANCE);
        Relationship copy = relationship.copy();
        assertNotSame("Copy is a new object", relationship, copy);
        assertEquals("Same \"from\" class", relationship.getClassNameFrom(), copy.getClassNameFrom());
        assertEquals("Same \"to\" class", relationship.getClassNameTo(), copy.getClassNameTo());
        assertEquals("Same type", relationship.getType(), copy.getType());
    }

    @Test
    public void testToString()
    {
        Relationship relationship = new Relationship("ClassFrom", "ClassTo", relationshipType.INHERITANCE);
        assertEquals("Correct toString() output", "ClassFrom -> ClassTo (INHERITANCE)", relationship.toString());
    }
}
