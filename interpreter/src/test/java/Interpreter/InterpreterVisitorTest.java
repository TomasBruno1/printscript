package Interpreter;

import AST.Expression.Expression;
import AST.Expression.Operand;
import AST.Expression.Variable;
import AST.Node.Assignment;
import AST.Node.CodeBlock;
import AST.Node.Declaration;
import AST.Node.Print;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterpreterVisitorTest {

    @Test
    public void test001_WhenVisitingAPrintStatementThenItShouldBeWrittenToResult() {
        InterpreterVisitor visitor = new InterpreterVisitor();
        Print printStatement = new Print(new Variable("45"));
        printStatement.accept(visitor);
        assertEquals("45\n", visitor.getResult().read());
    }

    @Test
    public void test002_WhenVisitingAPrintStatementWithAnExpressionThenItsResultShouldBeWritten() {
        InterpreterVisitor visitor = new InterpreterVisitor();
        Print printStatement = new Print(new Expression(new Variable("45"), Operand.SUM, new Variable("5")));
        printStatement.accept(visitor);
        assertEquals("50\n", visitor.getResult().read());
    }

    @Test
    public void test003_WhenVisitingAPrintStatementWithAMixedExpressionThenItsResultShouldBeWritten() {
        InterpreterVisitor visitor = new InterpreterVisitor();
        Print printStatement = new Print(
                new Expression(
                        new Variable("100"),
                        Operand.SUM,
                        new Expression(new Variable("\"Hello\""), Operand.SUM, new Variable("\" world!\""))
                )
        );
        printStatement.accept(visitor);
        assertEquals("100Hello world!\n", visitor.getResult().read());
    }

    @Test
    public void test004_WhenReceivingFullCodeBlockThenPrintStatementsShouldBeWrittenToResult() {
        InterpreterVisitor visitor = new InterpreterVisitor();
        CodeBlock program = new CodeBlock();
        program.addChild(new Print(new Variable("45")));
        program.addChild(new Declaration("x", "number", new Variable("2")));
        program.addChild(new Print(new Variable("x")));
        program.addChild(new Declaration("y", "number"));
        program.addChild(new Assignment("y", new Expression(new Variable("x"), Operand.MUL, new Variable("3"))));
        program.addChild(new Print(new Variable("y")));
        program.addChild(new Declaration("string", "string", new Variable("\"Hello\"")));
        program.addChild(new Print(new Expression(new Variable("string"), Operand.SUM, new Variable("\" world!\""))));
        program.accept(visitor);
        assertEquals("45\n2\n6\nHello world!\n", visitor.getResult().read());
    }

}
