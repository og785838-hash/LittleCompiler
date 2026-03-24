package code.tiny.tac.instr.arith;
import code.tiny.tac.TinyInstr;
import code.tiny.tac.TinyInstrFunc;
import code.tiny.tac.operand.TinyOperand;
import code.tiny.tac.operand.TinyRegister;

public class TinyDivI extends TinyInstr
{
    public TinyDivI(TinyOperand opOne, TinyRegister reg)
    {
        this.func = TinyInstrFunc.DIVI;
        this.opOne = opOne;
        this.opTwo = reg;
    }
}
