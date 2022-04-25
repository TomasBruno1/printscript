package AST.Node;

import AST.Expression.ReadInput;

public interface NodeVisitor {
    void visit(CodeBlock codeBlock) throws NodeException;

    void visit(Declaration declaration) throws NodeException;

    void visit(Assignment assignment) throws NodeException;

    void visit(Print print) throws NodeException;

    void visit(IfBlock ifBlock) throws NodeException;

    void visit(ReadInput readInput) throws NodeException;
}
