package Lexer;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.austral.ingsis.printscript.common.LexicalRange;
import org.austral.ingsis.printscript.common.Token;
import org.jetbrains.annotations.NotNull;

public class DefaultTokenizer implements Tokenizer {

    @Override
    public Token tokenize(String currentString, int from, int fromCol, int col, int row) {
        DefaultTokenTypes type = getType(currentString);
        return new Token(
                type,
                from,
                from + currentString.length(),
                new LexicalRange(fromCol, row, col, row)
        );
    }

    @NotNull
    private DefaultTokenTypes getType(String currentString) {
        DefaultTokenTypes type;
        if (isOperator(currentString)) {
            if (currentString.equals("="))
                type = DefaultTokenTypes.ASSIGN;
            else
                type = DefaultTokenTypes.OPERATOR;
        } else if (isSeparator(currentString)) {
            type = DefaultTokenTypes.SEPARATOR;
        } else if (isKeyword(currentString)) {
            type = DefaultTokenTypes.KEYWORD;
        } else if (isLiteral(currentString)) {
            type = DefaultTokenTypes.LITERAL;
        } else if (isIdentifier(currentString)) {
            type = DefaultTokenTypes.IDENTIFIER;
        } else
            throw new IllegalArgumentException("Unknown token: " + currentString);
        return type;
    }

    private boolean isKeyword(String currentString) {
        return Arrays.stream(Keyword.values())
            .map(keyword -> keyword.getKeyword())
            .collect(Collectors.toList())
            .contains(currentString);
    }

    private boolean isOperator(String currentString) {
        return (currentString.length() == 1)
            && Arrays.stream(Operator.values())
                .map(operator -> operator.getOperator())
                .collect(Collectors.toList())
                .contains(currentString.charAt(0));
    }

    private boolean isSeparator(String currentString) {
        return (currentString.length() == 1)
            && Arrays.stream(Separator.values())
                .map(separator -> separator.getSeparator())
                .collect(Collectors.toList())
                .contains(currentString.charAt(0));
    }

    private boolean isLiteral(String currentString) {
        return (currentString.matches("[0-9]{1,9}(\\.[0-9]*)?|\"[\\s\\S][^\"]*\"|'[\\s\\S][^']*'"));
    }

    private boolean isIdentifier(String currentString) {
        return (currentString.matches("[a-zA-Z_][a-zA-Z0-9_]*"));
    }
}
