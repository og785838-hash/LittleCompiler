package code.ir.tac.operand;

public class Label extends Operand
{
    public static int count = 0;

    public Label()
    {
        super("L" + ++Label.count);
    }

    public Label(String name)
    {
        super(name);
    }
}
