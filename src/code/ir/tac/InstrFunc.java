package code.ir.tac;

public enum InstrFunc
{
    ADDI, SUBI, MULTI, DIVI,
    ADDF, SUBF, MULTF, DIVF,
    STOREI, STOREF,
    READI, READF,
    WRITEI, WRITEF, WRITES,
    LABEL, JUMP, BT, BF,
    LINK, PUSH, CALL, POP, RET,
    SLT, SGT, SLE, SGE, SEQ, SNE;
}