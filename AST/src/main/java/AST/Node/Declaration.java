package AST.Node;

import AST.Expression.Expression;
import AST.Expression.Identifier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.austral.ingsis.printscript.common.Token;

@AllArgsConstructor
public class Declaration implements Node {
    Identifier left;
    Token type;
    Expression right;

    @Override
    public void accept(NodeVisitor visitor) {

    }
}
