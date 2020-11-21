package controller;

public interface CLIController extends Controller
{
    void addMethod(String className, String methodName, String methodType, String methodVisName);

    void addParameter(String className, String methodName, String parameterName, String parameterType);

    void removeParameter(String className, String methodName, String parameterName);

    void renameParameter(String className, String methodName, String parameterName, String newParameterName);

    void changeParameterType(String className, String methodName, String parameterName, String newParameterType);

    String getLastCommandStatusMessage();

}