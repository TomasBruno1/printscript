package AST.Node;

import AST.Expression.Function;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IfBlock implements Node {

    private Function condition;
    private CodeBlock ifCodeBlock;
    private CodeBlock elseCodeBlock;

    @Override
    public void accept(NodeVisitor visitor) throws NodeException {
        visitor.visit(this);
    }
}
