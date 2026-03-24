package code.tiny.tac.instr.io;

import code.tiny.tac.TinyInstr;
import code.tiny.tac.TinyInstrFunc;

public class TinyHalt extends TinyInstr
{
    public TinyHalt()
    {
        this.func = TinyInstrFunc.HALT;
    }
}
