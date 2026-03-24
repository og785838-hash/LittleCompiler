package resolve;

import ast.ProgramNode;
import ast.decl.*;
import ast.flow.BranchNode;
import ast.flow.ElseNode;
import ast.flow.IfNode;
import ast.flow.WhileNode;
import ast.stmt.StmtNode;
import ast.visitor.generic.ASTVisitor;
import ast.Type;

import java.util.*;

public class SymbolTableBuilder implements ASTVisitor
{
    public TableMap tables;
    private Stack<Integer> scope;
    private int numScopes;
    private int numBlocks;

    public SymbolTableBuilder(ProgramNode prog)
    {
        this.build(prog);
    }

    public void build(ProgramNode prog)
    {
        this.tables = new TableMap();
        this.scope = new Stack<>();
        this.numScopes = 0;
        this.numBlocks = 0;
        this.visit(prog);
    }

    // VISITOR METHODS
    @Override
    public void visitProgramNode(ProgramNode node)
    {
        SymbolTable table = new SymbolTable("GLOBAL");

        this.scope.push(this.numScopes);
        this.addTable(table);
        this.numScopes++;

        // DECLARATIONS
        for (DeclNode d : node.declList)
        {
            this.visit(d);
        }

        // FUNCTION DECLARATIONS
        for (FuncDeclNode f : node.funcDeclList)
        {
            this.visit(f);
        }

        this.scope.pop();
    }

    @Override
    public void visitDeclNode(DeclNode node)
    {
        if (node instanceof VarDeclNode)
        {
            this.visitVarDeclNode((VarDeclNode) node);
        }
        else if (node instanceof StrDeclNode)
        {
            this.visitStrDeclNode((StrDeclNode) node);
        }
    }

    @Override
    public void visitVarDeclNode(VarDeclNode node)
    {
        for (String id : node.idList)
        {
            Symbol var = new Symbol();
            var.meta = Type.Meta.EVAL;
            var.type = node.type;
            var.id = id;

            this.addSymbol(var);
        }
    }

    @Override
    public void visitStrDeclNode(StrDeclNode node)
    {
        Symbol str = new Symbol();
        str.meta = Type.Meta.EVAL;
        str.type = node.type;
        str.id = node.id;
        str.val = node.val;

        this.addSymbol(str);
    }

    @Override
    public void visitFuncDeclNode(FuncDeclNode node)
    {
        // ADD FUNCTION AS REFERENCE SYMBOL TO PARENT SCOPE
        Symbol func = new Symbol();
        func.meta = Type.Meta.BLOCK;
        func.type = node.retType;
        func.id = node.id;

        this.addSymbol(func);

        // CREATE SCOPE
        SymbolTable symbolTable = new SymbolTable(func.id);

        this.scope.push(this.numScopes);
        this.addTable(symbolTable);
        this.numScopes++;

        // PARAMETERS
        for (ParamNode p : node.paramList)
        {
            this.visit(p);
        }

        // DECLARATIONS
        for (DeclNode d : node.declList)
        {
            this.visit(d);
        }

        // STATEMENTS
        for (StmtNode s : node.stmtList)
        {
            this.visit(s);
        }

        this.scope.pop();
    }

    @Override
    public void visitParamNode(ParamNode node)
    {
        Symbol param = new Symbol();
        param.meta = Type.Meta.EVAL;
        param.type = node.type;
        param.id = node.id;

        this.addSymbol(param);
    }

    @Override
    public void visitStmtNode(StmtNode node)
    {
        if (node instanceof BranchNode)
        {
            this.visitBranchNode((BranchNode) node);
        }
        else if (node instanceof WhileNode)
        {
            this.visitWhileNode((WhileNode) node);
        }
    }

    @Override
    public void visitBranchNode(BranchNode node)
    {
        if (node.ifStmt != null)
        {
            this.visit(node.ifStmt);
        }
        if (node.elseStmt != null)
        {
            this.visit(node.elseStmt);
        }
    }

