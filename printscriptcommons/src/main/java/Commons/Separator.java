package Commons;

import lombok.Getter;

public enum Separator {
    EOL(';'),
    LEFT_PARENTHESIS('('),
    RIGHT_PARENTHESIS(')'),
    TYPE(':');

    @Getter
    final char separator;

    Separator(char separator) {
        this.separator = separator;
    }
}
