package controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import command.Command;
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
		storeViewState();
		project.openClass(className);
		checkStatus();
	}

	/**
	 * Closes class to editing in WPEditor
	 * @param className is the name of the class
	 */
	public void closeClass(String className)
	{
		storeViewState();
		project.closeClass(className);
		checkStatus();
	}

	/**
	 * Adds a class in WPEditor
	 * @param className is the name of the class
	 */
	public void addClass(String className) 
	{
		storeViewState();
		project.addClass(className);
		checkStatus();
	}

	/**
	 * Deletes class in WPEditor
	 * @param className is the name of the class
	 */
	public void removeClass(String className) 
	{
		storeViewState();
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
		storeViewState();
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
		storeViewState();
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
		storeViewState();
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
		storeViewState();
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
		storeViewState();
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
		storeViewState();
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
		storeViewState();
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
		storeViewState();
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
		storeViewState();
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
		storeViewState();
		project.changeMethodType(className, methodName, newMethodType);
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
		storeViewState();
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
		storeViewState();
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
		storeViewState();
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
		storeViewState();
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
		storeViewState();
		project.changeRelationshipType(classNameFrom, classNameTo, newTypeName);
		checkStatus();
	}
	
	/**********************************OTHERS********************************************/
	/**
	 * Loads a project from a JSON string. If the project was saved in the GUI, also loads state of the view.
	 * @param jsonString is the WPEditor loaded string
	 */
	public void loadProject(String jsonString)
	{
		try
		{
			JSONObject jsonInput = (JSONObject)JSONValue.parse(jsonString);
			if (jsonInput == null)
			{
				view.alert("Error: Error loading project");
			}

			if (jsonInput.get("project") != null)
			{
				JSONObject jsonProject = (JSONObject)jsonInput.get("project");
				storeViewState();
				project.loadProject(jsonProject.toJSONString());
				checkStatus();
				
				if (project.getLastCommandStatus())
				{
					view.loadFromJSON(jsonString);
					view.onUpdate(project.getProjectSnapshot(), false);
				}
			}
			else
			{
				storeViewState();
				project.loadProject(jsonString);
				checkStatus();
			}
		}
		catch (ClassCastException e)
		{
			view.alert("Error: Error loading project");
		}
	}

	/**
	 * Save into WPEditor
	 * @return WPEditor string
	 */
	public String toJSONString()
	{
		return view.toJSONString(project.toJSONString());
	}

	/**
	 * WPEditor undo
	 */
	public void undo()
	{
		storeViewState();
		project.undo();
		checkStatus();
	}

	/**
	 * WPEditor redo
	 */
	public void redo()
	{
		storeViewState();
		project.redo();
		checkStatus();
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
        view.addListeners(new FileButtonClick(view, this), new ViewButtonClick(view, this), new ClassClick(view, this), new RelationshipClick(view, this), new RightClickListenerFactory(view, this));
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
	 * Stores the state of the view in the last command's optional status data
	 */
	private void storeViewState()
	{
		Command lastCmd = project.getLastCommand();
		if (lastCmd != null)
		{
			lastCmd.setOptionalState(view.toJSONString(lastCmd.getProjectState()));
		}
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
		else
		{
			Command lastCmd = project.getLastCommand();
			if (lastCmd != null && lastCmd.hasOptionalState())
			{
				view.loadFromJSON(lastCmd.getOptionalState());
				view.onUpdate(project.getProjectSnapshot(), false);
			}
		}
    }

}
