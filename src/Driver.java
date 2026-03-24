import ast.AST;
import ast.ProgramNode;
import code.ir.Code;
import code.ir.IR;
import code.tiny.Tiny;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import resolve.SymbolTableBuilder;
import resolve.TypeChecker;
import gen.LittleLexer;
import gen.LittleParser;
import util.File;

public class Driver
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("Hello Compiler Testing!");
        CharStream input = CharStreams.fromStream(System.in);

        LittleLexer lexer = new LittleLexer(input);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        LittleParser parser = new LittleParser(tokenStream);

        ParseTree tree = parser.program();
        ProgramNode prog = (ProgramNode) new AST().visit(tree);
        File.write(prog.toString(),"./src/littleCodeFiles/code.ast");

        SymbolTableBuilder stb = new SymbolTableBuilder(prog);
        TypeChecker tc = new TypeChecker(prog, stb.tables);
        File.write(stb.toString(),"./src/littleCodeFiles/code.sym");

        Code code = new IR(tc).generate(prog);
        File.write(code.toString(),"./src/littleCodeFiles/code.ir");

        Tiny tiny = new Tiny(stb.tables, code);

        StringBuilder sb = new StringBuilder();

        sb.append(";IR code\n;");
        sb.append(code.toString().replaceAll("\n", "\n;"));
        sb.append("tiny code\n");
        sb.append(tiny);

        File.write(sb.toString(), "./src/littleCodeFiles/test.out");
    }
}
