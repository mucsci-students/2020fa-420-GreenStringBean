package controller;

import model.ClassObject;
import model.WorkingProject;
import view.*;

public class HelperControllers {
	private WorkingProject project;
	private MenuViews view;
	
	public HelperControllers(WorkingProject p, MenuViews v) 
	{
		this.project = p;
		this.view = v;
	}

	public void createClass(String className) 
	{
		System.out.println("made it to createClass() in HellperController");
		
		project.addClass(className);
		ClassObject aClass = project.getClass(className);
		String classes = aClass.toString();
		view.createClass(classes);
	}

	public void delClass(String className) 
	{
		ClassObject aClass = project.getClass(className);
		project.removeClass(className);
		String classes = aClass.toString();
		view.delClass(classes);
	}

	public void renameClass(String oldName, String newName) 
	{
		ClassObject oldClass = project.getClass(oldName);
		String old = oldClass.toString();
		project.renameClass(oldName, newName);
		sendView("Class", "renamed", newName, old);
	}

	public void addField(String className, String name, String type) 
	{
		ClassObject aClass = project.getClass(className);
		String oldClass = aClass.toString();
		project.addField(className, name, type);
		sendView("Field", "added", className, oldClass);
	}

	public void removeField(String className, String name) 
	{
		ClassObject aClass = project.getClass(className);
		String oldClass = aClass.toString();
		project.removeField(className, name);
		sendView("Field", "deleted", className, oldClass);
	}

	public void renameField(String className, String name, String newName) 
	{
		ClassObject aClass = project.getClass(className);
		String oldClass = aClass.toString();
		project.renameField(className, name, newName);
		sendView("Field", "renamed", className, oldClass);
	}

	public void load(String projectToLoad) 
	{
		// TODO Auto-generated method stub
		
	}

	public void addRelationship(String classNameFrom, String classNameTo, String type) 
	{
		ClassObject className = project.getClass(classNameFrom);
		ClassObject toClass = project.getClass(classNameTo);
		String fromClassStr = className.toString();
		String toClassStr = toClass.toString();
		project.addRelationship(classNameFrom, classNameTo, type);
		sendView("Relationship", "added", classNameFrom, fromClassStr);
		sendView("Relationship", "added", classNameTo, toClassStr);
	}
	
	private void sendView(String object, String action, String className, String oldClass)
	{
		String updateClassString = project.getClass(className).toString();
		view.updateClass(oldClass, updateClassString);
	}
	
	public void addListeners()
    {
		System.out.println("Made it to addListeners()");
        view.addListeners(new FileButtonClick(project, view, this), new ClassClick(project, view, this), new FieldClick(project, view, this), new RelationshipClick(project, view, this));
    }

}
