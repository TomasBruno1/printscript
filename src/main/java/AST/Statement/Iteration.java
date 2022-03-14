package AST.Statement;

import AST.Expression.Expression;
import lombok.Getter;

// Iteration statement -> while ( Expression ) Statement
@Getter
public class Iteration implements Statement {
    Expression cond;
    Statement body;

    public Iteration(Expression cond, Statement body) {
        this.cond = cond;
        this.body = body;
    }

}
