package AST.Expression;

import AST.Node.Node;
import AST.Node.NodeVisitor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExpressionOptional implements Node {

    Operand operand;

    String value;

    @Override
    public void accept(NodeVisitor visitor) {
    }

    @Override
    public String toString() {
        return "ExpressionOptional(" + "operand=" + operand + ", value='" + value + '\'' + ')';
    }
}
