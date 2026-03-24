package ast.stmt;

import java.util.ArrayList;
import java.util.List;

public class ReadNode extends StmtNode
{
    public List<String> idList;

    public ReadNode()
    {
        this.idList = new ArrayList<>();
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("ReadNode:");

        for (String id : idList)
        {
            sb.append("\n\tID: ").append(id);
        }

        return sb.toString();
    }
}
