import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import controller.GUIEditorController;
import controller.ModelEditor;
import controller.WorkingProjectEditor;
import model.Model;
import model.WorkingProject;

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
}
