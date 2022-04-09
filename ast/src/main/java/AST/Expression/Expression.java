package AST.Expression;

import AST.Node.NodeException;
import AST.Node.NodeVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Expression implements Function {

    Function left;
    Operand operand;
    Function right;

    public Expression(String value) {
        this.left = new Variable(value);
    }

    @Override
    public void accept(NodeVisitor visitor) {
    }

    @Override
    public void accept(ExpressionVisitor visitor) throws NodeException {
        visitor.visitExpression(this);
    }

    public Function addVariable(Operand operand, Variable variable) {
        if (operand == Operand.SUB || operand == Operand.SUM) {
            return new Expression(this, operand, variable);
        } else {
            Function right = new Expression(this.right, operand, variable);
            return new Expression(left, this.operand, right);
        }
    }

    // toString
    @Override
    public String toString() {
        String sb = "("
            + left.toString()
            + ")"
            +
            operand
            +
            "("
            + right
            + ")";
        return sb;
    }
}
