package AST.Expression;

import lombok.Getter;
import lombok.Setter;

// Constant expression -> 6
@Getter
@Setter
public class Constant implements Expression {
    public String value;

    public Constant(String value) {
        this.value = value;
    }
}
