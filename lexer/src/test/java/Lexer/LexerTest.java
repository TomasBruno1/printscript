package Lexer;

import static org.junit.jupiter.api.Assertions.*;

import Commons.DefaultTokenTypes;
import ContentProvider.StringContentProvider;
import java.util.List;
import org.austral.ingsis.printscript.common.LexicalRange;
import org.austral.ingsis.printscript.common.Token;
import org.junit.jupiter.api.Test;

class LexerTest {

    Lexer lexer = new DefaultLexer();

    // + - * / ( ) =
    @Test
    public void operandLexerTest() {
        List<Token> tokens =
            List.of(
                new Token(DefaultTokenTypes.OPERATOR, 0, 1, new LexicalRange(0, 0, 1, 0)),
                new Token(DefaultTokenTypes.OPERATOR, 2, 3, new LexicalRange(2, 0, 3, 0)),
                new Token(DefaultTokenTypes.OPERATOR, 4, 5, new LexicalRange(4, 0, 5, 0)),
                new Token(DefaultTokenTypes.OPERATOR, 6, 7, new LexicalRange(6, 0, 7, 0)),
                new Token(DefaultTokenTypes.ASSIGN, 9, 10, new LexicalRange(0, 1, 1, 1))
            );

        List<Token> result = lexer.lex(new StringContentProvider("+ - * / \n= \n "));

        System.out.println(result);
        System.out.println(tokens);

        assertEquals(tokens.size(), result.size());
        assertTrue(result.containsAll(tokens));
    }

    @Test
    public void keywordLexerTest() {
        List<Token> tokens =
            List.of(
                new Token(DefaultTokenTypes.KEYWORD, 0, 3, new LexicalRange(0, 0, 3, 0)),
                new Token(DefaultTokenTypes.KEYWORD, 4, 11, new LexicalRange(4, 0, 11, 0)),
                new Token(DefaultTokenTypes.KEYWORD, 13, 19, new LexicalRange(0, 1, 6, 1)),
                new Token(DefaultTokenTypes.KEYWORD, 20, 26, new LexicalRange(7, 1, 13, 1)),
                new Token(DefaultTokenTypes.KEYWORD, 27, 34, new LexicalRange(0, 2, 7, 2))
            );

        List<Token> result =
            lexer.lex(new StringContentProvider("let println \nnumber string\nprintln "));

        System.out.println(result);
        System.out.println(tokens);

        assertEquals(tokens.size(), result.size());
        assertTrue(result.containsAll(tokens));
    }

    @Test
    public void operatorAndKeywordLexerTest() {
        List<Token> tokens =
            List.of(
                new Token(DefaultTokenTypes.OPERATOR, 1, 2, new LexicalRange(1, 0, 2, 0)),
                new Token(DefaultTokenTypes.KEYWORD, 4, 7, new LexicalRange(4, 0, 7, 0)),
                new Token(DefaultTokenTypes.KEYWORD, 9, 16, new LexicalRange(0, 1, 7, 1)),
                new Token(
                        DefaultTokenTypes.OPERATOR,
                        17,
                        18,
                        new LexicalRange(8, 1, 9, 1)
                )
            );

        List<Token> result = lexer.lex(new StringContentProvider(" +  let \nprintln * \n "));

        System.out.println(result);
        System.out.println(tokens);

        assertEquals(tokens.size(), result.size());
        assertTrue(result.containsAll(tokens));
    }

    @Test
    public void literalLexerTest() {
        List<Token> tokens =
            List.of(
                new Token(DefaultTokenTypes.LITERAL, 1, 16, new LexicalRange(1, 0, 16, 0)),
                new Token(DefaultTokenTypes.LITERAL, 18, 23, new LexicalRange(1, 1, 6, 1)),
                new Token(
                        DefaultTokenTypes.LITERAL,
                        23,
                        29,
                        new LexicalRange(6, 1, 12, 1)
                )
            );
        List<Token> result =
            lexer.lex(new StringContentProvider(" \"Hello\r World!\"\n 534.6'54.6'"));

        System.out.println(result);
        System.out.println(tokens);

        assertEquals(tokens.size(), result.size());
        assertTrue(result.containsAll(tokens));
    }

    @Test
    public void identifierLexerTest() {
        List<Token> tokens =
            List.of(
                new Token(
                        DefaultTokenTypes.IDENTIFIER,
                        1,
                        18,
                        new LexicalRange(1, 0, 18, 0)
                ),
                new Token(
                        DefaultTokenTypes.IDENTIFIER,
                        21,
                        38,
                        new LexicalRange(1, 1, 18, 1)
                )
            );
        List<Token> result =
            lexer.lex(new StringContentProvider(" variable_name_123 \n vArIaBlE_NamE_123 "));

        System.out.println(result);
        System.out.println(tokens);

        assertEquals(tokens.size(), result.size());
        assertTrue(result.containsAll(tokens));
    }

