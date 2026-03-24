package code.ir.tac.instr.io;

import code.ir.tac.AddressCode;
import code.ir.tac.InstrFunc;
import code.ir.tac.operand.Operand;
import code.ir.tac.operand.Register;

public class StoreFCode extends AddressCode
{
    public StoreFCode(Operand floatOperand)
    {
        this.func = InstrFunc.STOREF;
        this.opOne = floatOperand;
        this.result = new Register();
    }

    public StoreFCode(Operand floatOperand, Operand resOperand)
    {
        this.func = InstrFunc.STOREF;
        this.opOne = floatOperand;
        this.result = resOperand;
    }
}
