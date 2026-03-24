package code.tiny.tac;

import code.tiny.tac.operand.TinyOperand;

public class TinyInstr
{
    public TinyInstrFunc func;
    public TinyOperand opOne;
    public TinyOperand opTwo;

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(this.func.toString());

        if (this.opOne != null)
        {
            sb.append(" ").append(this.opOne);
        }
        if (this.opTwo != null)
        {
            sb.append(" ").append(this.opTwo);
        }

        return sb.toString();
    }
}
