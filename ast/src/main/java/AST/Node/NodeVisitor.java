package AST.Node;

public interface NodeVisitor {
    void visit(CodeBlock codeBlock);

    void visit(Declaration declaration);

    void visit(Assignment function);

    void visit(Print print);
}
