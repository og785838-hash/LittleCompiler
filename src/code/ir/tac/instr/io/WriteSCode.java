package code.ir.tac.instr.io;

import code.ir.tac.AddressCode;
import code.ir.tac.InstrFunc;
import code.ir.tac.operand.Operand;

public class WriteSCode extends AddressCode
{
    public WriteSCode(Operand strOperand)
    {
        this.func = InstrFunc.WRITES;
        this.opOne = strOperand;
    }
}
