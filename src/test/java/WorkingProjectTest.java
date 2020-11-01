import org.json.simple.*;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertTrue;
import model.*;
import model.Parameter;

public class WorkingProjectTest {

    @Test 
    public void testCreateProject()
    {
        WorkingProject wp = new WorkingProject();
        assertTrue(wp != null);
    }

    @Test
    public void testAddClass()
    {
        WorkingProject wp = new WorkingProject();
        wp.addClass("Fruits");
        assertTrue(wp.hasClass("Fruits"));
    }

    @Test
    public void testAddField()
    {
        WorkingProject wp = new WorkingProject();
        wp.addClass("Fruits");
        wp.addField("Fruits", "mass", "float", "public");
        assertTrue(wp.getClass("Fruits").hasField("mass"));
    }

    @Test
    public void testAddMethod()
    {
        WorkingProject wp = new WorkingProject();
        wp.addClass("Fruits");
        wp.addMethod("Fruits", "Purchase", "float", "public");
        assertTrue(wp.getClass("Fruits").hasMethod("Purchase"));
    }

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

    @Test
    public void testChangeFieldType()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.changeFieldType("Fruits", "nutrients", "double");
        assertTrue(wp.getClass("Fruits").getField("nutrients").getType() == "double");
    }

    @Test
    public void testChangeMethodType()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.changeMethodType("Fruits", "Eat", "Time");
        assertTrue(wp.getClass("Fruits").getMethod("Eat").getType().equals("Time"));
    }

    @Test
    public void testChangeParameterType()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.changeParameterType("Fruits", "Eat", "cooked", "int");
        //Note: Can Parameter have a simple getParameter method that takes a name and returns the Parameter object instead of a list?
        assertTrue(wp.getClass("Fruits").getMethod("Eat").getParameters().get(0).getType().equals("int"));
    }

    @Test
    public void testChangeFieldName()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.renameField("Fruits", "nutrients", "nutritionalContent");
        assertTrue(wp.getClass("Fruits").hasField("nutritionalContent"));
        assertTrue(!wp.getClass("Fruits").hasField("nutrients"));
    }

    @Test
    public void testChangeMethodName()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.renameMethod("Fruits", "Eat", "Digest");
        assertTrue(wp.getClass("Fruits").hasMethod("Digest"));
        assertTrue(!wp.getClass("Fruits").hasMethod("Eat"));
    }

    @Test
    public void testChangeParameterName()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.renameParameter("Fruits", "Eat", "cooked", "prepared");
        assertTrue(wp.getClass("Fruits").getMethod("Eat").getParameters().get(0).getName().equals("prepared"));
        assertTrue(!(wp.getClass("Fruits").getMethod("Eat").getParameters().get(0).getName().equals("cooked")));
    }
        

    @Test
    public void testChangeFieldVisibility()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.changeFieldVisibility("Fruits", "nutrients", "private");
        assertTrue(wp.getClass("Fruits").getField("nutrients").getVisibility().name() == VisibleDeclaration.visibility.PRIVATE.toString());
    }

    @Test
    public void testChangeMethodVisibility()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.changeMethodVisibility("Fruits", "Eat", "private");
        assertTrue(wp.getClass("Fruits").getMethod("Eat").getVisibility().name() == VisibleDeclaration.visibility.PRIVATE.toString());
    }

    @Test
    public void testAddRelationship()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.addRelationship("Fruits", "Vegetables", "A");
        assertTrue(wp.hasRelationship("Fruits", "Vegetables"));
        //Need to check relationship type
    }

    @Test
    public void testChangeRelationshipType()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.addRelationship("Fruits", "Vegetables", "A");
        wp.changeRelationshipType("Fruits", "Vegetables", "C");
        //Need to check relationship type
    }

    @Test
    public void testRemoveRelationship()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.addRelationship("Fruits", "Vegeatbles", "A");
        wp.removeRelationship("Fruits", "Vegetables");
        assertTrue(!wp.hasRelationship("Fruits", "Vegetables"));
    }

    @Test
    public void testRemoveField()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.removeField("Fruits", "nutrients");
        assertTrue(!wp.getClass("Fruits").hasField("nutrients"));
    }

    @Test
    public void testRemoveParameter()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.removeParameter("Fruits", "Eat", "cooked");
        assertTrue(!wp.getClass("Fruits").getMethod("Eat").hasParameter("cooked"));
    }

    @Test
    public void testRemoveMethod()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.removeMethod("Fruits", "Eat");
        assertTrue(!wp.getClass("Fruits").hasMethod("Eat"));
    }

    @Test
    public void testRemoveClass()
    {
        WorkingProject wp = buildMockWorkingProject();
        wp.removeClass("Fruits");
        assertTrue(!wp.hasClass("Fruits"));
    }

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
    //public static void main(String[] args)
    //{
        /*
        System.out.println("-Testing Working Project Class");

        // Test creating a new project
        System.out.println("-Creating working project");
        WorkingProject project = new WorkingProject();

        // Test printing empty class/relationship lists
        System.out.println("-Printing empty class list");
        project.printClasses();
        System.out.println("-Printing empty relationship list");
        project.printRelationships();

        // Test adding classes
        System.out.println("-Adding a new class \"a\"");
        project.addClass("a");
        System.out.println("-Adding a new class \"b\"");
        project.addClass("b");
        System.out.println("-Adding a new class \"c\"");
        project.addClass("c");

        // Test adding class with empty name (should behave as normal for now)
        System.out.println("-Adding a new class \"\"");
        project.addClass("");

        // Test adding an existing class
        System.out.println("-Adding another class called \"a\"");
        project.addClass("a");

        // Test opening/closing classes (no effect for now)
        System.out.println("-Opening class a");
        project.openClass("a");
        System.out.println("-Opening class a again");
        project.openClass("a");
        System.out.println("-Closing class a");
        project.closeClass("a");
        System.out.println("-Closing class a again");
        project.closeClass("a");


        // Test adding relationships
        System.out.println("-Adding a new relationship from a to b");
        project.addRelationship("a", "b");
        System.out.println("-Adding a new relationship from b to a");
        project.addRelationship("b", "a");
        System.out.println("-Adding a new relationship from a to c");
        project.addRelationship("a", "c");
        System.out.println("-Adding a new relationship from a to a");
        project.addRelationship("a", "a");

        // Test adding an existing relationship
        System.out.println("-Adding another relationship from a to b");
        project.addRelationship("a", "b");

        // Test adding a relationship involving a class that doesn't exist
        System.out.println("-Adding a new relationship from a to d");
        project.addRelationship("a", "d");
        System.out.println("-Adding a new relationship from d to a");
        project.addRelationship("d", "a");
        System.out.println("-Adding a new relationship from d to e");
        project.addRelationship("d", "e");

        // Test printing class/relationship lists
        System.out.println("-Printing class list");
        project.printClasses();
        System.out.println("-Printing relationship list");
        project.printRelationships();

        // Test printing empty attribute lists
        System.out.println("-Printing a's attribute list");
        project.printAttributes("a");
        System.out.println("-Printing b's attribute list");
        project.printAttributes("b");
        System.out.println("-Printing c's attribute list");
        project.printAttributes("c");

        // Test printing attributes of a class that doens't exist
        System.out.println("-Printing d's attribute list");
        project.printAttributes("d");

        // Test adding attributes
        System.out.println("-Adding a new attribute \"x\" to a");
        project.addAttribute("a", "x");
        System.out.println("-Adding a new attribute \"y\" to a");
        project.addAttribute("a", "y");
        System.out.println("-Adding a new attribute \"y\" to b");
        project.addAttribute("b", "y");
        System.out.println("-Adding a new attribute \"z\" to c");
        project.addAttribute("c", "z");

        // Test adding attribute with empty name
        System.out.println("-Adding a new attribute \"\" to c");
        project.addAttribute("c", "");

        // Test adding an attribute to a class that already has it
        System.out.println("-Adding another attribute called \"x\" to a");
        project.addAttribute("a", "x");

        // Test adding an attribute to a class that doesn't exist
        System.out.println("-Adding a new attribute \"x\" to d");
        project.addAttribute("d", "x");

        // Test printing attribute lists
        System.out.println("-Printing a's attribute list");
        project.printAttributes("a");
        System.out.println("-Printing b's attribute list");
        project.printAttributes("b");
        System.out.println("-Printing c's attribute list");
        project.printAttributes("c");

        // Test renaming an attribute
        System.out.println("-Renaming a's attribute x to w");
        project.renameAttribute("a", "x", "w");
        System.out.println("-Printing a's attribute list");
        project.printAttributes("a");

        // Test renaming an attribute to an existing name
        System.out.println("-Renaming a's attribute y to w");
        project.renameAttribute("a", "y", "w");

        // Test renaming an attribute that doesn't exist
        System.out.println("-Renaming a's attribute v to u");
        project.renameAttribute("a", "v", "u");

        // Test removing an attribute
        System.out.println("-Removing attribute y from a");
        project.removeAttribute("a", "y");
        System.out.println("-Printing a's attribute list");
        project.printAttributes("a");

        // Test removing an attribute that doesn't exist
        System.out.println("-Removing attribute v from a");
        project.removeAttribute("a", "v");

        // Test renaming a class
        System.out.println("-Renaming class a to m");
        project.renameClass("a", "m");
        System.out.println("-Printing class list");
        project.printClasses();

        // Test renaming a class to an existing name
        System.out.println("-Renaming class b to m");
        project.renameClass("b", "m");

        // Test renaming a class that doesn't exist
        System.out.println("-Renaming class d to n");
        project.renameClass("d", "n");

        // Test removing a class
        System.out.println("-Removing class b");
        project.removeClass("b");
        System.out.println("-Printing class list");
        project.printClasses();

        // Test removing a class that doesn't exist
        System.out.println("-Removing class d");
        project.removeClass("d");

        // Test that relationships updated properly
        System.out.println("-Printing relationship list");
        project.printRelationships();

        // Test removing a relationship
        System.out.println("-Removing relationship from m to c");
        project.removeRelationship("m", "c");
        System.out.println("-Printing relationship list");
        project.printRelationships();

        // Test removing a relationship that doesn't exist
        System.out.println("-Remvoing relationship from c to m");
        project.removeRelationship("c", "m");


        // TEST JSON ENCODE
        WorkingProject encodeTest = new WorkingProject();
        encodeTest.addClass("Apple");
        encodeTest.addClass("Tomato");
        encodeTest.addAttribute("Apple", "Seeds");
        encodeTest.addAttribute("Apple", "Core");
        encodeTest.addAttribute("Tomato", "Sauce");
        encodeTest.addRelationship("Apple", "Tomato");
        String j = encodeTest.toJSONString();
        System.out.println(j);
        WorkingProject loadedProject = loadProject(j);
        System.out.println(loadedProject.toJSONString());
        encodeTest.printClasses();
        encodeTest.printAttributes("Apple");
        encodeTest.printRelationships();
        loadedProject.printClasses();
        loadedProject.printAttributes("Apple");
        loadedProject.printRelationships();
    }

    public static WorkingProject loadProject(String projectString)
    {
        WorkingProject loadedProject = new WorkingProject();

        Object obj = JSONValue.parse(projectString);
        JSONObject object = (JSONObject)obj;
        JSONArray classes = (JSONArray)object.get("Classes");
        JSONArray relationships = (JSONArray)object.get("Relationships");
        
        for (int i = 0; i < classes.size(); ++i)
        {
            JSONObject c = (JSONObject)classes.get(i);
            String className = (String)c.get("Name");
            loadedProject.addClass(className);

            JSONArray attributes = (JSONArray)c.get("Attributes");
            for (int j = 0; j < attributes.size(); ++j)
            {
                JSONObject a = (JSONObject)attributes.get(j);
                String attributeName = (String)a.get("Name");
                //String attributeType = a.get("Type");
                //loadedProject.addAttribute(className, attributeName, attributeType);
                loadedProject.addAttribute(className, attributeName);
            }
        }
        for (int i = 0; i < relationships.size(); ++i)
        {
            JSONObject r = (JSONObject)relationships.get(i);
            String classOne = (String)r.get("ClassOne");
            String classTwo = (String)r.get("ClassTwo");
            loadedProject.addRelationship(classOne, classTwo);
        }

        return loadedProject;*/
    //}
//}
