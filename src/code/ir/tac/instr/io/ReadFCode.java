package code.ir.tac.instr.io;

import code.ir.tac.AddressCode;
import code.ir.tac.InstrFunc;
import code.ir.tac.operand.Operand;

public class ReadFCode extends AddressCode
{
    public ReadFCode(Operand floatOperand)
    {
        this.func = InstrFunc.READF;
        this.opOne = floatOperand;
    }
}
