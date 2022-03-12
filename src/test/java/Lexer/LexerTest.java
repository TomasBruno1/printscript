package Lexer;

import org.austral.ingsis.printscript.common.LexicalRange;
import org.austral.ingsis.printscript.common.Token;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class LexerTest {

    Lexer lexer = new DefaultLexer();

    // + - * / ( ) =
    @Test
    public void operandLexerTest() {
        List<Token> tokens = List.of(
                new Token(DefaultTokenTypes.OPERATOR, 0, 1, new LexicalRange(0, 0, 1, 0)),
                new Token(DefaultTokenTypes.OPERATOR, 2, 3, new LexicalRange(2, 0, 3, 0)),
                new Token(DefaultTokenTypes.OPERATOR, 4, 5, new LexicalRange(4, 0, 5, 0)),
                new Token(DefaultTokenTypes.OPERATOR, 6, 7, new LexicalRange(6, 0, 7, 0)),
                new Token(DefaultTokenTypes.OPERATOR, 9, 10, new LexicalRange(0, 1, 1, 1)),
                new Token(DefaultTokenTypes.OPERATOR, 13, 14, new LexicalRange(1, 2, 2, 2)),
                new Token(DefaultTokenTypes.OPERATOR, 15, 16, new LexicalRange(3, 2, 4, 2))
        );

        List<Token> result = lexer.getTokens("+ - * / \n= \n ( )");

        System.out.println(result);
        System.out.println(tokens);

        assertEquals(tokens.size(), result.size());
        assertTrue(result.containsAll(tokens));

    }

    @Test
    public void keywordLexerTest() {
        List<Token> tokens = List.of(
                new Token(DefaultTokenTypes.KEYWORD, 0, 3, new LexicalRange(0, 0, 3, 0)),
                new Token(DefaultTokenTypes.KEYWORD, 4, 11, new LexicalRange(4, 0, 11, 0)),
                new Token(DefaultTokenTypes.KEYWORD, 13, 16, new LexicalRange(0, 1, 3, 1)),
                new Token(DefaultTokenTypes.KEYWORD, 17, 20, new LexicalRange(4, 1, 7, 1)),
                new Token(DefaultTokenTypes.KEYWORD, 21, 28, new LexicalRange(0, 2, 7, 2))
        );

        List<Token> result = lexer.getTokens("let println \nlet let\nprintln");

        System.out.println(result);
        System.out.println(tokens);

        assertEquals(tokens.size(), result.size());
        assertTrue(result.containsAll(tokens));
    }

    @Test
    public void operatorAndKeywordLexerTest() {
        List<Token> tokens = List.of(
                new Token(DefaultTokenTypes.OPERATOR, 1, 2, new LexicalRange(1, 0, 2, 0)),
                new Token(DefaultTokenTypes.KEYWORD, 4, 7, new LexicalRange(4, 0, 7, 0)),
                new Token(DefaultTokenTypes.KEYWORD, 9, 16, new LexicalRange(0, 1, 7, 1)),
                new Token(DefaultTokenTypes.OPERATOR, 17, 18, new LexicalRange(8, 1, 9, 1))
        );

        List<Token> result = lexer.getTokens(" +  let \nprintln * \n ");

        System.out.println(result);
        System.out.println(tokens);

        assertEquals(tokens.size(), result.size());
        assertTrue(result.containsAll(tokens));
    }

    @Test
    public void literalLexerTest(){
        List<Token> tokens = List.of(
                new Token(DefaultTokenTypes.LITERAL, 1, 16, new LexicalRange(1, 0, 16, 0)),
                new Token(DefaultTokenTypes.LITERAL, 18, 23, new LexicalRange(1, 1, 6, 1)),
                new Token(DefaultTokenTypes.LITERAL, 23, 29, new LexicalRange(6, 1, 12, 1))
        );
        List<Token> result = lexer.getTokens(" \"Hello\r World!\"\n 534.6'54.6'");

        System.out.println(result);
        System.out.println(tokens);

        assertEquals(tokens.size(), result.size());
        assertTrue(result.containsAll(tokens));
    }

}