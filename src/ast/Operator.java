package ast;

public enum Operator
{
    ADD, SUB, MUL, DIV,
    LT, GT, EQ, NEQ, GE, LE,
    ASSIGN;

    public static Operator arithmetic(String op)
    {
        return switch (op)
        {
            case "+" -> ADD;
            case "-" -> SUB;
            case "*" -> MUL;
            case "/" -> DIV;
            default -> null;
        };
    }

    public static Operator comparison(String op)
    {
        return switch (op)
        {
            case "<" -> LT;
            case ">" -> GT;
            case "=" -> EQ;
            case "!=" -> NEQ;
            case "<=" -> LE;
            case ">=" -> GE;
            default -> null;
        };
    }

    @Override
    public String toString()
    {
        return "Operator: " + this.name();
    }
}
