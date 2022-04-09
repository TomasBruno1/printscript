package Interpreter;

import AST.Node.Node;
import AST.Node.NodeException;

public class Interpreter {

    public Writer run(Node codeBlock) throws NodeException {
        InterpreterVisitor visitor = new InterpreterVisitor();
        codeBlock.accept(visitor);
        return visitor.getResult();
    }

}
