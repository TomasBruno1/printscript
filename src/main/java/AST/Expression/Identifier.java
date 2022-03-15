package AST.Expression;

import AST.Node.NodeVisitor;
import lombok.Getter;
import lombok.Setter;

// Variable expression -> var
@Getter
@Setter
public class Identifier implements Expression {
    String name;
    String value;

    public Identifier(String name) {
        this.name = name;
    }

    public Identifier(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public void accept(NodeVisitor visitor) {

    }
}
