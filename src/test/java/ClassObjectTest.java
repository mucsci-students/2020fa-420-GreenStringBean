import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.json.simple.JSONObject;
import org.junit.Ignore;
import org.junit.Test;

import model.ClassObject;
import model.Field;
import model.VisibleDeclaration.visibility;

public class ClassObjectTest {
    /**
     * Test creating a class object.
     */
    @Test
    public void testCreateClassObject()
    {
        ClassObject classObj = new ClassObject("ClassObj");
        assertEquals("Correct name", "ClassObj", classObj.getName());
        assertTrue("New class is open", classObj.isOpen());
        assertTrue("New class has no fields", classObj.getFieldNames().isEmpty());
        assertTrue("New class has no methods", classObj.getMethodNames().isEmpty());
    }

    /**
     * Test setting the name.
     */
    @Test
    public void testSetName()
    {
        ClassObject classObj = new ClassObject("ClassObj");
        assertEquals("Correct initial name", "ClassObj", classObj.getName());
        classObj.setName("ClassObj2");
        assertEquals("Correct new name", "ClassObj2", classObj.getName());
    }

    /**
     * Test opening and closing.
     */
    @Test
    public void testOpenClose()
    {
        ClassObject classObj = new ClassObject("ClassObj");
        assertTrue("Class starts open", classObj.isOpen());
        classObj.close();
        assertFalse("Class is now closed", classObj.isOpen());
        classObj.open();
        assertTrue("Class is now open again", classObj.isOpen());
    }

    /**
     * Test copying a class object. Ensure the copy is a new object, but has
     * the same state.
     */
    @Test
    public void testCopy()
    {
        ClassObject classObj = new ClassObject("ClassObj");
        ClassObject classObjCopy = classObj.copy();
        assertNotSame("Copy is a new object", classObj, classObjCopy);
        assertEquals("Same name", classObj.getName(), classObjCopy.getName());
        assertEquals("Same isOpen value", classObj.isOpen(), classObjCopy.isOpen());
        assertEquals("Same fields", classObj.getFieldNames(), classObjCopy.getFieldNames());
        assertEquals("Same methods", classObj.getMethodNames(), classObjCopy.getMethodNames());
    }

    /**
     * Test attaching and detaching an observer.
     */
    @Test
    public void testAttachDetach()
    {
        ClassObject classObj = new ClassObject("ClassObj");
        MockObserver observer = new MockObserver();
        classObj.attach(observer);
        classObj.close();
        assertTrue("Attached observer was notified", observer.wasNotifiedWithClass);
        observer.reset();
        classObj.detach(observer);
        classObj.open();
        assertFalse("Detached observer was not notified", observer.wasNotifiedWithClass);
    }

    /**
     * Test notifying observers. Ensure the copy recieved by each observer is
     * its own object, but with the same state.
     */
    @Test
    public void testNotifyObservers()
    {
        ClassObject classObj = new ClassObject("ClassObj");
        MockObserver observer1 = new MockObserver();
        MockObserver observer2 = new MockObserver();
        classObj.attach(observer1);
        classObj.attach(observer2);
        classObj.close();
        ClassObject observer1Notif = observer1.classRecieved;
        ClassObject observer2Notif = observer2.classRecieved;
        assertNotSame("Class recieved by observer1 is a new object", classObj, observer1Notif);
        assertEquals("Class recieved by observer1 has same name", classObj.getName(), observer1Notif.getName());
        assertEquals("Class recieved by observer1 has same isOpen value", classObj.isOpen(), observer1Notif.isOpen());
        assertEquals("Class recieved by observer1 has same fields", classObj.getFieldNames(), observer1Notif.getFieldNames());
        assertEquals("Class recieved by observer1 has same methods", classObj.getMethodNames(), observer1Notif.getMethodNames());
        assertNotSame("Class recieved by observer2 is a new object", classObj, observer1Notif);
        assertEquals("Class recieved by observer2 has same name", classObj.getName(), observer2Notif.getName());
        assertEquals("Class recieved by observer2 has same isOpen value", classObj.isOpen(), observer2Notif.isOpen());
        assertEquals("Class recieved by observer2 has same fields", classObj.getFieldNames(), observer2Notif.getFieldNames());
        assertEquals("Class recieved by observer2 has same methods", classObj.getMethodNames(), observer2Notif.getMethodNames());
        assertNotSame("Classes revieced by each observer are different objects", observer1Notif, observer2Notif);
    }

