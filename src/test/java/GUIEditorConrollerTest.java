import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import controller.GUIEditorController;
import controller.ModelEditor;
import controller.WorkingProjectEditor;
import model.ClassObject;
import model.Field;
import model.Method;
import model.Model;
import model.Parameter;
import model.Relationship;
import model.WorkingProject;
import model.Relationship.relationshipType;
import model.VisibleDeclaration.visibility;

public class GUIEditorConrollerTest {
    @Test
    public void testContstructor()
    {
        Model model = new WorkingProject();
        model.addClass("Apple");
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);

        assertTrue("Controller has correct model", controller.getProjectSnapshot().hasClass("Apple"));
    }

    @Test
    public void testAlertView()
    {
        Model model = new WorkingProject();
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);

        controller.addClass("Invalid name");
        assertEquals("Controller properly alerted view", "Error: Name is not valid", view.lastAlert);
    }

    @Test
    public void testOpenCloseClass()
    {
        Model model = new WorkingProject();
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);
        
        controller.addClass("Apple");
        assertTrue("Class is open", controller.getProjectSnapshot().getClass("Apple").isOpen());
        controller.closeClass("Apple");
        assertFalse("Class is closed", controller.getProjectSnapshot().getClass("Apple").isOpen());
        controller.openClass("Apple");
        assertTrue("Class is open", controller.getProjectSnapshot().getClass("Apple").isOpen());
    }

    @Test
    public void testAddClass()
    {
        Model model = new WorkingProject();
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);
        
        controller.addClass("Apple");
        controller.addClass("Orange");
        Model snapshot = controller.getProjectSnapshot();
        assertTrue("Class exists", snapshot.hasClass("Apple"));
        assertTrue("Class exists", snapshot.hasClass("Orange"));
    }

    @Test
    public void testRemoveClass()
    {
        Model model = new WorkingProject();
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);
        
        controller.addClass("Apple");
        assertEquals("Class exists", controller.getProjectSnapshot().hasClass("Apple"), true);
        controller.removeClass("Apple");
        assertEquals("Class doesn't exist", controller.getProjectSnapshot().hasClass("Apple"), false);
    }

    @Test
    public void testRenameClass()
    {
        Model model = new WorkingProject();
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);
        
        controller.addClass("Apple");
        assertTrue("Class exists", controller.getProjectSnapshot().hasClass("Apple"));
        controller.renameClass("Apple", "Orange");
        assertFalse("Class doesn't exist", controller.getProjectSnapshot().hasClass("Apple"));
        assertTrue("Class exists", controller.getProjectSnapshot().hasClass("Orange"));
    }

    @Test
    public void testAddField()
    {
        Model model = new WorkingProject();
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);
        
        controller.addClass("Apple");
        controller.addField("Apple", "Sliced", "boolean", "public");
        assertEquals("Field name", controller.getProjectSnapshot().getClass("Apple").getField("Sliced").getName(), "Sliced");
        assertEquals("Field type", controller.getProjectSnapshot().getClass("Apple").getField("Sliced").getType(), "boolean");
        assertEquals("Field type", controller.getProjectSnapshot().getClass("Apple").getField("Sliced").getVisibility(), visibility.PUBLIC);
    }

    @Test
    public void testRemoveField()
    {
        Model model = new WorkingProject();
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);
        
        controller.addClass("Apple");
        controller.addField("Apple", "Sliced", "boolean", "public");
        controller.removeField("Apple", "Sliced");
        assertEquals("Field exists", controller.getProjectSnapshot().getClass("Apple").hasField("Sliced"), false);
    }

    @Test
    public void testRenameField()
    {
        Model model = new WorkingProject();
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);
        
        controller.addClass("Apple");
        controller.addField("Apple", "Sliced", "boolean", "public");
        controller.renameField("Apple", "Sliced", "Diced");
        assertEquals("Field doesn't exist", controller.getProjectSnapshot().getClass("Apple").hasField("Sliced"), false);
        assertEquals("Field exists", controller.getProjectSnapshot().getClass("Apple").hasField("Diced"), true);
    }

    @Test
    public void testChangeFieldVis()
    {
        Model model = new WorkingProject();
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);
        
        controller.addClass("Apple");
        controller.addField("Apple", "Sliced", "boolean", "public");
        assertEquals("Field visiblility", controller.getProjectSnapshot().getClass("Apple").getField("Sliced").getVisibility(), visibility.PUBLIC);
        controller.changeFieldVisibility("Apple", "Sliced", "protected");
        assertEquals("Field visiblility", controller.getProjectSnapshot().getClass("Apple").getField("Sliced").getVisibility(), visibility.PROTECTED);
    }

    @Test
    public void testChangeFieldType()
    {
        Model model = new WorkingProject();
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);
        
        controller.addClass("Apple");
        controller.addField("Apple", "Sliced", "boolean", "public");
        assertEquals("Field visiblility", controller.getProjectSnapshot().getClass("Apple").getField("Sliced").getType(), "boolean");
        controller.changeFieldType("Apple", "Sliced", "int");
        assertEquals("Field visiblility", controller.getProjectSnapshot().getClass("Apple").getField("Sliced").getType(), "int");
    }

    @Test
    public void testAddMethod()
    {
        Model model = new WorkingProject();
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);
        
        controller.addClass("Apple");
        controller.addMethod("Apple", "Eat", "void", "public", new ArrayList<String>(), new ArrayList<String>());
        assertEquals("Method name", controller.getProjectSnapshot().getClass("Apple").getMethod("Eat").getName(), "Eat");
        assertEquals("Method type", controller.getProjectSnapshot().getClass("Apple").getMethod("Eat").getType(), "void");
        assertEquals("Method type", controller.getProjectSnapshot().getClass("Apple").getMethod("Eat").getVisibility(), visibility.PUBLIC);
    }

    @Test
    public void testAddMethodWithParams()
    {
        Model model = new WorkingProject();
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);
        
        List<String> paramNames = new ArrayList<>();
        paramNames.add("calories");
        paramNames.add("isCooked");
        List<String> paramTypes = new ArrayList<>();
        paramTypes.add("int");
        paramTypes.add("boolean");
        controller.addClass("Apple");
        controller.addMethod("Apple", "Eat", "void", "public", paramNames, paramTypes);
        assertEquals("Method name", controller.getProjectSnapshot().getClass("Apple").getMethod("Eat").getName(), "Eat");
        assertEquals("Method type", controller.getProjectSnapshot().getClass("Apple").getMethod("Eat").getType(), "void");
        assertEquals("Method type", controller.getProjectSnapshot().getClass("Apple").getMethod("Eat").getVisibility(), visibility.PUBLIC);
    }

    @Test
    public void testRemoveMethod()
    {
        Model model = new WorkingProject();
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);
        
        controller.addClass("Apple");
        controller.addMethod("Apple", "Eat", "void", "public", new ArrayList<String>(), new ArrayList<String>());
        controller.removeMethod("Apple", "Eat");
        assertEquals("Method doesn't exist", controller.getProjectSnapshot().getClass("Apple").hasMethod("Eat"), false);
    }

    @Test
    public void testRenameMethod()
    {
        Model model = new WorkingProject();
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);
        
        controller.addClass("Apple");
        controller.addMethod("Apple", "Eat", "void", "public", new ArrayList<String>(), new ArrayList<String>());
        controller.renameMethod("Apple", "Eat", "Devour");
        assertEquals("Method doesn't exist", controller.getProjectSnapshot().getClass("Apple").hasMethod("Eat"), false);
        assertEquals("Method exists", controller.getProjectSnapshot().getClass("Apple").hasMethod("Devour"), true);
    }

    @Test
    public void testChangeMethodType()
    {
        Model model = new WorkingProject();
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);
        
        controller.addClass("Apple");
        controller.addMethod("Apple", "Eat", "void", "public", new ArrayList<String>(), new ArrayList<String>());
        controller.changeMethodType("Apple", "Eat", "int");
        assertEquals("Method is of type", controller.getProjectSnapshot().getClass("Apple").getMethod("Eat").getType(), "int");
    }

    @Test
    public void testChangeMethodVis()
    {
        Model model = new WorkingProject();
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);
        
        controller.addClass("Apple");
        controller.addMethod("Apple", "Eat", "void", "public", new ArrayList<String>(), new ArrayList<String>());
        controller.changeMethodVisibility("Apple", "Eat", "private");
        assertEquals("Method is of visibility", controller.getProjectSnapshot().getClass("Apple").getMethod("Eat").getVisibility(), visibility.PRIVATE);
    }

    @Test
    public void testLoadProject()
    {
        Model model = new WorkingProject();
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);

        // Test loading a project saved in CLI
        controller.loadProject(buildJSONProject().toJSONString());
        Model snapshotCLI = controller.getProjectSnapshot();

        assertTrue("Loaded correct class", snapshotCLI.hasClass("Apple"));
        ClassObject loadedClassCLI = snapshotCLI.getClass("Apple");
        
        assertTrue("Loaded class has correct field", loadedClassCLI.hasField("weight"));
        Field loadedFieldCLI = loadedClassCLI.getField("weight");
        assertEquals("Loaded field has correct data type", "double", loadedFieldCLI.getType());
        assertEquals("Loaded field has correct visibility", visibility.PRIVATE, loadedFieldCLI.getVisibility());

        assertTrue("Loaded class has correct method", loadedClassCLI.hasMethod("setWeight"));
        Method loadedMethodCLI = loadedClassCLI.getMethod("setWeight");
        assertEquals("Loaded method has correct return type", "void", loadedMethodCLI.getType());
        assertEquals("Loaded method has correct visibility", visibility.PUBLIC, loadedMethodCLI.getVisibility());

        assertTrue("Loaded method has correct parameter", loadedMethodCLI.hasParameter("newWeight"));
        Parameter loadedParamCLI = loadedMethodCLI.getParameters().get(0);
        assertEquals("Loaded parameter has correct data type", "double", loadedParamCLI.getType());

        assertEquals("Loaded project has a relationship", 1, snapshotCLI.getRelationships().size());
        for (Relationship loadedRelCLI : snapshotCLI.getRelationships())
        {
            assertEquals("Loaded relationship has correct \"from\" class", "Apple", loadedRelCLI.getClassNameFrom());
            assertEquals("Loaded relationship has correct \"to\" class", "Apple", loadedRelCLI.getClassNameTo());
            assertEquals("Loaded relationship has correct type", relationshipType.INHERITANCE, loadedRelCLI.getType());
        }

        // Test loading a project saved in GUI
        JSONObject jsonProject = buildJSONProject();
        JSONObject jsonView = new JSONObject();
        jsonView.put("project", jsonProject);
        controller.loadProject(jsonView.toJSONString());

        Model snapshotGUI = controller.getProjectSnapshot();
        
        assertTrue("Loaded correct class", snapshotGUI.hasClass("Apple"));
        ClassObject loadedClassGUI = snapshotGUI.getClass("Apple");
        
        assertTrue("Loaded class has correct field", loadedClassGUI.hasField("weight"));
        Field loadedFieldGUI = loadedClassGUI.getField("weight");
        assertEquals("Loaded field has correct data type", "double", loadedFieldGUI.getType());
        assertEquals("Loaded field has correct visibility", visibility.PRIVATE, loadedFieldGUI.getVisibility());

        assertTrue("Loaded class has correct method", loadedClassGUI.hasMethod("setWeight"));
        Method loadedMethodGUI = loadedClassGUI.getMethod("setWeight");
        assertEquals("Loaded method has correct return type", "void", loadedMethodGUI.getType());
        assertEquals("Loaded method has correct visibility", visibility.PUBLIC, loadedMethodGUI.getVisibility());

        assertTrue("Loaded method has correct parameter", loadedMethodGUI.hasParameter("newWeight"));
        Parameter loadedParamGUI = loadedMethodGUI.getParameters().get(0);
        assertEquals("Loaded parameter has correct data type", "double", loadedParamGUI.getType());

        assertEquals("Loaded project has a relationship", 1, snapshotGUI.getRelationships().size());
        for (Relationship loadedRelGUI : snapshotGUI.getRelationships())
        {
            assertEquals("Loaded relationship has correct \"from\" class", "Apple", loadedRelGUI.getClassNameFrom());
            assertEquals("Loaded relationship has correct \"to\" class", "Apple", loadedRelGUI.getClassNameTo());
            assertEquals("Loaded relationship has correct type", relationshipType.INHERITANCE, loadedRelGUI.getType());
        }
    }

    @Test
    public void testAddRelationship()
    {
        Model model = new WorkingProject();
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);

        controller.addClass("Apple");
        controller.addClass("Orange");
        controller.addClass("Banana");
        controller.addClass("Peach");
        controller.addRelationship("Apple", "Orange", "A");
        assertEquals("Relationship exists", controller.getProjectSnapshot().hasRelationship("Apple", "Orange"), true);
        controller.addRelationship("Apple", "Peach", "C");
        assertEquals("Relationship exists", controller.getProjectSnapshot().hasRelationship("Apple", "Peach"), true);
        controller.addRelationship("Orange", "Banana", "I");
        assertEquals("Relationship exists", controller.getProjectSnapshot().hasRelationship("Orange", "Banana"), true);
        controller.addRelationship("Banana", "Peach", "R");
        assertEquals("Relationship exists", controller.getProjectSnapshot().hasRelationship("Banana", "Peach"), true);
    }

    @Test
    public void testRemoveRelationship()
    {
        Model model = new WorkingProject();
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);

        controller.addClass("Apple");
        controller.addClass("Orange");
        controller.addRelationship("Apple", "Orange", "A");
        controller.removeRelationship("Apple", "Orange");
        assertEquals("Relationship doesn't exist", controller.getProjectSnapshot().hasRelationship("Apple", "Orange"), false);
    }

    @Test
    public void testChangeRelationshipType()
    {
        Model model = new WorkingProject();
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);

        controller.addRelationship("Apple", "Orange", "A");
        controller.changeRelationshipType("Apple", "Orange", "I");
        for (Relationship r : controller.getProjectSnapshot().getRelationships())
        {
            assertEquals("Relationship is of type", r.getType(), relationshipType.INHERITANCE);
        }
    }

    @Test
    public void testUndo()
    {
        Model model = new WorkingProject();
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);

        controller.addClass("Orange");
        controller.addClass("Apple");
        controller.undo();
        assertFalse("Action undone", controller.getProjectSnapshot().hasClass("Apple"));
    }

    @Test
    public void testRedo()
    {
        Model model = new WorkingProject();
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);

        controller.addClass("Orange");
        controller.addClass("Apple");
        controller.undo();
        controller.redo();
        assertTrue("Action redone", controller.getProjectSnapshot().hasClass("Apple"));
    }

    @Test
    public void testClearProject()
    {
        Model model = new WorkingProject();
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);

        controller.addClass("Apple");
        controller.addRelationship("Apple", "Apple", "I");

        controller.clearProject();
        assertTrue("Cleared project has no classes", controller.getProjectSnapshot().getClassNames().isEmpty());
        assertTrue("Cleared project has no relationships", controller.getProjectSnapshot().getRelationships().isEmpty());
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
