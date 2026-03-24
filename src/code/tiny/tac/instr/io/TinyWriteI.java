package code.tiny.tac.instr.io;

import code.tiny.tac.TinyInstr;
import code.tiny.tac.TinyInstrFunc;
import code.tiny.tac.operand.TinyOperand;

public class TinyWriteI extends TinyInstr
{
    public TinyWriteI(TinyOperand op)
    {
        this.func = TinyInstrFunc.WRITEI;
        this.opOne = op;
    }
}
