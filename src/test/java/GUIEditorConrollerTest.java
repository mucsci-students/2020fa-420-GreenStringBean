import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import controller.GUIEditorController;
import controller.ModelEditor;
import controller.WorkingProjectEditor;
import model.Model;
import model.WorkingProject;
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
        model.addClass("Apple");
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
        model.addClass("Apple");
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
        model.addClass("Apple");
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
        model.addClass("Apple");
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
        model.addClass("Apple");
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
        model.addClass("Apple");
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
        model.addClass("Apple");
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
        model.addClass("Apple");
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
        model.addClass("Apple");
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
        model.addClass("Apple");
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
        model.addClass("Apple");
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
        model.addClass("Apple");
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
        model.addClass("Apple");
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
        model.addClass("Apple");
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
        model.addClass("Apple");
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
        model.addClass("Apple");
        ModelEditor editor = new WorkingProjectEditor(model);
        MockView view = new MockView();
        GUIEditorController controller = new GUIEditorController(view, editor);
        
        controller.addClass("Apple");
        controller.addMethod("Apple", "Eat", "void", "public", new ArrayList<String>(), new ArrayList<String>());
        controller.changeMethodVisibility("Apple", "Eat", "private");
        assertEquals("Method is of visibility", controller.getProjectSnapshot().getClass("Apple").getMethod("Eat").getVisibility(), visibility.PRIVATE);
    }
}
