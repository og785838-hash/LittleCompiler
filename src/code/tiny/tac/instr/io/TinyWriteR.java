package code.tiny.tac.instr.io;

import code.tiny.tac.TinyInstr;
import code.tiny.tac.TinyInstrFunc;
import code.tiny.tac.operand.TinyOperand;

public class TinyWriteR extends TinyInstr
{
    public TinyWriteR(TinyOperand op)
    {
        this.func = TinyInstrFunc.WRITER;
        this.opOne = op;
    }
}
