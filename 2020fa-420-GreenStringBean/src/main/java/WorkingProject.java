import java.io.File;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

public class WorkingProject {
    private ArrayList<ClassObject> classes;
    private ArrayList<Relationship> relationships;
    private String currentLoadedFile;

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
    public void addField(String className, String fieldName, String fieldType)
    {
        int index = getClassIndex(className);
        if (index != -1)
        {
            classes.get(index).addField(fieldName, fieldType);
        }
        else
        {
            System.out.println("error, class doesn't exist");
        }
    }

    // Remove the Attribute called attrName from the ClassObject called className
    // Print an error if there is no ClassObject with that name in the list
    public void removeField(String className, String fieldName)
    {
        int index = getClassIndex(className);
        if (index != -1)
        {
            classes.get(index).removeField(fieldName);
        }
        else
        {
            System.out.println("error, class doesn't exist");
        }
    }

    // Rename the Attribute called oldAttrName to newAttrName in the ClassObject called className
    // Print an error if there is no ClassObject with that name in the list
    public void renameField(String className, String oldFieldName, String newFieldName)
    {
        int index = getClassIndex(className);
        if (index != -1)
        {
            classes.get(index).renameField(oldFieldName, newFieldName);
        }
        else
        {
            System.out.println("error, class doesn't exist");
        }
    }

    public void changeFieldType(String className, String fieldName, String newFieldType)
    {
        int index = getClassIndex(className);
        if (index != -1)
        {
            classes.get(index).changeFieldType(fieldName, newFieldType);
        }
        else
        {
            System.out.println("error, class doesn't exist");
        }
    }

    // Add a new Attribute called attrName to the ClassObject called className
    // Print an error if there is no ClassObject with that name in the list
    public void addMethod(String className, String methodName, String methodType)
    {
        int index = getClassIndex(className);
        if (index != -1)
        {
            classes.get(index).addMethod(methodName, methodType);
        }
        else
        {
            System.out.println("error, class doesn't exist");
        }
    }

    // Remove the Attribute called attrName from the ClassObject called className
    // Print an error if there is no ClassObject with that name in the list
    public void removeMethod(String className, String methodName)
    {
        int index = getClassIndex(className);
        if (index != -1)
        {
            classes.get(index).removeMethod(methodName);
        }
        else
        {
            System.out.println("error, class doesn't exist");
        }
    }

    // Rename the Attribute called oldAttrName to newAttrName in the ClassObject called className
    // Print an error if there is no ClassObject with that name in the list
    public void renameMethod(String className, String oldMethodName, String newMethodName)
    {
        int index = getClassIndex(className);
        if (index != -1)
        {
            classes.get(index).renameMethod(oldMethodName, newMethodName);
        }
        else
        {
            System.out.println("error, class doesn't exist");
        }
    }

    public void changeMethodType(String className, String methodName, String newMethodType)
    {
        int index = getClassIndex(className);
        if (index != -1)
        {
            classes.get(index).changeMethodType(methodName, newMethodType);
        }
        else
        {
            System.out.println("error, class doesn't exist");
        }
    }

    // Add a new Attribute called attrName to the ClassObject called className
    // Print an error if there is no ClassObject with that name in the list
    public void addParameter(String className, String methodName, String parameterName, String parameterType)
    {
        int index = getClassIndex(className);
        if (index != -1)
        {
            classes.get(index).addParameter(methodName, parameterName, parameterType);
        }
        else
        {
            System.out.println("error, class doesn't exist");
        }
    }

    // Remove the Attribute called attrName from the ClassObject called className
    // Print an error if there is no ClassObject with that name in the list
    public void removeParameter(String className, String methodName, String parameterName)
    {
        int index = getClassIndex(className);
        if (index != -1)
        {
            classes.get(index).removeParameter(methodName, parameterName);
        }
        else
        {
            System.out.println("error, class doesn't exist");
        }
    }

    // Rename the Attribute called oldAttrName to newAttrName in the ClassObject called className
    // Print an error if there is no ClassObject with that name in the list
    public void renameParameter(String className, String methodName, String oldParamName, String newParamName)
    {
        int index = getClassIndex(className);
        if (index != -1)
        {
            classes.get(index).renameParameter(methodName, oldParamName, newParamName);
        }
        else
        {
            System.out.println("error, class doesn't exist");
        }
    }

    public void changeParamType(String className, String methodName, String paramName, String newParamType)
    {
        int index = getClassIndex(className);
        if (index != -1)
        {
            classes.get(index).changeParameterType(methodName, paramName, newParamType);
        }
        else
        {
            System.out.println("error, class doesn't exist");
        }
    }

