package code.tiny.tac.instr.arith;
import code.tiny.tac.TinyInstr;
import code.tiny.tac.TinyInstrFunc;
import code.tiny.tac.operand.TinyOperand;
import code.tiny.tac.operand.TinyRegister;

public class TinySubI extends TinyInstr
{
    public TinySubI(TinyOperand opOne, TinyRegister reg)
    {
        this.func = TinyInstrFunc.SUBI;
        this.opOne = opOne;
        this.opTwo = reg;
    }
}
