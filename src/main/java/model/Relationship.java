package model;

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
	private String classNameFrom;
	private String classNameTo;
	private relationshipType type;
	
	/**
	 * Creates a new relationship.
	 * @param classNameFrom the name of the class the relationship starts at
	 * @param classNameTo   the name of the class the relationship goes to
	 * @param type      the relationship's type
	 */
	public Relationship(String classNameFrom, String classNameTo, relationshipType type)
	{
		this.classNameFrom = classNameFrom;
		this.classNameTo = classNameTo;
		this.type = type;
	}
	
	/**
	 * Accessor for the relationship's "from" class
	 * @return the class the relationship starts at
	 */
	public String getClassNameFrom()
	{
		return classNameFrom;
	}
	
	/**
	 * Accessor for the relationship's "to" class
	 * @return the class the relationship goes to
	 */
	public String getClassNameTo()
	{
		return classNameTo;
	}

	public void setClassNameFrom(String newClassNameFrom){
		classNameFrom = newClassNameFrom;
	}

	public void setClassNameTo(String newClassNameTo){
		classNameTo = newClassNameTo;
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
	public boolean containsClass(String className)
	{
		return (className.equals(classNameFrom) || className.equals(classNameTo));
	}

    public JSONObject toJSON()
    {
		JSONObject jsonRelationship = new JSONObject();

		jsonRelationship.put("from", classNameFrom);
		jsonRelationship.put("to", classNameTo);
		jsonRelationship.put("type", type.name());

		return jsonRelationship;
	}

	public Relationship copy(){
		return new Relationship(classNameFrom, classNameTo, type);
	}

	public String toString()
	{
		return classNameFrom + " -> " + classNameTo + " (" + type.name() + ")";
	}
}
