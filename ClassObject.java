import java.util.ArrayList;

public class ClassObjectMain
{   
    private String name;
    private boolean isOpen;
    private ArrayList<Attribute> attributes = new ArrayList<Attribute>();
    
    public ClassObjectMain()
    {
        System.out.println("error, no name");
    }
    public ClassObjectMain(String giveName)
    {
        name = giveName;
        isOpen = false;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String newName)
    {
        name = newName;
    }
    public boolean isOpen()
    {
        return isOpen;
    }
    public void close()
    {
        if(isOpen)
            isOpen = false;
        else
            System.out.println("it's already closed");
    }
    public void open()
    {
        if(isOpen)
            System.out.println("it's already open");
        else
            isOpen = true;
    }
    public void addAttribute(String attrName)
    {
        attributes.add(new Attribute(attrName));
    }
    public void removeAttribute(String attrName)
    {
        int index = getAttrIndex(attrName);
        if(index!=-1)
            attributes.remove(index);
        else
            System.out.println("error, attribute doesn't exist");
    }
    public void renameAttribute(String oldName, String newName)
    {
        int index = getAttrIndex(oldName);
        if(index!=-1)
            attributes[index].setName(newName);
        else
            System.out.println("error, attribute doesn't exist");
    }
    public boolean hasAttribute(String attrName)
    {
        for (Attribute attr : attributes)
        {
            if(attributes.getName.equals(attrName)
                return true;
        }
        return false;
    }
    private int getAttrIndex(String attrName)
    {
        for(int i = 0; i < attributes.size(); i++)
        {
            if(Attributes[i].getName().equals(attrName))
                return i;
        }
        return -1;
    }
    public static void main(String[]args)
    {
        new ClassObjectMain();
    }
}
