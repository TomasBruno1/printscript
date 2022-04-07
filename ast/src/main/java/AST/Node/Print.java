package AST.Node;

import AST.Expression.Function;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Print implements Node {
    Function content;

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    // toString
    @Override
    public String toString() {
        return "Print(" + "content=" + content + ')';
    }
}
