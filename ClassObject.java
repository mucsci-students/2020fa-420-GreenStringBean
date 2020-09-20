import java.util.ArrayList;

public class ClassObject
{   
    private String name;
    private boolean isOpen;
    private ArrayList<Attribute> attributes;
  
    public ClassObject()
    {
        System.out.println("error, no name");
    }
    public ClassObject(String giveName)
    {
        name = giveName;
        isOpen = false;
        attributes = new ArrayList<Attribute>();
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
        if (getAttrIndex(attrName) == -1)
        {
            attributes.add(new Attribute(attrName));
        }
        else
        {
            System.out.println("error, attribute already exists");
        }
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
        int oldNameIndex = getAttrIndex(oldName);
        if (oldNameIndex != -1)
        {
            int newNameIndex = getAttrIndex(newName);
            if (newNameIndex == -1)
            {
                attributes.get(oldNameIndex).setName(newName);
            }
            else
            {
                System.out.println("error, attribute with new name already exists");
            }
        }
        else
        {
            System.out.println("error, attribute doesn't exist");
        }
    }
    public boolean hasAttribute(String attrName)
    {
        for (Attribute attr : attributes)
        {
            if(attr.getName().equals(attrName))
                return true;
        }
        return false;
    }
    public void printAttributes()
    {
        for (Attribute attr : attributes)
        {
            System.out.println(attr.getName());
        }
    }
    private int getAttrIndex(String attrName)
    {
        for(int i = 0; i < attributes.size(); i++)
        {
            if(attributes.get(i).getName().equals(attrName))
                return i;
        }
        return -1;
    }
}
