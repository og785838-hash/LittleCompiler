package ast.decl;

import ast.ASTNode;
import ast.Type;

public class ParamNode extends ASTNode
{
    public Type type;
    public String id;

    @Override
    public String toString()
    {
        return "ParamNode:" + "\n\t" + this.type.toString() + "\n\t" + this.id;
    }
}
