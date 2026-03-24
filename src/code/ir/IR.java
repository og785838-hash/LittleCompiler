package code.ir;

import ast.Operator;
import ast.ProgramNode;
import ast.Type;
import ast.decl.FuncDeclNode;
import ast.expr.*;
import ast.flow.BranchNode;
import ast.flow.ElseNode;
import ast.flow.IfNode;
import ast.flow.WhileNode;
import ast.stmt.*;
import ast.visitor.param.ASTVisitor;
import code.ir.tac.*;
import code.ir.tac.instr.block.RetCode;
import code.ir.tac.operand.Label;
import code.ir.tac.operand.Operand;
import code.ir.tac.operand.Register;
import resolve.TypeChecker;

import java.util.ArrayList;
import java.util.List;

public class IR implements ASTVisitor<Code>
{
    private final List<Code> labels;
    private final TypeChecker types;
    public Code code;

    public IR(TypeChecker types)
    {
        this.labels = new ArrayList<>();
        this.code = new Code();
        this.types = types;
    }

    public Code generate(ProgramNode prog)
    {
        this.visit(prog);

        for (Code l : this.labels)
        {
            List<String> labels = l.getLabels();

            if (labels.size() != 1)
            {
                throw new RuntimeException("IR GENERATION ERROR: Labels were not validly constructed.");
            }

            this.code.add(l);
        }

        return this.code;
    }

    // VISITOR IMPLEMENTATION
    @Override
    public Code visitProgramNode(ProgramNode prog)
    {
        // FUNCTION DECLARATIONS
        for (FuncDeclNode f : prog.funcDeclList)
        {
            Code fCode = this.visit(f);

            if (fCode != null)
            {
                this.labels.add(fCode);
            }
        }

        return null;
    }

    @Override
    public Code visitFuncDeclNode(FuncDeclNode func)
    {
        Code code = new Code();

        // LABEL
        AddressCode label = new AddressCode();
        label.func = InstrFunc.LABEL;
        label.opOne = new Label(func.id);
        code.add(label);

        // LINK
        AddressCode link = new AddressCode();
        link.func = InstrFunc.LINK;
        code.add(link);

        // STATEMENTS
        for (StmtNode s : func.stmtList)
        {
            Code sCode = this.visit(s);

            if (sCode != null)
            {
                code.add(sCode);
            }
        }

        // RETURN
        code.add(new RetCode());

        return code;
    }

    @Override
    public Code visitStmtNode(StmtNode stmt)
    {
        if (stmt instanceof BranchNode branch)
        {
            return this.visitBranchNode(branch);
        }
        else if (stmt instanceof WhileNode whl)
        {
            return this.visitWhileNode(whl);
        }
        else if (stmt instanceof AssignNode assign)
        {
            return this.visitAssignNode(assign);
        }
        else if (stmt instanceof BreakNode br)
        {
            return this.visitBreakNode(br);
        }
        else if (stmt instanceof ContNode cont)
        {
            return this.visitContNode(cont);
        }
        else if (stmt instanceof ReturnNode ret)
        {
            return this.visitReturnNode(ret);
        }
        else if (stmt instanceof ReadNode read)
        {
            return this.visitReadNode(read);
        }
        else if (stmt instanceof WriteNode write)
        {
            return this.visitWriteNode(write);
        }

        return null;
    }

