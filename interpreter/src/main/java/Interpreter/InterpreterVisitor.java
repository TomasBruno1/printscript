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

    Map<String, Integer> numericVariables = new HashMap<>();
    Map<String, String> stringVariables = new HashMap<>();

    @Override
    public void visit(CodeBlock codeBlock) {
        codeBlock.getChildren().forEach(node -> node.accept(this));
    }

    @Override
    public void visit(Declaration declaration) {
        String type = declaration.getType();
        String name = declaration.getVarName();
        Function function = declaration.getValue();

        // if(type.equals("number")) {
        // numericVariables.put(name, )
        // }
    }

    @Override
    public void visit(Assignment function) {
    }

    @Override
    public void visit(Print print) {
    }
}
