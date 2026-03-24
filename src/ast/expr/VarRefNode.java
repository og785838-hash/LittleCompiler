package ast.expr;

public class VarRefNode extends ExprNode
{
    public String id;

    @Override
    public String toString()
    {
        return "VarRefNode:" + "\n\tID: " + this.id;
    }
}
