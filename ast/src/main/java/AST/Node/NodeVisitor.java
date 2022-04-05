package AST.Node;

import AST.Expression.Expression;
import AST.Expression.ExpressionOptional;

public interface NodeVisitor {
    void visit(CodeBlock codeBlock);
    void visit(Declaration declaration);
    void visit(Assignment function);
    void visit(Print print);
}
