package Interpreter;

import AST.Node.CodeBlock;

public class Interpreter {

    public Writer interpret(CodeBlock codeBlock) {

        InterpreterVisitor visitor = new InterpreterVisitor();
        codeBlock.accept(visitor);
        return visitor.getResult();

    }

}
