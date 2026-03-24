package ast.decl;

import ast.Type;

public class StrDeclNode extends DeclNode
{
    public Type type;
    public String id;
    public String val;

    @Override
    public String toString()
    {
        return "StrDeclNode:" + "\n\t" + this.type + "\n\tID: " + this.id + "\n\tValue: " + this.val;
    }
}
