package code.tiny.tac.instr.io;

import code.tiny.tac.TinyInstr;
import code.tiny.tac.TinyInstrFunc;
import code.tiny.tac.operand.TinyOperand;

public class TinyWriteS extends TinyInstr
{
    public TinyWriteS(TinyOperand op)
    {
        this.func = TinyInstrFunc.WRITES;
        this.opOne = op;
    }
}
