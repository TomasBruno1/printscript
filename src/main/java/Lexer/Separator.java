package Lexer;

import lombok.Getter;

public enum Separator {
    EOL(';');

    @Getter
    final char separator;

    Separator(char separator) {
        this.separator = separator;
    }
}
