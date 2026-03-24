package code.tiny.tac.operand;

public class TinyRegister extends TinyOperand
{
    public static int count = 0;

    public int num;

    public TinyRegister(int reg)
    {
        super("r" + reg);
        this.num = reg;
    }

    public TinyRegister()
    {
        super("r" + TinyRegister.count);
        this.num = TinyRegister.count++;
    }
}
