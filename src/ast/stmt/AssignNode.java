package ast.stmt;

import ast.Operator;
import ast.expr.ExprNode;

public class AssignNode extends StmtNode
{
    public String id;
    public Operator op;
    public ExprNode val;

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("AssignNode:").append("\n\t").append(this.op.toString()).append("\n\tID: ").append(this.id);
        String indented = this.val.toString().replaceAll("\n", "\n\t");
        sb.append("\n\t").append(indented);

        return sb.toString();
    }
}
