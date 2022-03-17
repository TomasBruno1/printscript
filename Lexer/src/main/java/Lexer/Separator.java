package Lexer;

import lombok.Getter;

public enum Separator {
    EOL(';'),
    TYPE(':');


    @Getter
    final char separator;

    Separator(char separator) {
        this.separator = separator;
    }
}
