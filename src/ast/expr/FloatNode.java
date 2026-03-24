package ast.expr;

public class FloatNode extends ExprNode
{
    public float val;

    public FloatNode(String val)
    {
        this.val = Float.parseFloat(val);
    }

    @Override
    public String toString()
    {
        return "FloatNode:\n\tValue: " + this.val;
    }
}
