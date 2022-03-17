package AST.Expression;

import AST.Node.NodeVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class StringLiteral implements Literal<String> {

    @Getter
    private String value;

    @Override
    public void accept(NodeVisitor visitor) {

    }
}
