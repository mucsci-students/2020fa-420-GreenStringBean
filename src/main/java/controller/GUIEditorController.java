package controller;

import java.util.List;

import model.Model;
import view.*;

public class GUIEditorController implements GUIController
{
	private ModelEditor project;
	private GUIView view;
	
	/**
     * Cronstructor for controller helpers that will work with
	 * the WorkingProjectEditor to move data around to its respected parts
     * @param v view of the GUI that is to be updated occordingly 
     */
	public GUIEditorController(GUIView v, ModelEditor m) 
	{
		this.project = m;
		this.view = v;
		project.attach(v);
	}

	/**********************************CLASSES********************************************/
	/**
	 * Opens class for editing in WPEditor
	 * @param className is the name of the class
	 */
	public void openClass(String className)
	{
		project.openClass(className);
		checkStatus();
	}

	/**
	 * Closes class to editing in WPEditor
	 * @param className is the name of the class
	 */
	public void closeClass(String className)
	{
		project.closeClass(className);
		checkStatus();
	}

	/**
	 * Adds a class in WPEditor
	 * @param className is the name of the class
	 */
	public void addClass(String className) 
	{
		project.addClass(className);
		checkStatus();
	}

	/**
	 * Deletes class in WPEditor
	 * @param className is the name of the class
	 */
	public void removeClass(String className) 
	{
		project.removeClass(className);
		checkStatus();
	}

	/**
	 * Renames a class in the WPEditor
	 * @param oldName is the old name of class
	 * @param newName is the new name of class
	 */
	public void renameClass(String oldName, String newName) 
	{
		project.renameClass(oldName, newName);
		checkStatus();
	}
	/**********************************ATRIBUTES********************************************/
	/***********************************FIELDS**********************************************/
	/**
	 * Adds field in WPEditor
	 * @param className is the class name
	 * @param fieldName is the field name
	 * @param fieldType is the field type
	 * @param fieldVis is the visibility
	 */
	public void addField(String className, String fieldName, String fieldType, String fieldVis) 
	{
		project.addField(className, fieldName, fieldType, fieldVis);
		checkStatus();
	}
	
	/**
	 * Deletes field in WPEditor
	 * @param className is the class name
	 * @param name is the field name
	 */
	public void removeField(String className, String name) 
	{
		project.removeField(className, name);
		checkStatus();
	}

	/**
	 * Reanmes a field in WPEditor
	 * @param className is the class name
	 * @param name is the field name 
	 * @param newName is the new field name
	 */
	public void renameField(String className, String name, String newName) 
	{
		project.renameField(className, name, newName);
		checkStatus();
	}

	/**********************************METHODS*****************************************/
	/**
	 * Adds method in WPEditor
	 * @param className is the class name
	 * @param methodName is the method name
	 * @param methodType is the method type
	 * @param methodVis is the visibility
	 */
	public void addMethod(String className, String methodName, String methodType, String methodVis, List<String> paramNames, List<String> paramTypes)
	{
		project.addMethod(className, methodName, methodType, methodVis, paramNames, paramTypes);
		checkStatus();
	}

	/**
	 * Deletes method in WPEditor
	 * @param className is the class name
	 * @param name is the method name
	 */
	public void removeMethod(String className, String name)
	{
		project.removeMethod(className, name);
		checkStatus();
	}

	/**
	 * Renames a method in the WPEditor
	 * @param className is the class name
	 * @param name is the method name
	 * @param newName is the method old name
	 */
	public void renameMethod(String className, String name, String newName)
	{
		project.renameMethod(className, name, newName);
		checkStatus();
	}
	/**********************************PARAMS********************************************/
	
	/**
	 * Edits params in WPEditor
	 * @param className is the class name
	 * @param methodName is the method name
	 * @param paramNames is the param name list
	 * @param paramTypes is the param type list
	 */
	public void changeParameterList(String className, String methodName, List<String> paramNames, List<String> paramTypes)
	{
		project.changeParameterList(className, methodName, paramNames, paramTypes);
		checkStatus();
	}

	/**********************************DATA TYPE********************************************/
	/**
	 * Changes field type in WPEditor
	 * @param className is the class name
	 * @param fieldName is the field name
	 * @param newFieldType is the new field type
	 */
	public void changeFieldType(String className, String fieldName, String newFieldType)
	{
		project.changeFieldType(className, fieldName, newFieldType);
		checkStatus();
	}

