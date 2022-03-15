package AST.Expression;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class NumberLiteral implements Literal<Double> {

    @Getter
    private Double value;

}
