package controller;

public interface CLIController extends Controller
{
    /**
     * Add a new method to a class
     * @param className the name of the class to add a method to
     * @param methodName the name of the method to be added
     * @param methodType the return type of the method being added
     * @param methodVisName the visibility of the method being added
     */
    void addMethod(String className, String methodName, String methodType, String methodVisName);

    /**
     * Add a new parameter to a method
     * @param className the name of the class with the method to add a parameter to
     * @param methodName the name of the method to add a parameter to
     * @param parameterName the name of the parameter to be added
     * @param parameterType the type of the parameter to be added
     */
    void addParameter(String className, String methodName, String parameterName, String parameterType);

    /**
     * Remove a parameter from a method
     * @param className the name of the class with the method to remove a parameter from
     * @param methodName the name of the method to remove a parameter from
     * @param parameterName the name of the parameter to be removed
     */
    void removeParameter(String className, String methodName, String parameterName);

    /**
     * Rename a parameter
     * @param className the name of the class with the method whose parameter is being renamed
     * @param methodName the name of the method whose parameter is being renamed
     * @param parameterName the name of the parameter being renamed
     * @param newParameterName the new name of the parameter
     */
    void renameParameter(String className, String methodName, String parameterName, String newParameterName);

    /**
     * Change a parameter type
     * @param className the name of the class with the method whose parameter's type is changing
     * @param methodName the name of the method whose parameter's type is changing
     * @param parameterName the name of the parameter whose type is changing
     * @param newParameterType the new type for the parameter
     */
    void changeParameterType(String className, String methodName, String parameterName, String newParameterType);

    /**
     * 
     * @return
     */
    String getLastCommandStatusMessage();

}