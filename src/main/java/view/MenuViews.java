package view;
import java.awt.event.ActionListener;

public interface MenuViews extends Observer
{
	String getText(String string);
	
	void alert(String message);

	void addListeners(ActionListener fileClick, ActionListener classClick,
			ActionListener fieldClick, ActionListener relationshipClick);	

	void start();

	void createClass(String classes);

	void delClass(String className);

	void updateClass(String oldClass, String updateClassString);
	
}