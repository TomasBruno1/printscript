package Lexer;

import java.util.Arrays;
import java.util.stream.Collectors;

import Commons.*;
import org.austral.ingsis.printscript.common.LexicalRange;
import org.austral.ingsis.printscript.common.Token;

public abstract class AbstractTokenizer implements Tokenizer {

    @Override
    public final Token tokenize(String currentString, int from, int fromCol, int col, int row)
            throws UnknownTokenException {
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
        } else if (isType(currentString)) {
            type = DefaultTokenTypes.TYPE;
        } else if (isLiteral(currentString)) {
            type = DefaultTokenTypes.LITERAL;
        } else if (isIdentifier(currentString)) {
            type = DefaultTokenTypes.IDENTIFIER;
        } else
            type = null;
        return type;
    }

    protected boolean isType(String currentString) {
        return Arrays.stream(Type.V1_0.values())
            .map(Type::getType)
            .collect(Collectors.toList())
            .contains(currentString);
    }

    protected boolean isKeyword(String currentString) {
        return Arrays.stream(Keyword.V1_0.values())
            .map(Keyword::getKeyword)
            .collect(Collectors.toList())
            .contains(currentString);
    }

    protected boolean isOperator(String currentString) {
        return (currentString.length() == 1)
            && Arrays.stream(Operator.values())
                .map(Operator::getOperator)
                .collect(Collectors.toList())
                .contains(currentString.charAt(0));
    }

    protected boolean isSeparator(String currentString) {
        return (currentString.length() == 1)
            && Arrays.stream(Separator.V1_0.values())
                .map(Separator::getSymbol)
                .collect(Collectors.toList())
                .contains(currentString.charAt(0));
    }

    protected boolean isLiteral(String currentString) {
        return (currentString.matches("[0-9]{1,9}(\\.[0-9]*)?|\"[\\s\\S][^\"]*\"|'[\\s\\S][^']*'|true|false"));
    }

    protected boolean isIdentifier(String currentString) {
        return (currentString.matches("[a-zA-Z_][a-zA-Z0-9_]*"));
    }
}
