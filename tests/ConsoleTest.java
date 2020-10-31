import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;

//Use Dependency Injection to test the Console. Same goes for the GUI
public class ConsoleTest {
    

    @Test
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