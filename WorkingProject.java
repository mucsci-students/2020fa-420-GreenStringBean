import java.util.ArrayList;

public class WorkingProject {
    private ArrayList<ClassObject> classes;
    private ArrayList<Relationship> relationships;

    // Create a new Working Project with empty ClassObject/Relationship lists
    public WorkingProject()
    {
        classes = new ArrayList<ClassObject>();
        relationships = new ArrayList<Relationship>();
    }

    // Add a new ClassObject called className to the end of the list
    // Print an error if there is already a ClassObject with that name in the list
    public void addClass(String className)
    {
        if (getClassIndex(className) == -1)
        {
            classes.add(new ClassObject(className));
        }
        else
        {
            System.out.println("error, class already exists");
        }
    }

    // Remove the ClassObject called className from the list
    // Print an error if there is no ClassObject with that name in the list
    public void removeClass(String className)
    {
        int index = getClassIndex(className);
        if (index != -1)
        {
            removeRelationshipsByClass(classes.get(index));
            classes.remove(index);
        }
        else
        {
            System.out.println("error, class doesn't exist");
        }
    }

    // Rename the ClassObject called oldName to newName
    // Print an error if there is no ClassObject called oldName in the list
    // Print an error if there is already a ClassObject called newName in the list
    public void renameClass(String oldName, String newName)
    {
        int oldNameIndex = getClassIndex(oldName);
        if (oldNameIndex != -1)
        {
            int newNameIndex = getClassIndex(newName);
            if (newNameIndex == -1)
            {
                classes.get(oldNameIndex).setName(newName);
            }
            else
            {
                System.out.println("error, class with new name already exists");
            }
        }
        else
        {
            System.out.println("error, class doesn't exist");
        }
    }

    // Open ClassObject called className
    // Print an error if there is no ClassObject with that name in the lsit
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

    // Close ClassObject called className
    // Print an error if there is no ClassObject with that name in the list
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

    // Add a new Attribute called attrName to the ClassObject called className
    // Print an error if there is no ClassObject with that name in the list
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

    // Remove the Attribute called attrName from the ClassObject called className
    // Print an error if there is no ClassObject with that name in the list
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

    // Rename the Attribute called oldAttrName to newAttrName in the ClassObject called className
    // Print an error if there is no ClassObject with that name in the list
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

    // Add a new ordered Relationship between the ClassObjects called className1 and className2 to the end of the list
    // Print an error if either of those names are not found in the ClassObject list
    // Print an error if there is already a Relationship between those classes in the list
    public void addRelationship(String className1, String className2)
    {
        int classIndex1 = getClassIndex(className1);
        int classIndex2 = getClassIndex(className2);
        if (classIndex1 != -1 && classIndex2 != -1)
        {
            ClassObject class1 = classes.get(classIndex1);
            ClassObject class2 = classes.get(classIndex2); 
            if (getRelationshipIndex(class1, class2) == -1)
            {
                relationships.add(new Relationship(class1, class2));
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

    // Remove the relationship between the ClassObjects called className1 and className2 from the list
    // Print an error if either of those names are not found in the ClassObject list
    // Print an error if there is no Relationship between those classes in the list
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

    // Print the name of each ClassObject in the list
    public void printClasses()
    {
        for (ClassObject classObj : classes)
        {
            System.out.println(classObj.getName());
        }
    }

    // Print the names of the ClassObjects in each Relationship in the list
    // Print in the format className1 -> className2
    public void printRelationships()
    {
        for (Relationship relation : relationships)
        {
            System.out.println(relation.getClassOne().getName() + " -> " + relation.getClassTwo().getName());
        }
    }

    // Print the name of each Attribute in the ClassObject called className
    // Print an error if there is no ClassObject with that name in the list
    public void printAttributes(String className)
    {
        int index = getClassIndex(className);
        if (index != -1)
        {
            classes.get(index).printAttributes();
        }
        else
        {
            System.out.println("error, class doesn't exist");
        }
    }

    // Return the index of the first ClassObject called className in the list
    // Return -1 if no ClassObject is found
    private int getClassIndex(String className)
    {
        for (int i = 0; i < classes.size(); ++i)
        {
            if (classes.get(i).getName().equals(className))
            {
                return i;
            }
        }
        return -1;
    }

    // Return the index of the first ordered Relationship between classObj1 and classObj2 in the list
    // Return -1 if no Relationship is found
    private int getRelationshipIndex(ClassObject classObj1, ClassObject classObj2)
    {
        for (int i = 0; i < relationships.size(); ++i)
        {
            if (relationships.get(i).getClassOne() == classObj1 && relationships.get(i).getClassTwo() == classObj2)
            {
                return i;
            }
        }
        return -1;
    }

    // Remove any relationships which contain classObj from the list
    private void removeRelationshipsByClass(ClassObject classObj)
    {
        int i = 0;
        while (i < relationships.size())
        {
            if (relationships.get(i).getClassOne() == classObj || relationships.get(i).getClassTwo() == classObj)
            {
                relationships.remove(i);
            }
            else
            {
                ++i;
            }
        }
    }
}