    @Override
    public Code visitBranchNode(BranchNode node)
    {
        Code code = new Code();
        AddressCode cond = new AddressCode();

        if (node.ifStmt != null)
        {
            IfNode ifNode = node.ifStmt;

            Code block = new Code();

            // LABEL
            AddressCode label = new AddressCode();
            label.func = InstrFunc.LABEL;
            label.opOne = new Label();
            block.add(label);

            // CONDITIONAL
            cond.func = this.getCompFunc(ifNode.cond.op);

            Code lExpr = this.visit(ifNode.cond.left);
            Code rExpr = this.visit(ifNode.cond.right);

            cond.opOne = lExpr.result;
            cond.opTwo = rExpr.result;
            cond.result = new Register();

            AddressCode bTrue = new AddressCode();
            bTrue.func = InstrFunc.BT;
            bTrue.opOne = cond.result;
            bTrue.opTwo = label.opOne;

            code.add(lExpr);
            code.add(rExpr);
            code.add(cond);
            code.add(bTrue);

            // STATEMENTS
            for (StmtNode s : ifNode.stmtList)
            {
                Code sCode = this.visit(s);

                if (sCode != null)
                {
                    block.add(sCode);
                }
            }

            this.labels.add(block);
        }
        if (node.elseStmt != null)
        {
            ElseNode elseNode = node.elseStmt;

            Code block = new Code();

            // LABEL
            AddressCode label = new AddressCode();
            label.func = InstrFunc.LABEL;
            label.opOne = new Operand("B" + elseNode.nBlock);
            block.add(label);

            // CONDITIONAL
            AddressCode bFalse = new AddressCode();
            bFalse.func = InstrFunc.BF;
            bFalse.opOne = cond.result;
            bFalse.opTwo = label.opOne;

            code.add(bFalse);

            // STATEMENTS
            for (StmtNode s : elseNode.stmtList)
            {
                Code sCode = this.visit(s);

                if (sCode != null)
                {
                    block.add(sCode);
                }
            }

            this.labels.add(block);
        }

        return code;
    }

    @Override
    public Code visitWhileNode(WhileNode node)
    {
        Code code = new Code();
        Code block = new Code();

        // LABEL
        AddressCode label = new AddressCode();
        label.func = InstrFunc.LABEL;
        label.opOne = new Operand("B" + node.nBlock);
        block.add(label);

        // CONDITIONAL
        AddressCode cond = new AddressCode();
        cond.func = this.getCompFunc(node.cond.op);

        Code lExpr = this.visit(node.cond.left);
        Code rExpr = this.visit(node.cond.right);

        cond.opOne = lExpr.result;
        cond.opTwo = rExpr.result;
        cond.result = new Register();

        AddressCode bTrue = new AddressCode();
        bTrue.func = InstrFunc.BT;
        bTrue.opOne = cond.result;
        bTrue.opTwo = label.opOne;

        code.add(lExpr);
        code.add(rExpr);
        code.add(cond);
        code.add(bTrue);

        // STATEMENTS
        for (StmtNode s : node.stmtList)
        {
            block.add(this.visit(s));
        }

        block.add(lExpr);
        block.add(rExpr);
        block.add(cond);
        block.add(bTrue);

        this.labels.add(block);

        return code;
    }

    @Override
    public Code visitAssignNode(AssignNode node)
    {
        Code code = new Code();

        Type type = this.types.getSymbol(node.id).type;

        InstrFunc func = null;

        if (type == Type.INT)
        {
            func = InstrFunc.STOREI;
        }
        else if (type == Type.FLOAT)
        {
            func = InstrFunc.STOREF;
        }

        // EXPRESSION
        Code expr = this.visit(node.val);

        AddressCode storeToVar = new AddressCode();
        storeToVar.func = func;
        storeToVar.opOne = expr.result;
        storeToVar.result = new Operand(node.id);

        code.add(expr);
        code.add(storeToVar);

        return code;
    }

    @Override
    public Code visitReadNode(ReadNode node)
    {
        Code code = new Code();

        for (String id : node.idList)
        {
            Type type = this.types.getSymbol(id).type;

            AddressCode read = new AddressCode();

            read.opOne = new Operand(id);

            if (type == Type.INT)
            {
                read.func = InstrFunc.READI;
            }
            else if (type == Type.FLOAT)
            {
                read.func = InstrFunc.READF;
            }

            code.add(read);
        }

        return code;
    }

    @Override
    public Code visitWriteNode(WriteNode node)
    {
        Code code = new Code();

        for (String id : node.idList)
        {
            Type type = this.types.getSymbol(id).type;

            AddressCode write = new AddressCode();

            write.opOne = new Operand(id);

            if (type == Type.INT)
            {
                write.func = InstrFunc.WRITEI;
            }
            else if (type == Type.FLOAT)
            {
                write.func = InstrFunc.WRITEF;
            }
            else if (type == Type.STRING)
            {
                write.func = InstrFunc.WRITES;
            }

            code.add(write);
        }

        return code;
    }

