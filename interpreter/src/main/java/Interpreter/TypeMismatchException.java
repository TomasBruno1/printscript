package Interpreter;

import AST.Node.NodeException;

public class TypeMismatchException extends NodeException {
    public TypeMismatchException(String name, String type) {
        super("Type mismatch: " + name + " is of type " + type);
    }
}
