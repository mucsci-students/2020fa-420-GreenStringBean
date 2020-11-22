package controller;

import java.util.List;

public interface GUIController extends Controller {
    /**
     * Adds a new method with parameters to a class.
     * @param className  the name of the class to add a method to
     * @param methodName the name to be used by the new method
     * @param methodType the data type to be used by the new method
     */
    void addMethod(String className, String methodName, String methodType, String methodVis, List<String> paramNames, List<String> paramTypes);
    
    /**
     * Changes the entire parameter list of a method, if it exists.
     * @param className  the name of the class with the method to modify
     * @param methodName the name of the method to modify
     * @param paramNames the list of new parameter names
     * @param paramTypes the list of new parameter data types
     */
    void changeParameterList(String className, String methodName, List<String> paramNames, List<String> paramTypes);

	/**
	 * Creates listeners and adds them to the view
	 */
    void addListeners();
    
    /**
     * Starts the view
     */
    void start();
}
