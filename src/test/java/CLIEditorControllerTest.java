import org.json.simple.*;
import org.junit.Test;

import command.Command;

import org.junit.Ignore;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import model.*;
import model.Relationship.relationshipType;
import model.VisibleDeclaration.visibility;
import controller.*;
import view.*;

public class CLIEditorControllerTest {
    /**
     * Test creating a new CLIEditorController
     * 
     * @throws IOException
     */
    @Test
    public void testConstructor() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        assertTrue(controller != null);
    }

    @Ignore
    public void testGetProjectSnapshot() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        Model model = controller.getProjectSnapshot();
        assertTrue(model != null);
    }

    @Test
    public void testAddClass() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addClass("Apple");
        controller.addClass("Orange");
        Model model = controller.getProjectSnapshot();
        assertTrue("Class exists", model.hasClass("Apple"));
        assertTrue("Class exists", model.hasClass("Orange"));
    }

    @Test
    public void testRemoveClass() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addClass("Apple");
        assertEquals("Class exists", controller.getProjectSnapshot().hasClass("Apple"), true);
        controller.removeClass("Apple");
        assertEquals("Class doesn't exist", controller.getProjectSnapshot().hasClass("Apple"), false);
    }

    @Test
    public void testRenameClass() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addClass("Apple");
        assertTrue("Class exists", controller.getProjectSnapshot().hasClass("Apple"));
        controller.renameClass("Apple", "Orange");
        assertEquals("Class doesn't exist", controller.getProjectSnapshot().hasClass("Apple"), false);
        assertEquals("Class exists", controller.getProjectSnapshot().hasClass("Orange"), true);
    }

    @Test
    public void testOpenCloseClass() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addClass("Apple");
        assertTrue("Class is open", controller.getProjectSnapshot().getClass("Apple").isOpen());
        controller.closeClass("Apple");
        assertFalse("Class is closed", controller.getProjectSnapshot().getClass("Apple").isOpen());
        controller.openClass("Apple");
        assertTrue("Class is open", controller.getProjectSnapshot().getClass("Apple").isOpen());
    }

    @Test
    public void testAddField() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addClass("Apple");
        controller.addField("Apple", "Sliced", "boolean", "public");
        assertEquals("Field name", controller.getProjectSnapshot().getClass("Apple").getField("Sliced").getName(), "Sliced");
        assertEquals("Field type", controller.getProjectSnapshot().getClass("Apple").getField("Sliced").getType(), "boolean");
        assertEquals("Field type", controller.getProjectSnapshot().getClass("Apple").getField("Sliced").getVisibility(), visibility.PUBLIC);
    }

    @Test
    public void testRemoveField() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addClass("Apple");
        controller.addField("Apple", "Sliced", "boolean", "public");
        controller.removeField("Apple", "Sliced");
        assertEquals("Field exists", controller.getProjectSnapshot().getClass("Apple").hasField("Sliced"), false);
    }

    @Test
    public void testRenameField() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addClass("Apple");
        controller.addField("Apple", "Sliced", "boolean", "public");
        controller.renameField("Apple", "Sliced", "Diced");
        assertEquals("Field doesn't exist", controller.getProjectSnapshot().getClass("Apple").hasField("Sliced"), false);
        assertEquals("Field exists", controller.getProjectSnapshot().getClass("Apple").hasField("Diced"), true);
    }

    @Test
    public void testChangeFieldVis() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addClass("Apple");
        controller.addField("Apple", "Sliced", "boolean", "public");
        assertEquals("Field visiblility", controller.getProjectSnapshot().getClass("Apple").getField("Sliced").getVisibility(), visibility.PUBLIC);
        controller.changeFieldVisibility("Apple", "Sliced", "protected");
        assertEquals("Field visiblility", controller.getProjectSnapshot().getClass("Apple").getField("Sliced").getVisibility(), visibility.PROTECTED);
    }

    @Test
    public void testChangeFieldType() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addClass("Apple");
        controller.addField("Apple", "Sliced", "boolean", "public");
        assertEquals("Field visiblility", controller.getProjectSnapshot().getClass("Apple").getField("Sliced").getType(), "boolean");
        controller.changeFieldType("Apple", "Sliced", "int");
        assertEquals("Field visiblility", controller.getProjectSnapshot().getClass("Apple").getField("Sliced").getType(), "int");
    }

    @Test
    public void testAddMethod() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addClass("Apple");
        controller.addMethod("Apple", "Eat", "void", "public");
        assertEquals("Method name", controller.getProjectSnapshot().getClass("Apple").getMethod("Eat").getName(), "Eat");
        assertEquals("Method type", controller.getProjectSnapshot().getClass("Apple").getMethod("Eat").getType(), "void");
        assertEquals("Method type", controller.getProjectSnapshot().getClass("Apple").getMethod("Eat").getVisibility(), visibility.PUBLIC);
    }

    @Test
    public void testRemoveMethod() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addClass("Apple");
        controller.addMethod("Apple", "Eat", "void", "public");
        controller.removeMethod("Apple", "Eat");
        assertEquals("Method doesn't exist", controller.getProjectSnapshot().getClass("Apple").hasMethod("Eat"), false);
    }

    @Test
    public void testRenameMethod() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addClass("Apple");
        controller.addMethod("Apple", "Eat", "void", "public");
        controller.renameMethod("Apple", "Eat", "Devour");
        assertEquals("Method doesn't exist", controller.getProjectSnapshot().getClass("Apple").hasMethod("Eat"), false);
        assertEquals("Method exists", controller.getProjectSnapshot().getClass("Apple").hasMethod("Devour"), true);
    }

    @Test
    public void testChangeMethodType() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addClass("Apple");
        controller.addMethod("Apple", "Eat", "void", "public");
        controller.changeMethodType("Apple", "Eat", "int");
        assertEquals("Method is of type", controller.getProjectSnapshot().getClass("Apple").getMethod("Eat").getType(), "int");
    }

    @Test
    public void testChangeMethodVis() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addClass("Apple");
        controller.addMethod("Apple", "Eat", "void", "public");
        controller.changeMethodVisibility("Apple", "Eat", "private");
        assertEquals("Method is of visibility", controller.getProjectSnapshot().getClass("Apple").getMethod("Eat").getVisibility(), visibility.PRIVATE);
    }

    @Test
    public void testAddParameter() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addClass("Apple");
        controller.addMethod("Apple", "Eat", "void", "public");
        controller.addParameter("Apple", "Eat", "Seeds", "int");
        assertEquals("Parameter has name", controller.getProjectSnapshot().getClass("Apple").getMethod("Eat").getParameters().get(0).getName(), "Seeds");
        assertEquals("Parameter has type", controller.getProjectSnapshot().getClass("Apple").getMethod("Eat").getParameters().get(0).getType(), "int");
    }

    @Test
    public void testRemoveParameter() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addClass("Apple");
        controller.addMethod("Apple", "Eat", "void", "public");
        controller.addParameter("Apple", "Eat", "Seeds", "int");
        controller.removeParameter("Apple", "Eat", "Seeds");
        assertEquals("Parameter doesn't exist", controller.getProjectSnapshot().getClass("Apple").getMethod("Eat").hasParameter("Seeds"), false);
    }

    @Test
    public void testRenameParameter() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addClass("Apple");
        controller.addMethod("Apple", "Eat", "void", "public");
        controller.addParameter("Apple", "Eat", "Seeds", "int");
        controller.renameParameter("Apple", "Eat", "Seeds", "Pods");
        assertEquals("Parameter exists", controller.getProjectSnapshot().getClass("Apple").getMethod("Eat").hasParameter("Pods"), true);
    }

    @Test
    public void testChangeParameterType() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addClass("Apple");
        controller.addMethod("Apple", "Eat", "void", "public");
        controller.addParameter("Apple", "Eat", "Seeds", "int");
        controller.changeParameterType("Apple", "Eat", "Seeds", "boolean");
        assertEquals("Parameter is of type", controller.getProjectSnapshot().getClass("Apple").getMethod("Eat").getParameters().get(0).getType(), "boolean");
    }

    @Test
    public void testAddRelationship() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addClass("Apple");
        controller.addClass("Orange");
        controller.addClass("Banana");
        controller.addClass("Peach");
        controller.addRelationship("Apple", "Orange", "A");
        assertEquals("Relationship exists", controller.getProjectSnapshot().hasRelationship("Apple", "Orange"), true);
        //assertEquals("Relationship is of type", controller.getProjectSnapshot().getRelationships().iterator().next().getType(), relationshipType.AGGREGATION);
        controller.addRelationship("Apple", "Peach", "C");
        assertEquals("Relationship exists", controller.getProjectSnapshot().hasRelationship("Apple", "Peach"), true);
        //assertEquals("Relationship is of type", controller.getProjectSnapshot().getRelationships().iterator().next().getType(), relationshipType.COMPOSITION);
        controller.addRelationship("Orange", "Banana", "I");
        assertEquals("Relationship exists", controller.getProjectSnapshot().hasRelationship("Orange", "Banana"), true);
        //assertEquals("Relationship is of type", controller.getProjectSnapshot().getRelationships().iterator().next().getType(), relationshipType.INHERITANCE);
        controller.addRelationship("Banana", "Peach", "R");
        assertEquals("Relationship exists", controller.getProjectSnapshot().hasRelationship("Banana", "Peach"), true);
        //assertEquals("Relationship is of type", controller.getProjectSnapshot().getRelationships().iterator().next().getType(), relationshipType.REALIZATION);
    }

    @Test
    public void testRemoveRelationship() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addClass("Apple");
        controller.addClass("Orange");
        controller.addRelationship("Apple", "Orange", "A");
        controller.removeRelationship("Apple", "Orange");
        assertEquals("Relationship doesn't exist", controller.getProjectSnapshot().hasRelationship("Apple", "Orange"), false);
    }

    @Test
    public void testChangeRelationshipType() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addRelationship("Apple", "Orange", "A");
        controller.changeRelationshipType("Apple", "Orange", "I");
        for (Relationship r : controller.getProjectSnapshot().getRelationships())
        {
            assertEquals("Relationship is of type", r.getType(), relationshipType.INHERITANCE);
        }
    }

    @Test
    public void testUndo() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addClass("Orange");
        controller.addClass("Apple");
        controller.undo();
        assertEquals("Action undone", controller.getProjectSnapshot().hasClass("Apple"), false);
    }

    @Test
    public void testRedo() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addClass("Orange");
        controller.addClass("Apple");
        controller.undo();
        controller.redo();
        assertEquals("Action redone", controller.getProjectSnapshot().hasClass("Apple"), true);
    }

    @Test
    public void testLoadProject() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);

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
    public void testLoadInvalid() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);

        controller.loadProject("abc");
        assertEquals("Nothing was loaded", 0, controller.getProjectSnapshot().getClassNames().size());
        controller.loadProject("{\"project\":0}");
        assertEquals("Nothing was loaded", 0, controller.getProjectSnapshot().getClassNames().size());
    }

    @Test
    public void testToJSONString() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);

        controller.addClass("Apple");
        controller.addField("Apple", "weight", "double", "private");
        controller.addMethod("Apple", "setWeight", "void", "public");
        controller.addParameter("Apple", "setWeight", "newWeight", "double");
        controller.addRelationship("Apple", "Apple", "I");

        assertEquals("Correct toJSONString output", buildJSONProject(), (JSONObject)JSONValue.parse(controller.toJSONString()));
    }

    @Test
    public void testOptionalState() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);

        controller.addClass("Apple");
        Command cmd = modelEditor.getLastCommand();

        assertFalse("In CLI, optional state should not be used", cmd.hasOptionalState());
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