package ast;

public enum Type
{
    INT,
    FLOAT,
    STRING,
    VOID;

    public static Type get(String type)
    {
        return switch(type)
        {
            case "STRING" -> STRING;
            case "INT" -> INT;
            case "FLOAT" -> FLOAT;
            case "VOID" -> VOID;
            default -> null;
        };
    }

    public enum Meta { EVAL, BLOCK }

    @Override
    public String toString()
    {
        return "Type: " + this.name();
    }
}
