package AST.Expression;

public interface ExpressionVisitor {
    void visitExpression(Expression e);
    void visitExpressionOptional(ExpressionOptional e);
}
