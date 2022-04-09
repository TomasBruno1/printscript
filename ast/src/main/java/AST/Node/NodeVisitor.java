package AST.Node;

public interface NodeVisitor {
    void visit(CodeBlock codeBlock);

    void visit(Declaration declaration);

    void visit(Assignment assignment);

    void visit(Print print);
}