    @Test
    public void integrationLexerTest() {
        List<Token> tokens =
            List.of(
                new Token(DefaultTokenTypes.KEYWORD, 0, 3, new LexicalRange(0, 0, 3, 0)),
                new Token(
                        DefaultTokenTypes.IDENTIFIER,
                        4,
                        12,
                        new LexicalRange(4, 0, 12, 0)
                ),
                new Token(
                        DefaultTokenTypes.SEPARATOR,
                        12,
                        13,
                        new LexicalRange(12, 0, 13, 0)
                ),
                new Token(
                        DefaultTokenTypes.KEYWORD,
                        14,
                        20,
                        new LexicalRange(14, 0, 20, 0)
                ),
                new Token(DefaultTokenTypes.ASSIGN, 21, 22, new LexicalRange(21, 0, 22, 0)),
                new Token(
                        DefaultTokenTypes.LITERAL,
                        23,
                        37,
                        new LexicalRange(23, 0, 37, 0)
                ),
                new Token(
                        DefaultTokenTypes.SEPARATOR,
                        37,
                        38,
                        new LexicalRange(37, 0, 38, 0)
                ),
                new Token(DefaultTokenTypes.KEYWORD, 40, 43, new LexicalRange(0, 1, 3, 1)),
                new Token(
                        DefaultTokenTypes.IDENTIFIER,
                        44,
                        47,
                        new LexicalRange(4, 1, 7, 1)
                ),
                new Token(
                        DefaultTokenTypes.SEPARATOR,
                        47,
                        48,
                        new LexicalRange(7, 1, 8, 1)
                ),
                new Token(DefaultTokenTypes.KEYWORD, 48, 54, new LexicalRange(8, 1, 14, 1)),
                new Token(DefaultTokenTypes.ASSIGN, 54, 55, new LexicalRange(14, 1, 15, 1)),
                new Token(
                        DefaultTokenTypes.LITERAL,
                        55,
                        56,
                        new LexicalRange(15, 1, 16, 1)
                ),
                new Token(
                        DefaultTokenTypes.OPERATOR,
                        56,
                        57,
                        new LexicalRange(16, 1, 17, 1)
                ),
                new Token(
                        DefaultTokenTypes.LITERAL,
                        57,
                        60,
                        new LexicalRange(17, 1, 20, 1)
                ),
                new Token(
                        DefaultTokenTypes.SEPARATOR,
                        60,
                        61,
                        new LexicalRange(20, 1, 21, 1)
                )
            );
        List<Token> result =
            lexer.lex(
                new StringContentProvider(
                        "let variable: string = 'Hello World!'; \nlet var:number=2+2.2;"
                )
            );

        System.out.println(result);
        System.out.println(tokens);

        assertEquals(tokens.size(), result.size());
        assertTrue(result.containsAll(tokens));
    }

    @Test
    public void integrationLexerTest2() {
        List<Token> tokens =
            List.of(
                new Token(DefaultTokenTypes.LITERAL, 0, 19, new LexicalRange(0, 0, 19, 0)),
                new Token(
                        DefaultTokenTypes.SEPARATOR,
                        21,
                        22,
                        new LexicalRange(1, 1, 2, 1)
                ),
                new Token(DefaultTokenTypes.KEYWORD, 23, 26, new LexicalRange(3, 1, 6, 1)),
                new Token(DefaultTokenTypes.LITERAL, 27, 32, new LexicalRange(7, 1, 12, 1)),
                new Token(
                        DefaultTokenTypes.LITERAL,
                        32,
                        41,
                        new LexicalRange(12, 1, 21, 1)
                ),
                new Token(
                        DefaultTokenTypes.IDENTIFIER,
                        44,
                        52,
                        new LexicalRange(1, 2, 9, 2)
                ),
                new Token(DefaultTokenTypes.ASSIGN, 53, 54, new LexicalRange(10, 2, 11, 2)),
                new Token(
                        DefaultTokenTypes.LITERAL,
                        55,
                        56,
                        new LexicalRange(12, 2, 13, 2)
                )
            );
        List<Token> result =
            lexer.lex(
                new StringContentProvider(
                        "\"Hello\n World! let\"\n ; let 534.6'54let.6' \n variable = 2"
                )
            );

        System.out.println(result);
        System.out.println(tokens);

        assertEquals(tokens.size(), result.size());
        assertTrue(result.containsAll(tokens));
    }
}
