package AST.Expression;

import AST.Node.NodeException;
import AST.Node.NodeVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ReadInput implements Function {

    @Getter
    Function prompt;

    @Override
    public void accept(NodeVisitor visitor) throws NodeException {
    }

    @Override
    public void accept(ExpressionVisitor visitor) throws NodeException {
        visitor.visitReadInput(this);
    }

    @Override
    public Function addVariable(Operand operand, Function variable) {
        return new Expression(this, operand, variable);
    }
}
