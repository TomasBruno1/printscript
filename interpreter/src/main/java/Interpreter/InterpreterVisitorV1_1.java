package Interpreter;

import AST.Node.IfBlock;
import AST.Node.NodeException;
import AST.Expression.ReadInput;
import AST.Node.Print;

public class InterpreterVisitorV1_1 extends AbstractInterpreterVisitor {

    private final IPrintEmitter printEmitter;

    public InterpreterVisitorV1_1(IInputProvider inputProvider, IPrintEmitter printEmitter) {
        solverVisitor = new SolverVisitorV1_1(inputProvider, printEmitter);
        this.printEmitter = printEmitter;
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

    @Override
    public void visit(ReadInput readInput) throws NodeException {
    }

    @Override
    public void visit(Print print) throws NodeException {
        print.getContent().accept(solverVisitor);
        String result = solverVisitor.getResult().replaceAll("[\"']", "");
        this.result.write(result);
        printEmitter.print(result);
    }
}
