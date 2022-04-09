package Interpreter;

import AST.Expression.Function;
import AST.Node.*;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class InterpreterVisitor implements NodeVisitor {

    @Getter
    private final Writer result = new Writer();

    private final SolverVisitor solverVisitor = new SolverVisitor();

    final Map<String, String> varTypes = new HashMap<>();

    @Override
    public void visit(CodeBlock codeBlock) throws NodeException {
        for (Node child : codeBlock.getChildren()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(Declaration declaration) throws NodeException {
        String type = declaration.getType();
        String name = declaration.getVarName();
        Function function = declaration.getValue();
        solverVisitor.declareVariable(name);
        if (function != null) {
            function.accept(solverVisitor);
            checkType(name, type);
            solverVisitor.assignVariable(name);
        }

        varTypes.put(name, type);
    }

    private boolean isString(String result) {
        return result.matches("\"[\\s\\S][^\"]*\"|'[\\s\\S][^']*'");
    }

    private boolean isNumber(String result) {
        return result.matches("-?[0-9]{1,9}(\\.[0-9]*)?");
    }

    @Override
    public void visit(Assignment assignment) throws NodeException {
        String name = assignment.getName();
        Function value = assignment.getValue();
        value.accept(solverVisitor);

        String type = varTypes.get(name);
        if (type == null) {
            throw new UndeclaredVariableException(name);
        }

        checkType(name, type);

        solverVisitor.assignVariable(name);
    }

    private void checkType(String name, String type) throws TypeMismatchException {
        if (type.equals("number")) {
            if (!isNumber(solverVisitor.getResult()))
                throw new TypeMismatchException(name, type);
        } else if (type.equals("string")) {
            if (!isString(solverVisitor.getResult()))
                throw new TypeMismatchException(name, type);
        }
    }

    @Override
    public void visit(Print print) throws NodeException {
        print.getContent().accept(solverVisitor);
        String result = solverVisitor.getResult().replaceAll("[\"']", "");
        this.result.write(result);
    }
}
