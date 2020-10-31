import org.junit.Test;
import org.json.simple.JSONObject;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import model.ClassObject;
import model.Field;
import model.Method;

public class ClassObjectTest {

    String name;
    String type;
    ClassObject c;
    @Test
    public void TestCreateClassObject (String name, String type)
    {
        ClassObject c = new ClassObject("Fruit");
    }

    @Test
    public void TestGetClassObjectName ()
    {
        assertTrue(null, c.getName().equals(name));
    }

    @Test 
    public void TestSetClassName ()
    {
        String newName;
        if (name.equals("SSN"))
            newName = "Routing_Number";
        else if (name.equals("Routing_Number"))
            newName = "SSN";
        else 
            newName = "Routing_Number";
        
        c.setName(newName);
        assertTrue(null, c.getName().equals(newName));
    }

    @Test
    public void TestAddField()
    {
        c.addField("Edible", "bool");
        assertTrue(c.getField("Edible") != null);
    }

    @Test
    public void TestRenameField()
    {
        c.renameField("Edible", "Nontoxic");
        assertTrue(c.getField("Edible") == null);
        assertTrue(c.getField("Nontoxic") != null);
    }

    @Test
    public void TestChangeFieldType()
    {
        c.changeFieldType("Nontoxic", "int");
        assertTrue(c.getField("Nontoxic").getType() == "int");
    }

    @Test
    public void TestRemoveField()
    {
        c.removeField("Nontoxic");
        assertTrue(c.getField("Nontoxic") == null);
    }

    @Test
    public void TestAddMethod()
    {
        c.addMethod("Eat", "void");
        assertTrue(c.getMethod("Eat").getType() == "void");
    }

    @Test
    public void TestRenameMethod()
    {
        c.renameMethod("Eat", "Cook");
        assertTrue(c.getMethod("Eat") == null);
        assertTrue(c.getMethod("Cook") != null);
    }

    @Test
    public void TestChangeMethodType()
    {
        c.changeMethodType("Cook", "Meal");
        assertTrue(c.getMethod("Cook").getType() == "Meal");
    }

    @Test
    public void TestAddParameter()
    {
        c.addParameter("Cook", "Time", "time");
        assertTrue(c.getMethod("Cook").getParameters().contains(new Parameter("Time", "time")));
    } 

    @Test
    public void TestRenameParameter()
    {
        c.renameParameter("Cook", "Time", "Prep_Time");
        assertTrue(c.getMethod("Cook").getParameters().contains(new Parameter("Prep_Time", "time")));
    }

    @Test
    public void TestChangeParameterType()
    {
        c.changeParameterType("Cook", "Prep_Time", "float");
        assertTrue(c.getMethod("Cook").getParameters().contains(new Parameter("Prep_Time", "float")));
    }

    @Test
    public void TestRemoveParameter()
    {
        c.removeParameter("Cook", "Prep_Time");
        assertTrue(!c.getMethod("Cook").getParameters().contains(new Parameter("Prep_Time", "float")));
    }

    public void TestRemoveMethod()
    {
        c.removeMethod("Cook");
        assertTrue(c.getMethod("Cook") == null);
    }

    @Test
    public void TestToJSON ()
    {
        ClassObject d = new ClassObject("Construction");
        d.addField("Height", "double");
        d.addMethod("BuildHouse", "void");
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

}