package ast.flow;

import ast.ASTNode;
import ast.Operator;
import ast.expr.ExprNode;

public class CondNode extends ASTNode
{
    public ExprNode left;
    public ExprNode right;
    public Operator op;

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("CondNode:");
        String indentOp = this.op.toString().replaceAll("\n", "\n\t");
        sb.append("\n\t").append(indentOp);
        String indentLeft = this.left.toString().replaceAll("\n", "\n\t");
        sb.append("\n\t").append(indentLeft);
        String indentRight = this.right.toString().replaceAll("\n", "\n\t");
        sb.append("\n\t").append(indentRight);

        return sb.toString();
    }
}
