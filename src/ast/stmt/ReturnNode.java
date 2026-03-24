package ast.stmt;

import ast.expr.ExprNode;

public class ReturnNode extends StmtNode
{
    public ExprNode expr;

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("ReturnNode:");
        String indented = this.expr.toString().replaceAll("\n", "\n\t");
        sb.append(indented);

        return sb.toString();
    }
}