    // Add a new ordered Relationship between the ClassObjects called className1 and className2 to the end of the list
    // Print an error if either of those names are not found in the ClassObject list
    // Print an error if there is already a Relationship between those classes in the list
    public void addRelationship(String className1, String className2, String typeName)
    {
        int classIndex1 = getClassIndex(className1);
        int classIndex2 = getClassIndex(className2);
        Relationship.relationshipType type = stringToRelationshipType(typeName);
        if(type != null)
        {
            if (classIndex1 != -1 && classIndex2 != -1)
            {
                ClassObject class1 = classes.get(classIndex1);
                ClassObject class2 = classes.get(classIndex2); 
                if (getRelationshipIndex(class1, class2) == -1)
                {
                    relationships.add(new Relationship(class1, class2, type));
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

    // Change the relationship type
    // print an error if the relationship type doesn't exist
    public void changeRelationshipType(String className1, String className2, String newTypeName)
    {
        int index1 = getClassIndex(className1);
        int index2 = getClassIndex(className2);
        Relationship.relationshipType newType = stringToRelationshipType(newTypeName);
        if(newType != null)
        {
            if (index1 != -1 && index2 != -1)
            {
                int relIndex = getRelationshipIndex(classes.get(index1), classes.get(index2));
                if (relIndex != -1)
                {
                    relationships.get(relIndex).setRelationshipType(newType);
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
        
    }

    // Print the name of each ClassObject in the list
    public void printClasses()
    {
        for (ClassObject classObj : classes)
        {
            System.out.println(classObj.getName());
        }
    }

    // Find a class in the array and return it
    public ClassObject getClass(String className)
    {
      int index = getClassIndex(className);
      if (index != -1)
      {
        return classes.get(index);
      }
      else
      {
        System.out.println("error, class doesn’t exist");
        return null;
      }
    }
    
    // Print the names of the ClassObjects in each Relationship in the list
    // Print in the format className1 -> className2
    public void printRelationships()
    {
        for (Relationship relation : relationships)
        {
            System.out.println(relation.getClassOne().getName() + " -> " + relation.getClassTwo().getName() + " type = " + relation.getRelationshipType());
        }
    }

    // Print the name of each Attribute in the ClassObject called className
    // Print an error if there is no ClassObject with that name in the list
    public void printFields(String className)
    {
        int index = getClassIndex(className);
        if (index != -1)
        {
            classes.get(index).printFields();
        }
        else
        {
            System.out.println("error, class doesn't exist");
        }
    }

    // Print the name of each Attribute in the ClassObject called className
    // Print an error if there is no ClassObject with that name in the list
    public void printMethods(String className)
    {
        int index = getClassIndex(className);
        if (index != -1)
        {
            classes.get(index).printMethods();
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
    
    private Relationship.relationshipType stringToRelationshipType(String str)
    {
        switch(str){
            case "C":
                return Relationship.relationshipType.COMPOSITION;
            case "A":
                return Relationship.relationshipType.AGGREGATION;
            case "G":
                return Relationship.relationshipType.GENERALIZATION;
            case "I":
                return Relationship.relationshipType.INHERITANCE;
            default :
                System.out.println("error: not a valid relationship type");
                return null;
        }
    }

    public void loadFromJSON(String jsonString)
    {
        this.classes.clear();
        this.relationships.clear();
        
        Object obj = JSONValue.parse(jsonString);
        JSONObject object = (JSONObject)obj;
        JSONArray classes = (JSONArray)object.get("Classes");
        JSONArray relationships = (JSONArray)object.get("Relationships");
        
        for (int i = 0; i < classes.size(); ++i)
        {
            JSONObject c = (JSONObject)classes.get(i);
            String className = (String)c.get("Name");
            this.addClass(className);

            JSONArray attributes = (JSONArray)c.get("Attributes");
            for (int j = 0; j < attributes.size(); ++j)
            {
                JSONObject a = (JSONObject)attributes.get(j);
                String attributeName = (String)a.get("Name");
                String attributeType = (String)a.get("Type");
                String fieldOrMethod = (String)a.get("FieldOrMethod");
                if (fieldOrMethod.equals("Method"))
                {
                    this.addMethod(className, attributeName, attributeType);

                    JSONArray parameters = (JSONArray)a.get("Parameters");
                    for (int k = 0; k < parameters.size(); ++k)
                    {
                        JSONObject p = (JSONObject)parameters.get(k);
                        String parameterName = (String)p.get("Name");
                        String parameterType = (String)p.get("Type");
                        this.addParameter(className, attributeName, parameterName, parameterType);
                    }
                }
                else if (fieldOrMethod.equals("Field"))
                {
                    this.addField(className, attributeName, attributeType);
                }
            }
        }
        for (int i = 0; i < relationships.size(); ++i)
        {
            JSONObject r = (JSONObject)relationships.get(i);
            String classOne = (String)r.get("ClassOne");
            String classTwo = (String)r.get("ClassTwo");
            String relationshipType; 
            switch ((String)r.get("RelationshipType"))
            {
                case "AGGREGATION" : relationshipType = "A";
                break;

                case "GENERALIZATION" : relationshipType = "G";
                break;

                case "INHERITANCE" : relationshipType = "I";
                break;

                case "COMPOSITION" : relationshipType = "C";
                break;

                default : relationshipType = "Yikes";
            }
            this.addRelationship(classOne, classTwo, relationshipType);
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
        
        if (classes.size() > 0)
        {
            for (int i = 0; i < classes.size() - 1; ++i)
            {
                sb.append(classes.get(i).toJSONString());
                sb.append(",");
            }
            sb.append(classes.get(classes.size() - 1).toJSONString());
        }

        sb.append("]");
        sb.append(",");
        sb.append("\"" + "Relationships" + "\"");
        sb.append(":");
        sb.append("[");

        if (relationships.size() > 0)
        {
            for (int i = 0; i < relationships.size() - 1; ++i)
            {
                sb.append(relationships.get(i).toJSONString());
                sb.append(",");
            }
            sb.append(relationships.get(relationships.size() - 1).toJSONString());
        }

        sb.append("]");
        sb.append("}");
        return sb.toString();
    }
}