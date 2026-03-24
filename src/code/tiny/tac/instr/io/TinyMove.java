package code.tiny.tac.instr.io;

import code.tiny.tac.TinyInstr;
import code.tiny.tac.TinyInstrFunc;
import code.tiny.tac.operand.TinyOperand;
import code.tiny.tac.operand.TinyRegister;

public class TinyMove extends TinyInstr
{
    public TinyMove(TinyRegister reg, TinyOperand op)
    {
        this.func = TinyInstrFunc.MOVE;
        this.opOne = reg;
        this.opTwo = op;
    }

    public TinyMove(TinyOperand op, TinyRegister reg)
    {
        this.func = TinyInstrFunc.MOVE;
        this.opOne = op;
        this.opTwo = reg;
    }
}
