package AST.Node;

import AST.Expression.Expression;
import AST.Expression.Identifier;
import lombok.Getter;
import lombok.Setter;

// Assignment expression -> Variable = Expression ;
public class Assignment implements Node {
    Identifier left;
    Expression right;

    public Assignment(Identifier left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(NodeVisitor visitor) {

    }
}
