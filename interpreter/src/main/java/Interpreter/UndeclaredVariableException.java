package Interpreter;

import AST.Node.NodeException;

public class UndeclaredVariableException extends NodeException {
    public UndeclaredVariableException(String name) {
        super("Undeclared variable: " + name);
    }
}