    /**
     * Test adding a field.
     */
    @Test
    public void testAddField()
    {
        ClassObject classObj = new ClassObject("ClassObj");
        classObj.addField("field", "int", "public");
        assertTrue("Field was added", classObj.hasField("field"));
        Field field = classObj.getField("field");
        assertEquals("New field has correct data type", "int", field.getType());
        assertEquals("New field has correct visibility", visibility.PUBLIC, field.getVisibility());
    }

    /**
     * Test adding a field with a name that is not valid.
     */
    @Test
    public void testAddFieldInvalidName()
    {
        ClassObject classObj = new ClassObject("ClassObj");
        int ret = classObj.addField("invalid name", "int", "public");
        assertEquals("Correct error code returned", 9, ret);
        assertFalse("Field was not added", classObj.hasField("invalid name"));
    }

    /**
     * Test adding a field with a data type that is not valid.
     */
    @Test
    public void testAddFieldInvalidType()
    {
        ClassObject classObj = new ClassObject("ClassObj");
        int ret = classObj.addField("field", "invalid type", "public");
        assertEquals("Correct error code returned", 10, ret);
        assertFalse("Field was not added", classObj.hasField("field"));
    }

    /**
     * Test adding a field with a visibility that is not valid.
     */
    @Test
    public void testAddFieldInvalidVisibility()
    {
        ClassObject classObj = new ClassObject("ClassObj");
        int ret = classObj.addField("field", "int", "invalid");
        assertEquals("Correct error code returned", 14, ret);
        assertFalse("Field was not added", classObj.hasField("field"));
    }

    /**
     * Test adding a field with a name that is already in use.
     */
    @Test
    public void testAddExistingField()
    {
        ClassObject classObj = new ClassObject("ClassObj");
        classObj.addField("field", "int", "public");
        int ret = classObj.addField("field", "double", "private");
        assertEquals("Correct error code returned", 8, ret);
        assertEquals("Dublicate field was not added", 1, classObj.getFieldNames().size());
    }

    /**
     * Test removing a field.
     */
    @Test
    public void testRemoveField()
    {
        ClassObject classObj = new ClassObject("ClassObj");
        classObj.addField("field", "int", "public");
        assertTrue("Field was added", classObj.hasField("field"));
        classObj.removeField("field");
        assertFalse("Field was removed", classObj.hasField("field"));
    }

    /**
     * Test removing a field that doesn't exist.
     */
    @Test
    public void testRemoveMissingField()
    {
        ClassObject classObj = new ClassObject("ClassObj");
        classObj.addField("field", "int", "public");
        classObj.addField("field2", "int", "public");
        assertEquals("Fields were added", 2, classObj.getFieldNames().size());
        int ret = classObj.removeField("field3");
        assertEquals("Correct error code returned", 3, ret);
        assertEquals("No fields were removed", 2, classObj.getFieldNames().size());
    }

    /**
     * Test renaming a field.
     */
    @Test
    public void testRenameField()
    {
        ClassObject classObj = new ClassObject("ClassObj");
        classObj.addField("field", "int", "public");
        assertTrue("Field was added", classObj.hasField("field"));
        Field field = classObj.getField("field");
        classObj.renameField("field", "field2");
        assertFalse("Old name is gone", classObj.hasField("field"));
        assertTrue("New name is present", classObj.hasField("field2"));
        assertSame("Renamed field is same object", field, classObj.getField("field2"));
    }

    /**
     * Test renaming a field that doesn't exist.
     */
    @Test
    public void testRenameMissingField()
    {
        ClassObject classObj = new ClassObject("ClassObj");
        int ret = classObj.renameField("field", "field2");
        assertEquals("Correct error code returned", 3, ret);
        assertFalse("Old name is not present", classObj.hasField("field"));
        assertFalse("New name is not present", classObj.hasField("field2"));
    }

    /**
     * Test renaming a field to a name that is already in use.
     */
    @Test
    public void testRenameFieldExistingName()
    {
        ClassObject classObj = new ClassObject("ClassObj");
        classObj.addField("field", "int", "public");
        classObj.addField("field2", "int", "public");
        assertTrue("First field was added", classObj.hasField("field"));
        assertTrue("Second field was added", classObj.hasField("field2"));
        int ret = classObj.renameField("field", "field2");
        assertEquals("Correct error code returned", 8, ret);
        assertTrue("First field is still present", classObj.hasField("field"));
        assertTrue("Second field is still present", classObj.hasField("field2"));
    }

