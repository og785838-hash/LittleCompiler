package code.ir.tac.instr.io;

import code.ir.tac.AddressCode;
import code.ir.tac.InstrFunc;
import code.ir.tac.operand.Operand;
import code.ir.tac.operand.Register;

public class StoreICode extends AddressCode
{
    public StoreICode(Operand intOperand)
    {
        this.func = InstrFunc.STOREI;
        this.opOne = intOperand;
        this.result = new Register();
    }

    public StoreICode(Operand intOperand, Operand resOperand)
    {
        this.func = InstrFunc.STOREI;
        this.opOne = intOperand;
        this.result = resOperand;
    }
}
