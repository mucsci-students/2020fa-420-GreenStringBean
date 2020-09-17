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
}
