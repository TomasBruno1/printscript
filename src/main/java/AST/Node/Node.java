package AST.Node;

import org.austral.ingsis.printscript.common.Token;

public interface Node {
    void accept(NodeVisitor visitor);
}
