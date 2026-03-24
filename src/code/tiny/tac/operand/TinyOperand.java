package code.tiny.tac.operand;

public class TinyOperand
{
    public String ref;

    public TinyOperand(String ref)
    {
        this.ref = ref;
    }

    @Override
    public String toString()
    {
        return this.ref;
    }
}
