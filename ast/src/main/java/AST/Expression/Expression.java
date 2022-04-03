package AST.Expression;

import AST.Node.Node;
import AST.Node.NodeVisitor;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Expression implements Node {

    String value;

    List<ExpressionOptional> exprOpts;

    @Override
    public void accept(NodeVisitor visitor) {}

    @Override
    public String toString() {
        return "Expression(" + "value='" + value + '\'' + ", exprOpts=" + exprOpts + ')';
    }
}
