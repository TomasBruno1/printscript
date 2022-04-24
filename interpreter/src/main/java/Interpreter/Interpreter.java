package Interpreter;

import AST.Node.Node;
import AST.Node.NodeException;

public class Interpreter {

    public Writer run(Node codeBlock) throws NodeException {
        // TODO: implement version
        AbstractInterpreterVisitor visitor = new InterpreterVisitorV1_1();
        codeBlock.accept(visitor);
        return visitor.getResult();
    }

}
