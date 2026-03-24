package code.ir.tac.instr.io;

import code.ir.tac.AddressCode;
import code.ir.tac.InstrFunc;
import code.ir.tac.operand.Operand;

public class ReadICode extends AddressCode
{
    public ReadICode(Operand intOperand)
    {
        this.func = InstrFunc.READI;
        this.opOne = intOperand;
    }
}
