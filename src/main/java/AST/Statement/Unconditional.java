package AST.Statement;

import AST.Expression.Expression;
import lombok.Getter;

// Unconditional statement -> 2 * x
@Getter
public class Unconditional implements Statement {
    Expression expression;

    public Unconditional(Expression expression) {
        this.expression = expression;
    }

}
