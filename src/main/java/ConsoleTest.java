import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;

public class ConsoleTest {
    

    @Test
    public void TestAddClass() {
        WorkingProject wp = EmptyProject();
        wp.addClass("Apple");
        //assertEquals(wp.classExists("Apple"), True);
    }

    private WorkingProject EmptyProject() {
        WorkingProject wp = new WorkingProject();
        return wp;
    }
    
}