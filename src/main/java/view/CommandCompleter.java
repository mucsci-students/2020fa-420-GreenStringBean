package view;

import java.util.ArrayList;
import org.jline.reader.Completer;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.builtins.Completers.FileNameCompleter;
import org.jline.reader.impl.completer.AggregateCompleter;
import org.jline.reader.impl.completer.ArgumentCompleter;
import org.jline.reader.impl.completer.NullCompleter;

import model.Model;


public class CommandCompleter
{
    //Need to get all class names
    //Need to get all field names
    //Need to get all method names
    //Need to get all parameter names
    //Idea: This might be done with a collection of Completers; one for every class
    //Find the Completer that corresponds to the previous argument, and then use that to make the completion for the current argument
    //There needs to be a strings completer for every class' fields and methods, and each method needs a string completer for its parameters

    private Model model;
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
        relationshipTypeCompleter = new StringsCompleter("AGREGATE", "COMPOSITION", "INHERITANCE", "REALIZATION");
        classCompleter = new StringsCompleter();
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
    public void updateCompleter(Model model)
    {
        this.model = model;
        this.classCompleter = new StringsCompleter(model.getClassNames());

        ArrayList<Completer> addParamComp = new ArrayList<Completer>();
        ArrayList<Completer> remFieldComp = new ArrayList<Completer>();
        ArrayList<Completer> remMethComp = new ArrayList<Completer>();
        ArrayList<Completer> remParamComp = new ArrayList<Completer>();
        ArrayList<Completer> renameFieldComp = new ArrayList<Completer>();
        ArrayList<Completer> renameMethComp = new ArrayList<Completer>();
        ArrayList<Completer> renameParamComp = new ArrayList<Completer>();
        ArrayList<Completer> changeFieldVisComp = new ArrayList<Completer>();
        ArrayList<Completer> changeMethVisComp = new ArrayList<Completer>();
        ArrayList<Completer> changeFieldTypeComp = new ArrayList<Completer>();
        ArrayList<Completer> changeMethTypeComp = new ArrayList<Completer>();
        ArrayList<Completer> changeParamTypeComp = new ArrayList<Completer>();
        for (String UMLClass : model.getClassNames())
        {
            for (String UMLMethod : model.getClass(UMLClass).getMethodNames())
            {
                addParamComp.add(addParameterCompleter(UMLClass, UMLMethod));
                remParamComp.add(removeParameterCompleter(UMLClass, UMLMethod));
                renameParamComp.add(renameParameterCompleter(UMLClass, UMLMethod));
                changeParamTypeComp.add(changeParameterTypeCompleter(UMLClass, UMLMethod));
            }
            remFieldComp.add(removeFieldCompleter(UMLClass));
            remMethComp.add(removeMethodCompleter(UMLClass));
            renameFieldComp.add(renameFieldCompleter(UMLClass));
            renameMethComp.add(renameMethodCompleter(UMLClass));
            changeFieldVisComp.add(changeFieldVisibilityCompleter(UMLClass));
            changeMethVisComp.add(changeMethodVisibilityCompleter(UMLClass));
            changeFieldTypeComp.add(changeFieldTypeCompleter(UMLClass));
            changeMethTypeComp.add(changeMethodTypeCompleter(UMLClass));
        }


        this.completer = new AggregateCompleter(quitCompleter(), saveCompleter(), loadCompleter(), helpCompleter(), addClassCompleter(),
            addFieldCompleter(), addMethodCompleter(), new AggregateCompleter(addParamComp), addRelationshipCompleter(), removeClassCompleter(),
            new AggregateCompleter(remFieldComp), new AggregateCompleter(remMethComp), new AggregateCompleter(remParamComp), removeRelationshipCompleter(),
            renameClassCompleter(), new AggregateCompleter(renameFieldComp), new AggregateCompleter(renameMethComp), 
            new AggregateCompleter(renameParamComp), new AggregateCompleter(changeFieldTypeComp), new AggregateCompleter(changeFieldVisComp), 
            new AggregateCompleter(changeMethTypeComp), new AggregateCompleter(changeMethVisComp), new AggregateCompleter(changeParamTypeComp), 
            changeRelationshipTypeCompleter());
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
            visibilityCompleter, typeCompleter, new NullCompleter());
    }
    private ArgumentCompleter addMethodCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("addMethod"), classCompleter, 
            visibilityCompleter, typeCompleter, new NullCompleter());
    }
    private ArgumentCompleter addParameterCompleter(String UMLClass, String UMLMethod)
    {
        return new ArgumentCompleter(new StringsCompleter("addParameter"), new StringsCompleter(UMLClass), 
            new StringsCompleter(UMLMethod), typeCompleter, new NullCompleter());
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
    private ArgumentCompleter removeFieldCompleter(String UMLClass)
    {
        return new ArgumentCompleter(new StringsCompleter("removeField"), new StringsCompleter(UMLClass), 
            new StringsCompleter(model.getClass(UMLClass).getFieldNames()), new NullCompleter());
    }
    private ArgumentCompleter removeMethodCompleter(String UMLClass)
    {
        return new ArgumentCompleter(new StringsCompleter("removeMethod"), new StringsCompleter(UMLClass), 
            new StringsCompleter(model.getClass(UMLClass).getMethodNames()), new NullCompleter());
    }
    private ArgumentCompleter removeParameterCompleter(String UMLClass, String UMLMethod)
    {
        return new ArgumentCompleter(new StringsCompleter("removeParameter"), new StringsCompleter(UMLClass), 
            new StringsCompleter(UMLMethod), new StringsCompleter(model.getClass(UMLClass).getMethod(UMLMethod).getParameterNames()),
            new NullCompleter());
    }
    
    private ArgumentCompleter removeRelationshipCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("removeRelationship"), classCompleter, classCompleter);
    }
    private ArgumentCompleter renameClassCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("renameClass"), classCompleter, new NullCompleter());
    }
    private ArgumentCompleter renameFieldCompleter(String UMLClass)
    {
        return new ArgumentCompleter(new StringsCompleter("renameField"), new StringsCompleter(UMLClass), 
            new StringsCompleter(model.getClass(UMLClass).getFieldNames()), new NullCompleter());
    }
    private ArgumentCompleter renameMethodCompleter(String UMLClass)
    {
        return new ArgumentCompleter(new StringsCompleter("renameMethod"), new StringsCompleter(UMLClass), 
            new StringsCompleter(model.getClass(UMLClass).getMethodNames()), new NullCompleter());
    }
    private ArgumentCompleter renameParameterCompleter(String UMLClass, String UMLMethod)
    {
        return new ArgumentCompleter(new StringsCompleter("renameParameter"), new StringsCompleter(UMLClass), 
            new StringsCompleter(UMLMethod), new StringsCompleter(model.getClass(UMLClass).getMethod(UMLMethod).getParameterNames()), 
            new NullCompleter());
    }

    private ArgumentCompleter changeFieldVisibilityCompleter(String UMLClass)
    {
        return new ArgumentCompleter(new StringsCompleter("changeFieldVisibility"), new StringsCompleter(UMLClass), 
            new StringsCompleter(model.getClass(UMLClass).getFieldNames()), visibilityCompleter, new NullCompleter());
    }
    private ArgumentCompleter changeMethodVisibilityCompleter(String UMLClass)
    {
        return new ArgumentCompleter(new StringsCompleter("changeMethodVisibility"), new StringsCompleter(UMLClass), 
            new StringsCompleter(model.getClass(UMLClass).getMethodNames()), visibilityCompleter, new NullCompleter());
    }

    private ArgumentCompleter changeFieldTypeCompleter(String UMLClass)
    {
        return new ArgumentCompleter(new StringsCompleter("changeFieldType"), new StringsCompleter(UMLClass), 
            new StringsCompleter(model.getClass(UMLClass).getFieldNames()), typeCompleter, new NullCompleter());
    }
    private ArgumentCompleter changeMethodTypeCompleter(String UMLClass)
    {
        return new ArgumentCompleter(new StringsCompleter("changeMethodType"), new StringsCompleter(UMLClass), 
            new StringsCompleter(model.getClass(UMLClass).getMethodNames()), typeCompleter, new NullCompleter());
    }
    private ArgumentCompleter changeParameterTypeCompleter(String UMLClass, String UMLMethod)
    {
        return new ArgumentCompleter(new StringsCompleter("changeParameterType"), new StringsCompleter(UMLClass), new StringsCompleter(UMLMethod), 
            new StringsCompleter(model.getClass(UMLClass).getMethod(UMLMethod).getParameterNames()), typeCompleter, new NullCompleter());
    }
    private ArgumentCompleter changeRelationshipTypeCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("changeRelationshipType"), classCompleter, classCompleter, 
            relationshipTypeCompleter, new NullCompleter());
    }
}