package Interpreter;

import AST.Expression.Operand;
import AST.Expression.Variable;
import AST.Node.NodeException;

import java.util.HashMap;
import java.util.Map;

public class SolverVisitorV1_1 extends AbstractSolverVisitor {

    private final String boolRegex = "true|false";
    private final Map<String, String> constants = new HashMap<>();

    public SolverVisitorV1_1() {
    }

    public SolverVisitorV1_1(Map<String, String> variables) {
        super(variables);
    }

    @Override
    protected String solveOperation(Operand operand, String leftResult, String rightResult)
            throws InvalidOperationException,
                BooleanOperationException,
                UndeclaredVariableException {
        String result = super.solveOperation(operand, leftResult, rightResult);
        if (result == null) {
            if (leftResult.matches(boolRegex) || rightResult.matches(boolRegex)) {
                throw new BooleanOperationException(leftResult, rightResult, operand);
            }
        }
        return result;
    }

    @Override
    public void visitVariable(Variable variable) throws NodeException {
        if (variables.containsKey(variable.getValue())) {
            result = variables.get(variable.getValue());
        } else if (constants.containsKey(variable.getValue())) {
            result = constants.get(variable.getValue());
        } else {
            result = variable.getValue();
        }
        if (!result.matches(numberRegex) && !result.matches(stringRegex) && !result.matches(boolRegex)) {
            throw new UndeclaredVariableException(variable.getValue());
        }
    }

    @Override
    public void declareVariable(String name, boolean isConstant) {
        if (isConstant) {
            constants.put(name, variables.get(name));
        } else {
            super.declareVariable(name, false);
        }
    }

    @Override
    public void assignVariable(String name) throws ConstantReassignmentException {
        if (constants.containsKey(name)) {
            if (constants.get(name) == null) {
                constants.put(name, result);
                return;
            }
            throw new ConstantReassignmentException(name);
        }
        super.assignVariable(name);
    }
}
