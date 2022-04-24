package Interpreter;

public class InterpreterVisitorV1_1 extends AbstractInterpreterVisitor {

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
}
