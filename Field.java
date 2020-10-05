public class Field extends FormalDeclaration {
    public Field(String name, String type)
    {
        super(name, type);
    }

    public String toJSONString ()
   {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"" + "Name" + "\"");
        sb.append(":");
        sb.append("\"" + this.getName() + "\"");
        sb.append(",");
        sb.append("\"" + "Type" + "\"");
        sb.append(":");
        sb.append("\"" + this.getType() + "\"");
        sb.append(",");
        sb.append("\"" + "FieldOrMethod" + "\"");
        sb.append(":");
        sb.append("\"" + "Field" + "\"");
        sb.append("}");
        return sb.toString();
   }
}
