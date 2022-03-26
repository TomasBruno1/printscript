package AST.Node;

import AST.Expression.Expression;
import lombok.AllArgsConstructor;

// Assignment expression -> Variable = Expression ;
@AllArgsConstructor
public class Assignment implements Node {
    String name;
    Expression value;

    @Override
    public void accept(NodeVisitor visitor) {}

    @Override
    public String toString() {
        return "Assignment(" + "name='" + name + '\'' + ", value=" + value + ')';
    }
}
