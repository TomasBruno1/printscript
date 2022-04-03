package AST.Node;

import java.util.ArrayList;
import java.util.List;

public class CodeBlock implements Node {

    private final List<Node> children = new ArrayList<>();

    public void addChild(Node child) {
        children.add(child);
    }

    @Override
    public void accept(NodeVisitor visitor) {
    }

    // toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node child : children) {
            sb.append(child.toString());
        }
        return sb.toString();
    }
}
