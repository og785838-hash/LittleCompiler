package resolve;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TableMap implements Iterable<SymbolTable>
{
    private final Map<Integer, SymbolTable> mapInt = new HashMap<>();
    private final Map<String, SymbolTable> mapStr = new HashMap<>();

    public void put(int scopeIndex, SymbolTable table)
    {
        this.mapInt.put(scopeIndex, table);
        this.mapStr.put(table.title, table);
    }

    public SymbolTable get(int scopeIndex)
    {
        return this.mapInt.get(scopeIndex);
    }

    public SymbolTable get(String title)
    {
        return this.mapStr.get(title);
    }

    @Override
    public Iterator<SymbolTable> iterator()
    {
        return this.mapInt.values().iterator();
    }
}
