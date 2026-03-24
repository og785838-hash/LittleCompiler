package ast.visitor.param;

import ast.ASTNode;
import ast.ProgramNode;
import ast.decl.*;
import ast.expr.*;
import ast.flow.*;
import ast.stmt.*;

public interface ASTVisitor<T>
{
    default T visit(ASTNode node)
    {
        if (node instanceof ProgramNode)
        {
            return this.visitProgramNode((ProgramNode) node);
        }
        else if (node instanceof DeclNode)
        {
            return this.visitDeclNode((DeclNode) node);
        }
        else if (node instanceof StmtNode)
        {
            return this.visitStmtNode((StmtNode) node);
        }
        else if (node instanceof FuncDeclNode)
        {
            return this.visitFuncDeclNode((FuncDeclNode) node);
        }
        else if (node instanceof ParamNode)
        {
            return this.visitParamNode((ParamNode) node);
        }
        else if (node instanceof CondNode)
        {
            return this.visitCondNode((CondNode) node);
        }
        else if (node instanceof ExprNode)
        {
            return this.visitExprNode((ExprNode) node);
        }

        return null;
    }

    default T visitProgramNode(ProgramNode node) { return null; }
    default T visitDeclNode(DeclNode node) { return null; }
    default T visitVarDeclNode(VarDeclNode node) { return null; }
    default T visitStrDeclNode(StrDeclNode node) { return null; }
    default T visitStmtNode(StmtNode node) { return null; }
    default T visitFuncDeclNode(FuncDeclNode node) { return null; }
    default T visitParamNode(ParamNode node) { return null; }
    default T visitBranchNode(BranchNode node) { return null; }
    default T visitIfNode(IfNode node) { return null; }
    default T visitElseNode(ElseNode node) { return null; }
    default T visitWhileNode(WhileNode node) { return null; }
    default T visitCondNode(CondNode node) { return null; }
    default T visitReadNode(ReadNode node) { return null; }
    default T visitWriteNode(WriteNode node) { return null; }
    default T visitReturnNode(ReturnNode node) { return null; }
    default T visitBreakNode(BreakNode node) { return null; }
    default T visitContNode(ContNode node) { return null; }
    default T visitAssignNode(AssignNode node) { return null; }
    default T visitExprNode(ExprNode node) { return null; }
    default T visitBinExprNode(ExprNode node) { return null; }
    default T visitCallNode(CallNode node) { return null; }
    default T visitFloatNode(FloatNode node) { return null; }
    default T visitIntNode(IntNode node) { return null; }
    default T visitVarRefNode(VarRefNode node) { return null; }
}