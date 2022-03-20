package AST.Expression;

import AST.Node.Node;
import AST.Node.NodeVisitor;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Expression implements Node {
    String literal;
    String identifier;

    List<ExpressionOptional> exprOpts;

    @Override
    public void accept(NodeVisitor visitor) {

    }
}
