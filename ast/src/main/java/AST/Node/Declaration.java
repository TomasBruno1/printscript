package AST.Node;

import AST.Expression.Function;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Declaration implements Node {
    String varName;
    String type;
    boolean isConstant = false;
    Function value;

    public Declaration(String varName, String type) {
        this.varName = varName;
        this.type = type;
    }

    public Declaration(String varName, String type, boolean isConstant) {
        this.varName = varName;
        this.type = type;
        this.isConstant = isConstant;
    }

    @Override
    public void accept(NodeVisitor visitor) throws NodeException {
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
