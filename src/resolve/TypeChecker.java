package resolve;

import ast.ProgramNode;
import ast.Type;
import ast.decl.FuncDeclNode;
import ast.expr.*;
import ast.flow.*;
import ast.stmt.*;
import ast.visitor.generic.ASTVisitor;

public class TypeChecker implements ASTVisitor
{
    private TableMap tables;
    private Type currRetType;

    public TypeChecker(ProgramNode prog, TableMap tables)
    {
        this.resolve(prog, tables);
    }

    public void resolve(ProgramNode prog, TableMap tables)
    {
        this.tables = tables;
        this.visit(prog);
    }

    // VISITOR METHODS
    @Override
    public void visitProgramNode(ProgramNode node)
    {
        // FUNCTION DECLARATIONS
        for (FuncDeclNode f : node.funcDeclList)
        {
            this.visit(f);
        }
    }

    @Override
    public void visitFuncDeclNode(FuncDeclNode node)
    {
        // VALIDATE RETURN TYPE
        this.currRetType = node.retType;

        if (this.currRetType != Type.INT && this.currRetType != Type.FLOAT && this.currRetType != Type.VOID)
        {
            throw new RuntimeException("FUNC RESOLUTION ERROR: Return type may only be INT, FLOAT, or VOID.");
        }

        // VALIDATE RETURN STATEMENTS
        for (StmtNode s : node.stmtList)
        {
            if (s instanceof ReturnNode r)
            {
                if (this.getExprType(r.expr) != node.retType)
                {
                    throw new RuntimeException("TYPE RESOLUTION ERROR: Return type mismatch.");
                }
            }
            else
            {
                this.visit(s);
            }
        }
    }

    @Override
    public void visitStmtNode(StmtNode node)
    {
        if (node instanceof ReturnNode)
        {
            this.visitReturnNode((ReturnNode) node);
        }
        else if (node instanceof AssignNode)
        {
            this.visitAssignNode((AssignNode) node);
        }
        else if (node instanceof ReadNode)
        {
            this.visitReadNode((ReadNode) node);
        }
        else if (node instanceof WriteNode)
        {
            this.visitWriteNode((WriteNode) node);
        }
        else if (node instanceof BranchNode)
        {
            this.visitBranchNode((BranchNode) node);
        }
        else if (node instanceof WhileNode)
        {
            this.visitWhileNode((WhileNode) node);
        }
    }

    @Override
    public void visitReturnNode(ReturnNode node)
    {
        Type r = this.getExprType(node.expr);

        if (r != this.currRetType)
        {
            throw new RuntimeException("FUNC RESOLUTION ERROR: Return statement does not match function return type.");
        }
    }

    @Override
    public void visitBranchNode(BranchNode node)
    {
        if (node.ifStmt != null)
        {
            this.visitIfNode(node.ifStmt);
        }
        if (node.elseStmt != null)
        {
            this.visitElseNode(node.elseStmt);
        }
    }

    @Override
    public void visitIfNode(IfNode node)
    {
        // VALIDATE CONDITIONAL
        this.getExprType(node.cond.left);
        this.getExprType(node.cond.right);

        // STATEMENTS
        for (StmtNode s : node.stmtList)
        {
            this.visit(s);
        }
    }

    @Override
    public void visitElseNode(ElseNode node)
    {
        // STATEMENTS
        for (StmtNode s : node.stmtList)
        {
            this.visit(s);
        }
    }

    @Override
    public void visitWhileNode(WhileNode node)
    {
        // VALIDATE CONDITIONAL
        this.getExprType(node.cond.left);
        this.getExprType(node.cond.right);

        // STATEMENTS
        for (StmtNode s : node.stmtList)
        {
            this.visit(s);
        }
    }

    @Override
    public void visitAssignNode(AssignNode node)
    {
        // VARIABLE EXISTENCE + TYPE ALIGNMENT
        Symbol s = this.getSymbol(node.id);

        if (s == null)
        {
            throw new RuntimeException("ASSIGNMENT RESOLUTION ERROR: Referenced variable does not exist.");
        }
        else if (s.type != this.getExprType(node.val))
        {
            throw new RuntimeException("ASSIGNMENT RESOLUTION ERROR: Type mismatch.");
        }
    }

    @Override
    public void visitReadNode(ReadNode node)
    {
        for (String id : node.idList)
        {
            Symbol s = this.getSymbol(id);

            if (s == null)
            {
                throw new RuntimeException("READ CALL RESOLUTION ERROR: Referenced variable does not exist.");
            }
            else if (s.type != Type.INT && s.type != Type.FLOAT)
            {
                throw new RuntimeException("READ CALL RESOLUTION ERROR: Invalid variable types in expression.");
            }
        }
    }

    @Override
    public void visitWriteNode(WriteNode node)
    {
        for (String id : node.idList)
        {
            Symbol s = this.getSymbol(id);

            if (s == null)
            {
                throw new RuntimeException("WRITE CALL RESOLUTION ERROR: Referenced variable does not exist.");
            }
            else if (s.type != Type.INT && s.type != Type.FLOAT && s.type != Type.STRING)
            {
                throw new RuntimeException("WRITE CALL RESOLUTION ERROR: Invalid variable types in expression.");
            }
        }
    }

    // AUXILIARY HELPER FUNCTIONS
    public Type getExprType(ExprNode expr)
    {
        if (expr instanceof BinExprNode binExpr)
        {
            Type left = this.getExprType(binExpr.left);
            Type right = this.getExprType(binExpr.right);

            if (left == Type.FLOAT || right == Type.FLOAT)
            {
                return Type.FLOAT;
            }
            else if (left == Type.INT && right == Type.INT)
            {
                return Type.INT;
            }
        }
        else if (expr instanceof CallNode call)
        {
            Symbol s = this.getSymbol(call.id);

            if (s != null)
            {
                return s.type;
            }
        }
        else if (expr instanceof VarRefNode var)
        {
            Symbol s = this.getSymbol(var.id);

            if (s != null && s.type != Type.STRING)
            {
                return s.type;
            }
        }
        else if (expr instanceof FloatNode)
        {
            return Type.FLOAT;
        }
        else if (expr instanceof IntNode)
        {
            return Type.INT;
        }

        throw new RuntimeException("EXPRESSION RESOLUTION ERROR: Invalid types in expression.");
    }

    public Symbol getSymbol(String id)
    {
        for (SymbolTable tb : this.tables)
        {
            Symbol s = tb.symbols.get(id);

            if (s != null)
            {
                return s;
            }
        }

        return null;
    }
}
