package Lexer;

import Commons.Keyword;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TokenizerV1_1 extends AbstractTokenizer {
    @Override
    protected boolean isKeyword(String currentString) {
        return Arrays.stream(Keyword.V1_1.values())
                .map(Keyword::getKeyword)
                .collect(Collectors.toList())
                .contains(currentString) || super.isKeyword(currentString);
    }
}
