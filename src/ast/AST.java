package ast;

import ast.decl.*;
import ast.expr.*;
import ast.flow.*;
import ast.stmt.*;
import gen.LittleBaseVisitor;
import gen.LittleParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class AST extends LittleBaseVisitor<ASTNode>
{
    @Override
    public ASTNode visitProgram(LittleParser.ProgramContext ctx)
    {
        ProgramNode prog = new ProgramNode();

        // PROGRAM ID
        prog.id = ctx.id().getText();

        // GLOBAL DECLARATIONS
        LittleParser.DeclContext decl = ctx.pgm_body().decl();

        while (decl.getChildCount() != 0)
        {
            prog.declList.add((DeclNode) visit(decl));
            decl = decl.decl();
        }

        // FUNCTION DECLARATIONS
        LittleParser.Func_declarationsContext funcDeclList = ctx.pgm_body().func_declarations();

        while (funcDeclList.getChildCount() != 0)
        {
            LittleParser.Func_declContext funcDecl = funcDeclList.func_decl();
            funcDeclList = funcDeclList.func_declarations();

            prog.funcDeclList.add((FuncDeclNode) visit(funcDecl));
        }

        return prog;
    }

    @Override
    public ASTNode visitDecl(LittleParser.DeclContext ctx)
    {
        if (ctx.string_decl() != null)
        {
            return visit(ctx.string_decl());
        }
        if (ctx.var_decl() != null)
        {
            return visit(ctx.var_decl());
        }

        return null;
    }

    @Override
    public ASTNode visitString_decl(LittleParser.String_declContext ctx)
    {
        StrDeclNode str = new StrDeclNode();
        str.type = Type.STRING;
        str.id = ctx.id().getText();
        str.val = ctx.str().getText();

        return str;
    }

    @Override
    public ASTNode visitVar_decl(LittleParser.Var_declContext ctx)
    {
        VarDeclNode var = new VarDeclNode();
        var.type = Type.get(ctx.var_type().getText());

        // VARIABLE DECLARATIONS
        var.idList.add(ctx.id_list().id().getText());
        LittleParser.Id_tailContext idTail = ctx.id_list().id_tail();

        while (idTail.getChildCount() != 0)
        {
            var.idList.add(idTail.id().getText());
            idTail = idTail.id_tail();
        }

        return var;
    }

    @Override
    public ASTNode visitFunc_decl(LittleParser.Func_declContext ctx)
    {
        FuncDeclNode func = new FuncDeclNode();
        func.retType = Type.get(ctx.any_type().getText());
        func.id = ctx.id().getText();

        // PARAMETERS
        LittleParser.Param_decl_listContext paramDeclList = ctx.param_decl_list();

        if (paramDeclList.getChildCount() != 0)
        {
            func.paramList.add((ParamNode) visit(paramDeclList.param_decl()));
            LittleParser.Param_decl_tailContext paramDeclTail = paramDeclList.param_decl_tail();

            while (paramDeclTail.getChildCount() != 0)
            {
                func.paramList.add((ParamNode) visit(paramDeclTail.param_decl()));
                paramDeclTail = paramDeclTail.param_decl_tail();
            }
        }

        // DECLARATIONS
        LittleParser.DeclContext decl = ctx.func_body().decl();

        while (decl.getChildCount() != 0)
        {
            func.declList.add((DeclNode) visit(decl));
            decl = decl.decl();
        }

        // STATEMENTS
        LittleParser.Stmt_listContext stmtList = ctx.func_body().stmt_list();

        while (stmtList.getChildCount() != 0)
        {
            func.stmtList.add((StmtNode) visit(stmtList.stmt()));
            stmtList = stmtList.stmt_list();
        }

        return func;
    }

    @Override
    public ASTNode visitStmt(LittleParser.StmtContext ctx)
    {
        if (ctx.base_stmt() != null)
        {
            return visit(ctx.base_stmt());
        }
        if (ctx.if_stmt() != null)
        {
            return visit(ctx.if_stmt());
        }
        if (ctx.while_stmt() != null)
        {
            return visit(ctx.while_stmt());
        }

        return null;
    }

    @Override
    public ASTNode visitBase_stmt(LittleParser.Base_stmtContext ctx)
    {
        if (ctx.assign_stmt() != null)
        {
            return visit(ctx.assign_stmt());
        }
        if (ctx.read_stmt() != null)
        {
            return visit(ctx.read_stmt());
        }
        if (ctx.write_stmt() != null)
        {
            return visit(ctx.write_stmt());
        }
        if (ctx.return_stmt() != null)
        {
            return visit(ctx.return_stmt());
        }

        return null;
    }

    @Override
    public ASTNode visitAssign_stmt(LittleParser.Assign_stmtContext ctx)
    {
        AssignNode assign = new AssignNode();
        assign.id = ctx.assign_expr().id().getText();
        assign.op = Operator.ASSIGN;
        assign.val = (ExprNode) visit(ctx.assign_expr().expr());

        return assign;
    }

    @Override
    public ASTNode visitRead_stmt(LittleParser.Read_stmtContext ctx)
    {
        ReadNode read = new ReadNode();
        read.idList.add(ctx.id_list().id().getText());

        // ID LIST
        LittleParser.Id_tailContext idTail = ctx.id_list().id_tail();

        while (idTail.getChildCount() != 0)
        {
            read.idList.add(idTail.id().getText());
            idTail = idTail.id_tail();
        }

        return read;
    }

    @Override
    public ASTNode visitWrite_stmt(LittleParser.Write_stmtContext ctx)
    {
        WriteNode write = new WriteNode();
        write.idList.add(ctx.id_list().id().getText());

        // ID LIST
        LittleParser.Id_tailContext idTail = ctx.id_list().id_tail();

        while (idTail.getChildCount() != 0)
        {
            write.idList.add(idTail.id().getText());
            idTail = idTail.id_tail();
        }

        return write;
    }

    @Override
    public ASTNode visitReturn_stmt(LittleParser.Return_stmtContext ctx)
    {
        ReturnNode ret = new ReturnNode();
        ret.expr = (ExprNode) visit(ctx.expr());
        return ret;
    }

    @Override
    public ASTNode visitBreak_stmt(LittleParser.Break_stmtContext ctx)
    {
        return new BreakNode();
    }

    @Override
    public ASTNode visitContinue_stmt(LittleParser.Continue_stmtContext ctx)
    {
        return new ContNode();
    }

    @Override
    public ASTNode visitIf_stmt(LittleParser.If_stmtContext ctx)
    {
        BranchNode branch = new BranchNode();

        // IF-PART
        IfNode ifStmt = new IfNode();

        // CONDITIONAL
        ifStmt.cond = (CondNode) visit(ctx.cond());

        // DECLARATIONS
        LittleParser.DeclContext decl = ctx.decl();

        while (decl.getChildCount() != 0)
        {
            ifStmt.declList.add((DeclNode) visit(decl));
            decl = decl.decl();
        }

        // STATEMENTS
        LittleParser.Stmt_listContext stmtList = ctx.stmt_list();

        while (stmtList.getChildCount() != 0)
        {
            ifStmt.stmtList.add((StmtNode) visit(stmtList.stmt()));
            stmtList = stmtList.stmt_list();
        }

        branch.ifStmt = ifStmt;

        // ELSE-PART
        if (ctx.else_part() != null)
        {
            branch.elseStmt = (ElseNode) visit(ctx.else_part());
        }

        return branch;
    }

    @Override
    public ASTNode visitElse_part(LittleParser.Else_partContext ctx)
    {
        ElseNode elseStmt = new ElseNode();

        // DECLARATIONS
        LittleParser.DeclContext decl = ctx.decl();

        while (decl.getChildCount() != 0)
        {
            elseStmt.declList.add((DeclNode) visit(decl));
            decl = decl.decl();
        }

        // STATEMENTS
        LittleParser.Stmt_listContext stmtList = ctx.stmt_list();

        while (stmtList.getChildCount() != 0)
        {
            elseStmt.stmtList.add((StmtNode) visit(stmtList.stmt()));
            stmtList = stmtList.stmt_list();
        }

        return elseStmt;
    }

    @Override
    public ASTNode visitWhile_stmt(LittleParser.While_stmtContext ctx)
    {
        WhileNode whileStmt = new WhileNode();

        // CONDITIONAL
        whileStmt.cond = (CondNode) visit(ctx.cond());

        // DECLARATIONS
        LittleParser.DeclContext decl = ctx.decl();

        while (decl.getChildCount() != 0)
        {
            whileStmt.declList.add((DeclNode) visit(decl));
            decl = decl.decl();
        }

        // STATEMENTS
        LittleParser.Stmt_listContext stmtList = ctx.stmt_list();

        while (stmtList.getChildCount() != 0)
        {
            whileStmt.stmtList.add((StmtNode) visit(stmtList.stmt()));
            stmtList = stmtList.stmt_list();
        }

        return whileStmt;
    }

    @Override
    public ASTNode visitCond(LittleParser.CondContext ctx)
    {
        CondNode cond = new CondNode();
        cond.left = (ExprNode) visit(ctx.expr().getFirst());
        cond.right = (ExprNode) visit(ctx.expr().getLast());
        cond.op = Operator.comparison(ctx.compop().getText());

        return cond;
    }

    @Override
    public ASTNode visitExpr(LittleParser.ExprContext ctx)
    {
        ExprNode right = (ExprNode) visit(ctx.factor());

        if (ctx.expr_prefix().getChildCount() == 0)
        {
            return right;
        }

        BinExprNode binExpr = new BinExprNode();
        binExpr.left = (ExprNode) visit(ctx.expr_prefix());
        binExpr.right = right;
        binExpr.op = Operator.arithmetic(ctx.expr_prefix().addop().getText());

        return binExpr;
    }

    @Override
    public ASTNode visitExpr_prefix(LittleParser.Expr_prefixContext ctx)
    {
        if (ctx.getChildCount() != 0)
        {
            ExprNode right = (ExprNode) visit(ctx.factor());

            if (ctx.expr_prefix().getChildCount() == 0)
            {
                return right;
            }

            BinExprNode binExpr = new BinExprNode();
            binExpr.left = (ExprNode) visit(ctx.expr_prefix());
            binExpr.right = right;
            binExpr.op = Operator.arithmetic(ctx.expr_prefix().addop().getText());

            return binExpr;
        }

        return null;
    }

    @Override
    public ASTNode visitFactor(LittleParser.FactorContext ctx)
    {
        ExprNode right = (ExprNode) visit(ctx.postfix_expr());

        if (ctx.factor_prefix().getChildCount() == 0)
        {
            return right;
        }

        BinExprNode binExpr = new BinExprNode();
        binExpr.left = (ExprNode) visit(ctx.factor_prefix());
        binExpr.right = right;
        binExpr.op = Operator.arithmetic(ctx.factor_prefix().mulop().getText());

        return binExpr;
    }

    @Override
    public ASTNode visitFactor_prefix(LittleParser.Factor_prefixContext ctx)
    {
        if (ctx.getChildCount() != 0)
        {
            ExprNode right = (ExprNode) visit(ctx.postfix_expr());

            if (ctx.factor_prefix().getChildCount() == 0)
            {
                return right;
            }

            BinExprNode binExpr = new BinExprNode();
            binExpr.left = (ExprNode) visit(ctx.factor_prefix());
            binExpr.right = right;
            binExpr.op = Operator.arithmetic(ctx.factor_prefix().mulop().getText());

            return binExpr;
        }

        return null;
    }

    @Override
    public ASTNode visitPostfix_expr(LittleParser.Postfix_exprContext ctx)
    {
        if (ctx.primary() != null)
        {
            return visit(ctx.primary());
        }
        if (ctx.call_expr() != null)
        {
            return visit(ctx.call_expr());
        }

        return null;
    }

    @Override
    public ASTNode visitPrimary(LittleParser.PrimaryContext ctx)
    {
        if (ctx.expr() != null)
        {
            return visit(ctx.expr());
        }
        if (ctx.id() != null)
        {
            VarRefNode var = new VarRefNode();
            var.id = ctx.id().getText();
            return var;
        }
        if (ctx.INTLITERAL() != null)
        {
            return new IntNode(ctx.INTLITERAL().getText());
        }
        if (ctx.FLOATLITERAL() != null)
        {
            return new FloatNode(ctx.FLOATLITERAL().getText());
        }

        return null;
    }

    @Override
    public ASTNode visitCall_expr(LittleParser.Call_exprContext ctx)
    {
        CallNode call = new CallNode();

        // FUNCTION NAME
        call.id = ctx.id().getText();

        // ARGUMENTS
        call.args.add((ExprNode) visit(ctx.expr_list().expr()));
        LittleParser.Expr_list_tailContext argTail = ctx.expr_list().expr_list_tail();

        while (argTail.getChildCount() != 0)
        {
            call.args.add((ExprNode) visit(argTail.expr()));
            argTail = argTail.expr_list_tail();
        }

        return call;
    }
}