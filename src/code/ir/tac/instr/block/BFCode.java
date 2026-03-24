package code.ir.tac.instr.block;

import code.ir.tac.AddressCode;
import code.ir.tac.InstrFunc;
import code.ir.tac.operand.Label;
import code.ir.tac.operand.Operand;

public class BFCode extends AddressCode
{
    public BFCode(Operand cond, Label label)
    {
        this.func = InstrFunc.BF;
        this.opOne = cond;
        this.opTwo = label;
    }
}
