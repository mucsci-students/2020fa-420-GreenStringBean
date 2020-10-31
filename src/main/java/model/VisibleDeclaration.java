package model;

/**
 * The visible declaration represets a formal declaration that also has a
 * visibility modifier (public / protected / private).
 */
public abstract class VisibleDeclaration extends FormalDeclaration {
    public enum visibility
    {
        PUBLIC,
        PRIVATE,
        PROTECTED
    }
    
    protected visibility vis;

    /**
     * Creates a new formal declaration with a visibility modifier.
     * @param name the name of the declaration
     * @param type the type of the declaration
     * @param vis  the visibility of the declaration
     */
    protected VisibleDeclaration(String name, String type, visibility vis)
    {
        super(name, type);
        this.vis = vis;
    }

    /**
     * Accessor for the visiblity modifier.
     * @return the declaration's visibility
     */
    public visibility getVisibility()
    {
        return vis;
    }

    /**
     * Mutator for the visibility modifier.
     * @param vis the new visibility
     */
    public void setVisibility(visibility vis)
    {
        this.vis = vis;
    }
}
