package Lexer;

import ContentProvider.ContentProvider;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import org.austral.ingsis.printscript.common.Token;

@AllArgsConstructor
public class DefaultLexer implements Lexer {

    private final Tokenizer tokenizer;

    @Override
    public List<Token> lex(ContentProvider provider) throws UnknownTokenException, UnclosedStringLiteralException {
        String input = provider.getContent();
        List<Token> tokens = new ArrayList<>();

        int length = input.length();
        for (int i = 0, col = 0, row = 0; i < length; i++) {

            char currentChar = input.charAt(i);
            col++;

            StringBuilder currentString = new StringBuilder();
            currentString.append(currentChar);
            while (i < length - 1) {
                if (currentChar == '"' || currentChar == '\'') {
                    int nextQuoteMark = getNextQuoteMark(input, currentChar, i);
                    if (nextQuoteMark == -1)
                        throw new UnclosedStringLiteralException(col, row);
                    currentString.append(input, i + 1, i + nextQuoteMark + 2);
                    i = i + nextQuoteMark + 1;
                    col = col + nextQuoteMark + 1;
                    break;
                }
                if (
                    !Character.isLetterOrDigit(currentChar)
                        && currentChar != '.'
                        && currentChar != '_'
                )
                    break;

                char nextChar = input.charAt(i + 1);
                if (Character.isLetterOrDigit(nextChar) || nextChar == '.' || nextChar == '_') {
                    currentString.append(nextChar);
                    currentChar = nextChar;
                    col++;
                    i++;
                } else
                    break;
            }

            if (currentChar == '\n') {
                col = 0;
                row++;
            }

            if (currentString.length() != 0 && currentString.charAt(0) > 32) {
                tokens.add(
                    tokenizer.tokenize(
                        currentString.toString(),
                        i - currentString.length() + 1,
                        col - currentString.length(),
                        col,
                        row
                    )
                );
            }
        }
        return tokens;
    }

    private int getNextQuoteMark(String input, char currentChar, int i) {
        return currentChar == '"'
            ? (input.substring(i + 1)).indexOf('"')
            : (input.substring(i + 1)).indexOf('\'');
    }
}
