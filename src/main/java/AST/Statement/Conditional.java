package AST.Statement;

import AST.Expression.Expression;
import lombok.Getter;

// Conditional statement -> if ( Expression ) Statement else Statement ;
@Getter
public class Conditional implements Statement {
    Expression condition;
    Statement then;
    Statement otherwise;

    public Conditional(Expression condition, Statement then, Statement otherwise) {
        this.condition = condition;
        this.then = then;
        this.otherwise = otherwise;
    }

    public Conditional(Expression condition, Statement then) {
        this.condition = condition;
        this.then = then;
    }

}
