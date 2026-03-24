package code.ir.tac.instr.io;

import code.ir.tac.AddressCode;
import code.ir.tac.InstrFunc;
import code.ir.tac.operand.Operand;

public class WriteFCode extends AddressCode
{
    public WriteFCode(Operand floatOperand)
    {
        this.func = InstrFunc.WRITEF;
        this.opOne = floatOperand;
    }
}
