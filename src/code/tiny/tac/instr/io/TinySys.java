package code.tiny.tac.instr.io;

import code.tiny.Tiny;
import code.tiny.tac.TinyInstr;
import code.tiny.tac.TinyInstrFunc;
import code.tiny.tac.operand.TinyOperand;

public class TinySys extends TinyInstr
{
    public TinyInstr instr;

    public TinySys(TinyInstr instr)
    {
        this.func = TinyInstrFunc.SYS;
        this.instr = instr;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(this.func.toString());
        sb.append(" ").append(this.instr.toString());

        return sb.toString();
    }
}
