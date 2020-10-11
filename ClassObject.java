import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONAware;

public class ClassObject
{   
    private String name;
    private boolean isOpen;
    private ArrayList<Field> fields;
    private ArrayList<Method> methods;

    public ClassObject()
    {
        System.out.println("error, no name");
    }

    public ClassObject(String giveName)
    {
        name = giveName;
        isOpen = false;
        fields = new ArrayList<>();
        methods = new ArrayList<>();
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

    public void addField(String fieldName, String fieldType)
    {
        if (getFieldIndex(fieldName) == -1)
        {
            fields.add(new Field(fieldName, fieldType));
        }
        else
        {
            System.out.println("error, field already exists");
        }
    }

    public void removeField(String fieldName)
    {
        int index = getFieldIndex(fieldName);
        if(index != -1)
        {
            fields.remove(index);
        }
        else
        {
            System.out.println("error, field doesn't exist");
        }
    }

    public void renameField(String oldName, String newName)
    {
        int oldNameIndex = getFieldIndex(oldName);
        if (oldNameIndex != -1)
        {
            int newNameIndex = getFieldIndex(newName);
            if (newNameIndex == -1)
            {
                fields.get(oldNameIndex).setName(newName);
            }
            else
            {
                System.out.println("error, field with new name already exists");
            }
        }
        else
        {
            System.out.println("error, field doesn't exist");
        }
    }

    public void changeFieldType(String fieldName, String newType)
    {
        int index = getFieldIndex(fieldName);
        if (index != -1)
        {
            Field field = fields.get(index);
            if (!field.getType().equals(newType))
            {
                field.setType(newType);
            }
            else
            {
                System.out.println("error, field already has that type");
            }
        }
        else
        {
            System.out.println("error, field doesn't exist");
        }
    }

    public void addMethod(String methodName, String methodType)
    {
        if (getMethodIndex(methodName) == -1)
        {
            methods.add(new Method(methodName, methodType));
        }
        else
        {
            System.out.println("error, method already exists");
        }
    }

    public void removeMethod(String methodName)
    {
        int index = getMethodIndex(methodName);
        if(index != -1)
        {
            methods.remove(index);
        }
        else
        {
            System.out.println("error, method doesn't exist");
        }
    }

    public void renameMethod(String oldName, String newName)
    {
        int oldNameIndex = getMethodIndex(oldName);
        if (oldNameIndex != -1)
        {
            int newNameIndex = getMethodIndex(newName);
            if (newNameIndex == -1)
            {
                methods.get(oldNameIndex).setName(newName);
            }
            else
            {
                System.out.println("error, method with new name already exists");
            }
        }
        else
        {
            System.out.println("error, method doesn't exist");
        }
    }

    public void changeMethodType(String methodName, String newType)
    {
        int index = getMethodIndex(methodName);
        if (index != -1)
        {
            Method method = methods.get(index);
            if (!method.getType().equals(newType))
            {
                method.setType(newType);
            }
            else
            {
                System.out.println("error, method already has that type");
            }
        }
        else
        {
            System.out.println("error, method doesn't exist");
        }
    }

    public void addParameter(String methodName, String paramName, String paramType)
    {
        int index = getMethodIndex(methodName);
        if (index != -1)
        {
            methods.get(index).addParameter(paramName, paramType);
        }
        else
        {
            System.out.println("error, method doesn't exist");
        }
    }

    public void removeParameter(String methodName, String paramName)
    {
        int index = getMethodIndex(methodName);
        if (index != -1)
        {
            //parameters.get(index).removeParameter(paramName);
            methods.get(index).removeParameter(paramName);
        }
        else
        {
            System.out.println("error, method doesn't exist");
        }
    }

    public void renameParameter(String methodName, String oldParamName, String newParamName)
    {
        int index = getMethodIndex(methodName);
        if (index != -1)
        {
            //parameters.get(index).renameParameter(oldParamName, newParamName);
            methods.get(index).renameParameter(oldParamName, newParamName);
        }
        else
        {
            System.out.println("error, method doesn't exist");
        }
    }

    public void changeParameterType(String methodName, String paramName, String newParamType)
    {
        int index = getMethodIndex(methodName);
        if (index != -1)
        {
            //parameters.get(index).changeParameterType(paramName, newParamType);
            methods.get(index).changeParameterType(paramName, newParamType);
        }
        else
        {
            System.out.println("error, method doesn't exist");
        }
    }

    /*
    public boolean hasAttribute(String attrName)
    {
        for (Attribute attr : attributes)
        {
            if(attr.getName().equals(attrName))
                return true;
        }
        return false;
    }
    */

    public void printFields()
    {
        for (Field field : fields)
        {
            System.out.println(field.getType() + " " + field.getName());
        }
    }

    public void printMethods()
    {
        for (Method method : methods)
        {
            System.out.print(method.getType() + " " + method.getName() + "(");
            method.printParameters();
            System.out.println(")");
        }
    }

    private int getFieldIndex(String fieldName)
    {
        for(int i = 0; i < fields.size(); i++)
        {
            if(fields.get(i).getName().equals(fieldName))
                return i;
        }
        return -1;
    }

    private int getMethodIndex(String methodName)
    {
        for(int i = 0; i < methods.size(); i++)
        {
            if(methods.get(i).getName().equals(methodName))
                return i;
        }
        return -1;
    }

    public String toJSONString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"" + "Name" + "\"");
        sb.append(":");
        sb.append("\"" + this.getName() +  "\"");
        sb.append(",");
        sb.append("\"" + "Attributes" + "\"");
        sb.append(":");
        sb.append("[");
        if (fields.size() > 0)
        {
            for (int i = 0; i < fields.size() - 1; ++i)
            {
                sb.append(fields.get(i).toJSONString());
                sb.append(",");
            }
            sb.append(fields.get(fields.size() - 1).toJSONString());
        }

        if (fields.size() > 0 && methods.size() > 0)
            sb.append(",");
            
        if (methods.size() > 0)
        {
            for (int i = 0; i < methods.size() -1; ++i)
            {
                sb.append(methods.get(i).toJSONString());
                sb.append(",");
            }
            sb.append(methods.get(methods.size() - 1).toJSONString());
        }
        sb.append("]");
        sb.append("}");
        return sb.toString();
    }
}
