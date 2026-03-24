package ast.decl;

import ast.Type;

import java.util.ArrayList;
import java.util.List;

public class VarDeclNode extends DeclNode
{
    public Type type;
    public List<String> idList;

    public VarDeclNode()
    {
        this.idList = new ArrayList<>();
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("VarDeclNode:").append("\n\t").append(this.type.toString());

        for (String id : this.idList)
        {
            sb.append("\n\tID: ").append(id);
        }

        return sb.toString();
    }
}
