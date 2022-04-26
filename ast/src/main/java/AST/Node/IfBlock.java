package AST.Node;

import AST.Expression.Function;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IfBlock implements Node {

    private Function condition;
    private CodeBlock ifCodeBlock;
    private CodeBlock elseCodeBlock;

    @Override
    public void accept(NodeVisitor visitor) throws NodeException {
        visitor.visit(this);
    }

    // toString
    @Override
    public String toString() {
        return "ifBlock{"
            +
            "condition="
            + condition.toString()
            +
            ", ifCodeBlock="
            + ifCodeBlock.toString()
            +
            ", elseCodeBlock="
            + elseCodeBlock.toString()
            +
            '}';
    }
}