    @Override
    public Code visitExprNode(ExprNode node)
    {
        if (node instanceof BinExprNode bin)
        {
            Code code = new Code();

            Type lType = this.types.getExprType(bin.left);
            Type rType = this.types.getExprType(bin.right);

            AddressCode expr = new AddressCode();

            if (lType == Type.FLOAT || rType == Type.FLOAT)
            {
                expr.func = this.getArithmeticFunc(Type.FLOAT, bin.op);
            }
            else if (lType == Type.INT && rType == Type.INT)
            {
                expr.func = this.getArithmeticFunc(Type.INT, bin.op);
            }
            else
            {
                return null;
            }

            Code lCode = this.visit(bin.left);
            Code rCode = this.visit(bin.right);

            expr.opOne = lCode.result;
            expr.opTwo = rCode.result;

            expr.result = new Register();

            code.add(lCode);
            code.add(rCode);
            code.add(expr);
            code.result = expr.result;

            return code;
        }
        else if (node instanceof CallNode call)
        {
            Code code = new Code();

            // ARGUMENTS
            for (ExprNode arg : call.args)
            {
                Code argExpr = this.visit(arg);

                AddressCode push = new AddressCode();
                push.func = InstrFunc.PUSH;
                push.opOne = argExpr.result;

                code.add(argExpr);
                code.add(push);
            }

            // CALL + RETRIEVAL
            AddressCode run = new AddressCode();
            run.func = InstrFunc.CALL;
            run.opOne = new Operand(call.id);
            code.add(run);

            AddressCode get = new AddressCode();
            get.func = InstrFunc.POP;
            get.result = new Register();
            code.add(get);

            return code;
        }
        else if (node instanceof FloatNode literal)
        {
            Code code = new Code();

            AddressCode store = new AddressCode();
            store.func = InstrFunc.STOREF;
            store.opOne = new Operand(Float.toString(literal.val));
            store.result = new Register();

            code.add(store);
            code.result = store.result;

            return code;
        }
        else if (node instanceof IntNode literal)
        {
            Code code = new Code();

            AddressCode store = new AddressCode();
            store.func = InstrFunc.STOREI;
            store.opOne = new Operand(Integer.toString(literal.val));
            store.result = new Register();

            code.add(store);
            code.result = store.result;

            return code;
        }
        else if (node instanceof VarRefNode ref)
        {
            Code code = new Code();

            /*
            AddressCode store = new AddressCode();

            Type type = this.types.getSymbol(ref.id).type;

            if (type == Type.INT)
            {
                store.func = InstrFunc.STOREI;
            }
            else if (type == Type.FLOAT)
            {
                store.func = InstrFunc.STOREF;
            }

            store.opOne = new Operand(ref.id);
            store.result = new Register();

            code.add(store);
             */
            code.result = new Operand(ref.id);

            return code;
        }

        return null;
    }

    // AUXILIARY HELPER FUNCTIONS
    private InstrFunc getArithmeticFunc(Type type, Operator op)
    {
        if (type == Type.INT)
        {
            return switch (op)
            {
                case Operator.ADD -> InstrFunc.ADDI;
                case Operator.SUB -> InstrFunc.SUBI;
                case Operator.MUL -> InstrFunc.MULTI;
                case Operator.DIV -> InstrFunc.DIVI;
                default -> null;
            };
        }
        else if (type == Type.FLOAT)
        {
            return switch (op)
            {
                case Operator.ADD -> InstrFunc.ADDF;
                case Operator.SUB -> InstrFunc.SUBF;
                case Operator.MUL -> InstrFunc.MULTF;
                case Operator.DIV -> InstrFunc.DIVF;
                default -> null;
            };
        }

        return null;
    }

    private InstrFunc getCompFunc(Operator op)
    {
        return switch (op)
        {
            case Operator.LT -> InstrFunc.SLT;
            case Operator.GT -> InstrFunc.SGT;
            case Operator.LE -> InstrFunc.SLE;
            case Operator.GE -> InstrFunc.SGE;
            case Operator.EQ -> InstrFunc.SEQ;
            case Operator.NEQ -> InstrFunc.SNE;
            default -> null;
        };
    }
}
