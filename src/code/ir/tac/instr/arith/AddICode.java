package code.ir.tac.instr.arith;

import code.ir.tac.AddressCode;
import code.ir.tac.InstrFunc;
import code.ir.tac.operand.Operand;
import code.ir.tac.operand.Register;

public class AddICode extends AddressCode
{
    public AddICode(Operand opOne, Operand opTwo)
    {
        this.func = InstrFunc.ADDI;
        this.opOne = opOne;
        this.opTwo = opTwo;
        this.result = new Register();
    }

    public AddICode(Operand opOne, Operand opTwo, Operand resOp)
    {
        this.func = InstrFunc.ADDI;
        this.opOne = opOne;
        this.opTwo = opTwo;
        this.result = resOp;
    }
}
