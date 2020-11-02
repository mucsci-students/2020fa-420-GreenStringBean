package view;

import model.WorkingProject;
import model.ClassObject;

public interface Observer{
    void onUpdate(WorkingProject project);
    void onUpdate(ClassObject classObj);
}