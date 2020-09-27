public class Relationship 
{
	enum relationshipType
	{
		COMPOSITION, AGGREGATION, GENERALIZATION, INHERITANCE;
	}
	private ClassObject classOne;
	private ClassObject classTwo;
	private relationshipType type;
	
	
	public Relationship(ClassObject classO, ClassObject classT, relationshipType t)
	{
		classOne = classO;
		classTwo = classT;
		type = t;
	}
	
	public ClassObject getClassOne()
	{
		return classOne;
	}
	
	public ClassObject getClassTwo()
	{
		return classTwo;
	}

	public relationshipType getRelationshipType()
	{
		return type;
	}

	public void setRelationshipType(relationshipType newType)
	{
		type = newType;
	}

}
