package code.ir.tac.instr.block;

import code.ir.tac.AddressCode;
import code.ir.tac.InstrFunc;
import code.ir.tac.operand.Label;

public class LinkCode extends AddressCode
{
    public LinkCode()
    {
        this.func = InstrFunc.LINK;
    }
}
