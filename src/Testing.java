import ast.AST;
import ast.ProgramNode;
import code.ir.Code;
import code.tiny.Tiny;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import resolve.SymbolTableBuilder;
import resolve.TypeChecker;
import util.File;
import gen.LittleLexer;
import gen.LittleParser;
import code.ir.IR;

import java.io.IOException;

public class Testing
{
    public static void main(String[] args) throws IOException
    {
        System.out.println("Hello Compiler Testing!");
        String sourceCode = File.read("./src/littleCodeFiles/test3.little");
        CharStream input = CharStreams.fromString(sourceCode);

        LittleLexer lexer = new LittleLexer(input);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        LittleParser parser = new LittleParser(tokenStream);

        ParseTree tree = parser.program();
        ProgramNode prog = (ProgramNode) new AST().visit(tree);
        File.write(prog.toString(),"./src/littleCodeFiles/test.ast");

        SymbolTableBuilder stb = new SymbolTableBuilder(prog);
        TypeChecker tc = new TypeChecker(prog, stb.tables);
        File.write(stb.toString(),"./src/littleCodeFiles/test.sym");

        Code code = new IR(tc).generate(prog);
        File.write(code.toString(),"./src/littleCodeFiles/test.ir");

        Tiny tiny = new Tiny(stb.tables, code);

        StringBuilder sb = new StringBuilder();

        sb.append(";IR code\n;");
        sb.append(code.toString().replaceAll("\n", "\n;"));
        sb.append("tiny code\n");
        sb.append(tiny);

        File.write(sb.toString(), "./src/littleCodeFiles/test.out");
    }
}
