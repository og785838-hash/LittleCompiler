package ast.decl;

import ast.ASTNode;
import ast.stmt.StmtNode;
import ast.Type;

import java.util.ArrayList;
import java.util.List;

public class FuncDeclNode extends ASTNode
{
    public Type retType;
    public String id;
    public List<ParamNode> paramList;
    public List<DeclNode> declList;
    public List<StmtNode> stmtList;

    public FuncDeclNode()
    {
        this.paramList = new ArrayList<>();
        this.declList = new ArrayList<>();
        this.stmtList = new ArrayList<>();
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("FuncDeclNode:").append("\n\tReturn").append(this.retType.toString()).append("\n\tID: ").append(this.id);

        for (ParamNode param : this.paramList)
        {
            String indented = param.toString().replaceAll("\n", "\n\t");
            sb.append("\n\t").append(indented);
        }

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
