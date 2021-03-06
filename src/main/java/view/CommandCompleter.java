package view;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.jline.reader.Completer;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.builtins.Completers.FileNameCompleter;
import org.jline.reader.impl.completer.AggregateCompleter;
import org.jline.reader.impl.completer.ArgumentCompleter;
import org.jline.reader.impl.completer.NullCompleter;

import model.ClassObject;
import model.Model;


public class CommandCompleter
{
    private Model model;
    private AggregateCompleter completer;
    private StringsCompleter basicAttributeTypeCompleter;
    private AggregateCompleter attributeTypeCompleter;
    private StringsCompleter basicReturnTypeCompleter;
    private AggregateCompleter returnTypeCompleter;
    private StringsCompleter visibilityCompleter;
    private StringsCompleter relationshipTypeCompleter;
    private StringsCompleter classCompleter;
    

    /**
     * Initialize the CommandCompleter object
     */
    public CommandCompleter()
    {
        //completer = new AggregateCompleter(quitCompleter(), helpCompleter(), addClassCompleter(), saveCompleter(), loadCompleter());
        completer = new AggregateCompleter();
        basicAttributeTypeCompleter = new StringsCompleter("byte", "short", "int", "long", "float", "double", "boolean", "char");
        attributeTypeCompleter = new AggregateCompleter(basicAttributeTypeCompleter);
        basicReturnTypeCompleter = new StringsCompleter("byte", "short", "int", "long", "float", "double", "boolean", "char", "void");
        returnTypeCompleter = new AggregateCompleter(basicReturnTypeCompleter);
        visibilityCompleter = new StringsCompleter("public", "private", "protected");
        relationshipTypeCompleter = new StringsCompleter("AGGREGATE", "COMPOSITION", "INHERITANCE", "REALIZATION");
        classCompleter = new StringsCompleter();
    }

    /**
     * 
     * @return the current completer used for tab-completion
     */
    public AggregateCompleter getCompleter()
    {
        return completer;
    }

    /**
     * Aggregate a single completer encompassing the completers for every command
     * @param classes All class names in the project
     * @return An AggregateCompleter made of the completer for every command
     */
    public void updateCompleter(Model model)
    {
        this.model = model;
        this.classCompleter = new StringsCompleter(model.getClassNames());
        attributeTypeCompleter = new AggregateCompleter(basicAttributeTypeCompleter, classCompleter);
        returnTypeCompleter = new AggregateCompleter(basicReturnTypeCompleter, classCompleter);

        /**
         * Each command that takes an existing attribute/parameter requires a specific completer for every class.
         * Each ArrayList corresponds to a command that takes a class attribute/method parameter.
         * This way, class attributes and method parameters can be completed with tab-completion for a specific class/method.
        **/
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

        //Iterate through each class, adding corresponding completers to the respective Collection
        //Iterate through methods as necessary
        for (String UMLClass : model.getClassNames())
        {
            for (String UMLMethod : model.getClass(UMLClass).getMethodNames())
            {
                remParamComp.add(removeParameterCompleter(model.getClass(UMLClass), UMLMethod));
                renameParamComp.add(renameParameterCompleter(model.getClass(UMLClass), UMLMethod));
                changeParamTypeComp.add(changeParameterTypeCompleter(model.getClass(UMLClass), UMLMethod));
            }
            addParamComp.add(addParameterCompleter(model.getClass(UMLClass)));
            remFieldComp.add(removeFieldCompleter(model.getClass(UMLClass)));
            remMethComp.add(removeMethodCompleter(model.getClass(UMLClass)));
            renameFieldComp.add(renameFieldCompleter(model.getClass(UMLClass)));
            renameMethComp.add(renameMethodCompleter(model.getClass(UMLClass)));
            changeFieldVisComp.add(changeFieldVisibilityCompleter(model.getClass(UMLClass)));
            changeMethVisComp.add(changeMethodVisibilityCompleter(model.getClass(UMLClass)));
            changeFieldTypeComp.add(changeFieldTypeCompleter(model.getClass(UMLClass)));
            changeMethTypeComp.add(changeMethodTypeCompleter(model.getClass(UMLClass)));
        }

        //Aggregate all of the completers into a single AggregateCompleter that can be used for tab-completion
        this.completer = new AggregateCompleter(quitCompleter(), saveCompleter(), loadCompleter(), helpCompleter(), addClassCompleter(),
            addFieldCompleter(), addMethodCompleter(), new AggregateCompleter(addParamComp), addRelationshipCompleter(), removeClassCompleter(),
            new AggregateCompleter(remFieldComp), new AggregateCompleter(remMethComp), new AggregateCompleter(remParamComp), removeRelationshipCompleter(),
            renameClassCompleter(), new AggregateCompleter(renameFieldComp), new AggregateCompleter(renameMethComp), 
            new AggregateCompleter(renameParamComp), new AggregateCompleter(changeFieldTypeComp), new AggregateCompleter(changeFieldVisComp), 
            new AggregateCompleter(changeMethTypeComp), new AggregateCompleter(changeMethVisComp), new AggregateCompleter(changeParamTypeComp), 
            changeRelationshipTypeCompleter(), printClassCompleter(), printClassesCompleter(), printRelationshipsCompleter(), launchGUICompleter(),
            clearModelCompleter());
    }

