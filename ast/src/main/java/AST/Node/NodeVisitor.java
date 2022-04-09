package AST.Node;

public interface NodeVisitor {
    void visit(CodeBlock codeBlock) throws NodeException;

    void visit(Declaration declaration) throws NodeException;

    void visit(Assignment assignment) throws NodeException;

    void visit(Print print) throws NodeException;
}
