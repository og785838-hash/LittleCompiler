package code.ir.tac.instr.block;

import code.ir.tac.AddressCode;
import code.ir.tac.InstrFunc;
import code.ir.tac.operand.Label;
import code.ir.tac.operand.Operand;

public class CallCode extends AddressCode
{
    public CallCode(Label callOp)
    {
        this.func = InstrFunc.CALL;
        this.opOne = callOp;
    }
}
