package Parser;

import static org.junit.jupiter.api.Assertions.*;

import AST.Expression.Expression;
import AST.Expression.ExpressionOptional;
import AST.Expression.Operand;
import AST.Node.*;
import Commons.DefaultTokenTypes;
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
                                    new LexicalRange(0, 0, 3, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.ASSIGN,
                                    2,
                                    3,
                                    new LexicalRange(2, 0, 3, 1)
                            ),
                            new Token(
                                    DefaultTokenTypes.LITERAL,
                                    4,
                                    5,
                                    new LexicalRange(4, 0, 5, 1)
                            )
                        )
                    )
            );
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
                                    new LexicalRange(0, 0, 3, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.ASSIGN,
                                    2,
                                    3,
                                    new LexicalRange(2, 0, 3, 1)
                            ),
                            new Token(
                                    DefaultTokenTypes.IDENTIFIER,
                                    4,
                                    5,
                                    new LexicalRange(4, 0, 5, 1)
                            )
                        )
                    )
            );
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
                                    new LexicalRange(0, 0, 3, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.ASSIGN,
                                    2,
                                    3,
                                    new LexicalRange(2, 0, 3, 1)
                            ),
                            new Token(
                                    DefaultTokenTypes.LITERAL,
                                    4,
                                    7,
                                    new LexicalRange(4, 0, 7, 1)
                            )
                        )
                    )
            );
        Assignment assignment = new Assignment("a", new Expression("'a'", List.of()));
        assertEquals(assignment.toString(), parser.parse().toString());
    }

    @Test
    public void expressionParserTestForSimpleSum() {
        Parser<Expression> parser =
            new ExpressionParser(
                    TokenIterator.Companion.create(
                        "2 + 3",
                        List.of(
                            new Token(
                                    DefaultTokenTypes.LITERAL,
                                    0,
                                    1,
                                    new LexicalRange(0, 0, 1, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.OPERATOR,
                                    2,
                                    3,
                                    new LexicalRange(2, 0, 3, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.LITERAL,
                                    4,
                                    5,
                                    new LexicalRange(4, 0, 5, 0)
                            )
                        )
                    )
            );
        Expression expression =
            new Expression("2", List.of(new ExpressionOptional(Operand.SUM, "3")));
        assertEquals(expression.toString(), parser.parse().toString());
    }

    @Test
    public void expressionParserTestForNumberOperation() {
        Parser<Expression> parser =
            new ExpressionParser(
                    TokenIterator.Companion.create(
                        "2 + 3 * 4 - 10 / 5",
                        List.of(
                            new Token(
                                    DefaultTokenTypes.LITERAL,
                                    0,
                                    1,
                                    new LexicalRange(0, 0, 1, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.OPERATOR,
                                    2,
                                    3,
                                    new LexicalRange(2, 0, 3, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.LITERAL,
                                    4,
                                    5,
                                    new LexicalRange(4, 0, 5, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.OPERATOR,
                                    6,
                                    7,
                                    new LexicalRange(6, 0, 7, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.LITERAL,
                                    8,
                                    9,
                                    new LexicalRange(8, 0, 9, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.OPERATOR,
                                    10,
                                    11,
                                    new LexicalRange(10, 0, 11, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.LITERAL,
                                    12,
                                    14,
                                    new LexicalRange(12, 0, 14, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.OPERATOR,
                                    15,
                                    16,
                                    new LexicalRange(15, 0, 16, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.LITERAL,
                                    17,
                                    18,
                                    new LexicalRange(17, 0, 18, 0)
                            )
                        )
                    )
            );
        Expression expression =
            new Expression(
                    "2",
                    List.of(
                        new ExpressionOptional(Operand.SUM, "3"),
                        new ExpressionOptional(Operand.MUL, "4"),
                        new ExpressionOptional(Operand.SUB, "10"),
                        new ExpressionOptional(Operand.DIV, "5")
                    )
            );
        assertEquals(expression.toString(), parser.parse().toString());
    }

    @Test
    public void expressionParserTestForNumberAndVariableOperation() {
        Parser<Expression> parser =
            new ExpressionParser(
                    TokenIterator.Companion.create(
                        "2 + a",
                        List.of(
                            new Token(
                                    DefaultTokenTypes.LITERAL,
                                    0,
                                    1,
                                    new LexicalRange(0, 0, 1, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.OPERATOR,
                                    2,
                                    3,
                                    new LexicalRange(2, 0, 3, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.IDENTIFIER,
                                    4,
                                    5,
                                    new LexicalRange(4, 0, 5, 0)
                            )
                        )
                    )
            );
        Expression expression =
            new Expression("2", List.of(new ExpressionOptional(Operand.SUM, "a")));
        assertEquals(expression.toString(), parser.parse().toString());
    }

    @Test
    public void expressionParserTestForMixedOperation() {
        Parser<Expression> parser =
            new ExpressionParser(
                    TokenIterator.Companion.create(
                        "2 + a * 'hola' - 8",
                        List.of(
                            new Token(
                                    DefaultTokenTypes.LITERAL,
                                    0,
                                    1,
                                    new LexicalRange(0, 0, 1, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.OPERATOR,
                                    2,
                                    3,
                                    new LexicalRange(2, 0, 3, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.IDENTIFIER,
                                    4,
                                    5,
                                    new LexicalRange(4, 0, 5, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.OPERATOR,
                                    6,
                                    7,
                                    new LexicalRange(6, 0, 7, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.LITERAL,
                                    8,
                                    14,
                                    new LexicalRange(8, 0, 14, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.OPERATOR,
                                    15,
                                    16,
                                    new LexicalRange(15, 0, 16, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.LITERAL,
                                    17,
                                    18,
                                    new LexicalRange(17, 0, 18, 0)
                            )
                        )
                    )
            );
        Expression expression =
            new Expression(
                    "2",
                    List.of(
                        new ExpressionOptional(Operand.SUM, "a"),
                        new ExpressionOptional(Operand.MUL, "'hola'"),
                        new ExpressionOptional(Operand.SUB, "8")
                    )
            );
        assertEquals(expression.toString(), parser.parse().toString());
    }

    @Test
    public void printParserTest() {
        Parser<Print> parser =
            new PrintParser(
                    TokenIterator.Companion.create(
                        "println('hola')",
                        List.of(
                            new Token(
                                    DefaultTokenTypes.KEYWORD,
                                    0,
                                    7,
                                    new LexicalRange(0, 0, 7, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.SEPARATOR,
                                    7,
                                    8,
                                    new LexicalRange(7, 0, 8, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.LITERAL,
                                    8,
                                    14,
                                    new LexicalRange(8, 0, 14, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.SEPARATOR,
                                    14,
                                    15,
                                    new LexicalRange(14, 0, 15, 0)
                            )
                        )
                    )
            );
        Print print = new Print(new Expression("'hola'", List.of()));
        assertEquals(print.toString(), parser.parse().toString());
    }

    @Test
    public void declarationParserTestForInitialization() {
        Parser<Declaration> parser =
            new DeclarationParser(
                    TokenIterator.Companion.create(
                        "let a:number;",
                        List.of(
                            new Token(
                                    DefaultTokenTypes.KEYWORD,
                                    0,
                                    3,
                                    new LexicalRange(0, 0, 3, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.IDENTIFIER,
                                    4,
                                    5,
                                    new LexicalRange(4, 0, 5, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.SEPARATOR,
                                    5,
                                    6,
                                    new LexicalRange(5, 0, 6, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.KEYWORD,
                                    6,
                                    12,
                                    new LexicalRange(6, 0, 12, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.SEPARATOR,
                                    12,
                                    13,
                                    new LexicalRange(12, 0, 13, 0)
                            )
                        )
                    )
            );
        Declaration declaration = new Declaration("a", "number");
        assertEquals(declaration.toString(), parser.parse().toString());
    }

    @Test
    public void declarationParserTestForInitializationAndAssignment() {
        Parser<Declaration> parser =
            new DeclarationParser(
                    TokenIterator.Companion.create(
                        "let a:number = 8;",
                        List.of(
                            new Token(
                                    DefaultTokenTypes.KEYWORD,
                                    0,
                                    3,
                                    new LexicalRange(0, 0, 3, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.IDENTIFIER,
                                    4,
                                    5,
                                    new LexicalRange(4, 0, 5, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.SEPARATOR,
                                    5,
                                    6,
                                    new LexicalRange(5, 0, 6, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.KEYWORD,
                                    6,
                                    12,
                                    new LexicalRange(6, 0, 12, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.ASSIGN,
                                    13,
                                    14,
                                    new LexicalRange(13, 0, 14, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.LITERAL,
                                    15,
                                    16,
                                    new LexicalRange(15, 0, 16, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.SEPARATOR,
                                    16,
                                    17,
                                    new LexicalRange(16, 0, 17, 0)
                            )
                        )
                    )
            );
        Declaration declaration = new Declaration("a", "number", new Expression("8", List.of()));
        assertEquals(declaration.toString(), parser.parse().toString());
    }

    @Test
    public void defaultParserTestForSimpleCodeBlockTest() {
        Parser<Node> parser =
            new DefaultParser(
                    TokenIterator.Companion.create(
                        "let a:number = 8;\nlet b:string = '8';\na = a + b;\nprintln(a);",
                        List.of(
                            new Token(
                                    DefaultTokenTypes.KEYWORD,
                                    0,
                                    3,
                                    new LexicalRange(0, 0, 3, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.IDENTIFIER,
                                    4,
                                    5,
                                    new LexicalRange(4, 0, 5, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.SEPARATOR,
                                    5,
                                    6,
                                    new LexicalRange(5, 0, 6, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.KEYWORD,
                                    6,
                                    12,
                                    new LexicalRange(6, 0, 12, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.ASSIGN,
                                    13,
                                    14,
                                    new LexicalRange(13, 0, 14, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.LITERAL,
                                    15,
                                    16,
                                    new LexicalRange(15, 0, 16, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.SEPARATOR,
                                    16,
                                    17,
                                    new LexicalRange(16, 0, 17, 0)
                            ),
                            new Token(
                                    DefaultTokenTypes.KEYWORD,
                                    18,
                                    21,
                                    new LexicalRange(0, 1, 3, 1)
                            ),
                            new Token(
                                    DefaultTokenTypes.IDENTIFIER,
                                    22,
                                    23,
                                    new LexicalRange(4, 1, 5, 1)
                            ),
                            new Token(
                                    DefaultTokenTypes.SEPARATOR,
                                    23,
                                    24,
                                    new LexicalRange(5, 1, 6, 1)
                            ),
                            new Token(
                                    DefaultTokenTypes.KEYWORD,
                                    24,
                                    30,
                                    new LexicalRange(6, 1, 12, 1)
                            ),
                            new Token(
                                    DefaultTokenTypes.ASSIGN,
                                    31,
                                    32,
                                    new LexicalRange(13, 1, 14, 1)
                            ),
                            new Token(
                                    DefaultTokenTypes.LITERAL,
                                    33,
                                    36,
                                    new LexicalRange(15, 1, 16, 1)
                            ),
                            new Token(
                                    DefaultTokenTypes.SEPARATOR,
                                    36,
                                    37,
                                    new LexicalRange(16, 1, 17, 1)
                            ),
                            new Token(
                                    DefaultTokenTypes.IDENTIFIER,
                                    38,
                                    39,
                                    new LexicalRange(0, 2, 1, 2)
                            ),
                            new Token(
                                    DefaultTokenTypes.ASSIGN,
                                    40,
                                    41,
                                    new LexicalRange(2, 2, 3, 2)
                            ),
                            new Token(
                                    DefaultTokenTypes.IDENTIFIER,
                                    42,
                                    43,
                                    new LexicalRange(4, 2, 5, 2)
                            ),
                            new Token(
                                    DefaultTokenTypes.OPERATOR,
                                    44,
                                    45,
                                    new LexicalRange(6, 2, 7, 2)
                            ),
                            new Token(
                                    DefaultTokenTypes.IDENTIFIER,
                                    46,
                                    47,
                                    new LexicalRange(8, 2, 9, 2)
                            ),
                            new Token(
                                    DefaultTokenTypes.SEPARATOR,
                                    47,
                                    48,
                                    new LexicalRange(9, 2, 10, 2)
                            ),
                            new Token(
                                    DefaultTokenTypes.KEYWORD,
                                    49,
                                    56,
                                    new LexicalRange(0, 3, 7, 3)
                            ),
                            new Token(
                                    DefaultTokenTypes.SEPARATOR,
                                    56,
                                    57,
                                    new LexicalRange(7, 3, 8, 3)
                            ),
                            new Token(
                                    DefaultTokenTypes.IDENTIFIER,
                                    57,
                                    58,
                                    new LexicalRange(8, 3, 9, 3)
                            ),
                            new Token(
                                    DefaultTokenTypes.SEPARATOR,
                                    58,
                                    59,
                                    new LexicalRange(9, 3, 10, 3)
                            ),
                            new Token(
                                    DefaultTokenTypes.SEPARATOR,
                                    59,
                                    60,
                                    new LexicalRange(10, 3, 11, 3)
                            )
                        )
                    )
            );

        CodeBlock codeBlock = new CodeBlock();
        codeBlock.addChild(new Declaration("a", "number", new Expression("8", List.of())));
        codeBlock.addChild(new Declaration("b", "string", new Expression("'8'", List.of())));
        codeBlock.addChild(
            new Assignment(
                    "a",
                    new Expression("a", List.of(new ExpressionOptional(Operand.SUM, "b")))
            )
        );
        codeBlock.addChild(new Print(new Expression("a", List.of())));
        assertEquals(codeBlock.toString(), parser.parse().toString());
    }

    @Test
    public void defaultParserTestForNonValidBlockShouldThrowException() {
        Parser<Node> parser =
            new DefaultParser(
                    TokenIterator.Companion.create(
                        "number",
                        List.of(
                            new Token(
                                    DefaultTokenTypes.KEYWORD,
                                    0,
                                    6,
                                    new LexicalRange(0, 0, 6, 0)
                            )
                        )
                    )
            );
        assertThrows(IllegalStateException.class, parser::parse);
    }
}
