import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Method extends FormalDeclaration {
    private ArrayList<Parameter> parameters;

    // Create a new Method with a name and return type
    public Method(String name, String type)
    {
        super(name, type);
        parameters = new ArrayList<>();
    }

    // Add a new Parameter called paramName with data type paramType
    // Print an error if there is aleady a Parameter called paramName in the method
    public void addParameter(String paramName, String paramType)
    {
        if (getParamIndex(paramName) != -1)
        {
            System.out.println("Error: Name is already in use");
            return;
        }

        parameters.add(new Parameter(paramName, paramType));
        System.out.println("Successfully added");
    }

    // Remove the Parameter called paramName from the method
    // Print an error if there is no Parameter with that name in the method
    public void removeParameter(String paramName)
    {
        int index = getParamIndex(paramName);
        if (index == -1)
        {
            System.out.println("Error: Parameter does not exist");
            return;
        }

        parameters.remove(index);
        System.out.println("Successfully removed");
    }

    // Rename the Parameter called oldParamName to newParamName
    // Print an error if there is no Parameter called oldParamName in the method
    // Print an error if there is already a Parameter called newParamName in the method
    public void renameParameter(String oldParamName, String newParamName)
    {
        int oldIndex = getParamIndex(oldParamName);
        if (oldIndex == -1)
        {
            System.out.println("Error: Parameter does not exist");
            return;
        }
        
        int newIndex = getParamIndex(newParamName);
        if (newIndex != -1)
        {
            System.out.println("Error: Name is already in use");
            return;
        }

        parameters.get(oldIndex).setName(newParamName);
        System.out.println("Successfully renamed");
    }

    // Change the data type of the Parameter called paramName to newType
    // Print an error if there is no Parameter with that name in the method
    public void changeParameterType(String paramName, String newType)
    {
        int index = getParamIndex(paramName);
        if (index == -1)
        {
            System.out.println("Error: Parameter does not exist");
            return;
        }

        parameters.get(index).setType(newType);
        System.out.println("Successfully changed type");
    }

    // Print the list of Parameters, separated by commas and flanked with spaces
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

    // Returns the index of the Parameter called paramName
    // Returns -1 if there is no Parameter with that name in the list
    private int getParamIndex(String paramName)
    {
        for(int i = 0; i < parameters.size(); i++)
        {
            if(parameters.get(i).getName().equals(paramName))
                return i;
        }
        return -1;
    }

   public JSONObject toJSON()
   {
       JSONObject jsonMethod = new JSONObject();

       JSONArray jsonParameters = new JSONArray();
       for (Parameter parameter : parameters)
       {
            jsonParameters.add(parameter.toJSON());
       }

        jsonMethod.put("name", getName());
        jsonMethod.put("type", getType());
        jsonMethod.put("parameters", jsonParameters);

        return jsonMethod;       
   }

   public static Method loadFromJSON(JSONObject jsonMethod)
   {
       String name = (String)jsonMethod.get("name");
       String type = (String)jsonMethod.get("type");

       Method method = new Method(name, type);

       JSONArray jsonParameters = (JSONArray)jsonMethod.get("parameters");

       for (Object jsonParameter : jsonParameters)
       {
           String paramName = (String)((JSONObject)jsonParameter).get("name");
           String paramType = (String)((JSONObject)jsonParameter).get("type");
           method.parameters.add(new Parameter(paramName, paramType));
       }

       return method;
   }
}
