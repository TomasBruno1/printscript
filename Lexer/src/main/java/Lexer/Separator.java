package Lexer;

import lombok.Getter;

public enum Separator {
    EOL(';'),
    TYPE(':');
    // todo add parenthesis

    @Getter
    final char separator;

    Separator(char separator) {
        this.separator = separator;
    }
}
