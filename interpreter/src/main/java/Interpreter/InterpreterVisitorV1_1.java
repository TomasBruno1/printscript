package Interpreter;

import AST.Node.IfBlock;
import AST.Node.NodeException;

public class InterpreterVisitorV1_1 extends AbstractInterpreterVisitor {

    public InterpreterVisitorV1_1() {
        solverVisitor = new SolverVisitorV1_1();
    }

    @Override
    protected void checkType(String name, String type) throws TypeMismatchException {
        super.checkType(name, type);
        if (type.equals("boolean")) {
            if (!isBoolean(solverVisitor.getResult())) {
                throw new TypeMismatchException(name, type);
            }
        }
    }

    private boolean isBoolean(String result) {
        return result.equals("true") || result.equals("false");
    }

    @Override
    public void visit(IfBlock ifBlock) throws NodeException {
        ifBlock.getCondition().accept(solverVisitor);
        String result = solverVisitor.getResult();
        if (result.equals("true")) {
            ifBlock.getIfCodeBlock().accept(this);
        } else if (result.equals("false") && ifBlock.getElseCodeBlock() != null) {
            ifBlock.getElseCodeBlock().accept(this);
        }
    }
}
