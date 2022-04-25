package Interpreter;

import AST.Node.Node;
import AST.Node.NodeException;

public class Interpreter {

    public Writer run(Node codeBlock) throws NodeException {
        AbstractInterpreterVisitor visitor = new InterpreterVisitorV1_0();
        codeBlock.accept(visitor);
        return visitor.getResult();
    }

    public Writer run(Node codeBlock, InputProvider inputProvider) throws NodeException {
        AbstractInterpreterVisitor visitor = new InterpreterVisitorV1_1(inputProvider);
        codeBlock.accept(visitor);
        return visitor.getResult();
    }

}
