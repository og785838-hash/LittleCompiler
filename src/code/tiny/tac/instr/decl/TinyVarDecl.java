package code.tiny.tac.instr.decl;

import code.tiny.tac.TinyInstr;
import code.tiny.tac.TinyInstrFunc;
import code.tiny.tac.operand.TinyOperand;

public class TinyVarDecl extends TinyInstr
{
    public TinyVarDecl(String id)
    {
        this.func = TinyInstrFunc.VAR;
        this.opOne = new TinyOperand(id);
    }
}
