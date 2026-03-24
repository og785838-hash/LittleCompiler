package ast.flow;

import ast.ASTNode;
import ast.decl.DeclNode;
import ast.stmt.StmtNode;

import java.util.ArrayList;
import java.util.List;

public class ElseNode extends StmtNode
{
    public int nBlock;
    public List<DeclNode> declList;
    public List<StmtNode> stmtList;

    public ElseNode()
    {
        this.declList = new ArrayList<>();
        this.stmtList = new ArrayList<>();
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("ElseNode:");

        for (DeclNode decl : this.declList)
        {
            String indented = decl.toString().replaceAll("\n", "\n\t");
            sb.append("\n\t").append(indented);
        }

        for (StmtNode stmt : this.stmtList)
        {
            String indented = stmt.toString().replaceAll("\n", "\n\t");
            sb.append("\n\t").append(indented);
        }

        return sb.toString();
    }
}
