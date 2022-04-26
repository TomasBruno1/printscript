package Interpreter;

import AST.Expression.Operand;
import AST.Node.NodeException;

public class BooleanOperationException extends NodeException {
    public BooleanOperationException(String leftResult, String rightResult, Operand operand) {
        super("Boolean operations are not supported: " + leftResult + " " + operand.getString() + " " + rightResult);
    }
}
