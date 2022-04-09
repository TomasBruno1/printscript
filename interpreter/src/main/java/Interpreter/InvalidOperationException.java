package Interpreter;

import AST.Expression.Operand;
import AST.Node.NodeException;

public class InvalidOperationException extends NodeException {
    public InvalidOperationException(String lString, String rString, Operand opString) {
        super("Invalid operation: " + lString + " " + opString.getString() + " " + rString);
    }
}
