import java.util.ArrayList;

public class Method extends FormalDeclaration {
    private ArrayList<Parameter> parameters;

    public Method(String name, String type)
    {
        super(name, type);
        parameters = new ArrayList<>();
    }

    public void addParameter(String paramName, String paramType)
    {
        if (getParamIndex(paramName) == -1)
        {
            parameters.add(new Parameter(paramName, paramType));
        }
        else
        {
            System.out.println("error, parameter already exists");
        }
    }

    public void removeParameter(String paramName)
    {
        int index = getParamIndex(paramName);
        if (index != -1)
        {
            parameters.remove(index);
        }
        else
        {
            System.out.println("error, parameter doesn't exist");
        }
    }

    public void renameParameter(String oldParamName, String newParamName)
    {
        int oldIndex = getParamIndex(oldParamName);
        if (oldIndex != -1)
        {
            int newIndex = getParamIndex(newParamName);
            if (newIndex == -1)
            {
                parameters.get(oldIndex).setName(newParamName);
            }
            else
            {
                System.out.println("error, parameter with new name already exists");
            }
        }
        else
        {
            System.out.println("error, parameter doesn't exist");
        }
    }

    public void changeParameterType(String paramName, String newType)
    {
        int index = getParamIndex(paramName);
        if (index != -1)
        {
            Parameter param = parameters.get(index);
            if (!param.getType().equals(newType))
            {
                param.setType(newType);
            }
            else
            {
                System.out.println("error, parameter already has that type");
            }
        }
        else
        {
            System.out.println("error, parameter doesn't exist");
        }
    }

    public void printParameters()
    {
        if (!parameters.isEmpty())
        {
            Parameter currentParam = parameters.get(0);
            System.out.print(" " + currentParam.getType() + " " + currentParam.getName());
            for (int i = 1; i < parameters.size(); ++i)
            {
                currentParam = parameters.get(i);
                System.out.print(", " + currentParam.getType() + " " + currentParam.getName());
            }
            System.out.print(" ");
        }
    }

    private int getParamIndex(String paramName)
    {
        for(int i = 0; i < parameters.size(); i++)
        {
            if(parameters.get(i).getName().equals(paramName))
                return i;
        }
        return -1;
    }

    public String toJSONString ()
   {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"" + "Name" + "\"");
        sb.append(":");
        sb.append("\"" + this.getName() + "\"");
        sb.append(",");
        sb.append("\"" + "Type" + "\"");
        sb.append(":");
        sb.append("\"" + this.getType() + "\"");
        sb.append(",");
        sb.append("\"" + "FieldOrMethod" + "\"");
        sb.append(":");
        sb.append("\"" + "Method" + "\"");
        sb.append(",");
        sb.append("\"" + "Parameters" + "\"");
        sb.append(":");
        sb.append("[");

        if (parameters.size() > 0)
        {
            for (int i = 0; i < parameters.size() - 1; ++i)
            {
                sb.append(parameters.get(i).toJSONString());
                sb.append(",");
            }
            sb.append((parameters.get(parameters.size() - 1)).toJSONString());
        }

        sb.append("]");
        sb.append("}");
        return sb.toString();
   }
}
