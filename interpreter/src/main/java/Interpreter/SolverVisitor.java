package Interpreter;

import AST.Expression.*;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class SolverVisitor implements ExpressionVisitor {

    String result;

    Map<String, String> variables = new HashMap<>();

    public SolverVisitor(Map<String, String> variables) {
        this.variables = variables;
    }

    @Override
    public void visitExpression(Expression expression) {
        Operand operand = expression.getOperand();
        String leftResult = getResult(expression.getLeft());
        String rightResult = getResult(expression.getRight());

        if (variables.containsKey(leftResult)) leftResult = variables.get(leftResult);
        if (variables.containsKey(rightResult)) rightResult = variables.get(rightResult);

        String result;
        String regex = "\"[\\s\\S][^\"]*\"|'[\\s\\S][^']*'";
        if(leftResult.matches(regex) || rightResult.matches(regex)) {
            result = stringOperation(leftResult, rightResult, operand);
        } else {
            result = numericOperation(leftResult, rightResult, operand);
        }

        this.result = result;
    }

    private String stringOperation(String leftResult, String rightResult, Operand operand) {
        if (operand != Operand.SUM) throw new RuntimeException("String operations can only be SUM");
        if((leftResult.charAt(0) != '\'' && leftResult.charAt(0) != '"') && (rightResult.charAt(0) != '\'' && rightResult.charAt(0) != '"')) throw new RuntimeException("Undefined identifier");
        String left = leftResult.replaceAll("[\"']", "");
        String right = rightResult.replaceAll("[\"']", "");
        return "\"" + left + right + "\"";
    }

    private String numericOperation(String leftResult, String rightResult, Operand operand) {
        String result = "";
        Double left = Double.parseDouble(leftResult);
        Double right = Double.parseDouble(rightResult);
        DecimalFormat df = new DecimalFormat("0.#");

        switch (operand) {
            case SUM:
                result = df.format(left + right);
                break;
            case SUB:
                result = df.format(left - right);
                break;
            case MUL:
                result = df.format(left * right);
                break;
            case DIV:
                result = df.format(left / right);
                break;
        }
        return result;
    }

    @Override
    public void visitVariable(Variable variable) {
        result = variable.getValue();
    }

    private String getResult(Function function) {
        function.accept(this);
        return result;
    }

}
