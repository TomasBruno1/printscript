package AST.Expression;

public interface ExpressionVisitor {
    void visitExpression(Expression expression);

    void visitVariable(Variable variable);
}
