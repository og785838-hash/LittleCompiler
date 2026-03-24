package ast.visitor.generic;

import ast.ASTNode;
import ast.ProgramNode;
import ast.decl.*;
import ast.expr.*;
import ast.flow.*;
import ast.stmt.*;

public interface ASTVisitor
{
    default void visit(ASTNode node)
    {
        if (node instanceof ProgramNode)
        {
            this.visitProgramNode((ProgramNode) node);
        }
        else if (node instanceof DeclNode)
        {
            this.visitDeclNode((DeclNode) node);
        }
        else if (node instanceof StmtNode)
        {
            this.visitStmtNode((StmtNode) node);
        }
        else if (node instanceof FuncDeclNode)
        {
            this.visitFuncDeclNode((FuncDeclNode) node);
        }
        else if (node instanceof ParamNode)
        {
            this.visitParamNode((ParamNode) node);
        }
        else if (node instanceof CondNode)
        {
            this.visitCondNode((CondNode) node);
        }
        else if (node instanceof ExprNode)
        {
            this.visitExprNode((ExprNode) node);
        }
    }

    default void visitProgramNode(ProgramNode node) {}
    default void visitDeclNode(DeclNode node) {}
    default void visitVarDeclNode(VarDeclNode node) {}
    default void visitStrDeclNode(StrDeclNode node) {}
    default void visitStmtNode(StmtNode node) {}
    default void visitFuncDeclNode(FuncDeclNode node) {}
    default void visitParamNode(ParamNode node) {}
    default void visitBranchNode(BranchNode node) {}
    default void visitIfNode(IfNode node) {}
    default void visitElseNode(ElseNode node) {}
    default void visitWhileNode(WhileNode node) {}
    default void visitCondNode(CondNode node) {}
    default void visitReadNode(ReadNode node) {}
    default void visitWriteNode(WriteNode node) {}
    default void visitReturnNode(ReturnNode node) {}
    default void visitBreakNode(BreakNode node) {}
    default void visitContNode(ContNode node) {}
    default void visitAssignNode(AssignNode node) {}
    default void visitExprNode(ExprNode node) {}
    default void visitBinExprNode(ExprNode node) {}
    default void visitCallNode(CallNode node) {}
    default void visitFloatNode(FloatNode node) {}
    default void visitIntNode(IntNode node) {}
    default void visitVarRefNode(VarRefNode node) {}
}