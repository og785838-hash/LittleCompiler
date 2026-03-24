package resolve;

import ast.Type;

public class Symbol
{
    public Type.Meta meta;
    public Type type;
    public String id;
    public String val;

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("name ").append(this.id).append(" type ").append(this.type.name());

        if (this.val != null)
        {
            sb.append(" value ").append(this.val);
        }

        return sb.toString();
    }
}
