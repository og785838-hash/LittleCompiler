package resolve;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable
{
    public String title;
    public Map<String, Symbol> symbols = new HashMap<>();

    public SymbolTable(String title)
    {
        this.title = title;
    }

    public void add(Symbol s)
    {
        this.symbols.put(s.id, s);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Symbol table ").append(this.title);

        for (Symbol s : this.symbols.values())
        {
            sb.append("\n").append(s.toString());
        }

        return sb.toString();
    }
}
