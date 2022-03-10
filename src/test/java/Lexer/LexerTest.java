package Lexer;

import org.junit.jupiter.api.Test;

class LexerTest {

    DefaultLexer lexer = new DefaultLexer();

    @Test
    public void operandLexerTest() {
        System.out.println(lexer.getTokens(" + \n + "));
    }

}