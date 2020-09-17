import java.util.ArrayList;

public class WorkingProject {
    private ArrayList<ClassObject> classes;
    private ArrayList<Relationship> relationships;

    public WorkingProject()
    {
        classes = new ArrayList<ClassObject>;
        relationships = new ArrayList<Relationship>;
    }

    public void addClass(String className)
    {
        if (getClassIndex(className) == -1)
        {
            classes.add(new Class(className));
        }
        else
        {
            System.out.println("error, class already exists");
        }
    }

    public void removeClass(String className)
    {
        int index = getClassIndex(className);
        if (index != -1)
        {
            classes.remove(index);
        }
        else
        {
            System.out.println("error, class doesn't exist");
        }
    }

    public void renameClass(String oldName, String newName)
    {
        int index = getClassIndex(oldName);
        if (index != -1)
        {
            classes.get(index).setName(newName);
        }
        else
        {
            System.out.println("error, class doesn't exist");
        }
    }

    public void openClass(String className)
    {
        int index = getClassIndex(className);
        if (index != -1)
        {
            classes.get(index).open();
        }
        else
        {
            System.out.println("error, class doesn't exist");
        }
    }

    public void closeClass(String className)
    {
        int index = getClassIndex(className);
        if (index != -1)
        {
            classes.get(index).close();
        }
        else
        {
            System.out.println("error, class doesn't exist");
        }
    }

    public void addAttribute(String className, String attrName)
    {
        int index = getClassIndex(className);
        if (index != -1)
        {
            classes.get(index).addAttribute(attrName);
        }
        else
        {
            System.out.println("error, class doesn't exist");
        }
    }

    public void removeAttribute(String className, String attrName)
    {
        int index = getClassIndex(className);
        if (index != -1)
        {
            classes.get(index).removeAttribute(attrName);
        }
        else
        {
            System.out.println("error, class doesn't exist");
        }
    }

    public void renameAttribute(String className, String oldAttrName, String newAttrName)
    {
        int index = getClassIndex(className);
        if (index != -1)
        {
            classes.get(index).renameAttribute(oldAttrName, newAttrName);
        }
        else
        {
            System.out.println("error, class doesn't exist");
        }
    }

    public void addRelationship(String className1, String className2)
    {
        int classIndex1 = getClassIndex(className1);
        int classIndex2 = getClassIndex(className2);
        if (classIndex1 != -1 && classIndex2 != -1)
        {
            if (getRelationshipIndex(class1, class2) == -1)
            {
                relationships.add(new Relationship(classes.get(classIndex1), classes.get(classIndex2)));
            }
            else
            {
                System.out.println("error, relationship already exists");
            }
        }
        else
        {
            System.out.println("error, one or both classes don't exist");
        }
    }

    public void removeRelationship(String className1, String className2)
    {
        int index1 = getClassIndex(className1);
        int index2 = getClassIndex(className2);
        if (index1 != -1 && index2 != -1)
        {
            int relIndex = getRelationshipIndex(classes.get(index1), classes.get(index2));
            if (relIndex != -1)
            {
                relationships.remove(relIndex);
            }
            else
            {
                System.out.println("error, relationship doesn't exist");
            }
        }
        else
        {
            System.out.println("error, one or both classes don't exist");
        }
    }

    private int getClassIndex(String className)
    {
        for(int i = 0; i < classes.size(); ++i)
        {
            if (classes.get(i).getName.equals(className))
            {
                return i;
            }
        }
        return -1;
    }

    private int getRelationshipIndex(ClassObject class1, ClassObject class2)
    {
        for(int i = 0; i < relationships.size(); ++i)
        {
            if (relationships.get(i).getClassOne() == class1 && relationships.get(i).getClassTwo() == class2)
            {
                return i;
            }
        }
        return -1;
    }
}