package code.ir.tac;

import code.ir.tac.operand.Operand;

public class AddressCode
{
    public InstrFunc func;
    public Operand opOne;
    public Operand opTwo;
    public Operand result;

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(this.func.toString());

        if (this.opOne != null)
        {
            builder.append(" ");
            builder.append(this.opOne);
        }

        if (this.opTwo != null)
        {
            builder.append(" ");
            builder.append(this.opTwo);
        }

        if (this.result != null)
        {
            builder.append(" ");
            builder.append(this.result);
        }

        return builder.toString();
    }
}
