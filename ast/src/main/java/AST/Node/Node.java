package AST.Node;

public interface Node {
    void accept(NodeVisitor visitor);
}
