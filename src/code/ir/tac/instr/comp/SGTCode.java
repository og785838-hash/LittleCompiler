package code.ir.tac.instr.comp;
import code.ir.tac.AddressCode;
import code.ir.tac.InstrFunc;
import code.ir.tac.operand.Operand;
import code.ir.tac.operand.Register;

public class SGTCode extends AddressCode
{
    public SGTCode(Operand opOne, Operand opTwo)
    {
        this.func = InstrFunc.SGT;
        this.opOne = opOne;
        this.opTwo = opTwo;
        this.result = new Register();
    }

    public SGTCode(Operand opOne, Operand opTwo, Operand resOp)
    {
        this.func = InstrFunc.SGT;
        this.opOne = opOne;
        this.opTwo = opTwo;
        this.result = resOp;
    }
}
