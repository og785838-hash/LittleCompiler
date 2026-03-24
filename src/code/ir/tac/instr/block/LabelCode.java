package code.ir.tac.instr.block;

import code.ir.tac.AddressCode;
import code.ir.tac.InstrFunc;
import code.ir.tac.operand.Label;

public class LabelCode extends AddressCode
{
    public LabelCode(Label label)
    {
        this.func = InstrFunc.LABEL;
        this.opOne = label;
    }

    public LabelCode()
    {
        this.func = InstrFunc.LABEL;
        this.opOne = new Label();
    }
}
