package ast.expr;

import ast.Operator;

public class BinExprNode extends ExprNode
{
    public Operator op;
    public ExprNode left;
    public ExprNode right;

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("BinExprNode:");
        String indentOp = this.op.toString().replaceAll("\n", "\n\t");
        sb.append("\n\t").append(indentOp);
        String indentLeft = this.left.toString().replaceAll("\n", "\n\t");
        sb.append("\n\t").append(indentLeft);
        String indentRight = this.right.toString().replaceAll("\n", "\n\t");
        sb.append("\n\t").append(indentRight);

        return sb.toString();
    }
}
