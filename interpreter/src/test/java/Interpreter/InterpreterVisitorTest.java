package Interpreter;

import AST.Expression.Expression;
import AST.Expression.Operand;
import AST.Expression.Variable;
import AST.Node.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterpreterVisitorTest {

    @Test
    public void test001_WhenVisitingAPrintStatementThenItShouldBeWrittenToResult() throws NodeException {
        InterpreterVisitor visitor = new InterpreterVisitor();
        Print printStatement = new Print(new Variable("45"));
        printStatement.accept(visitor);
        assertEquals("45", visitor.getResult().read());
    }

    @Test
    public void test002_WhenVisitingAPrintStatementWithAnExpressionThenItsResultShouldBeWritten() throws NodeException {
        InterpreterVisitor visitor = new InterpreterVisitor();
        Print printStatement = new Print(new Expression(new Variable("45"), Operand.SUM, new Variable("5")));
        printStatement.accept(visitor);
        assertEquals("50", visitor.getResult().read());
    }

    @Test
    public void test003_WhenVisitingAPrintStatementWithAMixedExpressionThenItsResultShouldBeWritten()
            throws NodeException {
        InterpreterVisitor visitor = new InterpreterVisitor();
        Print printStatement = new Print(
                new Expression(
                        new Variable("100"),
                        Operand.SUM,
                        new Expression(new Variable("\"Hello\""), Operand.SUM, new Variable("\" world!\""))
                )
        );
        printStatement.accept(visitor);
        assertEquals("100Hello world!", visitor.getResult().read());
    }

    @Test
    public void test004_WhenReceivingFullCodeBlockThenPrintStatementsShouldBeWrittenToResult() throws NodeException {
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
        assertEquals("4526Hello world!", visitor.getResult().read());
    }

}
