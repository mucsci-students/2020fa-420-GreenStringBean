import org.json.simple.*;
import org.junit.Test;
import org.junit.Ignore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import model.*;
import model.Relationship.relationshipType;

/**
 * Test WorkingProject functions
 * TODO: Add more edge case tests
 */
public class WorkingProjectTest {

    /**
     * Test creating a new WorkingProject
     */
    @Test 
    public void testCreateProject()
    {
        WorkingProject wp = new WorkingProject();
        assertTrue(wp != null);
    }

    /**
     * Test adding a ClassObject to WorkingProject
     */
    @Test
    public void testAddClass()
    {
        WorkingProject wp = new WorkingProject();
        wp.addClass("Fruits");
        assertTrue(wp.hasClass("Fruits"));
    }

    @Test
    public void testAddInvalidClass()
    {
        WorkingProject project = new WorkingProject();
        project.addClass("Apple");
        
        int ret = project.addClass("Apple");
        assertEquals("Correct error code returned", 8, ret);
        assertEquals("Class count didn't change", 1, project.getClassNames().size());

        ret = project.addClass("Invalid Class");
        assertEquals("Correct error code returned", 9, ret);
        assertFalse("Class was not added", project.hasClass("Invalid Class"));
    }

    /**
     * Test removing ClassObject from WorkingProject
     */
    @Test
    public void testRemoveClass()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.removeClass("Fruits");
        assertTrue(!wp.hasClass("Fruits"));
    }

    @Test
    public void testRemoveMissingClass()
    {
        WorkingProject project = buildMockWorkingProject();
        int ret = project.removeClass("Legumes");
        assertEquals("Correct error code returned", 2, ret);
        assertEquals("No classes were removed", 2, project.getClassNames().size());
    }

    @Test
    public void testRenameClass()
    {
        WorkingProject project = buildMockWorkingProject();
        ClassObject classObj = project.getClass("Fruits");
        project.renameClass("Fruits", "Legumes");
        assertFalse("Old name is gone", project.hasClass("Fruits"));
        assertTrue("New name is present", project.hasClass("Legumes"));
        assertSame("Renamed class is same object", classObj, project.getClass("Legumes"));
    }

    @Test
    public void testRenameInvalidClass()
    {
        WorkingProject project = buildMockWorkingProject();
        int ret = project.renameClass("Legumes", "Grains");
        assertEquals("Correct error code returned", 2, ret);
        assertFalse("Old name is still not present", project.hasClass("Legumes"));
        assertFalse("New name is not present", project.hasClass("Grains"));

        ret = project.renameClass("Fruits", "Vegetables");
        assertEquals("Correct error code returned", 8, ret);
        assertTrue("Old name is still present", project.hasClass("Fruits"));
        assertTrue("New name is still present", project.hasClass("Vegetables"));

        ret = project.renameClass("Fruits", "Invalid Class");
        assertEquals("Correct error code returned", 9, ret);
        assertTrue("Old name is still present", project.hasClass("Fruits"));
        assertFalse("New name is not present", project.hasClass("Invalid Class"));
    }

    @Test
    public void testOpenCloseClass()
    {
        WorkingProject project = buildMockWorkingProject();
        project.closeClass("Fruits");
        assertFalse("Class is closed", project.getClass("Fruits").isOpen());
        project.openClass("Fruits");
        assertTrue("Class is open", project.getClass("Fruits").isOpen());
    }

    @Test
    public void testOpenCloseMissingClass()
    {
        WorkingProject project = buildMockWorkingProject();
        int ret = project.closeClass("Legumes");
        assertEquals("Correct error code returned", 2, ret);
        ret = project.openClass("Legumes");
        assertEquals("Correct error code returned", 2, ret);
    }

    /**
     * Test adding a Field to ClassObject in WorkingProject
     */
    @Test
    public void testAddField()
    {
        WorkingProject wp = new WorkingProject();
        wp.addClass("Fruits");
        wp.addField("Fruits", "mass", "float", "public");
        assertTrue(wp.getClass("Fruits").hasField("mass"));
    }

    /**
     * Test adding a Method to ClassObject in WorkingProject
     */
    @Test
    public void testAddMethod()
    {
        WorkingProject wp = new WorkingProject();
        wp.addClass("Fruits");
        wp.addMethod("Fruits", "Purchase", "float", "public");
        assertTrue(wp.getClass("Fruits").hasMethod("Purchase"));
    }

    /**
     * Test adding a Parameter to Method in ClassObject in WorkingProject
     */
    @Test
    public void testAddParameter()
    {
        WorkingProject wp = new WorkingProject();
        wp.addClass("Fruits");
        wp.addMethod("Fruits", "Purchase", "float", "public");
        wp.addParameter("Fruits", "Purchase", "paymentType", "String");
        assertTrue(wp.getClass("Fruits").getMethod("Purchase").hasParameter("paymentType"));
        assertTrue(wp.getClass("Fruits").getMethod("Purchase").getParameters().get(0).getType().equals("String"));
    }

    /**
     * Test changing Field type in ClassObject in WorkingProject
     */
    @Test
    public void testChangeFieldType()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.changeFieldType("Fruits", "nutrients", "double");
        assertTrue(wp.getClass("Fruits").getField("nutrients").getType() == "double");
    }

    /**
     * Test changing Method return type in ClassObject in WorkingProject
     */
    @Test
    public void testChangeMethodType()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.changeMethodType("Fruits", "Eat", "Time");
        assertTrue(wp.getClass("Fruits").getMethod("Eat").getType().equals("Time"));
    }

    /**
     * Test changing Parameter type in Method in ClassObject in WorkingProject
     */
    @Test
    public void testChangeParameterType()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.changeParameterType("Fruits", "Eat", "cooked", "int");
        //Note: Can Parameter have a simple getParameter method that takes a name and returns the Parameter object instead of a list?
        assertTrue(wp.getClass("Fruits").getMethod("Eat").getParameters().get(0).getType().equals("int"));
    }

    /**
     * Test changing Field name in ClassObject in WorkingProject
     */
    @Test
    public void testChangeFieldName()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.renameField("Fruits", "nutrients", "nutritionalContent");
        assertTrue(wp.getClass("Fruits").hasField("nutritionalContent"));
        assertTrue(!wp.getClass("Fruits").hasField("nutrients"));
    }

    /**
     * Test changing Method name in ClassObject in WorkingProject
     */
    @Test
    public void testChangeMethodName()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.renameMethod("Fruits", "Eat", "Digest");
        assertTrue(wp.getClass("Fruits").hasMethod("Digest"));
        assertTrue(!wp.getClass("Fruits").hasMethod("Eat"));
    }

    /**
     * Test changing Parameter name in Method in ClassObject in WorkingProject
     */
    @Test
    public void testChangeParameterName()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.renameParameter("Fruits", "Eat", "cooked", "prepared");
        assertTrue(wp.getClass("Fruits").getMethod("Eat").getParameters().get(0).getName().equals("prepared"));
        assertTrue(!(wp.getClass("Fruits").getMethod("Eat").getParameters().get(0).getName().equals("cooked")));
    }
        

    /**
     * Test changing Field visibility in ClassObject in WorkingProject
     */
    @Test
    public void testChangeFieldVisibility()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.changeFieldVisibility("Fruits", "nutrients", "private");
        assertTrue(wp.getClass("Fruits").getField("nutrients").getVisibility().name() == VisibleDeclaration.visibility.PRIVATE.toString());
    }

    /**
     * Test changing Method visibility in ClassObject in WorkingProject
     */
    @Test
    public void testChangeMethodVisibility()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.changeMethodVisibility("Fruits", "Eat", "private");
        assertTrue(wp.getClass("Fruits").getMethod("Eat").getVisibility().name() == VisibleDeclaration.visibility.PRIVATE.toString());
    }

    /**
     * Test adding Relationship between two ClassObjects in WorkingProject
     */
    @Test
    public void testAddRelationship()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.addRelationship("Fruits", "Vegetables", "A");
        assertTrue(wp.hasRelationship("Fruits", "Vegetables"));
        //Need to check relationship type
    }

    /**
     * Test changing Relationship type 
     */
    @Test
    public void testChangeRelationshipType()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.addRelationship("Fruits", "Vegetables", "A");
        wp.changeRelationshipType("Fruits", "Vegetables", "C");
        for (Relationship r : wp.getRelationships())
        {
            assertEquals("Relationship has new type", relationshipType.COMPOSITION, r.getType());
        }
    }

    /**
     * Test removing a Relationship in WorkingProject
     */
    @Test
    public void testRemoveRelationship()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.addRelationship("Fruits", "Vegeatbles", "A");
        wp.removeRelationship("Fruits", "Vegetables");
        assertTrue(!wp.hasRelationship("Fruits", "Vegetables"));
    }

    /**
     * Test removing a Field from ClassObject in WorkingProject
     */
    @Test
    public void testRemoveField()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.removeField("Fruits", "nutrients");
        assertTrue(!wp.getClass("Fruits").hasField("nutrients"));
    }

    /**
     * Test removing a Parameter from Method in ClassObject in WorkingProject
     */
    @Test
    public void testRemoveParameter()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.removeParameter("Fruits", "Eat", "cooked");
        assertTrue(!wp.getClass("Fruits").getMethod("Eat").hasParameter("cooked"));
    }

    /**
     * Test removing Method from ClassObject in WorkingProject
     */
    @Test
    public void testRemoveMethod()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.removeMethod("Fruits", "Eat");
        assertTrue(!wp.getClass("Fruits").hasMethod("Eat"));
    }

    /**
     * Create an example WorkingProject object to run tests on
     * @return A WorkingProject object with a Class with a Field and a Method with a Parameter and a Class without them
     */
    private static WorkingProject buildMockWorkingProject()
    {
        WorkingProject wp = new WorkingProject();
        wp.addClass("Fruits");
        wp.addField("Fruits", "nutrients", "int", "public");
        wp.addMethod("Fruits", "Eat", "void", "public");
        wp.addParameter("Fruits", "Eat", "cooked", "bool");
        wp.addClass("Vegetables");
        return wp;
    }
}
