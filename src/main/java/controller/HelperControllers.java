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
		checkStatus();
	}

	public void closeClass(String className)
	{
		project.closeClass(className);
		checkStatus();
	}

	public void createClass(String className) 
	{
		project.addClass(className);
		checkStatus();
	}

	public void delClass(String className) 
	{
		project.removeClass(className);
		checkStatus();
	}

	public void renameClass(String oldName, String newName) 
	{
		project.renameClass(oldName, newName);
		checkStatus();
	}
	/**********************************ATRIBUTES********************************************/
	/***********************************FIELDS**********************************************/
	public void addField(String className, String fieldName, String fieldType, String fieldVis) 
	{
		project.addField(className, fieldName, fieldType, fieldVis);
		checkStatus();
	}
	
	public void removeField(String className, String name) 
	{
		project.removeField(className, name);
		checkStatus();
	}

	public void renameField(String className, String name, String newName) 
	{
		project.renameField(className, name, newName);
		checkStatus();
	}

	/**********************************METHODS*****************************************/
	public void addMethod(String className, String methodName, String methodType, String methodVis)
	{
		project.addMethod(className, methodName, methodType, methodVis);
		checkStatus();
	}

	public void removeMethod(String className, String name)
	{
		project.removeMethod(className, name);
		checkStatus();
	}

	public void renameMethod(String className, String name, String newName)
	{
		project.renameMethod(className, name, newName);
		checkStatus();
	}
    /**********************************PARAMS********************************************/
	public void addParameter(String className, String methodName, String paramName, String paramType)
	{
		project.addParameter(className, methodName, paramName, paramType);
		checkStatus();
	}

	public void removeParameter(String className, String methodName, String paramName)
	{
		project.removeParameter(className, methodName, paramName);
		checkStatus();
	}

	public void renameParameter(String className, String methodName, String oldParamName, String newParamName)
	{
		project.renameParameter(className, methodName, oldParamName, newParamName);
		checkStatus();
	}

	/**********************************DATA TYPE********************************************/
	public void changeFieldType(String className, String fieldName, String newFieldType)
	{
		project.changeFieldType(className, fieldName, newFieldType);
		checkStatus();
	}

	public void changeMethodType(String className, String methodName, String newMethodType)
	{
		project.changeMethodType(className, methodName, newMethodType);
		checkStatus();
	}

	public void changeParameterType(String className, String methodName, String paramName, String newParamType)
	{
		project.changeParameterType(className, methodName, paramName, newParamType);
		checkStatus();
	}

	/**********************************VISIBILITY********************************************/
	public void changeFieldVisiblity( String className, String fieldName, String newFieldVis)
	{
		project.changeFieldVisibility(className, fieldName, newFieldVis);
		checkStatus();
	}

	public void changeMethodVisiblity(String className, String methodName, String newMethVis)
	{
		project.changeMethodVisibility(className, methodName, newMethVis);
		checkStatus();
	}

	/**********************************RELATIONS********************************************/
	public void addRelationship(String classNameFrom, String classNameTo, String type) 
	{
		project.addRelationship(classNameFrom, classNameTo, type);
		checkStatus();
	}

	public void removeRelationship(String classNameFrom, String classNameTo)
	{
		project.removeRelationship(classNameFrom, classNameTo);
		checkStatus();
	}

	public void changeRelationshipType(String classNameFrom, String classNameTo, String newTypeName)
	{
		project.changeRelationshipType(classNameFrom, classNameTo, newTypeName);
		checkStatus();
	}

	// Temporary until we can display arrows between class boxes
	public void showRelationships()
	{
		view.showRelationships(project.getProjectSnapshot());
	}

	/**********************************OTHERS********************************************/
	public void load(String projectJsonString) 
	{
		project.loadProject(projectJsonString);		
		checkStatus();
	}

	public String save()
	{
		return project.toJSONString();
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
