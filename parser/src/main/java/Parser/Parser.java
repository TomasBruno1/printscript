package Parser;

import AST.Node.Node;

public interface Parser<T extends Node> {
    T parse();
}