    /**
     * Test renaming a field to a name that is not valid.
     */
    @Test
    public void testRenameFieldInvalidName()
    {
        ClassObject classObj = new ClassObject("ClassObj");
        classObj.addField("field", "int", "public");
        assertTrue("Field was added", classObj.hasField("field"));
        int ret = classObj.renameField("field", "invalid name");
        assertEquals("Correct error code returned", 9, ret);
        assertTrue("Old name is still present", classObj.hasField("field"));
        assertFalse("New name is not present", classObj.hasField("invalid name"));
    }

    /**
     * Test changing the data type of a field.
     */
    @Test
    public void testChangeFieldType()
    {
        ClassObject classObj = new ClassObject("ClassObj");
        classObj.addField("field", "int", "public");
        assertTrue("Field was added", classObj.hasField("field"));
        assertEquals("Field has correct initial data type", "int", classObj.getField("field").getType());
        classObj.changeFieldType("field", "double");
        assertEquals("Field has correct new data type", "double", classObj.getField("field").getType());
    }

    /**
     * Test changing the data type of a field to one that is not valid.
     */
    @Test
    public void testChangeFieldInvalidType()
    {
        ClassObject classObj = new ClassObject("ClassObj");
        classObj.addField("field", "int", "public");
        assertTrue("Field was added", classObj.hasField("field"));
        assertEquals("Field has correct initial data type", "int", classObj.getField("field").getType());
        int ret = classObj.changeFieldType("field", "invalid type");
        assertEquals("Correct error code returned", 10, ret);
        assertEquals("Field still has old data type", "int", classObj.getField("field").getType());
    }

    /**
     * Test changing the visibility of a field.
     */
    @Test
    public void testChangeFieldVisibility()
    {
        ClassObject classObj = new ClassObject("ClassObj");
        classObj.addField("field", "int", "public");
        assertTrue("Field was added", classObj.hasField("field"));
        assertEquals("Field has correct initial visibility", visibility.PUBLIC, classObj.getField("field").getVisibility());
        classObj.changeFieldVisibility("field", "private");
        assertEquals("Field has correct new visibility", visibility.PRIVATE, classObj.getField("field").getVisibility());
    }

    /**
     * Test changing the visibility of a field to one that is not valid.
     */
    @Test
    public void testChangeFieldInvalidVisibility()
    {
        ClassObject classObj = new ClassObject("ClassObj");
        classObj.addField("field", "int", "public");
        assertTrue("Field was added", classObj.hasField("field"));
        assertEquals("Field has correct initial visibility", visibility.PUBLIC, classObj.getField("field").getVisibility());
        int ret = classObj.changeFieldVisibility("field", "invalid");
        assertEquals("Correct error code returned", 14, ret);
        assertEquals("Field still has old visibility", visibility.PUBLIC, classObj.getField("field").getVisibility());
    }

    // TODO: Add unexpected input tests for methods and parameters

    /**
     * Test trying to modify the contents of a closed class.
     */
    @Test
    public void testOperationsOnClosedClass()
    {
        ClassObject classObj = new ClassObject("ClassObj");
        classObj.addField("field", "int", "public");
        classObj.close();
        int ret = classObj.addField("field2", "int", "public");
        assertEquals("Correct error code returned", 1, ret);
        assertFalse("Field was not added", classObj.hasField("field2"));
        ret = classObj.removeField("field");
        assertEquals("Correct error code returned", 1, ret);
        assertTrue("Field was not removed", classObj.hasField("field"));
        ret = classObj.renameField("field", "field2");
        assertEquals("Correct error code returned", 1, ret);
        assertTrue("Old name is still present", classObj.hasField("field"));
        assertFalse("New name is not present", classObj.hasField("field2"));
        ret = classObj.changeFieldType("field", "double");
        assertEquals("Correct error code returned", 1, ret);
        assertEquals("Field still has old data type", "int", classObj.getField("field").getType());
        ret = classObj.changeFieldVisibility("field", "private");
        assertEquals("Correct error code returned", 1, ret);
        assertEquals("Field still has old visibility", visibility.PUBLIC, classObj.getField("field").getVisibility());
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
     * Test changing visiblity of a Method in ClassObject
     */
    @Test
    public void testChangeMethodVisibility()
    {
        ClassObject c = buildMockClassObject();
        c.changeMethodVisibility("Eat", "private");
        assertTrue(c.getMethod("Eat").getVisibility().name() == visibility.PRIVATE.name());
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
