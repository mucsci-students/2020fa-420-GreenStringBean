package view;

import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.builtins.Completers.FileNameCompleter;
import org.jline.reader.impl.completer.AggregateCompleter;
import org.jline.reader.impl.completer.ArgumentCompleter;
import org.jline.reader.impl.completer.NullCompleter;

import model.WorkingProject;
import java.util.Set;

public class CommandCompleter
{
    //Need to get all class names
    //Need to get all field names
    //Need to get all method names
    //Need to get all parameter names
    //Idea: This might be done with a collection of Completers; one for every class
    //Find the Completer that corresponds to the previous argument, and then use that to make the completion for the current argument

    private AggregateCompleter completer;
    private StringsCompleter typeCompleter;
    private StringsCompleter visibilityCompleter;
    private StringsCompleter relationshipTypeCompleter;
    private StringsCompleter classCompleter;
    

    public CommandCompleter()
    {
        //completer = new AggregateCompleter(quitCompleter(), helpCompleter(), addClassCompleter(), saveCompleter(), loadCompleter());
        completer = new AggregateCompleter();
        typeCompleter = new StringsCompleter("byte", "short", "int", "long", "float", "double", "boolean", "char");
        visibilityCompleter = new StringsCompleter("public", "private", "protected");
        relationshipTypeCompleter = new StringsCompleter("-a", "-c", "-i", "-r");
        classCompleter = new StringsCompleter();
    }

    public CommandCompleter(WorkingProject project)
    {

    }

    public AggregateCompleter getCompleter()
    {
        return completer;
    }
    /**
     * Return a single completer encompassing the completers for every command
     * @param classes All class names in the project
     * @return An AggregateCompleter made of the completer for every command
     */
    public void updateCompleter(Set<String> classes)
    {
        this.classCompleter = new StringsCompleter(classes);
        this.completer = new AggregateCompleter(quitCompleter(), saveCompleter(), loadCompleter(), helpCompleter(), addClassCompleter(),
            addFieldCompleter(), addMethodCompleter(), addParameterCompleter(), addRelationshipCompleter(), removeClassCompleter(),
            removeFieldCompleter(), removeMethodCompleter(), removeParameterCompleter(), removeRelationshipCompleter(), renameClassCompleter(),
            renameFieldCompleter(), renameMethodCompleter(), renameParameterCompleter(), changeFieldTypeCompleter(), changeFieldVisibilityCompleter(),
            changeMethodTypeCompleter(), changeMethodVisibilityCompleter(), changeParameterTypeCompleter(), changeRelationshipTypeCompleter());
    }

    
    private ArgumentCompleter quitCompleter()
    {
        return new ArgumentCompleter (new StringsCompleter("quit"), new NullCompleter());
    }
    private ArgumentCompleter saveCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("save"), new NullCompleter());
    }
    private ArgumentCompleter loadCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("load"), new FileNameCompleter(), new NullCompleter());
    }
    private ArgumentCompleter helpCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("help"), new NullCompleter());
    }

    private ArgumentCompleter addClassCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("addClass"), new NullCompleter());
    } 
    private ArgumentCompleter addFieldCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("addField"), classCompleter, 
            visibilityCompleter, typeCompleter, /*field name*/new NullCompleter());
    }
    private ArgumentCompleter addMethodCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("addMethod"), classCompleter, 
            visibilityCompleter, typeCompleter, /*method name*/new NullCompleter());
    }
    //TODO: Find how to make a methodCompleter and add it to this completer
    private ArgumentCompleter addParameterCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("addParameter"), classCompleter, 
        /*methodCompleter*//*status completer*/new NullCompleter());
    }
    private ArgumentCompleter addRelationshipCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("addRelationship"), classCompleter, classCompleter, 
            relationshipTypeCompleter, new NullCompleter());
    }

    private ArgumentCompleter removeClassCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("removeClass"), classCompleter, new NullCompleter());
    }
    //TODO: Find how to make a completer for fields and add it to this completer
    private ArgumentCompleter removeFieldCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("removeField"), classCompleter, 
            /*field*/new NullCompleter());
    }
    //TODO: Find how to make a completer for methods and add it to this completer
    private ArgumentCompleter removeMethodCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("removeMethod"), classCompleter, 
            /*method*/new NullCompleter());
    }
    //TODO: Find how to make a completer for methods and for parameters and add them to this method
    private ArgumentCompleter removeParameterCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("removeParameter"), classCompleter, /*method*/new NullCompleter(),
            /*parameter*/new NullCompleter());
    }
    private ArgumentCompleter removeRelationshipCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("removeRelationship"), classCompleter, classCompleter);
    }
    private ArgumentCompleter renameClassCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("renameClass"), classCompleter, new NullCompleter());
    }
    
    //TODO: Find how to make a completer for fields and add it to this completer
    private ArgumentCompleter renameFieldCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("renameField"), classCompleter, /*field*/new NullCompleter());
    }
    //TODO: Find how to make a completer for methods and add it to this completer
    private ArgumentCompleter renameMethodCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("renameMethod"), classCompleter, /*method*/new NullCompleter());
    }
    //TODO: Find how to make a completer for methods and parameters and add them to this completer
    private ArgumentCompleter renameParameterCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("renameParameter"), classCompleter, /*method*//*parameter*/
            new NullCompleter());
    }

    //TODO: Find how to make a completer for fields and add it to this completer
    private ArgumentCompleter changeFieldVisibilityCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("changeFieldVisibility"), classCompleter, /*field*/new NullCompleter(), visibilityCompleter, new NullCompleter());
    }
    //TODO: Find how to make a completer for methods and add it to this completer
    private ArgumentCompleter changeMethodVisibilityCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("changeMethodVisibility"), classCompleter, /*method*/new NullCompleter(), visibilityCompleter, new NullCompleter());
    }

    //TODO: Find how to make a completer for fields and add it to this completer
    private ArgumentCompleter changeFieldTypeCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("changeFieldType"), classCompleter, /*field*/new NullCompleter(), typeCompleter, new NullCompleter());
    }
    //TODO: Find how to make a completer for methods and add it to this completer
    private ArgumentCompleter changeMethodTypeCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("changeMethodType"), classCompleter, /*method*/new NullCompleter(), typeCompleter, new NullCompleter());
    }
    //TODO: Find how to make a completer for methods and parameters and add them to this completer
    private ArgumentCompleter changeParameterTypeCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("changeParameterType"), classCompleter, /*method*/new NullCompleter(), 
            /*parameter*/new NullCompleter(), typeCompleter, new NullCompleter());
    }
    private ArgumentCompleter changeRelationshipTypeCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("changeRelationshipType"), classCompleter, classCompleter, new StringsCompleter("-a", "-c", "-i", "-r"), new NullCompleter());
    }
}