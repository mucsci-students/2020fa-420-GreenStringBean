import org.json.simple.JSONObject;

/**
 * A relationship represents an ordered UML relationship from one class to
 * another class. Relationships can be one of four types: Aggregation,
 * Composition, Inheritance, or Realization.
 */

public class Relationship {
	enum relationshipType
	{
		AGGREGATION, COMPOSITION, INHERITANCE, REALIZATION;
	}
	private ClassObject classFrom;
	private ClassObject classTo;
	private relationshipType type;
	
	/**
	 * Creates a new relationship.
	 * @param classFrom the class the relationship starts at
	 * @param classTo   the class the relationship goes to
	 * @param type      the relationship's type
	 */
	public Relationship(ClassObject classFrom, ClassObject classTo, relationshipType type)
	{
		this.classFrom = classFrom;
		this.classTo = classTo;
		this.type = type;
	}
	
	/**
	 * Accessor for the relationship's "from" class
	 * @return the class the relationship starts at
	 */
	public ClassObject getClassFrom()
	{
		return classFrom;
	}
	
	/**
	 * Accessor for the relationship's "to" class
	 * @return the class the relationship goes to
	 */
	public ClassObject getClassTo()
	{
		return classTo;
	}

	/**
	 * Accessor for the relationship's type
	 * @return the type of the relationship
	 */
	public relationshipType getType()
	{
		return type;
	}

	/**
	 * Mutator for the relationship's type
	 * @param type the new type to give to the relationship
	 */
	public void setType(relationshipType type)
	{
		this.type = type;
	}

	/**
	 * Check if the relationship contains a given class
	 * @param classObj the class to search for
	 * @return         true if the class is this relationship's "from" or "to"
	 *                 class
	 */
	public boolean containsClass(ClassObject classObj)
	{
		return (classObj == classFrom || classObj == classTo);
	}

    public JSONObject toJSON()
    {
		JSONObject jsonRelationship = new JSONObject();

		jsonRelationship.put("from", classFrom.getName());
		jsonRelationship.put("to", classTo.getName());
		jsonRelationship.put("type", type.name());

		return jsonRelationship;
	}
}
