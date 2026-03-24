package ast;

import ast.decl.DeclNode;
import ast.decl.FuncDeclNode;

import java.util.ArrayList;
import java.util.List;

public class ProgramNode extends ASTNode
{
    public String id;
    public List<DeclNode> declList = new ArrayList<>();
    public List<FuncDeclNode> funcDeclList = new ArrayList<>();

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("ProgramNode:").append("\n\tID: ").append(this.id);

        for (DeclNode decl : this.declList)
        {
            String indented = decl.toString().replaceAll("\n", "\n\t");
            sb.append("\n\t").append(indented);
        }

        for (FuncDeclNode funcDecl : this.funcDeclList)
        {
            String indented = funcDecl.toString().replaceAll("\n", "\n\t");
            sb.append("\n\t").append(indented);
        }

        return sb.toString();
    }
}
