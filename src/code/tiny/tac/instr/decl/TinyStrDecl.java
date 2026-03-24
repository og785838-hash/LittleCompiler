package code.tiny.tac.instr.decl;

import code.tiny.tac.TinyInstr;
import code.tiny.tac.TinyInstrFunc;
import code.tiny.tac.operand.TinyOperand;

public class TinyStrDecl extends TinyInstr
{
    public TinyStrDecl(String id, String val)
    {
        this.func = TinyInstrFunc.STR;
        this.opOne = new TinyOperand(id);
        this.opTwo = new TinyOperand(val);
    }
}
