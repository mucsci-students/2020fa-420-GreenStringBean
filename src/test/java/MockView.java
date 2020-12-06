import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import controller.RightClickListenerFactory;
import view.GUIView;

/**
 * Mock version of GUI view used for testing GUI controller.
 */

public class MockView extends MockObserver implements GUIView {
    public String lastAlert;

    public MockView()
    {
        lastAlert = "";
    }

    @Override
    public void alert(String message) {
        lastAlert = message;
    }

    @Override
    public void showWindow() {

    }

    @Override
    public String promptForString(String prompt, String title) {
        return null;
    }

    @Override
    public String promptForVis(String prompt, String title) {
        return null;
    }

    @Override
    public String promptForRelType(String prompt, String title) {
        return null;
    }

    @Override
    public String promptForClassName(String prompt, String title) {
        return null;
    }

    @Override
    public Map<String, String> promptForNewField(String title) {
        return null;
    }

    @Override
    public Map<String, Object> promptForNewMethod(String title) {
        return null;
    }

    @Override
    public Map<String, List<String>> promptForNewParamList(String title, List<String> oldParamNames,
            List<String> oldParamTypes) {
        return null;
    }

    @Override
    public Map<String, String> promptForNewRelationship(String title) {
        return null;
    }

    @Override
    public Map<String, String> promptToModifyRelationship(String title, boolean promptForType) {
        return null;
    }

    @Override
    public File getSaveFile() {
        return null;
    }

    @Override
    public File getSaveAsFile() {
        return null;
    }

    @Override
    public File getLoadFile() {
        return null;
    }

    @Override
    public void addListeners(ActionListener fileClick, ActionListener viewClick, ActionListener classClick,
            ActionListener relationshipClick, RightClickListenerFactory clickFactory) {

    }

    @Override
    public boolean zoomIn() {
        return false;
    }

    @Override
    public boolean zoomOut() {
        return false;
    }

    @Override
    public void toggleDarkMode() {

    }

    @Override
    public void contain(JPanel panel) {

    }

    @Override
    public void containAll() {

    }

    @Override
    public void refresh() {

    }

    @Override
    public String toJSONString(String jsonProjectString) {
        return null;
    }

    @Override
    public void loadFromJSON(String jsonString) {

    }
    
}
