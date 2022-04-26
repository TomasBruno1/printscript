package AST.Expression;

import AST.Node.NodeException;

public interface ExpressionVisitor {
    void visitExpression(Expression expression) throws NodeException;

    void visitVariable(Variable variable) throws NodeException;

    void visitReadInput(ReadInput readInput) throws NodeException;
}
