package code.ir.tac.instr.arith;

import code.ir.tac.AddressCode;
import code.ir.tac.InstrFunc;
import code.ir.tac.operand.Operand;
import code.ir.tac.operand.Register;

public class SubICode extends AddressCode
{
    public SubICode(Operand opOne, Operand opTwo)
    {
        this.func = InstrFunc.SUBI;
        this.opOne = opOne;
        this.opTwo = opTwo;
        this.result = new Register();
    }

    public SubICode(Operand opOne, Operand opTwo, Operand resOp)
    {
        this.func = InstrFunc.SUBI;
        this.opOne = opOne;
        this.opTwo = opTwo;
        this.result = resOp;
    }
}
