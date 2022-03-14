package AST.Expression;

import lombok.Getter;
import lombok.Setter;

// Variable expression -> var
@Getter
@Setter
public class Variable implements Expression {
    public String name;
    public String value;

    public Variable(String name) {
        this.name = name;
    }

    public Variable(String name, String value) {
        this.name = name;
        this.value = value;
    }

}
