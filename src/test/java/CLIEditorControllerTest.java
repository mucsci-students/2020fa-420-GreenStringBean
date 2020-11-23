import org.json.simple.*;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.beans.Visibility;
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

    @Ignore
    public void testChangeRelationshipType() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
        controller.addRelationship("Apple", "Orange", "A");
        controller.changeRelationshipType("Apple", "Orange", "I");
        assertEquals("Relationship is of type", controller.getProjectSnapshot().getRelationships().iterator().next().getType(), relationshipType.INHERITANCE);
    }

    @Ignore
    public void testToJSONString() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
    }

    @Test
    public void testUndo() throws IOException
    {
        ModelEditor modelEditor = new WorkingProjectEditor(new WorkingProject());
        CLIView view = new CLIEditorView();
        CLIEditorController controller = new CLIEditorController(view, modelEditor);
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
        controller.addClass("Apple");
        controller.undo();
        controller.redo();
        assertEquals("Action redone", controller.getProjectSnapshot().hasClass("Apple"), true);
    }
}