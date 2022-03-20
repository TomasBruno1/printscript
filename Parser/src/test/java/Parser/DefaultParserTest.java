package Parser;

import AST.Node.Node;
import Lexer.DefaultTokenTypes;
import org.austral.ingsis.printscript.common.LexicalRange;
import org.austral.ingsis.printscript.common.Token;
import org.austral.ingsis.printscript.parser.TokenIterator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefaultParserTest {

    Parser<Node> parser = new DefaultParser(TokenIterator.Companion.create("let variable: string = 'Hello World!'; \nlet var:number=2+2.2;", List.of(
            new Token(DefaultTokenTypes.KEYWORD, 0, 3, new LexicalRange(0, 0, 3, 0)),
            new Token(DefaultTokenTypes.IDENTIFIER, 4, 12, new LexicalRange(4, 0, 12, 0)),
            new Token(DefaultTokenTypes.SEPARATOR, 12, 13, new LexicalRange(12, 0, 13, 0)),
            new Token(DefaultTokenTypes.KEYWORD, 14, 20, new LexicalRange(14, 0, 20, 0)),
            new Token(DefaultTokenTypes.OPERATOR, 21, 22, new LexicalRange(21, 0, 22, 0)),
            new Token(DefaultTokenTypes.LITERAL, 23, 37, new LexicalRange(23, 0, 37, 0)),
            new Token(DefaultTokenTypes.SEPARATOR, 37, 38, new LexicalRange(37, 0, 38, 0)),
            new Token(DefaultTokenTypes.KEYWORD, 40, 43, new LexicalRange(0, 1, 3, 1)),
            new Token(DefaultTokenTypes.IDENTIFIER, 44, 47, new LexicalRange(4, 1, 7, 1)),
            new Token(DefaultTokenTypes.SEPARATOR, 47, 48, new LexicalRange(7, 1, 8, 1)),
            new Token(DefaultTokenTypes.KEYWORD, 48, 54, new LexicalRange(8, 1, 14, 1)),
            new Token(DefaultTokenTypes.OPERATOR, 54, 55, new LexicalRange(14, 1, 15, 1)),
            new Token(DefaultTokenTypes.LITERAL, 55, 56, new LexicalRange(15, 1, 16, 1)),
            new Token(DefaultTokenTypes.OPERATOR, 56, 57, new LexicalRange(16, 1, 17, 1)),
            new Token(DefaultTokenTypes.LITERAL, 57, 60, new LexicalRange(17, 1, 20, 1)),
            new Token(DefaultTokenTypes.SEPARATOR, 60, 61, new LexicalRange(20, 1, 21, 1))
    )));

    @Test
    public void testParse() {
        parser.parse();

    }

}