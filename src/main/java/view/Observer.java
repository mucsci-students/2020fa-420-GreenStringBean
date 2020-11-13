package view;

import model.Model;
import model.ClassObject;

public interface Observer{
    void onUpdate(Model project, boolean newLoadedProject);
    void onUpdate(ClassObject classObj);
}