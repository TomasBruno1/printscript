package AST.Expression;

import AST.Node.NodeVisitor;
import lombok.Getter;
import lombok.Setter;

// Variable expression -> var
@Getter
@Setter
public class Identifier implements Expression {
    String name;

    public Identifier(String name) {
        this.name = name;
    }

    @Override
    public void accept(NodeVisitor visitor) {

    }
}
