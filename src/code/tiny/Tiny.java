package code.tiny;

import ast.Type;
import code.ir.Code;
import code.ir.tac.AddressCode;
import code.ir.tac.InstrFunc;
import code.ir.tac.operand.Register;
import code.tiny.tac.TinyInstr;
import code.tiny.tac.TinyInstrFunc;
import code.tiny.tac.instr.decl.TinyStrDecl;
import code.tiny.tac.instr.decl.TinyVarDecl;
import code.tiny.tac.instr.io.TinyHalt;
import code.tiny.tac.instr.io.TinySys;
import code.tiny.tac.operand.TinyLabel;
import code.tiny.tac.operand.TinyOperand;
import code.tiny.tac.operand.TinyRegister;
import resolve.Symbol;
import resolve.SymbolTable;
import resolve.TableMap;

import java.util.ArrayList;
import java.util.List;

public class Tiny
{
    public final List<TinyInstr> instrList;

    public Tiny(TableMap map, Code code)
    {
        this.instrList = new ArrayList<>();
        this.generate(map, code);
    }

    private void generate(TableMap map, Code code)
    {
        for (SymbolTable tb : map)
        {
            for (Symbol sym : tb.symbols.values())
            {
                if (sym.type == Type.INT || sym.type == Type.FLOAT)
                {
                    this.instrList.add(new TinyVarDecl(sym.id));
                }
                else if (sym.type == Type.STRING)
                {
                    this.instrList.add(new TinyStrDecl(sym.id, sym.val));
                }
            }
        }

        for (AddressCode line : code.instr)
        {
            this.translate(line);
        }

        this.instrList.add(new TinySys(new TinyHalt()));
    }

