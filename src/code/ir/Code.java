package code.ir;

import code.ir.tac.AddressCode;
import code.ir.tac.operand.Label;
import code.ir.tac.operand.Operand;

import java.util.ArrayList;
import java.util.List;

public class Code
{
    public List<AddressCode> instr;
    public Operand result;

    public Code()
    {
        this.instr = new ArrayList<>();
    }

    public void add(Code code)
    {
        this.instr.addAll(code.instr);
    }

    public void add(AddressCode instr)
    {
        this.instr.add(instr);
    }

    public List<String> getLabels()
    {
        List<String> labels = new ArrayList<>();

        for (AddressCode code : this.instr)
        {
            if (code.opOne instanceof Label)
            {
                labels.add(code.opOne.toString());
            }
        }

        return labels;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        for (AddressCode code : this.instr)
        {
            builder.append(code.toString()).append("\n");
        }

        return builder.toString();
    }
}
