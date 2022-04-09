package Interpreter;

import AST.Expression.*;
import AST.Node.NodeException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class SolverVisitor implements ExpressionVisitor {

    @Getter
    private String result;

    private Map<String, String> variables = new HashMap<>();

    private final String stringRegex = "\"[\\s\\S][^\"]*\"|'[\\s\\S][^']*'";
    private final String numberRegex = "-?[0-9]{1,9}(\\.[0-9]*)?";

    public SolverVisitor(Map<String, String> variables) {
        this.variables = variables;
    }

    @Override
    public void visitExpression(Expression expression) throws NodeException {
        Operand operand = expression.getOperand();
        String leftResult = getResult(expression.getLeft());
        String rightResult = getResult(expression.getRight());

        if (variables.containsKey(leftResult))
            leftResult = variables.get(leftResult);
        if (variables.containsKey(rightResult))
            rightResult = variables.get(rightResult);

        String result;

        if (isStringOperation(leftResult, rightResult)) {
            result = stringOperation(leftResult, rightResult, operand);
        } else if (leftResult.matches(numberRegex) && rightResult.matches(numberRegex)) {
            result = numericOperation(leftResult, rightResult, operand);
        } else {
            throw new UndeclaredVariableException("Missing variable declaration");
        }
        this.result = result;
    }

    private boolean isStringOperation(String leftResult, String rightResult) {
        return (leftResult.matches(stringRegex) && rightResult.matches(numberRegex))
            || ((leftResult.matches(numberRegex) && rightResult.matches(stringRegex)))
            || (leftResult.matches(stringRegex) && rightResult.matches(stringRegex));
    }

    private String stringOperation(String leftResult, String rightResult, Operand operand) throws InvalidOperationException {
        if (operand != Operand.SUM)
            throw new InvalidOperationException(leftResult, rightResult, operand);
        String left = leftResult.replaceAll("[\"']", "");
        String right = rightResult.replaceAll("[\"']", "");
        return "\"" + left + right + "\"";
    }

    private String numericOperation(String leftResult, String rightResult, Operand operand) {
        String result = "";
        Double left = Double.parseDouble(leftResult);
        Double right = Double.parseDouble(rightResult);

        switch (operand) {
            case SUM:
                result = format(left + right);
                break;
            case SUB:
                result = format(left - right);
                break;
            case MUL:
                result = format(left * right);
                break;
            case DIV:
                result = format(left / right);
                break;
        }
        return result;
    }

    private String format(double value) {
        String result = value + "";
        while (result.charAt(result.length() - 1) == '0')
            result = result.substring(0, result.length() - 1);
        if (result.charAt(result.length() - 1) == '.')
            result = result.substring(0, result.length() - 1);
        return result;
    }

    @Override
    public void visitVariable(Variable variable) throws NodeException {
        result = variables.containsKey(variable.getValue()) ? variables.get(variable.getValue()) : variable.getValue();
        if (!result.matches(numberRegex) && !result.matches(stringRegex)) throw new UndeclaredVariableException(variable.getValue());
    }

    private String getResult(Function function) throws NodeException {
        function.accept(this);
        return result;
    }

    public void declareVariable(String name) {
        variables.put(name, null);
    }

    public void assignVariable(String name) {
        variables.put(name, result);
    }
}
