package code.ir.tac.instr.block;

import code.ir.tac.AddressCode;
import code.ir.tac.InstrFunc;
import code.ir.tac.operand.Label;

public class JumpCode extends AddressCode
{
    public JumpCode(Label label)
    {
        this.func = InstrFunc.JUMP;
        this.opOne = label;
    }
}