    private void translate(AddressCode line)
    {
        if (line.func == InstrFunc.LABEL)
        {
//            TinyInstr instr = new TinyInstr();
//
//            instr.func = TinyInstrFunc.LABEL;
//            instr.opOne = new TinyLabel(line.opOne.toString());
//
//            this.instrList.add(instr);
        }
        else if (line.func == InstrFunc.STOREI || line.func == InstrFunc.STOREF)
        {
            TinyInstr instr = new TinyInstr();

            instr.func = TinyInstrFunc.MOVE;

            if (line.opOne instanceof Register reg)
            {
                instr.opOne = new TinyRegister(reg.num - 1);
                instr.opTwo = new TinyOperand(line.result.ref);
            }
            else
            {
                instr.opOne = new TinyOperand(line.opOne.ref);
                instr.opTwo = new TinyRegister(((Register) line.result).num - 1);
            }

            this.instrList.add(instr);
        }
        else if (line.func == InstrFunc.READI)
        {
            TinyInstr instr = new TinyInstr();

            instr.func = TinyInstrFunc.READI;
            instr.opOne = new TinyOperand(line.opOne.ref);

            TinySys sys = new TinySys(instr);

            this.instrList.add(sys);
        }
        else if (line.func == InstrFunc.READF)
        {
            TinyInstr instr = new TinyInstr();

            instr.func = TinyInstrFunc.READR;
            instr.opOne = new TinyOperand(line.opOne.ref);

            TinySys sys = new TinySys(instr);

            this.instrList.add(sys);
        }
        else if (line.func == InstrFunc.WRITEI)
        {
            TinyInstr instr = new TinyInstr();

            instr.func = TinyInstrFunc.WRITEI;
            instr.opOne = new TinyOperand(line.opOne.ref);

            TinySys sys = new TinySys(instr);

            this.instrList.add(sys);
        }
        else if (line.func == InstrFunc.WRITEF)
        {
            TinyInstr instr = new TinyInstr();

            instr.func = TinyInstrFunc.WRITER;
            instr.opOne = new TinyOperand(line.opOne.ref);

            TinySys sys = new TinySys(instr);

            this.instrList.add(sys);
        }
        else if (line.func == InstrFunc.WRITES)
        {
            TinyInstr instr = new TinyInstr();

            instr.func = TinyInstrFunc.WRITES;
            instr.opOne = new TinyOperand(line.opOne.ref);

            TinySys sys = new TinySys(instr);

            this.instrList.add(sys);
        }
        else if (line.func == InstrFunc.ADDI)
        {
            TinyInstr moveInstr = new TinyInstr();

            moveInstr.func = TinyInstrFunc.MOVE;
            moveInstr.opOne = new TinyOperand(line.opOne.ref);
            moveInstr.opTwo = new TinyRegister(((Register) line.result).num - 1);

            TinyInstr arithInstr = new TinyInstr();

            arithInstr.func = TinyInstrFunc.ADDI;

            if (line.opTwo instanceof Register reg)
            {
                arithInstr.opOne = new TinyRegister(reg.num - 1);
            }
            else
            {
                arithInstr.opOne = new TinyOperand(line.opTwo.ref);
            }

            arithInstr.opTwo = new TinyRegister(((Register) line.result).num - 1);

            this.instrList.add(moveInstr);
            this.instrList.add(arithInstr);
        }
        else if (line.func == InstrFunc.ADDF)
        {
            TinyInstr moveInstr = new TinyInstr();

            moveInstr.func = TinyInstrFunc.MOVE;
            moveInstr.opOne = new TinyOperand(line.opOne.ref);
            moveInstr.opTwo = new TinyRegister(((Register) line.result).num - 1);

            TinyInstr arithInstr = new TinyInstr();

            arithInstr.func = TinyInstrFunc.ADDR;

            if (line.opTwo instanceof Register reg)
            {
                arithInstr.opOne = new TinyRegister(reg.num - 1);
            }
            else
            {
                arithInstr.opOne = new TinyOperand(line.opTwo.ref);
            }

            arithInstr.opTwo = new TinyRegister(((Register) line.result).num - 1);

            this.instrList.add(moveInstr);
            this.instrList.add(arithInstr);
        }
        else if (line.func == InstrFunc.SUBI)
        {
            TinyInstr moveInstr = new TinyInstr();

            moveInstr.func = TinyInstrFunc.MOVE;
            moveInstr.opOne = new TinyOperand(line.opOne.ref);
            moveInstr.opTwo = new TinyRegister(((Register) line.result).num - 1);

            TinyInstr arithInstr = new TinyInstr();

            arithInstr.func = TinyInstrFunc.SUBI;

            if (line.opTwo instanceof Register reg)
            {
                arithInstr.opOne = new TinyRegister(reg.num - 1);
            }
            else
            {
                arithInstr.opOne = new TinyOperand(line.opTwo.ref);
            }

            arithInstr.opTwo = new TinyRegister(((Register) line.result).num - 1);

            this.instrList.add(moveInstr);
            this.instrList.add(arithInstr);
        }
        else if (line.func == InstrFunc.SUBF)
        {
            TinyInstr moveInstr = new TinyInstr();

            moveInstr.func = TinyInstrFunc.MOVE;
            moveInstr.opOne = new TinyOperand(line.opOne.ref);
            moveInstr.opTwo = new TinyRegister(((Register) line.result).num - 1);

            TinyInstr arithInstr = new TinyInstr();

            arithInstr.func = TinyInstrFunc.SUBR;

            if (line.opTwo instanceof Register reg)
            {
                arithInstr.opOne = new TinyRegister(reg.num - 1);
            }
            else
            {
                arithInstr.opOne = new TinyOperand(line.opTwo.ref);
            }

            arithInstr.opTwo = new TinyRegister(((Register) line.result).num - 1);

            this.instrList.add(moveInstr);
            this.instrList.add(arithInstr);
        }
        else if (line.func == InstrFunc.MULTI)
        {
            TinyInstr moveInstr = new TinyInstr();

            moveInstr.func = TinyInstrFunc.MOVE;
            moveInstr.opOne = new TinyOperand(line.opOne.ref);
            moveInstr.opTwo = new TinyRegister(((Register) line.result).num - 1);

            TinyInstr arithInstr = new TinyInstr();

            arithInstr.func = TinyInstrFunc.MULI;

            if (line.opTwo instanceof Register reg)
            {
                arithInstr.opOne = new TinyRegister(reg.num - 1);
            }
            else
            {
                arithInstr.opOne = new TinyOperand(line.opTwo.ref);
            }

            arithInstr.opTwo = new TinyRegister(((Register) line.result).num - 1);

            this.instrList.add(moveInstr);
            this.instrList.add(arithInstr);
        }
        else if (line.func == InstrFunc.MULTF)
        {
            TinyInstr moveInstr = new TinyInstr();

            moveInstr.func = TinyInstrFunc.MOVE;
            moveInstr.opOne = new TinyOperand(line.opOne.ref);
            moveInstr.opTwo = new TinyRegister(((Register) line.result).num - 1);

            TinyInstr arithInstr = new TinyInstr();

            arithInstr.func = TinyInstrFunc.MULR;

            if (line.opTwo instanceof Register reg)
            {
                arithInstr.opOne = new TinyRegister(reg.num - 1);
            }
            else
            {
                arithInstr.opOne = new TinyOperand(line.opTwo.ref);
            }

            arithInstr.opTwo = new TinyRegister(((Register) line.result).num - 1);

            this.instrList.add(moveInstr);
            this.instrList.add(arithInstr);
        }
        else if (line.func == InstrFunc.DIVI)
        {
            TinyInstr moveInstr = new TinyInstr();

            moveInstr.func = TinyInstrFunc.MOVE;
            moveInstr.opOne = new TinyOperand(line.opOne.ref);
            moveInstr.opTwo = new TinyRegister(((Register) line.result).num - 1);

            TinyInstr arithInstr = new TinyInstr();

            arithInstr.func = TinyInstrFunc.DIVI;

            if (line.opTwo instanceof Register reg)
            {
                arithInstr.opOne = new TinyRegister(reg.num - 1);
            }
            else
            {
                arithInstr.opOne = new TinyOperand(line.opTwo.ref);
            }

            arithInstr.opTwo = new TinyRegister(((Register) line.result).num - 1);

            this.instrList.add(moveInstr);
            this.instrList.add(arithInstr);
        }
        else if (line.func == InstrFunc.DIVF)
        {
            TinyInstr moveInstr = new TinyInstr();

            moveInstr.func = TinyInstrFunc.MOVE;
            moveInstr.opOne = new TinyOperand(line.opOne.ref);
            moveInstr.opTwo = new TinyRegister(((Register) line.result).num - 1);

            TinyInstr arithInstr = new TinyInstr();

            arithInstr.func = TinyInstrFunc.DIVR;

            if (line.opTwo instanceof Register reg)
            {
                arithInstr.opOne = new TinyRegister(reg.num - 1);
            }
            else
            {
                arithInstr.opOne = new TinyOperand(line.opTwo.ref);
            }

            arithInstr.opTwo = new TinyRegister(((Register) line.result).num - 1);

            this.instrList.add(moveInstr);
            this.instrList.add(arithInstr);
        }
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        for (TinyInstr instr : this.instrList)
        {
            sb.append(instr.toString()).append("\n");
        }

        return sb.toString();
    }
}
