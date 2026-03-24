package code.ir.tac.instr.io;

import code.ir.tac.AddressCode;
import code.ir.tac.InstrFunc;
import code.ir.tac.operand.Operand;

public class WriteICode extends AddressCode
{
    public WriteICode(Operand intOperand)
    {
        this.func = InstrFunc.WRITEI;
        this.opOne = intOperand;
    }
}
