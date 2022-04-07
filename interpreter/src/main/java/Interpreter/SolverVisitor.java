package Interpreter;

import AST.Expression.Expression;
import AST.Expression.ExpressionVisitor;
import AST.Expression.Variable;

public class SolverVisitor implements ExpressionVisitor {
    @Override
    public void visitExpression(Expression e) {
    }

    @Override
    public void visitVariable(Variable variable) {
    }

}
