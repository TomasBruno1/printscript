package AST.Expression;

import AST.Node.NodeVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class NumberLiteral implements Literal<Double> {

    @Getter
    private Double value;

    @Override
    public void accept(NodeVisitor visitor) {

    }
}
