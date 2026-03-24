package code.ir.tac.instr.arith;

import code.ir.tac.AddressCode;
import code.ir.tac.InstrFunc;
import code.ir.tac.operand.Operand;
import code.ir.tac.operand.Register;

public class MultICode extends AddressCode
{
    public MultICode(Operand opOne, Operand opTwo)
    {
        this.func = InstrFunc.MULTI;
        this.opOne = opOne;
        this.opTwo = opTwo;
        this.result = new Register();
    }

    public MultICode(Operand opOne, Operand opTwo, Operand resOp)
    {
        this.func = InstrFunc.MULTI;
        this.opOne = opOne;
        this.opTwo = opTwo;
        this.result = resOp;
    }
}
