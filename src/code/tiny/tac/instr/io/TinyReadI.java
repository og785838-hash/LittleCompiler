package code.tiny.tac.instr.io;

import code.tiny.tac.TinyInstr;
import code.tiny.tac.TinyInstrFunc;
import code.tiny.tac.operand.TinyOperand;

public class TinyReadI extends TinyInstr
{
    public TinyReadI(TinyOperand op)
    {
        this.func = TinyInstrFunc.READI;
        this.opOne = op;
    }
}
