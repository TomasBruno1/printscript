package Parser;

import static org.junit.jupiter.api.Assertions.*;

import AST.Expression.Expression;
import AST.Expression.ExpressionOptional;
import AST.Expression.Operand;
import AST.Node.Assignment;
import Lexer.DefaultTokenTypes;
import java.util.List;
import org.austral.ingsis.printscript.common.LexicalRange;
import org.austral.ingsis.printscript.common.Token;
import org.austral.ingsis.printscript.parser.TokenIterator;
import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    public void assignmentParserTestForSingleLiteral() {
        Parser<Assignment> parser =
                new AssignmentParser(
                        TokenIterator.Companion.create(
                                "a = 5",
                                List.of(
                                        new Token(
                                                DefaultTokenTypes.IDENTIFIER,
                                                0,
                                                1,
                                                new LexicalRange(0, 0, 3, 0)),
                                        new Token(
                                                DefaultTokenTypes.ASSIGN,
                                                2,
                                                3,
                                                new LexicalRange(2, 0, 3, 1)),
                                        new Token(
                                                DefaultTokenTypes.LITERAL,
                                                4,
                                                5,
                                                new LexicalRange(4, 0, 5, 1)))));
        Assignment assignment = new Assignment("a", new Expression("5", List.of()));
        assertEquals(assignment.toString(), parser.parse().toString());
    }

    @Test
    public void assignmentParserTestForSingleIdentifier() {
        Parser<Assignment> parser =
                new AssignmentParser(
                        TokenIterator.Companion.create(
                                "a = b",
                                List.of(
                                        new Token(
                                                DefaultTokenTypes.IDENTIFIER,
                                                0,
                                                1,
                                                new LexicalRange(0, 0, 3, 0)),
                                        new Token(
                                                DefaultTokenTypes.ASSIGN,
                                                2,
                                                3,
                                                new LexicalRange(2, 0, 3, 1)),
                                        new Token(
                                                DefaultTokenTypes.IDENTIFIER,
                                                4,
                                                5,
                                                new LexicalRange(4, 0, 5, 1)))));
        Assignment assignment = new Assignment("a", new Expression("b", List.of()));
        assertEquals(assignment.toString(), parser.parse().toString());
    }

    @Test
    public void assignmentParserTestForSingleStringLiteral() {
        Parser<Assignment> parser =
                new AssignmentParser(
                        TokenIterator.Companion.create(
                                "a = 'a'",
                                List.of(
                                        new Token(
                                                DefaultTokenTypes.IDENTIFIER,
                                                0,
                                                1,
                                                new LexicalRange(0, 0, 3, 0)),
                                        new Token(
                                                DefaultTokenTypes.ASSIGN,
                                                2,
                                                3,
                                                new LexicalRange(2, 0, 3, 1)),
                                        new Token(
                                                DefaultTokenTypes.LITERAL,
                                                4,
                                                7,
                                                new LexicalRange(4, 0, 7, 1)))));
        Assignment assignment = new Assignment("a", new Expression("'a'", List.of()));
        assertEquals(assignment.toString(), parser.parse().toString());
    }

    @Test
    public void expressionsParserTest() {
        Parser<Expression> parser =
                new ExpressionParser(
                        TokenIterator.Companion.create(
                                "2 + 3",
                                List.of(
                                        new Token(
                                                DefaultTokenTypes.LITERAL,
                                                0,
                                                1,
                                                new LexicalRange(0, 0, 1, 0)),
                                        new Token(
                                                DefaultTokenTypes.OPERATOR,
                                                2,
                                                3,
                                                new LexicalRange(2, 0, 3, 1)),
                                        new Token(
                                                DefaultTokenTypes.LITERAL,
                                                4,
                                                5,
                                                new LexicalRange(4, 0, 4, 1)))));
        Expression expression =
                new Expression("2", List.of(new ExpressionOptional(Operand.SUM, "3")));
        assertEquals(expression.toString(), parser.parse().toString());
    }
}
