import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

public class WorkingProject {
    private ArrayList<ClassObject> classes;
    private ArrayList<Relationship> relationships;

    public WorkingProject()
    {
        classes = new ArrayList<ClassObject>();
        relationships = new ArrayList<Relationship>();
    }

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

    public void printClasses()
    {
        for (ClassObject classObj : classes)
        {
            System.out.println(classObj.getName());
        }
    }

    public void printRelationships()
    {
        for (Relationship relation : relationships)
        {
            System.out.println(relation.getClassOne().getName() + " -> " + relation.getClassTwo().getName());
        }
    }

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

    private int getRelationshipIndex(ClassObject class1, ClassObject class2)
    {
        for (int i = 0; i < relationships.size(); ++i)
        {
            if (relationships.get(i).getClassOne() == class1 && relationships.get(i).getClassTwo() == class2)
            {
                return i;
            }
        }
        return -1;
    }

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

    //Encode the project into a JSONArray by encoding the classes and their attributes, and the relationships
    public String toJSONString ()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"" + "Classes" + "\"");
        sb.append(":");
        sb.append("[");
        
        for (int i = 0; i < classes.size() - 1; ++i)
        {
            sb.append(classes.get(i).toJSONString());
            sb.append(",");
        }
        sb.append(classes.get(classes.size() - 1).toJSONString());
        sb.append("]");
        sb.append(",");
        sb.append("\"" + "Relationships" + "\"");
        sb.append(":");
        sb.append("[");

        for (int i = 0; i < relationships.size() - 1; ++i)
        {
            sb.append(relationships.get(i).toJSONString());
            sb.append(",");
        }
        sb.append(relationships.get(relationships.size() - 1).toJSONString());
        sb.append("]");
        sb.append("}");
        return sb.toString();
    }
}