package AST.Expression;

import lombok.Getter;
import lombok.Setter;

// Constant expression -> 6
public interface Literal<T> extends Expression {

    T getValue();

}