    @Override
    public void visitIfNode(IfNode node)
    {
        // ADD BLOCK AS REFERENCE SYMBOL TO PARENT SCOPE
        Symbol block = new Symbol();

        this.numBlocks++;
        node.nBlock = this.numBlocks;
        String blockName = "BLOCK " + this.numBlocks;

        block.meta = Type.Meta.BLOCK;
        block.type = Type.VOID;
        block.id = blockName;

        this.addSymbol(block);

        // CREATE SCOPE
        SymbolTable table = new SymbolTable(blockName);

        this.scope.push(this.numScopes);
        this.addTable(table);
        this.numScopes++;

        // DECLARATIONS
        for (DeclNode d : node.declList)
        {
            this.visit(d);
        }

        // STATEMENTS
        for (StmtNode s : node.stmtList)
        {
            this.visit(s);
        }

        this.scope.pop();
    }

    @Override
    public void visitElseNode(ElseNode node)
    {
        // ADD BLOCK AS REFERENCE SYMBOL TO PARENT SCOPE
        Symbol block = new Symbol();

        this.numBlocks++;
        node.nBlock = this.numBlocks;
        String blockName = "BLOCK " + this.numBlocks;

        block.meta = Type.Meta.BLOCK;
        block.type = Type.VOID;
        block.id = blockName;

        this.addSymbol(block);

        // CREATE SCOPE
        SymbolTable table = new SymbolTable(blockName);

        this.scope.push(this.numScopes);
        this.addTable(table);
        this.numScopes++;

        // DECLARATIONS
        for (DeclNode d : node.declList)
        {
            this.visit(d);
        }

        // STATEMENTS
        for (StmtNode s : node.stmtList)
        {
            this.visit(s);
        }

        this.scope.pop();
    }

    @Override
    public void visitWhileNode(WhileNode node)
    {
        // ADD BLOCK AS REFERENCE SYMBOL TO PARENT SCOPE
        Symbol block = new Symbol();

        this.numBlocks++;
        node.nBlock = this.numBlocks;
        String blockName = "BLOCK " + this.numBlocks;

        block.meta = Type.Meta.BLOCK;
        block.type = Type.VOID;
        block.id = blockName;

        this.addSymbol(block);

        // CREATE SCOPE
        SymbolTable table = new SymbolTable(blockName);

        this.scope.push(this.numScopes);
        this.addTable(table);
        this.numScopes++;

        // DECLARATIONS
        for (DeclNode d : node.declList)
        {
            this.visit(d);
        }

        // STATEMENTS
        for (StmtNode s : node.stmtList)
        {
            this.visit(s);
        }

        this.scope.pop();
    }

    // AUXILIARY HELPER FUNCTIONS
    private void addTable(SymbolTable table)
    {
        this.tables.put(this.scope.peek(), table);
    }

    private void addSymbol(Symbol symbol)
    {
        this.tables.get(this.scope.peek()).add(symbol);
    }

    public SymbolTable getTable(int scopeIndex)
    {
        return this.tables.get(scopeIndex);
    }

    public SymbolTable getTable(String title)
    {
        return this.tables.get(title);
    }

    // RESOLUTION
    public void resolve()
    {
        this.resolve(new HashSet<>(), this.getTable("GLOBAL"));
    }

    private void resolve(HashSet<Symbol> seen, SymbolTable table)
    {
        seen = new HashSet<>(seen); // Copy seen set to branch - DFS

        for (Symbol s : table.symbols.values())
        {
            // COLLECT SYMBOLS IN CURRENT SCOPE (TOP-DOWN ORDER)
            if (!seen.add(s))
            {
                throw new RuntimeException("SYMBOL RESOLUTION ERROR: Identical variables within the same scope are disallowed.");
            }

            // BRANCH TO FUNC + BLOCK REFERENCES
            if (s.meta == Type.Meta.BLOCK)
            {
                this.resolve(seen, this.getTable(s.id));
            }
        }
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        for (SymbolTable table : this.tables)
        {
            sb.append("\n").append(table.toString()).append("\n");
        }

        return sb.toString();
    }
}
