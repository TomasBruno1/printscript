package AST.Expression;

import lombok.Getter;
import lombok.Setter;
import org.austral.ingsis.printscript.common.Token;

// Operation expression -> Expression + Expression | Expression - Expression | Expression * Expression | Expression / Expression
@Getter
@Setter
public class Operation implements Expression {
    Expression left;
    Expression right;
    Token op;

    public Operation(Expression left, Token op, Expression right) {
        this.left = left;
        this.right = right;
        this.op = op;
    }
}
