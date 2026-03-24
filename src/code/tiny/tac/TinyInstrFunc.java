package code.tiny.tac;

public enum TinyInstrFunc
{
    VAR("var"), STR("str"),
    LABEL("label"), SYS("sys"),
    MOVE("move"),
    MULI("muli"), DIVI("divi"), ADDI("addi"), SUBI("subi"),
    MULR("mulr"), DIVR("divr"), ADDR("addr"), SUBR("subr"),
    READI("readi"), READR("readr"),
    WRITEI("writei"), WRITER("writer"), WRITES("writes"),
    HALT("halt");

    private final String funcName;

    TinyInstrFunc(String funcName)
    {
        this.funcName = funcName;
    }

    @Override
    public String toString()
    {
        return this.funcName;
    }
}
