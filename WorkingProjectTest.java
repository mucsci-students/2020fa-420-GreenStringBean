public class WorkingProjectTest {
    public static void main(String[] args)
    {
        System.out.println("-Testing Working Project Class");

        // Test creating a new project
        System.out.println("-Creating working project");
        WorkingProject project = new WorkingProject();

        // Test printing empty class/relationship lists
        System.out.println("-Printing empty class list");
        project.printClasses();
        System.out.println("-Printing empty relationship list");
        project.printRelationships();

        // Test adding classes
        System.out.println("-Adding a new class \"a\"");
        project.addClass("a");
        System.out.println("-Adding a new class \"b\"");
        project.addClass("b");
        System.out.println("-Adding a new class \"c\"");
        project.addClass("c");

        // Test adding class with empty name (should behave as normal for now)
        System.out.println("-Adding a new class \"\"");
        project.addClass("");

        // Test adding an existing class
        System.out.println("-Adding another class called \"a\"");
        project.addClass("a");

        // Test opening/closing classes (no effect for now)
        System.out.println("-Opening class a");
        project.openClass("a");
        System.out.println("-Opening class a again");
        project.openClass("a");
        System.out.println("-Closing class a");
        project.closeClass("a");
        System.out.println("-Closing class a again");
        project.closeClass("a");


        // Test adding relationships
        System.out.println("-Adding a new relationship from a to b");
        project.addRelationship("a", "b");
        System.out.println("-Adding a new relationship from b to a");
        project.addRelationship("b", "a");
        System.out.println("-Adding a new relationship from a to c");
        project.addRelationship("a", "c");
        System.out.println("-Adding a new relationship from a to a");
        project.addRelationship("a", "a");

        // Test adding an existing relationship
        System.out.println("-Adding another relationship from a to b");
        project.addRelationship("a", "b");

        // Test adding a relationship involving a class that doesn't exist
        System.out.println("-Adding a new relationship from a to d");
        project.addRelationship("a", "d");
        System.out.println("-Adding a new relationship from d to a");
        project.addRelationship("d", "a");
        System.out.println("-Adding a new relationship from d to e");
        project.addRelationship("d", "e");

        // Test printing class/relationship lists
        System.out.println("-Printing class list");
        project.printClasses();
        System.out.println("-Printing relationship list");
        project.printRelationships();

        // Test printing empty attribute lists
        System.out.println("-Printing a's attribute list");
        project.printAttributes("a");
        System.out.println("-Printing b's attribute list");
        project.printAttributes("b");
        System.out.println("-Printing c's attribute list");
        project.printAttributes("c");

        // Test printing attributes of a class that doens't exist
        System.out.println("-Printing d's attribute list");
        project.printAttributes("d");

        // Test adding attributes
        System.out.println("-Adding a new attribute \"x\" to a");
        project.addAttribute("a", "x");
        System.out.println("-Adding a new attribute \"y\" to a");
        project.addAttribute("a", "y");
        System.out.println("-Adding a new attribute \"y\" to b");
        project.addAttribute("b", "y");
        System.out.println("-Adding a new attribute \"z\" to c");
        project.addAttribute("c", "z");

        // Test adding attribute with empty name
        System.out.println("-Adding a new attribute \"\" to c");
        project.addAttribute("c", "");

        // Test adding an attribute to a class that already has it
        System.out.println("-Adding another attribute called \"x\" to a");
        project.addAttribute("a", "x");

        // Test adding an attribute to a class that doesn't exist
        System.out.println("-Adding a new attribute \"x\" to d");
        project.addAttribute("d", "x");

        // Test printing attribute lists
        System.out.println("-Printing a's attribute list");
        project.printAttributes("a");
        System.out.println("-Printing b's attribute list");
        project.printAttributes("b");
        System.out.println("-Printing c's attribute list");
        project.printAttributes("c");

        // Test renaming an attribute
        System.out.println("-Renaming a's attribute x to w");
        project.renameAttribute("a", "x", "w");
        System.out.println("-Printing a's attribute list");
        project.printAttributes("a");

        // Test renaming an attribute to an existing name
        System.out.println("-Renaming a's attribute y to w");
        project.renameAttribute("a", "y", "w");

        // Test renaming an attribute that doesn't exist
        System.out.println("-Renaming a's attribute v to u");
        project.renameAttribute("a", "v", "u");

        // Test removing an attribute
        System.out.println("-Removing attribute y from a");
        project.removeAttribute("a", "y");
        System.out.println("-Printing a's attribute list");
        project.printAttributes("a");

        // Test removing an attribute that doesn't exist
        System.out.println("-Removing attribute v from a");
        project.removeAttribute("a", "v");

        // Test renaming a class
        System.out.println("-Renaming class a to m");
        project.renameClass("a", "m");
        System.out.println("-Printing class list");
        project.printClasses();

        // Test renaming a class to an existing name
        System.out.println("-Renaming class b to m");
        project.renameClass("b", "m");

        // Test renaming a class that doesn't exist
        System.out.println("-Renaming class d to n");
        project.renameClass("d", "n");

        // Test removing a class
        System.out.println("-Removing class b");
        project.removeClass("b");
        System.out.println("-Printing class list");
        project.printClasses();

        // Test removing a class that doesn't exist
        System.out.println("-Removing class d");
        project.removeClass("d");

        // Test that relationships updated properly
        System.out.println("-Printing relationship list");
        project.printRelationships();

        // Test removing a relationship
        System.out.println("-Removing relationship from m to c");
        project.removeRelationship("m", "c");
        System.out.println("-Printing relationship list");
        project.printRelationships();

        // Test removing a relationship that doesn't exist
        System.out.println("-Remvoing relationship from c to m");
        project.removeRelationship("c", "m");
    }
}
