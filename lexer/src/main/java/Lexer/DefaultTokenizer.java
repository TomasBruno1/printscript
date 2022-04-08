package Lexer;

import java.util.Arrays;
import java.util.stream.Collectors;

import Commons.DefaultTokenTypes;
import Commons.Keyword;
import Commons.Operator;
import Commons.Separator;
import org.austral.ingsis.printscript.common.LexicalRange;
import org.austral.ingsis.printscript.common.Token;

public class DefaultTokenizer implements Tokenizer {

    @Override
    public Token tokenize(String currentString, int from, int fromCol, int col, int row) throws UnknownTokenException {
        DefaultTokenTypes type = getType(currentString);
        if (type == null)
            throw new UnknownTokenException(currentString, fromCol, row);
        return new Token(
                type,
                from,
                from + currentString.length(),
                new LexicalRange(fromCol, row, col, row)
        );
    }

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
            type = null;
        return type;
    }

    private boolean isKeyword(String currentString) {
        return Arrays.stream(Keyword.values())
            .map(Keyword::getKeyword)
            .collect(Collectors.toList())
            .contains(currentString);
    }

    private boolean isOperator(String currentString) {
        return (currentString.length() == 1)
            && Arrays.stream(Operator.values())
                .map(Operator::getOperator)
                .collect(Collectors.toList())
                .contains(currentString.charAt(0));
    }

    private boolean isSeparator(String currentString) {
        return (currentString.length() == 1)
            && Arrays.stream(Separator.values())
                .map(Separator::getSeparator)
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
