package AST.Node;

import AST.Expression.Expression;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Print implements Node {
    Expression content;

    @Override
    public void accept(NodeVisitor visitor) {}
}