	/**
	 * Changes method type in WPEditor
	 * @param className is the class name
	 * @param methodName is the method name
	 * @param newMethodType is the new method type
	 */
	public void changeMethodType(String className, String methodName, String newMethodType)
	{
		project.changeMethodType(className, methodName, newMethodType);
		checkStatus();
	}

	/**
	 * Changes param type in WPEditor
	 * @param className is the class name
	 * @param methodName is the method name
	 * @param paramName is the param name
	 * @param newParamType is the new param name
	 */
	public void changeParameterType(String className, String methodName, String paramName, String newParamType)
	{
		project.changeParameterType(className, methodName, paramName, newParamType);
		checkStatus();
	}

	/**********************************VISIBILITY********************************************/
	/**
	 * Changes field visibility in WPEditor
	 * @param className is the class name
	 * @param fieldName is the field name
	 * @param newFieldVis is the new field visibility
	 */
	public void changeFieldVisibility( String className, String fieldName, String newFieldVis)
	{
		project.changeFieldVisibility(className, fieldName, newFieldVis);
		checkStatus();
	}

	/**
	 * Changes method visibility in WPEditor
	 * @param className is the class name
	 * @param methodName is the method name
	 * @param newMethVis is the new method visibility
	 */
	public void changeMethodVisibility(String className, String methodName, String newMethVis)
	{
		project.changeMethodVisibility(className, methodName, newMethVis);
		checkStatus();
	}

	/**********************************RELATIONS********************************************/
	/**
	 * Adds relationship to WPEditor
	 * @param classNameFrom is the relationship from class name
	 * @param classNameTo is the relationship to class name 
	 * @param type is the relationship type
	 */
	public void addRelationship(String classNameFrom, String classNameTo, String type) 
	{
		project.addRelationship(classNameFrom, classNameTo, type);
		checkStatus();
	}

	/**
	 * Deletes relationship in WPEditor
	 * @param classNameFrom is the relationship from class name
	 * @param classNameTo is the relationship to class name
	 */
	public void removeRelationship(String classNameFrom, String classNameTo)
	{
		project.removeRelationship(classNameFrom, classNameTo);
		checkStatus();
	}

	/**
	 * Changes relationship type in WPEditor
	 * @param classNameFrom is the relationship from class name
	 * @param classNameTo is the relationship to class name
	 * @param newTypeName is the new relationshipe type
	 */
	public void changeRelationshipType(String classNameFrom, String classNameTo, String newTypeName)
	{
		project.changeRelationshipType(classNameFrom, classNameTo, newTypeName);
		checkStatus();
	}
	
	/**********************************OTHERS********************************************/
	/**
	 * Loads from the WPEditor
	 * @param projectJsonString is the WPEditor loaded string
	 */
	public void loadProject(String projectJsonString) 
	{
		project.loadProject(projectJsonString);		
		checkStatus();
	}

	/**
	 * Save into WPEditor
	 * @return WPEditor string
	 */
	public String toJSONString()
	{
		return project.toJSONString();
	}

	/**
	 * WPEditor undo
	 */
	public void undo()
	{
		project.undo();
	}

	/**
	 * WPEditor redo
	 */
	public void redo()
	{
		project.redo();
	}

	/**
	 * Creates a copy of the current state of the Model
     * @return copy of the model
	 */
	public Model getProjectSnapshot()
	{
		return project.getProjectSnapshot();
	}

	/**
	 * Creates listeners and adds them to the view
	 */
	public void addListeners()
    {
        view.addListeners(new FileButtonClick(view, this), new ClassClick(view, this), new RelationshipClick(view, this), new RightClickListenerFactory(view, this));
	}

	/**
	 * Creates starting window for UML
	 */
	public void start()
	{
		view.showWindow();
		addListeners();
	}
	
	/**
	 * Checks if the WPEditor modification was legal or not
	 */
	private void checkStatus()
    {
        // If the last command failed
        if(!project.getLastCommandStatus())
        {
            view.alert("Error: " + project.getLastCommandStatusMessage());
        }
    }

}
