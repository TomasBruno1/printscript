package AST.Expression;

import lombok.Getter;
import lombok.Setter;

// Assignment expression -> Variable = Expression ;
@Getter
@Setter
public class Assignment implements Expression {
    public Variable left;
    public Expression right;

    public Assignment(Variable left, Expression right) {
        this.left = left;
        this.right = right;
    }
}
