package AST.Node;

import AST.Expression.Expression;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Declaration implements Node {
    String varName;
    String type;

    Expression value;

    public Declaration(String varName, String type) {
        this.varName = varName;
        this.type = type;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        if (value != null)
            return "Declaration("
                + "varName="
                + varName
                + ", type="
                + type
                + ", value="
                + value.toString()
                + ')';
        else
            return "Declaration(" + "varName=" + varName + ", type=" + type + ')';
    }
}
