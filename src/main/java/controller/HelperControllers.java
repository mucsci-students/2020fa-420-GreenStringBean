package controller;
import view.*;

public class HelperControllers 
{
	private WorkingProjectEditor project;
	private MenuViews view;
	
	public HelperControllers(MenuViews v) 
	{
		this.project = new WorkingProjectEditor();
		this.view = v;
		project.attach(v);
		addListeners();
	}

	/**********************************CLASSES********************************************/
	public void openClass(String className)
	{
		project.openClass(className);
	}

	public void createClass(String className) 
	{
		System.out.println("made it to createClass() in HellperController");	
		project.addClass(className);
	}

	public void delClass(String className) 
	{
		project.removeClass(className);
	}

	public void renameClass(String oldName, String newName) 
	{
		project.renameClass(oldName, newName);
	}
	/**********************************ATRIBUTES********************************************/
	/***********************************FIELDS**********************************************/
	public void addField(String className, String visability, String name, String type) 
	{
		project.addField(className, name, type, visability);
	}
	
	public void removeField(String className, String name) 
	{
		project.removeField(className, name);
	}

	public void renameField(String className, String name, String newName) 
	{
		project.renameField(className, name, newName);
	}

	/**********************************METHODS*****************************************/
	public void addMethod(String className, String visability, String name, String type)
	{
		project.addMethod(className, name, type, visability);
	}

	public void removeMethod(String className, String name)
	{
		project.removeMethod(className, name);
	}

	public void renameMethod(String className, String name, String newName)
	{
		project.renameMethod(className, name, newName);
	}
    /**********************************PARAMS********************************************/
	public void addParameter(String className, String methodName, String paramName, String paramType)
	{
		project.addParameter(className, methodName, paramName, paramType);
	}

	public void removeParameter(String className, String methodName, String paramName)
	{
		project.removeParameter(className, methodName, paramName);
	}

	public void renameParameter(String className, String methodName, String oldParamName, String newParamName)
	{
		project.renameParameter(className, methodName, oldParamName, newParamName);
	}

	/**********************************VISABILITY********************************************/
	public void changeFieldVisablity( String className, String fieldName, String newFieldVis)
	{
		project.changeFieldVisibility(className, fieldName, newFieldVis);
	}

	public void changeMethodVisablity(String className, String methodName, String newMethVis)
	{
		project.changeMethodVisibility(className, methodName, newMethVis);
	}

	/**********************************RELATIONS********************************************/
	public void addRelationship(String classNameFrom, String classNameTo, String type) 
	{
		project.addRelationship(classNameFrom, classNameTo, type);
	}

	public void removeRelationship(String classNameFrom, String classNameTo)
	{
		project.removeRelationship(classNameFrom, classNameTo);
	}

	public void changeRelationship(String classNameFrom, String classNameTo, String newTypeName)
	{
		project.changeRelationshipType(classNameFrom, classNameTo, newTypeName);
	}

	/**********************************OTHERS********************************************/
	public void load(String projectToLoad) 
	{
		// TODO Auto-generated method stub		
	}

	public void save(String projectToSave)
	{

	}

	public void undo()
	{
		project.undo();
	}

	public void redo()
	{
		project.redo();
	}
	
	public void addListeners()
    {
		System.out.println("Made it to addListeners()");
        view.addListeners(new FileButtonClick(view, this), new ClassClick(view, this), new FieldClick(view, this), new RelationshipClick(view, this));
	}
	
	public void checkStatus()
    {
        // If the last command failed
        if(!project.getLastCommandStatus())
        {
            view.alert("Error: " + project.getLastCommandStatusMessage());
        }
    }

}
