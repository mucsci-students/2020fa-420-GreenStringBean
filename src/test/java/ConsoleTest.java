import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertTrue;
import model.WorkingProject;
import org.junit.Assert;

//Use Dependency Injection to test the Console. Same goes for the GUI
public class ConsoleTest {
    

    @Ignore
    public void TestAddClass() {
        WorkingProject wp = EmptyProject();
        wp.addClass("Apple");
        assertTrue(wp.hasClass("Apple"));
    }


    private WorkingProject EmptyProject() {
        WorkingProject wp = new WorkingProject();
        return wp;
    }
    
}