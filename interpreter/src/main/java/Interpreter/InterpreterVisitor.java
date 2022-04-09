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
    public void visit(CodeBlock codeBlock) {
        codeBlock.getChildren().forEach(node -> node.accept(this));
    }

    @Override
    public void visit(Declaration declaration) {
        String type = declaration.getType();
        String name = declaration.getVarName();
        Function function = declaration.getValue();
        solverVisitor.declareVariable(name);
        if(function != null) {
            function.accept(solverVisitor);
            checkType(type);
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
    public void visit(Assignment assignment) {
        String name = assignment.getName();
        Function value = assignment.getValue();
        value.accept(solverVisitor);

        String type = varTypes.get(name);
        if (type == null) {
            throw new UndeclaredVariableException("Variable " + name + " is not declared");
        }

        checkType(type);

        solverVisitor.assignVariable(name);
    }

    private void checkType(String type) {
        if (type.equals("number")) {
            if (!isNumber(solverVisitor.getResult()))
                throw new TypeMismatchException();
        } else if (type.equals("string")) {
            if (!isString(solverVisitor.getResult()))
                throw new TypeMismatchException();
        }
    }

    @Override
    public void visit(Print print) {
        print.getContent().accept(solverVisitor);
        String result = solverVisitor.getResult().replaceAll("[\"']", "");
        this.result.write(result);
    }
}
