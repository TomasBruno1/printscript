package AST.Expression;

import AST.Node.Node;

public interface Function extends Node {
    void accept(ExpressionVisitor visitor);

    Function addVariable(Operand operand, Variable variable);
}
