import org.json.simple.*;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertTrue;
import model.*;
import model.Parameter;

//Test ClassObject functions
//TODO: Add edge cases
public class ClassObjectTest {

    /**
     * Test creating a ClassObject object
     */
    @Test
    public void testCreateClassObject ()
    {
        ClassObject c = new ClassObject("Fruit");
        assertTrue(c != null);
    }

    /**
     * Test getting a ClassObject name
     */
    @Test
    public void testGetClassObjectName ()
    {
        ClassObject c = buildMockClassObject();
        assertTrue(null, c.getName().equals("Watermelon"));
    }

    /**
     * Test setting a ClassObject name
     */
    @Test 
    public void testSetClassName ()
    {
        ClassObject c = buildMockClassObject();
        c.setName("Berry");
        assertTrue(null, c.getName().equals("Berry"));
    }

    /**
     * Test adding a Field to ClassObject
     */
    @Test
    public void testAddField()
    {
        ClassObject c = buildMockClassObject();
        c.addField("Edible", "bool", "public");
        assertTrue(c.hasField("Edible"));
        assertTrue(c.getField("Edible").getType().equals("bool"));
    }

    /**
     * Test renaming a Field in ClassObject
     */
    @Test
    public void testRenameField()
    {
        ClassObject c = buildMockClassObject();
        c.renameField("Seedless", "SeedOrNotToSeed");
        assertTrue(!c.hasField("Seedless"));
        assertTrue(c.hasField("SeedOrNotToSeed"));
    }

    /**
     * Test changing a Field type in ClassObject
     */
    @Test
    public void testChangeFieldType()
    {
        ClassObject c = buildMockClassObject();
        c.changeFieldType("Seedless", "int");
        assertTrue(c.getField("Seedless").getType().equals("int"));
    }

    /** 
     * Test removing a Field in ClassObject
     */
    @Test
    public void testRemoveField()
    {
        ClassObject c = buildMockClassObject();
        c.removeField("Seedless");
        assertTrue(!c.hasField("Seedless"));
    }

    /**
     * Test adding a Method to ClassObject
     */
    @Test
    public void testAddMethod()
    {
        ClassObject c = buildMockClassObject();
        c.addMethod("Scoop", "void", "public");
        assertTrue(c.hasMethod("Scoop"));
        assertTrue(c.getMethod("Scoop").getType() == "void");
    }

    /**
     * Test renaming a Method in ClassObject
     */
    @Test
    public void testRenameMethod()
    {
        ClassObject c = buildMockClassObject();
        c.renameMethod("Eat", "Digest");
        assertTrue(c.hasMethod("Digest"));
        assertTrue(!c.hasMethod("Eat"));
    }

    /**
     * Test changing a Method return type in ClassObject
     */
    @Test
    public void testChangeMethodType()
    {
        ClassObject c = buildMockClassObject();
        c.changeMethodType("Eat", "int");
        assertTrue(c.getMethod("Eat").getType() == "int");
    }

    /**
     * Test changing visibility of a Field in ClassObject
     */
    @Test
    public void testChangeFieldVisibility()
    {
        ClassObject c = buildMockClassObject();
        c.changeFieldVisibility("Seedless", "protected");
        assertTrue(c.getField("Seedless").getVisibility().name() == VisibleDeclaration.visibility.PROTECTED.toString());
    }

    /**
     * Test changing visiblity of a Method in ClassObject
     */
    @Test
    public void testChangeMethodVisibility()
    {
        ClassObject c = buildMockClassObject();
        c.changeMethodVisibility("Eat", "private");
        assertTrue(c.getMethod("Eat").getVisibility().name() == VisibleDeclaration.visibility.PRIVATE.toString());
    }

    /**
     * Test adding a Parameter to a Method in ClassObject
     */
    @Test
    public void testAddParameter()
    {
        ClassObject c = buildMockClassObject();
        c.addParameter("Eat", "cooked", "bool");
        assertTrue(c.getMethod("Eat").hasParameter("cooked"));
    } 

    /**
     * Test renaming a Parameter in a Method in ClassObject
     */
    @Test
    public void testRenameParameter()
    {
        ClassObject c = buildMockClassObject();
        c.renameParameter("Eat", "Spoon", "Utensil");
        assertTrue(c.getMethod("Eat").getParameters().get(0).getName().equals("Utensil"));
    }

    /**
     * Test chaning a Parameter type in a Method in ClassObject
     */
    @Test
    public void testChangeParameterType()
    {
        ClassObject c = buildMockClassObject();
        c.changeParameterType("Eat", "Spoon", "int");
        assertTrue(c.getMethod("Eat").getParameters().get(0).getType().equals("int"));
    }

    /**
     * Test removing a Parameter type from a Method in ClassObject
     */
    @Test
    public void testRemoveParameter()
    {
        ClassObject c = buildMockClassObject();
        c.removeParameter("Eat", "Spoon");
        assertTrue(!c.getMethod("Eat").hasParameter("Spoon"));
    }

    /**
     * Test removing a Method from a ClassObject
     */
    @Test
    public void testRemoveMethod()
    {
        ClassObject c = buildMockClassObject(); 
        c.removeMethod("Eat");
        assertTrue(!c.hasMethod("Eat"));
    }

    /**
     * Test converting a ClassObject to JSON
     * TODO: Finish this test
     */
    @Ignore
    public void TestToJSON ()
    {
        ClassObject d = new ClassObject("Construction");
        d.addField("Height", "double", "public");
        d.addMethod("BuildHouse", "void", "public");
        d.addParameter("BuildHouse", "Four_Bedroom", "HouseType");
        JSONObject expectedF = new JSONObject();
        JSONObject expectedM = new JSONObject();
        JSONObject expectedP = new JSONObject();
        expectedF.put("name", "Height");
        expectedF.put("type", "double");
        expectedM.put("name", "buildHouse");
        expectedM.put("type", "void");
        expectedP.put("name", "Four_Bedroom");
        expectedP.put("type", "HouseType");
        expectedM.put("parameters", expectedP);
        JSONObject expectedC = new JSONObject();
        expectedC.put("name", "Construction");
        expectedC.put("methods", expectedM);
        expectedC.put("fields", expectedF);


        assertTrue(null, d.toJSON().equals(expectedC));
    }

    /**
     * Create an example ClassObject to run tests on
     * @return A ClassObject with a Field and a Method with a Parameter
     */
    private static ClassObject buildMockClassObject()
    {
        ClassObject co = new ClassObject("Watermelon");
        co.addField("Seedless", "bool", "public");
        co.addMethod("Eat", "void", "public");
        co.addParameter("Eat", "Spoon", "bool");
        return co;
    }

}