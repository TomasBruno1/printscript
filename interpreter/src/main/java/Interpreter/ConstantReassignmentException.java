package Interpreter;

import AST.Node.NodeException;

public class ConstantReassignmentException extends NodeException {
    public ConstantReassignmentException(String name) {
        super("Constant " + name + " cannot be reassigned");
    }
}
