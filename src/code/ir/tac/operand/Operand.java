package code.ir.tac.operand;

public class Operand
{
    public String ref;

    public Operand(String ref)
    {
        this.ref = ref;
    }

    @Override
    public String toString()
    {
        return this.ref;
    }
}
