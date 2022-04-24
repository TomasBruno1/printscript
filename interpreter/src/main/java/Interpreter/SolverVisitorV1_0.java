package Interpreter;

import java.util.Map;

public class SolverVisitorV1_0 extends AbstractSolverVisitor {
    public SolverVisitorV1_0() {
    }

    public SolverVisitorV1_0(Map<String, String> variables) {
        super(variables);
    }
}
