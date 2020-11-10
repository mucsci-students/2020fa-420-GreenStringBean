import model.ClassObject;
import model.Model;
import view.Observer;

/**
 * A mock observer is an implementation of observer which recieves updates with
 * working projects and class objects, and stores them in variables where they
 * can be accessed by a driver class.
 */
public class MockObserver implements Observer {
    public boolean wasNotified;
    public boolean wasNotifiedWithProject;
    public boolean wasNotifiedWithClass;
    public Model projectRecieved;
    public ClassObject classRecieved;

    public MockObserver()
    {
        wasNotified = false;
        wasNotifiedWithProject = false;
        wasNotifiedWithClass = false;
    }

    public void onUpdate(Model project)
    {
        wasNotified = true;
        wasNotifiedWithProject = true;
        projectRecieved = project;
    }

    public void onUpdate(ClassObject classObj)
    {
        wasNotified = true;
        wasNotifiedWithClass = true;
        classRecieved = classObj;
    }

    public void reset()
    {
        wasNotified = false;
        wasNotifiedWithProject = false;
        wasNotifiedWithClass = false;
        projectRecieved = null;
        classRecieved = null;
    }
}
