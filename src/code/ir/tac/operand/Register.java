package code.ir.tac.operand;

public class Register extends Operand
{
    public static int count = 0;

    public int num;

    public Register(int reg)
    {
        super("$T" + reg);
        this.num = reg;
    }

    public Register()
    {
        super("$T" + ++Register.count);
        this.num = Register.count;
    }
}
