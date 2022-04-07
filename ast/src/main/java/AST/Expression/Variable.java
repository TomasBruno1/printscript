package AST.Expression;

import AST.Node.NodeVisitor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Variable implements Function {

    private String value;

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visitVariable(this);
    }

    @Override
    public Function addVariable(Operand operand, Variable variable) {
        return new Expression(this, operand, variable);
    }

    @Override
    public void accept(NodeVisitor visitor) {
    }

    // toString
    public String toString() {
        return value;
    }
}
