import java.util.List;
import java.util.Set;

import model.ClassObject;
import model.Model;
import model.Relationship;
import model.WorkingProject;

/**
 * Mock implementation of model which returns -1 for add class and 99 for all others. Used to test the "unknown error" command message
 */

public class UnknownErrorModel implements Model {

    @Override
    public int addClass(String className) {
        return -1;
    }

    @Override
    public int removeClass(String className) {
        return 99;
    }

    @Override
    public int renameClass(String oldClassName, String newClassName) {
        return 99;
    }

    @Override
    public int closeClass(String className) {
        return 99;
    }

    @Override
    public int openClass(String className) {
        return 99;
    }

    @Override
    public int addField(String className, String fieldName, String fieldType, String fieldVisName) {
        return 99;
    }

    @Override
    public int removeField(String className, String fieldName) {
        return 99;
    }

    @Override
    public int renameField(String className, String oldFieldName, String newFieldName) {
        return 99;
    }

    @Override
    public int changeFieldType(String className, String fieldName, String newFieldType) {
        return 99;
    }

    @Override
    public int changeFieldVisibility(String className, String fieldName, String fieldVisName) {
        return 99;
    }

    @Override
    public int addMethod(String className, String methodName, String methodType, String methodVisName) {
        return 99;
    }

    @Override
    public int addMethod(String className, String methodName, String methodType, String methodVisName,
            List<String> paramNames, List<String> paramTypes) {
        return 99;
    }

    @Override
    public int removeMethod(String className, String methodName) {
        return 99;
    }

    @Override
    public int renameMethod(String className, String oldMethodName, String newMethodName) {
        return 99;
    }

    @Override
    public int changeMethodType(String className, String methodName, String newMethodType) {
        return 99;
    }

    @Override
    public int changeMethodVisibility(String className, String methodName, String methodVisName) {
        return 99;
    }

    @Override
    public int changeParameterList(String className, String methodName, List<String> paramNames,
            List<String> paramTypes) {
        return 99;
    }

    @Override
    public int addParameter(String className, String methodName, String paramName, String paramType) {
        return 99;
    }

    @Override
    public int removeParameter(String className, String methodName, String paramName) {
        return 99;
    }

    @Override
    public int renameParameter(String className, String methodName, String oldParamName, String newParamName) {
        return 99;
    }

    @Override
    public int changeParameterType(String className, String methodName, String paramName, String newParamType) {
        return 99;
    }

    @Override
    public int addRelationship(String classNameFrom, String classNameTo, String typeName) {
        return 99;
    }

    @Override
    public int removeRelationship(String classNameFrom, String classNameTo) {
        return 99;
    }

    @Override
    public int changeRelationshipType(String classNameFrom, String classNameTo, String newTypeName) {
        return 99;
    }

    @Override
    public void printClasses() {
        return;
    }

    @Override
    public int loadFromJSON(String jsonString) {
        return 99;
    }

    @Override
    public String toJSONString() {
        return null;
    }

    @Override
    public boolean hasClass(String className) {
        return false;
    }

    @Override
    public Set<String> getClassNames() {
        return null;
    }

    @Override
    public ClassObject getClass(String className) {
        return null;
    }

    @Override
    public boolean hasRelationship(String classNameFrom, String classNameTo) {
        return false;
    }

    @Override
    public Set<Relationship> getRelationships() {
        return null;
    }

    @Override
    public WorkingProject copy() {
        return null;
    }
    
}