    /*
    public void updateCompleter(ClassObject UMLClass)
    {
        ArrayList<Completer> remParamComp = new ArrayList<Completer>();
        ArrayList<Completer> renameParamComp = new ArrayList<Completer>();
        ArrayList<Completer> changeParamTypeComp = new ArrayList<Completer>();

        //Problem is candidates aren't removed when an attribute is removed
        for (String UMLMethod : UMLClass.getMethodNames())
        {
            remParamComp.add(removeParameterCompleter(UMLClass, UMLMethod));
            renameParamComp.add(renameParameterCompleter(UMLClass, UMLMethod));
            changeParamTypeComp.add(changeParameterTypeCompleter(UMLClass, UMLMethod));
        }
        completer = new AggregateCompleter(completer, addParameterCompleter(UMLClass), removeFieldCompleter(UMLClass), removeMethodCompleter(UMLClass), 
            renameFieldCompleter(UMLClass),  renameMethodCompleter(UMLClass), changeFieldVisibilityCompleter(UMLClass), changeMethodVisibilityCompleter(UMLClass),
            changeFieldTypeCompleter(UMLClass), changeMethodTypeCompleter(UMLClass), new AggregateCompleter(remParamComp), new AggregateCompleter(renameParamComp),
            new AggregateCompleter(changeParamTypeComp), launchGUICompleter(), clearModelCompleter());
    }
    */

