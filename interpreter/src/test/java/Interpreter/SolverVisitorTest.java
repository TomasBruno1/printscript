package Interpreter;

import AST.Expression.Expression;
import AST.Expression.Operand;
import AST.Expression.Variable;
import AST.Node.NodeException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SolverVisitorTest {

    @Test
    public void test001_WhenReceivingSimpleAdditionShouldReturnCorrectResult() throws NodeException {
        Expression input = new Expression(new Variable("1"), Operand.SUM, new Variable("2"));
        String expected = "3";
        SolverVisitorV1_0 visitor = new SolverVisitorV1_0();
        input.accept(visitor);
        String actual = visitor.getResult();
        assertEquals(expected, actual);
    }

    @Test
    public void test002_WhenReceivingAdditionAndMultiplicationOperationShouldReturnCorrectResult()
            throws NodeException {
        Expression input = new Expression(
                new Variable("1"),
                Operand.SUM,
                new Expression(new Variable("2"), Operand.MUL, new Variable("3"))
        );
        String expected = "7";
        SolverVisitorV1_0 visitor = new SolverVisitorV1_0();
        input.accept(visitor);
        String actual = visitor.getResult();
        assertEquals(expected, actual);
    }

    @Test
    public void test003_WhenReceivingComplexNumericalOperationShouldReturnCorrectResult() throws NodeException {
        Expression input = new Expression(
                new Expression(new Variable("5"), Operand.DIV, new Variable("2")),
                Operand.SUM,
                new Expression(
                        new Variable("2"),
                        Operand.MUL,
                        new Expression(new Variable("3"), Operand.SUB, new Variable("4"))
                )
        );
        String expected = "0.5";
        SolverVisitorV1_0 visitor = new SolverVisitorV1_0();
        input.accept(visitor);
        String actual = visitor.getResult();
        assertEquals(expected, actual);
    }

    @Test
    public void test004_WhenReceivingSimpleNumberAndVariableOperationShouldReturnCorrectResult() throws NodeException {
        Expression input = new Expression(new Variable("aNumber"), Operand.SUM, new Variable("2"));
        String expected = "7";

        HashMap<String, String> variables = new HashMap<>();
        variables.put("aNumber", "5");

        SolverVisitorV1_0 visitor = new SolverVisitorV1_0(variables);
        input.accept(visitor);
        String actual = visitor.getResult();
        assertEquals(expected, actual);
    }

    @Test
    public void test005_WhenReceivingComplexNumberAndVariableOperationShouldReturnCorrectResult() throws NodeException {
        Expression input = new Expression(
                new Expression(new Variable("aNumber"), Operand.DIV, new Variable("2")),
                Operand.SUM,
                new Expression(
                        new Variable("2"),
                        Operand.MUL,
                        new Expression(new Variable("anotherNumber"), Operand.SUB, new Variable("4"))
                )
        );
        String expected = "0.5";

        HashMap<String, String> variables = new HashMap<>();
        variables.put("aNumber", "5");
        variables.put("anotherNumber", "3");

        SolverVisitorV1_0 visitor = new SolverVisitorV1_0(variables);
        input.accept(visitor);
        String actual = visitor.getResult();
        assertEquals(expected, actual);
    }

    @Test
    public void test006_WhenReceivingStringConcatenationShouldReturnCorrectResult() throws NodeException {
        Expression input = new Expression(new Variable("'Hello'"), Operand.SUM, new Variable("\" world!\""));
        String expected = "\"Hello world!\"";
        SolverVisitorV1_0 visitor = new SolverVisitorV1_0();
        input.accept(visitor);
        String actual = visitor.getResult();
        assertEquals(expected, actual);
    }

    @Test
    public void test007_WhenReceivingStringConcatenationWithVariableShouldReturnCorrectResult() throws NodeException {
        Expression input = new Expression(
                new Variable("'Hello'"),
                Operand.SUM,
                new Expression(new Variable("aString"), Operand.SUM, new Variable("\"!!!\""))
        );
        String expected = "\"Hello world!!!\"";

        HashMap<String, String> variables = new HashMap<>();
        variables.put("aString", "\" world\"");

        SolverVisitorV1_0 visitor = new SolverVisitorV1_0(variables);
        input.accept(visitor);
        String actual = visitor.getResult();
        assertEquals(expected, actual);
    }

    @Test
    public void test008_WhenReceivingStringConcatenationWithNumberShouldReturnCorrectResult() throws NodeException {
        Expression input = new Expression(
                new Variable("'Hello'"),
                Operand.SUM,
                new Expression(new Variable("5.12"), Operand.SUM, new Variable("\"!!!\""))
        );
        String expected = "\"Hello5.12!!!\"";
        SolverVisitorV1_0 visitor = new SolverVisitorV1_0();
        input.accept(visitor);
        String actual = visitor.getResult();
        assertEquals(expected, actual);
    }

    @Test
    public void test009_WhenReceivingStringsNumbersAndVariablesOperationShouldReturnCorrectResult()
            throws NodeException {
        Expression input = new Expression(
                new Expression(new Variable("'Hello'"), Operand.SUM, new Variable("\" world!\"")),
                Operand.SUM,
                new Expression(
                        new Expression(new Variable("5.5"), Operand.MUL, new Variable("2")),
                        Operand.SUM,
                        new Variable("aString")
                )
        );
        String expected = "\"Hello world!11!!!\"";

        HashMap<String, String> variables = new HashMap<>();
        variables.put("aString", "\"!!!\"");

        SolverVisitorV1_0 visitor = new SolverVisitorV1_0(variables);
        input.accept(visitor);
        String actual = visitor.getResult();
        assertEquals(expected, actual);
    }

    @Test
    public void test010_WhenReceivingUndeclaredVariableShouldThrowException() {
        Expression input = new Expression(new Variable("aVariable"), Operand.SUM, new Variable("\"2\""));
        SolverVisitorV1_0 visitor = new SolverVisitorV1_0();
        assertThrows(UndeclaredVariableException.class, () -> input.accept(visitor));
    }

    @Test
    public void test011_WhenReceivingUndeclaredVariableShouldThrowException() {
        Expression input = new Expression(new Variable("aVariable"), Operand.SUM, new Variable("2"));
        SolverVisitorV1_0 visitor = new SolverVisitorV1_0();
        assertThrows(UndeclaredVariableException.class, () -> input.accept(visitor));
    }

    @Test
    public void test012_WhenReceivingUndeclaredVariablesShouldThrowException() {
        Expression input = new Expression(new Variable("aVariable"), Operand.SUM, new Variable("anotherVariable"));
        SolverVisitorV1_0 visitor = new SolverVisitorV1_0();
        assertThrows(UndeclaredVariableException.class, () -> input.accept(visitor));
    }

}
