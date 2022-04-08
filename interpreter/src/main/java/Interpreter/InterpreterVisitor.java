package Interpreter;

import AST.Expression.Function;
import AST.Node.*;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class InterpreterVisitor implements NodeVisitor {

    @Getter
    Writer result;

    SolverVisitor solverVisitor = new SolverVisitor();

    @Override
    public void visit(CodeBlock codeBlock) {
        codeBlock.getChildren().forEach(node -> node.accept(this));
    }

    @Override
    public void visit(Declaration declaration) {
        String type = declaration.getType();
        String name = declaration.getVarName();
        Function function = declaration.getValue();
        function.accept(solverVisitor);

        if(type.equals("number")) {
            if(!isNumber(solverVisitor.result)) throw new TypeMismatchException();
        } else if(type.equals("string")) {
            if(!isString(solverVisitor.result)) throw new TypeMismatchException();
        }

        solverVisitor.declareVariable(name);
    }

    private boolean isString(String result) {
        return result.matches("\"[\\s\\S][^\"]*\"|'[\\s\\S][^']*'");
    }

    private boolean isNumber(String result) {
        return result.matches("-?[0-9]{1,9}(\\.[0-9]*)?");
    }

    @Override
    public void visit(Assignment function) {
    }

    @Override
    public void visit(Print print) {
    }
}
