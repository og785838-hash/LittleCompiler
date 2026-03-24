package code.ir.tac.instr.block;

import code.ir.tac.AddressCode;
import code.ir.tac.InstrFunc;
import code.ir.tac.operand.Operand;

public class PushCode extends AddressCode
{
    public PushCode(Operand argOp)
    {
        this.func = InstrFunc.PUSH;
        this.opOne = argOp;
    }
}
