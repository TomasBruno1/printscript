package AST.Node;

import AST.Expression.Expression;
import AST.Expression.Identifier;
import lombok.Getter;
import lombok.Setter;
import org.austral.ingsis.printscript.common.Token;

@Getter
@Setter
public class Declaration implements Node {
    Identifier left;
    Token type;
    Expression right;

    public Declaration(Identifier left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(NodeVisitor visitor) {

    }
}
