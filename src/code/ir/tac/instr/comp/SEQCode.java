package code.ir.tac.instr.comp;
import code.ir.tac.AddressCode;
import code.ir.tac.InstrFunc;
import code.ir.tac.operand.Operand;
import code.ir.tac.operand.Register;

public class SEQCode extends AddressCode
{
    public SEQCode(Operand opOne, Operand opTwo)
    {
        this.func = InstrFunc.SEQ;
        this.opOne = opOne;
        this.opTwo = opTwo;
        this.result = new Register();
    }

    public SEQCode(Operand opOne, Operand opTwo, Operand resOp)
    {
        this.func = InstrFunc.SEQ;
        this.opOne = opOne;
        this.opTwo = opTwo;
        this.result = resOp;
    }
}
