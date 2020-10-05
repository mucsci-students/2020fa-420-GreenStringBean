import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONAware;


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

	public String toJSONString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"" + "ClassOne" + "\"");
		sb.append(":");
		sb.append("\"" + this.getClassOne().getName() + "\"");
		sb.append(",");
		sb.append("\"" + "ClassTwo" + "\"");
		sb.append(":");
		sb.append("\"" + this.getClassTwo().getName() + "\"");
		sb.append(",");
		sb.append("\"" + "RelationshipType" + "\"");
		sb.append(":");
		sb.append("\"" + this.getRelationshipType() + "\"");
		sb.append("}");
		return sb.toString();
	}
}
