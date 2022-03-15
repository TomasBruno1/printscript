package AST.Expression;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class StringLiteral implements Literal<String> {

    @Getter
    private String value;

}
