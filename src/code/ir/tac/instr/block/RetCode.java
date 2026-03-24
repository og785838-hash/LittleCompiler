package code.ir.tac.instr.block;

import code.ir.tac.AddressCode;
import code.ir.tac.InstrFunc;
import code.ir.tac.operand.Operand;

public class RetCode extends AddressCode
{
    public RetCode(Operand retOp)
    {
        this.func = InstrFunc.RET;
        this.opOne = retOp;
    }

    public RetCode()
    {
        this.func = InstrFunc.RET;
    }
}