    /**
     * @return A new Completer for the quit command
     */
    private ArgumentCompleter quitCompleter()
    {
        return new ArgumentCompleter (new StringsCompleter("quit"), new NullCompleter());
    }
    /**
     * @return A new Completer for the save command
     */
    private ArgumentCompleter saveCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("save"), new NullCompleter());
    }
    /**
     * @return A new Complater for the load Command
     */
    private ArgumentCompleter loadCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("load"), new FileNameCompleter(), new NullCompleter());
    }
    /**
     * @return A new Completer for the help command
     */
    private ArgumentCompleter helpCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("help"), new NullCompleter());
    }

    /**
     * @return A new Completer for the addClass command
     */
    private ArgumentCompleter addClassCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("addClass"), new NullCompleter());
    } 
    /**
     * @return A new Completer for the addField command
     */
    private ArgumentCompleter addFieldCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("addField"), classCompleter, 
            visibilityCompleter, attributeTypeCompleter, new NullCompleter());
    }
    /**
     * @return A new Completer for the addMethod command
     */
    private ArgumentCompleter addMethodCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("addMethod"), classCompleter, 
            visibilityCompleter, returnTypeCompleter, new NullCompleter());
    }
    /**
     * @param UMLClass The class whose methods will be completed 
     * @return A new Completer for the addParameter command
     */
    private ArgumentCompleter addParameterCompleter(ClassObject UMLClass)
    {
        return new ArgumentCompleter(new StringsCompleter("addParameter"), new StringsCompleter(UMLClass.getName()), 
            new StringsCompleter(UMLClass.getMethodNames()), attributeTypeCompleter, new NullCompleter());
    }
    /**
     * @return A new Completer for the addRelationship command
     */
    private ArgumentCompleter addRelationshipCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("addRelationship"), classCompleter, classCompleter, 
            relationshipTypeCompleter, new NullCompleter());
    }

    /**
     * @return A new Completer for the removeClass command
     */
    private ArgumentCompleter removeClassCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("removeClass"), classCompleter, new NullCompleter());
    }
    /**
     * @param UMLClass The class whose fields will be completed
     * @return A new Completer for the removeField command
     */
    private ArgumentCompleter removeFieldCompleter(ClassObject UMLClass)
    {
        return new ArgumentCompleter(new StringsCompleter("removeField"), new StringsCompleter(UMLClass.getName()), 
            new StringsCompleter(UMLClass.getFieldNames()), new NullCompleter());
    }
    /**
     * @param UMLClass The class whose methods will be completed
     * @return A new Completer for the removeMethod command
     */
    private ArgumentCompleter removeMethodCompleter(ClassObject UMLClass)
    {
        return new ArgumentCompleter(new StringsCompleter("removeMethod"), new StringsCompleter(UMLClass.getName()), 
            new StringsCompleter(UMLClass.getMethodNames()), new NullCompleter());
    }
    /**
     * @param UMLClass The class whose methods will be completed
     * @param UMLMethod The methods whose parameters will be completed
     * @return A new Completer for the removeParameter command
     */
    private ArgumentCompleter removeParameterCompleter(ClassObject UMLClass, String UMLMethod)
    {
        return new ArgumentCompleter(new StringsCompleter("removeParameter"), new StringsCompleter(UMLClass.getName()), 
            new StringsCompleter(UMLMethod), new StringsCompleter(UMLClass.getMethod(UMLMethod).getParameterNames()), 
            new NullCompleter());
    }
    
    /**
     * @return A new Completer for the removeRelationship command
     */
    private ArgumentCompleter removeRelationshipCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("removeRelationship"), classCompleter, classCompleter);
    }
    /**
     * @return A new Completer for the renameClass command
     */
    private ArgumentCompleter renameClassCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("renameClass"), classCompleter, new NullCompleter());
    }
    /**
     * @param UMLClass The class whose fields will be completed
     * @return A new Completer for the renameField command
     */
    private ArgumentCompleter renameFieldCompleter(ClassObject UMLClass)
    {
        return new ArgumentCompleter(new StringsCompleter("renameField"), new StringsCompleter(UMLClass.getName()), 
            new StringsCompleter(UMLClass.getFieldNames()), new NullCompleter());
    }
    /**
     * @param UMLClass The class whose methods will be completed
     * @return A new Completer for the renameMethod command
     */
    private ArgumentCompleter renameMethodCompleter(ClassObject UMLClass)
    {
        return new ArgumentCompleter(new StringsCompleter("renameMethod"), new StringsCompleter(UMLClass.getName()), 
            new StringsCompleter(UMLClass.getMethodNames()), new NullCompleter());
    }
    /**
     * @param UMLClass The class whose methods will be completed
     * @param UMLMethod The method whose parameters will be completed
     * @return A new Completer for the renameParameter command
     */
    private ArgumentCompleter renameParameterCompleter(ClassObject UMLClass, String UMLMethod)
    {
        return new ArgumentCompleter(new StringsCompleter("renameParameter"), new StringsCompleter(UMLClass.getName()), 
            new StringsCompleter(UMLMethod), new StringsCompleter(UMLClass.getMethod(UMLMethod).getParameterNames()), 
            new NullCompleter());
    }

    /**
     * @param UMLClass The class whose fields will be completed
     * @return A new Completer for the changeFieldVisibility command
     */
    private ArgumentCompleter changeFieldVisibilityCompleter(ClassObject UMLClass)
    {
        return new ArgumentCompleter(new StringsCompleter("changeFieldVisibility"), new StringsCompleter(UMLClass.getName()), 
            new StringsCompleter(UMLClass.getFieldNames()), visibilityCompleter, new NullCompleter());
    }
    /**
     * @param UMLClass The class whose methods will be completed
     * @return A new Completer for the changeMethodVisibility command
     */
    private ArgumentCompleter changeMethodVisibilityCompleter(ClassObject UMLClass)
    {
        return new ArgumentCompleter(new StringsCompleter("changeMethodVisibility"), new StringsCompleter(UMLClass.getName()), 
            new StringsCompleter(UMLClass.getMethodNames()), visibilityCompleter, new NullCompleter());
    }

    /**
     * @param UMLClass The class whose fields will be completed
     * @return A new Completer for the changeFieldType command
     */
    private ArgumentCompleter changeFieldTypeCompleter(ClassObject UMLClass)
    {
        return new ArgumentCompleter(new StringsCompleter("changeFieldType"), new StringsCompleter(UMLClass.getName()), 
            new StringsCompleter(UMLClass.getFieldNames()), attributeTypeCompleter, new NullCompleter());
    }
    /**
     * @param UMLClass The class whose methods will be completed
     * @return A new Completer for the changeMethodType command
     */
    private ArgumentCompleter changeMethodTypeCompleter(ClassObject UMLClass)
    {
        return new ArgumentCompleter(new StringsCompleter("changeMethodType"), new StringsCompleter(UMLClass.getName()), 
            new StringsCompleter(UMLClass.getMethodNames()), returnTypeCompleter, new NullCompleter());
    }
    /**
     * @param UMLClass The class whose methods will be completed
     * @param UMLMethod The method whose parameters will be completed
     * @return A new Completer for the changeParameterType command
     */
    private ArgumentCompleter changeParameterTypeCompleter(ClassObject UMLClass, String UMLMethod)
    {
        return new ArgumentCompleter(new StringsCompleter("changeParameterType"), new StringsCompleter(UMLClass.getName()), new StringsCompleter(UMLMethod), 
            new StringsCompleter(UMLClass.getMethod(UMLMethod).getParameterNames()), attributeTypeCompleter, new NullCompleter());
    }
    /**
     * @return A new Completer for the changeRelationshipType command
     */
    private ArgumentCompleter changeRelationshipTypeCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("changeRelationshipType"), classCompleter, classCompleter, 
            relationshipTypeCompleter, new NullCompleter());
    }

    /**
     * @return A new Completer for the printClass command
     */
    private ArgumentCompleter printClassCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("printClass"), classCompleter, new NullCompleter());
    }

    /**
     * @return A new Completer for the printClasses command
     */
    private ArgumentCompleter printClassesCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("printClasses"), new NullCompleter());
    }

    /**
     * @return A new Completer for the printRelationships command
     */
    private ArgumentCompleter printRelationshipsCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("printRelationships"), new NullCompleter());
    }

    /**
     * @return A new Completer for the launchGUI command
     */
    private ArgumentCompleter launchGUICompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("launchGUI"), new NullCompleter());
    }

    /**
     * @return A new Completer for the clearModel command
     */
    private ArgumentCompleter clearModelCompleter()
    {
        return new ArgumentCompleter(new StringsCompleter("clearModel"), new NullCompleter());
    }
}