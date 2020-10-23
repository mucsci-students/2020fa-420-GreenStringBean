package model;

/**
 * A formal declaration contains a name and a type.
 */

public abstract class FormalDeclaration {
    private String name;
    private String type;

    /**
     * Creates a new formal declatation.
     * @param name the name of the formal declaration
     * @param type the type of the formal declaration
     */
    public FormalDeclaration(String name, String type)
    {
        this.name = name;
        this.type = type;
    }

    /**
     * Accessor for the name.
     * @return the name of the formal declaration
     */
    public String getName()
    {
        return name;
    }

    /**
     * Accessor for the type.
     * @return the type of the formal declaration
     */
    public String getType()
    {
        return type;
    }

    /**
     * Mutator for the name
     * @param name the new name to give the formal declaration
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Mutator for the type
     * @param type the new type to give the formal declaration
     */
    public void setType(String type)
    {
        this.type = type;
    }
}
