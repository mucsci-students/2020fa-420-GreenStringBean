package view;
import java.awt.event.ActionListener;

public interface MenuViews extends Observer
{
	String getText(String prompt, String title);
	
	void alert(String message);

	void addListeners(ActionListener fileClick, ActionListener classClick,
			ActionListener fieldClick, ActionListener relationshipClick);	

	void start();
}
