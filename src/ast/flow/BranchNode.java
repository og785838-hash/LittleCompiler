package ast.flow;

import ast.stmt.StmtNode;

public class BranchNode extends StmtNode
{
    public IfNode ifStmt;
    public ElseNode elseStmt;

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("BranchNode:");
        String indentIf = this.ifStmt.toString().replaceAll("\n", "\n\t");
        sb.append(indentIf);
        String indentElse = this.elseStmt.toString().replaceAll("\n", "\n\t");
        sb.append(indentElse);

        return sb.toString();
    }
}
