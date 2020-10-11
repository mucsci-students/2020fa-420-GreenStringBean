import org.json.simple.JSONObject;


public class Relationship {
	enum relationshipType
	{
		AGGREGATION, COMPOSITION, INHERITANCE, REALIZATION;
	}
	private ClassObject classFrom;
	private ClassObject classTo;
	private relationshipType type;
	
	// Create a new Relationship with "from" and "to" ClassObject and a type
	public Relationship(ClassObject classFrom, ClassObject classTo, relationshipType type)
	{
		this.classFrom = classFrom;
		this.classTo = classTo;
		this.type = type;
	}
	
	// Return the "from" ClassObject
	public ClassObject getClassFrom()
	{
		return classFrom;
	}
	
	// Return the "to" ClassObject
	public ClassObject getClassTo()
	{
		return classTo;
	}

	// Return the relationship's type
	public relationshipType getType()
	{
		return type;
	}

	public void setType(relationshipType type)
	{
		this.type = type;
	}

	// Return true if classObj is part of the relationship
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
	
	// Two Relationships are equivalent if they have the same "from" and "to", even if they have different types
	// Used by the HashSet in WorkingProject
	public boolean equals(Object o)
	{
		return (o instanceof Relationship && ((Relationship)o).getClassFrom() == classFrom && ((Relationship)o).getClassTo() == classTo);
	}
}
