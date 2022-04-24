package Lexer;

import Commons.Keyword;
import Commons.Separator;
import Commons.Type;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TokenizerV1_1 extends AbstractTokenizer {
    @Override
    protected boolean isKeyword(String currentString) {
        return Arrays.stream(Keyword.V1_1.values())
            .map(Keyword::getKeyword)
            .collect(Collectors.toList())
            .contains(currentString)
            || super.isKeyword(currentString);
    }

    @Override
    protected boolean isSeparator(String currentString) {
        return Arrays.stream(Separator.V1_1.values())
            .map(Separator::getSymbol)
            .collect(Collectors.toList())
            .contains(currentString.charAt(0))
            || super.isSeparator(currentString);
    }

    @Override
    protected boolean isType(String currentString) {
        return Arrays.stream(Type.V1_1.values())
            .map(Type::getType)
            .collect(Collectors.toList())
            .contains(currentString)
            || super.isType(currentString);
    }
}
