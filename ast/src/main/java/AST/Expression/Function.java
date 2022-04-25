package AST.Expression;

import AST.Node.Node;
import AST.Node.NodeException;

public interface Function extends Node {
    void accept(ExpressionVisitor visitor) throws NodeException;

    Function addVariable(Operand operand, Function variable);
}
