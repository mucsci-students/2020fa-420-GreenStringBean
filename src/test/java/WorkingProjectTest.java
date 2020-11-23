import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import model.ClassObject;
import model.Field;
import model.Method;
import model.Parameter;
import model.Relationship;
import model.Relationship.relationshipType;
import model.VisibleDeclaration.visibility;
import model.VisibleDeclaration;
import model.WorkingProject;

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
        WorkingProject project = buildMockWorkingProject();
        project.addRelationship("Fruits", "Vegetables", "I");
        project.addRelationship("Vegetables", "Vegetables", "I");
        project.removeClass("Fruits");
        assertFalse("Class was removed", project.hasClass("Fruits"));
        assertEquals("Relationship containing class was removed", 1, project.getRelationships().size());
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
        project.addRelationship("Fruits", "Vegetables", "I");
        project.addRelationship("Vegetables", "Fruits", "I");
        project.renameClass("Fruits", "Legumes");
        assertFalse("Old name is gone", project.hasClass("Fruits"));
        assertTrue("New name is present", project.hasClass("Legumes"));
        assertSame("Renamed class is same object", classObj, project.getClass("Legumes"));
        assertTrue("Relationships are still present", project.hasRelationship("Legumes", "Vegetables"));
        assertTrue("Relationships are still present", project.hasRelationship("Vegetables", "Legumes"));
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
    public void testAddMethodNoParameters()
    {
        WorkingProject wp = new WorkingProject();
        wp.addClass("Fruits");
        wp.addMethod("Fruits", "Purchase", "float", "public");
        assertTrue(wp.getClass("Fruits").hasMethod("Purchase"));
    }

    @Test
    public void testAddMethodWithParameters()
    {
        WorkingProject project = buildMockWorkingProject();
        List<String> paramNames = new ArrayList<>();
        List<String> paramTypes = new ArrayList<>();
        paramNames.add("newNutrients");
        paramTypes.add("long");
        project.addMethod("Fruits", "setNutrients", "void", "public", paramNames, paramTypes);
        assertTrue("Method was added", project.getClass("Fruits").hasMethod("setNutrients"));
        List<Parameter> addedParams = project.getClass("Fruits").getMethod("setNutrients").getParameters();
        assertEquals("Method has a parameter", 1, addedParams.size());
        Parameter addedParam = addedParams.get(0);
        assertEquals("Parameter has correct name", "newNutrients", addedParam.getName());
        assertEquals("Parameter has correct type", "long", addedParam.getType());
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

    @Test
    public void testChangeParameterList()
    {
        WorkingProject project = buildMockWorkingProject();
        List<String> paramNames = new ArrayList<>();
        List<String> paramTypes = new ArrayList<>();
        project.changeParameterList("Fruits", "Eat", paramNames, paramTypes);
        assertEquals("Method now has no parameters", 0, project.getClass("Fruits").getMethod("Eat").getParameters().size());
    }

    @Test
    public void testOperationsOnMissingClass()
    {
        WorkingProject project = buildMockWorkingProject();
        int ret = project.addField("Legumes", "mass", "float", "public");
        assertEquals("Correct error code returned", 2, ret);
        ret = project.removeField("Legumes", "mass");
        assertEquals("Correct error code returned", 2, ret);
        ret = project.renameField("Legumes", "mass", "weight");
        assertEquals("Correct error code returned", 2, ret);
        ret = project.changeFieldType("Legumes", "mass", "double");
        assertEquals("Correct error code returned", 2, ret);
        ret = project.changeFieldVisibility("Legumes", "mass", "private");
        assertEquals("Correct error code returned", 2, ret);
        
        ret = project.addMethod("Legumes", "eat", "void", "public");
        assertEquals("Correct error code returned", 2, ret);     
        List<String> paramNames = new ArrayList<>();
        List<String> paramTypes = new ArrayList<>();
        ret = project.addMethod("Legumes", "eat", "void", "public", paramNames, paramTypes);
        assertEquals("Correct error code returned", 2, ret);     
        ret = project.removeMethod("Legumes", "eat");
        assertEquals("Correct error code returned", 2, ret);
        ret = project.renameMethod("Legumes", "eat", "consume");
        assertEquals("Correct error code returned", 2, ret);
        ret = project.changeMethodType("Legumes", "eat", "boolean");
        assertEquals("Correct error code returned", 2, ret);
        ret = project.changeMethodVisibility("Legumes", "eat", "private");
        assertEquals("Correct error code returned", 2, ret);

        ret = project.changeParameterList("Legumes", "eat", paramNames, paramTypes);
        assertEquals("Correct error code returned", 2, ret);
        ret = project.addParameter("Legumes", "eat", "cooked", "boolean");
        assertEquals("Correct error code returned", 2, ret);
        ret = project.removeParameter("Legumes", "eat", "cooked");
        assertEquals("Correct error code returned", 2, ret);
        ret = project.renameParameter("Legumes", "eat", "cooked", "boiled");
        assertEquals("Correct error code returned", 2, ret);
        ret = project.changeParameterType("Legumes", "eat", "cooked", "int");
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
        for (Relationship r : wp.getRelationships())
        {
            assertEquals("Relationship has correct type", relationshipType.AGGREGATION, r.getType());
        }
    }

    @Test
    public void testAddInvalidRelationship()
    {
        WorkingProject project = buildMockWorkingProject();
        project.addRelationship("Fruits", "Vegetables", "I");

        int ret = project.addRelationship("Fruits", "Vegetables", "Z");
        assertEquals("Correct error code returned", 11, ret);
        assertEquals("No relationship was added", 1, project.getRelationships().size());

        ret = project.addRelationship("Legumes", "Fruits", "I");
        assertEquals("Correct error code returned", 2, ret);
        assertEquals("No relationship was added", 1, project.getRelationships().size());

        ret = project.addRelationship("Fruits", "Legumes", "R");
        assertEquals("Correct error code returned", 2, ret);
        assertEquals("No relationship was added", 1, project.getRelationships().size());

        ret = project.addRelationship("Fruits", "Vegetables", "A");
        assertEquals("Correct error code returned", 7, ret);
        assertEquals("No relationship was added", 1, project.getRelationships().size());
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

    @Test
    public void changeRelationshipInvalidType()
    {
        WorkingProject project = buildMockWorkingProject();
        project.addRelationship("Vegetables", "Vegetables", "I");

        int ret = project.changeRelationshipType("Vegetables", "Vegetables", "Z");
        assertEquals("Correct error code returned", 11, ret);
        ret = project.changeRelationshipType("Legumes", "Fruits", "I");
        assertEquals("Correct error code returned", 2, ret);
        ret = project.changeRelationshipType("Fruits", "Legumes", "I");
        assertEquals("Correct error code returned", 2, ret);
        ret = project.changeRelationshipType("Fruits", "Fruits", "I");
        assertEquals("Correct error code returned", 6, ret);
        ret = project.changeRelationshipType("Vegetables", "Fruits", "I");
        assertEquals("Correct error code returned", 6, ret);
        ret = project.changeRelationshipType("Fruits", "Vegetables", "I");
        assertEquals("Correct error code returned", 6, ret);
    }

    /**
     * Test removing a Relationship in WorkingProject
     */
    @Test
    public void testRemoveRelationship()
    {
        WorkingProject project = buildMockWorkingProject();
        project.addRelationship("Fruits", "Vegetables", "I");
        assertTrue("Relationship was added", project.hasRelationship("Fruits", "Vegetables"));
        project.removeRelationship("Fruits", "Vegetables");
        assertFalse("Relationship was removed", project.hasRelationship("Fruits", "Vegetables"));
    }

    @Test
    public void testRemoveInvalidRelationship()
    {
        WorkingProject project = buildMockWorkingProject();
        int ret = project.removeRelationship("Legumes", "Fruits");
        assertEquals("Correct error code returned", 2, ret);
        ret = project.removeRelationship("Fruits", "Legumes");
        assertEquals("Correct error code returned", 2, ret);
        ret = project.removeRelationship("Fruits", "Vegetables");
        assertEquals("Correct error code returned", 6, ret);
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

    @Test
    public void testPrintClasses()
    {
        WorkingProject project = buildMockWorkingProject();
        project.printClasses();
    }

    @Test
    public void testLoadFromJSON()
    {
        JSONObject jsonProject = buildJSONProject();

        WorkingProject project = new WorkingProject();
        project.loadFromJSON(jsonProject.toJSONString());

        assertTrue("Loaded correct class", project.hasClass("Apple"));
        ClassObject loadedClass = project.getClass("Apple");
        
        assertTrue("Loaded class has correct field", loadedClass.hasField("weight"));
        Field loadedField = loadedClass.getField("weight");
        assertEquals("Loaded field has correct data type", "double", loadedField.getType());
        assertEquals("Loaded field has correct visibility", visibility.PRIVATE, loadedField.getVisibility());

        assertTrue("Loaded class has correct method", loadedClass.hasMethod("setWeight"));
        Method loadedMethod = loadedClass.getMethod("setWeight");
        assertEquals("Loaded method has correct return type", "void", loadedMethod.getType());
        assertEquals("Loaded method has correct visibility", visibility.PUBLIC, loadedMethod.getVisibility());

        assertTrue("Loaded method has correct parameter", loadedMethod.hasParameter("newWeight"));
        Parameter loadedParam = loadedMethod.getParameters().get(0);
        assertEquals("Loaded parameter has correct data type", "double", loadedParam.getType());

        assertEquals("Loaded project has a relationship", 1, project.getRelationships().size());
        for (Relationship loadedRel : project.getRelationships())
        {
            assertEquals("Loaded relationship has correct \"from\" class", "Apple", loadedRel.getClassNameFrom());
            assertEquals("Loaded relationship has correct \"to\" class", "Apple", loadedRel.getClassNameTo());
            assertEquals("Loaded relationship has correct type", relationshipType.INHERITANCE, loadedRel.getType());
        }
    }

    @Test
    public void testLoadInvalidJSON()
    {
        JSONObject jsonProject = buildJSONProject();
        WorkingProject project = new WorkingProject();
        int ret = project.loadFromJSON("abc");
        assertEquals("Correct error code returned", 12, ret);

        JSONObject jsonProjectNoClasses = buildJSONProject();
        jsonProjectNoClasses.remove("classes");
        ret = project.loadFromJSON(jsonProjectNoClasses.toJSONString());
        assertEquals("Correct error code returned", 12, ret);

        JSONObject jsonProjectNoRelationships = buildJSONProject();
        jsonProjectNoRelationships.remove("relationships");
        ret = project.loadFromJSON(jsonProjectNoRelationships.toJSONString());
        assertEquals("Correct error code returned", 12, ret);

        JSONObject jsonProjectInvalidClass = buildJSONProject();
        JSONArray jsonClassesInvalidClass = new JSONArray();
        JSONObject invalidJsonClass = new JSONObject();
        jsonClassesInvalidClass.add(invalidJsonClass);
        jsonProjectInvalidClass.put("classes", jsonClassesInvalidClass);
        ret = project.loadFromJSON(jsonProjectInvalidClass.toJSONString());
        assertEquals("Correct error code returned", 12, ret);

        JSONObject jsonProjectDupClass = buildJSONProject();
        JSONArray jsonClassesDupClass = (JSONArray)jsonProjectDupClass.get("classes");
        JSONObject dupJsonClass = (JSONObject)jsonClassesDupClass.get(0);
        jsonClassesDupClass.add(dupJsonClass);
        jsonProjectDupClass.put("classes", jsonClassesDupClass);
        ret = project.loadFromJSON(jsonProjectDupClass.toJSONString());
        assertEquals("Correct error code returned", 12, ret);

        JSONObject jsonProjectNoFromClass = buildJSONProject();
        JSONArray jsonRelationshipsNoFromClass = (JSONArray)jsonProjectNoFromClass.get("relationships");
        JSONObject jsonRelationshipNoFromClass = (JSONObject)jsonRelationshipsNoFromClass.get(0);
        jsonRelationshipNoFromClass.remove("from");
        ret = project.loadFromJSON(jsonProjectNoFromClass.toJSONString());
        assertEquals("Correct error code returned", 12, ret);

        JSONObject jsonProjectNoToClass = buildJSONProject();
        JSONArray jsonRelationshipsNoToClass = (JSONArray)jsonProjectNoToClass.get("relationships");
        JSONObject jsonRelationshipNoToClass = (JSONObject)jsonRelationshipsNoToClass.get(0);
        jsonRelationshipNoToClass.remove("to");
        ret = project.loadFromJSON(jsonProjectNoToClass.toJSONString());
        assertEquals("Correct error code returned", 12, ret);

        JSONObject jsonProjectNoRelType = buildJSONProject();
        JSONArray jsonRelationshipsNoRelType = (JSONArray)jsonProjectNoRelType.get("relationships");
        JSONObject jsonRelationshipNoRelType = (JSONObject)jsonRelationshipsNoRelType.get(0);
        jsonRelationshipNoRelType.remove("type");
        ret = project.loadFromJSON(jsonProjectNoRelType.toJSONString());
        assertEquals("Correct error code returned", 12, ret);

        JSONObject jsonProjectDupRel = buildJSONProject();
        JSONArray jsonRelationshipsDupRel = (JSONArray)jsonProjectDupRel.get("relationships");
        JSONObject dupJsonRelationship = (JSONObject)jsonRelationshipsDupRel.get(0);
        jsonRelationshipsDupRel.add(dupJsonRelationship);
        ret = project.loadFromJSON(jsonProjectDupRel.toJSONString());
        assertEquals("Correct error code returned", 12, ret);

        JSONObject jsonProjectInvalidRelType = buildJSONProject();
        JSONArray jsonRelationshipsInvalidRelType = (JSONArray)jsonProjectInvalidRelType.get("relationships");
        JSONObject jsonRelationshipInvalidRelType = (JSONObject)jsonRelationshipsDupRel.get(0);
        jsonRelationshipInvalidRelType.put("type", "z");
        jsonRelationshipsInvalidRelType.clear();
        jsonRelationshipsInvalidRelType.add(jsonRelationshipInvalidRelType);
        ret = project.loadFromJSON(jsonProjectInvalidRelType.toJSONString());
        assertEquals("Correct error code returned", 12, ret);

        JSONObject jsonProjectBadCast = buildJSONProject();
        JSONArray jsonClassesBadCast = (JSONArray)jsonProjectBadCast.get("classes");
        JSONObject jsonClassBadCast = (JSONObject)jsonClassesBadCast.get(0);
        jsonClassBadCast.put("isOpen", "abc");
        ret = project.loadFromJSON(jsonProjectBadCast.toJSONString());
        assertEquals("Correct error code returned", 12, ret);
    }

    @Test
    public void testValidityCheck()
    {
        WorkingProject project = new WorkingProject();

        JSONObject jsonProject = buildJSONProject();
        JSONArray jsonClasses = (JSONArray)jsonProject.get("classes");
        JSONObject jsonClass = (JSONObject)jsonClasses.get(0);
        jsonClass.put("name", "Invalid Name");
        int ret = project.loadFromJSON(jsonProject.toJSONString());
        assertEquals("Correct error code returned", 12, ret);
        jsonClass.put("name", "Apple");

        JSONArray jsonFields = (JSONArray)jsonClass.get("fields");
        JSONObject jsonField = (JSONObject)jsonFields.get(0);
        jsonField.put("name", "invalid name");
        ret = project.loadFromJSON(jsonProject.toJSONString());
        assertEquals("Correct error code returned", 12, ret);
        jsonField.put("name", "weight");

        jsonField.put("type", "invalid type");
        ret = project.loadFromJSON(jsonProject.toJSONString());
        assertEquals("Correct error code returned", 12, ret);
        jsonField.put("type", "double");

        JSONArray jsonMethods = (JSONArray)jsonClass.get("methods");
        JSONObject jsonMethod = (JSONObject)jsonMethods.get(0);
        jsonMethod.put("name", "invalid name");
        ret = project.loadFromJSON(jsonProject.toJSONString());
        assertEquals("Correct error code returned", 12, ret);
        jsonMethod.put("name", "setWeight");

        jsonMethod.put("type", "invalid type");
        ret = project.loadFromJSON(jsonProject.toJSONString());
        assertEquals("Correct error code returned", 12, ret);
        jsonMethod.put("type", "void");

        JSONArray jsonParams = (JSONArray)jsonMethod.get("parameters");
        JSONObject jsonParam = (JSONObject)jsonParams.get(0);
        jsonParam.put("name", "invalid name");
        ret = project.loadFromJSON(jsonProject.toJSONString());
        assertEquals("Correct error code returned", 12, ret);
        jsonParam.put("name", "newWeight");

        jsonParam.put("type", "invalid type");
        ret = project.loadFromJSON(jsonProject.toJSONString());
        assertEquals("Correct error code returned", 12, ret);
        jsonParam.put("type", "double");

        JSONArray jsonRelationships = (JSONArray)jsonProject.get("relationships");
        JSONObject jsonRelationship = (JSONObject)jsonRelationships.get(0);
        jsonRelationship.put("from", "Orange");
        ret = project.loadFromJSON(jsonProject.toJSONString());
        assertEquals("Correct error code returned", 12, ret);
        jsonRelationship.put("from", "Apple");

        jsonRelationship.put("to", "Orange");
        ret = project.loadFromJSON(jsonProject.toJSONString());
        assertEquals("Correct error code returned", 12, ret);
    }

    @Test
    public void testCopy()
    {
        WorkingProject project = buildMockWorkingProject();
        project.addRelationship("Fruits", "Vegetables", "I");
        WorkingProject projectCopy = project.copy();

        assertNotSame("Copy is a new object", project, projectCopy);
        assertEquals("Same class count", project.getClassNames().size(), projectCopy.getClassNames().size());
        assertEquals("Same relationship count", project.getRelationships().size(), projectCopy.getRelationships().size());
        for (String className : project.getClassNames())
        {
            assertTrue("Same class name", projectCopy.hasClass(className));
            assertEquals("Same isOpen value", project.getClass(className).isOpen(), projectCopy.getClass(className).isOpen());
        }
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

    /**
     * Create a valid JSON working project to load
     * @return a JSONObject encoding a working project
     */
    private static JSONObject buildJSONProject()
    {
        JSONObject jsonProject = new JSONObject();
        JSONArray jsonClasses = new JSONArray();
        JSONArray jsonRelationships = new JSONArray();

        JSONObject jsonClass = new JSONObject();
        jsonClass.put("name", "Apple");
        jsonClass.put("isOpen", true);

        JSONArray jsonFields = new JSONArray();
        JSONObject jsonField = new JSONObject();
        jsonField.put("name", "weight");
        jsonField.put("type", "double");
        jsonField.put("visibility", visibility.PRIVATE.name());
        jsonFields.add(jsonField);

        JSONArray jsonMethods = new JSONArray();
        JSONObject jsonMethod = new JSONObject();
        jsonMethod.put("name", "setWeight");
        jsonMethod.put("type", "void");
        jsonMethod.put("visibility", visibility.PUBLIC.name());

        JSONArray jsonParams = new JSONArray();
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("name", "newWeight");
        jsonParam.put("type", "double");

        jsonParams.add(jsonParam);
        jsonMethod.put("parameters", jsonParams);
        jsonMethods.add(jsonMethod);

        jsonClass.put("fields", jsonFields);
        jsonClass.put("methods", jsonMethods);
        jsonClasses.add(jsonClass);

        JSONObject jsonRelationship = new JSONObject();
        jsonRelationship.put("from", "Apple");
        jsonRelationship.put("to", "Apple");
        jsonRelationship.put("type", relationshipType.INHERITANCE.name());
        jsonRelationships.add(jsonRelationship);

        jsonProject.put("classes", jsonClasses);
        jsonProject.put("relationships", jsonRelationships);

        return jsonProject;
    }
}
