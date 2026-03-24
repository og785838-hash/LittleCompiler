package code.tiny.tac.instr.io;

import code.tiny.tac.TinyInstr;
import code.tiny.tac.TinyInstrFunc;
import code.tiny.tac.operand.TinyOperand;

public class TinyReadR extends TinyInstr
{
    public TinyReadR(TinyOperand op)
    {
        this.func = TinyInstrFunc.READR;
        this.opOne = op;
    }
}
