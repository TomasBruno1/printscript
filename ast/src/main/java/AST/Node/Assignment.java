package AST.Node;

import AST.Expression.Function;
import lombok.AllArgsConstructor;
import lombok.Getter;

// Assignment expression -> Variable = Expression ;
@AllArgsConstructor
@Getter
public class Assignment implements Node {
    String name;
    Function value;

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Assignment(" + "name='" + name + '\'' + ", value=" + value + ')';
    }
}
