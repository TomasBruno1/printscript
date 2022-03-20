package AST.Expression;

import AST.Node.Node;
import AST.Node.NodeVisitor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExpressionOptional implements Node {

    Operand operand;

    String literal;
    String identifier;

    @Override
    public void accept(NodeVisitor visitor) {

    }
}
