package Lexer;

import org.austral.ingsis.printscript.common.Token;

public interface Tokenizer {
    Token tokenize(String currentString, int from, int fromCol, int col, int row) throws UnknownTokenException;
}
