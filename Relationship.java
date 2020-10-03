import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONAware;


public class Relationship
{

	private ClassObject classOne;
	private ClassObject classTwo;
	
	public Relationship(ClassObject classO, ClassObject classT)
	{
		classOne = classO;
		classTwo = classT;
	}
	
	public ClassObject getClassOne()
	{
		return classOne;
	}
	
	public ClassObject getClassTwo()
	{
		return classTwo;
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
		//sb.append(",");
		//sb.append("\"" + "Type" + "\"");
		//sb.append(":");
		//sb.append("\"" + this.getRelationshipType() + "\"");
		sb.append("}");
		return sb.toString();
	}
}
