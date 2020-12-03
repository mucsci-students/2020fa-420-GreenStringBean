import org.json.simple.*;
import org.junit.Test;

import command.Command;
import command.RemoveClassCommand;
import model.Model;
import model.WorkingProject;
import command.AddClassCommand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommandTest {
    
    @Test
    public void testUnknownError()
    {
        Model mockModel = new UnknownErrorModel();
        Command cmd1 = new AddClassCommand(mockModel, "Apple");
        cmd1.execute();
        assertEquals(cmd1.getStatusMessage(), "Unknown error");

        Command cmd2 = new RemoveClassCommand(mockModel, "Apple");
        cmd2.execute();
        assertEquals(cmd2.getStatusMessage(), "Unknown error");
    }

    @Test
    public void testProjectState()
    {
        Model model = new WorkingProject();
        String modelState = model.toJSONString();
        Command cmd = new AddClassCommand(model, "Apple");
        cmd.execute();
        assertEquals("Correct saved state", modelState, cmd.getProjectState());
    }

    @Test
    public void testOptionalState()
    {
        Model model = new WorkingProject();
        Command cmd = new AddClassCommand(model, "Apple");
        cmd.execute();
        assertFalse("No optional state", cmd.hasOptionalState());

        cmd.setOptionalState("abc");

        assertTrue("Has optional state", cmd.hasOptionalState());
        assertEquals("Correct optional state", "abc", cmd.getOptionalState());
    }
}
