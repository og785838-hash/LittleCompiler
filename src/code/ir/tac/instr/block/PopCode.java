package code.ir.tac.instr.block;

import code.ir.tac.AddressCode;
import code.ir.tac.InstrFunc;
import code.ir.tac.operand.Operand;

public class PopCode extends AddressCode
{
    public PopCode(Operand saveOp)
    {
        this.func = InstrFunc.POP;
        this.opOne = saveOp;
    }
}
