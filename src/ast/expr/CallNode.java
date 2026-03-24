package ast.expr;

import java.util.ArrayList;
import java.util.List;

public class CallNode extends ExprNode
{
    public String id;
    public List<ExprNode> args;

    public CallNode()
    {
        this.args = new ArrayList<>();
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("CallNode:").append("\n\tID: ").append(this.id);

        for (ExprNode arg : this.args)
        {
            String indented = arg.toString().replaceAll("\n", "\n\t");
            sb.append("\n\t").append(indented);
        }

        return sb.toString();
    }
}