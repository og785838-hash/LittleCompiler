package ast.stmt;

import java.util.ArrayList;
import java.util.List;

public class WriteNode extends StmtNode
{
    public List<String> idList;

    public WriteNode()
    {
        this.idList = new ArrayList<>();
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("WriteNode:");

        for (String id : idList)
        {
            sb.append("\n\tID: ").append(id);
        }

        return sb.toString();
    }
}
